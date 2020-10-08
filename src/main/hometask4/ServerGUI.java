package hometask4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler{
    private static final int POS_X = 1000;
    private static final int POS_Y = 550;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;

    private final ChatServer chatServer = new ChatServer();

    private JButton btnStart = new JButton("Start");
    private JButton btnStop = new JButton("Stop");


    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ServerGUI();
            }
        });
        System.out.println("Main is ended");
    }

    private ServerGUI(){
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH,HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);

        setLayout(new GridLayout(1,2));
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);

        add(btnStart);
        add(btnStop);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == btnStop){
            chatServer.stop();
        }
        else if(src == btnStart){
            //throw new RuntimeException("Hello from EDT");
            chatServer.start(8189);
        } else{
            throw new RuntimeException("Unknown source: " + src);
        }
    }

    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = "Exception in " + t.getName() + " " + e.getClass().getCanonicalName() + ": " +
                e.getMessage() + "\n\t at " + ste[0];
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
