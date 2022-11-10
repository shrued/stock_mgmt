package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import model.PortfolioModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * The test class for the Stock Controller.
 */
public class StockControllerImplTest {

  PortfolioModelImpl stockModel = new PortfolioModelImpl();
  Reader in;
  StringBuffer out;
  StockControllerImpl stockController;

  @Test
  public void testCreate1() throws IOException {
    in = new StringReader("1 garlic N q\n");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
           + "\tStock Trading System\n"
           + "1. Create new portfolio\n"
           + "2. Upload existing portfolio\n"
           + "3. View Composition \n"
           + "4. View portfolio value\n"
           + "5. View portfolio value by date\n"
           + "6. View all portfolios\n"
           + "7. Exit(q/Q)\n"
           + "Choose an option.\n"
           + "Enter the new portfolio's name:\n"
           + "New portfolio created. Do you want to add shares? (Y/N)\n"
           + "\tStock Trading System\n"
           + "1. Create new portfolio\n"
           + "2. Upload existing portfolio\n"
           + "3. View Composition \n"
           + "4. View portfolio value\n"
           + "5. View portfolio value by date\n"
           + "6. View all portfolios\n"
           + "7. Exit(q/Q)\n"
           + "Choose an option.\n"
           + "Exit.\n", out.toString());
  }

  @Test
  public void testCreate2() throws IOException {
    in = new StringReader("1 q \n"
            + "Y garlic GOOG \n 69 "
            + "2 \ngarlic.csv \n"
            + "3 \ngarlic \n"
            + "4 \n garlic \n"
            + "5 \ngarlic 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n\tStock Trading System\n"
           + "1. Create new portfolio\n"
           + "2. Upload existing portfolio\n"
           + "3. View Composition \n"
           + "4. View portfolio value\n"
           + "5. View portfolio value by date\n"
           + "6. View all portfolios\n"
           + "7. Exit(q/Q)\n"
           + "Choose an option.\n"
           + "Enter the new portfolio's name:\n"
           + "Exit.\n", out.toString());
  }

  @Test
  public void testBuy() throws IOException {
    in = new StringReader("1 garlic Y\n"
            + "q GOOG \n 69 "
            + "2 \ngarlic.csv \n"
            + "3 \ngarlic \n"
            + "4 \n garlic \n"
            + "5 \ngarlic 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
           + "\tStock Trading System\n"
           + "1. Create new portfolio\n"
           + "2. Upload existing portfolio\n"
           + "3. View Composition \n"
           + "4. View portfolio value\n"
           + "5. View portfolio value by date\n"
           + "6. View all portfolios\n"
           + "7. Exit(q/Q)\n"
           + "Choose an option.\n"
           + "Enter the new portfolio's name:\n"
           + "New portfolio created. Do you want to add shares? (Y/N)\n"
           + "Which company do you want to buy shares for? (Enter ticker)\n"
           + "Exit.\n", out.toString());
  }

  @Test
  public void testBuy2() throws IOException {
    in = new StringReader("1 garlic Y\n"
            + "GOOG \n q 69 "
            + "2 \ngarlic.csv \n"
            + "3 \ngarlic \n"
            + "4 \n garlic \n"
            + "5 \ngarlic 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
           + "\tStock Trading System\n"
           + "1. Create new portfolio\n"
           + "2. Upload existing portfolio\n"
           + "3. View Composition \n"
           + "4. View portfolio value\n"
           + "5. View portfolio value by date\n"
           + "6. View all portfolios\n"
           + "7. Exit(q/Q)\n"
           + "Choose an option.\n"
           + "Enter the new portfolio's name:\n"
           + "New portfolio created. Do you want to add shares? (Y/N)\n"
           + "Which company do you want to buy shares for? (Enter ticker)\n"
           + "How many shares do you want to buy?\n"
           + "Exit.\n", out.toString());
  }

  @Test
  public void testBuy3() throws IOException {
    in = new StringReader("1 garlic Y\n"
            + "GOOG \n 69 q "
            + "2 \ngarlic.csv \n"
            + "3 \ngarlic \n"
            + "4 \n garlic \n"
            + "5 \ngarlic 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 69 shares in GOOG for $6007.83\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n", out.toString());
  }

  @Test
  public void testUpload() throws IOException {
    in = new StringReader("1 garlic Y"
          + "GOOG \n 69 \n N\n"
            + "2 q \n garlic.csv\n"
            + "3 \ngarlic \n"
            + "4 \ngarlic \n"
            + "5 \ngarlic 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n", out.toString());
  }

  @Test
  public void testUpload2() throws IOException {
    in = new StringReader("1 garlic Y"
          + "GOOG \n 69 \n N\n"
            + "2 \ngarlic.csv q\n"
            + "3 \ngarlic \n"
            + "4 \n garlic \n"
            + "5 \ngarlic 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n", out.toString());
  }

  @Test
  public void testComposition() throws IOException {
    in = new StringReader("1 garlic Y\n"
          + "GOOG \n 69 \n N\n"
            + "2 \ngarlic.csv \n"
            + "3 q \ngarlic \n"
            + "4 \n garlic \n"
            + "5 \ngarlic 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 69 shares in GOOG for $6007.83\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the CSV file's name to upload.\n"
          + "Portfolio name already taken.\n", out.toString());
  }

  @Test
  public void testComposition2() throws IOException {
    in = new StringReader("1 garlic Y\n"
          + "GOOG \n 69 \n N\n"
            + "3 \ngarlic \n q"
            + "4 \n garlic \n"
            + "5 \ngarlic 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 69 shares in GOOG for $6007.83\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the portfolio's name.\n"
          + "Composition of garlic:\n"
          + "Ticker: GOOG, Shares: 69, Cost: 87.070000\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the portfolio's name.\n"
          + "Which date do you want to check the portfolio value for? (YYYY-MM-DD)\n"
          + "The total portfolio value on 2022-10-31 is $6648.15\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "All portfolios:\n"
          + "Ticker: GOOG, Shares: 69, Cost: 87.070000\n"
          + "\n"
          + "\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Exit.\n", out.toString());
  }

  @Test
  public void testPortfolioValue() throws IOException {
    in = new StringReader("1 garlic Y\n"
          + "GOOG \n 69 \n N\n"
            + "4 q \ngarlic \n"
            + "5 \ngarlic \n 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 69 shares in GOOG for $6007.83\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the portfolio's name.\n"
          + "Exit.\n", out.toString());
  }

  @Test
  public void testPortfolioValue2() throws IOException {
    in = new StringReader("1 garlic Y\n"
          + "GOOG \n 69 \n N\n"
            + "4 \ngarlic q \n"
            + "5 \ngarlic \n 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 69 shares in GOOG for $6007.83\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the portfolio's name.\n"
          + "No data found for this date.\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Exit.\n", out.toString());
  }

  @Test
  public void testValueDate() throws IOException {
    in = new StringReader("1 garlic Y\n"
          + "GOOG \n 69 \n N\n"
            + "5 q \ngarlic \n 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 69 shares in GOOG for $6007.83\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the portfolio's name.\n"
          + "Exit.\n", out.toString());
  }

  @Test
  public void testValueDate2() throws IOException {
    in = new StringReader("1 garlic Y\n"
          + "GOOG \n 69 \n N\n"
            + "5 \ngarlic q \n 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 69 shares in GOOG for $6007.83\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the portfolio's name.\n"
          + "Which date do you want to check the portfolio value for? (YYYY-MM-DD)\n"
          + "Exit.\n", out.toString());
  }

  @Test
  public void testValueDate3() throws IOException {
    in = new StringReader("1 garlic Y\n"
          + "GOOG \n 69 \n N\n"
            + "5 \ngarlic \n 2022-10-31 q \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 69 shares in GOOG for $6007.83\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the portfolio's name.\n"
          + "Which date do you want to check the portfolio value for? (YYYY-MM-DD)\n"
          + "The total portfolio value on 2022-10-31 is $6648.15\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Exit.\n", out.toString());
  }

  @Test
  public void testAllPortfolios() throws IOException {
    in = new StringReader("1 garlic Y\n"
          + "GOOG \n 69 \n N\n"
            + "6 q\n"
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 69 shares in GOOG for $6007.83\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "All portfolios:\n"
          + "Ticker: GOOG, Shares: 69, Cost: 87.070000\n"
          + "\n"
          + "\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Exit.\n", out.toString());
  }

  @Test
  public void testAllPortfolios2() throws IOException {
    in = new StringReader("1 garlic Y\n"
          + "GOOG \n 69 \n N\n"
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 69 shares in GOOG for $6007.83\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Exit.\n", out.toString());
  }

  @Test
  public void testModel() throws IOException {
    in = new StringReader("1 walmart Y\n"
            + "GOOG \n 69 \n Y\n"
            + "AAPL \n 12 \n N\n"
            + "2 \n garlic.csv\n "
            + "3 \n garlic \n"
            + "4 \n walmart \n"
            + "5 \n walmart 2022-10-31 \n"
            + "6 "
            + "q");
    out = new StringBuffer();
    stockController = new StockControllerImpl(in, out);
    stockController.begin(stockModel);
    assertEquals("Hello!\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the new portfolio's name:\n"
          + "New portfolio created. Do you want to add shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 69 shares in GOOG for $6007.83\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n"
          + "Which company do you want to buy shares for? (Enter ticker)\n"
          + "How many shares do you want to buy?\n"
          + "Successfully bought 12 shares in AAPL for $1044.84\n"
          + "Share(s) created. Do you want to add more shares? (Y/N)\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the CSV file's name to upload.\n"
          + "Portfolio loaded.\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the portfolio's name.\n"
          + "Composition of garlic:\n"
          + "Ticker: GOOG, Shares: 69, Cost: 87.070000\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the portfolio's name.\n"
          + "No data found for this date.\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Enter the portfolio's name.\n"
          + "Which date do you want to check the portfolio value for? (YYYY-MM-DD)\n"
          + "The total portfolio value on 2022-10-31 is $8499.029999999999\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "All portfolios:\n"
          + "Ticker: GOOG, Shares: 69, Cost: 87.070000\n"
          + "\n"
          + "Ticker: GOOG, Shares: 69, Cost: 87.070000\n"
          + "Ticker: AAPL, Shares: 12, Cost: 87.070000\n"
          + "\n"
          + "\n"
          + "\tStock Trading System\n"
          + "1. Create new portfolio\n"
          + "2. Upload existing portfolio\n"
          + "3. View Composition \n"
          + "4. View portfolio value\n"
          + "5. View portfolio value by date\n"
          + "6. View all portfolios\n"
          + "7. Exit(q/Q)\n"
          + "Choose an option.\n"
          + "Exit.\n", out.toString());
  }
}