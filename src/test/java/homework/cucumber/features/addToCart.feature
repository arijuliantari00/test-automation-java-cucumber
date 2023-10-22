Feature: Add To Cart

  Scenario: add To Cart from Halaman Shop
    Given Halaman Products
    When Click Add to cart button
    And Click Cart Button
    Then User Masuk Halaman Cart
    Then Close

  Scenario: add To Cart from Halaman Detail Product
    Given Halaman Products
    When Click Title Product
    Then User masuk ke halaman Detail Product
    And Click Add to cart button
    And Click Back to Product
    And Click Cart Button
    Then User Masuk Halaman Cart
    Then Close