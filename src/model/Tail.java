package model;

import java.util.List;

public class Tail {
    public List<SnakePart> body;

    public Tail(List<SnakePart> body) {
        this.body = body;
    }

    public void move(Head head) {
        body.get(body.size()-1).position = head.position;
        System.out.println(body.get(body.size()-1).position);
    }

    public void grow(Head head, Apple apple) {
        body.add(new SnakePart(head.position));
        head.position = apple.position;
    }
}
