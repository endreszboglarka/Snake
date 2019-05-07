package view;

import controller.Map;
import model.Direction;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.stream.IntStream;

import static utils.Constants.DIMENSION;
import static utils.Constants.FIELDWIDTH;

public class Window extends JFrame {
    private Map map;

    private HashMap<Character, Direction> keyDirectionBinding = new HashMap<Character, Direction>() {{
        put('w', Direction.UP);
        put('s', Direction.DOWN);
        put('a', Direction.LEFT);
        put('d', Direction.RIGHT);
    }};

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
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (keyDirectionBinding.containsKey(e.getKeyChar())) {
                    map.snake.getHead().changeDirection(keyDirectionBinding.get(e.getKeyChar()));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (map.snake.isDead(map.fields)) {
            map.timer.stop();
            JOptionPane.showMessageDialog(this,
                    "meghalt volna :( ");
        }
        drawSnake(g);
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
