@QA-FEATURE-MIGRATION-CREDENTIALS @Smoke @Sanity @QA-REGRESSION
Feature: Test on Migration credentials feature

  Background: 
    Given User is at sign in page
    Then Verify user is at migrate console
    Then Admin sign in

  @Smoke @Sanity @P4 @QA-REGRESSION
  Scenario: To verify add,edit, delete migration credentials.And To verify Credential name is unique.(MIG-571,MIG-574,MIG-575,MIG-558,MIG-576)
    When User is at migration credentials page
    And User enter the migration credentials details
      | credentials_name | menka@hitachi.com | menaka@123 |
    And Click "Save" button
    Then Verify success message
      | Migration credentials Saved successfully |
    And User enter the migration credentials details
      | credentials_name | menka@hitachi.com | menaka@123 |
    And Click "Save" button
    Then Verify warning message
      | Migration credentials with the name credentials_name already exists. |
    When User click on edit: "credentials_name"
    And User update the credentials details
      | edit_credentials_name | edit_menka | editmenka@123 |
    And Click "Update" button
    Then Verify success message
      | Migration credentials Updated successfully. |
    When User click on delete: "edit_credentials_name"
    And Click "Cancel" span
    Then Verify user "edit_credentials_name" is present
    When User click on delete: "edit_credentials_name"
    And Click "Yes!" span
    Then Verify success message
      | Migration credentials removed successfully |

  @Smoke @Sanity @P4 @QA-REGRESSION
  Scenario: To verify user should not able to add empty migration credentials.(MIG-573_
    When User is at migration credentials page
    And Click on : "Save" button
