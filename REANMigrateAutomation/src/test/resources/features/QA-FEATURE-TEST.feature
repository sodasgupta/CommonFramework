@Test
Feature: Test on CSV Discovery feature

  Background: 
    Given User is at home page

  Scenario: New User creation
    Given User enter data for the following fields and click on "Add"
      | Name   | Age | Salary |
      | Souvik |  29 |  10000 |
    Then user wait "60" sec

  Scenario: Edit User
    Given User click on Edit for below resource
      | Name   |
      | Souvik |
    Then enter "Abhijit" in "Name" text field
    Then click on "Update"
     Then user wait "60" sec

  Scenario: Remove User
    Given User click on Remove for below resource
      | Name   |
      | Abhijit |
       Then user wait "60" sec
