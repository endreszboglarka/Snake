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
        snake = new Snake(new Head(new Point(3,3), Direction.RIGHT), new Tail(new ArrayList<SnakePart>(
                Arrays.asList(new SnakePart(new Point(2, 3)))
        )));
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snake.move();
            }
        });
    }
}