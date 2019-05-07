package model;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    private boolean headIsBelowMinimalDimensions() {
        return head.position.x < 0 || head.position.y < 0;
    }

    private boolean headIsBeyondMaximalDimensions() {
        return head.position.x > DIMENSION / FIELDWIDTH || head.position.y > DIMENSION / FIELDWIDTH;
    }

    private boolean headIsOutOfMap() {
        return headIsBelowMinimalDimensions() || headIsBeyondMaximalDimensions();
    }

    private boolean headIsOnObstacle(List<Field> fields) {
        if (fields != null && fields.size() > 0) {
            Field nextStep = fields.stream().filter(field -> field.position.equals(head.position)).collect(Collectors.toList()).get(0);
            return isFieldABlockOrSnakePart(nextStep);
        }
        return false;
    }

    private boolean headIsOnTail() {
        if (tail.body != null && tail.body.size() > 0) {
            List<Field> elementsOnHead = tail.body.stream().filter(field -> field.position.equals(head.position)).collect(Collectors.toList());
            if(elementsOnHead.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isDead(List<Field> fields) {
        return headIsOutOfMap() || headIsOnObstacle(fields) || headIsOnTail();
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
