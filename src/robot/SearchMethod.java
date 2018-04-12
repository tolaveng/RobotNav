package robot;

import java.util.*;

public abstract class SearchMethod {
	public String code;				//the code used to identify the method at the command line
	public String longName;			//the actual name of the method.

	public Location startLocation;
	public Location goalLocation;

	public Frontier Frontier = new Frontier();

	public ArrayList<Location> Searched = new ArrayList<>();;

	/* custom search by strategy */
	public abstract Location search();

	/* implement by adding a location to the frontier */
	public abstract boolean addToFrontier(Location aLocation);


	/* pop the first element from the frontier */
	public Location popFromFrontier()
	{
		//remove an item from the fringe to be searched
		Location location = Frontier.pop();
		//Add it to the list of searched states, so that it isn't searched again
		Searched.add(location);

		return location;
	}

	public void reset()
	{
		this.Frontier.clear();
		this.Searched.clear();
	}
}