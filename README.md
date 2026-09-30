## 1. Project Overview

This project is an end-to-end Selenium automation framework for the BlazeDemo flight booking application.

The framework is designed using Java, Selenium WebDriver, TestNG, and Maven with the Page Object Model (POM) design pattern.

**Application Under Test:**  
https://blazedemo.com/

---

## 2. Objective

The objective of this project is to:

- Automate the BlazeDemo flight booking workflow.
- Implement a reusable Selenium automation framework.
- Apply the Page Object Model design pattern.
- Execute smoke, functional, and negative test scenarios.
- Implement data-driven testing using TestNG DataProvider.
- Manage dependencies and test execution using Maven.
- Organize test execution using TestNG groups.
- Apply explicit waits to improve test stability.

---

## 3. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Web automation |
| TestNG | Test execution, assertions, groups, and DataProvider |
| Maven | Build and dependency management |
| ChromeDriver | Chrome browser automation |
| Eclipse | Development IDE |
| Git/GitHub | Version control |

---

## 4. Framework Structure

```text
BlazeDemoAutomation/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/
│   │           ├── HomePage.java
│   │           ├── ReservePage.java
│   │           ├── PurchasePage.java
│   │           └── ConfirmationPage.java
│   │
│   └── test/
│       └── java/
│           └── tests/
│               ├── BaseTest.java
│               └── FlightBookingTest.java
│
├── pom.xml
├── testng.xml
└── README.md
________________________________________
5. Page Objects
HomePage.java
Handles the BlazeDemo home page:
•	Select departure city.
•	Select destination city.
•	Click the Find Flights button.
•	Search for flights using reusable methods.
•	Wait for navigation to the Reserve page.
ReservePage.java
Handles the flight selection page:
•	Wait for available flights.
•	Select the first available flight.
•	Wait for navigation to the Purchase page.
PurchasePage.java
Handles passenger and payment details:
•	Enter passenger details.
•	Select card type.
•	Enter credit card details.
•	Submit the flight purchase.
•	Wait for passenger fields when required.
ConfirmationPage.java
Handles booking confirmation:
•	Verify the confirmation message.
•	Retrieve the confirmation message for assertions.
BaseTest.java
Provides common test setup and cleanup:
•	Initialize ChromeDriver.
•	Maximize the browser.
•	Open BlazeDemo.
•	Initialize Page Object classes.
•	Close the browser after each test.
________________________________________
6. Base Test
BaseTest.java provides common test setup and cleanup for the automation tests.
It handles:
•	ChromeDriver initialization.
•	Browser maximization.
•	Application navigation.
•	Page Object initialization.
•	Browser cleanup.
TestNG Lifecycle Annotations
The framework uses:
•	@BeforeMethod(alwaysRun = true)
•	@AfterMethod(alwaysRun = true)
Using alwaysRun = true ensures that the setup and cleanup methods are executed correctly when TestNG groups are used.
________________________________________
7. Test Scenarios
ID	Scenario	Type
TC01	Verify flight search and navigation	Smoke
TC02	Search flights with valid cities	Functional
TC03	Complete a flight booking	Functional
TC04	Multiple flight bookings with different datasets	Data-driven
TC05	Blank credit card	Negative
TC06	Non-numeric credit card	Negative
TC07	Same departure and destination validation	Negative
TC08	Required fields blank	Negative
________________________________________
8. Data-Driven Testing
TestNG @DataProvider is used to execute tests with multiple flight datasets.
Current Flight Datasets
Departure	Destination
Boston	New York
Paris	London
Portland	Berlin
The data-driven booking test executes the complete booking workflow for each dataset and validates the confirmation page.
The data-driven search test also validates the Reserve page for each dataset.
________________________________________
9. TestNG Groups
The test suite is organized into the following TestNG groups:
•	smoke
•	functional
•	negative
These groups are configured in testng.xml.
Smoke Tests
Smoke tests validate the basic flight search and booking flow.
Functional Tests
Functional tests validate flight searching and booking functionality, including data-driven execution.
Negative Tests
Negative tests cover invalid or incomplete booking scenarios.
________________________________________
10. Synchronization
Explicit waits are used to improve test stability during page navigation and element interaction.
Examples include:
•	Waiting for flight results to become available.
•	Waiting for the first flight to become clickable.
•	Waiting for navigation to the Purchase page.
•	Waiting for passenger fields to become visible.
•	Waiting for navigation to the Reserve page.
This reduces synchronization-related failures during execution.
________________________________________
11. Negative Test Observations
The following application behaviors were observed during automation.
Blank Credit Card
When the Credit Card Number field is left blank, BlazeDemo proceeds to the confirmation page.
This behavior was captured by the automated test.
Non-Numeric Credit Card
When non-numeric characters are entered in the Credit Card Number field, BlazeDemo proceeds to the confirmation page.
This behavior was captured by the automated test.
Required Fields Blank
When passenger and payment fields are left blank, the observed application behavior was captured by the automated test using the resulting URL.
Same Departure and Destination
The available departure and destination dropdowns do not contain a common city.
Therefore, the same city cannot currently be selected through the available UI options.
The automated test verifies that there is no common city between the two dropdown lists.
________________________________________
12. How to Run
From Eclipse
1.	Right-click testng.xml.
2.	Select Run As → TestNG Suite.
3.	Wait for the TestNG execution to complete.
4.	Review the execution results in the TestNG Results window.
From Maven
Open a terminal in the project root directory and run:
mvn test
Maven uses the configured testng.xml suite for test execution.
________________________________________
13. Test Execution Result
Latest Successful TestNG Execution
Test Suite: BlazeDemo Test Suite

Total tests run: 14
Passes: 14
Failures: 0
Skipped: 0
The successful execution includes:
•	Smoke tests.
•	Functional tests.
•	Negative tests.
•	Data-driven flight searches.
•	Data-driven flight bookings.
________________________________________
14. Framework Benefits
The framework provides:
•	Reusable Page Object classes.
•	Centralized browser setup.
•	Reusable test cleanup.
•	Data-driven test execution.
•	TestNG group-based execution.
•	Explicit synchronization.
•	Maven-based execution.
•	Modular test organization.
•	Reusable page-level methods.
•	Separation of test logic from page interaction logic.
________________________________________
15. Project Execution Flow
The automated flight booking flow follows:
Launch BlazeDemo
       ↓
Select Departure City
       ↓
Select Destination City
       ↓
Click Find Flights
       ↓
Select First Available Flight
       ↓
Enter Passenger Details
       ↓
Enter Payment Details
       ↓
Click Purchase Flight
       ↓
Verify Confirmation Page
________________________________________
16. Test Framework Components
BaseTest
Responsible for common browser setup, application launch, Page Object initialization, and browser cleanup.
FlightBookingTest
Contains the TestNG test cases, assertions, TestNG groups, and DataProvider-driven tests.
Page Object Classes
Encapsulate page-specific locators and reusable actions for:
•	Home Page
•	Reserve Page
•	Purchase Page
•	Confirmation Page
testng.xml
Organizes test execution using TestNG groups:
•	Smoke
•	Functional
•	Negative
pom.xml
Manages Maven dependencies and test execution configuration.
________________________________________
17. Conclusion
The BlazeDemo automation framework provides a modular Selenium-based automation solution using Java, TestNG, Maven, and Page Object Model.
The framework supports:
•	End-to-end flight booking automation.
•	Smoke testing.
•	Functional testing.
•	Negative testing.
•	Data-driven testing.
•	TestNG group execution.
•	Explicit synchronization.
•	Maven-based test execution.
The current automated suite has successfully completed:
14 Tests
14 Passed
0 Failed
0 Skipped

### After pasting

Just do:

**Ctrl + S** ✅

Then **do not add anything else to the README**.

One note: the `Git/GitHub` row is fine because Git/GitHub is part of the overall framework/tooling direction, but **we have not yet pushed this BlazeDemo project to GitHub**. We will do that later; don't claim a BlazeDemo repository exists yet.
Absolutely. 👍 Here is the complete, clean README.md content. You can select everything below and paste it directly into your README.md.
I’ve removed all our conversation instructions and corrected the Markdown formatting, especially the tables.
# BlazeDemo Automation Framework

## 1. Project Overview

This project is an end-to-end Selenium automation framework for the BlazeDemo flight booking application.

The framework is designed using Java, Selenium WebDriver, TestNG, and Maven with the Page Object Model (POM) design pattern.

**Application Under Test:**  
https://blazedemo.com/

---

## 2. Objective

The objective of this project is to:

- Automate the BlazeDemo flight booking workflow.
- Implement a reusable Selenium automation framework.
- Apply the Page Object Model design pattern.
- Execute smoke, functional, and negative test scenarios.
- Implement data-driven testing using TestNG DataProvider.
- Manage dependencies and test execution using Maven.
- Organize test execution using TestNG groups.
- Apply explicit waits to improve test stability.

---

## 3. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Web automation |
| TestNG | Test execution, assertions, groups, and DataProvider |
| Maven | Build and dependency management |
| ChromeDriver | Chrome browser automation |
| Eclipse | Development IDE |
| Git/GitHub | Version control |

---

## 4. Framework Structure

```text
BlazeDemoAutomation/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/
│   │           ├── HomePage.java
│   │           ├── ReservePage.java
│   │           ├── PurchasePage.java
│   │           └── ConfirmationPage.java
│   │
│   └── test/
│       └── java/
│           └── tests/
│               ├── BaseTest.java
│               └── FlightBookingTest.java
│
├── pom.xml
├── testng.xml
└── README.md
________________________________________
5. Page Objects
HomePage.java
Handles the BlazeDemo home page:
•	Select departure city.
•	Select destination city.
•	Click the Find Flights button.
•	Search for flights using reusable methods.
•	Wait for navigation to the Reserve page.
ReservePage.java
Handles the flight selection page:
•	Wait for available flights.
•	Select the first available flight.
•	Wait for navigation to the Purchase page.
PurchasePage.java
Handles passenger and payment details:
•	Enter passenger details.
•	Select card type.
•	Enter credit card details.
•	Submit the flight purchase.
•	Wait for passenger fields when required.
ConfirmationPage.java
Handles booking confirmation:
•	Verify the confirmation message.
•	Retrieve the confirmation message for assertions.
BaseTest.java
Provides common test setup and cleanup:
•	Initialize ChromeDriver.
•	Maximize the browser.
•	Open BlazeDemo.
•	Initialize Page Object classes.
•	Close the browser after each test.
________________________________________
6. Base Test
BaseTest.java provides common test setup and cleanup for the automation tests.
It handles:
•	ChromeDriver initialization.
•	Browser maximization.
•	Application navigation.
•	Page Object initialization.
•	Browser cleanup.
TestNG Lifecycle Annotations
The framework uses:
•	@BeforeMethod(alwaysRun = true)
•	@AfterMethod(alwaysRun = true)
Using alwaysRun = true ensures that the setup and cleanup methods are executed correctly when TestNG groups are used.
________________________________________
7. Test Scenarios
ID	Scenario	Type
TC01	Verify flight search and navigation	Smoke
TC02	Search flights with valid cities	Functional
TC03	Complete a flight booking	Functional
TC04	Multiple flight bookings with different datasets	Data-driven
TC05	Blank credit card	Negative
TC06	Non-numeric credit card	Negative
TC07	Same departure and destination validation	Negative
TC08	Required fields blank	Negative
________________________________________
8. Data-Driven Testing
TestNG @DataProvider is used to execute tests with multiple flight datasets.
Current Flight Datasets
Departure	Destination
Boston	New York
Paris	London
Portland	Berlin
The data-driven booking test executes the complete booking workflow for each dataset and validates the confirmation page.
The data-driven search test also validates the Reserve page for each dataset.
________________________________________
9. TestNG Groups
The test suite is organized into the following TestNG groups:
•	smoke
•	functional
•	negative
These groups are configured in testng.xml.
Smoke Tests
Smoke tests validate the basic flight search and booking flow.
Functional Tests
Functional tests validate flight searching and booking functionality, including data-driven execution.
Negative Tests
Negative tests cover invalid or incomplete booking scenarios.
________________________________________
10. Synchronization
Explicit waits are used to improve test stability during page navigation and element interaction.
Examples include:
•	Waiting for flight results to become available.
•	Waiting for the first flight to become clickable.
•	Waiting for navigation to the Purchase page.
•	Waiting for passenger fields to become visible.
•	Waiting for navigation to the Reserve page.
This reduces synchronization-related failures during execution.
________________________________________
## 11. Negative Test Observations

