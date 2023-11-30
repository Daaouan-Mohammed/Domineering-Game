package Domineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DomineeringGUI extends JFrame implements ActionListener {
    JComboBox<Integer> comboSize;
    JComboBox<String> comboplayer1;
    JComboBox<String> comboplayer2;
    JButton btnplay;

    DomineeringGUI() {
        Integer[] size = {2, 3, 4, 5, 6, 7, 8};
        String[] player1 = {"Human"};
        String[] player2 = {"Human", "Program: Level1", "Program: Level2", "Program: Level3"};

        comboSize = new JComboBox<>(size);
        comboSize.addActionListener(this);

        comboplayer1 = new JComboBox<>(player1);
        comboplayer1.addActionListener(this);

        comboplayer2 = new JComboBox<>(player2);
        comboplayer2.addActionListener(this);

        btnplay = new JButton();
        btnplay.addActionListener(this);
        btnplay.setText("Play");
        btnplay.setFocusable(false);

        JPanel paneltop = new JPanel(new BorderLayout());
        JPanel panelimg1 = new JPanel();
        JPanel panelimg2 = new JPanel();
        JPanel paneltitle = new JPanel();

        ImageIcon img0 = new ImageIcon("Domineering.png");
        Image image = img0.getImage();
        Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(scaledImage);

        JLabel img1 = new JLabel();
        img1.setIcon(img);
        panelimg1.add(img1);
        panelimg1.setOpaque(false);

        JLabel img2 = new JLabel();
        img2.setIcon(img);
        panelimg2.add(img2);
        panelimg2.setOpaque(false);

        JLabel title = new JLabel();
        title.setText("Domineering Game");
        title.setForeground(Color.black);
        title.setFont(new Font("MV Boli", Font.PLAIN, 40));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        paneltitle.add(title);
        paneltitle.setOpaque(false);

        paneltop.add(panelimg1, BorderLayout.WEST);
        paneltop.add(paneltitle, BorderLayout.CENTER);
        paneltop.add(panelimg2, BorderLayout.EAST);
        ///////////////Panel in the buttom/////////////////

        JPanel panelbuttom = new JPanel();
        panelbuttom.setSize(300,300);
        JPanel panelgrid =new JPanel();
        panelgrid.setPreferredSize(new Dimension(250,200));
        panelgrid.setBackground(Color.lightGray);
        panelgrid.setLayout(new GridLayout(4,2,10,15));// Use a layout manager

        JLabel labelSize = new JLabel("Size:");
        JLabel labelPlayer1 = new JLabel("Player 1:");
        JLabel labelPlayer2 = new JLabel("Player 2:");

        panelgrid.add(labelSize);
        panelgrid.add(comboSize);
        panelgrid.add(labelPlayer1);
        panelgrid.add(comboplayer1);
        panelgrid.add(labelPlayer2);
        panelgrid.add(comboplayer2);

        // Make the button span two columns
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridwidth = 2;
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        panelgrid.add(btnplay, buttonConstraints);

        panelgrid.setOpaque(false);
        panelbuttom.add(panelgrid);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(paneltop, BorderLayout.NORTH);
        this.add(panelbuttom, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(null); // Center the frame on the screen
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboSize) {
            System.out.println(comboSize.getSelectedItem());
        }
        if (e.getSource() == comboplayer1) {
            System.out.println(comboplayer1.getSelectedItem());
        }
        if (e.getSource() == comboplayer2) {
            System.out.println(comboplayer2.getSelectedItem());
        }
        if (e.getSource() == btnplay) {
            int depth=0;
            int size= (int) comboSize.getSelectedItem();

            if(comboplayer2.getSelectedIndex()==0) {
                //playGameP2P
                depth=4;
            }
            else if (comboplayer2.getSelectedIndex()==1) {
                //playGame
                depth=1;
            }
            else if (comboplayer2.getSelectedIndex()==2) {
                //playGame
                depth=2;
            }
            else {
                //playGame
                depth=4;
            };

            this.dispose();
            DomineeringGrid DominneeringGrid =new DomineeringGrid(size);
        }
    }


}