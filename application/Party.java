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
            percent = projectedPercentageOfVotes;
        } else {
            System.out.println("This is not a valid projected percentage number of votes. It must be between 0 and 1 inclusive.");
        }

    }

    @Override
    public String toString() {
        return getName() + " (" + Math.round((getProjectedPercentageOfVotes()) * 100) + "%, " + getProjectedNumberOfSeats() + ")";
        // Projected percentage of seats should be a number between 0 and 100.
    }

    public double projectedPercentOfSeats(int totalNumberOfSeats) {
        float projectedNumberOfSeats = getProjectedNumberOfSeats();
        double projectedPercentOfSeats = projectedNumberOfSeats / (float) totalNumberOfSeats;
        return projectedPercentOfSeats;
    }

    public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
        float projectedNumberOfSeats = getProjectedNumberOfSeats();
        int expectedNumberOfSeats = (int) Math.round(projectedNumberOfSeats / numOfSeatsPerStar);
        int starsNeededForSeatMajority = starsNeededForMajority;

        String[] textVisualizationBySeats = textVisualization(maxStars, expectedNumberOfSeats, starsNeededForSeatMajority);
        String textVisualizationBySeatsString = "";
        textVisualizationBySeatsString = Stream.of(textVisualizationBySeats).collect(Collectors.joining());
        return textVisualizationBySeatsString;
    }

    public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double percentOfVotesPerStar) {
        float projectedNumberOfVotes = getProjectedPercentageOfVotes();
        int expectedPercentageOfVotes = (int) Math.round(projectedNumberOfVotes / percentOfVotesPerStar);
        int starsNeededForVoteMajority = starsNeededForMajority;

        String[] textVisualizationByVotes = textVisualization(maxStars, expectedPercentageOfVotes, starsNeededForVoteMajority);
        String textVisualizationByVotesString = "";
        textVisualizationByVotesString = Stream.of(textVisualizationByVotes).collect(Collectors.joining());
        return textVisualizationByVotesString;
    }

    private String[] textVisualization(int maxStars, int partyStars, int starsNeededForMajority) {
        int visualizationLength = maxStars + 2;
        String[] textVisualization = new String[visualizationLength + 1];

        if (partyStars >= starsNeededForMajority) {
            for (int i = 0; i <= partyStars; i++) {
                textVisualization[i] = "*";
            }

            for (int j = visualizationLength - 1; j > partyStars; j--) {
                textVisualization[j] = " ";
            }
        }

        if (partyStars < starsNeededForMajority) {
            for (int i = 0; i < partyStars; i++) {
                textVisualization[i] = "*";
            }

            for (int j = visualizationLength - 1; j >= partyStars; j--) {
                textVisualization[j] = " ";
            }
        }

        textVisualization[starsNeededForMajority] = "|";
        textVisualization[visualizationLength] = toString();

        return textVisualization;
    }
}
