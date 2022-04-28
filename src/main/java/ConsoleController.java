import logger.LogManager;
import menace.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleController {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, HashSet<String>> states = StateGenerator.getStates();
        Menace menace = new Menace(states);
        int iterations = 0;
        int totalNoOfMatches = 0;
        int totalNoOfWins = 0;
        int totalNoOfLoss = 0;
        int totalNoOfDraws = 0;
        int probability = 0;

        int totalMatchesPlayed = 0;
        int totalWins = 0;
        int totalLoss = 0;
        int totalDraws = 0;

        GrandMaster grandMaster = new GrandMaster(states);
        StringBuilder sb = new StringBuilder();
        System.out.println("\nInitial Values for ALPHA is: " + Constants.ALPHA + ", BETA is : " + Constants.BETA
        + ", GAMMA is: " + Constants.GAMMA + " and DELTA is: " + Constants.DELTA);
        System.out.println("\n*****************************************************************************");
        System.out.println("Menace is trained with the help of GrandMaster which trains it in such\n" +
                "a way that it does not win helping the Menace remove the beads from \n" +
                "the matchboxes that might lead him to lose while playing against a Human\n");
        sb.append("Initial Values for ALPHA is: " + Constants.ALPHA + ", BETA is : " + Constants.BETA
                + ", GAMMA is: " + Constants.GAMMA + " and DELTA is: " + Constants.DELTA +"\n");

        while (iterations < 10000) {
            int level = 0;
            String initial = states.get(level).stream().findFirst().orElse(null);

            char[] board = initial.toCharArray();
            char move = 'X';
            GameStatus gameStatus = GameStatusHelper.getStatus(String.valueOf(board));
            while (gameStatus.equals(GameStatus.RUNNING)) {
                if (move == 'X') {
                    Bead bead = menace.getBead(String.valueOf(board), Mode.TEST);
                    board[bead.getPosition()] = move;
                } else {
                    int grandMasterMove = grandMaster.nextMove(String.valueOf(board), level);
                    if (grandMasterMove == -1) {
                        System.out.println(String.valueOf(board));
                    }
                    board[grandMasterMove] = move;
                }
                move = move == 'X' ? 'O' : 'X';
                gameStatus = GameStatusHelper.getStatus(String.valueOf(board));
                level++;
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            if (gameStatus.equals(GameStatus.X_WINNER)) {
                menace.reward();
                totalNoOfWins++;
                totalNoOfMatches++;
                sb.append(formatter.format(date) + " Menace won \n");
            } else if (gameStatus.equals(GameStatus.DRAW)) {
                menace.draw();
                totalNoOfDraws++;
                totalNoOfMatches++;
                sb.append(formatter.format(date) + " Menace Draw \n");
            } else {
                menace.punish();
                totalNoOfLoss++;
                totalNoOfMatches++;
                sb.append(formatter.format(date) + " Menace Lose \n");
            }
            probability =  (totalNoOfLoss + totalNoOfDraws) / totalNoOfMatches;
            menace.finish();
            iterations++;
        }
        System.out.println("Menace played total: " +totalNoOfMatches + " games of which he won: " + totalNoOfWins + ", lost: "
                + totalNoOfLoss + " and draw: " +totalNoOfDraws + " and Probability p is: " +probability +"\n");
        sb.append("Menace played total: " +totalNoOfMatches + " games of which he won: " + totalNoOfWins + ", lost: "
        + totalNoOfLoss + " and draw: " +totalNoOfDraws +  " and Probability p is: " +probability +"\n");
        try {
            LogManager.log(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {

            StringBuilder sb1 = new StringBuilder();
            System.out.println("New Game: Please enter values between 0 and 8\n");
            sb1.append("New Game:\n");

            String initial = states.get(0).stream().findFirst().orElse(null);

            char[] board = initial.toCharArray();
            char move = 'X';
            GameStatus gameStatus = GameStatusHelper.getStatus(String.valueOf(board));
            int level = 0;
            while (gameStatus.equals(GameStatus.RUNNING)) {
                print(board);
                if (move == 'X') {
                    Bead bead = menace.getBead(String.valueOf(board), Mode.TEST);
                    int pos = bead.getPosition();
                    board[bead.getPosition()] = move;
//                    int pos = grandMaster.nextMove(String.valueOf(board), level);
//                    board[pos] = move;
                    System.out.println("Menace played the move at position " + pos);
                    sb1.append("Menace played the move at position " + pos  + "\n");
                }
                else {
                    int pos = -1;
                    while (true) {
                        System.out.print("Your move(0): ");
                        pos = sc.nextInt();
                        if (pos < 0 || pos > 8 || board[pos] != '-') {
                            System.out.println("Please enter a valid move.");
                        } else {
                            break;
                        }
                    }

                    board[pos] = move;
                    System.out.println("Human played the move at position " + pos);
                    sb1.append("Human played the move at position ").append(pos).append("\n");
                }

                move = move == 'X' ? 'O' : 'X';
                gameStatus = GameStatusHelper.getStatus(String.valueOf(board));
                level++;
            }

            totalMatchesPlayed++;
            print(board);
            if(gameStatus == GameStatus.X_WINNER){
                menace.reward();
                totalWins++;
                System.out.println("Menace is the winner");
                sb1.append("Menace is the winner\n");
            }
            else if(gameStatus == GameStatus.O_WINNER) {
                menace.punish();
                totalLoss++;
                System.out.println("Human is the winner");
                sb1.append("Human is the winner\n");
            }
            else {
                menace.draw();
                totalDraws++;
                System.out.println("Match is Draw");
                sb1.append("Match is Draw\n");
            }

            System.out.println("Menace played total: " + totalMatchesPlayed + " games of which he won: " + totalWins + ", lost: "
                    + totalLoss + " and draw: " + totalDraws + "\n");
            sb1.append("\nMenace played total: " +totalMatchesPlayed + " games of which he won: " + totalWins + ", lost: "
                    + totalLoss + " and draw: " +totalDraws + "\n");

            try {
                LogManager.log(sb1.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void print(char[] state) {
        System.out.println(state[0] + " | " + state[1] + " | " + state[2]);
        System.out.println(state[3] + " | " + state[4] + " | " + state[5]);
        System.out.println(state[6] + " | " + state[7] + " | " + state[8]);
        System.out.println();
    }
}
