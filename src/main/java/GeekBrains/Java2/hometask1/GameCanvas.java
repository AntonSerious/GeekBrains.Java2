package GeekBrains.Java2.hometask1;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    long lastFrameTime;
    MainCircles gameController;
    static final int FPS = 60;
    public static final int newFramePeriod = 1000 / FPS; //пауза между фреймами в миллисекундах
    GameCanvas(MainCircles gameController){
        lastFrameTime = System.nanoTime();
        this.gameController = gameController;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;
        gameController.onDrawFrame(this, g, deltaTime);
        MainCircles.timeLeftFromLaunchInSec += deltaTime;
        try {
            Thread.sleep(newFramePeriod);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public int getLeft(){return 0;}
    public int getRight(){return getWidth() - 1;}
    public int getTop(){return 0;}
    public int getBottom(){return getHeight() - 1;}


}
