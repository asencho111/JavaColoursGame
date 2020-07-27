package javagame;
import java.util.*;
public class Grid {

    private Field[][] grid;
    private Field[][] changedGrid;

    public Grid(int x, int y, String[] genZero) {
        //Constraint as per the requirements
        if ((x <= y) && (y < 1000)) {
            //Initialize both grids and fill them with Field objects
            grid = new Field[x][y];
            changedGrid = new Field[x][y];
            
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    int val = Character.getNumericValue(genZero[j].charAt(i));
                    grid[i][j] = new Field(i,j,val);
                }
            }
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    int val = Character.getNumericValue(genZero[j].charAt(i));
                    changedGrid[i][j] = new Field(i,j, val);
                }
            }
        }
        else {System.out.println("Input parameters out of bound!");}
    }

    public int getFieldValue(int x, int y){
        return grid[x][y].getValue();
    }

    public int getChangedGridFieldValue(int x, int y){
        return changedGrid[x][y].getValue();
    }

    public void setFieldValue(int x, int y, int newVal){
        grid[x][y].setValue(newVal);
    }

    public void setChangedGridFieldValue(int x, int y, int newVal){
        changedGrid[x][y].setValue(newVal);
    }

    //Helper method to print the grid
    public void printGrid(){

        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[j][i].getValue());
            }
            System.out.println(" ");
        }
    }

    public List<Integer> getNeighbours(int x, int y) {
        List<Integer> neighbours = new ArrayList<Integer>();

        //Booleans for the bounds of the grid
        boolean top = y - 1 >= 0;
        boolean bottom = y + 1 < grid[0].length; 
        boolean left = x-1 >= 0;
        boolean right = x + 1 < grid.length;
        //Conditional statements to add all neighbours that exist
        if (top) {
            neighbours.add(getFieldValue(x, y - 1));
            if (left) { neighbours.add(getFieldValue(x - 1, y- 1)); }
            if (right) { neighbours.add(getFieldValue(x + 1, y - 1)); }
        }
        if (bottom) {
            neighbours.add(getFieldValue(x, y + 1));
            if (left) { neighbours.add(getFieldValue(x -1, y + 1)); }
            if (right) { neighbours.add(getFieldValue(x + 1, y + 1)); }
        }
        if (left) { neighbours.add(getFieldValue(x - 1 , y)); }
        if (right) { neighbours.add(getFieldValue(x + 1, y)); }        

        return neighbours;
    }

    public void changeColor() {

        //For each column
        for (int i = 0; i < grid.length; i++) {
            //For each row
            for (int j = 0; j < grid[0].length; j++) {
                List<Integer> neighboursArrayList = getNeighbours(i,j);
                int[] neighbours = new int[neighboursArrayList.size()];
                for (int k=0; k < neighbours.length; k++) {
                    neighbours[k] = neighboursArrayList.get(k).intValue();
                }
                int[] filteredGreenNeighbours = Arrays.stream(neighbours).filter(x -> x == 1).toArray();
                if (getFieldValue(i,j) == 0) {
                    if (filteredGreenNeighbours.length == 3 || filteredGreenNeighbours.length == 6) {
                        setChangedGridFieldValue(i,j,1);
                    }
                } else {
                    if (filteredGreenNeighbours.length != 2 && filteredGreenNeighbours.length != 3 && filteredGreenNeighbours.length != 6) {
                        setChangedGridFieldValue(i,j,0);
                    }
                }
                //Update fields in the auxilliary grid, so changes in the main one don't interfere with the rest of the for loop
            }
        }
        //When all changes are done, update the original grid with the new values
        for (int i = 0; i < changedGrid.length; i++) {
            for (int j = 0; j < changedGrid[0].length; j++) {
                int changedFieldValue = getChangedGridFieldValue(i,j);
                setFieldValue(i,j, changedFieldValue);
            }
        }
    }

}