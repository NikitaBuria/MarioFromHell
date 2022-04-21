import javax.swing.*;

public class Main extends JFrame{

    public static void main(String[] args) {
        new Main();
    }

    public Main()
    {

        JFrame window =new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Mario From Hell");
        GameScene gameScene=new GameScene();
        window.add(gameScene);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }

}
