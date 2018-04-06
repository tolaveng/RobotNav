package robot;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class Main {

    private static String fileName = "";
    private static SearchMethod method;
    private static int[] boundaryXY = new int[2]; // max x and y

    private static Location startLocation;
    private static Location goalLocation;
    private static Location foundLocation;  // a location match the goal only(x,y)
    private static ArrayList<Location> blockedLocations; // cells are obstruction


    public static void main(String[] args) {
        //check args: <filename> <method>
        if(args.length < 2) {
            System.out.println("Usage: search <filename> <search-method>.");
            System.exit(1);
        }

        fileName = args[0];
        String methodCode = args[1].toUpperCase();

        /* read the problem file */
        try {
            readProblemFile();
        }catch(IOException e){
            //e.printStackTrace();
            System.out.println("File name " + fileName + " cannot read properly.");
            System.exit(1);
        }

        /* search by method */
        System.out.println();

        switch (methodCode){
            case "DFS":
                method = new DFSSearch(startLocation, goalLocation, boundaryXY, blockedLocations);
                foundLocation = method.search();
                break;
            case "BFS":
				method = new BFSSearch(startLocation, goalLocation, boundaryXY, blockedLocations);
                foundLocation = method.search();
                break;
            case "GBFS":
                break;
            case "AS":
                break;
            default:
                System.out.println("Unknown method");
                System.exit(1);
        }

        printResult();
        printVisualizer();


        method.reset();
        System.exit(0);

    } // end main


    /* read problem from file */
    private static void readProblemFile() throws IOException {
        //create file reading objects
        FileReader fileReader = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(fileReader);

        try {
            // read grid of cell row and column
            int[] gridDimens = Helper.parseToIntegers(reader.readLine());
            boundaryXY[0] = gridDimens[1]; // x is column
            boundaryXY[1] = gridDimens[0]; // y is row

            // read start and goal position
            int[] xy = Helper.parseToIntegers(reader.readLine());
            startLocation = new Location( xy[0],xy[1] );

            xy = Helper.parseToIntegers(reader.readLine());
            goalLocation = new Location( xy[0],xy[1] );

            // read blocked cell
            String line;
            blockedLocations = new ArrayList<>();

            while ( (line = reader.readLine())!= null ){
                int[] xywh = Helper.parseToIntegers(line);
                for (int w=0; w<xywh[2]; w++){
                    for (int h=0; h<xywh[3]; h++){
                        Location temp = new Location( xywh[0]+w,xywh[1]+h);
                        //System.out.println(temp.getX() +"," + temp.getY());
                        blockedLocations.add(temp);
                    }
                }
            }

        }catch (FatalErrorException e){
            e.printStackTrace();
            System.exit(2);
        }
    } // end read file

    /* print final result */
    private static void printResult(){
        System.out.println( fileName + "  " + method.code + "  " + method.Searched.size());

        if ( foundLocation == null ) {
            System.out.println("No solution found");
        }else{
            for( Location loc : foundLocation.getTravelNodes() ){
                System.out.print( loc.getDirectionFromParent() + "; ");
            }
            System.out.println();
        }

    } // end print result


    /* print visualizer
        @ start
        $ goal
        * path
        | blocked
    * */

    private static void printVisualizer() {
        System.out.println();
        System.out.println("Visually Printing");
        System.out.println(method.longName);

        // print header
        System.out.println();
        System.out.print("\t");
        for( int col=0; col<boundaryXY[0]; col++) {
            System.out.printf("%4s", "----");
        }

        // print data table
        for( int row=0; row<boundaryXY[1]; row++){
            System.out.println();
            System.out.print("\t");
            for( int col=0; col<boundaryXY[0]; col++) {
                Location temp = new Location( col, row );
                System.out.printf("%1s", "|");
                if ( startLocation.equals(temp) ) {
                    System.out.printf("%3s", " @ ");
                }else if ( goalLocation.equals(temp)) {
                    System.out.printf("%3s", " $ ");
                }else if ( blockedLocations.contains(temp)){
                    System.out.printf("%3s", " # ");
                }else if ( foundLocation.getTravelNodes().contains(temp)){
                    System.out.printf("%3s", " o ");
                }else {
                    System.out.printf("%3s", "   ");
                }
            }//col
            System.out.printf("%1s", "|");
            // print separate
            System.out.println();
            System.out.print("\t");
            for( int col=0; col<boundaryXY[0]; col++) {
                System.out.printf("%4s", "----");
            }
        }
        System.out.println();

        System.out.println("\t @ start location");
        System.out.println("\t $ goal location");
        System.out.println("\t # obstruction");
        System.out.println("\t o solution path");

    } // end print visualization

    private static void printVisualizerOld() {
        System.out.println();
        System.out.println("Visually Printing");
        System.out.println(method.longName);
        System.out.println("\t @ start location");
        System.out.println("\t $ goal location");
        System.out.println("\t # obstruction");
        System.out.println("\t * solution path");

        System.out.println();
        for( int row=0; row<boundaryXY[1]; row++){
            for( int col=0; col<boundaryXY[0]; col++) {
                Location temp = new Location( col, row );

                if ( startLocation.equals(temp) ) {
                    System.out.print("\t@");
                }else if ( goalLocation.equals(temp)) {
                    System.out.print("\t$");
                }else if ( blockedLocations.contains(temp)){
                    System.out.print("\t#");
                }else if ( foundLocation.getTravelNodes().contains(temp)){
                    System.out.print("\t*");
                }else {
                    System.out.print("\t.");
                }
            }
            System.out.println();
        }
        System.out.println();

    } // end print visualization


} // end class
