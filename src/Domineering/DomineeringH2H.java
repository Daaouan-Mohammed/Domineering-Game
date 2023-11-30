package Domineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DomineeringH2H extends JFrame implements ActionListener {
    boolean player =true;
    private JLabel status;
    private int gridSize;
    JButton btnSauvgarde;
    JButton btnRetour;
    JButton button;
    private DomineeringButton buttons[][];
    public int state = 0;
    DomineeringH2H(int size){
        this.gridSize = size;
        this.buttons = new DomineeringButton[gridSize][gridSize];
//////////////Paneltop////////////////////////////////
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

        ///////////////////////panel button////////////////////////////
        JPanel panelbuttom = new JPanel(new BorderLayout());
        JPanel panelleftbuttom=new JPanel();
        JPanel panelrightbuttom=new JPanel();

        /////panelrightbuttom///////
        panelrightbuttom.setPreferredSize(new Dimension(250,200));
        panelrightbuttom.setLayout(new GridLayout(2,1,0,30));
        JPanel paneltopbuttom=new JPanel();
        JPanel panelbuttombuttom=new JPanel();

        /////paneltopbuttom///////
        paneltopbuttom.setPreferredSize(new Dimension(250,200));
        paneltopbuttom.setLayout(new GridLayout(5,2));
        JLabel player1 = new JLabel("player1:");
        JLabel player11 =new JLabel();
        JLabel maxmove1=new JLabel("Maximal Moves ");
        JLabel maxmove11=new JLabel();
        JLabel possiblemove1=new JLabel("Possible Moves ");
        JLabel possiblemove11=new JLabel();
        JLabel safemove1=new JLabel("Safe Moves ");
        JLabel safemove11=new JLabel();
        JButton help11= new JButton("Ask for Help (2)");
        JLabel help1=new JLabel();

        paneltopbuttom.add(player1);
        paneltopbuttom.add(player11);
        paneltopbuttom.add(maxmove1);
        paneltopbuttom.add(maxmove11);
        paneltopbuttom.add(possiblemove1);
        paneltopbuttom.add(possiblemove11);
        paneltopbuttom.add(safemove1);
        paneltopbuttom.add(safemove11);
        paneltopbuttom.add(help11);
        paneltopbuttom.add(help1);



        /////panelbuttombuttom///////
        panelbuttombuttom.setPreferredSize(new Dimension(250,200));
        panelbuttombuttom.setLayout(new GridLayout(5,2));
        JLabel player2 = new JLabel("player2:");
        JLabel player22 =new JLabel();
        JLabel maxmove2=new JLabel("Maximal Moves ");
        JLabel maxmove22=new JLabel();
        JLabel possiblemove2=new JLabel("Possible Moves ");
        JLabel possiblemove22=new JLabel();
        JLabel safemove2=new JLabel("Safe Moves ");
        JLabel safemove22=new JLabel();
        JButton help22= new JButton("Ask for Help (2)");
        JLabel help2=new JLabel();

        panelbuttombuttom.add(player2);
        panelbuttombuttom.add(player22);
        panelbuttombuttom.add(maxmove2);
        panelbuttombuttom.add(maxmove22);
        panelbuttombuttom.add(possiblemove2);
        panelbuttombuttom.add(possiblemove22);
        panelbuttombuttom.add(safemove2);
        panelbuttombuttom.add(safemove22);
        panelbuttombuttom.add(help22);
        panelbuttombuttom.add(help2);


        panelrightbuttom.add(paneltopbuttom);
        panelrightbuttom.add(panelbuttombuttom);

        /////panelleftbuttom//////
        JPanel gridPanel = new JPanel();
        gridPanel.setPreferredSize(new Dimension(300,300));
        gridPanel.setLayout(new GridLayout(gridSize,gridSize));
        //ButtonHandler buttonHandler = new ButtonHandler();

        System.out.println(gridSize);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                buttons[i][j] = new DomineeringButton(i, j, state);
                gridPanel.add(buttons[i][j]);
                buttons[i][j].addActionListener(new ButtonListener());
            }
        }

