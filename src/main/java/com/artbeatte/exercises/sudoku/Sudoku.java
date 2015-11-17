package com.artbeatte.exercises.sudoku;

import com.artbeatte.exercises.testing.MethodParameterTestCase;
import com.artbeatte.exercises.testing.SystemTestRunner;
import com.artbeatte.exercises.testing.TestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author art.beatte
 * @version 10/9/15
 */
@SuppressWarnings("SpellCheckingInspection")
public class Sudoku {

    private Sudoku() { /* no outside instances */ }

    @SuppressWarnings("unused")
    public static boolean isValid(int[][] board) {
        // check board size
        if (board.length != 9) return false;
        for (int i = 0; i < 9; i++) {
            int[] row = board[i];
            if (row.length != 9) return false;
            // check rows
            if (!isGroupValid(row)) return false;
            // check columns
            int[] col = new int[9];
            for (int j = 0; j < 9; j++) {
                col[j] = board[j][i];
                // check squares
                if (i % 3 == 0 && j % 3 == 0 && !isGroupValid(getSquare(board, i, j))) return false;
            }
            if (!isGroupValid(col)) return false;
        }

        return true;
    }

    private static int[] getSquare(int[][] grid, int baseRow, int baseCol) {
        int index = 0;
        int[] square = new int[9];
        for (int row = baseRow; row < (baseRow + 3); ++row)
        {
            for (int col = baseCol; col < (baseCol + 3); ++col)
            {
                square[index++] = grid[row][col];
            }
        }
        return square;
    }

    private static boolean isGroupValid(int[] row) {
        HashMap<Integer, Integer> buckets = new HashMap<>();
        for (int k : row) {
            Integer valid = buckets.put(k, k);
            if (valid != null && valid > 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        TestRunner testRunner = new SystemTestRunner();
        for (int[][] test : TESTS.keySet()) {
            testRunner.addTestCase(
                    new MethodParameterTestCase<>(new Sudoku(), "isValid", int[][].class, test, TESTS.get(test)));
        }
        testRunner.runTests();
    }

    private static final int[] EMPTY_ROW = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static final int[] BAD_ROW = new int[]{0, 1, 0, 0, 1, 0, 0, 0, 0};

    private static final int[] VALID_ROW = new int[]{0, 0, 1, 0, 0, 0, 0, 0, 0};

    private static final int[][] EMPTY_BOARD = new int[][]{
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW
    };

    private static final int[][] VALID_FILLED_BOARD = new int[][]{
            new int[]{1, 5, 2, 4, 6, 9, 3, 7, 8},
            new int[]{7, 8, 9, 2, 1, 3, 4, 5, 6},
            new int[]{4, 3, 6, 5, 8, 7, 2, 9, 1},
            new int[]{6, 1, 3, 8, 7, 2, 5, 4, 9},
            new int[]{9, 7, 4, 1, 5, 6, 8, 2, 3},
            new int[]{8, 2, 5, 9, 3, 4, 1, 6, 7},
            new int[]{5, 6, 7, 3, 4, 8, 9, 1, 2},
            new int[]{2, 4, 8, 6, 9, 1, 7, 3, 5},
            new int[]{3, 9, 1, 7, 2, 5, 6, 8, 4},
    };

    private static final int[][] BAD_ROW_BOARD = new int[][]{
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            BAD_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW
    };

    private static final int[][] BAD_COL_BOARD = new int[][]{
            EMPTY_ROW,
            VALID_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            VALID_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW
    };

    private static final int[][] VALID_GROUP_BOARD = new int[][]{
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            new int[]{0, 0, 0, 1, 7, 8, 0, 0, 0},
            new int[]{0, 0, 0, 6, 2, 9, 0, 0, 0},
            new int[]{0, 0, 0, 5, 4, 3, 0, 0, 0},
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW
    };

    private static final int[][] BAD_GROUP_BOARD = new int[][]{
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW,
            new int[]{0, 0, 0, 1, 7, 8, 0, 0, 0},
            new int[]{0, 0, 0, 6, 2, 1, 0, 0, 0},
            new int[]{0, 0, 0, 5, 4, 3, 0, 0, 0},
            EMPTY_ROW,
            EMPTY_ROW,
            EMPTY_ROW
    };

    private static final int[][] BAD_SIZE_BOARD = new int[][]{
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private static final Map<int[][], Boolean> TESTS = new HashMap<>();
    static {
        TESTS.put(EMPTY_BOARD, true);
        TESTS.put(VALID_FILLED_BOARD, true);
        TESTS.put(BAD_ROW_BOARD, false);
        TESTS.put(BAD_COL_BOARD, false);
        TESTS.put(VALID_GROUP_BOARD, true);
        TESTS.put(BAD_GROUP_BOARD, false);
        TESTS.put(BAD_SIZE_BOARD, false);
    }
}
