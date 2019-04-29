package PurdueX.course_4_polymorphismAndAdvancedDataStructures.notTypicalBattleshipGame;

public class AircraftCarrier extends Boat implements Attacker {

    private boolean hasPlanes;

    public AircraftCarrier(int team, Coordinates location, int direction) {
        super(team, location, direction, 5, 1, 1);
        hasPlanes = true;
    }

    /*
     If the AircraftCarrier has planes available,
     call the Boat’s takeHit method for all Boats within the AircraftCarrier’s vision (in any direction).
     An AircraftCarrier with no planes remaining cannot attack.
     If launch planes occurs with no boats in range, the AircraftCarrier forfeits its action.
     At the end of every attack, the hasPlanes variable should be updated based on the following rule.
     For every Boat attacked, multiply the success rate by 80%.
     Generate a random decimal, and if the value is greater than the success rate,
     set the hasPlanes value to false.
     Display the final message in this instance.
     If no boats are in this range, the AircraftCarrier forfeits the current action.
     "Air raid!" + results of Boat.takeHit method
    "There are no boats in range currently."
    "A1 has no planes remaining."
    "The planes have been destroyed."
     */
    @Override
    public String attack(World world) {
        if (!hasPlanes) return this.toString() + " has no planes remaining.";
        String result = "";
        int attacksCount = 0;
        double successRate = 1.0;
        Coordinates currC = this.getLocation();
        Coordinates adj;
        int dir;
        Boat b;

        for (int i = 0; i < World.directions.length; i++) {
            dir = this.getDirection();
            for (int j = 0; j < getVision(); j++) {
                adj = world.getAdjacentLocation(currC, dir);
                if (!world.isLocationValid(adj)) {
//                    System.out.println("location is invalid\n");
                    break;
                }
                if (world.isLocationOccupied(adj)) {
                    b = world.getOccupant(adj);
                    if (b.getTeam() != this.getTeam() && b.getHealth() > 0) {
                        result += "Air raid! " + b.takeHit(getStrength());
                        attacksCount++;
                        successRate = updatePlanes(attacksCount, successRate);
                        if (!hasPlanes) return result + "The planes have been destroyed.\n";
                    }
                }
                currC = adj;
            }
            this.turn(1);
            currC = this.getLocation();
        }

        if (attacksCount == 0) return "There are no boats in range currently.\n";
        return result;
    }

    private double updatePlanes(int attacks, double successRate) {
// a) I made the assumption that this did intend to check after each individual boat was attacked
// (only reason I hadn't assumed that in the first place was I couldn't figure out how
// there was a non-100% "probability of success"
// because the only way for the aircraft carrier to fail was to run out of planes),
// so "multiply the success rate by 80%" seemed to be saying "multiply 1 * .80" or .80
// b) since we were calculating a probability,
// I assumed we wanted something between 0 and 1 (e.g. 0% to 100%) and used Math.random()
// to generate that and set hasPlanes to false if that probability generated was greater than 0.80
// c) I couldn't make heads or tails of this unless as above,
// so considered the "success rate" to be 1 * .80 (symbolizing 80%)
//
//        For every Boat attacked, multiply the success rate by 80%.
//        Generate a random decimal, and if the value is greater than the success rate,
//        set the hasPlanes value to false.
//        Display the final message in this instance.
        successRate *= 0.8;
        hasPlanes = successRate > Math.random();
//        System.out.println("successRate: " + successRate);
        return successRate;
    }

    @Override
    public String getActions() {
        return String.format("Choose any of the following actions for the Aircraft Carrier:\n" +
                " 1. Move\n" +
                " 2. Turn left\n" +
                " 3. Turn right %s", (hasPlanes ? "\n 4. Launch planes\n" : "\n")
        );
    }

    @Override
    public String act(int[] choices, World world) {
        int choice = choices[0];
        switch (choice) {
            case 1:
                return move(world);
            case 2:
                return turn(-1);
            case 3:
                return turn(1);
            case 4:
                return attack(world);
            default:
                return "Invalid choice number.\n";
        }
    }

    @Override
    public String getID() {
        return "" + 'A' + getTeam();
    }
}
