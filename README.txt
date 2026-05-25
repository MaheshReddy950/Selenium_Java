# AlmaShines Test Automation Framework

A scalable and production-ready **BDD Test Automation Framework** developed for automating the end-to-end registration and login workflows of the AlmaShines platform.

The framework is built using **Java**, **Selenium WebDriver**, **Cucumber BDD**, and **TestNG**, following the **Page Object Model (POM)** design pattern to ensure maintainability, reusability, and clean test architecture.

---

# 🚀 Tech Stack

| Component | Technology | Version |
|---|---|---|
| Language | Java | 17 |
| Automation Tool | Selenium WebDriver | 4.21.0 |
| BDD Framework | Cucumber JVM | 7.18.0 |
| Test Framework | TestNG | 7.10.2 |
| Build Tool | Maven | Latest |
| Driver Management | WebDriverManager | 5.8.0 |

---

# 🏗️ Framework Design

## ✅ Page Object Model (POM)

The framework follows the Page Object Model design pattern where:

- Page locators are maintained inside page classes
- Reusable actions are exposed through public methods
- Test steps remain clean and readable
- UI changes can be managed centrally

---

## ✅ BDD with Cucumber

Test scenarios are written using Gherkin syntax:

```gherkin
Given User launches chrome browser
When User enters email address
Then Click on Login Button
```

This improves collaboration between QA, developers, and business teams.

---

## ✅ Dynamic Test Data Handling

The framework generates runtime dynamic data to avoid duplicate registration failures.

### Supported Dynamic Data

- Dynamic Email Generation
- Dynamic Names
- Dynamic Phone Numbers

### Example

```java
mahesh1234@yopmail.com
```

---

## ✅ OTP Automation using Yopmail

The framework supports automated OTP validation using Yopmail.

### Features

- Opens Yopmail in a new browser tab
- Switches between tabs dynamically
- Handles nested iframes
- Extracts OTP using Java Regex

### Regex Used

```java
\\b\\d{6}\\b
```

---

## ✅ Smart Reporting & Screenshots

### Features

- Automatic screenshot capture for failed steps
- Embedded screenshots in reports
- Timestamp-based report generation
- Non-overwriting HTML reports

### Example

```text
report_2026_05_24_10_30_15.html
```

---

# 📂 Project Structure

```text
selenium-java-framework/

├── src/test/java
│
├── base
│   └── BaseTest.java
│
├── hooks
│   └── Hooks.java
│
├── pages
│   └── LoginPage.java
│
├── runner
│   └── TestRunner.java
│
├── stepdefinitions
│   └── LoginSteps.java
│
├── utilities
│   └── CommonMethods.java
│
├── src/test/resources
│   └── features
│
├── target
│   └── cucumber-reports
│
└── pom.xml
```

---

# ⚙️ Key Framework Features

- BDD Framework using Cucumber
- Selenium WebDriver Integration
- TestNG Execution Engine
- Page Object Model Architecture
- Dynamic Wait Handling
- Reusable Utility Methods
- OTP Automation Support
- Dynamic Data Generation
- Screenshot Capture
- HTML Reporting
- Maven Dependency Management

---

# 🛠️ Steps to Build the Framework

## Step 1: Create Maven Project

Open Eclipse or IntelliJ IDEA.

Create new Maven project:

```text
File → New → Maven Project
```

Enter:

```text
Group Id  : com.almashines
Artifact Id : selenium-java-framework
```

---

## Step 2: Configure Java Version

Set Java compiler version to:

```text
Java 17
```

---

## Step 3: Add Maven Dependencies

Open:

```text
pom.xml
```

Add required dependencies:

```xml
<dependencies>

    <!-- SELENIUM -->

    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.21.0</version>
    </dependency>

    <!-- CUCUMBER -->

    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.18.0</version>
    </dependency>

    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-testng</artifactId>
        <version>7.18.0</version>
    </dependency>

    <!-- TESTNG -->

    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.10.2</version>
    </dependency>

    <!-- WEBDRIVER MANAGER -->

    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.8.0</version>
    </dependency>

</dependencies>
```

Then update Maven project.

---

## Step 4: Create Framework Folder Structure

Create packages:

```text
base
hooks
pages
runner
stepdefinitions
utilities
```

Create feature folder:

```text
src/test/resources/features
```

---

## Step 5: Create BaseTest Class

Responsible for:

- Browser Launch
- Driver Initialization
- Browser Teardown

File:

```text
base/BaseTest.java
```

---

## Step 6: Create CommonMethods Class

Responsible for reusable methods:

- click()
- type()
- waits
- dropdown handling
- screenshots
- OTP fetching
- dynamic data generation

File:

```text
utilities/CommonMethods.java
```

---

## Step 7: Create Page Classes

Store all locators and page actions.

Example:

```text
pages/LoginPage.java
```

Responsibilities:

- Element locators
- Action methods
- Reusable page functions

---

## Step 8: Create Feature Files

Create BDD scenarios.

Location:

```text
src/test/resources/features
```

Example:

```gherkin
Feature: Login Functionality

Scenario: Login with valid credentials

Given User launches chrome browser
When User enters email address
Then Click on Login Button
```

---

## Step 9: Create Step Definitions

Map Gherkin steps to Java methods.

Location:

```text
stepdefinitions/LoginSteps.java
```

Responsibilities:

- Call Page methods
- Handle test data
- Execute business flow

---

## Step 10: Create Hooks Class

Used for:

- Before Scenario execution
- After Scenario execution
- Screenshot capture on failure

File:

```text
hooks/Hooks.java
```

---

## Step 11: Create TestRunner Class

Responsible for:

- Running feature files
- Generating reports
- Configuring plugins

Location:

```text
runner/TestRunner.java
```

---

# ▶️ Test Execution

## Run Using Maven

```bash
mvn clean test
```

---

## Run Using TestRunner

Navigate to:

```text
src/test/java/runner/TestRunner.java
```

Right-click on:

```text
TestRunner.java
```

Then select:

```text
Run As → TestNG Test
```

---

# 📊 Reporting

After execution, reports are generated inside:

```text
target/cucumber-reports/
```

Open the generated HTML report in browser to view:

- Step execution status
- Passed/Failed scenarios
- Embedded screenshots
- Execution duration

---

# 🔥 Framework Highlights

- Scalable enterprise-level structure
- Clean separation of concerns
- Easy maintenance
- Reusable components
- Production-ready automation architecture
- Supports end-to-end workflow testing

---

# 👨‍💻 Author

Maheshwar Reddy  
Automation Test Engineer