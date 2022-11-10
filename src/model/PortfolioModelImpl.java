package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * This class is an implementation of the portfolio model's methods.
 */
public class PortfolioModelImpl implements PortfolioModel {

  private Map<String, Map<String, StockModel>> portfolio;
  private StockModelImpl stockModel;

  /**
   * This is an empty constructor for no-argument initialization.
   */
  public PortfolioModelImpl() {
    this.portfolio = new HashMap<>();
    this.stockModel = new StockModelImpl();
  }

  /**
   * Constructs a PortfolioModelImpl object with portfolioName as the argument.
   * @param portfolioName the name to be given to the new portfolio
   * @throws IllegalArgumentException exception for invalid arguments
   */
  @Override
  public void createPortfolio(String portfolioName) throws IllegalArgumentException {
    if (portfolioName == null || portfolioName.isEmpty()) {
      throw new IllegalArgumentException("Portfolio name is invalid.");
    }

    if (portfolio.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio name already taken.");
    }

    portfolio.put(portfolioName, new HashMap<>());
  }

  /**
   * To get a valid current date to retrieve data.
   * @param ticker the company to retrieve the data for
   * @return the extracted date
   */
  private String getDate(String ticker) {
    String newDate = "";

    for (int i = 0; i < 10; i++) {
      Calendar today = Calendar.getInstance();
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      today.setTime(new Date());
      today.add(Calendar.DAY_OF_YEAR, -i);
      newDate = format.format(today.getTime());
      stockModel.getClosingValue(ticker, newDate);
      break;
    }
    return newDate;
  }

  @Override
  public double buyStock(String portfolioName, String stock, int shares)
          throws IllegalArgumentException {

    if (shares <= 0) {
      throw new IllegalArgumentException("Invalid number of shares.");
    }

    if (!portfolio.containsKey(portfolioName)) {
      throw new IllegalArgumentException("This portfolio does not exist.");
    }

    String ticker;
    double cost;
    Map<String, StockModel> activePortfolio = portfolio.get(portfolioName);

    try {
      ticker = stockModel.getTicker(stock);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("No ticker data for company " + stock + ".");
    }

    try {
      String date = getDate(ticker);
      cost = stockModel.getClosingValue(ticker, date);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("No cost data for this date.");
    }

    if (activePortfolio.get(ticker) != null) {
      StockModel s = activePortfolio.get(ticker);
      activePortfolio.remove(ticker);
      activePortfolio.put(ticker, new StockModelImpl(ticker, s.getShares() + shares, cost));
    } else {
      activePortfolio.put(ticker, new StockModelImpl(ticker, shares, cost));
    }

    return cost * shares;
  }

  @Override
  public String getPortfolioComposition(String portfolioName) throws IllegalArgumentException {
    if (!portfolio.containsKey(portfolioName)) {
      throw new IllegalArgumentException("This portfolio does not exist.");
    }

    StringBuilder composition = new StringBuilder();

    for (StockModel s : portfolio.get(portfolioName).values()) {
      composition.append(s.getActiveComposition()).append("\n");
    }

    return composition.toString();
  }

  @Override
  public double getPortfolioValueByDate(String portfolioName, String date)
          throws IllegalArgumentException {
    if (!portfolio.containsKey(portfolioName)) {
      throw new IllegalArgumentException("This portfolio does not exist.");
    }

    Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");

    if (date == null || date.isEmpty() || !pattern.matcher(date).matches()) {
      throw new IllegalArgumentException("Invalid date.");
    }

    return portfolio.get(portfolioName).values()
            .stream()
            .mapToDouble(b -> stockModel.getHighValue(b.getTicker(), date) * b.getShares())
            .sum();
  }

  @Override
  public String getAllPortfolios() {
    StringBuilder portfolios = new StringBuilder();

    for (String pf : portfolio.keySet()) {
      portfolios.append(getPortfolioComposition(pf)).append("\n");
    }

    return portfolios.toString();
  }

  /**
   * Writes the portfolios to a CSV file.
   * @param fileName name of the file to be written to.
   * @param content content to be written to the file.
   */
  private void downloadToFile(String fileName, String content) {
    try {
      File writeName = new File(fileName);
      writeName.createNewFile();
      BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
      out.write(content);
      out.flush();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void savePortfolio() {
    for (String pf : portfolio.keySet()) {
      StringBuilder saved = new StringBuilder().append("Stock,Shares,Cost").append("\n");
      String res;
      double total = 0;
      for (StockModel s : portfolio.get(pf).values()) {
        saved.append(s.getTicker()).append(",")
                .append(s.getShares()).append(",")
                .append(s.getCost())
                .append("\n");
        total = total + s.getCost();
      }
      saved.append("Total,,").append(total);
      res = saved.toString();
      downloadToFile(pf + ".csv", res);
    }
  }

  @Override
  public void loadPortfolio(String fileName) throws IOException {
    if (!(fileName.endsWith(".csv"))) {
      throw new IllegalArgumentException("Wrong format.");
    }

    String portfolioName = fileName.substring(0, fileName.length() - 4);
    createPortfolio(portfolioName);
    BufferedReader b = new BufferedReader(new FileReader(fileName));
    String line;
    int counter = 0;
    while ((line = b.readLine()) != null) {
      String[] cols = line.split(",");
      if (counter != 0 && !(cols[0].equals("Total"))) {
        Map<String, StockModel> activePortfolio = this.portfolio.get(portfolioName);
        String ticker = cols[0];
        int shares;
        double cost;

        try {
          shares = Integer.parseInt(cols[1]);
        } catch (Exception e) {
          throw new IllegalArgumentException("Share should be an integer.");
        }

        try {
          cost = Double.parseDouble(cols[2]);
        } catch (Exception e) {
          throw new IllegalArgumentException("Cost should be a double.");
        }

        activePortfolio.put(ticker, new StockModelImpl(ticker, shares, cost));
      }
      counter++;
    }
  }
}
