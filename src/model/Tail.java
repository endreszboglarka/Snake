package model;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Tail {
    public List<SnakePart> body;

    public Tail(List<SnakePart> body) {
        this.body = body;
    }

    public void move(Head head) {
        body.get(body.size() - 1).position.x = head.position.x;
        body.get(body.size() - 1).position.y = head.position.y;
        body.add(0, new SnakePart(
                        new Point(
                                body.get(body.size() - 1).position.x,
                                body.get(body.size() - 1).position.y)
                )
        );
        body.remove(body.get(body.size() - 1));
    }

    public void grow(Head head, Apple apple) {
        body.add(new SnakePart(head.position));
        head.position = apple.position;
    }
}
