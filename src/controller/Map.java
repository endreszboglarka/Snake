package controller;

import model.*;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static utils.Constants.FIELDCOUNT;
import static utils.Constants.SPEED;

public class Map {
    private final Random random = new Random();
    public Snake snake;
    public List<Field> fields;
    public Apple apple;

    public Timer timer;

    public Map() {
        Random randDir = new Random();
        int rand = randDir.nextInt(5);
        Direction dir;
        switch (rand) {
            case 1: dir = Direction.UP; break;
            case 2: dir = Direction.DOWN; break;
            case 3: dir = Direction.LEFT; break;
            case 4: dir = Direction.RIGHT; break;
            default: dir = Direction.RIGHT;
        }

        snake = new Snake(new Head(new Point(FIELDCOUNT / 2, FIELDCOUNT / 2), dir),
                new Tail(new ArrayList<>(
                        Collections.singletonList(
                                new SnakePart(new Point(FIELDCOUNT / 2, FIELDCOUNT / 2 - 1))
                        )
                )
                )
        );
        fields = new ArrayList<>();
        generateBlocks();
        apple = generateApple();
        timer = new Timer(SPEED, e -> snake.move());
    }

    private void generateBlocks() {
        IntStream.range(0, Constants.BLOCKCOUNT).forEach(it -> generateBlock());
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

    public Apple generateApple() {
        int xCoord, yCoord;
        do {
            xCoord = randomPos();
            yCoord = randomPos();
        } while (pointIsTaken(new Point(xCoord, yCoord)));
        return new Apple(new Point(xCoord, yCoord));
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