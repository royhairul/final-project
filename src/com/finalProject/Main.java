package com.finalProject;

import java.util.Scanner;

import static com.finalProject.UserMenuHandling.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in).useDelimiter("\n");

        ListTask lTugas = new ListTask();
        CompletedTask lTugasSelesai = new CompletedTask();
        PriorityTask lTugasPrioritas = new PriorityTask();

        String inputChar;
        do {
            // Menampilkan Menu
            System.out.println("==--- Selamat Datang --------------==");
            System.out.println("1. Tambah Tugas");
            System.out.println("2. Hapus Tugas");
            System.out.println("3. Cari Tugas");
            System.out.println("4. Selesaikan Tugas");
            System.out.println("5. Tampilkan Daftar Tugas");
            System.out.println("==---------------------------------==");
            System.out.print(" Masukkan pilihan (1/2/3/4/5): ");

            int inputJawaban = input.nextInt();

            switch (inputJawaban) {
                case 1 -> {
                    // Tambah Tugas
                    addTaskFromUser(input, lTugas, lTugasPrioritas);
                }

                case 2 -> {
                    // Hapus Tugas
                    removeTaskByUser(input, lTugas, lTugasPrioritas);
                }

                case 3 -> {
                    // Cari Tugas
                    searchTask(input, lTugas, lTugasSelesai);
                }

                case 4 -> {
                    // Menyelesaikan Tugas
                    doTask(input, lTugas, lTugasPrioritas, lTugasSelesai);
                }

                case 5 -> {
                    // Menampilkan Tugas
                    printTask(input, lTugas, lTugasPrioritas, lTugasSelesai);
                }

                default -> {
                    System.out.println(" Pilihan yang anda masukkan tidak valid.");
                }
            }

            System.out.print("\n Kembali ke Menu (Y/n) ? ");
            inputChar = input.next();

        } while(inputChar.equalsIgnoreCase("Y"));

        System.out.println("-- Program Ended :D --");
    }
}