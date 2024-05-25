public class SudokuValidator {
    
    private static final int[][] invalidSudokuBoard = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

    // private static final int[][] sudokuBoard = {
    //         {5, 3, 4, 6, 7, 8, 9, 1, 2},
    //         {6, 7, 2, 1, 9, 5, 3, 4, 8},
    //         {1, 9, 8, 3, 4, 2, 5, 6, 7},
    //         {8, 5, 9, 7, 6, 1, 4, 2, 3},
    //         {4, 2, 6, 8, 5, 3, 7, 9, 1},
    //         {7, 1, 3, 9, 2, 4, 8, 5, 6},
    //         {9, 6, 1, 5, 3, 7, 2, 8, 4},
    //         {2, 8, 7, 4, 1, 9, 6, 3, 5},
    //         {3, 4, 5, 2, 8, 6, 1, 7, 9}
    //     };

    private static boolean rowsValid;
    private static boolean columnsValid;
    private static boolean subgridsValid;

     public static void main(String[] args) {
        // Create and start threads
        Thread rowThread = new Thread(new RowValidator());
        Thread columnThread = new Thread(new ColumnValidator());
        Thread subgridThread = new Thread(new SubgridValidator());

        rowThread.start();
        columnThread.start();
        subgridThread.start();

        // Wait for all threads to finish
        try {
            rowThread.join();
            columnThread.join();
            subgridThread.join();
        } catch (InterruptedException e) {
        }

        // Check results
        boolean isValid = rowsValid && columnsValid && subgridsValid;
        System.out.println("Sudoku board is valid: " + isValid);
    }

    // Thread class to check rows
    static class RowValidator implements Runnable {
        @Override
        public void run() {
            System.out.println("Starting RowValidator thread.");
            rowsValid = checkRows(invalidSudokuBoard);
            System.out.println("Completed RowValidator thread.");
        }

        private boolean checkRows(int[][] board) {
            for (int[] row : board) {
                if (!isUnique(row)) {
                    return false;
                }
            }
            return true;
        }

        private boolean isUnique(int[] array) {
            boolean[] seen = new boolean[10];
            for (int num : array) {
                if (num < 1 || num > 9 || seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
            return true;
        }
    }

    // Thread class to check columns
    static class ColumnValidator implements Runnable {
        @Override
        public void run() {
            System.out.println("Starting ColumnValidator thread.");
            columnsValid = checkColumns(invalidSudokuBoard);
            System.out.println("Completed ColumnValidator thread.");
        }

        private boolean checkColumns(int[][] board) {
            for (int col = 0; col < 9; col++) {
                boolean[] seen = new boolean[10];
                for (int row = 0; row < 9; row++) {
                    int num = board[row][col];
                    if (num < 1 || num > 9 || seen[num]) {
                        return false;
                    }
                    seen[num] = true;
                }
            }
            return true;
        }
    }

    // Thread class to check 3x3 subgrids
    static class SubgridValidator implements Runnable {
        @Override
        public void run() {
            System.out.println("Starting SubgridValidator thread.");
            subgridsValid = checkSubgrids(invalidSudokuBoard);
            System.out.println("Completed SubgridValidator thread.");
        }

        private boolean checkSubgrids(int[][] board) {
            for (int row = 0; row < 9; row += 3) {
                for (int col = 0; col < 9; col += 3) {
                    if (!checkSubgrid(board, row, col)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean checkSubgrid(int[][] board, int startRow, int startCol) {
            boolean[] seen = new boolean[10];
            for (int row = startRow; row < startRow + 3; row++) {
                for (int col = startCol; col < startCol + 3; col++) {
                    int num = board[row][col];
                    if (num < 1 || num > 9 || seen[num]) {
                        return false;
                    }
                    seen[num] = true;
                }
            }
            return true;
        }
    }
}




