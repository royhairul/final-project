package com.finalProject;

import java.sql.*;

// Penyimpanan Data dengan Database

public class DatabaseSystem {
    public static Connection connectDB() {
        try {
            final String URL = "jdbc:mysql://localhost/db_task_manager";
            final String USERNAME = "root";
            final String PASSWORD = "";

            // Koneksi ke Database
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        catch (Exception e) {
            // Jika terjadi error pada saat menghubungkan ke dalam database
            e.printStackTrace();
            return null;
        }
    }

    public static void saveToDB(Task task) {
        try {
            Connection conn = connectDB();

            // Membuat Query / Perintah SQL
            String query = "INSERT INTO `tb_list_task` (`name`, `matkul`, `deadline`) VALUES (?, ?, ?)";

            // Membuat Statement
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(query);


            // Set nilai parameter
            stmt.setString(1, task.namaTugas);
            stmt.setString(2, task.mataKuliah);
            stmt.setString(3, task.tglPengumpulan);

            // Eksekusi pernyataan SQL
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        }

        catch (Exception e) {
            // Jika terjadi error pada saat menghubungkan ke dalam database
            e.printStackTrace();
        }
    }

    public static void removefromDB(String namaTugas) {
        try {
            Connection conn = connectDB();
            // Membuat Query / Perintah SQL
                String query = "DELETE FROM `tb_list_task` WHERE name = ?";

            // Membuat Statement
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set nilai parameter
            stmt.setString(1, namaTugas);

            // Eksekusi pernyataan SQL
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        }

        catch (Exception e) {
            // Jika terjadi error pada saat menghubungkan ke dalam database
            e.printStackTrace();
        }
    }

    public static void searchFromDBByName(String namaTugas) {
        try {
            Connection conn = connectDB();

            // Membuat Query / Perintah SQL
            String query = "SELECT * FROM `tb_list_task` WHERE name = ?";

            // Membuat Statement
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set nilai parameter
            stmt.setString(1, namaTugas);

            // Eksekusi pernyataan SQL
            ResultSet res = stmt.executeQuery();

            int i = 0;
            while (res.next()) {
                i++;
                System.out.println(" - Nama Tugas  : " + res.getString("name"));
                System.out.println(" - Mata Kuliah : " + res.getString("matkul"));
                System.out.println(" - Deadline    : " + res.getString("deadline") + "\n");
            }

            stmt.close();
            conn.close();
        }

        catch (Exception e) {
            // Jika terjadi error pada saat menghubungkan ke dalam database
            e.printStackTrace();
        }
    }

    public static void searchFromDBByMatkul(String mataKuliah) {
        try {
            Connection conn = connectDB();

            // Membuat Query / Perintah SQL
            String query = "SELECT * FROM `tb_list_task` WHERE matkul = ?";

            // Membuat Statement
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set nilai parameter
            stmt.setString(1, mataKuliah);

            // Eksekusi pernyataan SQL
            ResultSet res = stmt.executeQuery();

            System.out.println(" Mata Kuliah : " + mataKuliah);

            int i = 0;
            while (res.next()) {
                i++;
                System.out.println(" - Nama Tugas  : " + res.getString("name"));
                System.out.println(" - Deadline    : " + res.getString("deadline") + "\n");
            }

            stmt.close();
            conn.close();
        }

        catch (Exception e) {
            // Jika terjadi error pada saat menghubungkan ke dalam database
            e.printStackTrace();
        }
    }

    public static void printFromDB() {
        try {
            Connection conn = connectDB();

            // Membuat Statement
            assert conn != null;
            Statement stmt = conn.createStatement();

            // Membuat Query / Perintah SQL
            String query = "SELECT * FROM `tb_list_task`";

            // Eksekusi Queri dan menyimpan hasilnya
            ResultSet res = stmt.executeQuery(query);

            int i = 0;
            while (res.next()) {
                i++;
                System.out.println(" Tugas ke - " + (i));
                System.out.println(" - Nama Tugas  : " + res.getString("name"));
                System.out.println(" - Mata Kuliah : " + res.getString("matkul"));
                System.out.println(" - Deadline    : " + res.getString("deadline") + "\n");
            }

            stmt.close();
            conn.close();
        }

        catch (Exception e) {
            // Jika terjadi error pada saat menghubungkan ke dalam database
            e.printStackTrace();
        }
    }
}
