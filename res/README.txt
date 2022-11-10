Team:
Shruti Saravanan
Ganavi Jayaram

The features provided in the menu:
        1. Create new portfolio
        2. Upload existing portfolio
        3. View Composition
        4. View portfolio value
        5. View portfolio value by date
        6. View all portfolios
        7. Exit(q/Q)

Features outside the menu tested in the test classes:
        1. getTicker(String company) - Retrieves the company's stock code by the given name.
        2. getOpeningValue(String ticker, String date) - Retrieves the company's opening value for the day.
        3. getHighValue(String ticker, String date) - Retrieves the company's high value for the day.
        4. getLowValue(String ticker, String date) - Retrieves the company's low value for the day.
        5. getClosingValue(String ticker, String date) - Retrieves the company's closing value for the day.
        6. getAvailableShares(String ticker, String date) - Retrieves the number of available shares for a stock for a day.
        7. getTicker() - Retrieves the ticker of the current company.
        8. getShares() - Retrieves the shares of the current company.
        9. getCost() - Retrieves the cost of all shares of one company.
       10. getActiveComposition() - Retrieves the composition of the current company.

The implementation of the API is done, with methods as mentioned in the list above.
NOTE: It should be noted that each test case can only be run one at a time within a minute's time due to the limitations of the API. But accessing the program's features through the CLI has no such limitations. It is also possible to experience issues trying to get the current day's closing value of a stock before EOD.

Data can also be retrieved from an existing CSV file. To upload a file, it is suggested to have the csv file outside the src folder, so you can easily just enter the file name and upload it to the program.

All portfolios are automatically downloaded as a CSV once the user exits the program inside the res folder.

Enter q/Q to exit the program at any point of time.
Enter 6 to exit the program while being asked to choose a menu option.