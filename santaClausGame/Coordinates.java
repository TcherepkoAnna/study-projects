package PurdueX.course_2_loopsAndDataStructures.holidayProject;

public class Coordinates {

    private int column, row;

    public Coordinates (){
        setCoordinates(0,0);
    }
    public Coordinates(int row, int column) {
        setCoordinates(row, column);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setCoordinates(int row, int column){
        //TODO should we check for validity here?
        this.column = column;
        this.row = row;
    }

    @Override
    public String toString() {
        return "row: " +  getRow() + " column: " + getColumn();
    }
}
