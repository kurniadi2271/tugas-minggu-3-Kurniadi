# SauceDemo Automation Test

## Deskripsi
Project ini adalah **automasi testing web aplikasi** [SauceDemo](https://www.saucedemo.com) menggunakan **Java, Selenium 4, TestNG, dan Maven**.  
Tujuan testing:
- Verifikasi fungsi login (positif & negatif)
- Verifikasi fungsi “Add to Cart” (positif & negatif)
- Menyediakan report otomatis dari TestNG

---

## Prasyarat
Sebelum menjalankan project, pastikan:
- Java JDK 11+ terinstal
- Maven terinstal
- Browser Google Chrome terinstal
- IDE (IntelliJ / Eclipse) opsional, bisa menggunakan cmd/terminal
- [Excel](https://drive.google.com/drive/folders/1dWXkjK5yhis66sdoBiRX5yGt5Io-Osv2?usp=drive_link) untuk membuka file SIT

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
    │                   ├── InventoryPage.java
    │                   └── LoginPage.java
    │
    └── test
        └── java
        └── com
        └── juaracoding
            └── resources
                ├── LoginTest.java
                └── AddToCartTest.java