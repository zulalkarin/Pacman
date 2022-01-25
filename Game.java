
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;


public class Game extends javax.swing.JFrame {
    
    boolean gameStarted; 
    int key;
    Pacman pac;
    JLabel pacman;
    ArrayList<Ghost> ghosts;
    
    ArrayList<JLabel> orbs;
    int points;
    int difficulty;
    
    
    Timer timer;
    int totalTime;
    int scatterTimer;
    int chaseTimer;
    
 
    
    
    final int[][] map =  
         {
         {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
         {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1},
         {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
         {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
         {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
         {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0},
         {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
         {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
         {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
         {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1},
         {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
         {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
         {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
         {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
         {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
         };
    
    public Game() {
        
        initComponents();
        points = 0;
        totalTime = 0;
        scatterTimer = 0;
        chaseTimer = 0;
        difficulty = 0;
        ready.setVisible(false);
        gameOver.setVisible(false);
        complete.setVisible(false);
        gameStarted = false;
        orbs = new ArrayList<>();
        
        
        pac = new Pacman();
        ghosts = new ArrayList<>();
        ghosts.add(new Ghost('r'));
        ghosts.add(new Ghost('b'));
        ghosts.add(new Ghost('p'));
        ghosts.add(new Ghost('o'));
        
        //In this part we print orbs to the screen
        ImageIcon smallOrb = new javax.swing.ImageIcon(getClass().getResource("/img/smallorb2.png"));
        ImageIcon bigOrb = new javax.swing.ImageIcon(getClass().getResource("/img/bigorb2.png"));
        
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                JLabel orb = new JLabel();
                boolean bigs = i==0 && j==0 || i==0 && j==13 || i==13 && j==0 || i==13 && j==13;
                if (i==11 && j==6) {
                 
               
                }
                // big orbs
                else if (bigs){
                    level.add(orb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30+j*30, 30+i*30, 32, 32), 6);
                    orb.setIcon(bigOrb);
                    orbs.add(orb);
                    // it flashs the big orbs
                    timer = new Timer(100, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (orb.isVisible()) {
                                orb.setVisible(false);
                            } else {
                                orb.setVisible(true);
                            }
                        }
                    }
                    );
                    timer.start();
                    
                }
                // small orbs
                else if (!bigs && map[2*i][2*j] == 1) {
                    level.add(orb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30+j*30, 30+i*30, 32, 32), 6);
                    orb.setIcon(smallOrb);
                    orbs.add(orb);
                    orb.setVisible(true);
                }
                
            }
        }
        
 
        
        //key listener 
        addKeyListener(new myAdapter());
        
        // flash start game prompt
        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (start.isVisible()) {
                    start.setVisible(false);
                } else {
                    start.setVisible(true);
                }
            }
        }
        );
        timer.start();
        
        // set initial movement to the right
        key = KeyEvent.VK_RIGHT;
        
        // print pacman initial
        JLabel newPacman = new javax.swing.JLabel();
        newPacman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pacman.png")));
        level.add(newPacman, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 32, 32), 6);
        pacman = newPacman;
        pacman.setVisible(true);
        
        //ghosts initial
        redGhost.setIcon(ghosts.get(0).getImage());
        blueGhost.setIcon(ghosts.get(1).getImage());
        pinkGhost.setIcon(ghosts.get(2).getImage());
        orangeGhost.setIcon(ghosts.get(3).getImage());
        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        background = new javax.swing.JPanel();
        level = new javax.swing.JPanel();
        complete = new javax.swing.JLabel();
        gameOver = new javax.swing.JLabel();
        ready = new javax.swing.JLabel();
        start = new javax.swing.JLabel();
        redGhost = new javax.swing.JLabel();
        blueGhost = new javax.swing.JLabel();
        pinkGhost = new javax.swing.JLabel();
        orangeGhost = new javax.swing.JLabel();
        obstacle1 = new javax.swing.JPanel();
        obstacle2 = new javax.swing.JPanel();
        obstacle3 = new javax.swing.JPanel();
        obstacle4 = new javax.swing.JPanel();
        obstacle5 = new javax.swing.JPanel();
        obstacle6 = new javax.swing.JPanel();
        obstacle7 = new javax.swing.JPanel();
        obstacle8 = new javax.swing.JPanel();
        obstacle9 = new javax.swing.JPanel();
        obstacle10 = new javax.swing.JPanel();
        obstacle11 = new javax.swing.JPanel();
        obstacle12 = new javax.swing.JPanel();
        obstacle13 = new javax.swing.JPanel();
        obstacle14 = new javax.swing.JPanel();
        obstacle15 = new javax.swing.JPanel();
        obstacle16 = new javax.swing.JPanel();
        obstacle17 = new javax.swing.JPanel();
        obstacle18 = new javax.swing.JPanel();
        obstacle19 = new javax.swing.JPanel();
        obstacle20 = new javax.swing.JPanel();
        obstacle21 = new javax.swing.JPanel();
        obstacle22 = new javax.swing.JPanel();
        obstacle23 = new javax.swing.JPanel();
        obstacle24 = new javax.swing.JPanel();
        obstacle25 = new javax.swing.JPanel();
        obstacle26 = new javax.swing.JPanel();
        obstacle27 = new javax.swing.JPanel();
        obstacle28 = new javax.swing.JPanel();
        obstacle29 = new javax.swing.JPanel();
        obstacle30 = new javax.swing.JPanel();
        door = new javax.swing.JPanel();
        heart1 = new javax.swing.JLabel();
        heart2 = new javax.swing.JLabel();
        heart3 = new javax.swing.JLabel();
        score = new javax.swing.JLabel();
        scorevalue = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pacman");
        setResizable(false);

        background.setBackground(java.awt.Color.black);
        background.setMinimumSize(new java.awt.Dimension(504, 541));
        background.setPreferredSize(new java.awt.Dimension(504, 541));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        level.setBackground(java.awt.Color.black);
        level.setPreferredSize(new java.awt.Dimension(480, 480));
        level.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        complete.setFont(new java.awt.Font("Lucida Fax", 1, 34)); // NOI18N
        complete.setForeground(java.awt.Color.white);
        complete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        complete.setText("LEVEL COMPLETE");
        level.add(complete, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        gameOver.setFont(new java.awt.Font("Lucida Fax", 1, 34)); // NOI18N
        gameOver.setForeground(java.awt.Color.white);
        gameOver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameOver.setText("GAME OVER");
        level.add(gameOver, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        ready.setFont(new java.awt.Font("Lucida Fax", 1, 34)); // NOI18N
        ready.setForeground(java.awt.Color.white);
        ready.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ready.setText("READY?");
        level.add(ready, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        start.setFont(new java.awt.Font("Lucida Fax", 1, 34)); // NOI18N
        start.setForeground(java.awt.Color.white);
        start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        start.setText("PRESS SPACE TO START");
        level.add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));
        level.add(redGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, -1, -1));
        level.add(blueGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, -1, -1));
        level.add(pinkGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, -1, -1));
        level.add(orangeGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        obstacle1.setBackground(new java.awt.Color(0, 0, 102));
        obstacle1.setPreferredSize(new java.awt.Dimension(32, 480));

        javax.swing.GroupLayout obstacle1Layout = new javax.swing.GroupLayout(obstacle1);
        obstacle1.setLayout(obstacle1Layout);
        obstacle1Layout.setHorizontalGroup(
            obstacle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        obstacle1Layout.setVerticalGroup(
            obstacle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        level.add(obstacle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, -1));

        obstacle2.setBackground(new java.awt.Color(0, 0, 102));
        obstacle2.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout obstacle2Layout = new javax.swing.GroupLayout(obstacle2);
        obstacle2.setLayout(obstacle2Layout);
        obstacle2Layout.setHorizontalGroup(
            obstacle2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        obstacle2Layout.setVerticalGroup(
            obstacle2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        level.add(obstacle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 460, 20));

        obstacle3.setBackground(new java.awt.Color(0, 0, 102));
        obstacle3.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout obstacle3Layout = new javax.swing.GroupLayout(obstacle3);
        obstacle3.setLayout(obstacle3Layout);
        obstacle3Layout.setHorizontalGroup(
            obstacle3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        obstacle3Layout.setVerticalGroup(
            obstacle3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        level.add(obstacle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 20, 20, 450));

        obstacle4.setBackground(new java.awt.Color(0, 0, 102));
        obstacle4.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout obstacle4Layout = new javax.swing.GroupLayout(obstacle4);
        obstacle4.setLayout(obstacle4Layout);
        obstacle4Layout.setHorizontalGroup(
            obstacle4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        obstacle4Layout.setVerticalGroup(
            obstacle4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        level.add(obstacle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 462, 460, 20));

        obstacle5.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle5Layout = new javax.swing.GroupLayout(obstacle5);
        obstacle5.setLayout(obstacle5Layout);
        obstacle5Layout.setHorizontalGroup(
            obstacle5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        obstacle5Layout.setVerticalGroup(
            obstacle5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        level.add(obstacle5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 10, 160));

        obstacle6.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle6Layout = new javax.swing.GroupLayout(obstacle6);
        obstacle6.setLayout(obstacle6Layout);
        obstacle6Layout.setHorizontalGroup(
            obstacle6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        obstacle6Layout.setVerticalGroup(
            obstacle6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        level.add(obstacle6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 10, 160));

        obstacle7.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle7Layout = new javax.swing.GroupLayout(obstacle7);
        obstacle7.setLayout(obstacle7Layout);
        obstacle7Layout.setHorizontalGroup(
            obstacle7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        obstacle7Layout.setVerticalGroup(
            obstacle7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        level.add(obstacle7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 10, 160));

        obstacle8.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle8Layout = new javax.swing.GroupLayout(obstacle8);
        obstacle8.setLayout(obstacle8Layout);
        obstacle8Layout.setHorizontalGroup(
            obstacle8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        obstacle8Layout.setVerticalGroup(
            obstacle8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 40, 10));

        obstacle9.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle9Layout = new javax.swing.GroupLayout(obstacle9);
        obstacle9.setLayout(obstacle9Layout);
        obstacle9Layout.setHorizontalGroup(
            obstacle9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        obstacle9Layout.setVerticalGroup(
            obstacle9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 70, 10));

        obstacle10.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle10Layout = new javax.swing.GroupLayout(obstacle10);
        obstacle10.setLayout(obstacle10Layout);
        obstacle10Layout.setHorizontalGroup(
            obstacle10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        obstacle10Layout.setVerticalGroup(
            obstacle10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 90, 10));

        obstacle11.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle11Layout = new javax.swing.GroupLayout(obstacle11);
        obstacle11.setLayout(obstacle11Layout);
        obstacle11Layout.setHorizontalGroup(
            obstacle11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        obstacle11Layout.setVerticalGroup(
            obstacle11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 160, 10));

        obstacle12.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle12Layout = new javax.swing.GroupLayout(obstacle12);
        obstacle12.setLayout(obstacle12Layout);
        obstacle12Layout.setHorizontalGroup(
            obstacle12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        obstacle12Layout.setVerticalGroup(
            obstacle12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, 90, 10));

        obstacle13.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle13Layout = new javax.swing.GroupLayout(obstacle13);
        obstacle13.setLayout(obstacle13Layout);
        obstacle13Layout.setHorizontalGroup(
            obstacle13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        obstacle13Layout.setVerticalGroup(
            obstacle13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 50, 10));

        obstacle14.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle14Layout = new javax.swing.GroupLayout(obstacle14);
        obstacle14.setLayout(obstacle14Layout);
        obstacle14Layout.setHorizontalGroup(
            obstacle14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        obstacle14Layout.setVerticalGroup(
            obstacle14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 402, 70, 10));

        obstacle15.setBackground(new java.awt.Color(0, 0, 102));
        obstacle15.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout obstacle15Layout = new javax.swing.GroupLayout(obstacle15);
        obstacle15.setLayout(obstacle15Layout);
        obstacle15Layout.setHorizontalGroup(
            obstacle15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        obstacle15Layout.setVerticalGroup(
            obstacle15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        level.add(obstacle15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 10, 70));

        obstacle16.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle16Layout = new javax.swing.GroupLayout(obstacle16);
        obstacle16.setLayout(obstacle16Layout);
        obstacle16Layout.setHorizontalGroup(
            obstacle16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        obstacle16Layout.setVerticalGroup(
            obstacle16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle16, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 130, 10));

        obstacle17.setBackground(new java.awt.Color(0, 0, 102));
        obstacle17.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout obstacle17Layout = new javax.swing.GroupLayout(obstacle17);
        obstacle17.setLayout(obstacle17Layout);
        obstacle17Layout.setHorizontalGroup(
            obstacle17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        obstacle17Layout.setVerticalGroup(
            obstacle17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        level.add(obstacle17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 10, 160));

        obstacle18.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle18Layout = new javax.swing.GroupLayout(obstacle18);
        obstacle18.setLayout(obstacle18Layout);
        obstacle18Layout.setHorizontalGroup(
            obstacle18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        obstacle18Layout.setVerticalGroup(
            obstacle18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 160, 10));

        obstacle19.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle19Layout = new javax.swing.GroupLayout(obstacle19);
        obstacle19.setLayout(obstacle19Layout);
        obstacle19Layout.setHorizontalGroup(
            obstacle19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        obstacle19Layout.setVerticalGroup(
            obstacle19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        level.add(obstacle19, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, -1, 100));

        obstacle20.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle20Layout = new javax.swing.GroupLayout(obstacle20);
        obstacle20.setLayout(obstacle20Layout);
        obstacle20Layout.setHorizontalGroup(
            obstacle20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        obstacle20Layout.setVerticalGroup(
            obstacle20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle20, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 60, -1));

        obstacle21.setBackground(new java.awt.Color(0, 0, 102));
        obstacle21.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout obstacle21Layout = new javax.swing.GroupLayout(obstacle21);
        obstacle21.setLayout(obstacle21Layout);
        obstacle21Layout.setHorizontalGroup(
            obstacle21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        obstacle21Layout.setVerticalGroup(
            obstacle21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        level.add(obstacle21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 10, 160));

        obstacle22.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle22Layout = new javax.swing.GroupLayout(obstacle22);
        obstacle22.setLayout(obstacle22Layout);
        obstacle22Layout.setHorizontalGroup(
            obstacle22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        obstacle22Layout.setVerticalGroup(
            obstacle22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle22, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        obstacle23.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle23Layout = new javax.swing.GroupLayout(obstacle23);
        obstacle23.setLayout(obstacle23Layout);
        obstacle23Layout.setHorizontalGroup(
            obstacle23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        obstacle23Layout.setVerticalGroup(
            obstacle23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle23, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, -1, -1));

        obstacle24.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle24Layout = new javax.swing.GroupLayout(obstacle24);
        obstacle24.setLayout(obstacle24Layout);
        obstacle24Layout.setHorizontalGroup(
            obstacle24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        obstacle24Layout.setVerticalGroup(
            obstacle24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        level.add(obstacle24, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, 190));

        obstacle25.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle25Layout = new javax.swing.GroupLayout(obstacle25);
        obstacle25.setLayout(obstacle25Layout);
        obstacle25Layout.setHorizontalGroup(
            obstacle25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        obstacle25Layout.setVerticalGroup(
            obstacle25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle25, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, -1, -1));

        obstacle26.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle26Layout = new javax.swing.GroupLayout(obstacle26);
        obstacle26.setLayout(obstacle26Layout);
        obstacle26Layout.setHorizontalGroup(
            obstacle26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        obstacle26Layout.setVerticalGroup(
            obstacle26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        level.add(obstacle26, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, 70));

        obstacle27.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle27Layout = new javax.swing.GroupLayout(obstacle27);
        obstacle27.setLayout(obstacle27Layout);
        obstacle27Layout.setHorizontalGroup(
            obstacle27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        obstacle27Layout.setVerticalGroup(
            obstacle27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle27, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 40, -1));

        obstacle28.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle28Layout = new javax.swing.GroupLayout(obstacle28);
        obstacle28.setLayout(obstacle28Layout);
        obstacle28Layout.setHorizontalGroup(
            obstacle28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        obstacle28Layout.setVerticalGroup(
            obstacle28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        level.add(obstacle28, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, -1, 70));

        obstacle29.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle29Layout = new javax.swing.GroupLayout(obstacle29);
        obstacle29.setLayout(obstacle29Layout);
        obstacle29Layout.setHorizontalGroup(
            obstacle29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        obstacle29Layout.setVerticalGroup(
            obstacle29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle29, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, -1, -1));

        obstacle30.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout obstacle30Layout = new javax.swing.GroupLayout(obstacle30);
        obstacle30.setLayout(obstacle30Layout);
        obstacle30Layout.setHorizontalGroup(
            obstacle30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        obstacle30Layout.setVerticalGroup(
            obstacle30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(obstacle30, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 100, -1));

        door.setBackground(new java.awt.Color(204, 51, 255));

        javax.swing.GroupLayout doorLayout = new javax.swing.GroupLayout(door);
        door.setLayout(doorLayout);
        doorLayout.setHorizontalGroup(
            doorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        doorLayout.setVerticalGroup(
            doorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        level.add(door, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 50, 10));

        background.add(level, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        heart1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/heart.png"))); // NOI18N
        background.add(heart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 500, -1, 30));

        heart2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/heart.png"))); // NOI18N
        background.add(heart2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, -1, 30));

        heart3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/heart.png"))); // NOI18N
        background.add(heart3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, -1, 30));

        score.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        score.setForeground(java.awt.Color.white);
        score.setText("SCORE:");
        background.add(score, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, -1, -1));

        scorevalue.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        scorevalue.setForeground(java.awt.Color.white);
        scorevalue.setText("000000");
        background.add(scorevalue, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 500, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(504, 541));
        setLocationRelativeTo(null);
    }// </editor-fold>                        

   
    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }
    
    // When pacman loses all hearts
    public void end() {
        timer.stop();
  
        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOver.isVisible()) {
                    gameOver.setVisible(false);
                } else {
                    gameOver.setVisible(true);
                }
            }
        }
        );
        timer.start();
    }
    
    // When pacman eats all the orbs
    public void complete() {
        timer.stop();
        
        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (complete.isVisible()) {
                    complete.setVisible(false);
                } else {
                    complete.setVisible(true);
                }
            }
        }
        );
        timer.start();
    }

    public void startGame() {

        // remove start prompt
        start.setVisible(false);
        timer.stop();
        // print ready prompt
        ready.setVisible(true);
        boolean[] left = {false, false, false, false};
        
        
       
        Timer starter = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ready.setVisible(false);

                // Create and print pacman again
                JLabel newPacman = new javax.swing.JLabel();
                newPacman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pacman_right.gif")));
                pacman.setVisible(false);
                level.remove(pacman);
                level.add(newPacman, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 32, 32), 6);
                pacman = newPacman;
                pacman.setVisible(true);
                
                // start animating pacman
                pac.setDir('r');
                pacman.setIcon(pac.getImage());

                // cycle of the game is every 120ms
                timer = new Timer(120, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        
                        
                        // chase for 50 cycles
                        if (scatterTimer > 49) {
                            for(Ghost ghost: ghosts) {
                                ghost.setState("scatter");
                            }
                            chaseTimer += 1;
                           
                        }
                        
                        // scatter for 20 cycles
                        if (chaseTimer > 19) {
                            scatterTimer = 0;
                            chaseTimer = 0;
                            for(Ghost ghost: ghosts) {
                                ghost.setState("chase");
                            }
                        }

                       
                       // red is the chaser
                             if (!left[0]) {
                            if (totalTime < 4) {
                                // (go up 4 times)
                                JLabel newRed = new JLabel();
                                newRed.setIcon(ghosts.get(0).getImage());
                                int red_y = redGhost.getLocation().y -15 ;
                                redGhost.setVisible(false);
                                level.remove(redGhost);
                                level.add(newRed, new org.netbeans.lib.awtextra.AbsoluteConstraints(redGhost.getLocation().x, red_y, 32, 32), 2);
                                redGhost = newRed;
                                redGhost.setVisible(true);
                            } else {
                                ghosts.get(0).setCurrentPos(new Point(210, 150));
                                left[0] = true;
                            }
                        }
                        
                        // blue is the trapper
                        
                        if (!left[1]) {
                            if (33-difficulty <= totalTime && totalTime < 35-difficulty) {
                                // (go right 2 times)
                                JLabel newBlue = new JLabel();
                                newBlue.setIcon(ghosts.get(1).getImage());
                                int blue_x = blueGhost.getLocation().x + 15;
                                blueGhost.setVisible(false);
                                level.remove(blueGhost);
                                level.add(newBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(blue_x, blueGhost.getLocation().y, 32, 32), 2);
                                blueGhost = newBlue;
                                blueGhost.setVisible(true);
                            } else if (35-difficulty <= totalTime && totalTime < 41-difficulty) {
                                // (go up 6 times)
                                JLabel newBlue = new JLabel();
                                newBlue.setIcon(ghosts.get(1).getImage());
                                int blue_y = blueGhost.getLocation().y - 15;
                                blueGhost.setVisible(false);
                                level.remove(blueGhost);
                                level.add(newBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(blueGhost.getLocation().x, blue_y, 32, 32), 2);
                                blueGhost = newBlue;
                                blueGhost.setVisible(true);
                            } else if (41-difficulty <= totalTime) {
                                ghosts.get(1).setCurrentPos(new Point(210, 150));
                                left[1] = true;
                            }
                        }
                        
                        // pink is the random
                     
                        if (!left[2]) {
                            if (81-difficulty <= totalTime && totalTime < 83-difficulty) {
                                // (go left 2 times)
                                JLabel newPink = new JLabel();
                                newPink.setIcon(ghosts.get(2).getImage());
                                int pink_x = pinkGhost.getLocation().x - 15;
                                pinkGhost.setVisible(false);
                                level.remove(pinkGhost);
                                level.add(newPink, new org.netbeans.lib.awtextra.AbsoluteConstraints(pink_x, pinkGhost.getLocation().y, 32, 32), 2);
                                pinkGhost = newPink;
                                pinkGhost.setVisible(true);
                            } else if (83-difficulty <= totalTime && totalTime < 89-difficulty) {
                                // (go up 6 times)
                                JLabel newPink = new JLabel();
                                newPink.setIcon(ghosts.get(2).getImage());
                                int pink_y = pinkGhost.getLocation().y - 15;
                                pinkGhost.setVisible(false);
                                level.remove(pinkGhost);
                                level.add(newPink, new org.netbeans.lib.awtextra.AbsoluteConstraints(pinkGhost.getLocation().x, pink_y, 32, 32), 2);
                                pinkGhost = newPink;
                                pinkGhost.setVisible(true);
                            } else if (89-difficulty <= totalTime) {
                                ghosts.get(2).setCurrentPos(new Point(210, 150));
                                left[2] = true;
                            }
                        }
                            
                        // orange is the scared
                   
                        if (!left[3]) {
                            if (139-difficulty <= totalTime && totalTime < 143-difficulty) {
                                // (go left 4 times)
                                JLabel newOrange = new JLabel();
                                newOrange.setIcon(ghosts.get(3).getImage());
                                int orange_x = orangeGhost.getLocation().x - 15;
                                orangeGhost.setVisible(false);
                                level.remove(orangeGhost);
                                level.add(newOrange, new org.netbeans.lib.awtextra.AbsoluteConstraints(orange_x, orangeGhost.getLocation().y, 32, 32), 2);
                                orangeGhost = newOrange;
                                orangeGhost.setVisible(true);
                            } else if (143-difficulty <= totalTime && totalTime < 147-difficulty) {
                                // (go up 4 times)
                                JLabel newOrange = new JLabel();
                                newOrange.setIcon(ghosts.get(3).getImage());
                                int orange_y = orangeGhost.getLocation().y - 15;
                                orangeGhost.setVisible(false);
                                level.remove(orangeGhost);
                                level.add(newOrange, new org.netbeans.lib.awtextra.AbsoluteConstraints(orangeGhost.getLocation().x, orange_y, 32, 32), 2);
                                orangeGhost = newOrange;
                                orangeGhost.setVisible(true);
                            } else if (147-difficulty <= totalTime) {
                                ghosts.get(3).setCurrentPos(new Point(210, 150));
                                left[3] = true;
                            }
                        }
                        
                        // move all ghosts after their scripted leave
                        for (Ghost ghost: ghosts) {
                            if (left[ghosts.indexOf(ghost)]) {
                                Point move = new Point(0, 0); 
                                // scatter to the corners of the map
                                if (ghost.getState().equals("scatter")) {
                                    switch(ghost.getColor()) {
                                        case 'r':
                                            move = ghost.scatter(map, new Point(450, 0));
                                            break;
                                        case 'b':
                                            move = ghost.scatter(map, new Point(0, 0));
                                            break;
                                        case 'p':
                                            move = ghost.scatter(map, new Point(450, 450));
                                            break;
                                        default:
                                            move = ghost.scatter(map, new Point(0, 450));
                                            break;
                                    }
                                }
                                // chase pacman
                                else if (ghost.getState().equals("chase")){
                                    move = ghost.chase(map, pacman.getLocation());
                                }

                                JLabel newGhost = new JLabel();
                                newGhost.setIcon(ghost.getImage());
                                int new_x = ghost.getCurrentPos().x + move.x;
                                int new_y = ghost.getCurrentPos().y + move.y;
                                switch(ghost.getColor()) {
                                    case 'r':
                                        redGhost.setVisible(false);
                                        level.remove(redGhost);
                                        level.add(newGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(new_x, new_y, 32, 32), 2);
                                        redGhost = newGhost;
                                        redGhost.setVisible(true);
                                        break;
                                    case 'b':
                                        blueGhost.setVisible(false);
                                        level.remove(blueGhost);
                                        level.add(newGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(new_x, new_y, 32, 32), 2);
                                        blueGhost = newGhost;
                                        blueGhost.setVisible(true);
                                        break;
                                    case 'p':
                                        pinkGhost.setVisible(false);
                                        level.remove(pinkGhost);
                                        level.add(newGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(new_x, new_y, 32, 32), 2);
                                        pinkGhost = newGhost;
                                        pinkGhost.setVisible(true);
                                        break;
                                    default:
                                        orangeGhost.setVisible(false);
                                        level.remove(orangeGhost);
                                        level.add(newGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(new_x, new_y, 32, 32), 2);
                                        orangeGhost = newGhost;
                                        orangeGhost.setVisible(true);
                                        break;
                                }

                                ghost.setCurrentPos(new Point(new_x, new_y));
                            }
                           
                        }

                        
                        
                        
                        // process input
                        switch (key) {
                            case KeyEvent.VK_LEFT:
                                {
                                    int new_x = pacman.getLocation().x - 15;
                                    if (new_x/15 > 1 && map[(pacman.getLocation().y/15)-2][(new_x/15)-2] == 1) {
                                        pac.setMove(new Point(-15, 0));
                                        pac.setDir('l');
                                    }       break;
                                }
                            case KeyEvent.VK_RIGHT:
                                {
                                    int new_x = pacman.getLocation().x + 15;
                                    if (new_x/15 < 29 && map[(pacman.getLocation().y/15)-2][(new_x/15)-2] == 1) {
                                        pac.setMove(new Point(15, 0));
                                        pac.setDir('r');
                                    }       break;
                                }
                            case KeyEvent.VK_UP:
                                {
                                    int new_y = pacman.getLocation().y - 15;
                                    if (new_y/15 > 1 && map[(new_y/15)-2][(pacman.getLocation().x/15)-2] == 1) {
                                        pac.setMove(new Point(0, -15));
                                        pac.setDir('u');
                                    }       break;
                                }
                            case KeyEvent.VK_DOWN:
                                {
                                    int new_y = pacman.getLocation().y + 15;
                                    if (new_y/15 < 29 && map[(new_y/15)-2][(pacman.getLocation().x/15)-2] == 1) {
                                        pac.setMove(new Point(0, 15));
                                        pac.setDir('d');
                                    }       break;
                                }
                            default:
                                break;
                        }

                        // move pacman accordingly
                        int new_x = pacman.getLocation().x + pac.getMove().x;
                        int new_y = pacman.getLocation().y + pac.getMove().y;
                        if (new_x/15 > 1 && new_x/15 < 29 && new_y/15 > 1 && new_y/15 < 29
                                && map[(new_y/15)-2][(new_x/15)-2]==1) {
                            pac.setLastPos(pacman.getLocation());
                            JLabel newPacman = new JLabel();
                            newPacman.setIcon(pac.getImage());
                            pacman.setVisible(false);
                            level.remove(pacman);
                            level.add(newPacman, new org.netbeans.lib.awtextra.AbsoluteConstraints(new_x, new_y, 32, 32), 6);
                            pacman = newPacman;
                            pacman.setVisible(true);             
                            pac.setCurrentPos(new Point(new_x, new_y));
                            // orb eating
                            for(JLabel orb: orbs) {
                                int orb_x = orb.getLocation().x;
                                int orb_y = orb.getLocation().y;
                                // if the orb is where pacman will be
                                if (orb_x==new_x && orb_y==new_y) {
                                    // we check the type of orb
                                    if (orb_x==30 && orb_y==30
                                            || orb_x==420 && orb_y==30
                                            || orb_x==420 && orb_y==420
                                            || orb_x==30 && orb_y==420)
                                        points += 50;
                                    else
                                        points += 10;

                                    level.remove(orb);
                                    orbs.remove(orb);
                                    break;
                                }
                            }
                            //:(
                            scorevalue.setText(String.format("%1$06d", points));

                        }
                        
                        scatterTimer += 1;
                        totalTime += 1;
                        
                        // check if pacman is caught
                        // get possible caught points
                        ArrayList<Point> caught = new ArrayList<>();
                        caught.add(pac.getCurrentPos());
                        caught.add(new Point(pac.getCurrentPos().x-15, pac.getCurrentPos().y));
                        caught.add(new Point(pac.getCurrentPos().x+15, pac.getCurrentPos().y));
                        caught.add(new Point(pac.getCurrentPos().x, pac.getCurrentPos().y-15));
                        caught.add(new Point(pac.getCurrentPos().x, pac.getCurrentPos().y+15));
                        
                        boolean again = false;
                        for (Ghost ghost: ghosts){
                            // check if they're in the same position
                            for (Point pos: caught) {
                                if (ghost.getCurrentPos().equals(pos)){
                                again = true;
                                break;
                                }
                            }
                            if (again) break;
                        }
                        
                        if (again) {
                            
                            // remove a heart
                            boolean gameEnded = true;
                            for (Component c: background.getComponents()) {
                                if (c.getLocation().x==100 && c.getLocation().y==500) {
                                    c.setVisible(false);
                                    background.remove(c);
                                    gameEnded = false;
                                    break;
                                }
                                if (c.getLocation().x==60 && c.getLocation().y==500) {
                                    c.setVisible(false);
                                    background.remove(c);
                                    gameEnded = false;
                                    break;
                                }
                                if (c.getLocation().x==20 && c.getLocation().y==500) {
                                    c.setVisible(false);
                                    background.remove(c);
                                    gameEnded = false;
                                    break;
                                }
                            }
                            
                            if (gameEnded) {
                              end();
                            } else {
                              
                                key = KeyEvent.VK_RIGHT;

                                JLabel newPacman = new javax.swing.JLabel();
                                newPacman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pacman.png")));
                                pacman.setVisible(false);
                                level.remove(pacman);
                                level.add(newPacman, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 32, 32), 6);
                                pacman = newPacman;
                                pacman.setVisible(true);
                                pac.setCurrentPos(new Point(210, 360));
                                pac.setLastPos(new Point(210, 360));

                              
                                for(Ghost ghost: ghosts) {
                                    JLabel newGhost = new javax.swing.JLabel();
                                    newGhost.setIcon(ghost.getImage());
                                    switch(ghost.getColor()) {
                                        case 'r':
                                            redGhost.setVisible(false);
                                            level.remove(redGhost);
                                            level.add(newGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 32, 32));
                                            redGhost = newGhost;
                                            redGhost.setVisible(true);
                                            ghost.setCurrentPos(new Point(210, 150));
                                            ghost.setLastPos(new Point(210, 150));
                                            break;
                                        case 'b':
                                            blueGhost.setVisible(false);
                                            level.remove(blueGhost);
                                            level.add(newGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 32, 32));
                                            blueGhost = newGhost;
                                            blueGhost.setVisible(true);
                                            ghost.setCurrentPos(new Point(210, 150));
                                            ghost.setLastPos(new Point(210, 150));
                                            break;
                                        case 'p':
                                            pinkGhost.setVisible(false);
                                            level.remove(pinkGhost);
                                            level.add(newGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 32, 32));
                                            pinkGhost = newGhost;
                                            pinkGhost.setVisible(true);
                                            ghost.setCurrentPos(new Point(210, 150));
                                            ghost.setLastPos(new Point(210, 150));
                                            break;
                                        default:
                                            orangeGhost.setVisible(false);
                                            level.remove(orangeGhost);
                                            level.add(newGhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 32, 32));
                                            orangeGhost = newGhost;
                                            orangeGhost.setVisible(true);
                                            ghost.setCurrentPos(new Point(210, 150));
                                            ghost.setLastPos(new Point(210, 150));
                                            break;
                                    }

                                }
                                totalTime = 0;
                                scatterTimer = 0;
                                chaseTimer = 0;
                               
                                startGame();
                            }
                        }
                        
                        // check if pacman won the game
                        System.out.println(orbs.size());
                        if (orbs.isEmpty()) {
                            complete();
                        }
                    }
                }
                );
                timer.start();
            }
        });
        starter.setRepeats(false);
        starter.start();
    }
    
    class myAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){
            int pressed = e.getKeyCode();
            if (gameStarted) {
                key = pressed;
            }
            else {
                if (pressed==KeyEvent.VK_SPACE) {
                    gameStarted = true;
                    startGame();
                }
            }
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel background;
    private javax.swing.JLabel blueGhost;
    private javax.swing.JLabel complete;
    private javax.swing.JPanel door;
    private javax.swing.JLabel gameOver;
    private javax.swing.JLabel heart1;
    private javax.swing.JLabel heart2;
    private javax.swing.JLabel heart3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel level;
    private javax.swing.JPanel obstacle1;
    private javax.swing.JPanel obstacle10;
    private javax.swing.JPanel obstacle11;
    private javax.swing.JPanel obstacle12;
    private javax.swing.JPanel obstacle13;
    private javax.swing.JPanel obstacle14;
    private javax.swing.JPanel obstacle15;
    private javax.swing.JPanel obstacle16;
    private javax.swing.JPanel obstacle17;
    private javax.swing.JPanel obstacle18;
    private javax.swing.JPanel obstacle19;
    private javax.swing.JPanel obstacle2;
    private javax.swing.JPanel obstacle20;
    private javax.swing.JPanel obstacle21;
    private javax.swing.JPanel obstacle22;
    private javax.swing.JPanel obstacle23;
    private javax.swing.JPanel obstacle24;
    private javax.swing.JPanel obstacle25;
    private javax.swing.JPanel obstacle26;
    private javax.swing.JPanel obstacle27;
    private javax.swing.JPanel obstacle28;
    private javax.swing.JPanel obstacle29;
    private javax.swing.JPanel obstacle3;
    private javax.swing.JPanel obstacle30;
    private javax.swing.JPanel obstacle4;
    private javax.swing.JPanel obstacle5;
    private javax.swing.JPanel obstacle6;
    private javax.swing.JPanel obstacle7;
    private javax.swing.JPanel obstacle8;
    private javax.swing.JPanel obstacle9;
    private javax.swing.JLabel orangeGhost;
    private javax.swing.JLabel pinkGhost;
    private javax.swing.JLabel ready;
    private javax.swing.JLabel redGhost;
    private javax.swing.JLabel score;
    private javax.swing.JLabel scorevalue;
    private javax.swing.JLabel start;
    // End of variables declaration                   


}
