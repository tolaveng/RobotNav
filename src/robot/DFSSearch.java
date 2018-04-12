package robot;

import java.util.ArrayList;


public class DFSSearch extends SearchMethod {

    public ArrayList<Location> blockedLocations;
    public int[] boundaryXY;  // max x and y

    public DFSSearch(Location startLocation, Location goalLocation, int[] boundaryXY, ArrayList<Location> blockedLocations){
        this.code = "DFS";
        this.longName = "Depth-First search";

        this.startLocation = startLocation;
        this.goalLocation = goalLocation;
        this.boundaryXY = boundaryXY;
        this.blockedLocations = blockedLocations;
    }

    public Location search(){
        // initial state
        addToFrontier( startLocation );
        // search in frontier
        while (Frontier.size() > 0){
            //get an item off the fringe
            Location thisLocation = popFromFrontier();
            //System.out.println("Pop: " + thisLocation.getX() + "," + thisLocation.getY());

            // check it is the goal item?
            if ( thisLocation.equals(goalLocation) ){
                return thisLocation;
            }else{
                //This isn't the goal, just explore
                ArrayList<Location> newLocationList = thisLocation.explore( boundaryXY, blockedLocations);

                //add the new list to the frontier
                for(int i = newLocationList.size()-1; i >= 0; i--)
                {
                    addToFrontier(newLocationList.get(i));
                }
            }
        } // end while
        // no solution found
        return null;
    } // end search

    public boolean addToFrontier(Location location)
    {
        //if this has been found before,
        if(Searched.contains(location) || Frontier.contains(location)) {
            //discard it
            return false;
        } else {
            //else put this item on the first stack;
            //System.out.println("Add: " + location.getX() + "," + location.getY());
            Frontier.push(location);
            return true;
        }
    }


} // end class
