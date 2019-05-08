package view;

import javax.swing.*;

public class GameMenu extends JMenuBar {

    private JMenuItem newGame;

    public GameMenu(String s) {
        setName(s);
        newGame = new JMenuItem("nem");
        add(newGame);
    }
}
