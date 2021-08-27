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
        window.setBounds(100,100,400,200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(null);
        l1.setBounds(10,10,200,25);
        l2.setBounds(10,40,200,25);
        l3.setBounds(10,70,200,25);
        in1.setBounds(190,10,200,25);
        in2.setBounds(190,40,200,25);
        in3.setBounds(190,70,200,25);
        bn1.setBounds(240,110,150,30);
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
    public static void main(String args[]) {
        new Main();
    }
}
