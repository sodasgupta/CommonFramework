@QA-FEATURE-PROVIDER @Smoke @sanity @QA-REGRESSION
Feature: Test on Provider functionality

  Background: 
    Given User is at sign in page
    Then Verify user is at migrate console
    Then Admin sign in

  @Smoke @sanity @QA-REGRESSION
  Scenario: Verify the behavior of various input fields while creating new provider
    Given User is at the Admin Console
    Then the user navigates to "Providers" page
    And click on "selectedProviderType"
    Then the user selects provider "AWS"
    #And the user enters provider name " "
    Then the save button should be "disabled"
