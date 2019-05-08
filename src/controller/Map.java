package controller;

import model.*;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static utils.Constants.FIELDCOUNT;
import static utils.Constants.SPEED;

public class Map {
    private final Random random = new Random();
    public Snake snake;
    public List<Field> fields;

    public Timer timer;

    public Map() {
        snake = new Snake(new Head(new Point(0, FIELDCOUNT / 2), Direction.DOWN),
                new Tail(new ArrayList<>(
                Arrays.asList(
                        new SnakePart(new Point(0, FIELDCOUNT / 2 - 1))
                        )
                )
                )
        );
        fields = new ArrayList<>();
        generateBlocks(10);
        timer = new Timer(SPEED, e -> snake.move());
    }

    private void generateBlocks(int amount) {
        IntStream.range(0, amount).forEach(it -> generateBlock());
    }

    private int randomPos() {
        return random.nextInt(Constants.FIELDCOUNT);
    }

    private void generateBlock() {
        int xCoord, yCoord;
        do {
            xCoord = randomPos();
            yCoord = randomPos();
        } while (pointIsTaken(new Point(xCoord, yCoord)));
        fields.add(new Block(new Point(xCoord, yCoord)));
    }

    private boolean pointIsTaken(Point point) {
        return snakeContainsPoint(point) || fieldsContainPoint(point);
    }

    private boolean snakeContainsPoint(Point point) {
        return snake.containsPoint(point);
    }

    private boolean fieldsContainPoint(Point point) {
        for (Field field : fields) {
            if (field.position.equals(point)) {
                return true;
            }
        }
        return false;
    }

}