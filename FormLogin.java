package gudangapp;

import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FormLogin extends JFrame {
    // Komponen GUI
    private JTextField inputUser;
    private JPasswordField inputPass;
    private JButton tombolLogin, tombolKeluar;

    public FormLogin() {
        
        setTitle("Form Login");
        setSize(400, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(230, 240, 250));

        JLabel judul = new JLabel("APLIKASI BARANG MASUK (LOGIN)", JLabel.CENTER);
        judul.setFont(new Font("Arial", Font.BOLD, 16));
        judul.setBounds(10, 20, 360, 30);
        add(judul);

        JLabel lblUser = new JLabel("Username :");
        lblUser.setBounds(40, 70, 100, 25);
        add(lblUser);

        inputUser = new JTextField();
        inputUser.setBounds(140, 70, 180, 25);
        add(inputUser);

        JLabel lblPass = new JLabel("Password :");
        lblPass.setBounds(40, 110, 100, 25);
        add(lblPass);

        inputPass = new JPasswordField();
        inputPass.setBounds(140, 110, 180, 25);
        add(inputPass);

        tombolLogin = new JButton("Login");
        tombolLogin.setBounds(80, 160, 100, 30);
        add(tombolLogin);

        tombolKeluar = new JButton("Keluar");
        tombolKeluar.setBounds(200, 160, 100, 30);
        add(tombolKeluar);

        
        tombolLogin.addActionListener(e -> {
            String user = inputUser.getText();
            String pass = new String(inputPass.getPassword());
            
            try {
                Connection c = Koneksi.cekingKoneksi();
                String sql = "SELECT * FROM akun_admin WHERE username=? AND password=?";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, user);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Selamat! Login Berhasil.");
                    new FormMenuUtama().setVisible(true);
                    this.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau Password Anda Salah!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        
        tombolKeluar.addActionListener(e -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            new FormLogin().setVisible(true);
        });
    }
}
