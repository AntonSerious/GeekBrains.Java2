package GeekBrains.Java2.hometask1;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainCircles();
            }
        });
    }
}
