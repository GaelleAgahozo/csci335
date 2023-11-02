package maze.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import core.Pos;

public class MazeExplorer {
	private Maze m;
	private Pos location;
	private TreeSet<Pos> treasureFound;
	private MazeExplorer goal;

	public MazeExplorer(Maze m, Pos location) {
		this.m = m;
		this.location = location;
		treasureFound = new TreeSet<>();
	}

	public Pos getLocation() {return location;}

	public Set<Pos> getAllTreasureFromMaze() {
		return m.getTreasures();
	}

	public Set<Pos> getAllTreasureFound() {
		return treasureFound;
	}

	public int getNumTreasuresFound() {
		return treasureFound.size();
	}

	public MazeExplorer getGoal() {
		if (goal == null) {
			goal = m.getGoal();
		}
		return goal;
	}

	public ArrayList<MazeExplorer> getSuccessors() {
		ArrayList<MazeExplorer> result = new ArrayList<>();

		// Get the neighbors of the current position
		Collection<Pos> neighbors = m.getNeighbors(location);

		for (Pos neighbor : neighbors) {
			if (!m.isBlocked(location, neighbor)) {
				// Create a new MazeExplorer for the unblocked neighbor
				MazeExplorer successor = new MazeExplorer(m, neighbor);

				// Copy over the treasures found so far to the successor
				successor.addTreasures(treasureFound);

				//If the new position(neighbor) contains a treasure and it hasn't been collected yet,
				//add it to the treasureFound set of the successor

				if(m.isTreasure(neighbor) && !treasureFound.contains(neighbor)){
					successor.treasureFound.add(neighbor);
				}

				// If the successor contains a treasure record it
				//check to see if the member is in a

				result.add(successor);
			}
		}

		return result;
	}


	public void addTreasures(Collection<Pos> treasures) {
		treasureFound.addAll(treasures);
	}

	public String toString() {
		StringBuilder treasures = new StringBuilder();
		for (Pos t: treasureFound) {
			treasures.append(";");
			treasures.append(t.toString());
		}
		return "@" + location.toString() + treasures;
	}

	@Override
	public int hashCode() {return toString().hashCode();}

	@Override
	public boolean equals(Object other) {
		if (other instanceof MazeExplorer that) {
			return this.location.equals(that.location) && this.treasureFound.equals(that.treasureFound);
		} else {
			return false;
		}
	}

	public boolean achievesGoal() {
		return this.equals(getGoal());
	}

	public Maze getM() {
		return m;
	}
}
