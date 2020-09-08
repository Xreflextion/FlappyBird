package java_flappy_bird;
import java.util.ArrayList;
import java.awt.Graphics;

public class PipeManagement {
    protected ArrayList<Pipe> pipes = new ArrayList<Pipe>();

    public void add(int x) {
        Pipe pipe = new Pipe(x);
        pipes.add(pipe);
    }

    public void draw_all(Graphics g) {
        for (Pipe i: pipes) {
            i.draw(g);
        }
    }
}