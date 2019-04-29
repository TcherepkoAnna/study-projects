package PurdueX.course_4_polymorphismAndAdvancedDataStructures.notTypicalBattleshipGame;

public class Cruiser extends ScoutBoat {

    public Cruiser(int team, Coordinates location, int direction) {
        super(team, location, direction, 3, 3);
    }

    @Override
    public String getActions() {
        return "Choose any of the following actions for the Cruiser: \n" +
                " 1. Move\n" +
                " 2. Turn left\n" +
                " 3. Turn right\n";
    }


    // A Cruiser can move twice,
    // so make sure to repeat the selection of an appropriate action for each element of the choices input.
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
                default:
                    result += "Invalid choice number.\n";
            }
        }
        return result;
    }

    @Override
    public String getID() {
        return "" + 'C' + getTeam();
    }
}
