

package Domineering;

public class DomineeringPosition extends Position {
    public static final int EMPTY = 0;
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = -1;
    public int[][] board;

    public DomineeringPosition(int r, int c) {

        this.board = new int[r][c];
        for(int i = 0; i < r; ++i) {
            for(int j = 0; j < c; ++j) {
                this.board[i][j] = EMPTY;
            }
        }
    }

    public DomineeringPosition(DomineeringPosition pos) {
        int rows = pos.board[0].length;
        int columns = pos.board[1].length;
        this.board = new int[rows][columns];

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < columns; ++j) {
                this.board[i][j] = pos.board[i][j];
            }
        }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[\n");

        for(int i = 0; i < this.board.length; ++i) {
            sb.append("  ");

            for(int j = 0; j < this.board[0].length; ++j) {
                sb.append(this.board[i][j]);
                if (j < this.board[0].length - 1) {
                    sb.append(", ");
                }
            }

            sb.append("\n");
        }

        sb.append("]");
        return sb.toString();
    }
}
