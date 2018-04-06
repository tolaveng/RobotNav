package robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Frontier {

	private LinkedList<Location> itemList;
	
	public Frontier()
	{
		itemList = new LinkedList<>();
	}

	/* pop the first element */
	public Location pop()
	{	
		return itemList.pop();
	}

	/* push to beginning */
	public void push(Location location)
	{
		itemList.push(location);
	}

	/* push an array list by maintaining the index */
	public void push(ArrayList<Location> locations)
	{
		for(int i = locations.size()-1; i >= 0 ; i--)
		{
			//add each item at the start of the list.
			itemList.push(locations.get(i));
		}
	}
	
	public void enqueue(Location location)
	{
		itemList.addLast(location);
	}

	/* enqueue an array list the first index go first */
	public void enqueue(ArrayList<Location> locations)
	{
		for(int i = 0; i < locations.size(); i++)
		{
			itemList.addLast(locations.get(i));
		}
	}

	public boolean contains( Location location){
		return itemList.contains(location);
	}

	public int size(){
		return itemList.size();
	}

	public void clear(){
		itemList.clear();
	}

	
	public void sortByCostAsc()
	{
		Collections.sort( itemList, new LocationComparatorByCost() );
	}

	public void sortByCostDesc()
	{
		sortByCostAsc();
		Collections.reverse(itemList);
	}
	
	public void sortByHeuristicAsc()
	{
		Collections.sort( itemList, new LocationComparatorByHeuristic() );
	}
	
	public void sortByHeuristicDesc()
	{
		sortByHeuristicAsc();
		Collections.reverse( itemList );
	}

	public void sortByEvaluationAsc()
	{
		Collections.sort( itemList, new LocationComparator() );
	}

	public void sortByEvaluationDesc()
	{
		sortByEvaluationAsc();
		Collections.reverse( itemList );
	}

	public String toString(){
		String str = "{";
		for( Location item: itemList){
			str += "(" + item.getX() + "," + item.getY() + "), ";
		}
		str += "}";
		return str;
	}


	class LocationComparator implements Comparator<Location>
	{
		@Override
		public int compare( Location loc1, Location loc2) {
			return loc1.evaluationFunction - loc2.evaluationFunction;
		}
	}

	class LocationComparatorByHeuristic implements Comparator<Location>
	{
		@Override
		public int compare( Location loc1, Location loc2) {
			return loc1.heuristicValue - loc2.heuristicValue;
		}
	}

	class LocationComparatorByCost implements Comparator<Location>
	{
		@Override
		public int compare( Location loc1, Location loc2) {
			return loc1.cost - loc2.cost;
		}
	}




} // end class

