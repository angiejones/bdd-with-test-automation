Feature: Withdraw
  As a customer, I want to withdraw funds from my account

Scenario: Withdraw from account when funds are available
    Given a customer has an account
    And the account balance is 100.00 dollars
    When the customer withdraws 10.00 dollars
    Then the account balance should be 90.00 dollars
    And a new transaction should be recorded

