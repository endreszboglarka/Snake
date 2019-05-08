package view;

import controller.Map;
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
        g.setColor(Color.blue);
        g.fillRect(map.snake.getHead().position.x * FIELDWIDTH, map.snake.getHead().position.y * FIELDWIDTH, FIELDWIDTH, FIELDWIDTH);
        map.snake.getTail().body
                .forEach(i -> g.fillRect(
                        i.position.x * FIELDWIDTH,
                        i.position.y * FIELDWIDTH,
                        FIELDWIDTH,
                        FIELDWIDTH));
    }

    private void drawBlocks(Graphics g) {
        g.setColor(Color.black);
        map.fields
                .forEach(i -> g.fillRect(
                        i.position.x * FIELDWIDTH,
                        i.position.y * FIELDWIDTH,
                        FIELDWIDTH,
                        FIELDWIDTH));
    }
}
