import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    Random random;
    int xVelocity;
    int yVelocity;
    int ballSpeed=2;

    Ball(int x,int y,int width,int height){
      super(x,y,width,height);
      random = new Random();
     //for x axis
      int randomXDirection = random.nextInt(2);
      if(randomXDirection==0){
          //move ball to the left .
         randomXDirection--;
      }
      setXDirection(randomXDirection*ballSpeed);
      //for y axis
      int randomYDirection = random.nextInt(2);
      if(randomYDirection==0){
          randomYDirection--;
      }
      setYDirection(randomYDirection*ballSpeed);
    }
    public void move(){
        x=x+xVelocity;
        y=y+yVelocity;
    }
    public void setXDirection(int xDirection){
         xVelocity=xDirection;
    }
    public void setYDirection(int yDirection){
      yVelocity=yDirection;
    }
    public void draw(Graphics g){
         g.setColor(Color.WHITE);
         g.fillOval(x,y,width,height);
    }


}