The following negative scenarios were implemented and executed as specified in the
capstone requirements.

### Blank Credit Card
The test submits the booking form with the credit card number blank.
During execution, BlazeDemo allowed the flow to proceed to the confirmation page.
This behavior was recorded as an observed application behavior.

### Non-Numeric Credit Card
The test submits alphabetic/non-numeric characters in the credit card field.
During execution, BlazeDemo allowed the flow to proceed without displaying
a validation error. This behavior was recorded as an observed application behavior.

### Required Fields Blank
The test submits the purchase form without entering the required passenger/payment
fields. During execution, BlazeDemo allowed the flow to proceed to the confirmation
page. This behavior was recorded as an observed application behavior.

### Same Departure and Destination
The test verifies whether the same city is available in both the departure and
destination dropdowns.

During execution, the departure and destination dropdowns contained different
sets of cities, with no common city available. Therefore, the scenario could
not be executed as an actual same-city booking because BlazeDemo does not provide
a common city in both dropdowns.

The test validates that no common city is available in both lists.aa________________________________________
12. How to Run
From Eclipse
1.	Right-click testng.xml.
2.	Select Run As → TestNG Suite.
3.	Wait for the TestNG execution to complete.
4.	Review the execution results in the TestNG Results window.
From Maven
Open a terminal in the project root directory and run:
mvn test
Maven uses the configured testng.xml suite for test execution.
________________________________________
13. Test Execution Result
Latest Successful TestNG Execution
Test Suite: BlazeDemo Test Suite

