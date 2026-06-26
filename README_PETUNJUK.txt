# TUGAS BESAR APLIKASI GUDANG (BARANG MASUK) - SEMESTER 2

Proyek NetBeans ini dibuat dengan menggunakan struktur Maven agar seluruh file library driver database (MySQL Connector) dapat terunduh secara otomatis. Ini sangat ampuh untuk mencegah error menyebalkan "package com.mysql.jdbc does not exist" atau driver hilang.

## Daftar 6 Form dalam Aplikasi Ini:
1. FormLogin.java          - Form otentikasi login masuk sistem petugas gudang.
2. FormMenuUtama.java      - Menu Dashboard navigasi utama aplikasi.
3. FormBarang.java         - Form master untuk menambah, menghapus, & melihat data barang.
4. FormSupplier.java       - Form manajemen data vendor/supplier penyuplai barang.
5. FormBarangMasuk.java    - Form transaksi barang masuk (otomatis menambahkan stok barang di Form 3!).
6. FormProfil.java         - Form Biodata Pembuat/Tentang Mahasiswa (Wajib ada di tugas kuliah semester 2).

## Cara Menjalankan Langsung di NetBeans:
1. Extract file `.zip` ini ke folder komputer kamu.
2. Buka aplikasi **NetBeans IDE** (versi Apache NetBeans sangat disarankan).
3. Klik **File** -> **Open Project**.
4. Cari dan pilih folder hasil ekstrak tadi yang bernama **"AplikasiGudangSemester2"** (Tandanya ada logo ikon project Maven/Java kecil). Klik **Open Project**.
5. Buka phpMyAdmin, bikin database baru dengan nama `db_gudang`, lalu **Import** file `db_gudang.sql` yang ada di dalam folder ini.
6. Klik kanan pada project -> pilih **Clean and Build** (Biar NetBeans mendownload library mysql otomatis lewat internet).
7. Jika sudah selesai, klik kanan file `FormLogin.java` -> pilih **Run File** (Atau pencet Shift + F6).
8. Login pakai -> Username: `admin` | Password: `admin123`.

*Kelebihan Gaya Coding Semester 2 ini:*
- Semua file import hanya menggunakan standard `java.sql.*` murni, dijamin tidak ada bentrok/error library lama.
- Codingan bersih, penuh komentar penjelasan, sangat mudah dipahami saat nanti ditanya/dosen menguji aplikasi kamu!
