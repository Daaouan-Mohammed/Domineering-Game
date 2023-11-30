package Domineering;


import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public abstract class GameSearch {
    public static final boolean DEBUG = false;
    public static boolean PROGRAM = false;
    public static boolean HUMAN = true;


    public abstract boolean drawnPosition(Position var1);

    public abstract boolean wonPosition(Position var1, boolean var2);

    public abstract float positionEvaluation(Position var1, boolean var2);

    public abstract void printPosition(Position var1);
    public abstract Position getProgramMove(Position p);

    public abstract Position[] possiblePositions(Position var1, boolean var2);
    public abstract void saveGame(Position position, boolean player1Turn, boolean player, int player1HelpCount, int player2HelpCount);

    public abstract Position makeMove(Position var1, boolean var2, Move var3);

    public abstract boolean reachedMaxDepth(Position var1, int var2);

    public abstract Move createMove();



    public Vector minValue(int depth, Position p, float alpha, float beta) {
        Vector best;
        if (this.reachedMaxDepth(p, depth)) {
            best = new Vector(2);
            float value = this.positionEvaluation(p, PROGRAM);
            best.addElement(new Float(value));
            best.addElement(p);
            return best;
        } else {
            best = new Vector();
            best.addElement(Float.MAX_VALUE);
            best.addElement(p);
            Position[] moves = this.possiblePositions(p, HUMAN);

            for(int i = 0; i < moves.length; ++i) {
                Vector v2 = this.maxValue(depth + 1, moves[i], alpha, beta);
                float value_best = (Float)best.elementAt(0);
                float value_v2 = (Float)v2.elementAt(0);
                if (value_v2 <= value_best) {
                    best = new Vector();
                    best.addElement(value_v2);
                    best.addElement(moves[i]);
                }

                if (value_best <= alpha) {
                    return best;
                }

                beta = beta <= value_best ? beta : value_best;
            }

            return best;
        }
    }

    public Vector maxValue(int depth, Position p, float alpha, float beta) {
        Vector best;
        if (this.reachedMaxDepth(p, depth)) {
            best = new Vector(2);
            float value = this.positionEvaluation(p, HUMAN);
            best.addElement(new Float(value));
            best.addElement(p);
            return best;
        } else {
            best = new Vector();
            best.addElement(Float.MIN_VALUE);
            best.addElement(p);
            Position[] moves = this.possiblePositions(p, PROGRAM);

            for(int i = 0; i < moves.length; ++i) {
                Vector v2 = this.minValue(depth + 1, moves[i], alpha, beta);
                float value_best = (Float)best.elementAt(0);
                float value_v2 = (Float)v2.elementAt(0);
                if (value_v2 >= value_best) {
                    best = new Vector();
                    best.addElement(value_v2);
                    best.addElement(moves[i]);
                }

                if (value_best >= beta) {
                    return best;
                }

                alpha = alpha >= value_best ? alpha : value_best;
            }

            return best;
        }
    }
    //////////////////////////////////////////////////////////

    public void playGame(Position startingPosition, boolean humanPlayFirst) {
        int humanHelpCount = 0;

        if (!humanPlayFirst) {
            Vector v = this.minValue(0, startingPosition, Float.MIN_VALUE, Float.MAX_VALUE);
            startingPosition = (Position)v.elementAt(1);
        }

        while(true) {
            this.printPosition(startingPosition);
            if (this.wonPosition(startingPosition, PROGRAM)) {
                System.out.println("Program won");
                break;
            }

            if (this.wonPosition(startingPosition, HUMAN)) {
                System.out.println("Human won");
                break;
            }

            System.out.println("Your move:");

            if (humanHelpCount < 2) {
                System.out.println("Do you want help from the program? (yes/no)");
                Scanner scanner = new Scanner(System.in);
                String helpResponse = scanner.next().toLowerCase();

                if (helpResponse.equals("yes")) {
                    Vector helpResult = this.minValue(0, startingPosition, Float.MIN_VALUE, Float.MAX_VALUE);
                    Enumeration enum2 = helpResult.elements();

                    while (enum2.hasMoreElements()) {
                        System.out.println(" next element: " + String.valueOf(enum2.nextElement()));
                    }

                    startingPosition = (Position) helpResult.elementAt(1);
                    humanHelpCount++;
                }
                else{
                    Move move = createMove();
                    startingPosition = this.makeMove(startingPosition, HUMAN, move);

                    // Demander si le joueur veut sauvegarder la partie
//                    System.out.println("Do you want to save the game? (yes/no)");
//                    Scanner scanner1 = new Scanner(System.in);
//                    String saveResponse = scanner1.next().toLowerCase();
//
//                    if (saveResponse.equals("yes")) {
//                        saveGameP2AI(startingPosition, humanPlayFirst, humanHelpCount);
//                        System.out.println("Game saved.");
//                        break;  // Vous pouvez terminer le jeu ici ou continuer
//                    }
                }
            }
            else{
                Move move = createMove();
                startingPosition = this.makeMove(startingPosition, HUMAN, move);

                // Demander si le joueur veut sauvegarder la partie
//                System.out.println("Do you want to save the game? (yes/no)");
//                Scanner scanner = new Scanner(System.in);
//                String saveResponse = scanner.next().toLowerCase();
//
//                if (saveResponse.equals("yes")) {
//                    saveGameP2AI(startingPosition, humanPlayFirst, humanHelpCount);
//                    System.out.println("Game saved.");
//                    break;  // Vous pouvez terminer le jeu ici ou continuer
//                }
            }

            //   this.printPosition(startingPosition);
            Vector v = this.maxValue(0, startingPosition, Float.MIN_VALUE, Float.MAX_VALUE);
            Enumeration enum2 = v.elements();

            while(enum2.hasMoreElements()) {
                System.out.println(" next element: " + String.valueOf(enum2.nextElement()));
            }

            startingPosition = (Position)v.elementAt(1);
        }

    }

    public void playGameP2P(Position startingPosition, boolean player1Turn) {
        int player1HelpCount = 0;
        int player2HelpCount = 0;

        while (true) {
            printPosition(startingPosition);

            if (wonPosition(startingPosition, PROGRAM)) {
                System.out.println("Player 2 won");
                break;
            }

            if (wonPosition(startingPosition, HUMAN)) {
                System.out.println("Player 1 won");
                break;
            }

            if (player1Turn) {
                System.out.println("Player 1's move:");

                if (player1HelpCount < 2) {
                    System.out.println("Do you want help from the program? (yes/no)");
                    Scanner scanner = new Scanner(System.in);
                    String helpResponse = scanner.next().toLowerCase();

                    if (helpResponse.equals("yes")) {
                        Vector v = minValue(0, startingPosition, Float.MIN_VALUE, Float.MAX_VALUE);
                        startingPosition = (Position) v.elementAt(1);
                        player1HelpCount++;
                    } else if (helpResponse.equals("save")) {
                        saveGame(startingPosition, true, player1Turn, player1HelpCount, player2HelpCount);
                        System.out.println("Game saved. Exiting.");
                        return;  // Arrêter le jeu
                    } else {
                        Move move = createMove();
                        startingPosition = makeMove(startingPosition, HUMAN, move);
                    }
                } else {
                    Move move = createMove();
                    startingPosition = makeMove(startingPosition, HUMAN, move);
                }
            } else {
                System.out.println("Player 2's move:");

                if (player2HelpCount < 2) {
                    System.out.println("Do you want to save the game? (save) or Do you want help from the program? (yes/no)");
                    Scanner scanner = new Scanner(System.in);
                    String helpResponse = scanner.next().toLowerCase();

                    if (helpResponse.equals("yes")) {
                        Vector v = maxValue(0, startingPosition, Float.MIN_VALUE, Float.MAX_VALUE);
                        startingPosition = (Position) v.elementAt(1);
                        player2HelpCount++;
                    } else if (helpResponse.equals("save")) {
                        saveGame(startingPosition, false, player1Turn, player1HelpCount, player2HelpCount);
                        System.out.println("Game saved. Exiting.");
                        return;  // Arrêter le jeu
                    } else {
                        Move move = createMove();
                        startingPosition = makeMove(startingPosition, PROGRAM, move);
                    }
                } else {
                    Move move = createMove();
                    startingPosition = makeMove(startingPosition, HUMAN, move);
                }
            }

            player1Turn = !player1Turn;
        }
    }



}
