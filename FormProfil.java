package gudangapp;

import java.awt.*;
import javax.swing.*;

public class FormProfil extends JFrame {
    public FormProfil() {
        setTitle("Form Profil Pembuat");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 220));

        JLabel lJdl = new JLabel("BIODATA MAHASISWA PEMBUAT", JLabel.CENTER);
        lJdl.setFont(new Font("Arial", Font.BOLD, 14));
        lJdl.setBounds(10, 20, 360, 25); add(lJdl);

        JLabel lNama = new JLabel("Nama Lengkap :  Kelompok");
        lNama.setBounds(40, 70, 320, 25); add(lNama);

        JLabel lNim = new JLabel("NIM                   :  (Semester 2)");
        lNim.setBounds(40, 100, 320, 25); add(lNim);

        JLabel lKelas = new JLabel("Kelas                 :  Teknik Informatika");
        lKelas.setBounds(40, 130, 320, 25); add(lKelas);

        JLabel lMatkul = new JLabel("Mata Kuliah       :  Pemrograman Berorientasi Objek");
        lMatkul.setBounds(40, 160, 320, 25); add(lMatkul);

        JButton btnTutup = new JButton("Tutup Form");
        btnTutup.setBounds(140, 210, 120, 30); add(btnTutup);

        btnTutup.addActionListener(e -> this.dispose());
    }
}
