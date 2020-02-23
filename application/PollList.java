package application;

public class PollList {
	private Poll[] polls;
	private int numOfSeats;
	
	public PollList(int numOfPolls, int numOfSeats) {
		polls = new Poll[numOfPolls];
		this.numOfSeats = numOfSeats;
	}
	
	public int getNumOfSeats() {
		return numOfSeats;
	}

	public Poll[] getPolls() {
		return polls;
	} 	

	public void addPoll(Poll aPoll) {
		if (aPoll == null) {
			System.out.println("Error: Argument is null");
			return;
		}
		
		// Get the index of the next empty space in the poll list
		// Debugging: System.out.println("Calling getEmptyPollIndex: ");
		int goodIndex = getEmptyPollIndex(polls);
		// Debugging: System.out.println("Good index= " + goodIndex);
		
		String sentPollName = aPoll.getPollName();
		for(int i=0;i<goodIndex;i++) {
			if(sentPollName == polls[i].getPollName()) {
				polls[i] = aPoll;
				return;
			}
		}
		
		if (goodIndex != (polls.length)) {
			polls[goodIndex] = aPoll;
		} else {
			System.out.println("Error: Poll list is full!");
		}
		return;
	}
	
	public Poll getAggregatePoll(String[] partyNames) {
		Poll bob = new Poll("aggregate",partyNames.length);
		for(int k=0;k<partyNames.length;k++) {
			int counter = 0;
			float averageSeats = 0.0f;
			float averagePercent = 0.0f;
			for(int i=0;i<polls.length;i++) {
				if (this.polls[i].getParty(partyNames[k]) != null) {
					if(this.polls[i].getParty(partyNames[k]).getName() == partyNames[k]) {
						counter++;
						averageSeats = averageSeats + this.polls[i].getParty(partyNames[k]).getProjectedNumberOfSeats();
						averagePercent = averagePercent + this.polls[i].getParty(partyNames[k]).getProjectedPercentageOfVotes();
					}
				}
			}
			float realSeats = (float) (averageSeats/counter);
			float realPercent = (float) (averagePercent/counter);
			Party dummyParty = new Party(partyNames[k],realSeats,realPercent);
			bob.addParty(dummyParty);
		}
		System.out.println("BOB: " + bob);
		// Check totals
		float seatSum = 0.0f;
		float percentSum = 0.0f;
		for(int i=0;i<bob.getNumberOfParties();i++) {
			seatSum = seatSum + bob.getParty(partyNames[i]).getProjectedNumberOfSeats();
			percentSum = percentSum + bob.getParty(partyNames[i]).getProjectedPercentageOfVotes();
		
		if (seatSum > numOfSeats) {
			float factor = numOfSeats/seatSum;
			for(int j=0;j<bob.getNumberOfParties();j++) {
				bob.getParty(partyNames[j]).setProjectedNumberOfSeats(bob.getParty(partyNames[j]).getProjectedNumberOfSeats()*factor);
			}
		}
		
		if (percentSum > 100.0f) {
			float factor = 100.0f/percentSum;
			for(int k=0;k<bob.getNumberOfParties();k++) {
				bob.getParty(partyNames[k]).setProjectedPercentageOfVotes(bob.getParty(partyNames[k]).getProjectedPercentageOfVotes()*factor);
			}
		}
	}
	return bob;
	}
	
	// Set index=0, check to see if it's empty
	// If empty, return that index
	// If not, increment index
	// If we get to the end of an array (error), return -1
	private static int getEmptyPollIndex(Poll[] polls) {
		for(int i=polls.length-1;i>=0;i--) {
			if (polls[i]!=null) {
				return i+1;
			}
		}
		return 0;
	}
	
	public String toString() {
		int index=0;
		while (true) {
			try {
				if (polls[index]==null) {
					return "";
				} else {
					System.out.println(polls[index]);
					index++;
				}
			} catch (Exception e) {
				return "";
			}
		}
	}
	
	public static void main(String[] args) {
		Party conservative = new Party("Conservative",200,60.0f);
		Party liberal = new Party("Liberal",100,30.0f);
		Party ndp = new Party("NDP",150,40.0f);
		
		Poll testPoll1 = new Poll("Test Poll 1",3);
		Poll testPoll2 = new Poll("Test Poll 2",2);
		Poll testPoll3 = new Poll("Test Poll 3",2);
		
		testPoll1.addParty(conservative);
		testPoll1.addParty(liberal);
		testPoll1.addParty(ndp);
		
		testPoll2.addParty(conservative);
		testPoll2.addParty(liberal);
		
		testPoll3.addParty(liberal);
		testPoll3.addParty(ndp);
		
		PollList testList = new PollList(3,225);
		
		testList.addPoll(testPoll1);
		testList.addPoll(testPoll2);
		testList.addPoll(testPoll3);
		
		// Print TestList:
		System.out.println("testList: " + testList);
		
		String[] myString = {"Conservative","Liberal","NDP"};
		Poll newAggregate = new Poll("aggregate",myString.length);
		newAggregate = testList.getAggregatePoll(myString);
		System.out.println(newAggregate);
	}
	
}