//        for (int i = 0; i < gridSize; i++) {
//            for (int j = 0; j < gridSize; j++) {
//                button = new JButton(i + " " + j );
//                button.addActionListener(this);
//                gridPanel.add(button);
//            }
//        }
        status = new JLabel(player + "'s turn");
        panelleftbuttom.add(status);
        panelleftbuttom.add(gridPanel);
        /////////panelsouth/////////////
        JPanel panelsouth =new JPanel();
        panelsouth.setLayout(new FlowLayout(FlowLayout.CENTER));

        btnRetour=new JButton("Retour");
        btnRetour.addActionListener(this);;
        btnRetour.setFocusable(false);

        btnSauvgarde=new JButton("Sauvgarder");
        btnSauvgarde.addActionListener(this);;
        btnSauvgarde.setFocusable(false);


        panelsouth.add(btnRetour);
        panelsouth.add(btnSauvgarde);


        panelbuttom.add(panelrightbuttom,BorderLayout.EAST);
        panelbuttom.add(panelleftbuttom,BorderLayout.CENTER);
        panelbuttom.add(panelsouth,BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(paneltop, BorderLayout.NORTH);
        this.add(panelbuttom, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null); // Center the frame on the screen
        this.setVisible(true);
    }

//    private void updateUI(Position newPosition) {
//        if (newPosition instanceof DomineeringPosition) {
//            DomineeringPosition domPosition = (DomineeringPosition) newPosition;
//            int rows = domPosition.board[0].length;
//            int columns = domPosition.board[1].length;
//
//            for (int i = 0; i < rows; ++i) {
//                for (int j = 0; j < columns; ++j) {
//                    int value = domPosition.board[i][j];
//
//                    // Set background color based on the value
//                    if (value == 1) {
//                        buttons[i][j].setBackground(Color.GREEN);  // Adjust color as needed
//                    } else if (value == -1) {
//                        buttons[i][j].setBackground(Color.RED);    // Adjust color as needed
//                    } else {
//                        buttons[i][j].setBackground(Color.WHITE);  // Adjust color as needed
//                    }
//                }
//            }
//        }
//    }
public boolean isValidMove(int row1, int col1, boolean player) {
    int row2 = row1;
    int col2 = col1;

    if (!player) {
        col2++;
        return col2 < buttons[row2].length && buttons[row1][col1].getState() == 0 && buttons[row2][col2].getState() == 0;
    } else {
        row2++;
        return row2 < buttons.length && buttons[row1][col1].getState() == 0 && buttons[row2][col2].getState() == 0;
    }
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSauvgarde) {
            System.out.println(btnSauvgarde.getText());
        }
        else if (e.getSource() == btnRetour) {
            System.out.println(btnRetour.getText());
            this.dispose();
            new DomineeringGUI();
        }

    }
    private void closeCurrentWindowAndOpenNewOne() {
        this.dispose(); // Close the current window
        new DomineeringGUI(); // Open the new window (replace YourNewWindow with your actual class)
    }

public class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        int a;
        int b;
        DomineeringButton clicked = (DomineeringButton) e.getSource();
        a = clicked.getRow();
        b = clicked.getCol();

        if (player && isValidMove(a, b, true) && !gameOver(true)) {
            buttons[a][b].setBackground(Color.GREEN);
            buttons[a + 1][b].setBackground(Color.GREEN);
            buttons[a][b].setStateH();
            buttons[a + 1][b].setStateH();
            player = false;
            if (gameOver(false)) {
                JOptionPane.showMessageDialog(
                        DomineeringH2H.this,
                        "Player 1 wins!",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE
                );
                closeCurrentWindowAndOpenNewOne();
            } else {
                status.setText(player + "'s turn");
            }
        } else if (!player && isValidMove(a, b, false) && !gameOver(false)) {
            buttons[a][b].setBackground(Color.RED);
            buttons[a][b + 1].setBackground(Color.RED);
            buttons[a][b].setStateP();
            buttons[a][b + 1].setStateP();
            player = true;
            if (gameOver(true)) {
                JOptionPane.showMessageDialog(
                        DomineeringH2H.this,
                        "Player 2 wins!",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE
                );
                closeCurrentWindowAndOpenNewOne();
            } else {
                status.setText(player + "'s turn");
            }
        } else {
            status.setText("Invalid move!\n" + "Try again " + player);
        }
    }
}
    public boolean gameOver(boolean player) {
        int row = 0;
        boolean foundMove = false;
        while (row < buttons.length && !foundMove) {
            int col = 0;
            while (col < buttons[row].length & !foundMove) {
                foundMove = isValidMove(row, col, player);
                col++;
            }
            row++;
        }
        return !foundMove;
    }
}
