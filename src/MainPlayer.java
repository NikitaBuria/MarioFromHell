
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class MainPlayer  extends Entity {

      GameScene gameScene;
      Movement movement;
      public final int screenX;
      public final int screenY;
      final long jumpingTime=200;


public MainPlayer(GameScene gameScene,Movement movement)
{

    this.gameScene=gameScene;
    this.movement=movement;
    screenX=gameScene.WINDOW_WIDTH/2- gameScene.tileSize/2;
    screenY=gameScene.WINDOW_HEIGHT/2- gameScene.tileSize/2;


    solidArea=new Rectangle(
            this.worldX,
            this.worldY,
            gameScene.tileSize-10
            ,gameScene.tileSize-10
    );


    this.setDefaultValues();
    this.getPlayerImage();
}

public void setDefaultValues()
{
    worldX=gameScene.tileSize*10;
    worldY=gameScene.tileSize*3;
    speed=4;
    direction="down";
}

public void update()
{

    if (movement.isMoveRight()) {
        direction = "right";
        worldX += speed;
    }
    if (movement.isMoveLeft()) {
        direction = "left";
        worldX -= speed;
    }
    if (this.movement.isJump()) {
        direction = "up";
        worldY -= speed-2;
        Thread jump = new Thread(() -> {
            try {
                Thread.sleep(200);
                movement.setJump(false);
                movement.setLanding(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        jump.start();
    }if (this.movement.isJump()&&this.movement.isMoveLeft()) {
        direction = "up and left";
        worldY -= speed-2;
        Thread jump = new Thread(() -> {
            try {
                Thread.sleep(200);
                movement.setJump(false);
                movement.setLanding(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        jump.start();
    }
    if (this.movement.isJump()&&this.movement.isMoveRight()) {
        direction = "up and right";
        worldY -= speed;
        Thread jump = new Thread(() -> {
            try {
                Thread.sleep(200);
                movement.setJump(false);
                movement.setLanding(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        jump.start();
    }

    if (this.movement.isLanding()) {
        worldY += speed;
    }

    collision=false;
    gameScene.collisionChecker.checkTile(this);


    spriteCounter++;
    if (spriteCounter > 10) {
        if (spriteNum == 1) {
            spriteNum = 2;
        } else if (spriteNum == 2) {
            spriteNum = 1;
        }
        spriteCounter = 0;
    }
}







    public long getJumpingTime() {
        return this.jumpingTime;
    }



    public boolean checkCollisionWithObstacles( )
    {
        boolean collision=false;
        Rectangle checkCollisionBody=new Rectangle(

        );
        Rectangle checkSpikes=new Rectangle(

        );
        if (checkSpikes.intersects(checkCollisionBody)) {

            collision = true;
        }
        return  collision;
    }
    public boolean checkCollisionWithTerrain( )
    {
        boolean collision=false;

        Rectangle checkCollisionBody=new Rectangle(

        );
        Rectangle checkSpikes=new Rectangle(

        );
        if (checkSpikes.intersects(checkCollisionBody)) {
            collision = true;
        }
        return  collision;
    }



    public void getPlayerImage()
    {

     try {


           right1= ImageIO.read(getClass().getResourceAsStream("PlayerMovement/JohhnyMovesRight-1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("PlayerMovement/JohhnyMovesRight-2.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("PlayerMovement/JohhnyMovesLeft-1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("PlayerMovement/JohhnyMovesLeft-2.png"));
            up1=ImageIO.read(getClass().getResourceAsStream("PlayerMovement/Jump-1.png"));
         up2=ImageIO.read(getClass().getResourceAsStream("PlayerMovement/Jump-2.png"));


     } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void draw(Graphics2D g2)
    {

        BufferedImage image=right1;

        switch (direction) {
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "up and left" ->{

            if (spriteNum == 1) {
                image = up1;
            }
            if (spriteNum == 2) {
                image = up1;
            }

        }
            case "up and right" ->{

                if (spriteNum == 1) {
                    image = up2;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }

        }
        g2.drawImage(image,screenX,screenY,gameScene.tileSize,gameScene.tileSize,null);
    }



}
