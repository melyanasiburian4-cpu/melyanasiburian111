package gudangapp;

import java.awt.*;
import javax.swing.*;

public class FormMenuUtama extends JFrame {
    private JButton btnBarang, btnSupplier, btnTransaksi, btnProfil, btnLogout;

    public FormMenuUtama() {
        setTitle("Menu Dashboard Utama BarangMasuk");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(220, 220, 220));

        JLabel lblJudul = new JLabel("MENU UTAMA APLIKASI BARANGMASUK", JLabel.CENTER);
        lblJudul.setFont(new Font("Serif", Font.BOLD, 18));
        lblJudul.setBounds(10, 20, 460, 30);
        add(lblJudul);

        
        btnBarang = new JButton("Data Master Barang Masuk");
        btnBarang.setBounds(60, 80, 160, 50);
        add(btnBarang);

        
        btnSupplier = new JButton("Data Supplier");
        btnSupplier.setBounds(260, 80, 160, 50);
        add(btnSupplier);

        
        btnTransaksi = new JButton("Input Barang Masuk");
        btnTransaksi.setBounds(60, 160, 160, 50);
        add(btnTransaksi);

        
        btnProfil = new JButton("Profil Pembuat");
        btnProfil.setBounds(260, 160, 160, 50);
        add(btnProfil);

        
        btnLogout = new JButton("Log Out");
        btnLogout.setBackground(Color.RED);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBounds(160, 250, 160, 40);
        add(btnLogout);

        
        btnBarang.addActionListener(e -> new FormBarang().setVisible(true));
        btnSupplier.addActionListener(e -> new FormSupplier().setVisible(true));
        btnTransaksi.addActionListener(e -> new FormBarangMasuk().setVisible(true));
        btnProfil.addActionListener(e -> new FormProfil().setVisible(true));
        
        btnLogout.addActionListener(e -> {
            new FormLogin().setVisible(true);
            this.dispose();
        });
    }
}
