package controller;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
    public Snake snake;
    private List<List<Field>> fields;

    public Timer timer;

    public Map() {
        snake = new Snake(new Head(new Point(3,3), Direction.DOWN),
                new Tail(new ArrayList<>(
                Arrays.asList(
                        new SnakePart(new Point(2, 3)),
                        new SnakePart(new Point(1,3)),
                        new SnakePart(new Point(0,3))
                        )
                )
        ));

        timer = new Timer(1000, e -> snake.move());
    }
}