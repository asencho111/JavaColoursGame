package javagame;
public class Field {
    
    private int value;
    private int xPos;
    private int yPos;

    public Field(int xPos, int yPos, int value){
        this.value = value;
        this.xPos = xPos;
        this.yPos = yPos;
    }
    public int getX() {
        return xPos;
    }
    public int getY() {
        return yPos;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int newValue) {
        value = newValue;
    }

}