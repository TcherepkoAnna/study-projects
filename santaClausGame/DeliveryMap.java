package PurdueX.course_2_loopsAndDataStructures.holidayProject;

import java.io.File;
import java.util.Scanner;

/**
 * used to read in a map from a text file, and will feature the following symbols.
 a. ‘S’ – the starting position for Scanner Claus
 b. ‘X’ – an impassable obstacle
 c. ‘C’ – extra carrots for Scanner Claus’ reindeers
 d. Numbers from 1-9 – locations for Scanner Claus to deliver presents to, in any order that it sees fit.

 */
public class DeliveryMap {
    private char map[][];
    private int rows;
    private int cols;
    private int numPresents;

    public DeliveryMap(String fileName) {
        Scanner s;
        try {
            s = new Scanner(new File(fileName));
            this.rows = s.nextInt();
            this.cols = s.nextInt();
            this.numPresents = s.nextInt();
            map = new char[rows][cols];
            String mapInput = s.nextLine();
            for (int r = 0; r < rows; r++) {
                mapInput = s.nextLine();
                for (int c = 0; c < cols; c++) {
                    map[r][c] = mapInput.charAt(c);
                }
            }
        } catch (Exception e) {
            System.err.print(e);
        }
    }

    public int getNumPresents() {
        return numPresents;
    }

    public char getPosition(Coordinates c) {
        return map[c.getRow()][c.getColumn()];
    }


    public boolean setPosition(Coordinates c, boolean previous) {
        if (map[c.getRow()][c.getColumn()] == 'X') {
            return false;
        } else if (previous) {
            map[c.getRow()][c.getColumn()] = '.';
        } else {
            map[c.getRow()][c.getColumn()] = 'S';
        }
        return true;
    }

    public Coordinates find(char toFind) {
        Coordinates pos = null;
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                if (map[r][c] == toFind) {
                    pos = new Coordinates(r,c);
                    return pos;
                }
            }
        }
        return pos;
    }

    public char[][] getMap() {
        return map;
    }

    public String printMap() {
        String out = "";
        for (char[] r : map) {
            for (char c : r) {
                out += c;
            }
            out += "\n";
        }
        return out;
    }

}