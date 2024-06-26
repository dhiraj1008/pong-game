import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    int id;
    int yVelocity;
    int speed=10;
    Paddle(int x,int y,int width,int height,int id){
        super(x,y,width,height);
        this.id=id;
    }
    public void move(){
        y=y+yVelocity;
    }
    public void setYDirection(int yDirection){
        this.yVelocity = yDirection;
    }
    public void draw(Graphics g){
      if (id==1)
          g.setColor(Color.RED);
      else
          g.setColor(Color.ORANGE);

      g.fillRect(x,y,width,height);
    }
    public void keyPressed(KeyEvent e){
        //System.out.println("key pressed");
         switch (id){
             case 1: {
                 if (e.getKeyCode() == KeyEvent.VK_W) {
                    // System.out.println("1up");
                     setYDirection(-speed);
                 }
                 if (e.getKeyCode() == KeyEvent.VK_S) {
                     setYDirection(speed);
                 }
                 move();
                 break;
             }
             case  2: {
                 if (e.getKeyCode() == KeyEvent.VK_UP) {
                     setYDirection(-speed);
                 }
                 if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                     setYDirection(speed);
                 }
                 move();
                 break;
             }
         }
    }
    public void keyReleased(KeyEvent e){
        //System.out.println("key released");
        switch (id){
            case 1: {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    //System.out.println("1up");
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                }
                move();
                break;
            }
            case  2: {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                move();
                break;
            }
        }
    }
}
