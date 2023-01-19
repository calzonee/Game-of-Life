import java.util.Scanner;

public class GameOfLife {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of rows: ");
        int row = sc.nextInt();
        System.out.println("Enter number of columns: ");
        int col = sc.nextInt();
        System.out.println("Enter number of Generations: ");
        int gen = sc.nextInt();
        
        // ToDo: initialise Objects
        Grid life = new Grid(row, col);
        for (int i = 0; i < gen; ++i) {
            System.out.println("Generation " + i + ":");
            life.print();
            life.nextGen();
        }
    }
}

class Grid{
    boolean[][] grid;

    Grid(int row, int col) {
        // random(row, col);
        pattern(row, col);
    }

    public void random(int row, int col) {
        grid = new boolean[row][col];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j)
                grid[i][j] = Math.random() < 0.5 ? true : false;
        }
    }

    public void pattern(int row, int col){
        grid = new boolean[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j)
                grid[i][j] = j % (i + 2) == 0 ? true : false;
        }
    }

    public void print() {
        for (boolean[] i : grid) {
            for (boolean j : i)
                System.out.print(j ? "+" : ".");
            System.out.println("");
        }
    }

    public void nextGen() {
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
                    grid[iG][jG] = false;

                // A new cell is born
                else if ((countLife == 3))
                    grid[iG][jG] = true;

                // Remains the same
                else
                    grid[iG][jG] = grid[iG][jG];
            }
        }
    }
}

class Stone{

}