#Question 2: Return all cars which have the lowest per day rental cost for both cases:
#				a. Price only
#				b. Price after discounts
Feature: Lowest Rental Car

  @tag1
  Scenario: As a user I want to see which car has lowest price
    Given User get request from url
    And Content Type and Accept type is JSON
    When User call Rent a car api
    And Status code is 200
    Then Verify user can see lowest car per day
    




Scenario: As a user I want to see which car has lowest price after discount
Given User get request from url
    And Content Type and Accept type is JSON
    When User call Rent a car api
    And Status code is 200
    Then Verify User can see lowest car per day after discount
    
