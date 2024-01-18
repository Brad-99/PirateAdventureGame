package main;


public class Game implements Runnable {

    private GameWindow gameWindow; // Creat an object gameWindow of GameWindow Class.
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;

    // Constructor: Head method of this Game class, any code in here will be run
    //
    public Game() { // Main Thread
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
   }

    private void startGameLoop() {
        gameThread = new Thread(this); // Create Game Thread, runs concurrently with main thread
        gameThread.start();
    }

    @Override
    public void run() { // main routine for the game thread.

        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        while (true) {

            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
    }
}
