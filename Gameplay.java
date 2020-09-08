package java_flappy_bird;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //abstract methods 
import java.awt.event.KeyEvent;
import java.awt.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private int width = 700;
    private int height = 600;

    private boolean running = false;
    private boolean end = false;
    private int score = 0;

    private Timer timer;
    private int delay = 8;

    private int player_x = 325;
    private int player_y = 270;
    private int player_r = 30;

    private int speed = 1;
    private Pipe last;

    PipeManagement generator = new PipeManagement();
    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        generator.add(width);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // bg
        g.setColor(Color.decode("#00cfff"));
        g.fillRect(1, 1, width, height);

        // ball
        g.setColor(Color.red);
        g.fillOval(player_x, player_y, player_r, player_r);

        // pipes
        generator.draw_all(g);

        // score
        g.setColor(Color.black);
        g.setFont(new Font("Dialog", Font.PLAIN, 25));
        g.drawString("" + score, 640, 30);

        if (!end && !running) {
            g.setColor(Color.black);
            g.setFont(new Font("Dialog", Font.BOLD, 30));
            g.drawString("Flappy Bird", 220, 300);

            g.setFont(new Font("Dialog", Font.PLAIN, 20));
            g.drawString("Press UP KEY to Start", 220, 350);
        }

        if (end && !running ) {
            g.setColor(Color.black);
            g.setFont(new Font("Dialog", Font.BOLD, 30));
            g.drawString("GAME OVER, SCORE: " + score, 190, 300);

            g.setFont(new Font("Dialog", Font.PLAIN, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }

        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (running && !end) {
            player_y += 1;
            int i = 0;
            while (i < generator.pipes.size()) {
                Pipe pipe = generator.pipes.get(i);
                if (pipe.x + pipe.width < 0) {
                    generator.pipes.remove(i);
                    continue;
                } else {
                    pipe.move(speed);
                    i++;
                }
                Rectangle player = new Rectangle(player_x, player_y, player_r, player_r);
                Rectangle top_pipe = new Rectangle(pipe.x, 1, pipe.width, pipe.top_height);
                Rectangle bot_pipe = new Rectangle(pipe.x, pipe.bot_y, pipe.width, pipe.bot_height);
                if (player.intersects(top_pipe) || player.intersects(bot_pipe)) {
                    running = false;
                    end = true;
                }
                if (pipe.flag == 0 && pipe.x + pipe.width < player_x) {
                    score += 1;
                    pipe.flag = 1;
                }
                if (player.y < 0) {
                    if (new Rectangle(player_x, player_y, player_r, player_r).intersects(new Rectangle(pipe.x, player_y, pipe.width, pipe.top_height))) {
                        running = false;
                        end = true;
                    }
                }
            }
            last = generator.pipes.get(generator.pipes.size()-1);
            if (last.x <= width - last.width*3) {
                generator.add(width);
            }
            if (player_y + player_r > height) {
                running = false;
                end = true;
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            move_up();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (end && !running) {
                end = false;
                running = true;
                score = 0;
                player_y = 270;
                generator = new PipeManagement();
                generator.add(width);
            } 
        }
    }

    public void move_up() {
        if (!end) {
            running = true;
            player_y -= 40;
        }
    }

    @Override 
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}