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
        System.out.println("Enter x to know the number of cells with x neighbours: ");
        int x = sc.nextInt();

        Grid life = new Grid(row, col);

        for (int i = 0; i < gen; ++i) {
            System.out.println("Generation " + i + ":");
            life.print();
            life.coordinates(row - 1, col - 1, x);
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

    public int neighbours(int iG, int jG){
        int n = 0;
        for (int iN = -1; iN < 2; ++iN) {
            for (int jN = -1; jN < 2; ++jN){
                int i = (grid.length + iG + iN) % grid.length;
                int j = (grid[iG].length + jG + jN) % grid[iG].length;
                if (grid[i][j] && !(i == iG && j == jG))
                    ++n;
            }
        }
        return n;
    }

    public void nextGen() {
        boolean[][] tmp = new boolean[grid.length][grid[0].length];
         // loop through every cell
         for (int iG = 0; iG < grid.length; ++iG) {         // rows
            for (int jG = 0; jG < grid[iG].length; ++jG) { // columns
                int countLife = neighbours(iG, jG);
                // Rules of Life
                // Cell dies due to lonliness or over population
                if ((grid[iG][jG]) && (countLife < 2 || countLife > 3))
                    tmp[iG][jG] = false;
                // A new cell is born
                else if ((countLife == 3) || grid[iG][jG])
                    tmp[iG][jG] = true;
            }
        }
        grid = tmp;
    }

    public void coordinates(int iG, int jG, int x){
        if (iG < 0)
            return;
        if (jG == 0)
            coordinates(iG - 1, grid[jG].length - 1, x);
        else
            coordinates(iG, jG - 1, x);
        if (neighbours(iG, jG) == x)
            System.out.println("Coordinates: " + iG + " / " + jG);
    }
}

class Stone{

}