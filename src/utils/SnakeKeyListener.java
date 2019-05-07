package utils;

import controller.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.KEYDIRECTIONBINDING;

public class SnakeKeyListener implements KeyListener {
    private Map map;

    public SnakeKeyListener(Map map) {
        this.map = map;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KEYDIRECTIONBINDING.containsKey(e.getKeyChar())) {
            map.snake.getHead().changeDirection(KEYDIRECTIONBINDING.get(e.getKeyChar()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
