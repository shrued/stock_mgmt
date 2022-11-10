package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the methods in the stock model.
 */
public class StockModelImpl implements StockModel {
  private String apiKey;
  private URL url;
  private Map<String, String> stocks;
  private Map<String, Map<String, Map<String, Double>>> costs;
  private final String ticker;
  private final int shares;
  private final double cost;

  /**
   * This is an empty constructor for no-argument initialization.
   */
  public StockModelImpl() {
    apiKey = "N8LVC6JOGADHP6RE";
    url = null;
    this.stocks = new HashMap<>();
    this.costs = new HashMap<>();
    this.ticker = null;
    this.shares = 0;
    this.cost = 0;
  }

  /**
   * Constructs a StockModelImpl object with portfolioName as the argument.
   * @param ticker the company's stock code
   * @param shares the shares for the stock
   * @param cost the cost of all shares for the company
   * @throws IllegalArgumentException exception for invalid arguments
   */
  StockModelImpl(String ticker, int shares, double cost) throws IllegalArgumentException {
    if (ticker == null || ticker.isEmpty() || shares <= 0 || cost <= 0) {
      throw new IllegalArgumentException("Invalid parameters.");
    }

    apiKey = "N8LVC6JOGADHP6RE";
    url = null;
    this.stocks = new HashMap<>();
    this.costs = new HashMap<>();
    this.ticker = ticker;
    this.shares = shares;
    this.cost = cost;
  }

  /**
   * Constructs the URL to call the time series daily API.
   * @param ticker the company's stock code
   */
  private void buildURL(String ticker) {
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + ticker + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("The Alpha Vantage API has either changed or no longer works.");
    }
  }

  /**
   * Calls the API to collect the stock data from the API.
   * @param ticker the company's stock code
   */
  private void callAPI(String ticker) {
    buildURL(ticker.toUpperCase());

    InputStream is;
    StringBuilder sb = new StringBuilder();

    try {
      is = url.openStream();
      int b;

      while ((b = is.read()) != -1) {
        sb.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No cost data found for " + ticker + ".");
    }
    Map<String, Map<String, Double>> data = new HashMap<>();
    String[] value1 = sb.toString().split("\n");
    String[] type = {"open", "high", "low", "close", "volume"};
    for (int i = 1; i < value1.length; i++) {
      String[] value2 = value1[i].split(",");
      data.put(value2[0], new HashMap<>());
      for (int j = 1; j < value2.length; j++) {
        data.get(value2[0]).put(type[j - 1], Double.parseDouble(value2[j]));
      }
    }
    this.costs.put(ticker, data);
  }

  /**
   * Calls the API to collect the stock ticker from the API.
   * @param company the company's name
   */
  private void callAPIForTicker(String company) {
    URL url;

    try {
      url = new URL("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&"
              + "keywords=" + company
              + "&apikey=" + apiKey
              + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("The Alpha Vantage API has either changed or no longer works.");
    }

    InputStream is;
    StringBuilder sb = new StringBuilder();

    try {
      is = url.openStream();
      int b;

      while ((b = is.read()) != -1) {
        sb.append((char) b);
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("No ticker data found for " + company + ".");
    }

    String[] value1 = sb.toString().split("\n");
    if (value1.length <= 1) {
      throw new IllegalArgumentException("No ticker data found for " + company + ".");
    }
    String[] value2 = value1[1].split(",");
    this.stocks.put(company, value2[0]);
  }

  @Override
  public String getTicker() {
    return this.ticker;
  }

  @Override
  public String getTicker(String company) {
    if (!this.stocks.containsKey(company)) {
      callAPIForTicker(company);
    }
    return this.stocks.get(company);
  }

  @Override
  public Double getOpeningValue(String ticker, String date) throws IllegalArgumentException {
    if (!costs.containsKey(ticker)) {
      callAPI(ticker);
    }
    Map<String, Map<String, Double>> data = this.costs.get(ticker);
    Double val;
    try {
      val = data
              .get(date)
              .get("open");
    } catch (Exception e) {
      throw new IllegalArgumentException("No data found for this date.");
    }
    return val;
  }

  @Override
  public Double getHighValue(String ticker, String date) throws IllegalArgumentException {
    if (!costs.containsKey(ticker)) {
      callAPI(ticker);
    }
    Map<String, Map<String, Double>> data = this.costs.get(ticker);
    Double val;
    try {
      val = data
              .get(date)
              .get("high");
    } catch (Exception e) {
      throw new IllegalArgumentException("No data found for this date.");
    }
    return val;
  }

  @Override
  public Double getLowValue(String ticker, String date) throws IllegalArgumentException {
    if (!costs.containsKey(ticker)) {
      callAPI(ticker);
    }
    Map<String, Map<String, Double>> data = this.costs.get(ticker);
    Double val;
    try {
      val = data
              .get(date)
              .get("low");
    } catch (Exception e) {
      throw new IllegalArgumentException("No data found for this date.");
    }
    return val;
  }

  @Override
  public Double getClosingValue(String ticker, String date) throws IllegalArgumentException {
    if (!costs.containsKey(ticker)) {
      callAPI(ticker);
    }
    Map<String, Map<String, Double>> data = this.costs.get(ticker);
    Double val;
    try {
      val = data
              .get(date)
              .get("close");
    } catch (Exception e) {
      throw new IllegalArgumentException("No data found for this date.");
    }
    return val;
  }

  @Override
  public Double getAvailableShares(String ticker, String date) throws IllegalArgumentException {
    if (!costs.containsKey(ticker)) {
      callAPI(ticker);
    }

    Map<String, Map<String, Double>> data = this.costs.get(ticker);
    Double val;
    try {
      val = data
              .get(date)
              .get("volume");
    } catch (Exception e) {
      throw new IllegalArgumentException("No data found for this date.");
    }
    return val;
  }

  @Override
  public int getShares() {
    return this.shares;
  }

  @Override
  public double getCost() {
    return this.cost;
  }

  @Override
  public String getActiveComposition() {
    return String.format("Ticker: %s, Shares: %d, Cost: %f", this.ticker, this.shares, this.cost);
  }
}