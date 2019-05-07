package controller;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.Constants.DIMENSION;
import static utils.Constants.SPEED;

public class Map {
    public Snake snake;
    public List<List<Field>> fields;

    public Timer timer;

    public Map() {
        snake = new Snake(new Head(new Point(1, 1), Direction.DOWN),
                new Tail(new ArrayList<>(
                Arrays.asList(
                        new SnakePart(new Point(1,0))
                        )
                )
                )
        );
        fields = new ArrayList<>();
        timer = new Timer(SPEED, e -> snake.move());
    }
}