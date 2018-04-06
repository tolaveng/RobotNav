package robot;

public class FatalErrorException extends Exception {
    public FatalErrorException( String error ){
        super(error);
    }

}
