package area_intruders.model;

import area_intruders.controller.GameController;

public class GameModel implements Runnable {
    private GameController gameController;
    private Thread gameThread;
    private boolean running = false;
    private boolean paused = false;
    private int score;

    public GameModel(GameController gameController) {
        this.gameController = gameController;
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
                    gameController.gameOver();
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
        score = 0;
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

