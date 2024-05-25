public class Functions {
    
    // Validate columns
    public static boolean isValidSudokuColumn(int[][] board) {
        for (int col = 0; col < 9; col++) {
            boolean[] seen = new boolean[10]; // To keep track of seen numbers (1-9)
            for (int row = 0; row < 9; row++) {
                int num = board[row][col];
                if (seen[num]) {
                    return false; // Duplicate found
                }
                seen[num] = true;
            }
        
        }
        return true; // No duplicates found
    }

    // Validate rows
    public static boolean isValidSudokuRow(int[][] board) {
        for (int row = 0; row < 9; row++) {
            boolean[] seen = new boolean[10]; // To keep track of seen numbers (1-9)
            for (int col = 0; col < 9; col++) {
                int num = board[row][col];
                if (seen[num]) {
                    return false; // Duplicate found
                }
                seen[num] = true;
                
            }
        }
        return true; // No duplicates found
    }

    // Validate 3x3 boxes
    public static boolean isValidSudokuBoxes(int[][] board) {
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                if (!isValidBox(board, boxRow, boxCol)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Helper method for single 3x3 box
    private static boolean isValidBox(int[][] board, int boxRow, int boxCol) {
        boolean[] seen = new boolean[10]; // To keep track of seen numbers (1-9)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int num = board[boxRow * 3 + row][boxCol * 3 + col];
                    if (seen[num]) {
                        return false; // Duplicate found
                    }
                    seen[num] = true;
            }
        }
        return true;
    }

}

