Feature: Account Page

  @regression
  Scenario Outline: Search image and search product
    Given the user is in account page with the page title "My Account"
    When the user search for the product "<product_search>"
    Then the product and no of image should match <resultsCount>
    Examples:
      | product_search | resultsCount |
      | macbook        | 3            |
      | imac           | 1            |
      | samsung        | 2            |
      | Airtel         | 0            |

  @regression
  Scenario: Verify the Account Page Header Test
    Given the user is in account page with the page title "My Account"
    When check for the account page header
    Then the list of header should match
      | My Account           |
      | My Orders            |
      | My Affiliate Account |
      | Newsletter           |

  @regression
Scenario: Verify the account page URL
  Given the user is in account page with the page title "My Account"
  When check for the account page URL
  Then the URL should contain "?route=account/account"