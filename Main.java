package java_flappy_bird;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] arg) {
        JFrame frame = new JFrame();
        Gameplay game_play = new Gameplay();
        frame.setBounds(10,10,700,600);
        frame.setTitle("Flappy Bird");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game_play);
        frame.setVisible(true);
    }
}