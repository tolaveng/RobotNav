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
                break;
            case "BFS":
                break;
            case "GBFS":
                break;
            case "AS":
                break;
            default:
                System.out.println("Unknown method");
                System.exit(1);
        }


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

    


} // end class
