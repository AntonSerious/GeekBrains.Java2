package GeekBrains.Java2.hometask1;

import java.awt.*;

public class Background extends Sprite{
    private Color bgdColor = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255) );
    private GameCanvas canvas;

    Background(GameCanvas canvas){
        setLeft(canvas.getLeft());
        setRight(canvas.getRight());
        setTop(canvas.getTop());
        setBottom(canvas.getBottom());
        //this.canvas = canvas;
        //this.bgdColor = bgdColor;
    }
    @Override
    public void render(GameCanvas canvas, Graphics g){
        g.setColor(bgdColor);
        g.fillRect(0,0, (int)getWidth(), (int)getHeight());
    }
}
