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
        boolean[][] futureGen = newBooleanGrid(row, col);
        
        for (int iG = 0; iG < grid.length; ++iG) {
            for (int jG = 0; jG < grid[row-1].length; ++jG) {
                // iC indexes through the 3x3 Cell
                int countLife = 0;
                for (int iC = -1; iC < 2; ++iC) {
                    for (int jC = -1; jC < 2; ++jC){
                        if ((iG + iC >= 0 && iG + iC < row) && (jG + jC >= 0 && jG + jC < col)){
                            ++countLife;
                        }
                    }
                }
                System.out.println(countLife);
                // substract 1 for the center cell
                if (grid[iG][jG]) countLife -= 1;

                // Rules of Life
                // Cell is lonely and dies
                if ((grid[iG][jG]) && (countLife < 2))
                    futureGen[iG][jG] = false;
 
                // Cell dies due to over population
                else if ((grid[iG][jG]) && (countLife > 3))
                    futureGen[iG][jG] = false;
 
                // A new cell is born
                else if ((grid[iG][jG]) && (countLife == 3))
                    futureGen[iG][jG] = true;
 
                // Remains the same
                else
                    futureGen[iG][jG] = grid[iG][jG];
            }
        }

        // print future Generation
        System.out.println("_______");
        System.out.println("Output:");
        printGrid(futureGen);

        //System.out.println(countLife);


    }
}
