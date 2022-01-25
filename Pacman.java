
import java.awt.Point;
import javax.swing.ImageIcon;


public class Pacman {
    
    private Point move;
    private int speed;
    private ImageIcon right;
    private ImageIcon left;
    private ImageIcon up;
    private ImageIcon down;
    private char dir;
    private Point lastPos;
    private Point currentPos;
    
    public Pacman() {
        move = new Point(0, 0);
        lastPos = new Point(210, 360);
        speed = 1;
        left = new javax.swing.ImageIcon(getClass().getResource("/img/pacman_left.gif"));
        right = new javax.swing.ImageIcon(getClass().getResource("/img/pacman_right.gif"));
        up = new javax.swing.ImageIcon(getClass().getResource("/img/pacman_up.gif"));
        down = new javax.swing.ImageIcon(getClass().getResource("/img/pacman_down.gif"));
        dir = 'r';
    }
    
    public Point getMove() {
        return move;
    }

    public void setMove(Point nextmove) {
        this.move = nextmove;
    }
    
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setDir(char dir) {
        this.dir = dir;
    }
    
    public ImageIcon getImage() {
        if (dir=='l')
            return left;
        else if (dir=='r')
            return right;
        else if (dir=='u')
            return up;
        else
            return down;
    }
    
    public Point getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(Point currentPos) {
        this.currentPos = currentPos;
    }

    public Point getLastPos() {
        return lastPos;
    }

    public void setLastPos(Point lastPos) {
        this.lastPos = lastPos;
    }
    
    public void powerUp() {
        this.speed *= 2;
    }
}


