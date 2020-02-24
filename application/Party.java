package application;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Party {
	private String name;
	private float seats;
	private float percent;
	
	public Party(String partyName) {
		setName(partyName);
	}
	
	public Party(String partyName, float projectedNumberOfSeats, float projectedPercentageOfVotes) {
		setName(partyName);
		setProjectedNumberOfSeats(projectedNumberOfSeats);
		setProjectedPercentageOfVotes(projectedPercentageOfVotes);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String partyName) {
		name = partyName;
	}

	public float getProjectedNumberOfSeats() {
		return seats;
	}

	public void setProjectedNumberOfSeats(float projectedNumberOfSeats) {
		if (projectedNumberOfSeats >= 0) {
			seats = projectedNumberOfSeats;
		} else {
			System.out.println("This is not a valid projected number of seats. It must be a non-negative value.");
		}
	}

	public float getProjectedPercentageOfVotes() {
		return percent;
	}
	
	public void setProjectedPercentageOfVotes(float projectedPercentageOfVotes) {
		if (projectedPercentageOfVotes >= 0 & projectedPercentageOfVotes <= 1) {
		//if (projectedPercentageOfVotes >= 0 & projectedPercentageOfVotes <= 100) {
			percent = projectedPercentageOfVotes;
		} else {
			System.out.println("This is not a valid projected percentage number of votes. It must be between 0 and 1 inclusive.");
		}
		
	}
	
	@Override
	public String toString() {
		return getName() + " (" + Math.round((getProjectedPercentageOfVotes()) * 100) + "%, " + getProjectedNumberOfSeats() + ")";
		//return getName() + " (" + Math.round((getProjectedPercentageOfVotes())) + "%, " + getProjectedNumberOfSeats() + ")";
		// Projected percentage of seats should be a number between 0 and 100.
	}
	
	public double projectedPercentOfSeats(int totalNumberOfSeats) {
		float projectedNumberOfSeats = getProjectedNumberOfSeats();
		double projectedPercentOfSeats = projectedNumberOfSeats / (float)totalNumberOfSeats;
		return projectedPercentOfSeats;
	}
	
	public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
		//System.out.println("maxStars: " + maxStars);
		//System.out.println("starsneededForMajority: " + starsNeededForMajority);
		//System.out.println("numOfSeatsPerStar: " + numOfSeatsPerStar);
		int visualizationLength = maxStars + 2;
		//String[] textVisualization = new String[visualizationLength + 1];
		char[] textVisualization = new char[visualizationLength + 1];
		float projectedNumberOfSeats = getProjectedNumberOfSeats();
		int partyStars = (int)Math.round(projectedNumberOfSeats/numOfSeatsPerStar);
		
		if (partyStars > starsNeededForMajority) {
			for (int i = 0; i <= partyStars; i++) {
				textVisualization[i] = '*';
			}
			
			for (int j = visualizationLength - 1; j > partyStars; j--) {
				textVisualization[j] = ' ';
			}
		}
		
		if (partyStars < starsNeededForMajority) {
			for (int i = 0; i < partyStars; i++) {
				textVisualization[i] = '*';
			}
			
			for (int j = visualizationLength - 1; j >= partyStars; j--) {
				textVisualization[j] = ' ';
			}
		}
		
		textVisualization[starsNeededForMajority] = '|';
		//textVisualization[visualizationLength] = toString();
		
		//String textVisualizationString = "";
		//textVisualizationString = Stream.of(textVisualization).collect(Collectors.joining());
		String textVisualizationString = String.valueOf(textVisualization) + name + "(" + percent*100 + "% of votes, " + seats + " seats)";
		return textVisualizationString;
	}

	public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double percentOfVotesPerStar) {
		return toString();
	}	
	
	public static void main(String[] args) {
		Party test = new Party("PartyName", 190, (float)0.53);
		System.out.println(test.textVisualizationBySeats(20, 10, 18.0));
		
		Party test2 = new Party("AnotherPartyName", 25, (float)0.36);
		System.out.println(test2.textVisualizationBySeats(15, 8, 4.6));
	}
}