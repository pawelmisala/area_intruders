package area_intruders.view;

import area_intruders.model.Player;
import area_intruders.model.ScoreManager;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Top10ScoresFrame extends JFrame {
    JPanel mainPanel;
    JTable scoresTable;
    JScrollPane scoresScrollPane;

    public Top10ScoresFrame() {
        super("AREA INTRUDERS | TOP 10 SCORES");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(this.mainPanel = new JPanel());
            this.mainPanel.setLayout(new BorderLayout());
            this.scoresTable = new JTable(new Top10ScoresTableModel());
            this.scoresTable.setFillsViewportHeight(true);
            this.scoresScrollPane = new JScrollPane(this.scoresTable);
        this.mainPanel.add(this.scoresScrollPane, BorderLayout.CENTER);
    }

    private static class Top10ScoresTableModel extends AbstractTableModel {
        private final ArrayList<Player> top10ScoresArrayList;

        public Top10ScoresTableModel() {
            this.top10ScoresArrayList = ScoreManager.getTop10scoresArrayList();
        }

        @Override
        public int getRowCount() {
            return top10ScoresArrayList.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Player player = top10ScoresArrayList.get(rowIndex);
            switch (columnIndex) {
                case 0: return rowIndex + 1;
                case 1: return player.getNickname();
                case 2: return player.getScore();
                case 3: return player.getDifficulty().toString();
                default: return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "PLACE";
                case 1: return "NICKNAME";
                case 2: return "SCORE";
                case 3: return "DIFFICULTY";
                default: return null;
            }
        }
    }
}
