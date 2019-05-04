package view;

import controller.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public class Window extends JFrame {

    private Map map;

    public Window(){
        map = new Map();
        setTitle("Snake");
        setSize(800, 800);
        setLocation(0,0);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        map.timer.start();
        map.timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint(getGraphics());
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(map.snake.getHead().position.x*100, map.snake.getHead().position.y*100,100,100);
        IntStream.range(0, map.snake.getTail().body.size()).forEach(i -> g.fillRect(map.snake.getTail().body.get(i).position.x * 100, map.snake.getTail().body.get(i).position.y * 100, 100, 100));
    }


}
