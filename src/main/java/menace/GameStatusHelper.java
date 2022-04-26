package menace;

public class GameStatusHelper {

    public static Status getStatus(String state) {
        if (isDraw(state)) return Status.DRAW;
        if (isXWinner(state)) return Status.X_WINNER;
        if (isOWinner(state)) return Status.O_WINNER;
        return Status.RUNNING;
    }

    private static boolean isXWinner(String state) {
        return checkWinner(state, 'X');
    }

    private static boolean isOWinner(String state) {
        return checkWinner(state, 'O');
    }

    private static boolean checkWinner(String state, char mark) {
        return verticalLine(state, mark) || horizontalLine(state, mark) || diagonalLine(state, mark);
    }

    private static boolean verticalLine(String state, char mark) {
        for (int column = 0; column < 3; column++) {
            if (state.charAt(column) == mark && state.charAt(column + 3) == mark && state.charAt(column + 6) == mark)
            {
                return true;
            }
        }

        return false;
    }

    private static boolean horizontalLine(String state, char mark) {
        for (int row = 0; row < 3; row++) {
            if (state.charAt((row * 3)) == mark
                    && state.charAt((row * 3) + 1) == mark
                    && state.charAt((row * 3) + 2) == mark)
            {
                return true;
            }
        }

        return false;
    }

    private static boolean diagonalLine(String state, char mark) {
        return (state.charAt(0) == mark && state.charAt(4) == mark && state.charAt(8) == mark)
                || (state.charAt(2) == mark && state.charAt(4) == mark && state.charAt(6) == mark);
    }

    private static boolean isDraw(String state) {
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == '-') {
                return false;
            }
        }

        return true;
    }
}