package model;

import java.awt.*;

public class Snake {
    private Head head;
    private Tail tail;

    public Snake(Head head, Tail tail) {
        this.head = head;
        this.tail = tail;
    }

    public void move() {
        tail.move(head);
        head.move();
    }

    public void grow() {
        tail.grow(head, new Apple(new Point(10,10)));
    }

    public Tail getTail() {
        return tail;
    }

    public void setTail(Tail tail) {
        this.tail = tail;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }
}
