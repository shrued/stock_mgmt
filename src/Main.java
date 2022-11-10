import java.io.IOException;
import java.io.InputStreamReader;

import controller.StockControllerImpl;
import model.PortfolioModelImpl;

/**
 * The main class for the program.
 */
public class Main {
  public static void main(String[] args) throws IOException {
    new StockControllerImpl(new InputStreamReader(System.in), System.out)
            .begin(new PortfolioModelImpl());
  }
}
