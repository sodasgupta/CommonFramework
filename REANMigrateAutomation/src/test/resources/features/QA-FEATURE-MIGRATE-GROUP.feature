@QA-FEATURE-MIGRATE-GROUP @Smoke @Sanity @QA-REGRESSION
Feature: Test on server group feature

  Background: 
    Given User is at sign in page
    Then Verify user is at migrate console
    Then Admin sign in

  @Smoke @Sanity @P4 @QA-REGRESSION
  Scenario: To verify that user is able to create group with valid fields (MIG-577)
    When User is at group page
    And User enter the server group details
      | group_name | group_description |
    And Click "Create Group" button

  @Smoke @Sanity @P4 @QA-REGRESSION
  Scenario: To verify that user is able to create group with same name (MIG-580)
    When User is at group page
    And User enter the server group details
      | group_name | group_description |
    And Click "Create Group" button
    Then Verify error message
      | Group with the name group_name already exists. |

  @Smoke @Sanity @P4 @QA-REGRESSION
  Scenario: To verify that user is able to create group without server(MIG-578)
    When User is at group page
    And User enter the server details:
      | group_name | group_description |
    And Click Create Group button
