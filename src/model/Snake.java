package model;

import java.awt.*;
import java.util.List;

import static utils.Constants.DIMENSION;
import static utils.Constants.FIELDWIDTH;

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
        tail.grow(head, new Apple(new Point(10, 10)));
    }

    private boolean isFieldABlockOrSnakePart(Field field) {
        return field instanceof Block || field instanceof SnakePart;
    }


    public boolean isDead(List<List<Field>> fields) {
        Field nextStep = fields.get(head.nextPosition().x).get(head.nextPosition().y);
        if (isFieldABlockOrSnakePart(nextStep)) {
            return true;
        }
        if (head.position.x < 0 || head.position.y < 0) {
            return true;
        }
        return head.position.x > DIMENSION / FIELDWIDTH || head.position.y > DIMENSION / FIELDWIDTH;
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
