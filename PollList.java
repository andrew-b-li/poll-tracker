// package application;

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
		// Get the index of the next empty space in the poll list
		// If the poll list is full, return -1 (could never be a good index)
		System.out.println("Calling getEmptyPollIndex: ");
		int goodIndex = getEmptyPollIndex();
		System.out.println("Good index= " + goodIndex);
		
		// Adds aPoll into the next available space
		try {
			polls[goodIndex] = aPoll;
		} catch (Exception e) {
			System.out.println("Poll list already full.");
		}
	}
	
	public Poll getAggregatePoll(String[] partyNames) {
		return polls[0];
	}
	
	// Set index=0, check to see if it's empty
	// If empty, return that index
	// If not, increment index
	// If we get to the end of an array (error), return -1
	private int getEmptyPollIndex() {
		int index=0;
		while (true) {
			try {
				if (polls[index]==null) {
					return index;
				} else {
					index++;
				}
			} catch (Exception e) {
				return -1;
			}
		}
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
		Party conservative = new Party("Conservative",100,0.444f);
		Party liberal = new Party("Liberal",50,0.222f);
		Party ndp = new Party("NDP",75,0.334f);
		
		Poll testPoll = new Poll("Test Poll",3);
		Poll testPoll2 = new Poll("Test Poll 2",2);
		Poll testPoll3 = new Poll("Test Poll 3",2);
		
		testPoll.addParty(conservative);
		testPoll.addParty(liberal);
		testPoll.addParty(ndp);
		
		testPoll2.addParty(conservative);
		testPoll2.addParty(liberal);
		
		testPoll3.addParty(liberal);
		testPoll3.addParty(ndp);
		
		PollList testList = new PollList(2,225);
		
		testList.addPoll(testPoll);
		testList.addPoll(testPoll2);
		testList.addPoll(testPoll3);
		
		System.out.println(testList);
	}
	
}
