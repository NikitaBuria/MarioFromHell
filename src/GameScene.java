import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameScene extends JPanel implements Runnable {

    public final int originalTileSize=24; //24x24
    public final int scale=3;
    public int tileSize=this.scale*originalTileSize;//72
    final int maxScreenCol=20;
    final int maxScreenRow=12;
    public final int WINDOW_WIDTH=tileSize*maxScreenCol;//1440
    public final int WINDOW_HEIGHT=tileSize*maxScreenRow;//864
    public   final int _FPS=60;
    public final  int MAX_WORLD_COL=100;
    public final  int MAX_WORLD_ROW=1;
    public final int WORLD_WIDTH=tileSize*MAX_WORLD_COL;
    public final int WORLD_HEIGHT=tileSize*MAX_WORLD_ROW;


    Thread mainLoop;
    TileManger tileManger=new TileManger(this);
    Movement movement=new Movement();
    CollisionChecker collisionChecker=new CollisionChecker(this);
    MainPlayer mainPlayer=new MainPlayer(this ,this.movement);


    public GameScene()
    {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(movement);
        this.setFocusable(true);
        this.startGameThread();
    }


    public void startGameThread()
    {
        mainLoop=new Thread(this);
        mainLoop.start();
    }

    public void run()
    {
        double drawInterval=1000000000/_FPS; //0.016666
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;
        while(mainLoop!=null) {
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            timer+=(currentTime-lastTime);
            lastTime=currentTime;

            if(delta>=1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer>=1000000000)
            {
                System.out.println("FPS :" + drawCount);
                drawCount=0;
                timer=0;
            }
        }
        }
    public void update()
    {

            this.mainPlayer.update();
    }



    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        tileManger.draw(g2);
        mainPlayer.draw(g2);
        g2.dispose();
    }



}
