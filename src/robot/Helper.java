package robot;

/**
 * Helper class
 * Author: Tola
 */

public class Helper {

    /* Parsing string format to integer */
    public static int[] parseToIntegers( String aString ) throws FatalErrorException{
        String[] splitString = aString.split(",");
        int[] ints = new int[splitString.length];

        for( int i=0; i<splitString.length; i++){
            try {
                splitString[i] = splitString[i].replaceAll("[^0-9]", "");
                ints[i] = Integer.parseInt(splitString[i]);
            }catch (NumberFormatException e){
                e.printStackTrace();
                throw new FatalErrorException("File format parsing error");
            }
        }
        return ints;
    } // end parse to int

} // end helper class
