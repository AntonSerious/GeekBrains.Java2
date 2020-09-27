package GeekBrains.Java2.hometask1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    public static float timeLeftFromLaunchInSec = 0;
    public static final int changeInterval = 2; //определяет - через сколько секунд начнется смена экрана
    public static final int changePeriod = 1; // в течении скольки секунд будет происходить плавная смена экрана. Стабильно работает при changePeriod = 1.
    public static int dR = 0;
    public static int dG = 0;
    public static int dB = 0;

    public static int newRcomp = 0;
    public static int newGcomp = 0;
    public static int newBcomp = 0;

    Sprite[] sprites = new Sprite[10];
    Sprite bgd = new Sprite();

    protected MainCircles(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X,POS_Y, WINDOW_WIDTH,WINDOW_HEIGHT);

        GameCanvas canvas = new GameCanvas(this);
        canvas.addMouseListener(new MouseAdapter() { //прописываю логику, при нажатии ЛКМ по канве
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) { //добавляю новый шарик при нажатии ЛКМ
                    System.out.println("MousePos: " + e.getX() + " " + e.getY());
                    addBall();
                }
                if(e.getButton() == MouseEvent.BUTTON3) { //удаляю самый "старый шарик" при нажатии ПКМ
                    System.out.println("MousePos: " + e.getX() + " " + e.getY());
                    removeBall();
                }

            }
        });
        initApplication();
        add(canvas);

        setTitle("Circles");
        setVisible(true);
    }

    private void initApplication(){
        for (int i = 0; i < sprites.length ; i++) { //добавляем 10 шариков
            sprites[i] = new Ball();
        }

    }
    void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime ){
        update(canvas, deltaTime);
        render(canvas, g);
        drawTimeFromLaunch(canvas, g);

        //if((int) timeLeftFromLaunchInSec % changeInterval == 0 && (timeLeftFromLaunchInSec - (int)timeLeftFromLaunchInSec) < canvas.newFramePeriod * 0.001f * 1.5f ){
        //    changeBgd(canvas, g);
        //}


       // changeBgdColor(canvas, timeLeftFromLaunchInSec, changeInterval); //метод, меняющий заливку
        changeBgdColorSmoothly(canvas, changeInterval, changePeriod);

    }
    private void update(GameCanvas canvas, float deltaTime){
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }
    private void render(GameCanvas canvas, Graphics g){
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].render(canvas, g);
        }
    }
    private void changeBgd(GameCanvas canvas, Graphics g){
        bgd.render(canvas, g);
    }
    private void drawTimeFromLaunch(GameCanvas canvas, Graphics g){
        g.setColor(Color.BLACK);
        g.drawString(Float.toString(timeLeftFromLaunchInSec), canvas.getLeft()+50,canvas.getBottom()-50);
    }
    //Метод меняющий цвет плавно.
    void changeBgdColorSmoothly(GameCanvas canvas, int changeInterval, int changePeriod){
        if(((int) timeLeftFromLaunchInSec % changeInterval == 0) &&(timeLeftFromLaunchInSec - (int)timeLeftFromLaunchInSec) < canvas.newFramePeriod * 0.001f * 1.9f){
            newRcomp = (int)(Math.random() * 255);
            newGcomp = (int)(Math.random() * 255);
            newBcomp = (int)(Math.random() * 255);
            dR = (newRcomp - canvas.getBackground().getRed()) / (changePeriod * canvas.FPS);
            dG = (newGcomp - canvas.getBackground().getGreen()) / (changePeriod * canvas.FPS);
            dB = (newBcomp - canvas.getBackground().getBlue()) / (changePeriod * canvas.FPS);
        }

        if(((int) (timeLeftFromLaunchInSec % changeInterval) < changePeriod)){
            canvas.setBackground(new Color(canvas.getBackground().getRed()+dR,canvas.getBackground().getGreen()+dG, canvas.getBackground().getBlue() + dB) );
        }
    }



    //Создаю метод который меняет заливку каждые changeInterval секунд.
    void changeBgdColor(GameCanvas canvas, float timeFromLaunch, int changeInterval){

        if((int) timeFromLaunch % changeInterval == 0 && (timeFromLaunch - (int)timeFromLaunch) < canvas.newFramePeriod * 0.001f * 1.3f ){

            canvas.setBackground(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));

        }

    }
    void addBall(){
        Sprite[] biggerSprites = new Sprite[sprites.length+1];
        int curLength = sprites.length;
        for (int i = 0; i < curLength ; i++) {
            biggerSprites[i] = sprites[i];
        }
        biggerSprites[curLength] = new Ball();
        sprites = biggerSprites;
    }
    void removeBall(){
        if(sprites.length>0) {
            Sprite[] smallerSprites = new Sprite[sprites.length - 1];
            int curLength = sprites.length;
            for (int i = 0; i < curLength-1; i++) {
                smallerSprites[i] = sprites[i+1];
            }
            sprites = smallerSprites;
        }
    }



}
