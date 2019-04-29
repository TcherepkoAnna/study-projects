package PurdueX.course_4_polymorphismAndAdvancedDataStructures.notTypicalBattleshipGame;

public abstract class Boat {

    private int team; //  refers to the team number of this Boat
    private Coordinates location;
    private int direction; //as defined by the constants in the World object
    private int health; //holds the number of hits that the Boat can endure before sinking
    private int strength; //holds the number of hits that the Boat can cause during an attack
    private int vision; //holds the number of spaces in any direction that the Boat can view on the map


    public Boat(int team, Coordinates location, int direction, int health, int strength, int vision) {
        this.team = team;
        //TODO use the methods of Coordinates to make sure that the x and y are valid
        setLocation(location);
        this.direction = direction;
        this.health = health;
        this.strength = strength;
        this.vision = vision;
    }

    public String takeHit(int strength) {
        String result = "";
        if (this.getHealth() > 0) {
            this.health -= strength;
            if (this.getHealth() <= 0) {
                result = String.format("%s has been sunk!\n", this.toString());
            } else {
                result = String.format("%s takes %d damage.\n", this.toString(), strength);
            }
        } else result = String.format("%s has already been sunk.\n", this.toString());
        return result;
    }

    public String turn(int turn) {
        if (turn != -1 && turn != 1)
            throw new IllegalArgumentException("Wrong turn argument - should be either 1 or -1.\n");
        this.direction += turn;
        if (this.getDirection() == 8) this.direction = 0;
        else if (this.getDirection() == -1) this.direction = 7;
        return String.format("%s turned %s, now facing %s.\n",
                this.toString(), (turn == 1 ? "right" : "left"), World.directions[this.getDirection()][0]);
    }

    public String move(World world) {
        String result = "";
        Coordinates adjacent = world.getAdjacentLocation(getLocation(), getDirection());

        if (world.isLocationValid(adjacent)) {
            if (world.isLocationOccupied(adjacent)) {
                result = String.format("%s cannot move to %s as it is occupied.\n", this.toString(), adjacent.toString());
            } else {
                if (world.setOccupant(this, adjacent)) {
                    if (world.setOccupant(null, location)) {
                        result = String.format("%s moves from %s to %s.\n", this.toString(), location.toString(), adjacent.toString());
                        setLocation(adjacent);
                    }
                }
            }
        } else {
            result = String.format("%s cannot move off the map.\n", this.toString());
        }
        return result;
    }

    //returns a String representing all of the options available to the Boat.
    public abstract String getActions();

    //will return a String reporting on the result of the action selected.
    public abstract String act(int[] choices, World world);

    /*
    assumes the input location is valid
     */
    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public abstract String getID();

    public int getTeam() {
        return team;
    }

    public Coordinates getLocation() {
        return location;
    }

    // should return the symbol used to refer to the given direction (look for “UTF-8 arrows” on the Internet).
    public int getDirection() {
        return direction;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getVision() {
        return vision;
    }

    /*String consisting of the ID of the boat and the team (see example below).
      "B1"*/
    @Override
    public String toString() {
        return getID();
    }
}
