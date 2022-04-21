
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener {

    private boolean isMoveRight;
    private boolean isMoveLeft;
    private boolean isJump;
    private boolean isLanding;
    private boolean isMove;
    private String  direction;



    public Movement()
    {
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_D -> {
                    setMove(true);
                    this.setMoveRight(true);
                }
                case KeyEvent.VK_A -> {
                    this.setMove(true);
                    this.setMoveLeft(true);
                }

                case KeyEvent.VK_SPACE ->{
                    this.setJump(true);
                    this.setMove(true);
                }
                }
            }


    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_D -> {
                this.setMove(false);
                this.setMoveRight(false);
            }
            case KeyEvent.VK_A -> {
                this.setMove(false);
                this.setMoveLeft(false);
            }
            case KeyEvent.VK_SPACE-> {
              this.setJump(true);
            }
        }
    }

    public boolean isMoveRight() {
        return isMoveRight;
    }

    public void setMoveRight(boolean moveRight) {
        isMoveRight = moveRight;
    }

    public boolean isMoveLeft() {
        return isMoveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        isMoveLeft = moveLeft;
    }

    public boolean isJump() {
        return isJump;
    }

    public void setJump(boolean jump) {
        isJump = jump;
    }



    public boolean isLanding() {
        return isLanding;
    }

    public void setLanding(boolean landing) {
        isLanding = landing;
    }

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean move) {
        isMove = move;
    }
}
