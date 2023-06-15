package com.finalProject;

// Menerapkan Konsep Queue
// Untuk melakukan penyimpana secara berurutan
// Pengurutan berdasarkan jadwal pengumpulan / deadline

import java.time.LocalDate;

public class PriorityTask {
    private Node front;
    private Node rear;

    public PriorityTask() {
        this.front = null;
        this.rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Task newTask) {
        Node newNode = new Node(newTask);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;

        insertionSortByDate();
    }

    public void insertionSortByDate() {
        if (front == null || front.next == null) {
            return; // Jika list kosong / hanya 1 tugas, tidak melakukan pengurutan
        }

        Node sorted = null; // Inisialisasi linked list sorted sebagai null

        Node current = front;
        while (current != null) {
            Node next = current.next;

            if (sorted == null ||
                    current.dataTugas.tglPengumpulan.compareTo(sorted.dataTugas.tglPengumpulan) < 0) {
                // Insert di depan (linked list sorted masih kosong atau tanggal lebih awal)
                current.next = sorted;
                sorted = current;
            } else {
                // Mencari posisi insert
                Node search = sorted;
                while (search.next != null &&
                        current.dataTugas.tglPengumpulan.compareTo(search.next.dataTugas.tglPengumpulan) >= 0) {
                    search = search.next;
                }

                // insert di tengah atau di belakang
                current.next = search.next;
                search.next = current;
            }

            current = next;
        }

        // Perbarui front setelah sorting
        front = sorted;
    }

    public Task dequeue() {
        if (isEmpty()) {
            System.out.println(" List Tugas Prioritas masih Kosong...");
            return null;
        }

        else {
            Task deleteTugas = front.dataTugas;
            front = front.next;

            if (front == null) {
                rear = null;
            }
            return deleteTugas;
        }
    }

    public void checkDeadline(Task tugas) {
        String tglHariIni = LocalDate.now().toString();
        if(tugas.tglPengumpulan.compareTo(tglHariIni) > 1) {
            System.out.println(" - Anda Mengumpulkan Tugas Tepat Waktu");
        } else {
            System.out.println(" - Anda Mengumpulkan Tugas Telambat");
        }
    }

    public Task removeByName(String inputNama) {
        if (isEmpty()) {
            return null;
        }

        else {
            Node currNode = front;
            Node delNode = null;

            while(currNode != null) {
                if(currNode.next.dataTugas.namaTugas.equalsIgnoreCase(inputNama)) {
                    delNode = currNode.next;
                    currNode.next = delNode.next;
                }

                currNode = currNode.next;
            }

            assert delNode != null;
            return delNode.dataTugas;
        }
    }

    public Task peek() {
        if (isEmpty()) {
            System.out.println("List Tugas Prioritas masih Kosong...");
        }
        return front.dataTugas;
    }

    public void printListTugas() {
        if(isEmpty()) {
            System.out.println(" !!! List Tugas Prioritas masih kosong");
        }
        else {
            Node currNode = front;

            while(currNode != null) {
                System.out.print(" ( " + currNode.dataTugas.tglPengumpulan + " ) ");
                System.out.println(" : " + currNode.dataTugas.namaTugas);

                currNode = currNode.next;
            }
        }
    }
}
