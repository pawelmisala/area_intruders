package area_intruders.model;

import area_intruders.controller.GameController;

public class GameModel implements Runnable {
    private GameController gameController;
    private Thread gameThread;
    private Player player;
    private ScoreManager scoreManager;
    private boolean running = false;
    private boolean paused = false;
    private int score;

    public GameModel(GameController gameController, Player player) {
        this.gameController = gameController;
        this.player = player;
        this.scoreManager = new ScoreManager();
        this.score = 0;
    }

    @Override
    public void run() {
        while (running) {
            synchronized (this) {
                while (paused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            try {
                Thread.sleep(1000/60);//60FPS
                gameController.repaintGameplayPanel();
                gameController.moveEnemies();
                gameController.moveBullets();
                if (gameController.checkBulletAndEnemyColision()){
                    gameController.updateScoreLabel(++score);
                }
                if (gameController.checkShipAndEnemyColision()){
                    this.stop();
                    player.setScore(this.score);
                    scoreManager.addScore(player);
                    scoreManager.saveScores();
                    gameController.gameOver(scoreManager.isInTop10(player), scoreManager.getTop10Placement(player));
                    this.score = 0;
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void start() {
        gameThread = new Thread(this);
        paused = false;
        running = true;
        player.setScore(0);
        gameThread.start();
    }
    public synchronized void pause(){
        paused = true;
    }
    public synchronized void resume(){
        paused = false;
        notify();
    }
    public void stop() {
        paused = false;
        running = false;
        gameThread.interrupt();
    }

    public Thread getGameThread() {
        return gameThread;
    }
    public boolean isPaused() {
        return paused;
    }
}

