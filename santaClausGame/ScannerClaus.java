package PurdueX.course_2_loopsAndDataStructures.holidayProject;

import java.util.ArrayList;

/**
 * Your goal is to complete an ScannerClaus map with a minimum number of steps,
 */

public class ScannerClaus {
    public static final char DOWN = 'S';
    public static final char UP = 'W';
    public static final char RIGHT = 'D';
    public static final char LEFT = 'A';

    private int numPresents;
    private int numCarrots; // is initialized to 5 times the number of presents
    private Coordinates currentPos; // the coordinates of Scanner Claus on the map.
    ArrayList<Coordinates> presents;
    ArrayList<Coordinates> carrots;
    Coordinates closestPresent;
    Coordinates closestCarrot;
    private String path; // will contain the list of all moves (coded as key presses: W,A,S,D) made by Scanner Claus.

    public ScannerClaus(DeliveryMap dMap) {
        this.numPresents = dMap.getNumPresents();
        this.presents = getAllPresentsArray(dMap.getMap());
        this.numCarrots = 4 * this.numPresents;
        this.carrots = getAllCarrotsArray(dMap.getMap());
        currentPos = dMap.find('S');
        this.path = "";
    }

    public int getNumCarrots() {
        return this.numCarrots;
    }

    public int getNumPresents() {
        return this.numPresents;
    }

    public String getPath() {
        return this.path + "\nSteps: " + this.path.length();
    }

    public Coordinates getTargetCrdnts(char dir) {
        switch (dir) {
            case DOWN:
                return new Coordinates(currentPos.getRow() + 1, currentPos.getColumn());
            case UP:
                return new Coordinates(currentPos.getRow() - 1, currentPos.getColumn());
            case RIGHT:
                return new Coordinates(currentPos.getRow(), currentPos.getColumn() + 1);
            case LEFT:
                return new Coordinates(currentPos.getRow(), currentPos.getColumn() - 1);
            default:
                return new Coordinates(currentPos.getRow(), currentPos.getColumn());
        }
    }

    private char turnRight(char dir) {
        switch (dir) {
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                return 0;
        }
    }

    public char chooseDir(Coordinates closestPresent, char previous) {
        if (previous == 0 || previous == RIGHT || previous == LEFT) {
            if (closestPresent.getRow() > currentPos.getRow()) return DOWN;
            else if (closestPresent.getRow() < currentPos.getRow()) return UP;
            else if (closestPresent.getColumn() > currentPos.getColumn()) return RIGHT;
            else return LEFT;
        } else {
            if (closestPresent.getColumn() > currentPos.getColumn()) return RIGHT;
            else if (closestPresent.getColumn() < currentPos.getColumn()) return LEFT;
            else if (closestPresent.getRow() > currentPos.getRow()) return DOWN;
            else return UP;
        }
    }

    public void tryMove(DeliveryMap dMap, Coordinates closestItem) {

        char dir = chooseDir(closestItem, path == "" ? '0' : path.charAt(path.length() - 1));
        do {
            if (dMap.getPosition(getTargetCrdnts(dir)) == 'X') {
                dir = turnRight(dir);
            }
//            if (dMap.getPosition(getTargetCrdnts(dir)) == '.') {
//                //choose opposite direction(turn right twice)
//                dir = turnRight(turnRight(dir));
//            }
        } while (!move(dMap, dir));
    }


    public void autoDeliver(DeliveryMap dMap) {
        do {
            System.out.println(dMap.printMap());
            System.out.println(this);
            closestPresent = getClosest(presents);
            closestCarrot = getClosest(carrots);
//            if (getStepsNeeded(closestPresent) > numCarrots) {
//                tryMove(dMap, closestCarrot);
//            }
            int toPresent = getStepsNeeded(closestPresent);
            int toCarrot = getStepsNeeded(closestCarrot);
            if (toCarrot < toPresent) {
                tryMove(dMap, closestCarrot);
            } else {
                tryMove(dMap, closestPresent);
            }
        } while (getNumCarrots() > 0 && getNumPresents() > 0);
    }

    private Coordinates getClosest(ArrayList<Coordinates> items) {
        int closest = 0;
        int min = getStepsNeeded(items.get(closest));
        for (int i = 1; i < items.size(); i++) {
            int stepsNeeded = getStepsNeeded(items.get(i));
            if (stepsNeeded < min) {
                min = stepsNeeded;
                closest = i;
            }
        }
        return items.get(closest);
    }

    private int getStepsNeeded(Coordinates item) {
        return Math.abs(item.getRow() - currentPos.getRow()) + Math.abs(item.getColumn() - currentPos.getColumn());
    }

    private ArrayList<Coordinates> getAllCarrotsArray(char[][] m) {
        ArrayList<Coordinates> carrots = new ArrayList<Coordinates>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 'C') {
                    carrots.add(new Coordinates(i, j));
                }
            }
        }
        return carrots;
    }

    private ArrayList<Coordinates> getAllPresentsArray(char[][] m) {
        ArrayList<Coordinates> presents = new ArrayList<>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if ((int) m[i][j] < 58 && (int) m[i][j] > 48) {
                    presents.add(new Coordinates(i, j));
                }
            }
        }
        return presents;
    }

    public boolean move(DeliveryMap dMap, char dir) {
        Coordinates targetCrdnts = getTargetCrdnts(dir);
        char targetChar = dMap.getPosition(targetCrdnts);
        if (targetChar == 'C') {
            this.numCarrots += 20;
            carrots.remove(closestCarrot);
        } else if ((int) targetChar > 48 && (int) targetChar < 58) {
            this.numPresents--;
            presents.remove(closestPresent);
        }
        if (dMap.setPosition(targetCrdnts, false)) {
            dMap.setPosition(currentPos, true);
            currentPos = targetCrdnts;
            this.path += dir;
            this.numCarrots--;
        }
        return true;
    }


    public String toString() {
        String msg = "Ho ho ho! (" + currentPos.getRow() + "," + currentPos.getColumn() + ")\n";
        msg += "Carrots = " + this.numCarrots + "; Presents = " + this.numPresents + "\n";
        msg += getPath();
        return msg;
    }

}
