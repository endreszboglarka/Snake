package view;

import controller.Map;
import model.Field;
import persistence.Database;
import view.HighScoreWindow;
import persistence.Database;
import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*;

import static utils.Constants.DIMENSION;
import static utils.Constants.FIELDWIDTH;

public class GamePanel extends JPanel {
    Map map;

    GamePanel(Graphics g) {
        map = new Map();
        setPreferredSize(new Dimension(DIMENSION, DIMENSION));
        map.timer.addActionListener(e -> paint(g));
        setBackground(Color.black);
        map.timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, DIMENSION, DIMENSION);
        drawBlocks(g);
        if (map.snake.isOnApple(map.apple)) {
            map.apple = map.generateApple();
            drawSnake(g);
        } else if (map.snake.isDead(map.fields)) {
            drawSnake(g);
            map.timer.stop();
            int score = map.snake.getTail().body.size() - 1;
            JOptionPane.showMessageDialog(this,
                    "Your score is ".concat(Integer.toString(score).concat(".")));
            Database database = new Database(score);
            HighScoreWindow hsw = new HighScoreWindow(database.getHighScores());
        } else {
            drawSnake(g);
        }
    }

    private void drawSnake(Graphics g) {
        drawField(g, map.snake.getHead(), Color.green);
        map.snake.getTail().body.forEach(i -> drawField(g, i, Color.green));
    }

    private void drawBlocks(Graphics g) {
        map.fields.forEach(i -> drawField(g, i, Color.gray));
        drawField(g, map.apple, Color.red);
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
