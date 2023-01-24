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
            life.nextGen();
            // life.coordinates(row - 1, col - 1, x, 0);
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
            for (int j = 0; j < grid[i].length; ++j) {
                int[] coord = {i, j};
                grid[i][j] = Math.random() < 0.99 ? new Stone(Math.random() < 0.5, coord) : (Math.random() < 0.8 ? new AlwaysStone(coord) : new NeverStone(coord));
            }
        }
    }

    public void pattern(int row, int col){
        grid = new Stone[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                int[] coord = {i, j};
                grid[i][j] = new Stone(j % (i + 2) == 0, coord);
            }
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
            for (int jG = 0; jG < grid[iG].length; ++jG)
                grid[iG][jG].computeNext(grid);
        }
        for (int iG = 0; iG < grid.length; ++iG) {
            for (int jG = 0; jG < grid[iG].length; ++jG)
                grid[iG][jG].setNext();
        }
    }

    // public void coordinates(int iG, int jG, int x, int count){
    //     if (iG < 0 || jG < 0 || iG >= grid.length || jG >= grid[iG].length) {
    //         return;
    //     }
    //     if(grid[iG][jG].aliveNeighbours == x) {
    //         count = count + 1;
    //         System.out.println(count + ". Coordinate: [" + iG + "]/[" + jG + "]");
    //     }
    //     if (jG == 0)
    //         coordinates(iG - 1, grid[jG].length - 1, x, count);
    //     else
    //         coordinates(iG, jG - 1, x, count);
    //     // coordinates(iG, jG - 1, x, count);
    //     // coordinates(iG - 1, jG, x, count);
    // }
}

class Stone{
    int age, nextAge, aliveNeighbours;
    int[] coord;
    int[] neighbours = new int[8];
    boolean born;

    public Stone(boolean born, int[] arr) {
        age = born ? 1 : 0;
        coord = arr;
    }

    public void printStone() {
        char[] ageIndicator = {' ', '.', 'o', 'O', '*'};
        System.out.print(age > 3 ? ageIndicator[4] : ageIndicator[age]);
    }

    public void listNeighbours (Stone[][] grid) {
        int[] iN = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] jN = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < iN.length; ++i){
            int y = (grid.length + coord[0] + iN[i]) % grid.length;
            int x = (grid[coord[0]].length + coord[1] + jN[i]) % grid[coord[0]].length;
            neighbours[i] = grid[y][x].age;
            // directly count living neighbours
            if (neighbours[i] > 0)
                ++aliveNeighbours;
        }
    }

    public void computeNext(Stone[][] grid) {
        listNeighbours(grid);
        if ((aliveNeighbours == 3) || isAlive() && aliveNeighbours == 2)
            survive();
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

class AlwaysStone extends Stone {
    public AlwaysStone(int[] arr) {
        super(true, arr);
    }
    
    @Override
    public void die() {
        survive();
    }
}

class NeverStone extends Stone {
    public NeverStone(int[] arr) {
        super(false, arr);
    }

    @Override
    public void setNext() {
        age = 0;
    }

    @Override
    public void printStone() {
        System.out.print("x");
    }
}