Total tests run: 14
Passes: 14
Failures: 0
Skipped: 0
The successful execution includes:
•	Smoke tests.
•	Functional tests.
•	Negative tests.
•	Data-driven flight searches.
•	Data-driven flight bookings.
________________________________________
14. Framework Benefits
The framework provides:
•	Reusable Page Object classes.
•	Centralized browser setup.
•	Reusable test cleanup.
•	Data-driven test execution.
•	TestNG group-based execution.
•	Explicit synchronization.
•	Maven-based execution.
•	Modular test organization.
•	Reusable page-level methods.
•	Separation of test logic from page interaction logic.
________________________________________
15. Project Execution Flow
The automated flight booking flow follows:
Launch BlazeDemo
       ↓
Select Departure City
       ↓
Select Destination City
       ↓
Click Find Flights
       ↓
Select First Available Flight
       ↓
Enter Passenger Details
       ↓
Enter Payment Details
       ↓
Click Purchase Flight
       ↓
Verify Confirmation Page
________________________________________
16. Test Framework Components
BaseTest
Responsible for common browser setup, application launch, Page Object initialization, and browser cleanup.
FlightBookingTest
Contains the TestNG test cases, assertions, TestNG groups, and DataProvider-driven tests.
Page Object Classes
Encapsulate page-specific locators and reusable actions for:
•	Home Page
•	Reserve Page
•	Purchase Page
•	Confirmation Page
testng.xml
Organizes test execution using TestNG groups:
•	Smoke
•	Functional
•	Negative
pom.xml
Manages Maven dependencies and test execution configuration.
________________________________________
17. Conclusion
The BlazeDemo automation framework provides a modular Selenium-based automation solution using Java, TestNG, Maven, and Page Object Model.
The framework supports:
•	End-to-end flight booking automation.
•	Smoke testing.
•	Functional testing.
•	Negative testing.
•	Data-driven testing.
•	TestNG group execution.
•	Explicit synchronization.
•	Maven-based test execution.
The current automated suite has successfully completed:
14 Tests
14 Passed
0 Failed
0 Skipped

