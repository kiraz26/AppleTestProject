#Question 2: Return all cars which have the lowest per day rental cost for both cases:
#				a. Price only
#				b. Price after discounts
@test2
Feature: Lowest Rental Car

  Scenario: As a user I want to see which car has lowest price
    Given User gets request from url
    And Status code is 200 from Response
    Then Verify user can see lowest car per day

  Scenario: As a user I want to see which car has lowest price after discount
    Given User gets request from url
    And Status code is 200 from Response
    Then Verify User can see lowest car per day after discount
