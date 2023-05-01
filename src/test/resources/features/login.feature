Feature: Login to ReportPortal

  Scenario: User can successfully login
    Given User opens login page
    When User logs in as default user
    Then Login notification "Signed in successfully" is displayed

  Scenario: User can't login with bad credentials
    Given User opens login page
    When User logs in with credentials
      | login | password |
    Then Login notification "An error occurred while connecting to server: You do not have enough permissions. Bad credentials" is displayed

  Scenario: User can restore password
    Given User opens login page
    When User clicks Forgot Password?
    Then Restore option should be available