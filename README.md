# Utilizing Good Gherkin for Test Automation

This code base shows how to utilize minimalist BDD-style Gherkin files for test automation. As a good practice, the implementation details are not specified in the Gherkin file, but instead are pushed down to the code level. This allows automation engineers to be free to implement the scenarios as they see fit.

Given this scenario, the tests are automated against the UI, API, and even visual testing, even though the Gherkin steps do not explicitly state this.

```feature
Scenario: Withdraw from account when funds are available
    Given a customer has an account
    And the account balance is 100.00 dollars
    When the customer withdraws 10.00 dollars
    Then the account balance should be 90.00 dollars
    And a new transaction should be recorded
```

Tools used: Java, Selenium WebDriver, Rest-Assured, Cucumber, Applitools

Video demonstration of this code base can be found as part of the [2019 Automation Guild Conference](https://guildconferences.com/).

