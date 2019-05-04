package model;

import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public boolean isDead(List<List<Field>> fields) {

        AtomicBoolean isDeadByObject = new AtomicBoolean(false);
        for (List<Field> row : fields) {
            for (Field field : row) {
                if (field instanceof SnakePart || field instanceof Block) {
                    if (
                            field.position.x == head.position.x &&
                                    field.position.y == head.position.y
                    ) {
                        isDeadByObject.set(true);
                        return isDeadByObject.get();
                    }
                }
            }
        }
        if (head.position.x < 0 || head.position.y < 0) {
            return true;
        }
        return head.position.x > 9 || head.position.y > 9;
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
