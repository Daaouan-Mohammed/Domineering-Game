package Domineering;

import java.util.Scanner;

public class Domineering extends GameSearch{

    public Domineering() {

    }

    public boolean wonPosition(Position p, boolean player) {
        DomineeringPosition pos = (DomineeringPosition)p;
        return this.Wincheck(pos, player);
    }

    public boolean Wincheck(Position p, boolean player) {
        DomineeringPosition pos = (DomineeringPosition)p;
        int rows = pos.board[0].length;
        int columns = pos.board[1].length;
        int row;
        int col;
        if (player) {
            for(row = 0; row < rows; ++row) {
                for(col = 0; col < columns - 1; ++col) {
                    if (pos.board[row][col] == 0 && pos.board[row][col + 1] == 0) {
                        return false;
                    }
                }
            }
        } else {
            for(row = 0; row < rows - 1; ++row) {
                for(col = 0; col < columns; ++col) {
                    if (pos.board[row][col] == 0 && pos.board[row + 1][col] == 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean drawnPosition(Position p) {
        return false;
    }

    private int MaximalMoves(DomineeringPosition pos, boolean player) {
        int rows = pos.board[0].length;
        int columns = pos.board[1].length;
        int count = 0;
        int row;
        int col;
        if (player) {
            for(row = 0; row < rows; ++row) {
                for(col = 0; col < columns - 1; ++col) {
                    if (pos.board[row][col] == 0 && pos.board[row][col + 1] == 0) {
                        ++count;
                    }
                }
            }
        } else {
            for(row = 0; row < rows - 1; ++row) {
                for(col = 0; col < columns; ++col) {
                    if (pos.board[row][col] == 0 && pos.board[row + 1][col] == 0) {
                        ++count;
                    }
                }
            }
        }

        return count;
    }

    public float positionEvaluation(Position p, boolean player) {
        DomineeringPosition position = (DomineeringPosition)p;
        float ret = 0;
        int countPlayerMoves = this.MaximalMoves(position, player);
        int countopponentMoves = this.MaximalMoves(position, !player);
        ret = countPlayerMoves - countopponentMoves;
        return ret;
    }

    public void printPosition(Position p) {
        System.out.println("Board position:");
        DomineeringPosition pos = (DomineeringPosition)p;
        int rows = pos.board[0].length;
        int columns = pos.board[1].length;

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < columns; ++j) {
                if (pos.board[i][j] == 1) {
                    System.out.print("H");
                } else if (pos.board[i][j] == -1) {
                    System.out.print("P");
                } else {
                    System.out.print("0");
                }
            }

            System.out.println();
        }

    }

    private boolean islegalMove(int i, int j, boolean player, DomineeringPosition pos) {
        int dominoValue = player ? 1 : -1;
        if (dominoValue == -1 && i < pos.board[0].length - 1 && pos.board[i][j] == 0 && pos.board[i + 1][j] == 0) {
            return true;
        } else {
            return dominoValue == 1 && i < pos.board[1].length - 1 && pos.board[i][j] == 0 && pos.board[i][j + 1] == 0;
        }
    }

    public Position[] possiblePositions(Position p, boolean player) {
        DomineeringPosition position = (DomineeringPosition)p;
        int count = 0;
        Position[] ret = new Position[1000];
        int row;
        int col;
        DomineeringPosition pos2;
        if (player) {
            for(row = 0; row < position.board.length - 1; ++row) {
                for(col = 0; col < position.board[0].length; ++col) {
                    if (position.board[row][col] == 0 && position.board[row + 1][col] == 0) {
                        pos2 = new DomineeringPosition(position);
                        pos2.board[row][col] = 1;
                        pos2.board[row + 1][col] = 1;
                        ret[count++] = pos2;
                    }
                }
            }
        } else {
            for(row = 0; row < position.board.length; ++row) {
                for(col = 0; col < position.board[0].length - 1; ++col) {
                    if (position.board[row][col] == 0 && position.board[row][col + 1] == 0) {
                        pos2 = new DomineeringPosition(position);
                        pos2.board[row][col] = -1;
                        pos2.board[row][col + 1] = -1;
                        ret[count++] = pos2;
                    }
                }
            }
        }

        if (count == 0) {
            return null;
        } else {
            Position[] result = new Position[count];
            System.arraycopy(ret, 0, result, 0, count);
            return result;
        }
    }

    public Position makeMove(Position p, boolean player, Move move) {
        DomineeringPosition position = (DomineeringPosition)p;
        DomineeringMove domMove = (DomineeringMove)move;
        DomineeringPosition newPosition = new DomineeringPosition(position);
        if (player) {
            newPosition.board[domMove.row + 1][domMove.col] = 1;
            newPosition.board[domMove.row][domMove.col] = 1;
        } else {
            newPosition.board[domMove.row][domMove.col] = -1;
            newPosition.board[domMove.row][domMove.col + 1] = -1;
        }

        return newPosition;
    }

    public boolean reachedMaxDepth(Position p, int depth) {
        boolean ret = false;
        if (depth >= 4) {
            return true;
        } else {
            if (this.wonPosition(p, false)) {
                ret = true;
            } else if (this.wonPosition(p, true)) {
                ret = true;
            } else if (this.drawnPosition(p)) {
                ret = true;
            }

            return ret;
        }
    }

    public Move createMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer la position de la tête du domino comme (e.g., 0 1):");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        DomineeringMove move = new DomineeringMove(row, col);
        return move;
    }


    public static void main(String[] args) {
        DomineeringPosition initialPosition = new DomineeringPosition(4, 4);
        Domineering domineering = new Domineering();
        System.out.println("1: Player To Player.");
        System.out.println("2: Player To AI.");
        Scanner scanner = new Scanner(System.in);
        if(scanner.nextInt()==1){
            domineering.playGameP2P(initialPosition, true);
        }
        else
            domineering.playGame(initialPosition, true);
    }
}
