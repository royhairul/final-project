package com.finalProject;

public class Node {
    Task dataTugas;
    Node next;

    public Node(Task inputTugas) {
        dataTugas = inputTugas;
        next = null;
    }
}
