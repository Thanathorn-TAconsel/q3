import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Main {
    JFrame window = new JFrame("Xo Game");
    JLabel l1 = new JLabel("Game Width (Block)");
    JLabel l2 = new JLabel("Game Height (Block)");
    JLabel l3 = new JLabel("closed block to win");
    JTextField in1 = new JTextField("4");
    JTextField in2 = new JTextField("4");
    JTextField in3 = new JTextField("3");
    JButton bn1 = new JButton("Start");
    Main() {
        window.setBounds(100,100,350,220);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(null);
        l1.setBounds(10,10,150,30);
        l2.setBounds(10,50,150,30);
        l3.setBounds(10,90,150,30);
        in1.setBounds(190,10,150,30);
        in2.setBounds(190,50,150,30);
        in3.setBounds(190,90,150,30);
        bn1.setBounds(240,130,100,30);
        window.add(l1);
        window.add(l2);
        window.add(l3);
        window.add(in1);
        window.add(in2);
        window.add(in3);
        window.add(bn1);
        window.setVisible(true);
        bn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XoGame xogame = new XoGame(Integer.parseInt(in1.getText()),Integer.parseInt(in2.getText()),Integer.parseInt(in3.getText()),window.getLocation());
                    window.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            }
            
        });
       
    }
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new Main();
    }
}
