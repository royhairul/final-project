package com.finalProject;

// Menerapkan Konsep Stack
// Untuk melakukan penyimpanan tugas yang telah diselesaikan

public class CompletedTask {
    private Node top;

    public CompletedTask() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Task newTask) {
        Node newNode = new Node(newTask);
        newNode.next = top;
        top = newNode;
    }

    public Task pop() {
        if (isEmpty()) {
            System.out.println("Anda belum menyelesaikan tugas.");
        }
        Task deleteTask = top.dataTugas;
        top = top.next;
        return deleteTask;
    }

    public Task peek() {
        if (isEmpty()) {
            System.out.println("Anda belum menyelesaikan tugas.");
        }
        return top.dataTugas;
    }

    public void cariTugasByNama(String namaTugas) {
        Node currNode = top;
        boolean isFound = false;

        while(currNode != null) {
            if(currNode.dataTugas.namaTugas.equalsIgnoreCase(namaTugas)) {
                System.out.println(" Tugas Ditemukan : ");
                System.out.println(" - Nama Tugas  : " + currNode.dataTugas.namaTugas);
                System.out.println(" - Mata Kuliah : " + currNode.dataTugas.mataKuliah);
                System.out.println(" - Deadline    : " + currNode.dataTugas.tglPengumpulan);
                System.out.println(" - Status      : Selesai ");
                isFound = true;
            }
            currNode = currNode.next;
        }

        if(!isFound) {
            System.out.println(" -- Tugas Tidak Ditemukan -- ");
        }
    }

    public void cariTugasByMatKul(String mataKuliah) {
        Node currNode = top;

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


    public void printListTugas() {
        if(isEmpty()) {
            System.out.println("Daftar Tugas masih kosong!");
        }
        else {
            Node currNode = top;

            while(currNode != null) {
                System.out.println(" >> Nama Tugas  : " + currNode.dataTugas.namaTugas);
                System.out.println("    Mata Kuliah : " + currNode.dataTugas.mataKuliah);

                currNode = currNode.next;
            }
        }
    }
}
