package model;

/**
 * This is an interface for the Stock model's methods.
 */
public interface StockModel {
  /**
   * Retrieves the ticker of the current company.
   * @return the ticker
   */
  String getTicker();

  /**
   * Retrieves the company's stock code by the given name.
   * @param company name of the company to get the stock for
   * @return the ticker for the company
   */
  String getTicker(String company);

  /**
   * Retrieves the company's opening value for the day.
   * @param ticker the company's stock code
   * @param date the date to retrieve the value for
   * @return the opening value
   * @throws IllegalArgumentException exception for invalid arguments
   */
  Double getOpeningValue(String ticker, String date) throws IllegalArgumentException;

  /**
   * Retrieves the company's high value for the day.
   * @param ticker the company's stock code
   * @param date the date to retrieve the value for
   * @return the high value
   * @throws IllegalArgumentException exception for invalid arguments
   */
  Double getHighValue(String ticker, String date) throws IllegalArgumentException;

  /**
   * Retrieves the company's low value for the day.
   * @param ticker the company's stock code
   * @param date the date to retrieve the value for
   * @return the low value
   * @throws IllegalArgumentException exception for invalid arguments
   */
  Double getLowValue(String ticker, String date) throws IllegalArgumentException;

  /**
   * Retrieves the company's closing value for the day.
   * @param ticker the company's stock code
   * @param date the date to retrieve the value for
   * @return the closing value
   * @throws IllegalArgumentException exception for invalid arguments
   */
  Double getClosingValue(String ticker, String date) throws IllegalArgumentException;

  /**
   * Retrieves the number of available shares for a stock for a day.
   * @param ticker the company's stock code
   * @param date the date to retrieve the value for
   * @return the volume of shares available
   * @throws IllegalArgumentException exception for invalid arguments
   */
  Double getAvailableShares(String ticker, String date) throws IllegalArgumentException;

  /**
   * Retrieves the shares of the current company.
   * @return the shares
   */
  int getShares();

  /**
   * Retrieves the cost of all shares of one company.
   * @return the cost
   */
  double getCost();

  /**
   * Retrieves the composition of the current company.
   * @return the composition
   */
  String getActiveComposition();
}