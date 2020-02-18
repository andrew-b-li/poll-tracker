package application;

// Dvij
public class Poll {
	private String name;
	private Party[] parties;
	private int partiesInPoll;

	public Poll(String name, int maxNumberOfParties) {
		this.name = name; 
		parties = new Party[maxNumberOfParties];
		partiesInPoll = 0;
	}
	
	public String getPollName() {
		return name;
	}
	
	public int getNumberOfParties() {
		return partiesInPoll;
	}
	
	public void addParty(Party aParty) {
		parties[partiesInPoll++] = aParty;
	}

	public Party getParty(String name) {
		for(int i=0;i<partiesInPoll;i++) {
			if (name == parties[i].getName()) {
				return parties[i];
			}
		}
		return null;
	}
	
	public Party[] getPartiesSortedBySeats() {
		return parties;
	}

	public Party[] getPartiesSortedByVotes() {
		return parties;
	}
	
	@Override
	public String toString() {
		String result = name + ":" + "\n";
		for (int i=0;i<partiesInPoll;i++) {
			result = result + parties[i].getName() + " " + parties[i].getProjectedPercentageOfVotes() + "% " + parties[i].getProjectedNumberOfSeats() + "\n";
		}
		return result;
	}
}
