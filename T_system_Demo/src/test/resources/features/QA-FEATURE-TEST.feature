@Test
Feature: Test on CSV Discovery feature

  Background: 
    Given User is at login page

  Scenario: Login to the application and validate text Manufacturing Solutions
    Then enter "demo" in "Username" text field
    Then enter "demo" in "Password" text field
    And click on "Log in"
    Then the user validates text "Manufacturing Solutions"
    
    
  Scenario: Login to the application and validate text Manufacturing Insights
    Then enter "demo" in "Username" text field
    Then enter "demo" in "Password" text field
    And click on "Log in"
    Then the user validates text "Manufacturing Insights"

