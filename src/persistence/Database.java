package persistence;

import model.GameID;
import model.Snake;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
    private final String tableName = "highscores";
    private Connection conn;
    private HashMap<GameID, Integer> highScores;
    private ArrayList<HmData> hms;

    public Database(){
        databaseInit();
        loadHighScores2();
    }

    public Database(int score){
        databaseInit();
        storeToDatabase2(JOptionPane.showInputDialog("What's your name???"),score);
        loadHighScores2();
    }

    public void databaseInit() {
        Connection c = null;
        try {
            c = ConnectionFactory.getConnection();
        } catch (Exception ex) { System.out.println("No connection");}
        this.conn = c;
        highScores = new HashMap<>();
        hms = new ArrayList<HmData>();
        //loadHighScores();
        //loadHighScores2();
    }

    public boolean storeHighScore(GameID id, int newScore){
        return mergeHighScores(id, newScore, newScore > 0);
    }

    public ArrayList<HmData> getHighScores(){
        return hms;
    }

    private void loadHighScores(){
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " ORDER BY score;");
            while (rs.next()){
                //String diff = rs.getString("Difficulty");
                //int level = rs.getInt("GameLevel");
                //int steps = rs.getInt("Steps");
                //GameID id = new GameID(diff, level);
                //mergeHighScores(id, steps, false);
            }
        } catch (Exception e){ System.out.println("loadHighScores error");}
    }

    private void loadHighScores2(){
        System.out.println("Adatbazisbol probalok kiolvasni");
        try (Statement stmt = conn.createStatement()) {
            System.out.println("most megadom az SQLt");
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " ORDER BY score desc limit 10;");
            while (rs.next()){
                System.out.println("Egy sort kiolvasok valtozokba");
                int id2 = rs.getInt("id");
                String name = rs.getString("name");
                int score = rs.getInt("score");
                System.out.println(id2 + name + score);
                hms.add(new HmData(name, score, id2));
            }
            System.out.println("hashmappembol probalok kiolvasni");
            for (HmData h : hms) {
                System.out.println("egy sor a hashmapbol");
                System.out.println(h.name + " " + h.score + " " + h.id);
                System.out.println("Adatbazisbol probalok kiolvasni");
            }
        } catch (Exception e){ System.out.println("loadHighScores error");}
    }

    private boolean mergeHighScores(GameID id, int score, boolean store){
        System.out.println("Merge: " + id.name + "-" + id.score + ":" + score + "(" + store + ")");
        boolean doUpdate = true;
        if (highScores.containsKey(id)){
            int oldScore = highScores.get(id);
            doUpdate = ((score < oldScore && score != 0) || oldScore == 0);
        }
        if (doUpdate){
            highScores.remove(id);
            highScores.put(id, score);
            if (store) return storeToDatabase(id, score) > 0;
            return true;
        }
        return false;
    }

    private int storeToDatabase(GameID id, int score){
        try (Statement stmt = conn.createStatement()){
            String s = "INSERT INTO " + tableName +
                    " (Difficulty, GameLevel, Steps) " +
                    "VALUES('" + id.name + "'," + id.score +
                    "," + score +
                    ") ON DUPLICATE KEY UPDATE Steps=" + score;
            return stmt.executeUpdate(s);
        } catch (Exception e){
            System.out.println("storeToDatabase error");
        }
        return 0;
    }

    public int storeToDatabase2(String name,int score){
        try (Statement stmt = conn.createStatement()){
            String s = "INSERT INTO " + tableName +
                    " (Name, Score) " +
                    "VALUES('" + name + "'," + score +
                    ");";
            return stmt.executeUpdate(s);
        } catch (Exception e){
            System.out.println("storeToDatabase error");
        }
        return 0;
    }

}
