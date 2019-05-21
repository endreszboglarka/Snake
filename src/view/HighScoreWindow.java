package view;

import persistence.HighScore;
import persistence.HmData;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;

public class HighScoreWindow extends JDialog {
    private final JTable table;

    //public HighScoreWindow(ArrayList<HmData> highScore, JFrame parent) {
    public HighScoreWindow(ArrayList<HmData> highScore) {
        super();
        table = new JTable(new HighScoreTableModel(highScore));
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table));
        setSize(400, 400);
        setTitle("Score table");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
