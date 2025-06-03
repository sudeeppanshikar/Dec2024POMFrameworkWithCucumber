Feature: Login Page Feature

  @smoke
  Scenario: Check login page title
    Given User is on the login page
    Then the page title should be "Account Login"


  @smoke
  Scenario: Check forgot password link existence
    Given the user is on the login page which contains "?route=account/login"
    When the user checks the forgot password link
    Then the forgot password link should be displayed

  @smoke
  Scenario: Login to application
    Given the user is on the login page which contains "?route=account/login"
    When the user logs in with username "septbatch2024@open.com" and password "Selenium@12345"
    Then the user should be redirected to the accounts page with title "My Account"


#
#  @wip @login
#  Scenario: reset password
#    Given the user is on the login page