package menace;

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
    private static Integer[] selfMapping = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private static Integer[] rotate90Mapping = {2, 5, 8, 1, 4, 7, 0, 3, 6};
    private static Integer[] rotate180Mapping = {8, 7, 6, 5, 4, 3, 2, 1, 0};
    private static Integer[] rotate270Mapping = {6, 3, 0, 7, 4, 1, 8, 5, 2};
    private static Integer[] flipHorizontalMapping = {6, 7, 8, 3, 4, 5, 0, 1, 2};
    private static Integer[] flipVerticalMapping = {2, 1, 0, 5, 4, 3, 8, 7, 6};
    private static Integer[] diagonalFlipMapping = {0, 3, 6, 1, 4, 7, 2, 5, 8};
    private static Integer[] antiDiagonalFlipMapping = {8, 5, 2, 7, 4, 1, 6, 3, 0};

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

    public static Integer[] getMappingIfEqual(String original, String combination) {
        if (original.equals(combination)) {
            return selfMapping;
        } else if (original.equals(rotate90(combination))) {
            return rotate90Mapping;
        }
        else if(original.equals(rotate180(combination))) {
            return rotate180Mapping;
        }
        else if(original.equals(rotate270(combination))) {
            return rotate270Mapping;
        }
        else if(original.equals(flipHorizontal(combination))) {
            return flipHorizontalMapping;
        }
        else if(original.equals(flipVertical(combination))) {
            return flipVerticalMapping;
        }
        else if(original.equals(diagonalFlip(combination))) {
            return diagonalFlipMapping;
        }
        else if(original.equals(antiDiagonalFlip(combination))) {
            return antiDiagonalFlipMapping;
        }
        else {
            return null;
        }
    }

    public static boolean contains(HashSet<String> set, String state) {
        return set.contains(state)
                || set.contains(rotate90(state))
                || set.contains(rotate180(state))
                || set.contains(rotate270(state))
                || set.contains(flipHorizontal(state))
                || set.contains(flipVertical(state))
                || set.contains(diagonalFlip(state))
                || set.contains(antiDiagonalFlip(state));
    }

    public static String rotate90(String board) {
        String transformedBoard = transform(board, rotate90Mapping);
        return transformedBoard;
    }

    public static String rotate180(String board) {
        String transformedBoard = transform(board, rotate180Mapping);
        return transformedBoard;
    }

    public static String rotate270(String board) {
        String transformedBoard = transform(board, rotate270Mapping);
        return transformedBoard;
    }

    public static String flipHorizontal(String board) {
        String transformedBoard = transform(board, flipHorizontalMapping);
        return transformedBoard;
    }

    public static String flipVertical(String board) {
        String transformedBoard = transform(board, flipVerticalMapping);
        return transformedBoard;
    }

    public static String diagonalFlip(String board) {
        String transformedBoard = transform(board, diagonalFlipMapping);
        return transformedBoard;
    }

    public static String antiDiagonalFlip(String board) {
        String transformedBoard = transform(board, antiDiagonalFlipMapping);
        return transformedBoard;
    }

    public static String foo(String board) {
        Integer[] map = {2, 1, 6, 5, 4, 3, 8, 7, 0};
        String transformedBoard = transform(board, map);
        return transformedBoard;
    }

    private static String transform(String board, Integer[] map) {
        char[] response = new char[9];
        for (int i = 0; i < board.length(); i++) {
            response[map[i]] = board.charAt(i);
        }
        return String.valueOf(response);
    }
}
