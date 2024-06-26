import java.awt.*;

public class Score {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;
    Score(int GAME_WIDTH,int GAME_HEIGHT){
      Score.GAME_WIDTH = GAME_WIDTH;
      Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas",Font.PLAIN,40));
        g2D.setStroke(new BasicStroke(3));
        g.drawLine(GAME_WIDTH/2,0,GAME_WIDTH/2,GAME_HEIGHT);
        g.drawLine(0,0,0,GAME_HEIGHT);
        g.drawLine(GAME_WIDTH,0,GAME_WIDTH,GAME_HEIGHT);
        g.setColor(new Color(0x12345678));
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10),(GAME_WIDTH/2)-65,30);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10),(GAME_WIDTH/2)+20,30);

    }

}
