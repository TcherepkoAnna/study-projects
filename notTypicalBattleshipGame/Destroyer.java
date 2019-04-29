package PurdueX.course_4_polymorphismAndAdvancedDataStructures.notTypicalBattleshipGame;

public class Destroyer extends Boat implements Attacker {

    public Destroyer(int team, Coordinates location, int direction) {

        super(team, location, direction, 2, 2, 1);
    }

    @Override
    public String takeHit(int strength) {
        if (Math.random() > 0.5) {
            return super.takeHit(strength);
        } else return this.toString() + " avoids the attack!\n";
    }

    /*
        The Destroyer can attack any Boat directly in its path and within its vision.
        If no boats are in this range, the Destroyer forfeits the current action.
        "" + results of Boat.takeHit method
        "There are no boats in range currently."
         */
    @Override
    public String attack(World world) {
        //TODO so you can't choose which boat to hit if there are several in vision (will hit the nearest) ?
        // TODO Should promt the user for choice of boat?
        Coordinates currC = this.getLocation();
        for (int i = 0; i < getVision(); i++) {
            currC = world.getAdjacentLocation(currC, this.getDirection());
            if (!world.isLocationValid(currC)) break;
            if (world.isLocationOccupied(currC)) {
                Boat b = world.getOccupant(currC);
                if (b.getTeam()== this.getTeam() || b.getHealth() <= 0) continue;
                return b.takeHit(this.getStrength());
            }
        }
        return "There are no boats in range currently.\n";
    }

    @Override
    public String getActions() {
        return "Choose any of the following actions for the Destroyer:\n" +
                " 1. Move\n" +
                " 2. Turn left\n" +
                " 3. Turn right\n" +
                " 4. Attack\n";
    }

    @Override
    public String act(int[] choices, World world) {
        String result = "";
        int choice;
        for (int i = 0; i < choices.length; i++) {
            choice = choices[i];
            switch (choice) {
                case 1:
                    result += move(world);
                    break;
                case 2:
                    result += turn(-1);
                    break;
                case 3:
                    result += turn(1);
                    break;
                case 4:
                    result += attack(world);
                    break;
                default:
                    result += "Invalid choice number.\n";
            }
        }
        return result;
    }

    @Override
    public String getID() {
        return "" + 'D' + getTeam();
    }
}
