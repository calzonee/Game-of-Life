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
        life.print();
        // for (int i = 0; i < gen; ++i) {
        //     System.out.println("Generation " + i + ":");
            
        //     life = nextGen(life);
        // }
    }
}

class Grid{
    boolean[][] grid;

    Grid(int row, int col) {
        init(row, col);
    }

    public void init(int row, int col) {
        grid = new boolean[row][col];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (Math.random() < 0.5)
                    grid[i][j] = true;
            }
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
        
    }
}
