package model;

import java.awt.*;
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

    private void grow() {
        tail.grow(head);
    }

    public boolean isOnApple(Apple apple) {
        if (this.head.position.equals(apple.position)) {
            this.grow();
            return true;
        }
        return false;
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

    public boolean containsPoint(Point point) {
        if (head.position.equals(point)) {
            return true;
        } else {
            for (SnakePart snakePart : tail.body) {
                if (snakePart.position.equals(point)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean headIsOnTail() {
        if (tail.body != null && tail.body.size() > 0) {
            List<Field> elementsOnHead = tail.body.stream()
                    .filter(field -> field.position.equals(head.position))
                    .collect(Collectors.toList());
            if(elementsOnHead.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isDead(List<Field> fields) {
        return headIsOutOfMap() || headIsOnTail() || isOnBlock(fields);
    }

    private boolean isOnBlock(List<Field> fields) {
        for (Field field : fields) {
            if (field.position.equals(head.position)) {
                return true;
            }
        }
        return false;
    }
    public Tail getTail() {
        return tail;
    }

    public Head getHead() {
        return head;
    }

}
