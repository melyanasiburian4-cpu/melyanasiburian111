package gudangapp;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class FormBarangMasuk extends JFrame {
    private JComboBox<String> cmbBarang, cmbSupplier;
    private JTextField txtJumlah, txtTanggal;
    private JTable tabelMasuk;
    private JButton btnSimpanTransaksi;

    public FormBarangMasuk() {
        setTitle("Input Transaksi Barang Masuk - Form 5");
        setSize(650, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel j = new JLabel("PENCATATAN TRANSAKSI BARANG MASUK", JLabel.CENTER);
        j.setFont(new Font("Arial", Font.BOLD, 13));
        j.setBounds(10, 10, 610, 20); add(j);

        JLabel l1 = new JLabel("Pilih Barang:"); l1.setBounds(30, 50, 120, 25); add(l1);
        cmbBarang = new JComboBox<>(); cmbBarang.setBounds(160, 50, 220, 25); add(cmbBarang);

        JLabel l2 = new JLabel("Pilih Vendor:"); l2.setBounds(30, 80, 120, 25); add(l2);
        cmbSupplier = new JComboBox<>(); cmbSupplier.setBounds(160, 80, 220, 25); add(cmbSupplier);

        JLabel l3 = new JLabel("Jumlah Masuk:"); l3.setBounds(30, 110, 120, 25); add(l3);
        txtJumlah = new JTextField(); txtJumlah.setBounds(160, 110, 80, 25); add(txtJumlah);

        JLabel l4 = new JLabel("Tanggal (YYYY-MM-DD):"); l4.setBounds(30, 140, 150, 25); add(l4);
        txtTanggal = new JTextField("2026-06-23"); txtTanggal.setBounds(180, 140, 120, 25); add(txtTanggal);

        btnSimpanTransaksi = new JButton("Simpan Log Transaksi");
        btnSimpanTransaksi.setBounds(30, 180, 200, 30); add(btnSimpanTransaksi);

        tabelMasuk = new JTable();
        JScrollPane sp = new JScrollPane(tabelMasuk);
        sp.setBounds(30, 230, 570, 150); add(sp);

        btnSimpanTransaksi.addActionListener(e -> simpanKuy());

        ambilBarang();
        ambilSupplier();
        tampilTransaksi();
    }

    private void ambilBarang() {
        try {
            Connection c = Koneksi.cekingKoneksi();
            ResultSet rs = c.createStatement().executeQuery("SELECT kode_barang, nama_barang FROM tabel_barang");
            while(rs.next()) {
                cmbBarang.addItem(rs.getString("kode_barang") + " - " + rs.getString("nama_barang"));
            }
        } catch(Exception e){}
    }

    private void ambilSupplier() {
        try {
            Connection c = Koneksi.cekingKoneksi();
            ResultSet rs = c.createStatement().executeQuery("SELECT id_supplier, nama_supplier FROM tabel_supplier");
            while(rs.next()) {
                cmbSupplier.addItem(rs.getString("id_supplier") + " - " + rs.getString("nama_supplier"));
            }
        } catch(Exception e){}
    }

    private void tampilTransaksi() {
        DefaultTableModel m = new DefaultTableModel(new String[]{"ID Transaksi", "Nama Barang", "Nama Supplier", "Jumlah", "Tanggal"}, 0);
        try {
            Connection c = Koneksi.cekingKoneksi();
            String sql = "SELECT tm.id_masuk, b.nama_barang, s.nama_supplier, tm.jumlah_masuk, tm.tanggal " +
                         "FROM transaksi_masuk tm JOIN tabel_barang b ON tm.kode_barang = b.kode_barang " +
                         "JOIN tabel_supplier s ON tm.id_supplier = s.id_supplier";
            ResultSet rs = c.createStatement().executeQuery(sql);
            while(rs.next()) {
                m.addRow(new Object[]{rs.getInt("id_masuk"), rs.getString("nama_barang"), rs.getString("nama_supplier"), rs.getInt("jumlah_masuk"), rs.getString("tanggal")});
            }
            tabelMasuk.setModel(m);
        } catch(Exception e){}
    }

    private void simpanKuy() {
        try {
            String itemB = (String) cmbBarang.getSelectedItem();
            String itemS = (String) cmbSupplier.getSelectedItem();
            if(itemB == null || itemS == null) return;

            String kBarang = itemB.split(" - ")[0];
            String idSup = itemS.split(" - ")[0];
            int jml = Integer.parseInt(txtJumlah.getText());
            String tgl = txtTanggal.getText();

            Connection c = Koneksi.cekingKoneksi();
            
            c.setAutoCommit(false);

            
            PreparedStatement ps1 = c.prepareStatement("INSERT INTO transaksi_masuk (kode_barang, id_supplier, jumlah_masuk, tanggal) VALUES (?, ?, ?, ?)");
            ps1.setString(1, kBarang);
            ps1.setString(2, idSup);
            ps1.setInt(3, jml);
            ps1.setString(4, tgl);
            ps1.executeUpdate();

            
            PreparedStatement ps2 = c.prepareStatement("UPDATE tabel_barang SET stok = stok + ? WHERE kode_barang = ?");
            ps2.setInt(1, jml);
            ps2.setString(2, kBarang);
            ps2.executeUpdate();

            c.commit();
            JOptionPane.showMessageDialog(this, "Transaksi Sukses! Stok otomatis ditambahkan.");
            tampilTransaksi();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Transaksi: " + e.getMessage());
        }
    }
}
