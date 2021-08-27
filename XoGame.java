import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.*;

class XoGame {
    JFrame window = new JFrame("XoGame");
    int gameWidth = 6,gameHeight = 6;
    int windowWidth = 75*gameWidth,windowHeight = 75*gameHeight;
    char BoardArray[][];
    
    char player = 'X';
    boolean iswin = false;
    JComponent canvas = new JComponent() {
        public void paint(Graphics g){  
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(2));
            g2d.setFont(new Font ("TimesRoman", Font.PLAIN, 36 ));
            int blockWidth = (windowWidth/gameWidth);
            int blockHeight = (windowHeight/gameHeight);
            //System.out.println(blockWidth + "," + blockHeight);
            for(int x=0; x<gameWidth; x++) {
                for(int y=0; y<gameHeight; y++ ) {
                    g2d.setColor(Color.white);
                    g2d.fillRect(x*blockWidth, y*blockHeight, blockWidth, blockHeight);
                    g2d.setColor(Color.black);
                    g2d.drawRect(x*blockWidth, y*blockHeight, blockWidth, blockHeight);
                    g2d.drawString(BoardArray[x][y] + "",25 + (x*blockWidth), 55 + (y*blockHeight));   
                }
            }
        }
    };
    XoGame(int wx,int wy,int closed,Point windowlocation) {
        this.gameWidth = wx;
        this.gameHeight = wy;
        this.windowWidth = 75*this.gameWidth;
        this.windowHeight = 75*this.gameHeight;
        BoardArray = new char[gameWidth][gameHeight];
        window.setResizable(false);
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(windowlocation);
        window.setSize(windowWidth + 1,windowHeight + 31);
        canvas.setBounds(0,0,windowWidth + 10,windowHeight + 10);
        window.add(canvas);
        window.setVisible(true);   

        window.getContentPane().setBackground(Color.white);
        
        
        
        window.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Point pos = mousePostitionToArray(e.getX(), e.getY() - 31);
                if (BoardArray[pos.x][pos.y] == 0 && !iswin) {
                    BoardArray[pos.x][pos.y]  = player;
                    if (checkwin(pos.x,pos.y,closed - 1)) {
                        updateMap();
                        if (JOptionPane.showConfirmDialog (null, player + " Win Game Play Again","Game End",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            System.out.println("Yes");    
                            clearBoard();
                            updateMap();
                        } else {
                            System.out.println("No");
                            System.exit(0);
                        }
                    }
                    if(player == 'X') {
                        player = 'O';
                    } else {
                        player = 'X';
                    }
                } 
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                updateMap();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });
        updateMap();
    }
    private void clearBoard() {
        for (int x = 0;x < gameWidth;x++) {
            for (int y = 0;y < gameHeight;y++) {
                BoardArray[x][y] = 0;
            }
        }
    }
    private int count(int fx,int fy,int ox,int oy,int counting) {
        if (fx+ox >= 0 && fx+ox < gameWidth && fy+oy >= 0 && fy+oy < gameHeight ) {
            if (BoardArray[fx][fy] == BoardArray[fx+ox][fy+oy]) {
                return count(fx+ox,fy+oy,ox,oy,counting+1);
            }
        }
        return counting;
    }
    private boolean checkwin(int sx,int sy,int closecount) {
        if (count(sx,sy,-1,0,0)  + count(sx,sy,1,0,0)  >= (closecount) ||
            count(sx,sy,0,-1,0)  + count(sx,sy,0,1,0)  >= (closecount) ||
            count(sx,sy,-1,-1,0) + count(sx,sy,1,1,0)  >= (closecount) ||
            count(sx,sy,1,-1,0)  + count(sx,sy,-1,1,0) >= (closecount) ) {
            return true;
        }
        return false;
    }
    public void updateMap() {
        canvas.repaint();
    }
    
    private Point mousePostitionToArray(int mousex,int mousey) {
        return new Point(mousex/(windowWidth/gameWidth),mousey/(windowHeight/gameHeight));
    }
}
