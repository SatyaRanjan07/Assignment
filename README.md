# E-Commerce & Job Discovery Automation Testing

## 📋 Project Overview

This project implements comprehensive automation testing for two web applications:

### **Assignment A: E-Commerce Experience Testing**
**Site**: https://automationexercise.com
- Product discovery and search
- Shopping cart operations
- User registration and login
- Form validation and error handling

### **Assignment B: Job Discovery Experience**
**Site**: https://www.glassdoor.com
- Job search stability and consistency
- Company review extraction
- Salary data extraction and filtering

---

## 🏗️ Project Structure

```
src/
├── main/java/com/automation/
│   └── framework/
│       ├── base/
│       │   ├── BasePage.java              # Base class for all page objects
│       │   └── BaseTest.java              # Base class for all tests
│       ├── core/
│       │   └── DriverManager.java         # WebDriver management
│       ├── pages/
│       │   ├── ecommerce/
│       │   │   ├── HomePage.java
│       │   │   ├── ProductPage.java
│       │   │   ├── CartPage.java
│       │   │   └── LoginPage.java
│       │   └── glassdoor/
│       │       ├── GlassdoorSearchPage.java
│       │       ├── CompanyReviewPage.java
│       │       └── SalaryExplorerPage.java
│       └── utils/
│           └── TestDataProvider.java      # Test data management
├── test/
│   ├── java/com/automation/tests/
│   │   ├── ecommerce/
│   │   │   ├── ProductDiscoveryTest.java
│   │   │   ├── LoginTest.java
│   │   │   └── FormValidationTest.java
│   │   └── glassdoor/
│   │       └── JobSearchTest.java
│   └── resources/
│       ├── application.properties          # Configuration
│       ├── testdata.json                   # Test data
│       └── log4j2.xml                      # Logging configuration
├── pom.xml                                 # Maven configuration
└── testng.xml                              # TestNG suite configuration
```

---

## 📦 Key Dependencies

| Dependency | Version | Purpose |
|---|---|---|
| Selenium | 4.15.0 | Web automation |
| TestNG | 7.8.1 | Test framework |
| WebDriverManager | 5.6.3 | Driver management |
| Log4j2 | 2.20.0 | Logging |
| Gson | 2.10.1 | JSON parsing |
| Typesafe Config | 1.4.2 | Configuration |

---

## 🔑 Key Features

### Page Object Model (POM)
- All UI interactions encapsulated in page classes
- Centralized locator management
- Reusable methods for common operations
- Clear separation of test logic and page logic

### Test Data Management
- Centralized test data in JSON format (`testdata.json`)
- Dynamic email generation with timestamp to avoid conflicts
- Easy test data updates without code changes

### Logging & Reporting
- Comprehensive logging via Log4j2
- Separate error and debug logs
- Detailed test execution trace

### Wait Strategies
- Explicit waits for dynamic content
- 15-second timeout for element interactions
- Proper wait conditions for Glassdoor's heavy JS content

---

## 🎯 Test Scenarios Covered

### **Assignment A: E-Commerce**

#### Scenario 1: "I want to buy something"
- ✅ Browse products and search
- ✅ View product details
- ✅ Add to cart
- ✅ Review cart with accurate quantities and totals

#### Scenario 2: "Am I logged in or not?"
- ✅ Test authenticated login flow
- ✅ Test guest browsing flow
- ✅ Test fresh user registration
- ✅ Test registration with existing email
- ✅ Test invalid login credentials

#### Scenario 3: "What breaks when things go wrong?"
- ✅ Invalid email format validation
- ✅ Empty required field validation
- ✅ Short password validation
- ✅ Missing field validation
- ✅ Error recovery and resubmission

### **Assignment B: Glassdoor**

#### Scenario 1: Search Results Stability
- ✅ Perform QA job search in India
- ✅ Capture job titles and companies
- ✅ Refresh page and verify consistency
- ✅ Compare results across multiple searches

#### Scenario 2: Extracting Reviews
- ✅ Navigate to company reviews
- ✅ Extract top 5 reviews with titles, pros, and cons
- ✅ Support extraction from multiple companies

#### Scenario 3: Salary Trends
- ✅ Search for "QA Engineer" in specific location
- ✅ Extract role titles and average salaries
- ✅ Capture company entries
- ✅ Verify filter functionality

---

## ⚙️ Assumptions & Design Decisions

### Assumptions
1. **Site Stability**: Both sites may have dynamic content and occasional downtime. Tests include appropriate waits and error handling.
2. **Email Uniqueness**: Test data uses timestamp-based email generation to ensure fresh registrations always succeed.
3. **Bot Detection**: Glassdoor has anti-bot mechanisms. Tests include realistic waits and navigation patterns.
4. **JavaScript Rendering**: Both sites rely on JS. Tests wait for elements to be interactive, not just present.
5. **Credentials**: Sample credentials may not exist on live sites. Update `testdata.json` with valid credentials for actual execution.

