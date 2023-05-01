Feature: ReportPortal Launches

  Background:
    Given User opens login page
    When User logs in as default user
    Then Login notification "Signed in successfully" is displayed

  Scenario Outline: User can see lauches table column
    When User opens launches tab
    Then Launches table contains "<column>" column

    Examples:
      | column         |
      | TOTAL          |
      | PASSED         |
      | FAILED         |
      | SKIPPED        |
      | PRODUCT BUG    |
      | AUTO BUG       |
      | SYSTEM ISSUE   |
      | TO INVESTIGATE |