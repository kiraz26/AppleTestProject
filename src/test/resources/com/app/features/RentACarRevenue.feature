#Question 3: Find the highest revenue generating car. year over year maintenance cost + depreciation is the total expense per car for the full year for the rental car company.
#			The objective is to find those cars that produced the highest profit in the last year
Feature: Highest Profit Car

  @tag1
  Scenario: As a user I want to see which car has a biggest revenue per year to date
    Given User get request from url
    And Content Type and Accept type is JSON
    When User call Rent a car api
    And Status code is 200
    Then Verify user can see revenues for each car

  Scenario: As a user I want to see which car has a biggest profit per year to date
    Given User get request from url
    And Content Type and Accept type is JSON
    When User call Rent a car api
    And Status code is 200
    Then Verify User can see see car profits in descending order
