@QA-FEATURE-MIGRATE-CONNECTION @QA-REGRESSION @Smoke @Sanity
Feature: Connection Feature

  Background: 
    Given User is at sign in page
    Then Admin sign in

  @Smoke @Sanity @QA-REGRESSION
  Scenario: Verify create, edit and delete ssh connection
    When User is at connection page
    When User enter the connection details
      | connection_ssh | ubuntu | SSH_Key |
    And Click "Save" button
    Then Verify success message
      | Connection Saved successfully. |
    And User click on "connection_ssh" edit icon
    And User Update the Connection details
      | connection_sshEdit | ubuntu |
    And Click "Update" button
    Then Verify success message
      | Server connection updated successfully |
    When Delete connection: "connection_sshEdit"
    Then Verify warning message
      | Connection removed successfully. |

  @Smoke @sanity @QA-REGRESSION
  Scenario: Verify create, edit and delete WinRM connection
    When User is at connection page
    When User enter the winrm connection details
      | connection_winrm | Administrator | Password@123 |
    And Click "Save" button
    Then Verify success message
      | Connection Saved successfully. |
    And User click on "connection_winrm" edit icon
    And User Update the winrm Connection details
      | connection_editWinrm | Administrator | password |
    And Click "Update" button
    Then Verify success message
      | Server connection updated successfully |
    When Delete connection: "connection_editWinrm"
    Then Verify warning message
      | Connection removed successfully. |
