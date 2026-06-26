package gudangapp;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class FormSupplier extends JFrame {
    private JTextField txtId, txtNama, txtTelp, txtAlamat;
    private JTable tabelSupplier;
    private JButton btnSimpan, btnBatal;

    public FormSupplier() {
        setTitle("Data Vendor Supplier Barang");
        setSize(550, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel j = new JLabel("INPUT DATA SUPPLIER / VENDOR", JLabel.CENTER);
        j.setBounds(10, 10, 510, 20); add(j);

        JLabel l1 = new JLabel("ID Supplier:"); l1.setBounds(30, 50, 100, 25); add(l1);
        txtId = new JTextField(); txtId.setBounds(140, 50, 120, 25); add(txtId);

        JLabel l2 = new JLabel("Nama PT:"); l2.setBounds(30, 80, 100, 25); add(l2);
        txtNama = new JTextField(); txtNama.setBounds(140, 80, 180, 25); add(txtNama);

        JLabel l3 = new JLabel("No. Telepon:"); l3.setBounds(30, 110, 100, 25); add(l3);
        txtTelp = new JTextField(); txtTelp.setBounds(140, 110, 150, 25); add(txtTelp);

        JLabel l4 = new JLabel("Alamat Toko:"); l4.setBounds(30, 140, 100, 25); add(l4);
        txtAlamat = new JTextField(); txtAlamat.setBounds(140, 140, 220, 25); add(txtAlamat);

        btnSimpan = new JButton("Simpan"); btnSimpan.setBounds(50, 180, 100, 30); add(btnSimpan);
        btnBatal = new JButton("Batal"); btnBatal.setBounds(170, 180, 100, 30); add(btnBatal);

        tabelSupplier = new JTable();
        JScrollPane sp = new JScrollPane(tabelSupplier);
        sp.setBounds(30, 220, 480, 120); add(sp);

        btnSimpan.addActionListener(e -> {
            try {
                Connection c = Koneksi.cekingKoneksi();
                String sql = "INSERT INTO tabel_supplier VALUES (?, ?, ?, ?)";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, txtId.getText());
                ps.setString(2, txtNama.getText());
                ps.setString(3, txtTelp.getText());
                ps.setString(4, txtAlamat.getText());
                ps.execute();
                JOptionPane.showMessageDialog(null, "Supplier Berhasil Dimasukkan!");
                txtId.setText(""); txtNama.setText(""); txtTelp.setText(""); txtAlamat.setText("");
                tampilSupplier();
            } catch(Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage()); }
        });

        tampilSupplier();
    }

    private void tampilSupplier() {
        DefaultTableModel m = new DefaultTableModel(new String[]{"ID", "Nama Supplier", "Telepon", "Alamat"}, 0);
        try {
            Connection c = Koneksi.cekingKoneksi();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM tabel_supplier");
            while(rs.next()) {
                m.addRow(new Object[]{rs.getString("id_supplier"), rs.getString("nama_supplier"), rs.getString("no_telp"), rs.getString("alamat")});
            }
            tabelSupplier.setModel(m);
        } catch(Exception e){}
    }
}
