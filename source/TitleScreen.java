import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    JButton startGame = new JButton("Start Game");

    ImageIcon image = new ImageIcon("snakePic.png");


    TitleScreen(){
        //button
        startGame.setBounds(225,300,150,50);
        startGame.setFocusable(false);
        startGame.setFont((new Font("Sitka Text", Font.PLAIN,20)));
        startGame.addActionListener(this);

        //labels
        JLabel gameLabel = new JLabel("Snake Game");
        JLabel dev = new JLabel("Developed by: Dustin Brown");
        gameLabel.setBounds(225,0,600,600);
        gameLabel.setFont((new Font("Sitka Text", Font.BOLD, 45)));

        dev.setBounds(225,0,600,600);
        dev.setFont((new Font("Sitka Text", Font.PLAIN, 15)));
        dev.setForeground(Color.GREEN);

        //panels
        JPanel panelTop = new JPanel();
        JPanel panelMenu = new JPanel();
        JPanel panelBottom = new JPanel();
        panelTop.setPreferredSize(new Dimension(600, 150));
        panelMenu.setPreferredSize(new Dimension(400, 400));
        panelBottom.setPreferredSize(new Dimension(600, 50));
        panelTop.add(gameLabel);
        panelMenu.add(startGame);
        panelBottom.add(dev);
        panelTop.setBackground(new Color(65,74,76));
        panelMenu.setBackground(new Color(65,74,76));
        panelBottom.setBackground(new Color(65,74,76));

        //window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);

        frame.setLayout(new BorderLayout());

        frame.setIconImage(image.getImage());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Snake Game");

        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelMenu, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startGame){
            frame.dispose();
            new GameFrame();
        }
    }
}
