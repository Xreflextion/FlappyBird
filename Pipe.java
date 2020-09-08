package java_flappy_bird;
import java.awt.Graphics;
import java.awt.Color;

public class Pipe {
    protected int space = 150;
    protected int pipe = 450;
    protected int min = 75;
    protected int x;
    protected int width = 100;
    protected int top_height = min + (int)(Math.random() * ((pipe - min) -min + 1));
    protected int bot_y = top_height + space;
    protected int bot_height = pipe - top_height;
    protected int flag = 0;

    public Pipe(int new_x) {
        this.x = new_x;
    }

    public void draw(Graphics g) {
        g.setColor(Color.decode("#ff6600"));
        g.fillRect(this.x, 1, width, this.top_height);
        g.fillRect(this.x, this.bot_y, width, this.bot_height);
    }

    public void move(int speed) {
        this.x -= speed;
    }
}