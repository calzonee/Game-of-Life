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
        // System.out.println("Enter x to output coordinates of cells with x neighbours: ");
        // int x = sc.nextInt();

        Grid life = new Grid(row, col);

        for (int i = 0; i < gen; ++i) {
            System.out.println();
            System.out.println("Generation " + i + ":");
            life.print();
            // life.coordinates(row - 1, col - 1, x);
            life.nextGen();
        }
    }
}

class Grid{
    int[][] grid;
    
    Grid(int row, int col) {
        // random(row, col);
        pattern(row, col);
    }

    public void random(int row, int col) {
        grid = new int[row][col];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j)
                grid[i][j] = Math.random() < 0.5 ? 1 : 0;
        }
    }

    public void pattern(int row, int col){
        grid = new int[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j)
                grid[i][j] = j % (i + 2) == 0 ? 1 : 0;
        }
    }

    public void print() {
        String[] age = {" ", ".", "o", "O", "*"};
        for (int[] i : grid) {
            for (int j : i)
                System.out.print(j > 3 ? age[4] : age[j]);
            System.out.println("");
        }
    }

    public int neighbours(int iG, int jG){
        int n = 0;
        for (int iN = -1; iN < 2; ++iN) {
            for (int jN = -1; jN < 2; ++jN){
                int i = (grid.length + iG + iN) % grid.length;
                int j = (grid[iG].length + jG + jN) % grid[iG].length;
                if (grid[i][j] > 0 && !(i == iG && j == jG))
                    ++n;
            }
        }
        return n;
    }

    public void nextGen() {
        int[][] tmp = new int[grid.length][grid[0].length];
         for (int iG = 0; iG < grid.length; ++iG) {         // rows
            for (int jG = 0; jG < grid[iG].length; ++jG) { // columns
                int countLife = neighbours(iG, jG);
                // Rules of Life
                // Cell dies due to lonliness or over population
                if ((grid[iG][jG]) > 0 && (countLife < 2 || countLife > 3))
                    tmp[iG][jG] = 0;
                // A new cell is born
                else if ((countLife == 3) || grid[iG][jG] > 0)
                    tmp[iG][jG] = grid[iG][jG] + 1;
            }
        }
        grid = tmp;
    }

    int x_cell = 0;
    public void coordinates(int iG, int jG, int x){
        if (iG < 0){
            x_cell = 0;
            return;
        }
        if (jG == 0)
            coordinates(iG - 1, grid[jG].length - 1, x);
        else
            coordinates(iG, jG - 1, x);
        if (neighbours(iG, jG) == x){
            ++x_cell;
            System.out.println(x_cell + ". Coordinate: " + iG + " / " + jG);
        }
    }
}

class Stone{
    int age;

    public void print() {}

    public void countNeighbour() {}

    public void isAlive(){}

    public void computeNext() {}
}