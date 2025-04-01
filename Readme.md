**Most Active Cookie**

A command-line program that processes cookie log files and identifies the most active cookie for a specific day.

**Overview**

This application reads a cookie log file in CSV format and determines which cookie was most active (appeared most frequently) on a given day. If multiple cookies tie for the most active, all of them are returned.

The program follows SOLID principles with clean, maintainable, and extensible code architecture.

**Features**

* Parse cookie log files in CSV format
* Identify the most active cookie(s) for a specified date
* Handle multiple cookies with equal activity levels
* Process files with timestamps in UTC format
* Simple command-line interface with intuitive parameters

**Requirements**

* Java 11 or higher
* Maven 3.4.0 or higher (for building and running tests)

**Installation**

**Option 1: Clone the repository**

```
git clone https://github.com/vxdant/most-active-cookie.git
cd most-active-cookie
```

**Option 2: Download the source code**

Download the source code as a ZIP file and extract it to your preferred location.

**Building the Application**

From the project root directory, run:

`mvn clean package`

This will compile the code, run tests, and create an executable JAR file in the target directory.

**Running the Application**

**Using the JAR file**

After building, you can run the application using:

```
java -jar target/MostActiveCookieFinder-1.0-SNAPSHOT.jar -f src/main/java/com/findmostactivecookie/log/cookie_log.csv -d [date]
```

Replace [cookie_log_file.csv] with the path to your cookie log file and [date] with the date you want to analyze in the format YYYY-MM-DD.

**Example:**

Finding the most active cookie on December 9, 2018:

```
java -jar target/MostActiveCookieFinder-1.0-SNAPSHOT.jar -f src/main/java/com/findmostactivecookie/log/cookie_log.csv -d 2018-12-06
```

**Output:**

`AtY0laUfhglK3lC7`

**Command-Line Parameters**

The application accepts the following command-line parameters:

* -f [filename]: Specifies the path to the cookie log file (required)
* -d [date]: Specifies the date to analyze in the format YYYY-MM-DD (required)

**Cookie Log File Format**

The cookie log file should be in CSV format with the following structure:

```cookie,timestamp
[cookie_id],[ISO-8601 timestamp]
...
```

**Example:**

```
cookie,timestamp
AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00
```

**Testing**

The application includes comprehensive unit tests for all components. To run the tests:

`mvn test
`

**The tests cover:**

* Log file parsing
* Cookie analysis
* Command-line argument parsing
* End-to-end workflow testing

**Design Decisions**

* Interface-based Design: Enables easy extension and testing
* Dependency Injection: Makes components easily replaceable and testable
* Separation of Concerns: Each class has a single responsibility
* Immutable Models: Prevents unexpected state changes
* Comprehensive Error Handling: Robust handling of invalid inputs