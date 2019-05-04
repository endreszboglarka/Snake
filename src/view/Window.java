package view;

import controller.Map;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;

public class Window extends JFrame {
    private int size = 50;
    private Map map;

    public Window() {
        map = new Map();
        setTitle("Snake");
        setSize(500, 500);
        setLocation(0, 0);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        map.timer.start();
        map.timer.addActionListener(e -> paint(getGraphics()));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(map.snake.getHead().position.x * size, map.snake.getHead().position.y * size, size, size);
        IntStream
                .range(0, map.snake.getTail().body.size())
                .forEach(i -> g.fillRect(
                        map.snake.getTail().body.get(i).position.x * size,
                        map.snake.getTail().body.get(i).position.y * size,
                        size,
                        size));
        if (map.snake.isDead(map.fields)) {
            System.out.println("meghalt volna :( ");
        }
    }


}
