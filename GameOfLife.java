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
    Stone[][] grid;
    
    Grid(int row, int col) {
        random(row, col);
        // pattern(row, col);
    }

    public void random(int row, int col) {
        grid = new Stone[row][col];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j)
                grid[i][j] = new Stone(Math.random() < 0.5 ? 1 : 0, i, j);
                                       // evtl boolean, ein stein wird geboren oder nicht
        }
    }

    public void pattern(int row, int col){
        grid = new Stone[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j)
                grid[i][j] = new Stone(j % (i + 2) == 0 ? 1 : 0, i, j);
        }
    }

    public void print() {
        for (Stone[] i : grid) {
            for (Stone j : i)
                j.printStone();
            System.out.println();
        }
    }

    public void nextGen() {
        for (int iG = 0; iG < grid.length; ++iG) {
            for (int jG = 0; jG < grid[iG].length; ++jG) {
                grid[iG][jG].computeNext(grid);
            }
        }
        for (int iG = 0; iG < grid.length; ++iG) {
            for (int jG = 0; jG < grid[iG].length; ++jG) {
                grid[iG][jG].setNext();
            }
        }
    }

    int x_cell = 0; // überarbeiten
    public void coordinates(int iG, int jG, int x){
        if (iG < 0){
            x_cell = 0;
            return;
        }
        if (grid[iG][jG].countNeighbours(grid) == x){
            ++x_cell;
            System.out.println(x_cell + ". Coordinate: " + iG + " / " + jG);
        }
        if (jG == 0)
            coordinates(iG - 1, grid[jG].length - 1, x);
        else
            coordinates(iG, jG - 1, x);

    }
}

class Stone{
    int age, row, col;
    int nextAge;
    // array aus 8 nachbarn 

    public Stone(int n, int i, int j) {
        age = n; //
        row = i; // i,j kann durch array ersetzt werden
        col = j;
    }

    public void printStone() {
        String[] ageIndicator = {" ", ".", "o", "O", "*"};//
        System.out.print(age > 3 ? ageIndicator[4] : ageIndicator[age]);
    }

    public int countNeighbours(Stone[][] grid) {
        int n = 0;
        for (int iN = -1; iN < 2; ++iN) {
            for (int jN = -1; jN < 2; ++jN){
                int i = (grid.length + row + iN) % grid.length;
                int j = (grid[row].length + col + jN) % grid[row].length;
                if (grid[i][j].isAlive() && !(i == row && j == col))
                    ++n;
            }
        }
        return n;
    }

    public void computeNext(Stone[][] grid) {
        int n = countNeighbours(grid);
        // A new cell is born
        if ((n == 3) || isAlive() && n == 2)
            survive();
        // Cell dies due to lonliness or over population
        else
            die();
    }

    public void setNext() {
        age = nextAge;
    }

    public boolean isAlive() {
        return age > 0;
    }

    public void die() {
        nextAge = 0;
    }

    public void survive() {
        nextAge = age + 1;
    }
}