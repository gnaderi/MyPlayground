package verizonmedia;

public class LandDiscovery {
    public static void main(String[] args) {
        char[][] input = new char[][]{
                {'0', '1', '0', '1', '0'},
                {'0', '0', '1', '1', '1'},
                {'1', '0', '0', '1', '0'},
                {'0', '1', '1', '0', '0'},
                {'1', '0', '1', '0', '1'}
        };
        System.out.println(numIslands(input));
        input = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslands(input));
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int rowSize = grid.length;
        int colSize = grid[0].length;

        int count = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    discoverLandTiles(grid, i, j);
                }
            }
        }

        return count;
    }

    public static void discoverLandTiles(char[][] grid, int i, int j) {

        //Check it is not crossed the grid!
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1')
            return;

        grid[i][j] = '*';
        discoverLandTiles(grid, i - 1, j);
        discoverLandTiles(grid, i + 1, j);
        discoverLandTiles(grid, i, j - 1);
        discoverLandTiles(grid, i, j + 1);
    }
}
