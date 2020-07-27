package javagame;
import java.util.Scanner;
public class App {

    public static void main(String[] args) {

        // Create Scanner for user input
        Scanner input = new Scanner(System.in);

        System.out.println("Enter number of rows and columns of the grid, separated by comas");
        String inputString = input.nextLine();
        //Cleans input of spaces and splits at the coma
        String[] gridOptions = inputString.replaceAll("\\s+","").split(",");
        int gridX = Integer.parseInt(gridOptions[0]);
        int gridY = Integer.parseInt(gridOptions[1]);

        String[] genZero = new String[gridY];
        //Fills the string array used for creating the starting grid
        for (int i = 0; i < gridY; i++){
            System.out.println("Enter rows of the starting grid one by one");
            //Cleans input of spaces
            String row = input.nextLine().replaceAll("\\s+","");
            genZero[i] = row;
        }

        System.out.println("Enter x and y coordinates of target cell, and the number of generations, separated by comas");
        //Cleans input of spaces and splits at the coma
        String[] solutionRequired = input.nextLine().replaceAll("\\s+","").split(",");
        int reqCellX = Integer.parseInt(solutionRequired[0]);
        int reqCellY = Integer.parseInt(solutionRequired[1]);
        int reqGenerations = Integer.parseInt(solutionRequired[2]);

        // //Last argument specifies solution required
        // String[] solutionRequired = args[args.length-1].split(",");
        // int reqCellX = Integer.parseInt(solutionRequired[0]);
        // int reqCellY = Integer.parseInt(solutionRequired[1]);
        // int reqGenerations = Integer.parseInt(solutionRequired[2]);
        // //First argument is the grid size
        // String[] gridOptions = args[0].split(",");
        // int gridX = Integer.parseInt(gridOptions[0]);
        // int gridY = Integer.parseInt(gridOptions[1]);
        // //Everything in between is the generation zero of the grid
        // String[] genZero = new String[args.length - 2];
        // for (int i = 1; i < args.length - 1; i++){
        //     genZero[i-1] = args[i];
        // }


        Grid grid = new Grid(gridX, gridY, genZero);
        int count = 0;
        for (int j = 0; j <= reqGenerations; j++) {
            if (grid.getFieldValue(reqCellX,reqCellY) == 1) { count++; }
            grid.changeColor();
        }
        System.out.println(count);
    }

}