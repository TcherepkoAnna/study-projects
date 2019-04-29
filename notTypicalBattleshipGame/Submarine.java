package PurdueX.course_4_polymorphismAndAdvancedDataStructures.notTypicalBattleshipGame;

public class Submarine extends ScoutBoat implements Attacker {

    private int numOfTorpedoes = 0; // records the current number of torpedoes

    public Submarine(int team, Coordinates location, int direction, int torpedoes) {
        super(team, location, direction, 3, 2);
        this.numOfTorpedoes = torpedoes;
    }

    @Override
    public String getActions() {
        String result = "Choose any of the following actions for the Submarine:\n" +
                " 1. Move\n" +
                " 2. Turn left\n" +
                " 3. Turn right\n" +
                " 4. Submerge\n";
        if (numOfTorpedoes > 0) {
            result += " 5. Fire torpedoes\n";
        }
        return result;
    }

    @Override
    public String act(int[] choices, World world) {
        int choice = choices[0];
//        for (int i = 0; i < choices.length; i++) {
//            choice = choices[i];
        switch (choice) {
            case 1:
                return move(world);
            case 2:
                return turn(-1);
            case 3:
                return turn(1);
            case 4:
                return submerge(world);
            case 5:
                return attack(world);
            default:
                return "Invalid choice number.\n";
        }
    }

    @Override
    public String getID() {
        return "" + 'S' + getTeam();
    }

    /*
    If the number of torpedoes is greater than zero,
    and an enemy boat appears in the direction the Submarine is facing (and within its vision),
    call that Boat’s takeHit method
    with an attack strength equal to a random integer between 1 and the current health of the boat being attacked.
    A Submarine with no torpedoes remaining cannot attack.
    If fire torpedoes occurs with no boats in range, the Submarine forfeits its action.
    "Fire torpedoes!" + results of Boat.takeHit method
    "S1 has no torpedoes remaining."
    "There are no boats in range currently."
     */
    @Override
    public String attack(World world) {
        if (this.numOfTorpedoes<=0) return  this.toString() + " has no torpedoes remaining.";
        Coordinates currC = this.getLocation();
        for (int i = 0; i < getVision(); i++) {
            currC = world.getAdjacentLocation(currC, this.getDirection());
            if (!world.isLocationValid(currC)) break;
            if (world.isLocationOccupied(currC)) {
                Boat b = world.getOccupant(currC);
                if (b.getTeam()== this.getTeam() || b.getHealth() <= 0) continue;
                numOfTorpedoes--;
                return "Fire torpedoes! " + b.takeHit((int)(Math.random()*b.getHealth() + 1));
            }
        }
        return "There are no boats in range currently.\n";
    }


    public String submerge(World world) {
        Coordinates currCS = this.getLocation();
        Coordinates newCs;
        int currX = currCS.getX();
        int currY = currCS.getY();
        int newX;
        int newY;
        do {
            do {
                newX = (int) (Math.random() * world.getWidth());
            } while (newX < currX + 2 && newX > currX - 2);
            do {
                newY = (int) (Math.random() * world.getHeight());
            } while (newY < currY + 2 && newY > currY - 2);
            newCs = new Coordinates(newX, newY);
        } while (world.isLocationOccupied(newCs));
        if (world.setOccupant(this, newCs)) {
            world.setOccupant(null, currCS);
            this.setLocation(newCs);
        }

        return String.format("%s moves from %s to %s.\n", this.toString(), currCS.toString(), newCs.toString());
    }
}
