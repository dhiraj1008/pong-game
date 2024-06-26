import javax.swing.*;
import javax.swing.plaf.PanelUI;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT =(int)(GAME_WIDTH*(5/9.0));

    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT =  100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Random random;
    Score score;
    GamePanel(){
        newPaddle();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        this.setFocusable(true);

        //start thread
        gameThread = new Thread(this);
        gameThread.start();

    }
    public void newBall(){
    //to generate the ball on the window
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt((GAME_HEIGHT)-(BALL_DIAMETER)),BALL_DIAMETER,BALL_DIAMETER);
    }
    public void newPaddle(){
    //to generate the paddle on the window
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);

    }
    public void paint(Graphics g){
        //to paint the 2D Graphics
        image = this.createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
        draw(g);

    }
    public void draw(Graphics g){
        //to draw the components on the window
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move(){
        //this method is to move the need objects on to the frame
        paddle1.move();
        paddle2.move();

        ball.move();
    }
    public  void checkCollision(){
        //check the collision of objects
        //check for top and bottom bounce
       if(ball.y<=0){
           ball.setYDirection(-ball.yVelocity);
       }
       if(ball.y>=(GAME_HEIGHT-BALL_DIAMETER)){
            ball.setYDirection(-ball.yVelocity);
       }
       //check if ball hits the paddles
       if(ball.intersects(paddle1)) {
           ball.setXDirection(-ball.xVelocity);
           ball.xVelocity=ball.xVelocity+2;//for more difficulty
           if(ball.yVelocity>0){
               ball.yVelocity++;//optional for more difficulty
           }
           else {
               ball.yVelocity--;
           }
       }
        if(ball.intersects(paddle2)) {
                ball.setXDirection(-ball.xVelocity);
                ball.xVelocity=ball.xVelocity-2;//for more difficulty
                if(ball.yVelocity>0){
                    ball.yVelocity++;//optional for more difficulty
                }
                else {
                    ball.yVelocity--;
                }
        }

      //stops paddle at window edges
        if(paddle1.y<=0){
            paddle1.y =0;
        }
        if(paddle2.y<=0){
            paddle2.y = 0;
        }
        if(paddle1.y>=(GAME_HEIGHT-PADDLE_HEIGHT)){
            paddle1.y =GAME_HEIGHT-PADDLE_HEIGHT;
        }
        if(paddle2.y>=GAME_HEIGHT-PADDLE_HEIGHT){
            paddle2.y =GAME_HEIGHT-PADDLE_HEIGHT;
        }
   //when ball touches left wall
        if(ball.x<=0){
            score.player2++;
            newPaddle();
            newBall();
  //          System.out.println("p2:"+score.player2);
        }
        //when ball touches right wall
        if(ball.x>=(GAME_WIDTH-BALL_DIAMETER)){
            score.player1++;
            newPaddle();
            newBall();
//            System.out.println("p1:"+score.player1);
        }



    }

    @Override
    public void run() {
      //game loop in ping-pong game,this will repaint the panel with updated dimensions per second  ..
        long lastTime = System.nanoTime();//system time in mm
        //System.out.println(lastTime);
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
       // System.out.println(ns);
        double delta = 0;
       while (true){
           long now = System.nanoTime();
           delta+=(now-lastTime)/ns;
           lastTime = now;
           if(delta>=1){
               move();
               checkCollision();
               repaint();
               delta--;
             //  System.out.println("hello world"+delta);
           }
       }
    }

    //Inner class for KeyListener
    class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
       //executes when key is pressed
            if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_S)
                paddle1.keyPressed(e);
            if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN)
                paddle2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
       //executes when key is released
            if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_S)
                paddle1.keyReleased(e);
            if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN)
                paddle2.keyReleased(e);
        }
    }

}
