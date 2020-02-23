// Dvij's class

package application;

import java.util.Arrays;
import java.util.Comparator;

public class Poll {
	private String name;
	private Party[] parties;
	private int partiesInPoll;

	public Poll(String name, int maxNumberOfParties) {
		this.name = name;
		if (maxNumberOfParties >= 1) {
			parties = new Party[maxNumberOfParties];
		}
		else {
			parties = new Party[10];
		}
	}
	
	
	public String getPollName() {
		return name;
	}
	
	public int getNumberOfParties() {
		int index = 0;
		if (parties[index] == null) {
			partiesInPoll = 0;
		}
		else {
			partiesInPoll = 0;
			while (index < parties.length && parties[index] != null ) {
				++index;
				++partiesInPoll;
			}
		} 
		return partiesInPoll;
	}
	public void addParty(Party aParty) {
		int index = 0;
		String testName = null;
		for (int i = 0; i < partiesInPoll; i++) {
			String partyName = parties[i].getName().toLowerCase();
			if (partyName.equals(aParty.getName().toLowerCase())) {
				testName = partyName;
				index = i;
			}
		}
		if (aParty == null) {
			System.out.println("The party is not defined.");
		}
		else if (parties[0] == null) {
			parties[0] = aParty;
			partiesInPoll++;
		}
		else if (testName != null) {
			parties[index] = aParty;
		}
		else if (partiesInPoll >= parties.length) {
			System.out.println("The poll is full and no further party can be added.");
		}
		else {
			parties[partiesInPoll++] = aParty;
		}
	}

	public Party getParty(String name) {
		Party partyWithName = null;
		for (Party p: parties) {
			//System.out.println("OLIVIA 1 " + p);
			//System.out.println("OLIVIA 2 " + name);
			//System.out.println("OLIVIA 3 " + p.getName());
			if (p != null) {
				if (p.getName() == name) {
					partyWithName = p;
				}	
			}
		}
		return partyWithName;
	}
	
	static class SeatsComparator implements Comparator<Party> {	
		@Override
		public int compare(Party a, Party b) {
			return Float.compare(b.getProjectedNumberOfSeats(), a.getProjectedNumberOfSeats());
		}
	}
	
	public Party[] getPartiesSortedBySeats() {
		Party [] sortedSeats = new Party[partiesInPoll];
		for (int i = 0; i < sortedSeats.length; i++) {
			String copyName = parties[i].getName();
			float copySeats = parties[i].getProjectedNumberOfSeats();
			float copyVotes = parties[i].getProjectedPercentageOfVotes();
			sortedSeats[i] = new Party(copyName, copySeats, copyVotes);
		}
		Arrays.sort(sortedSeats, new SeatsComparator());
		return sortedSeats;
	}
	
	static class VotesComparator implements Comparator<Party> {	
		@Override
		public int compare(Party a, Party b) {
			return Float.compare(b.getProjectedPercentageOfVotes(), a.getProjectedPercentageOfVotes());
		}
	}

	public Party[] getPartiesSortedByVotes() {
		Party [] sortedVotes = new Party[partiesInPoll];
		for (int i = 0; i < sortedVotes.length; i++) {
			String copyName = parties[i].getName();
			float copySeats = parties[i].getProjectedNumberOfSeats();
			float copyVotes = parties[i].getProjectedPercentageOfVotes();
			sortedVotes[i] = new Party(copyName, copySeats, copyVotes);
		}
		Arrays.sort(sortedVotes, new VotesComparator());
		return sortedVotes;
	}
	
	@Override
	public String toString() {
		String pollString = name;
		for (Party p: parties) {
			if (p != null) {
				String aParty = p.toString();
				pollString = pollString + "\n" + aParty;
			}
			else {
				pollString = pollString + "\nnull";
			}
		}
		return pollString;
	}
	
