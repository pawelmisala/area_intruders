package area_intruders.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager {
    private final String filePath = "src/area_intruders/scores.txt";
    private ArrayList<Player> scoresArrayList;
    private static ArrayList<Player> top10scoresArrayList;

    protected ScoreManager() {
        scoresArrayList = new ArrayList<>();
        top10scoresArrayList = new ArrayList<>();
        loadScores();
        updateScores();
    }

    protected void addScore(Player player) {
        scoresArrayList.add(player);
        updateScores();
    }

    private void updateScores(){
        top10scoresArrayList.clear();
        Collections.sort(scoresArrayList);
        if (scoresArrayList.size() > 10) {
            for (int i = 0; i < 10; i++) {
                top10scoresArrayList.add(scoresArrayList.get(i));
            }
        }
        else top10scoresArrayList.addAll(scoresArrayList);
    }

    public boolean isInTop10(Player player) {
        return top10scoresArrayList.contains(player);
    }

    public int getTop10Placement(Player player) {
        if (isInTop10(player)) {
            return top10scoresArrayList.indexOf(player) + 1;
        }
        return 0;
    }

    protected void saveScores() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            clearFile(filePath);
            for (Player player : scoresArrayList) {
                writer.write(player.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error while writing scores to file");
        }
    }

    public void loadScores() {
        try {
            scoresArrayList.clear();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                scoresArrayList.add(Player.readPlayerFromString(line));
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error occured while loading scores file");
        }
    }

    private void clearFile(String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Player> getTop10scoresArrayList() {
        return top10scoresArrayList;
    }

}
