@QA-FEATURE-CSV-DISCOVERY @Smoke @Sanity @QA-REGRESSION
Feature: Test on CSV Discovery feature

  Background: 
    Given User is at sign in page
    Then Verify user is at migrate console
    Then Admin sign in

  @Smoke @Sanity @P4 @QA-REGRESSION
  Scenario: To verify user is able to create discovery with valid CSV file And To verify discovery name is unique.(MIG-563,MIG-645)
    When User is at discovery page
    And User enter the discovery details
      | dis_test | test | CSV |
    And Import csv file
      | migrate.csv |
    And click "Create Discovery" button
    Then Verify success message
      | Successfully created the Discovery job |
    When User is at discovery page
    And User enter the discovery details
      | dis_test | test | CSV |
    And Import csv file
      | migrate.csv |
    And click "Create Discovery" button
    Then Verify same name error message for discovery
      | Job with the name | dis_test | already exists. |

  @Smoke @sanity @QA-REGRESSION
  Scenario: To verify user is not able to create discovery with invalid CSV (MIG-564)
    When User is at discovery page
    And User enter the discovery details
      | invalid_123487 | test | CSV |
    And Import csv file with OS column validation
      | migrate_wrong_data.csv |
    And click "Create Discovery" button
    Then Verify error message
      | Invalid OS mentioned on line number: 1 |
    When User enter the discovery details
      | invalid_123487 | test | CSV |
    And Import csv file with duplicate column validation
      | migrate_duplicate_header.csv |
    And click "Create Discovery" button
    Then Verify error message
      | CSV contains duplicate column header :HostName |
    When User enter the discovery details
      | invalid_123487 | test | CSV |
    And Import csv file empty row validation
      | migrate_empty_csv_row.csv |
    And click "Create Discovery" button
    Then Verify error message
      | CSV contains empty row on line number: 4 |

  @Sanity @P4 @QA-REGRESSION
  Scenario: To verify user is not able to create discovery with empty CSV (MIG-565)
    When User is at discovery page
    When User enter the discovery details
      | empty_csv11230986_empty | test | CSV |
    When Import csv file
      | empty_csv.csv |
    When click "Create Discovery" button
    Then Verify error message
      | No servers present in the CSV empty_csv11230986_empty |
