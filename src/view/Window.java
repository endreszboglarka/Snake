package view;

import persistence.Database;
import utils.SnakeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static utils.Constants.DIMENSION;


public class Window extends JFrame {
    private GamePanel gamePanel;

    public Window() {
        setProperties();

        paintPanel();
        makeMenu();
        pack();

    }

    public void setProperties() {
        setTitle("Snake");
        //setSize(DIMENSION, DIMENSION);
        //setSize(new Dimension(DIMENSION, DIMENSION));
        //setPreferredSize(new Dimension(DIMENSION, DIMENSION));
        //setMinimumSize(new Dimension(DIMENSION, DIMENSION));
        //setLayout(new BorderLayout(10,10));
        //JButton jb=new JButton("alma");
        //jb.setSize(10,10);
        //add(jb, BorderLayout.NORTH);
        setResizable(true);
        setVisible(true);
        setLayout(new BorderLayout());
        //setLayout(new GridLayout(400,400));
        //gameMenu = new GameMenu("Options");
        //setJMenuBar(gameMenu);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paintPanel() {

        gamePanel = new GamePanel();
        add(gamePanel,BorderLayout.NORTH);

        //getContentPane().add(gamePanel, BorderLayout.SOUTH);
        addKeyListener(new SnakeKeyListener(gamePanel.map));
        //pack();
    }

    public void makeMenu() {
        System.out.println("Menubart hozok letre");
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");

        JMenuItem menuHighScores = new JMenuItem(new AbstractAction("Score Table") {
            @Override
            public void actionPerformed(ActionEvent e) {

                Database db = new Database();
                HighScoreWindow hsw = new HighScoreWindow(db.getHighScores());
            }
        });

        JMenuItem menuNewGame = new JMenuItem(new AbstractAction("New Game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(gamePanel);
                paintPanel();
            }
        });

        menuGame.add(menuHighScores);
        menuGame.add(menuNewGame);
        menuBar.add(menuGame);
        setJMenuBar(menuBar);

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (g != null && this.gamePanel != null) {
            //this.gamePanel.paint(g);
        }
    }

    public String nameInput() {
        return JOptionPane.showInputDialog("What's your name?");
    }

}
