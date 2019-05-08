package view;

import controller.Map;
import model.Field;
import utils.SnakeKeyListener;

import javax.swing.*;
import java.awt.*;

import static utils.Constants.DIMENSION;
import static utils.Constants.FIELDWIDTH;


public class Window extends JFrame {
    private Map map;

    public Window() {
        map = new Map();
        setTitle("Snake");
        setSize(DIMENSION, DIMENSION);
        setLocation(0, 0);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        map.timer.start();
        map.timer.addActionListener(e -> paint(getGraphics()));
        addKeyListener(new SnakeKeyListener(map));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBlocks(g);
        if (map.snake.isDead(map.fields)) {
            drawSnake(g);
            map.timer.stop();
            int score = map.snake.getTail().body.size() + 1;
            JOptionPane.showMessageDialog(this,
                    "Your score is ".concat(Integer.toString(score).concat(".")));
        } else {
            drawSnake(g);
        }
    }

    private void drawSnake(Graphics g) {
        drawField(g, map.snake.getHead(), Color.blue);
        map.snake.getTail().body.forEach(i -> drawField(g, i, Color.blue));
    }

    private void drawBlocks(Graphics g) {
        map.fields.forEach(i -> drawField(g, i, Color.black));
    }

    private void drawField(Graphics g, Field field, Color color) {
        g.setColor(color);
        g.fillRect(
                field.position.x * FIELDWIDTH,
                field.position.y * FIELDWIDTH,
                FIELDWIDTH,
                FIELDWIDTH);
    }

}
