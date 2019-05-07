package view;

import controller.Map;
import utils.SnakeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.stream.IntStream;

import static utils.Constants.*;


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
        drawSnake(g);
        if (map.snake.isDead(map.fields)) {
            map.timer.stop();
            JOptionPane.showMessageDialog(this,
                    "meghalt volna :( ");
        }
    }

    private void drawSnake(Graphics g) {
        g.fillRect(map.snake.getHead().position.x * FIELDWIDTH, map.snake.getHead().position.y * FIELDWIDTH, FIELDWIDTH, FIELDWIDTH);
        IntStream
                .range(0, map.snake.getTail().body.size())
                .forEach(i -> g.fillRect(
                        map.snake.getTail().body.get(i).position.x * FIELDWIDTH,
                        map.snake.getTail().body.get(i).position.y * FIELDWIDTH,
                        FIELDWIDTH,
                        FIELDWIDTH));
    }

}
