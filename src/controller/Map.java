package controller;

import model.*;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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
        fields.add(new Block(new Point(randomPos(), randomPos())));
        timer = new Timer(SPEED, e -> snake.move());
    }

    private void generateBlocks(int amount) {
        IntStream.range(0, amount).parallel().forEach(it -> generateBlock());
    }

    private int randomPos() {
        return random.nextInt(Constants.FIELDCOUNT);
    }

    private void generateBlock() {
        int xCoord, yCoord;
        do {
            xCoord = randomPos();
            yCoord = randomPos();
        } while (fieldIsNotTaken(xCoord, yCoord));
        fields.add(new Block(new Point(xCoord, yCoord)));
    }

    private boolean fieldIsNotTaken(int xCoord, int yCoord) {
        Point positionToCompare = new Point(xCoord, yCoord);
        List<Field> previousField = fields.parallelStream().filter(field -> field.position.equals(positionToCompare)
        ).collect(Collectors.toList());
        return previousField instanceof Block || previousField instanceof SnakePart || previousField instanceof Head;
    }

}