	// Bonus
	public Party undeterminedVotesAndSeats() {
		String undeterminedName = "Undetermined";
		float seatsCountedFor = 0.0f;
		float votesCountedFor = 0.0f;
		for (Party p: parties) {
			seatsCountedFor = seatsCountedFor + p.getProjectedNumberOfSeats();
			votesCountedFor = votesCountedFor + p.getProjectedPercentageOfVotes();
		}
		if (Math.round(votesCountedFor) < 100) {
			int totalNumOfSeats = (int)(seatsCountedFor/(votesCountedFor/100));
			float undeterminedSeats = totalNumOfSeats - seatsCountedFor;
			float undeterminedVotes = 100.0f - votesCountedFor;
			Party undetermined = new Party(undeterminedName, undeterminedSeats, undeterminedVotes);
			return undetermined;
		}
		else {
			float undeterminedSeats = 0.0f;
			float undeterminedVotes = 0.0f;
			Party undetermined = new Party(undeterminedName, undeterminedSeats, undeterminedVotes);
			return undetermined;
		}
	}
	
	public static void main (String[] args) {
		
		//Checking Poll initialization
		Poll testPoll = new Poll("test poll", 5);
		System.out.println(testPoll.getPollName());
		System.out.println(testPoll.getNumberOfParties());
		System.out.println(testPoll.toString());
		
		//Generating parties
		Party liberal = new Party("liberal", 30.0f, 30.0f);
		Party Conservative = new Party("Conservative", 20.0f, 20.0f);
		Party NDP = new Party("NDP", 20.0f, 20.0f);
		Party BQ = new Party("BQ", 15.0f, 15.0f);
		Party green = new Party("green", 15.0f, 15.0f);
		Party Liberal = new Party("Liberal", 30.0f, 30.0f);
		Party Extra = new Party("Extra", 100.0f, 100.0f);
		
		//Populating testPoll
		testPoll.addParty(liberal);
		System.out.println(testPoll.getNumberOfParties());
		testPoll.addParty(Conservative);
		System.out.println(testPoll.getNumberOfParties());
		testPoll.addParty(NDP);
		System.out.println(testPoll.getNumberOfParties());
		testPoll.addParty(BQ);
		System.out.println(testPoll.getNumberOfParties());
		testPoll.addParty(green);
		System.out.println(testPoll.getNumberOfParties());
		
		//Checking replacement
		testPoll.addParty(Liberal);
		System.out.println(testPoll.getNumberOfParties());
		
		//Checking party addition over array limit
		testPoll.addParty(Extra);
		System.out.println(testPoll.getNumberOfParties());
		
		//Checking getParty method
		Party testGetParty = testPoll.getParty("NDP");
		System.out.println(testGetParty);
		
		//Checking toString method
		System.out.println(testPoll.toString());
		
		//Checking getPartiesSortedBySeats method
		Party[] testSeats = testPoll.getPartiesSortedBySeats();
		String testPrintSeats = "\n";
		for (Party p: testSeats) {
			String aParty = p.toString();
			testPrintSeats = testPrintSeats + aParty + "\n"; 
		}
		System.out.println(testPrintSeats);
		
		//Checking getPartiesSortedByVotes method
		Party[] testVotes = testPoll.getPartiesSortedByVotes();
		String testPrintVotes = "\n";
		for (Party p: testVotes) {
			String aParty = p.toString();
			testPrintVotes = testPrintVotes + aParty + "\n"; 
		}
		System.out.println(testPrintVotes);
		
		//Checking undeterminedVotesAndSeats method with a poll where all votes and seats are accounted for
		Party testUndeterminedNotPresent = testPoll.undeterminedVotesAndSeats();
		System.out.println(testUndeterminedNotPresent.toString());
		
		//Checking undeterminedVotesAndSeats method with a poll which has undetermined votes and seats
		Party Green = new Party("Green", 5.0f, 5.0f);
		testPoll.addParty(Green);
		Party testUndeterminedPresent = testPoll.undeterminedVotesAndSeats();
		System.out.println(testUndeterminedPresent.toString());

	}
}
