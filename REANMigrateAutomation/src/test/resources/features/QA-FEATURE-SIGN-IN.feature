@QA-FEATURE-SIGN-IN @Smoke @Sanity @QA-REGRESSION
Feature: Test on Sign in 
Background: 
	Given User is at sign in page
	Then Verify user is at migrate console 
	
@QA-FEATURE-SIGN-IN @Smoke @Sanity @QA-REGRESSION
Scenario: Verify admin user can sign in
	When Admin sign in
	Then Verify admin is logged in
	Then Logout user
	
 @QA-FEATURE-SIGN-IN @Smoke @Sanity @QA-REGRESSION
Scenario: Verify invalid user can not sign in
	When User sign in
  | some_user | some_password |
	Then Verify authentication failed error
  | Authentication Failed: User with the username: some_user not found. |

@QA-FEATURE-SIGN-IN @Smoke @Sanity @QA-REGRESSION
Scenario: To verify user should not be able to sign in with empty inputs
    When user click on sign in