import java.util.HashSet;

public class SymmetryUtil {
    //    {0, 1, 2, 3, 4, 5, 6, 7, 8}, // normal board
    //    {6, 3, 0, 7, 4, 1, 8, 5, 2}, // 90 degees right
    //    {2, 5, 8, 1, 4, 7, 0, 3, 6}, // 90 degrees left
    //    {8, 7, 6, 5, 4, 3, 2, 1, 0}, // 180 degree rotation.
    //    {2, 1, 0, 5, 4, 3, 8, 7, 6}, // flipped left to right
    //    {6, 7, 8, 3, 4, 5, 0, 1, 2}, // flipped top to bottom
    //    {2, 1, 6, 5, 4, 3, 8, 7, 0}, // 180 degree rotation, flipped horizontally
    //    {8, 5, 2, 7, 4, 1, 6, 3, 0}, // 90 degrees left,  flipped vertically
    //    {0, 3, 6, 1, 4, 7, 2, 5, 8}  // 90 degrees right, flipped vertically

    public static String[] getCombinations(String state) {
        String[] states = new String[8];
        states[0] = state;
        states[1] = rotate90(state);
        states[2] = rotate180(state);
        states[3] = rotate270(state);
        states[4] = flipHorizontal(state);
        states[5] = flipVertical(state);
        states[6] = diagonalFlip(state);
        states[7] = antiDiagonalFlip(state);
        return states;
    }

    public static boolean contains(HashSet<String> set, String state) {
        return set.contains(rotate90(state))
                || set.contains(rotate180(state))
                || set.contains(rotate270(state))
                || set.contains(flipHorizontal(state))
                || set.contains(flipVertical(state))
                || set.contains(diagonalFlip(state))
                || set.contains(antiDiagonalFlip(state));
    }

    public static String rotate90(String board) {
        int[] map = {2, 5, 8, 1, 4, 7, 0, 3, 6};
        String transformedBoard = transform(board, map);
        return transformedBoard;
    }

    public static String rotate180(String board) {
        int[] map = {8, 7, 6, 5, 4, 3, 2, 1, 0};
        String transformedBoard = transform(board, map);
        return transformedBoard;
    }

    public static String rotate270(String board) {
        int[] map = {6, 3, 0, 7, 4, 1, 8, 5, 2};
        String transformedBoard = transform(board, map);
        return transformedBoard;
    }

    public static String flipHorizontal(String board) {
        int[] map = {6, 7, 8, 3, 4, 5, 0, 1, 2};
        String transformedBoard = transform(board, map);
        return transformedBoard;
    }

    public static String flipVertical(String board) {
        int[] map = {2, 1, 0, 5, 4, 3, 8, 7, 6};
        String transformedBoard = transform(board, map);
        return transformedBoard;
    }

    public static String diagonalFlip(String board) {
        int[] map = {0, 3, 6, 1, 4, 7, 2, 5, 8};
        String transformedBoard = transform(board, map);
        return transformedBoard;
    }

    public static String antiDiagonalFlip(String board) {
        int[] map = {8, 5, 2, 7, 4, 1, 6, 3, 0};
        String transformedBoard = transform(board, map);
        return transformedBoard;
    }

    public static String foo(String board) {
        int[] map = {2, 1, 6, 5, 4, 3, 8, 7, 0};
        String transformedBoard = transform(board, map);
        return transformedBoard;
    }

    private static String transform(String board, int[] map) {
        char[] response = new char[9];
        for (int i = 0; i < board.length(); i++) {
            response[map[i]] = board.charAt(i);
        }
        return String.valueOf(response);
    }
}
