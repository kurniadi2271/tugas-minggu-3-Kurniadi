# SauceDemo Automation Test

## Deskripsi
Project ini adalah **automasi testing web aplikasi** [SauceDemo](https://www.saucedemo.com) menggunakan **Java, Selenium 4, TestNG, dan Maven**.  
Tujuan testing:
- Verifikasi fungsi login (positif & negatif)
- Verifikasi fungsi “Add to Cart” (positif & negatif)
- Verifikasi fungsi “Checkout” (positif & negatif)
- Menyediakan report otomatis dari TestNG

---

## Prasyarat
Sebelum menjalankan project, pastikan:
- Java JDK 11+ terinstal
- Maven terinstal
- Browser Google Chrome terinstal
- IDE (IntelliJ / Eclipse) opsional, bisa menggunakan cmd/terminal
- [Excel](https://drive.google.com/drive/folders/1nhk1hWWWwe6rLHDa8iG44-TwO4hkiCFi?usp=drive_link) untuk membuka file SIT

---

## Struktur Folder
```plaintext
selenium-test/
│── pom.xml
│
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── juaracoding
    │               └── kurniadi
    │                   └── CheckoutPage.java
    │                   ├── InventoryPage.java
    │                   └── LoginPage.java
    │
    └── test
        └── java
        └── com
        └── juaracoding
            └── resources
                └── AddToCartTest.java
                ├── CheckoutTest.java
                ├── LoginTest.java