### Design Decisions
1. **POM Structure**: Minimizes test maintenance and improves readability
2. **Explicit Waits**: Avoids flaky tests and implicit wait issues
3. **Centralized Data**: Makes test data management and CI/CD integration easier
4. **Logging**: Comprehensive logging for debugging and troubleshooting
5. **Thread-Safe Driver**: Uses ThreadLocal for parallel test execution support

---

## 🚀 How to Run Tests

### Prerequisites
```bash
- Java 11 or higher
- Maven 3.6+
- Chrome browser (or update browser in DriverManager.java)
- Internet connection
```

### Installation & Setup

1. **Clone or download the project**
   ```bash
   cd S:\Project\AssignmentProject\Assignment
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Update test data** (if needed)
   - Edit `src/test/resources/testdata.json`
   - Add valid credentials for the test sites

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=ProductDiscoveryTest
```

### Run Specific Test Method
```bash
mvn test -Dtest=ProductDiscoveryTest#testProductDiscovery
```

### Run E-Commerce Tests Only
```bash
mvn test -Dtest=com.automation.tests.ecommerce.*
```

### Run Glassdoor Tests Only
```bash
mvn test -Dtest=com.automation.tests.glassdoor.*
```

### Run with TestNG Suite
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Headless Mode
To run tests in headless mode, uncomment the headless option in `DriverManager.java`:
```java
// options.addArguments("--headless");
```

---

## 🔧 Handling Dynamic Content & Instability

### E-Commerce (automationexercise.com)
- **Issue**: Occasional server timeouts and slow page loads
- **Solution**: 
  - 15-second explicit waits for critical elements
  - Added `Thread.sleep(2000)` between actions for synchronization
  - Retry mechanism for flaky operations

### Glassdoor (glassdoor.com)
- **Issue**: Heavy JavaScript, dynamic content loading, anti-bot detection
- **Solution**:
  - Explicit waits for job listings to load
  - 5-second waits after search operations
  - Realistic navigation patterns (no rapid clicks)
  - Waits for AJAX calls to complete before assertion
  - Handled ad overlays by waiting for elements to be clickable

### General Strategies
- **Implicit vs Explicit Waits**: Used explicit waits (ExpectedConditions) for reliability
- **Wait Conditions**: 
  - `presenceOfElementLocated` for DOM elements
  - `elementToBeClickable` for interaction
  - Custom waits for dynamic content
- **Logging**: All wait failures logged for debugging
- **Error Handling**: Try-catch blocks for non-critical assertions

---

## 📊 Test Execution & Reports

### Logs Location
- **All logs**: `logs/automation_tests.log`
- **Error logs**: `logs/automation_errors.log`

### View Logs
```bash
type logs\automation_tests.log
```

### TestNG Reports
After running tests, TestNG generates reports in:
```
target/surefire-reports/
```

View HTML report:
```bash
target\surefire-reports\index.html
```

---

## 🔄 CI/CD Integration

### GitHub Actions Example
```yaml
- name: Run Automation Tests
  run: mvn clean test -Dbrowser=chrome
```

### Jenkins Example
```groovy
stage('Test') {
    steps {
        sh 'mvn clean test'
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
```

---

## 📝 Test Data Management

### Update Test Data
Edit `src/test/resources/testdata.json`:

```json
{
  "ecommerce": {
    "validUser": {
      "email": "your-email@example.com",
      "password": "your-password"
    }
  }
}
```

### Using Test Data in Tests
```java
String email = TestDataProvider.getEcommerceValidEmail();
String password = TestDataProvider.getEcommerceValidPassword();
```

---

## 🐛 Troubleshooting

### Issue: Tests fail with "NoSuchElementException"
**Solution**: Increase wait times in `BasePage.java`
```java
private static final int TIMEOUT = 20;  // Increase from 15 to 20
```

### Issue: "Chrome driver not found"
**Solution**: WebDriverManager should auto-download. If not:
```bash
mvn clean install -U
```

### Issue: "Connection timeout on Glassdoor"
**Solution**: The site may be unstable. Try:
- Running tests during off-peak hours
- Adding proxy support in `DriverManager.java`
- Increasing wait times for Glassdoor tests

### Issue: Tests fail locally but not in CI
**Solution**: Likely headless mode issues
- Run with GUI: Disable headless mode
- Check browser version compatibility

---

## 📚 References

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Page Object Model Best Practices](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

---

## ✅ Validation Checklist

Before submitting:
- [ ] All tests pass locally
- [ ] Test data is properly configured
- [ ] Logs are generated successfully
- [ ] README is comprehensive
- [ ] Code follows POM structure
- [ ] Assertions are meaningful
- [ ] Error handling is in place

---

## 📧 Support & Maintenance

For issues or improvements:
1. Check logs in `logs/` directory
2. Review test data in `testdata.json`
3. Verify browser and WebDriver versions
4. Increase wait times for slow networks

---

## 📄 License & Notes

This project is created for educational and testing purposes.

**Important Notes**:
- The automation exercise site may experience downtime
- Glassdoor has anti-bot measures; use responsibly
- Update test credentials before running against live environments
- Logs contain sensitive information; handle appropriately

---

**Last Updated**: April 29, 2026
**Framework Version**: 1.0.0
**Java Version**: 11+
**Selenium Version**: 4.15.0
