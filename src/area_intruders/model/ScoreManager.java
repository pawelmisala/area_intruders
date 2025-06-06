package area_intruders.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager {
    private final String filePath = "src/area_intruders/scores.txt";
    private ArrayList<Player> scoresArrayList;

    protected ScoreManager() {
        scoresArrayList = new ArrayList<>();
        loadScores();
    }

    protected void addScore(Player player) {
        scoresArrayList.add(player);
        Collections.sort(scoresArrayList);
    }

    protected void saveScores() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
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
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                scoresArrayList.add(Player.readPlayerFromString(line));
            }
        } catch (IOException e) {
            System.err.println("Error occured while loading scores file");
        }
    }

}
