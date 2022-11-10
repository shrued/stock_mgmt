package controller;

import java.io.IOException;

import model.PortfolioModelImpl;

/**
 * This stock controller interface allows implementing methods for the StockController class.
 */
public interface StockController {
  /**
   * The main method for the controller that calls the model's methods.
   * @param stock the model for a stock
   * @throws IOException throws I/O exception
   */
  void begin(PortfolioModelImpl stock) throws IOException;
}
