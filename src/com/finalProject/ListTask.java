package com.finalProject;

// Menerapkan Konsep Linked List
// Untuk melakukan penyimpanan tugas yang dimasukkan oleh user

public class ListTask {

    private Node head;

    public ListTask() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addTask(Task tugasBaru) {
        Node newNode = new Node(tugasBaru);

        if(isEmpty()) {
            head = newNode;
            head.next = null;
        }

        else {
            Node currNode = head;
            while(currNode.next != null) {
                currNode = currNode.next;
            }
            currNode.next = newNode;
        }

        System.out.println(" (+) Menambahkan Tugas Baru : " + tugasBaru.namaTugas);
    }

    public void removeTask(String namaTugas) {
        Node currNode, delNode;
        if(isEmpty()) {
            System.out.println(" Daftar Tugas masih kosong");
        }

        else {

            if(head.next != null) {
                currNode = head;
                while (!currNode.dataTugas.namaTugas.equalsIgnoreCase(namaTugas)) {
                    currNode = currNode.next;
                }
                delNode = currNode.next;
                currNode.next = delNode.next;
                delNode = null;

            }
            else {
                head = null;
            }

        }
    }

    public void cariTugasByNama(String namaTugas) {
        Node currNode = head;
        boolean isFound = false;

        while(currNode != null) {
            if(currNode.dataTugas.namaTugas.equalsIgnoreCase(namaTugas)) {
                System.out.println(" Tugas Ditemukan : ");
                System.out.println(" - Nama Tugas  : " + currNode.dataTugas.namaTugas);
                System.out.println(" - Mata Kuliah : " + currNode.dataTugas.mataKuliah);
                System.out.println(" - Deadline    : " + currNode.dataTugas.tglPengumpulan);
                System.out.println(" - Status      : Belum Selesai ");
                isFound = true;
            }
            currNode = currNode.next;
        }

        if(!isFound) {
            System.out.println(" -- Tugas Tidak Ditemukan -- ");
        }
    }



    public Task cariObjTugas(String namaTugas) {
        Node currNode = head;

        while(currNode != null) {
            if(currNode.dataTugas.namaTugas.equalsIgnoreCase(namaTugas)) {
                break;
            }
            currNode = currNode.next;
        }
        return currNode.dataTugas;
    }

    public void cariTugasByMatKul(String mataKuliah) {
        Node currNode = head;

        System.out.println(" Tugas Mata Kuliah : " + mataKuliah);
        boolean isFound = false;

        while(currNode != null) {
            if(currNode.dataTugas.mataKuliah.equalsIgnoreCase(mataKuliah)) {
                System.out.println(" - Nama Tugas  : " + currNode.dataTugas.namaTugas);
                System.out.println(" - Deadline    : " + currNode.dataTugas.tglPengumpulan);
                isFound = true;
            }
            currNode = currNode.next;
        }

        if(!isFound) {
            System.out.println(" -- Tidak Ditemukan -- ");
        }
    }

    public int getJumlahTugas() {
        int jumlah = 0;
        Node nodeSekarang = head;
        while(nodeSekarang != null) {
            jumlah++;
            nodeSekarang = nodeSekarang.next;
        }
        return jumlah;
    }

    public void printListTugas() {
        if(isEmpty()) {
            System.out.println(" Daftar Tugas masih kosong!");
        }
        else {
            Node currNode = head;
            int i = 1;

            while(currNode != null) {
                System.out.println(" Tugas ke - " + (i));
                System.out.println(" - Nama Tugas  : " + currNode.dataTugas.namaTugas);
                System.out.println(" - Mata Kuliah : " + currNode.dataTugas.mataKuliah);
                System.out.println(" - Deadline    : " + currNode.dataTugas.tglPengumpulan + "\n");

                i++;
                currNode = currNode.next;
            }
        }
    }
}
