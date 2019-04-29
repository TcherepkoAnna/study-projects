package PurdueX.course_4_polymorphismAndAdvancedDataStructures.notTypicalBattleshipGame;

public class Battleship extends Boat implements Attacker {

    public Battleship(int team, Coordinates location, int direction) {
        super(team, location, direction, 4, 3, 1);
    }

    @Override
    public String getActions() {
        return "Choose any of the following actions for the Battleship:\n" +
                " 1. Move\n" +
                " 2. Turn left\n" +
                " 3. Turn right\n" +
                " 4. Attack\n";
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
        return "" + 'B' + getTeam();
    }

    /*
    A Battleship will attack twice any Boat that is within its vision and in the direction it is facing.
    Call the Boat’s takeHit method using the Battleship’s attack strength.
    If no boats are in this range, the Battleship forfeits the current action.
    "Fire cannons!" + results of Boat.takeHit method + results of Boat.takeHit method
    "There are no boats in range currently."
     */
    @Override
    public String attack(World world) {
        //TODO will attack twice very same Boat ?
        Coordinates currC = this.getLocation();
        for (int i = 0; i < getVision(); i++) {
            currC = world.getAdjacentLocation(currC, this.getDirection());
            if (!world.isLocationValid(currC)) break;
            if (world.isLocationOccupied(currC)) {
                Boat b = world.getOccupant(currC);
                if (b.getTeam() == this.getTeam() || b.getHealth() <= 0) continue;
                return "Fire cannons! " + b.takeHit(this.getStrength()) + b.takeHit(this.getStrength());
            }
        }
        return "There are no boats in range currently.\n";

    }
}
