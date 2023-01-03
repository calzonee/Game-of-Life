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

    // create grid with pattern
    public static boolean[][] gridPattern(int row, int col) {
        boolean[][] grid = new boolean[row][col];
        
        // adding the pattern
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if(j % (i + 2) == 0)
                    grid[i][j] = true;
            }
        }
        return grid; 
    }

    // create random grid 
    public static boolean[][] gridRandom(int  row, int col) {
        boolean[][] grid = new boolean[row][col];

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (java.lang.Math.random() < 0.5)
                    grid[i][j] = true;
            }
        }
        return grid;
    }// ToDo: find out how to code gridPattern() and gridRandom() into one method

    // Method to print a grid
    public static void printGrid(boolean[][] grid) {
        for (boolean[] i : grid) {
            for (boolean j : i)
                System.out.print(j ? "+" : ".");
            System.out.println("");
        }
    }

    // print next Generation
    public static void printGenerations(boolean[][] grid, int x) {
        boolean[][] futureGen = nextGen(grid);

        // x is the number of generations you want to print
        for (int i = 1; i <= x; ++i) {
            System.out.println("_____________");
            System.out.println("Generation " + i + ":");
            printGrid(futureGen);
            futureGen = nextGen(futureGen);
        }
    }

    // nextGen() contains rules of Life
    public static boolean[][] nextGen(boolean[][] grid) {
        boolean[][] futureGen = new boolean[grid.length][grid[0].length];

        // loop through every cell
        for (int iG = 0; iG < grid.length; ++iG) {         // rows
            for (int jG = 0; jG < grid[iG].length; ++jG) { // columns

                // indexing through every neighbour iN of the cell
                int countLife = 0;
                for (int iN = -1; iN < 2; ++iN) {
                    for (int jN = -1; jN < 2; ++jN){
                        if (grid[(grid.length + iG + iN) % grid.length]
                        [(grid[iG].length + jG + jN) % grid[iG].length])
                            ++countLife;
                    }
                }
                // subtract 1 for the center cell
                if (grid[iG][jG]) countLife -= 1;

                // Rules of Life
                // Cell dies due to lonliness or over population
                if ((grid[iG][jG]) && (countLife < 2 || countLife > 3))
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

        // // create grid with pattern
        boolean[][] grid = gridRandom(size[0], size[1]);

        // print grid with pattern (first generation)
        System.out.println("Generation 0:");
        printGrid(grid);

        // // nextGen
        printGenerations(grid, 20);
    }
}