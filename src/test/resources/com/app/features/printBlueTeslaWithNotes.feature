Feature: As a user I want to see same model and color selections in the database

  @test
  Scenario Outline: As a user I want to see same model and color selections in the database
    Given User gets request from url
    When User can call car rental api to see <make> <color>
    Then verify user can see <make> <color>
    Then verify user can see detail notes

    Examples: 
      | make    | color  |
      | "Tesla" | "Blue" |
