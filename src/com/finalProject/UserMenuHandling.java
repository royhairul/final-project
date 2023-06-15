package com.finalProject;

import java.time.LocalDate;
import java.util.Scanner;

public class UserMenuHandling {

    public static void addTaskFromUser(Scanner input, ListTask lT, PriorityTask lTP) {
        do {
            System.out.print(" Masukkan Nama Tugas : ");
            String inputNama = input.next();

            System.out.print(" Masukkan Mata Kuliah : ");
            String inputMatKul = input.next();

            boolean isValid = false;
            String inputTanggal = null;

            while (!isValid) {
                System.out.print(" Masukkan Deadline (YYYY-MM-DD) : ");
                inputTanggal = input.next();
                String tglHariIni = LocalDate.now().toString();

                if (inputTanggal.compareTo(tglHariIni) > 0) {
                    isValid = true;
                } else {
                    System.out.println("Tanggal Yang Anda Masukkan Tidak Valid");
                }

            }

            Task tugasBaru = new Task(inputNama, inputTanggal, inputMatKul);

            lT.addTask(tugasBaru);
            lTP.enqueue(tugasBaru);

            System.out.print(" Tambah Tugas Lagi (Y/n) ? ");
        } while(input.next().equalsIgnoreCase("Y"));
    }

    public static void removeTaskByUser(Scanner input, ListTask lT, PriorityTask lTP) {
        System.out.print(" Masukkan Nama Tugas : ");
        String inputNamaTugas = input.next();

        lT.removeTask(inputNamaTugas);
        Task tugasDihapus = lTP.removeByName(inputNamaTugas);

        if(tugasDihapus != null) {
            System.out.println(" (-) Tugas " + inputNamaTugas + " telah dihapus");
        } else {
            System.out.println(" (!) Tugas yang akan dihapus tidak ditemukan...");
        }
    }

    public static void searchTask(Scanner input, ListTask lT, CompletedTask cT) {
        System.out.println();
        System.out.println("==--- Pencarian Tugas -------==");
        System.out.println("1. Berdasarkan Nama Tugas");
        System.out.println("2. Berdasarkan Mata Kuliah ");
        System.out.println("==---------------------------==");
        System.out.print(" Masukkan Pilihan (1/2) : ");
        int inputPencarian = input.nextInt();

        switch (inputPencarian) {
            case 1 -> {
                System.out.print(" Masukkan Nama Tugas : ");
                String inputNamaTugas = input.next();
                lT.cariTugasByNama(inputNamaTugas);
            }
            case 2 -> {
                System.out.print(" Masukkan Mata Kuliah : ");
                String inputMatKul = input.next();
                lT.cariTugasByMatKul(inputMatKul);
            }
            default -> System.out.println(" Pilihan Anda tidak valid.");
        }
    }

    public static void doTask(Scanner input, ListTask lT, PriorityTask lTP, CompletedTask cT) {
        System.out.println();
        System.out.println("==--- Mengerjakan Tugas -------==");
        System.out.println("1. Mengerjakan sesuai deadline");
        System.out.println("2. Mengerjakan yang lain");
        System.out.println("==----------------------------==");
        System.out.print(" Masukkan Pilihan (1/2) : ");
        int inputKerjakanTugas = input.nextInt();

        switch (inputKerjakanTugas) {
            case 1 -> {
                System.out.println(" Mengerjakan tugas yang dekat dengan deadline : ");
                System.out.println(" Tugas yang telah dikerjakan : ");

                Task tugasYgDikerjakan = lTP.dequeue();
                lT.removeTask(tugasYgDikerjakan.namaTugas);

                System.out.println(" Nama Tugas  : " + tugasYgDikerjakan.namaTugas);
                System.out.println(" Mata Kuliah : " + tugasYgDikerjakan.mataKuliah);

                cT.push(tugasYgDikerjakan);

                lTP.checkDeadline(tugasYgDikerjakan);
            }
            case 2 -> {
                System.out.print(" Masukkan Nama Tugas : ");
                String inputNamaTugas = input.next();

                lT.removeTask(inputNamaTugas);
                Task tugasYgDikerjakan =  lTP.removeByName(inputNamaTugas);
                System.out.println(" Nama Tugas  : " + tugasYgDikerjakan.namaTugas);
                System.out.println(" Mata Kuliah : " + tugasYgDikerjakan.mataKuliah);

                cT.push(tugasYgDikerjakan);

                lTP.checkDeadline(tugasYgDikerjakan);
            }
            default -> System.out.println(" Pilihan Anda tidak valid.");
        }
    }

    public static void printTask(Scanner input, ListTask lT, PriorityTask lTP, CompletedTask cT) {
        System.out.println();
        System.out.println("==--- Tampilkan Daftar Tugas -------==");
        System.out.println("1. Daftar Tugas yang Dimasukkan");
        System.out.println("2. Daftar Tugas Prioritas");
        System.out.println("3. Daftar Tugas Selesai");
        System.out.println("4. Tugas yang segera dikerjakan");
        System.out.println("5. Tugas yang terakhir dikerjakan");
        System.out.println("==----------------------------------==");
        System.out.print(" Masukkan Pilihan (1/2/3/4/5) : ");
        int inputTampilan = input.nextInt();

        switch (inputTampilan) {
            case 1 -> {
                System.out.println(" Berikut Daftar yang telah anda masukkan : ");
                lT.printListTugas();
            }
            case 2 -> {
                System.out.println(" Berikut Daftar Tugas Prioritas : ");
                System.out.println(" (Diurutkan berdasarkan deadline) ");
                lTP.printListTugas();
            }
            case 3 -> {
                System.out.println(" Berikut Daftar tugas yang telah diselesaikan : ");
                cT.printListTugas();
            }
            case 4 -> {
                System.out.println("Segera selesaikan Tugas berikut : ");
                Task deadlineTask = lTP.peek();
                System.out.println("Tugas : " + deadlineTask.namaTugas);
                System.out.println("Mata Kuliah : " + deadlineTask.mataKuliah);
                System.out.println("Deadline : " + deadlineTask.tglPengumpulan);
            }
            case 5 -> {
                System.out.println("Tugas terakhir yang telah dikerjakan : ");
                Task lastCompletedTask = cT.peek();
                System.out.println("Tugas : " + lastCompletedTask.namaTugas);
                System.out.println("Mata Kuliah : " + lastCompletedTask.mataKuliah);
            }
            default -> System.out.println(" Pilihan Anda tidak valid.");
        }
    }

}
