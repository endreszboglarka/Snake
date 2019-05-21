package view;

import persistence.HighScore;
import persistence.HmData;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HighScoreTableModel extends AbstractTableModel {
    private final ArrayList<HmData> highScores;

    private final String[] colName = new String[] {
            "name", "score", "id"
    };

    public HighScoreTableModel(ArrayList<HmData> highScores) {
        this.highScores = highScores;
    }

    @Override
    public int getRowCount() {
        return highScores.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int i) {
        return colName[i];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        HmData hm = highScores.get(rowIndex);
        Object o = null;

        switch (columnIndex) {
            case 0: o=hm.name; break;
            case 1: o=hm.score; break;
            case 2: o=hm.id; break;
        }

        return o;

    }
}
