package PurdueX.course_4_polymorphismAndAdvancedDataStructures.notTypicalBattleshipGame;

public class Coordinates {
    private int x, y;

    public Coordinates (){
        setCoordinates(0,0);
    }
    public Coordinates(int x, int y) {
        setCoordinates(x,y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinates(int x, int y){
        //TODO should we check for validity here?
        this.x = x;
        this.y = y;
    }

    // “C7” // x = 7, y = 2
    @Override
    public String toString() {
        return "" + (char)('A'+ getY()) + (getX()+1);
    }
}
