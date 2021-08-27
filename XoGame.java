import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.*;

class XoGame {
    JFrame window = new JFrame("XoGame");
    JComponent canvas = new JComponent() {

    };
    int gameWidth = 6,gameHeight = 6;
    int windowWidth = 75*gameWidth,windowHeight = 75*gameHeight;
    char BoardArray[][];
    Graphics2D g2d;
    char player = 'X';
    boolean iswin = false;
    XoGame() {
        BoardArray = new char[gameWidth][gameHeight];
        window.setBounds(100,100,windowWidth + 1,windowHeight + 31);
        window.setResizable(false);
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setBounds(0,0,windowWidth + 10,windowHeight + 10);
        window.add(canvas);
        window.setVisible(true);   

        window.getContentPane().setBackground(Color.white);
        g2d = (Graphics2D) canvas.getGraphics();
        
        
        
        window.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Point pos = mousePostitionToArray(e.getX(), e.getY() - 31);
                if (BoardArray[pos.x][pos.y] == 0 && !iswin) {
                    BoardArray[pos.x][pos.y]  = player;
                    if (checkwin()) {
                        updateMap();
                        JOptionPane.showMessageDialog(null, player + " Win Game is End Restart Game to Play Again");
                        iswin = true;
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
    private boolean checkwin() {
        //เคยเล่นในเว็ปที่เป็นแบบ n x n กติกาการเล่นคือถ้าติดกันแค่สามตัวจาก n x n คือชนะ
        for(int x=0; x<gameWidth; x++) {
            for(int y=0; y<gameHeight; y++ ) {
                if (x-1 >= 0 && x+1 < gameWidth && y-1 >= 0 && y+1 < gameHeight) {
                    if (BoardArray[x][y] != 0)
                    if (BoardArray[x-1][y] ==  BoardArray[x][y] && BoardArray[x][y] == BoardArray[x+1][y] ||
                    BoardArray[x][y - 1] ==  BoardArray[x][y] && BoardArray[x][y] == BoardArray[x][y + 1] ||
                    BoardArray[x-1][y-1] ==  BoardArray[x][y] && BoardArray[x][y] == BoardArray[x+1][y+1] ||
                    BoardArray[x+1][y-1] ==  BoardArray[x][y] && BoardArray[x][y] == BoardArray[x-1][y+1] 
                    ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private void updateMap() {
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
    
    private Point mousePostitionToArray(int mousex,int mousey) {
        return new Point(mousex/(windowWidth/gameWidth),mousey/(windowHeight/gameHeight));
    }
    public static void main(String[] args) {
        new XoGame();
    }
}
