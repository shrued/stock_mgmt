Team:
Shruti Saravanan
Ganavi Jayaram

To run the Jar file, open command prompt and navigate into the res folder that has the jar file.
Run the jar file:

    java -jar Assignment4.jar


Follow the menu to use the program.

            Hello!
                Stock Trading System
            1. Create new portfolio
            2. Upload existing portfolio
            3. View Composition
            4. View portfolio value
            5. View portfolio value by date
            6. View all portfolios
            7. Exit(q/Q)
            Choose an option.

A. To create a portfolio with 3 different stocks:
        Choose option 1 to create a new portfolio.
        Enter the name of the portfolio.
        Confirm you want to add shares. Enter Y.
        Enter the company ticker. GOOG.
        Enter number of shares you want to buy.
        Confirm you want to add shares. Enter Y.
        Enter the company ticker. AAPL.
        Enter number of shares you want to buy.
        Confirm you want to add shares. Enter Y.
        Enter the company ticker. AMZN.
        Enter number of shares you want to buy.
        Confirm you want to stop adding shares. Enter N.

   To query their value on a specific date:
        Continue the program and enter 5.
        Enter the portfolio's name you gave earlier.
        Enter the date. 2022-10-31
        Enter q/Q/6 to exit the program.

B. To create a portfolio with 2 different stocks:
        Choose option 1 to create a new portfolio.
        Enter the name of the portfolio.
        Confirm you want to add shares. Enter Y.
        Enter the company ticker. GOOG.
        Enter number of shares you want to buy.
        Confirm you want to add shares. Enter Y.
        Enter the company ticker. AAPL.
        Enter number of shares you want to buy.
        Confirm you want to stop adding shares. Enter N.

   To query their value on a specific date:
        Continue the program and enter 5.
        Enter the portfolio's name you gave earlier.
        Enter the date. 2022-11-02
        Enter q/Q/6 to exit the program.

C. List of typical stocks that the program supports:
        GOOG: 2022-11-02
        AMZN: 2022-10-31
        AAPL: 2022-11-01
        META: 2022-11-02
   (As far as we experimented, most stocks seem to be working for these dates.)

Data can also be retrieved from an existing CSV file. To upload a file, it is suggested to have the csv file outside the src folder, so you can easily just enter the file name and upload it to the program.
Example:
Choose an option.
2
Enter the CSV file's name to upload.
garlic.csv
Portfolio loaded.