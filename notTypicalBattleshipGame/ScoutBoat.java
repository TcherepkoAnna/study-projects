package PurdueX.course_4_polymorphismAndAdvancedDataStructures.notTypicalBattleshipGame;

public abstract class ScoutBoat extends Boat {


    public ScoutBoat(int team, Coordinates location, int direction, int health, int vision) {
        super(team, location, direction, health, 1, vision);
    }

    /*
    A ScoutBoat is designed to avoid attack,
    and the method should award a hit to the boat in only 25% of the attacks
    (use Math.random() to generate an outcome).
    When hit, the ScoutBoat should behave as any other Boat.
     "B1 has avoided the attack!"
     "B1 takes 1 damage."
     */
    public String takeHit(int attacks) {
        if (getHealth() <= 0) return String.format("%s has already been sunk.\n", this.toString());
        if (Math.random() > 0.25) {
            return this.toString() + " has avoided the attack!\n";
        } else {
            return super.takeHit(attacks);
        }
    }
}
