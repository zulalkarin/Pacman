
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Ghost {
    
    private int speed;
    private String state;
    private ImageIcon image;
    private char color;
    private Point lastPos;
    private Point currentPos;
    
    public Ghost(char color) {
        speed = 1;
        state="chase";
        this.color = color;
        currentPos = new Point(0, 0);
        lastPos = new Point(210, 150);
        switch (color) {
            case 'r':
                image = new javax.swing.ImageIcon(getClass().getResource("/img/red.gif"));
                break;
            case 'b':
                image = new javax.swing.ImageIcon(getClass().getResource("/img/blue.gif"));
                break;
            case 'p':
                image = new javax.swing.ImageIcon(getClass().getResource("/img/pink.gif"));
                break;
            case 'o':
                image = new javax.swing.ImageIcon(getClass().getResource("/img/orange.gif"));
                break;
            default:
                break;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ImageIcon getImage() {
        return image;
    }
    
    public void setLastPos(Point pos) {
        lastPos = pos;
    }
    
    public Point getLastPos() {
        return lastPos;
    }

    public Point getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(Point currentPos) {
        this.currentPos = currentPos;
    }

    public char getColor() {
        return color;
    }
    
    public Point scatter(int[][] map, Point target) {
        ArrayList<Point> positions = new ArrayList<>();
        Point nextMove = new Point(0, 0);
        double currentCost = Integer.MAX_VALUE;
        positions.add(new Point(currentPos.x-15, currentPos.y)); // left
        positions.add(new Point(currentPos.x+15, currentPos.y)); // right
        positions.add(new Point(currentPos.x, currentPos.y-15)); // up
        positions.add(new Point(currentPos.x, currentPos.y+15)); // down
        
        // just aim for the corners
        for (Point pos: positions) {
           
            if (!(pos.x==lastPos.x && pos.y==lastPos.y) && cost(pos, target) < currentCost) {
                // check if it's a valid position
                if (pos.x/15 > 1 && pos.x/15 < 29 && pos.y/15 > 1 && pos.y/15 < 29
                        && map[(pos.y/15)-2][(pos.x/15)-2]==1) {
                    nextMove = new Point(pos.x-currentPos.x, pos.y-currentPos.y);
                    currentCost = cost(pos, target);
                }
            }
        }
        
        lastPos = currentPos;
        return nextMove; 
    }
    
    public Point chase(int[][] map, Point target) {
        
        ArrayList<Point> positions = new ArrayList<>();
        Point nextMove = new Point(0, 0);
        double currentCost = Integer.MAX_VALUE;
        positions.add(new Point(currentPos.x-15, currentPos.y)); // left
        positions.add(new Point(currentPos.x+15, currentPos.y)); // right
        positions.add(new Point(currentPos.x, currentPos.y-15)); // up
        positions.add(new Point(currentPos.x, currentPos.y+15)); // down
        switch (color) {
            case 'r':
                // just aim for pacman's position
                for (Point pos: positions) {
                    
                    if (!(pos.x==lastPos.x && pos.y==lastPos.y) && cost(pos, target) < currentCost) {
                        // check if it's a valid position
                        if (pos.x/15 > 1 && pos.x/15 < 29 && pos.y/15 > 1 && pos.y/15 < 29
                                && map[(pos.y/15)-2][(pos.x/15)-2]==1) {
                            nextMove = new Point(pos.x-currentPos.x, pos.y-currentPos.y);
                            currentCost = cost(pos, target);
                        }
                    }
                }
                break;
            case 'b':
                // aim for the farthest direction pacman go
                int pac_x = (target.x/15) - 2;
                int pac_y = (target.y/15) - 2;
                
                int down_dist = 0;
                Point newTarget = new Point(0, 0);
                for (int i = pac_y; i < 26; i++) {
                    
                    if(map[i+1][pac_x]==1) {
                        down_dist++;
                    } else {
                        newTarget = new Point(target.x, (i+2)*15);
                        break;
                    }
                }
               
                int up_dist = 0;
                for (int i = pac_y; i > 0; i--) {
                    if(map[i-1][pac_x]==1) {
                        up_dist++;
                    } else {
                        if (up_dist > down_dist) {
                            newTarget = new Point(target.x, (i+2)*15);
                        }
                        break;
                    }
                }
                
                int left_dist = 0;
                for (int i = pac_x; i > 0; i--) {
                    if(map[pac_y][i-1]==1) {
                        left_dist++;
                    } else {
                        if (left_dist > up_dist) {
                            newTarget = new Point((i+2)*15, target.y);
                        }
                        break;
                    }
                }
              
                int right_dist = 0;
                for (int i = pac_x; i < 26; i++) {
                    if(map[pac_y][i+1]==1) {
                        right_dist++;
                    } else {
                        if (right_dist > left_dist) {
                            newTarget = new Point((i+2)*15, target.y);
                        }
                        break;
                    }
                }
                
                for (Point pos: positions) {
                  
                    if (!(pos.x==lastPos.x && pos.y==lastPos.y) && cost(pos, newTarget) < currentCost) {
                        // check if it's a valid position
                        if (pos.x/15 > 1 && pos.x/15 < 29 && pos.y/15 > 1 && pos.y/15 < 29
                                && map[(pos.y/15)-2][(pos.x/15)-2]==1) {
                            nextMove = new Point(pos.x-currentPos.x, pos.y-currentPos.y);
                            currentCost = cost(pos, newTarget);
                        }
                    }
                }
                break;
            case 'p':
                
                Random p = new Random();
                while (true) {
                    int index = p.nextInt(4);
                    Point pos = positions.get(index);
                    // check if it is valid
                    if (!(pos.x==lastPos.x && pos.y==lastPos.y)
                            && pos.x/15 > 1 && pos.x/15 < 29 && pos.y/15 > 1 && pos.y/15 < 29
                                && map[(pos.y/15)-2][(pos.x/15)-2]==1) {
                            nextMove = new Point(pos.x-currentPos.x, pos.y-currentPos.y);
                            break;
                    }
                }
                break;
            default:
               
                // if no available target in 3 tile proximity, try 2 and then 1
                ArrayList<Point> targets = new ArrayList<>();
                targets.add(new Point(target.x-90, target.y));
                targets.add(new Point(target.x+90, target.y));
                targets.add(new Point(target.x, target.y-90));
                targets.add(new Point(target.x, target.y+90));
                targets.add(new Point(target.x+90, target.y+90));
                targets.add(new Point(target.x-90, target.y-90));
                
                targets.add(new Point(target.x-60, target.y));
                targets.add(new Point(target.x+60, target.y));
                targets.add(new Point(target.x, target.y-60));
                targets.add(new Point(target.x, target.y+60));
                targets.add(new Point(target.x+60, target.y+60));
                targets.add(new Point(target.x-60, target.y-60));
                
                targets.add(new Point(target.x-30, target.y));
                targets.add(new Point(target.x+30, target.y));
                targets.add(new Point(target.x, target.y-30));
                targets.add(new Point(target.x, target.y+30));
                targets.add(new Point(target.x+30, target.y+30));
                targets.add(new Point(target.x-30, target.y-30));
                
                Random o = new Random();
                
                while (true) {
                    int index = o.nextInt(18); 
                    Point close = targets.get(index);
                    // check if it is valid
                    if (close.x/15 > 1 && close.x/15 < 29 && close.y/15 > 1 && close.y/15 < 29
                                && map[(close.y/15)-2][(close.x/15)-2]==1) {
                            for (Point pos: positions) {
                            
                                if (!pos.equals(lastPos) && cost(pos, close) < currentCost) {
                                    // check if it's a valid position
                                    if (pos.x/15 > 1 && pos.x/15 < 29 && pos.y/15 > 1 && pos.y/15 < 29
                                            && map[(pos.y/15)-2][(pos.x/15)-2]==1) {
                                        nextMove = new Point(pos.x-currentPos.x, pos.y-currentPos.y);
                                        currentCost = cost(pos, close);
                                    }
                                }
                            }
                            break;
                    }
                }
                break;
        }
        lastPos = currentPos;
        return nextMove;
    }
    
    public double cost(Point from, Point to) {
        return Math.abs(Math.pow(from.x-to.x, 2) + Math.pow(from.y-to.y, 2));
    }
    
}
