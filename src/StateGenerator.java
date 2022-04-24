import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StateGenerator {
    public static HashMap<Integer, HashSet<String>> getStates() {
        char[] board = {'-', '-','-','-','-','-','-','-','-'};
        char nextTurn = 'X';
        HashMap<Integer, ArrayList<String>> states = new HashMap<>();
        backTrack(board, nextTurn, states, 0);

        HashMap<Integer, HashSet<String>> symmetricStates = new HashMap<>();
        int count = 0;
        for (int i = 0; i <= 9; i++) {
            HashSet<String> set = new HashSet<>();
            for (String state: states.get(i)) {
                if (state.length() > 9) {
                    System.out.println(state);
                }
                if (!SymmetryUtil.contains(set, state)) {
                    set.add(state);
                }
            }
            symmetricStates.put(i, set);
            if (i % 2 == 0 || true) {
                count += set.size();
            }
        }

        System.out.println(count);
        return symmetricStates;
    }

    public static void backTrack(char[] board, char nextTurn, HashMap<Integer, ArrayList<String>> states, int level) {
        if (!states.containsKey(level)) {
            states.put(level, new ArrayList<>());
        }
        if (level % 2 == 0 || true) {
            states.get(level).add(String.valueOf(board));
        }
        for(int i = 0 ; i < 9 ; i++) {
            if (board[i] == '-') {
                board[i] = nextTurn;
                backTrack(board, nextTurn == 'X' ? 'O' : 'X', states, level + 1);
                board[i] = '-';
            }
        }
    }
}
