import java.awt.*;
import java.awt.event.*;
import java.nio.channels.Pipe;
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

    ArrayList<Pipe> pipes;
    Random random = new Random();


    Timer gameLoop;
    Timer placePipesTimer;

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
    pipes = new ArrayList<Pipe>();
    // place pipe 
    placePipesTimer = new Timer(1500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            placePipes();
        }
    } );
    placePipesTimer.start();
    // game timer
    gameLoop = new Timer(1000/60, this);
    gameLoop.start();
    }

    public void placePipes()
    {
        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        int openingSpace = boardHeight/4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);

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

        //pipes
        for(int i = 0; i < pipes.size(); i++){

            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);

        }
    }

    public void move()
    {
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        //pipes
        for(int i = 0; i < pipes.size(); i++)
        {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
        }
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