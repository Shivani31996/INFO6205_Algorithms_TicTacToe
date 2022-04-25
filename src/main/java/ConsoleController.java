import menace.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ConsoleController {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashMap<Integer, HashSet<String>> states = StateGenerator.getStates();

        Menace menace = new Menace(states);

        int iterations = 0;
        /**
         *  While training whom to penalize / reward
         *  Penalize x
         *  Penalize 0
         *  Penalize both
         */
        while (iterations < 10) {
            int level = 0;
            String initial = states.get(level).stream().findFirst().orElse(null);

            char[] board = initial.toCharArray();
            char move = 'X';
            Status gameStatus = GameStatusHelper.getStatus(String.valueOf(board));
            while (gameStatus.equals(Status.RUNNING)) {
                Bead bead = menace.getBead(String.valueOf(board), Mode.TRAIN);
                board[bead.getPosition()] = move;
                move = move == 'X' ? 'O' : 'X';
                gameStatus = GameStatusHelper.getStatus(String.valueOf(board));
            }

            if (gameStatus.equals(Status.X_WINNER)) {
                menace.reward();
            } else if (gameStatus.equals(Status.DRAW)) {
                menace.draw();
            } else {
                menace.punish();
            }

            menace.finish();

            //print(board);
            iterations++;
        }

        // TODO: Save the data to the training
        try {
            menace.printTrained();
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {

            System.out.println("\nNew Game:");

            String initial = states.get(0).stream().findFirst().orElse(null);

            char[] board = initial.toCharArray();
            char move = 'X';
            Status gameStatus = GameStatusHelper.getStatus(String.valueOf(board));
            while (gameStatus.equals(Status.RUNNING)) {
                print(board);
                if (move == 'X') {
                    Bead bead = menace.getBead(String.valueOf(board), Mode.TEST);
                    if (bead == null) {
                        System.out.println("Please try again. This block is already filled..");
                        break;
                    }
                    board[bead.getPosition()] = move;
                } else {
                    System.out.print("O move position: ");
                    int pos = sc.nextInt();
                    board[pos] = move;
                }

                move = move == 'X' ? 'O' : 'X';
                gameStatus = GameStatusHelper.getStatus(String.valueOf(board));
            }

            print(board);
        }
    }

    private static void print(char[] state) {
        System.out.println(state[0] + " " + state[1] + " " + state[2]);
        System.out.println(state[3] + " " + state[4] + " " + state[5]);
        System.out.println(state[6] + " " + state[7] + " " + state[8]);
        System.out.println();
    }
}
