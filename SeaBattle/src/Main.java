import java.util.Random;
import java.util.Scanner;

/**
 * How to play:
 * //  1.1 Y coordinate is vertically;
 * //  1.2 X coordinate is horizontally;
 */
public class Main {
    public static final int EMPTY = 0;
    public static final int SHIP = 1;
    public static final int DEAD = 2;
    public static final int MISS = 3;
    public static final int MAX_SHIP = 10;
    public static final int MAX_TURN = 30;
    public static final int SIZE = 10;
    public static final int warField[][] = new int[SIZE][SIZE];

    public static void main(String[] args) {
        deployShip();
        play();
    }

    public static void printArray(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.format("%4d", arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public static void isWin(int turn) {
        switch (turn) {
            case MAX_SHIP:
                System.out.println("You win");
                break;
            default:
                System.out.println("You lose");
                break;
        }
    }

    public static void deployShip() {
        Random random = new Random();
        for (int i = 0; i < MAX_SHIP; i++) {
            int shipPlace1 = random.nextInt(SIZE);
            int shipPlace2 = random.nextInt(SIZE);
            if (Main.warField[shipPlace1][shipPlace2] == SHIP) {
                i--;
            }
            Main.warField[shipPlace1][shipPlace2] = SHIP;
        }
    }

    public static void play() {
        int turnCount = 0;
        int deadCount = 0;
        Scanner scanner = new Scanner(System.in);
        while (turnCount < MAX_TURN) {
            printArray(Main.warField);
            int turnleft = MAX_TURN - turnCount;
            System.out.println("Choose coordinates: Y(vertically) and X(horizontally)"
                    + "\n" + turnleft + " turns left");
            int fireY = scanner.nextInt() - 1;
            int fireX = scanner.nextInt() - 1;
            switch (Main.warField[fireY][fireX]) {
                case EMPTY:
                    turnCount++;
                    Main.warField[fireY][fireX] = MISS;
                    System.out.println("You missed" + "\n");
                    break;
                case SHIP:
                    turnCount++;
                    deadCount++;
                    Main.warField[fireY][fireX] = DEAD;
                    System.out.println("You hit the ship" + "\n");
                    break;
                default:
                    System.out.println("You have already fired this cell. Try again");
                    break;
            }
            if (deadCount == MAX_SHIP) break;
        }
        scanner.close();
        isWin(deadCount);
    }
}

