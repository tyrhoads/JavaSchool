import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int boardWidth = 360;
    int boardHeight = 640;
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //Bird
    int birdx = boardWidth/8;
    int birdy = boardHeight/2;
    int birdWidth = 34;
    int birdHeight =24;


    class Bird
    {
        int x = birdx;
        int y = birdy;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img)
        {
            this.img = img;
        }

    
    }

    // Pipes
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

     class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img)
        {
            this.img = img;
        }
    
        
    }
    //game logic 
    Bird bird;
    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;

    
    Timer gameLoop;
    public Object requestFocus;
    FlappyBird() 
    {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        //setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);

    //load images
    backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
    birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
    topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
    bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
    
    //bird
    bird = new Bird(birdImg);
    // game timer
    gameLoop = new Timer(1000/60, this);
    gameLoop.start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight,null);

        g.drawImage(bird.img, bird.x, bird.y,bird.width, bird.height,null );
    }

    public void move()
    {
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        move();
        repaint();

    }

   
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            velocityY = -15;

        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}