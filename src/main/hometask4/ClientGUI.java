package hometask4;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2,3));

    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("Anton");
    private final JPasswordField tfPass = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList userList = new JList();

    protected static FileWriter systemLog;
    protected static Date date = new Date();

    public static void main(String[] args) throws IOException{
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ClientGUI();
                    wrtMsgToLogFile(" Client GUI has started to work..." + "\n");
                } catch (IOException e) {
                    try {
                        systemLog.write(e.toString());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        });

    }
    private static void wrtMsgToLogFile(String msg){
        try{
            FileWriter out = new FileWriter("systemLogs.txt",true);
            out.write(msg);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ClientGUI() throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);

        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUser = new JScrollPane(userList);

        String[] users = {"user1", "user2", "user3", "user4", "user_with_extremally_long_nickname_in_chat"};

        userList.setListData(users);
        log.setEditable(false);
        scrollUser.setPreferredSize(new Dimension(150,0));
        cbAlwaysOnTop.addActionListener(this);
        btnSend.addActionListener(this);
        tfMessage.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()== '\n'){
                    btnSend.doClick();
                    wrtMsgToLogFile(date.toString() +" key 'Enter' is pressed" + "\n");

                }
                //System.out.println(e.getKeyChar()); //почему e.getKeyCode всегда возвращает 0?
            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }
        });
        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPass);
        panelTop.add(btnLogin);

        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(scrollLog, BorderLayout.CENTER);
        add(scrollUser, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == cbAlwaysOnTop){
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        }
        else if(src == btnSend){
            System.out.println("btnSend");
            if("".equals(this.tfMessage.getText())) return;

            this.log.setText(this.log.getText() + this.tfLogin.getText() + " :" + this.tfMessage.getText() + "\n");
            wrtMsgToLogFile(this.tfLogin.getText() + " has sent the message: " + this.tfMessage.getText() + "\n");
            this.tfMessage.setText("");


        }
        else{
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

        try {
            systemLog.write(msg);
            systemLog.flush();
            systemLog.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

}
