Feature: Detail Produk

  Scenario: Open Detail
    Given Halaman Products
    When Click Title Product
    Then User masuk ke halaman Detail Product
    Then Close

  Scenario: Open New Tab Detail
    Given Halaman Products
    When Right Click Title Product
    Then User masuk ke halaman Detail Product
    Then Close