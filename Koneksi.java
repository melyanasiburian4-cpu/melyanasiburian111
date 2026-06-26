package gudangapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    
    private static Connection kon;
    
    public static Connection cekingKoneksi() {
        if (kon == null) {
            try {
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                String url = "jdbc:mysql://localhost:3306/db_gudang";
                String user = "root";
                String pass = "";
                
                kon = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi ke Database Berhasil!");
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan: " + e.getMessage());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal Konek Ke Database! Pastikan XAMPP nyala: " + e.getMessage());
            }
        }
        return kon;
    }
}
