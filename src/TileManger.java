import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManger {
    GameScene gameScene;
    Tile[] tiles;
    int[][] mapTileNum;

    public TileManger(GameScene gameScene)
    {

        this.gameScene=gameScene;
        tiles =new Tile[10];
        mapTileNum=new int[this.gameScene.MAX_WORLD_COL][this.gameScene.MAX_WORLD_ROW];
        getTiles();
        loadMap();
    }
    public void getTiles()
    {
        try {
            tiles[0]=new Tile();
            tiles[0].image= ImageIO.read(getClass().getResourceAsStream("Tiles/ground1.png"));
            tiles[0].collision=true;
            tiles[1]=new Tile();
            tiles[1].image= ImageIO.read(getClass().getResourceAsStream("Tiles/ground2.png"));
            tiles[2]=new Tile();
            tiles[2].image= ImageIO.read(getClass().getResourceAsStream("Tiles/ground6.png"));
            tiles[3]=new Tile();
            tiles[3].image= ImageIO.read(getClass().getResourceAsStream("SkyBackground/Sky-2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap()
    {
        try{
            InputStream inputStreams=getClass().getResourceAsStream("Maps/tileLayOut.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStreams));
            int col=0;
            int row=0;
            while (col<gameScene.MAX_WORLD_COL&& row<gameScene.MAX_WORLD_ROW)
            {
                String line = br.readLine();
                while (col < gameScene.MAX_WORLD_COL)
                {
                    String[] numbers = line.split("");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if (col==gameScene.MAX_WORLD_COL)
                {
                    col=0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e)
        {

        }
    }

    public void  draw(Graphics2D g2)
    {
        int worldCol=0;
        int worldRow=0;

        while (worldCol<gameScene.MAX_WORLD_COL && worldRow<gameScene.MAX_WORLD_ROW)
        {


            int tileNum=mapTileNum[worldCol][worldRow];


            int worldX=worldCol* gameScene.tileSize;
            int worldY=worldRow* gameScene.tileSize;

            int screenX=worldX- gameScene.mainPlayer.worldX+ gameScene.mainPlayer.screenX;
            int screenY=worldY- gameScene.mainPlayer.worldY+ gameScene.mainPlayer.screenY;


            g2.drawImage(tiles[tileNum].image,screenX,screenY,gameScene.tileSize,gameScene.tileSize,null);
            worldCol++;


            if (worldCol==gameScene.maxScreenCol)
            {
                worldCol=0;
                worldRow++;

            }
        }
    }
}
