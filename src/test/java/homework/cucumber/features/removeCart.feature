Feature: Add To Cart

  Scenario: remove Cart from Halaman Products
    Given Halaman Products with two cart product
    When Click remove button
    Then Cart have one product
    Then Close

  Scenario: remove Cart from Halaman Cart
    Given Halaman Products with two cart product
    When Click Cart Button
    Then User Masuk Halaman Cart
    When Click remove button
    Then Cart have one product
    Then Close

  Scenario: remove Cart from Halaman Detail Product
    Given Halaman Products with two cart product
    When Click Title Product
    And Click remove button
    Then Cart have one product
    Then Close