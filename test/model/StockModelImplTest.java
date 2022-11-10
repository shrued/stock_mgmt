package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The test class for the Stock Model.
 */
public class StockModelImplTest {
  private StockModel google;
  private StockModel apple;

  @Before
  public void setUp() {
    google = new StockModelImpl("GOOG", 69, 243.2);
    apple = new StockModelImpl("AAPL", 12, 3455.325645);
  }

  @Test
  public void testValidConstructor() {
    StockModel s;
    s = new StockModelImpl("AMZN", 12, 34.45);
    assertEquals("Ticker: AMZN, Shares: 12, Cost: 34.450000", s.getActiveComposition());

    s = new StockModelImpl();
    assertEquals(null, s.getTicker());
  }

  @Test
  public void testInvalidConstructor() {
    try {
      new StockModelImpl("", 12, 34.45);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters.", e.getMessage());
    }

    try {
      new StockModelImpl(null, 12, 34.45);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters.", e.getMessage());
    }

    try {
      new StockModelImpl("AMZN", 0, 563.3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters.", e.getMessage());
    }

    try {
      new StockModelImpl("AMZN", -18, 563.3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters.", e.getMessage());
    }

    try {
      new StockModelImpl("AMZN", 420, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters.", e.getMessage());
    }

    try {
      new StockModelImpl("AMZN", 420, -453.2);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters.", e.getMessage());
    }

  }

  @Test
  public void testGetTicker1() {
    assertEquals("GOOG", google.getTicker("GOOG"));
    assertEquals("AAPL", google.getTicker("AAPL"));
    assertEquals("GOOG", google.getTicker());
    assertEquals("AAPL", apple.getTicker());
  }

  @Test
  public void testGetTicker2() {
    try {
      google.getTicker("Ghost");
    } catch (IllegalArgumentException e) {
      assertEquals("No ticker data found for Ghost.", e.getMessage());
    }
  }

  @Test
  public void testGetShares() {
    assertEquals(69, google.getShares());
    assertEquals(12, apple.getShares());
  }

  @Test
  public void testGetCost() {
    assertEquals(243.2, google.getCost(), 0);
    assertEquals(3455.325645, apple.getCost(), 0);
  }

  @Test
  public void testGetActiveComposition() {
    assertEquals("Ticker: GOOG, Shares: 69, Cost: 243.200000",
            google.getActiveComposition());
    assertEquals("Ticker: AAPL, Shares: 12, Cost: 3455.325645",
            apple.getActiveComposition());
  }

  @Test
  public void testGetOpeningValue() {
    assertEquals(95.59, google.getOpeningValue("GOOG", "2022-11-01"), 0);

    try {
      assertEquals(95.59, google.getOpeningValue("GOOG", "2022-12-01"),
              0);
    } catch (IllegalArgumentException e) {
      assertEquals("No data found for this date.", e.getMessage());
    }
  }

  @Test
  public void testGetHighValue() {
    assertEquals(96.165, google.getHighValue("GOOG", "2022-11-01"), 0);

    try {
      assertEquals(95.59, google.getOpeningValue("GOOG", "2022-12-01"),
              0);
    } catch (IllegalArgumentException e) {
      assertEquals("No data found for this date.", e.getMessage());
    }
  }

  @Test
  public void testGetLowValue() {
    assertEquals(90.43, google.getLowValue("GOOG", "2022-11-01"), 0);

    try {
      assertEquals(95.59, google.getOpeningValue("GOOG", "2022-12-01"),
              0);
    } catch (IllegalArgumentException e) {
      assertEquals("No data found for this date.", e.getMessage());
    }
  }

  @Test
  public void testGetClosingValue() {
    assertEquals(90.5, google.getClosingValue("GOOG", "2022-11-01"), 0);

    try {
      assertEquals(95.59, google.getOpeningValue("GOOG", "2022-12-01"),
              0);
    } catch (IllegalArgumentException e) {
      assertEquals("No data found for this date.", e.getMessage());
    }
  }

  @Test
  public void testGetAvailableShares() {
    assertEquals(4.3220599E7, google.getAvailableShares("GOOG", "2022-11-01"),
            0);

    try {
      assertEquals(95.59, google.getOpeningValue("GOOG", "2022-12-01"),
              0);
    } catch (IllegalArgumentException e) {
      assertEquals("No data found for this date.", e.getMessage());
    }
  }
}