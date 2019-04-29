package PurdueX.course_4_polymorphismAndAdvancedDataStructures.notTypicalBattleshipGame;

public class World {

    private Boat[][] map;
    public static final int NORTH = 0;
    public static final int NORTHEAST = 1;
    public static final int EAST = 2;
    public static final int SOUTHEAST = 3;
    public static final int SOUTH = 4;
    public static final int SOUTHWEST = 5;
    public static final int WEST = 6;
    public static final int NORTHWEST = 7;
    public static final String[][] directions =
            {{"N", "" + (char) 8593}, {"NE", "" + (char) 8599},
                    {"E", "" + (char) 8594}, {"SE", "" + (char) 8600},
                    {"S", "" + (char) 8595}, {"SW", "" + (char) 8601},
                    {"W", "" + (char) 8592}, {"NW", "" + (char) 8598}};


    public World(int width, int height) {
        if (width < 4) width = 4;
        else if (width > 10) width = 10;
        if (height < 4) height = 4;
        else if (height > 10) height = 10;

        //TODO should be map = new Boat[width][height]; ???? change everywhere to map[c.getColumn()][c.getRow()] ???
        map = new Boat[height][width];
        //TODO ??? set all to null
    }


    public String drawTeamMap(Boat[] teamBoats, int view) {

        String drawMap = "";
        String[][] teamView = new String[this.getHeight()][this.getWidth()];
        int yStart, yEnd, xStart, xEnd;
        Coordinates boatLocation;

        for (int r = 0; r < teamView.length; r++) {
            for (int c = 0; c < teamView[0].length; c++) {
                teamView[r][c] = "###";
            }
        }

        if (view != 1) {
            for (Boat b : teamBoats) {
                if (b.getHealth() > 0) {
                    int vision = b.getVision();
                    boatLocation = b.getLocation();
                    int bY = boatLocation.getY();
                    int bX = boatLocation.getX();
                    yStart = bY - vision;
                    yEnd = bY + vision;
                    xStart = bX - vision;
                    xEnd = bX + vision;
                    if (yStart < 0) yStart = 0;
                    if (yEnd > getHeight() - 1) yEnd = getHeight() - 1;
                    if (xStart < 0) xStart = 0;
                    if (xEnd > getWidth() - 1) xEnd = getWidth() - 1;

                    for (int y = yStart; y <= yEnd; y++) {
                        for (int x = xStart; x <= xEnd; x++) {
                            Boat anyBoat = map[y][x];
                            if (anyBoat == null) {
                                teamView[y][x] = "~~~";
                            } else if (anyBoat.getHealth() <= 0)
                                teamView[y][x] = " ~ ";
                            else {
                                teamView[y][x] = (view == 2 ? directions[anyBoat.getDirection()][1] : anyBoat.getHealth()) + anyBoat.toString();
                            }
                        }
                    }
                }
            }
        }

        for (int y = -1; y < this.getHeight(); y++) {
            drawMap += (char) (y + 65) + " ";
            for (int x = 0; x < this.getWidth(); x++) {
                if (y == -1) {
                    drawMap += " " + (x + 1) + ((x < 9) ? " " : "");
                } else {
                    drawMap += teamView[y][x];
                }
            }
            drawMap += "\n";
        }
        return drawMap;
    }

    //    If the location is within the boundaries of the map, return true; otherwise, false.
    public boolean isLocationValid(Coordinates c) {
        if (c == null || c.getY() < 0 || c.getY() > getHeight() - 1 || c.getX() < 0 || c.getX() > getWidth() - 1)
            return false;
        return true;
    }

    //Returns true if the location is not null; otherwise, false.
    public boolean isLocationOccupied(Coordinates c) {
        //TODO should or should not check coordinates for validity ???
        if (isLocationValid(c)) {
            if (map[c.getY()][c.getX()] == null) return false;
            else return true;
        } else throw new IllegalArgumentException("Coordinates are out of boundaries. ");
    }

    //return the Boat in that location of the map (or null if unoccupied).
    public Boat getOccupant(Coordinates c) {
        if (isLocationValid(c)) {
            return map[c.getY()][c.getX()];
        } else throw new IllegalArgumentException("Coordinates are out of boundaries. ");
    }

    //If unoccupied, place the Boat parameter in that location.
    // Returns true if the location was set; otherwise, false.
    public boolean setOccupant(Boat boat, Coordinates c) {
        if (!isLocationOccupied(c) || boat == null) {
            map[c.getY()][c.getX()] = boat;
            return true;
        } else return false;
    }

    /*
    Returns the Coordinates of the location on the map adjacent to the one provided and in the direction provided
    (using the constants defined above).
    ??????? If the Coordinates input is not a valid location, return null.
     */
    public Coordinates getAdjacentLocation(Coordinates c, int dir) {
        if (isLocationValid(c)) {
            int x = c.getX();
            int y = c.getY();
            if (dir == NORTHEAST || dir == EAST || dir == SOUTHEAST) {
                x += 1;
            } else if (dir == SOUTHWEST || dir == WEST || dir == NORTHWEST) {
                x += -1;
            }
            if (dir == NORTHWEST || dir == NORTH || dir == NORTHEAST) {
                y += -1;
            } else if (dir == SOUTHEAST || dir == SOUTH || dir == SOUTHWEST) {
                y += 1;
            }
            Coordinates newCoordinates = new Coordinates(x, y);
//            return newCoordinates;
            if (isLocationValid(newCoordinates)) return newCoordinates;
            else return null;
//                throw new IllegalArgumentException("New coordinates are out of boundaries. ");
            //TODO If the Coordinates input is not a valid location, return null.
        } else return null;
//            throw new IllegalArgumentException("Given coordinates are out of boundaries. ");
    }

    public int getHeight() {
        return map.length;
    }

    public int getWidth() {
        return map[0].length;
    }
}