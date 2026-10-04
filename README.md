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
```

---

## 5. Page Objects

### HomePage.java

Handles the BlazeDemo home page:

- Select departure city.
- Select destination city.
- Click the Find Flights button.
- Search for flights using reusable methods.
- Wait for navigation to the Reserve page.

### ReservePage.java

Handles the flight selection page:

- Wait for available flights.
- Select the first available flight.
- Wait for navigation to the Purchase page.

### PurchasePage.java

Handles passenger and payment details:

- Enter passenger details.
- Select card type.
- Enter credit card details.
- Submit the flight purchase.
- Wait for passenger fields when required.

### ConfirmationPage.java

Handles booking confirmation:

- Verify the confirmation message.
- Retrieve the confirmation message for assertions.
- Use explicit waits for confirmation-page elements.

### BaseTest.java

Provides common test setup and cleanup:

- Initialize ChromeDriver.
- Maximize the browser.
- Open BlazeDemo.
- Initialize Page Object classes.
- Close the browser after each test.

---

## 6. Base Test

`BaseTest.java` provides common test setup and cleanup for the automation tests.

It handles:

- ChromeDriver initialization.
- Browser maximization.
- Application navigation.
- Page Object initialization.
- Browser cleanup.

### TestNG Lifecycle Annotations

The framework uses:

- `@BeforeMethod(alwaysRun = true)`
- `@AfterMethod(alwaysRun = true)`

Using `alwaysRun = true` ensures that the setup and cleanup methods are executed correctly when TestNG groups are used.

---

## 7. Test Scenarios

| ID | Scenario | Type |
|---|---|---|
| TC01 | Verify homepage loads and dropdowns visible | Smoke |
| TC02 | Search flights with valid cities | Functional |
| TC03 | Complete a flight booking | Functional |
| TC04 | Multiple bookings with different datasets | Data-driven |
| TC05 | Blank credit card | Negative |
| TC06 | Invalid credit card characters | Negative |
| TC07 | Same departure and destination city | Negative |
| TC08 | Required fields blank | Negative |

### TC01 – Homepage Validation

Verifies that the BlazeDemo homepage loads successfully and that the departure and destination city dropdowns are visible.

### TC02 – Valid Flight Search

Verifies that a valid departure and destination city combination navigates to the Reserve page and then to the Purchase page after selecting a flight.

### TC03 – Complete Flight Booking

Automates the complete booking workflow from flight search through passenger/payment entry and validates the confirmation message.

### TC04 – Multiple Bookings with Different Data Sets

Uses TestNG `@DataProvider` to execute the booking workflow with multiple flight combinations.

### TC05 – Blank Credit Card

Verifies that a booking should not be confirmed when the credit card number is left blank.

### TC06 – Invalid Credit Card Characters

Verifies that invalid non-numeric characters in the credit card field should not result in a successful booking.

### TC07 – Same Departure and Destination City

Verifies whether a common city exists in both the departure and destination dropdowns.

The current BlazeDemo UI does not provide a common city in both dropdowns. The automated test therefore verifies that no common city is available.

### TC08 – Required Fields Blank

Verifies that the application should provide validation when required passenger and payment fields are left blank.

---

## 8. Data-Driven Testing

TestNG `@DataProvider` is used to execute tests with multiple flight datasets.

### Current Flight Datasets

| Departure | Destination |
|---|---|
| Boston | New York |
| Paris | London |
| Portland | Berlin |

The data-driven booking test executes the complete booking workflow for each dataset and validates the confirmation page.

The data-driven search test also validates the Reserve page for each dataset.

---

## 9. TestNG Groups

The test suite is organized into the following TestNG groups:

- `smoke`
- `functional`
- `negative`

These groups are configured in `testng.xml`.

### Smoke Tests

Smoke tests validate the basic flight search and booking flow.

### Functional Tests

Functional tests validate flight searching and booking functionality, including data-driven execution.

### Negative Tests

Negative tests cover invalid or incomplete booking scenarios.

---

## 10. Synchronization

Explicit waits are used to improve test stability during page navigation and element interaction.

Examples include:

- Waiting for flight results to become available.
- Waiting for the first flight to become clickable.
- Waiting for navigation to the Purchase page.
- Waiting for passenger fields to become visible.
- Waiting for navigation to the Reserve page.
- Waiting for the confirmation message to become visible.

This reduces synchronization-related failures during execution.

---

## 11. Negative Test Observations

The following application behaviors were observed during automation.

### TC05 – Blank Credit Card

The test submits the booking form with the credit card number left blank.

**Expected:** The confirmation page should not appear.

**Actual:** BlazeDemo allowed the booking flow to proceed to the confirmation page.

**Result:** Application validation defect detected.

---

### TC06 – Invalid Credit Card Characters

The test submits non-numeric characters in the credit card number field.

**Expected:** Proper validation/error behavior and no successful booking.

**Actual:** BlazeDemo allowed the booking flow to proceed to the confirmation page.

**Result:** Application validation defect detected.

---

### TC08 – Required Fields Blank

The test submits the purchase form without entering the required passenger and payment fields.

**Expected:** Proper validation/error behavior and no successful booking.

**Actual:** The automated test detected the application's behavior against the expected negative condition.

**Result:** Application validation defect detected.

---

### TC07 – Same Departure and Destination

The test compares the departure and destination city dropdowns.

The current BlazeDemo UI contains different sets of cities, with no common city available in both dropdowns.

Therefore, a same-city combination cannot be selected through the available UI.

The automated test verifies that no common city is available.

**Result:** PASS.

---

## 12. How to Run

### From Eclipse

1. Right-click `testng.xml`.
2. Select **Run As → TestNG Suite**.
3. Wait for the TestNG execution to complete.
4. Review the execution results in the TestNG Results window.

### From Maven

Open a terminal in the project root directory and run:

```bash
mvn test
```

Maven uses the configured TestNG suite for test execution.

---

## 13. Test Execution Result

### Latest TestNG Execution

**Test Suite:** BlazeDemo Test Suite

| Metric | Result |
|---|---:|
| Total Tests Run | 15 |
| Passed | 12 |
| Failed | 3 |
| Skipped | 0 |

### Execution Summary

The latest execution includes:

- Smoke tests.
- Functional tests.
- Negative tests.
- Data-driven flight searches.
- Data-driven flight bookings.
- TC07 same-city availability validation.

### Failed Tests

The three failed tests are negative scenarios where the automated assertions correctly detected application validation issues:

1. **TC05 – Blank Credit Card**
   - Application allowed booking without a credit card number.

2. **TC06 – Invalid Credit Card Characters**
   - Application allowed booking with non-numeric credit card characters.

3. **TC08 – Required Fields Blank**
   - Application did not provide the expected validation behavior for blank required fields.

The failures are retained as test findings rather than changing the assertions to artificially produce a passing result.

---

## 14. Framework Benefits

The framework provides:

- Reusable Page Object classes.
- Centralized browser setup.
- Reusable test cleanup.
- Data-driven test execution.
- TestNG group-based execution.
- Explicit synchronization.
- Maven-based execution.
- Modular test organization.
- Reusable page-level methods.
- Separation of test logic from page interaction logic.

---

## 15. Project Execution Flow

The automated flight booking flow follows:

```text
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
```

---

## 16. Test Framework Components

### BaseTest

Responsible for common browser setup, application launch, Page Object initialization, and browser cleanup.

### FlightBookingTest

Contains:

- TestNG test cases.
- Assertions.
- TestNG groups.
- DataProvider-driven tests.
- Positive and negative scenarios.

### Page Object Classes

Encapsulate page-specific locators and reusable actions for:

- Home Page.
- Reserve Page.
- Purchase Page.
- Confirmation Page.

### testng.xml

Organizes test execution using TestNG groups:

- Smoke.
- Functional.
- Negative.

### pom.xml

Manages Maven dependencies and test execution configuration.

---

## 17. Conclusion

The BlazeDemo automation framework provides a modular Selenium-based automation solution using Java, TestNG, Maven, and the Page Object Model.

The framework supports:

- End-to-end flight booking automation.
- Smoke testing.
- Functional testing.
- Negative testing.
- Data-driven testing.
- TestNG group execution.
- Explicit synchronization.
- Maven-based test execution.
- Application validation defect detection.

The latest automated execution completed:

**15 test executions**

**12 Passed**

**3 Failed**

**0 Skipped**

The failed tests provide documented application validation findings for blank credit card, invalid credit card characters, and blank required fields.
```
