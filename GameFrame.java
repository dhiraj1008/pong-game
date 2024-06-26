import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements Runnable{
    GameFrame(){
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.setTitle("Ping-Pong Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(new Color(0x9DC171));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void run() {

    }
}
