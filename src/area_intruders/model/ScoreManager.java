package area_intruders.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager {
    private final String filePath = "src/area_intruders/top10.txt";
    private ArrayList<Player> top10ArrayList;

    protected ScoreManager() {
        top10ArrayList = new ArrayList<>();
        loadScores();
    }

    protected void addScore(Player player) {
        top10ArrayList.add(player);
        Collections.sort(top10ArrayList);
        if (top10ArrayList.size() > 10) {
            top10ArrayList.remove(top10ArrayList.size()-1);
        }
        if (top10ArrayList.contains(player)) {
            System.out.println("GRACZ WBIL SIE DO TOP 10");
        }
    }

    protected void saveScores() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Player player : top10ArrayList) {
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
                top10ArrayList.add(Player.readPlayerFromString(line));
            }
        } catch (IOException e) {
            System.err.println("Error occured while loading scores file");
        }
    }

}
