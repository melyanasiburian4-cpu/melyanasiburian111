package gudangapp;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class FormBarang extends JFrame {
    private JTextField txtKode, txtNama, txtKategori, txtStok;
    private JTable tabelKu;
    private JButton btnSimpan, btnHapus, btnReset;

    public FormBarang() {
        setTitle("Kelola Master BarangMasuk");
        setSize(600, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel jdl = new JLabel("FORM DATA BARANG", JLabel.CENTER);
        jdl.setFont(new Font("Arial", Font.BOLD, 14));
        jdl.setBounds(10, 10, 560, 20); add(jdl);

        JLabel l1 = new JLabel("Kode Barang:"); l1.setBounds(30, 50, 100, 25); add(l1);
        txtKode = new JTextField(); txtKode.setBounds(140, 50, 150, 25); add(txtKode);

        JLabel l2 = new JLabel("Nama Barang:"); l2.setBounds(30, 80, 100, 25); add(l2);
        txtNama = new JTextField(); txtNama.setBounds(140, 80, 200, 25); add(txtNama);

        JLabel l3 = new JLabel("Kategori:"); l3.setBounds(30, 110, 100, 25); add(l3);
        txtKategori = new JTextField(); txtKategori.setBounds(140, 110, 200, 25); add(txtKategori);

        JLabel l4 = new JLabel("Stok Awal:"); l4.setBounds(30, 140, 100, 25); add(l4);
        txtStok = new JTextField(); txtStok.setBounds(140, 140, 80, 25); add(txtStok);

        btnSimpan = new JButton("Simpan"); btnSimpan.setBounds(30, 180, 90, 30); add(btnSimpan);
        btnHapus = new JButton("Hapus"); btnHapus.setBounds(130, 180, 90, 30); add(btnHapus);
        btnReset = new JButton("Reset"); btnReset.setBounds(230, 180, 90, 30); add(btnReset);

        tabelKu = new JTable();
        JScrollPane sp = new JScrollPane(tabelKu);
        sp.setBounds(30, 230, 520, 150); add(sp);

        btnSimpan.addActionListener(e -> aksiSimpan());
        btnHapus.addActionListener(e -> aksiHapus());
        btnReset.addActionListener(e -> bersihForm());

        muatTabel();
    }

    private void muatTabel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Kode", "Nama Barang", "Kategori", "Stok"}, 0);
        try {
            Connection c = Koneksi.cekingKoneksi();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM tabel_barang");
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("kode_barang"), rs.getString("nama_barang"), rs.getString("kategori"), rs.getInt("stok")});
            }
            tabelKu.setModel(model);
        } catch(Exception e) { System.out.println(e.getMessage()); }
    }

    private void aksiSimpan() {
        try {
            Connection c = Koneksi.cekingKoneksi();
            String sql = "INSERT INTO tabel_barang VALUES (?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, txtKode.getText());
            ps.setString(2, txtNama.getText());
            ps.setString(3, txtKategori.getText());
            ps.setInt(4, Integer.parseInt(txtStok.getText()));
            ps.execute();
            JOptionPane.showMessageDialog(this, "Barang berhasil ditambahkan!");
            bersihForm();
            muatTabel();
        } catch(Exception e) { JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage()); }
    }

    private void aksiHapus() {
        try {
            Connection c = Koneksi.cekingKoneksi();
            String sql = "DELETE FROM tabel_barang WHERE kode_barang=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, txtKode.getText());
            ps.execute();
            JOptionPane.showMessageDialog(this, "Barang Berhasil Dihapus!");
            bersihForm();
            muatTabel();
        } catch(Exception e) { JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage()); }
    }

    private void bersihForm() {
        txtKode.setText(""); txtNama.setText(""); txtKategori.setText(""); txtStok.setText("");
    }
}
