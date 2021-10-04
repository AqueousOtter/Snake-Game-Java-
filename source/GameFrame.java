import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    GamePanel gamePanel;
    ImageIcon image = new ImageIcon("snakePic.png");
    GameFrame(){
        this.setIconImage(image.getImage());
        this.setTitle("Playing");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600,700);

        gamePanel = new GamePanel();

        this.add(gamePanel);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);//center screen
    }
}
