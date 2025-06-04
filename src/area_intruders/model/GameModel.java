package area_intruders.model;

import area_intruders.controller.GameController;

public class GameModel implements Runnable {
    private GameController gameController;
    private Thread gameThread;
    private boolean running = false;

    public GameModel(GameController gameController) {
        this.gameController = gameController;
        this.gameThread = new Thread(this);
    }

    public Thread getGameThread() {
        return gameThread;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000/60);//60FPS
                gameController.repaintGameplayPanel();
                gameController.moveEnemies();
                gameController.moveBullets();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void start() {
        gameThread.start();
        running = true;
    }
}

