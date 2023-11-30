package Domineering;

import javax.swing.*;

public class DomineeringButton extends JButton {

    private int row;
    private int col;
    private int state;

    public DomineeringButton(int row, int col, int state) {
        this.row = row;
        this.col = col;
        this.state = state;
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public int getState(){ return state;}
    public void setState0(){state = 0;}
    public void setStateH(){state = 1;}
    public void setStateP(){state = -1;}
}
