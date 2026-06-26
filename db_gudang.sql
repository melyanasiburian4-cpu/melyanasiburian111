-- TUGAS PROYEK BASIS DATA - APLIKASI GUDANG BARANG MASUK
-- KELOMPOK / MAHASISWA SEMESTER 2

CREATE DATABASE IF NOT EXISTS db_gudang;
USE db_gudang;

-- 1. Tabel Master Barang
CREATE TABLE IF NOT EXISTS tabel_barang (
    kode_barang VARCHAR(10) PRIMARY KEY,
    nama_barang VARCHAR(50) NOT NULL,
    kategori VARCHAR(30),
    stok INT DEFAULT 0
);

-- 2. Tabel Master Supplier
CREATE TABLE IF NOT EXISTS tabel_supplier (
    id_supplier VARCHAR(10) PRIMARY KEY,
    nama_supplier VARCHAR(50) NOT NULL,
    no_telp VARCHAR(15),
    alamat TEXT
);

-- 3. Tabel Transaksi Barang Masuk
CREATE TABLE IF NOT EXISTS transaksi_masuk (
    id_masuk INT AUTO_INCREMENT PRIMARY KEY,
    kode_barang VARCHAR(10),
    id_supplier VARCHAR(10),
    jumlah_masuk INT,
    tanggal DATE,
    FOREIGN KEY (kode_barang) REFERENCES tabel_barang(kode_barang),
    FOREIGN KEY (id_supplier) REFERENCES tabel_supplier(id_supplier)
);

-- 4. Tabel Admin / Pengguna untuk Login
CREATE TABLE IF NOT EXISTS akun_admin (
    username VARCHAR(20) PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    nama_petugas VARCHAR(50)
);

-- Isi data awal buat uji coba (Testing)
INSERT INTO akun_admin (username, password, nama_petugas) VALUES ('admin', 'admin123', 'Budi Setiawan');
INSERT INTO tabel_barang VALUES ('B001', 'Buku Tulis A4', 'Alat Tulis', 50);
INSERT INTO tabel_supplier VALUES ('S001', 'PT Sinar Jaya', '0812345678', 'Jl. Merdeka No. 10');
