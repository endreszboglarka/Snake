package view;

import utils.SnakeKeyListener;

import javax.swing.*;
import java.awt.*;

import static utils.Constants.DIMENSION;


public class Window extends JFrame {
    private GamePanel gamePanel;

    public Window() {
        setTitle("Snake");
        setSize(DIMENSION, DIMENSION);
        setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);
        gamePanel = new GamePanel(getGraphics());
        getContentPane().add(gamePanel, BorderLayout.NORTH);
        addKeyListener(new SnakeKeyListener(gamePanel.map));
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (g != null && this.gamePanel != null) {
            this.gamePanel.paint(g);
        }
    }
}
