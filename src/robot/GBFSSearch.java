package robot;

import java.util.ArrayList;


public class GBFSSearch extends SearchMethod {

    public ArrayList<Location> blockedLocations;
    public int[] boundaryXY;  // max x and y

    public GBFSSearch(Location startLocation, Location goalLocation, int[] boundaryXY, ArrayList<Location> blockedLocations){
        this.code = "GBFS";
        this.longName = "Greedy Best-First Search";

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
                for(int i = 0; i < newLocationList.size(); i++)
                {
                    //if can add
                    Location newChild = newLocationList.get(i);
                    if( addToFrontier( newChild ) ) {
                        //then, work out it's heuristic value, distance to the goal
                        newChild.heuristicValue = newChild.calculateDistanceTo( this.goalLocation );
                        // evaluate function is equal to its heuristic value
                        newChild.evaluationFunction = newChild.heuristicValue;
                    }
                }
                //Sort the frontier by the evaluation function
                Frontier.sortByEvaluationAsc();
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
            //else put this item on the last of the queue;
            //System.out.println("Add: " + location.getX() + "," + location.getY());
            Frontier.enqueue(location);
            return true;
        }
    }



} // end class
