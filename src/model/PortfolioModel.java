package model;

import java.io.IOException;

/**
 * This portfolio model interface allows implementing methods for the PortfolioModel class.
 */
public interface PortfolioModel {
  /**
   * Creates a new portfolio.
   *
   * @param portfolioName the name to be given to the new portfolio
   * @throws IllegalArgumentException exception for invalid arguments
   */
  void createPortfolio(String portfolioName) throws IllegalArgumentException;

  /**
   * Buys shares to a stock for a newly created portfolio.
   *
   * @param portfolioName name of the portfolio for the stocks to be addded to
   * @param stock the stock to buy shares from
   * @param shares number of shares to buy
   * @return the total stock amount
   * @throws IllegalArgumentException exception for invalid arguments
   */
  double buyStock(String portfolioName, String stock, int shares)
          throws IllegalArgumentException;

  /**
   * Retrieves the composition of a portfolio.
   *
   * @param portfolioName name of the portfolio to be retrieved information from
   * @return composition of the portfolio
   * @throws IllegalArgumentException exception for invalid arguments
   */
  String getPortfolioComposition(String portfolioName) throws IllegalArgumentException;

  /**
   * Retrieves value of the portfolio for a given date.
   *
   * @param portfolioName name of the portfolio to be retrieved information from
   * @param date          the date for the information to be retrieved from
   * @return total value of the portfolio
   * @throws IllegalArgumentException exception for invalid arguments
   */
  double getPortfolioValueByDate(String portfolioName, String date) throws IllegalArgumentException;

  /**
   * Retrieves all portfolios.
   *
   * @return all portfolios and their compositions
   */
  String getAllPortfolios();

  /**
   * To automatically save every portfolio into a CSV file.
   */
  void savePortfolio();

  /**
   * To load existing CSV files into the program.
   *
   * @param fileName the name of the file to be retrieved
   * @throws IOException throws I/O exception
   */
  void loadPortfolio(String fileName) throws IOException;
}
