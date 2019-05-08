package view;

import utils.SnakeKeyListener;

import javax.swing.*;
import java.awt.*;

import static utils.Constants.DIMENSION;


public class Window extends JFrame {
    private GamePanel gamePanel;
    private GameMenu gameMenu;

    public Window() {
        setTitle("Snake");
        setSize(DIMENSION, DIMENSION);
        setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);
        gameMenu = new GameMenu("Options");
        setJMenuBar(gameMenu);
        gamePanel = new GamePanel(getGraphics());
        add(gamePanel);
        addKeyListener(new SnakeKeyListener(gamePanel.map));
        pack();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (g != null && this.gamePanel != null) {
            this.gamePanel.paint(g);
        }
    }
}
