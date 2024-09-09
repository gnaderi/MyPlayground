package books;

class IslandCounter {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int numIslandsCounter = 0;
        for (int row = 0; row < rows; row++) {
            char[] finalRow = grid[row];
            for (int col = 0; col < cols; ++col)
                if (finalRow[col] == '1') {
                    markTheLand(grid, row, col);
                    numIslandsCounter++;
                }
        }
        return numIslandsCounter;
    }


    private void markTheLand(char[][] grid, int row, int col) {
        grid[row][col] = '*';
        if (row > 0 && grid[row - 1][col] == '1')
            markTheLand(grid, row - 1, col);
        if (row + 1 < grid.length && grid[row + 1][col] == '1')
            markTheLand(grid, row + 1, col);
        if (col > 0 && grid[row][col - 1] == '1')
            markTheLand(grid, row, col - 1);
        if (col + 1 < grid[row].length && grid[row][col + 1] == '1') {
            markTheLand(grid, row, col + 1);
        }

    }
}