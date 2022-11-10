package model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * The test class for the Portfolio Model.
 */
public class PortfolioModelImplTest {
  private PortfolioModel portfolioModel = new PortfolioModelImpl();

  @Before
  public void setUp() {
    portfolioModel.createPortfolio("purple");
    portfolioModel.buyStock("purple", "GOOG", 69);
    portfolioModel.buyStock("purple", "AAPL", 12);
    portfolioModel.createPortfolio("sublime");
    portfolioModel.buyStock("sublime", "AMZN", 13);
    portfolioModel.buyStock("sublime", "META", 420);
  }

  @Test
  public void buyStock() {
    try {
      portfolioModel.createPortfolio("purple");
    } catch (IllegalArgumentException e) {
      assertEquals("Portfolio name already taken.", e.getMessage());
    }

    portfolioModel.createPortfolio("fork");
    double cost1 = portfolioModel.buyStock("fork", "GOOG", 69);
    double cost2 = portfolioModel.buyStock("fork", "AAPL", 12);
    assertEquals(6007.83, cost1, 0);
    assertEquals(1044.84, cost2, 0);

    portfolioModel.buyStock("purple", "GOOG", 10);
  }

  @Test
  public void loadPortfolio() throws IOException {
    try {
      portfolioModel.loadPortfolio("herlic.csv");
    } catch (IllegalArgumentException e) {
      assertEquals("No cost data for this date.", e.getMessage());
    }

    try {
      portfolioModel.loadPortfolio("herlic");
    } catch (IllegalArgumentException e) {
      assertEquals("Wrong format.", e.getMessage());
    }
  }

  @Test
  public void savePortfolio() {
    try {
      portfolioModel.savePortfolio();
    } catch (IllegalArgumentException e) {
      assertEquals("No cost data for this date.", e.getMessage());
    }
  }

  @Test
  public void getPortfolioComposition() {
    assertEquals("Ticker: GOOG, Shares: 69, Cost: 87.070000\n"
                    + "Ticker: AAPL, Shares: 12, Cost: 87.070000\n",
            portfolioModel.getPortfolioComposition("purple"));

    try {
      portfolioModel.getPortfolioComposition("jules");
    } catch (IllegalArgumentException e) {
      assertEquals("This portfolio does not exist.", e.getMessage());
    }
  }

  @Test
  public void getPortfolioValueByDate() {
    assertEquals(8736.93, portfolioModel.getPortfolioValueByDate("purple",
            "2022-10-17"), 0);

    try {
      portfolioModel.getPortfolioValueByDate("uno", "");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid date.", e.getMessage());
    }

    try {
      portfolioModel.getPortfolioValueByDate("purple", "201-19-1");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid date.", e.getMessage());
    }
  }

  @Test
  public void getAllPortfolios() {
    assertEquals("Ticker: GOOG, Shares: 69, Cost: 87.070000\n"
           + "Ticker: AAPL, Shares: 12, Cost: 87.070000\n"
           + "\n"
           + "Ticker: META, Shares: 420, Cost: 104.930000\n"
           + "Ticker: AMZN, Shares: 13, Cost: 104.930000\n"
           + "\n", portfolioModel.getAllPortfolios());

    try {
      portfolioModel.getAllPortfolios();
    } catch (IllegalArgumentException e) {
      assertEquals("No cost data for this date.", e.getMessage());
    }
  }
}