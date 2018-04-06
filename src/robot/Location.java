package robot;


import java.util.ArrayList;

public class Location implements Comparable<Location>
{
    private int x;
    private int y;
	private Location parent;
	private Direction directionFromParent;

    public int cost;
    public int heuristicValue;
    public int evaluationFunction;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
        this.parent = null;
        this.directionFromParent = null;
        this.cost = 0;
        this.heuristicValue = 0;
        this.evaluationFunction = 0;
    }

    public Location(int x, int y, int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    public Location(int x, int y, Location parent, Direction directionFromParent ){
        this.x = x;
        this.y = y;
        if ( parent != null ) {
            this.parent = parent;
            this.cost = parent.cost + 1;
        }
        this.directionFromParent = directionFromParent;
        this.heuristicValue = 0;
        this.evaluationFunction = 0;
    }



    /* getter setter */
    //Get the x or y coordinate
    public int getX() {
        return x;
    }
    public void setX(int x) { this.x = x; }

    public int getY() {
        return y;
    }
    public void setY(int y) { this.y = y; }

    public void setLocation(int x, int y) { this.x = x; this.y = y; }


    public Location getParent() {
        return parent;
    }
    public void setParent(Location parent) {
        this.parent = parent;
    }

    public Direction getDirectionFromParent() {
        return directionFromParent;
    }
    public void setDirectionFromParent(Direction directionToParent) {
        this.directionFromParent = directionToParent;
    }

    /* end getter and setter */

    /* calculate distance to a target location */
    public int calculateDistanceTo( Location target ){
        // using manhattan distance
        int distance = Math.abs( this.x - target.getX() ) + Math.abs( this.y - target.getY() );
        return distance;
    }

	/* explore possible cells move in the boundary(x,y) avoid obstruction  */
	public ArrayList<Location> explore(int[] boundaryXY, ArrayList<Location> blockedLocations){
        ArrayList<Location> exploreList = new ArrayList<>();

        // check move in 4 direction
        for ( Direction dir : Direction.values() ){
            int x = this.x;
            int y = this.y;
            if ( dir == Direction.Up){ y--; }
            else if ( dir == Direction.Left){ x--; }
            else if ( dir == Direction.Down){ y++; }
            else if ( dir == Direction.Right){ x++; }

            // if it is in the boundary
            if ( x >= 0 && y >= 0 && x < boundaryXY[0] && y < boundaryXY[1] ){
                Location temp = new Location(x, y, this, dir);
                // if is not in blocked cells
                if ( !blockedLocations.contains(temp) ){
                    // add to explore list
                    exploreList.add(temp);
                }
            }
        }
        return exploreList;
	}

	/* recursive track back to parent node */
	private ArrayList<Location> getTrackBack(  ArrayList<Location> locationList ){
	    if ( this.parent != null ){
            locationList.add(this);
            return this.parent.getTrackBack( locationList );
        }
            return locationList;
    }

    /* return previous visited path from start node to the goal */
    public ArrayList<Location> getTravelNodes(){
	    ArrayList<Location> travelPath = new ArrayList<>();
        ArrayList<Location> trackBack = this.getTrackBack(new ArrayList<>());
        // reverse the order from the track back
        for ( int i = trackBack.size()-1; i>=0; i-- ){
            travelPath.add( trackBack.get(i) );
        }
        return travelPath;
    }


    /* direction from start node to the goal
     * Copy from PuzzleState
      * */
    public Direction[] getTravelDirection()
    {
        Direction result[];

        if(this.parent == null)	//If this is the root node, there is no path!
        {
            result = new Direction[0];
            return result;
        } else				//Other wise, path to here is the path to parent
        // plus parent to here
        {
            Direction[] pathToParent = this.parent.getTravelDirection();
            result = new Direction[pathToParent.length + 1];
            for(int i = 0; i < pathToParent.length; i++)
            {
                result[i] = pathToParent[i];
            }
            result[result.length - 1] = this.directionFromParent;
            return result;
        }
    }

	public String toString(){
	    return "(" + this.x + "," + this.y + ")";
    }

    public boolean equals(Object other) {
        if (!(other instanceof Location)) {
            return false;
        }
        Location temp = (Location) other;
        return this.x == temp.getX() && this.y == temp.getY();
    }

    @Override
    /* compare different between evaluation function f(n) */
    public int compareTo(Location other) {
	    return this.evaluationFunction - other.evaluationFunction;
    }


}