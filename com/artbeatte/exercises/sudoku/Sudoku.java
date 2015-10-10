package com.artbeatte.exercises.sudoku;

import java.util.HashMap;

/**
 * @author art.beatte
 * @version 10/9/15
 */
public class Sudoku {

    private static final int[] EMPTY_ROW = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static final int[] BAD_ROW = new int[]{0, 1, 0, 0, 1, 0, 0, 0, 0};

    private static final int[] VALID_ROW = new int[]{0, 0, 1, 0, 0, 0, 0, 0, 0};

    private static final int[] GOOD_ROW = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

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

    private static boolean isValid(int[][] board) {
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
            }
            if (!isGroupValid(col)) return false;
        }
        // check grids
        // Check that the subsquares contain no duplicate values
        for (int baseRow = 0; baseRow < 9; baseRow += 3)
        {
            for (int baseCol = 0; baseCol < 9; baseCol += 3)
            {
                if (!checkSquare(board, baseRow, baseCol))
                {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkSquare(int[][] grid, int baseRow, int baseCol)
    {
        int index = 0;
        int[] square = new int[9];
        for (int row = baseRow; row < (baseRow + 3); ++row)
        {
            for (int col = baseCol; col < (baseCol + 3); ++col)
            {
                square[index++] = grid[row][col];
            }
        }
        return isGroupValid(square);
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
        int[][][] boards = new int[][][]{
                EMPTY_BOARD,
                BAD_ROW_BOARD,
                BAD_COL_BOARD,
                VALID_GROUP_BOARD,
                BAD_GROUP_BOARD,
                BAD_SIZE_BOARD};
        boolean[] boardOutcomes = new boolean[]{
                true,
                false,
                false,
                true,
                false,
                false};
        for (int i = 0; i < boards.length; i++) {
            int[][] board = boards[i];
            boolean isValid = isValid(board);
            System.out.println("TEST #" + (i + 1) + ": " + (isValid == boardOutcomes[i] ? "PASSED" : "*** FAILED ***"));
        }
    }
}
