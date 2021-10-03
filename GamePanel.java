import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25; //object size
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; // how many objects appear on screen
    static final int DELAY = 75; //higher number, slower game
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6; //size of snake
    int applesEaten = 0;
    int appleX; //positions of apple
    int appleY;
    char direction = 'R'; //r = right, l= left, u =up, d = down
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

    }
    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY, this);//add this?
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }
    public void draw(Graphics g){

        if(running){
            //grid for display
          for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE;i++){
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            //snake
            for(int i = 0; i<bodyParts; i++){
                //head
                if(i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                //body
                else{
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

                }
            }
            g.setFont(new Font("Sitka Text", Font.BOLD, 35));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());

        }
        else {
            gameOver(g);
        }

    }
    public void newApple(){
        appleX = random.nextInt(SCREEN_WIDTH /UNIT_SIZE)*UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT /UNIT_SIZE)*UNIT_SIZE;
    }
    public void move(){

        for(int i = bodyParts; i > 0; i--){
            x[i] = x[i-1]; //moves by 1
            y[i] = y[i-1];
        }
        switch (direction) {
            case 'U' -> y[0] = y[0] - UNIT_SIZE;
            case 'D' -> y[0] = y[0] + UNIT_SIZE;
            case 'L' -> x[0] = x[0] - UNIT_SIZE;
            case 'R' -> x[0] = x[0] + UNIT_SIZE;
        }
    }
    public void checkApple() {
        if((x[0] == appleX) && (y[0] == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }

    }
    public void checkCollisions(){
        //checks for head hitting body
        for(int i = bodyParts; i > 0; i--){
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                break;
            }
        }
        //check borders
        //left
        if(x[0] < 0){
            running = false;
        }
        //right
        if(x[0] > SCREEN_WIDTH){
            running = false;
        }
        //top
        if(y[0] < 0){
            running = false;
        }
        //bottom
        if(y[0] > SCREEN_HEIGHT){
            running = false;
        }
        if(!running){
            timer.stop();
        }
    }
    public void gameOver(Graphics g){
        //Game OVer txt
        g.setColor(Color.red);
        g.setFont(new Font("Sitka Text", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("GAME OVER", (SCREEN_WIDTH - metrics.stringWidth("Game Over ."))/2, SCREEN_HEIGHT/2);
        //score
        g.setFont(new Font("Sitka Text", Font.BOLD, 35));
        FontMetrics metricsScore = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metricsScore.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollisions();

        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_W:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_S:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_A:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;

            }

        }
    }
}
