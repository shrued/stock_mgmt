package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.PortfolioModelImpl;

/**
 * This class implements the StockController interface and its begin method.
 */
public class StockControllerImpl implements StockController {
  private final Readable in;
  private final Appendable out;

  /**
   * Construct a StockControllerImpl object to initialize values.
   * @param in the input stream
   * @param out the output stream
   * @throws IllegalArgumentException exception for invalid arguments
   */
  public StockControllerImpl(Readable in, Appendable out) throws IllegalArgumentException {
    if (in == null || out == null) {
      throw new IllegalArgumentException();
    }
    this.in = in;
    this.out = out;
  }

  /**
   * Separates the input scanner object by blank space or \n.
   * @param scan the input scanner object
   * @return the string input
   * @throws IllegalStateException exception for invalid arguments
   */
  private String input(Scanner scan) throws IllegalStateException {
    String s;
    try {
      s = scan.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException();
    }
    return s;
  }

  /**
   * Outputs the string to the output stream.
   * @param s the string to be output into the stream
   * @throws IllegalStateException exception for invalid arguments
   */
  private void output(String s) throws IllegalStateException {
    try {
      this.out.append(s);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Checks if the user chose to quit the program.
   * @param st the user input
   * @param stock the stock to save the portfolio
   * @return boolean value of whether the user wants to quit or not
   */
  private boolean isExit(String st, PortfolioModelImpl stock) {
    if (st.equals("q") || st.equals("Q")) {
      stock.savePortfolio();
      output("Exit.\n");
    }
    return st.equals("q") || st.equals("Q");
  }

  @Override
  public void begin(PortfolioModelImpl stock) throws IOException {
    if (stock == null) {
      throw new IllegalArgumentException();
    }
    Scanner scan = new Scanner(this.in);
    output("Hello!\n");
    while (true) {
      output("\tStock Trading System\n"
              + "1. Create new portfolio\n"
              + "2. Upload existing portfolio\n"
              + "3. View Composition \n"
              + "4. View portfolio value\n"
              + "5. View portfolio value by date\n"
              + "6. View all portfolios\n"
              + "7. Exit(q/Q)\n"
              + "Choose an option.\n");
      String option = input(scan);

      if (isExit(option, stock)) {
        return;
      }

      if (option.equals("1")) {
        output("Enter the new portfolio's name:\n");
        String newPortfolioName = input(scan);
        if (isExit(newPortfolioName, stock)) {
          return;
        }
        try {
          stock.createPortfolio(newPortfolioName);
        } catch (IllegalArgumentException e) {
          output(e.getMessage());
          continue;
        }
        output("New portfolio created. Do you want to add shares? (Y/N)\n");
        String addShare = input(scan);

        while (!(addShare.equals("N") || addShare.equals("n"))) {
          if (addShare.equals("Y") || addShare.equals("y")) {
            if (isExit(newPortfolioName, stock)) {
              return;
            }
            output("Which company do you want to buy shares for? (Enter ticker)\n");
            String company = input(scan);
            if (isExit(company, stock)) {
              return;
            }
            output("How many shares do you want to buy?\n");
            int shares;
            while (true) {
              try {
                String st = input(scan);
                if (isExit(st, stock)) {
                  return;
                }
                shares = Integer.parseInt(st);
              } catch (Exception e) {
                output("Invalid shares.\n");
                continue;
              }
              break;
            }

            Double cost;
            try {
              cost = stock.buyStock(newPortfolioName, company, shares);
            } catch (IllegalArgumentException e) {
              output(e.getMessage());
              output("\n");
              continue;
            }
            output("Successfully bought " + shares + " shares in " + company + " for $"
                    + cost + "\n");
          } else {
            return;
          }
          output("Share(s) created. Do you want to add more shares? (Y/N)\n");
          addShare = input(scan);
        }
      }

      if (option.equals("2")) {
        output("Enter the CSV file's name to upload.\n");
        String fileName = input(scan);
        if (isExit(fileName, stock)) {
          output("Exit.\n");
          return;
        }

        try {
          stock.loadPortfolio(fileName);
        } catch (IllegalArgumentException e) {
          output(e.getMessage());
          output("\n");
          return;
        }
        output("Portfolio loaded.\n");
      }

      if (option.equals("3")) {
        output("Enter the portfolio's name.\n");
        String portfolioName = input(scan);
        if (isExit(portfolioName, stock)) {
          return;
        }
        String result;
        try {
          result = stock.getPortfolioComposition(portfolioName);
        } catch (IllegalArgumentException e) {
          output(e.getMessage());
          output("\n");
          continue;
        }
        output("Composition of " + portfolioName + ":\n");
        output(result);
      }

      if (option.equals("4")) {
        output("Enter the portfolio's name.\n");
        String portfolioName = input(scan);
        if (isExit(portfolioName, stock)) {
          return;
        }
        Double result;
        try {
          String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
          result = stock.getPortfolioValueByDate(portfolioName, date);
        } catch (IllegalArgumentException e) {
          output(e.getMessage());
          output("\n");
          continue;
        }
        output("The portfolio " + portfolioName + "'s value is $" + result + "\n");
      }

      if (option.equals("5")) {
        output("Enter the portfolio's name.\n");
        String portfolioName = input(scan);
        if (isExit(portfolioName, stock)) {
          return;
        }
        output("Which date do you want to check the portfolio value for? (YYYY-MM-DD)\n");
        String date = input(scan);
        if (isExit(date, stock)) {
          return;
        }
        Double result;
        try {
          result = stock.getPortfolioValueByDate(portfolioName, date);
        } catch (IllegalArgumentException e) {
          output(e.getMessage());
          output("\n");
          continue;
        }
        output("The total portfolio value on " + date + " is $" + result + "\n");
      }

      if (option.equals("6")) {
        String result;
        try {
          result = stock.getAllPortfolios();
        } catch (IllegalArgumentException e) {
          output(e.getMessage());
          output("\n");
          continue;
        }
        output("All portfolios:\n");
        output(result + "\n");
      }

      if (option.equals("7")) {
        stock.savePortfolio();
        output("Exit.\n");
        return;
      }
    }
  }
}
