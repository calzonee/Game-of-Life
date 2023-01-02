import java.util.Scanner;

public class gameOfLife {
    
    // Input Method
    public static int[] getUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of rows: ");
        int row = sc.nextInt();
        System.out.println("Enter number of columns: ");
        int col = sc.nextInt();
        int[] size = {row, col};
        return size;
    }

    // initialise new boolean grid
    public static boolean[][] newBooleanGrid(int row, int col) {
        boolean[][] grid = new boolean[row][col];
        return grid;
    }

    // create pattern on grid 
    public static boolean[][] addPattern(boolean[][] grid, int row, int col) {
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[row-1].length; ++j) {
                if(j % (i + 2) == 0){
                    grid[i][j] = true;
                }
            }
        }
        return grid; 
    }

    // Method to print a grid
    public static void printGrid(boolean[][] grid) {
        for (boolean[] i : grid) {
            for (boolean j : i) {
                System.out.print(j ? "+" : ".");
            }
            System.out.println("");
        }
    }

    // Method will contain rules of Life
    public static boolean[][] nextGen(boolean[][] grid, int row, int col) {
        boolean[][] futureGen = newBooleanGrid(row, col);
        for (int iG = 0; iG < grid.length; ++iG) {
            for (int jG = 0; jG < grid[row-1].length; ++jG) {
                // iN indexes through the 3x3 neighbourhood
                int countLife = 0;
                for (int iN = -1; iN < 2; ++iN) {
                    for (int jN = -1; jN < 2; ++jN){
                        if ((iG + iN >= 0 && iG + iN < row) 
                        && (jG + jN >= 0 && jG + jN < col)
                        && (grid[iG+iN][jG+jN]))
                            ++countLife;
                    }
                }
                // substract 1 for the center cell
                if (grid[iG][jG]) countLife -= 1;

                // Rules of Life
                // Cell dies due to lonliness or over population
                if ((grid[iG][jG]) && (countLife < 2)
                || (grid[iG][jG]) && (countLife > 3))
                    futureGen[iG][jG] = false;
 
                // A new cell is born
                else if ((countLife == 3))
                    futureGen[iG][jG] = true;
 
                // Remains the same
                else
                    futureGen[iG][jG] = grid[iG][jG];
            }
        }

        return futureGen;
    }

    public static void main (String[] args) {

        // Input
        int[] size = getUserInput();
        int row = size[0];
        int col = size[1];

        // 2D array
        boolean[][] grid = newBooleanGrid(row, col);
        
        // add pattern
        addPattern(grid,row,col);

        // print grid with pattern (first generation)
        System.out.println("Input:");
        printGrid(grid);

        // nextGen
        boolean[][] futureGen = nextGen(grid, row, col);

        // print future Generation
        System.out.println("_______");
        System.out.println("Output:");
        printGrid(futureGen);
    }
}
