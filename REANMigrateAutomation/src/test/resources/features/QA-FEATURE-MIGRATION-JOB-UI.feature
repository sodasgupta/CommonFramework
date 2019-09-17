@QA-FEATURE-MIGRATION-JOB-UI @Smoke @Sanity @QA-REGRESSION
Feature: Test on CSV Migration UI page

  Background: 
    Given User is at sign in page
    Then Verify user is at migrate console
    Then Admin sign in

  @Smoke @sanity @QA-REGRESSION
  Scenario: Verify that Discovered, Agents and Migrations are displayed with its Status [MIG 617]
    Given the user is on the Migration Status UI page
    Then the user validates the dashboard labels
      | Label      |
      | DISCOVERED |
      | AGENTS     |
      | MIGRATIONS |
    Then the user validates column header
      | Header         |
      | Name           |
      | Description    |
      | Job Start Time |
      | Type           |
      | Action         |
