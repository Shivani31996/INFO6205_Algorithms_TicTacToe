import menace.Bead;
import menace.Menace;
import menace.StateGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ConsoleController {
    public static void main(String[] args) {
        HashMap<Integer, HashSet<String>> states = StateGenerator.getStates();

        Menace menace = new Menace(states);

        int level = 0;
        String initial = states.get(level).stream().findFirst().orElse(null);

        char[] state = initial.toCharArray();
        char move = 'X';
        while (!isGameOver(state)) {
            level++;
//            System.out.println(Arrays.toString(state));
            print(state);
            Bead bead = menace.getBead(String.valueOf(state));
//            if (bead == null) {
//                System.out.println(level);
//                System.out.println(Arrays.toString(states.get(level).toArray()));
//            }
            state[bead.getPosition()] = move;
            move = move == 'X' ? 'O' : 'X';
        }

        //System.out.println(Arrays.toString(state));
        print(state);
    }

    private static boolean isGameOver(char[] state) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == '-') {
                return false;
            }
        }

        return true;
    }

    private static void print(char[] state) {
        System.out.println(state[0] + " " + state[1] + " " + state[2]);
        System.out.println(state[3] + " " + state[4] + " " + state[5]);
        System.out.println(state[6] + " " + state[7] + " " + state[8]);
        System.out.println();
    }
}
