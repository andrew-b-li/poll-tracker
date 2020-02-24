package application;

import java.util.Scanner;

public class TextApplication {
	public int MAX_NUMBER_OF_STARS = 25; //added instance variable
	private PollList polls; //added instance variable - 
	
	public TextApplication() { //default constructor - takes no arguments and does nothing
	}
	
	public TextApplication(PollList aPollList) { //constructor that takes a PollList as an argument - will be called polls
		polls = aPollList;
	}
	
	public void displayPollDataBySeat(Poll aPoll) { //first method to add - visualize one poll's data
		System.out.println(aPoll.getPollName()); //prints the poll name
		for (Party aParty: aPoll.getPartiesSortedBySeats()) { //for each party in the poll, print name and stats
				System.out.println(aParty.textVisualizationBySeats(1,1,1.0));
				}
		System.out.println(""); //prints a space between polls
		}

	public void displayPollsBySeat(String[] partyNames) {
		System.out.println("--------");
		System.out.println("");
		System.out.println("DisplayPollsBySeat");
		System.out.println("");
		for (Poll aPoll: polls.getPolls()){
			displayPollDataBySeat(aPoll);
		}

		Poll Aggregate = polls.getAggregatePoll(partyNames);
		
		System.out.println(Aggregate);

		
//		for (Party aParty: Aggregate.getPartiesSortedBySeats()) { //for each party in the poll, print name and stats
//			System.out.println(aParty.textVisualizationBySeats(1,1,1.0));
//			}	
	}
	
	public static void run() {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Welcome to the poll tracker.");
		
		System.out.println("How many seats are available in the election?");
		int numOfSeats = keyboard.nextInt();
		keyboard.nextLine();
		
		System.out.println("Which parties are in the election (provide names, comma separated)?");
		String names = keyboard.nextLine();
        String[] nameList = names.split(",");
        		
		//String[] partyNames = keyboard.nextInt();
		
		System.out.println("How many polls do you want to track with this application?");
		int numOfPolls = keyboard.nextInt();
		keyboard.nextLine();


		System.out.println("Would you like me to create a random set of polls? (Yes/No)");
		String generateRandom = keyboard.nextLine();

		System.out.println("Options: all (show result of all polls), aggregate (show aggregate result), quit (end application)\n" + 
				"Choose an option:");
		
		String displayOption = keyboard.nextLine();
		displayOption = displayOption.trim();
		displayOption = displayOption.toLowerCase();
		
		//actual stuff

		PollList polls = new PollList(numOfPolls, numOfSeats);
		Factory factory = new Factory(numOfSeats);
		factory.setPartyNames(nameList);
		for (int i = 1; i <= numOfPolls; i++) {
			polls.addPoll(factory.createRandomPoll("Poll #" + i));
		}
		
		TextApplication app2 = new TextApplication(polls);


		if (displayOption.equals("aggregate")) {
			System.out.println("Which parties would you like me to include?");
			String aggregatenames = keyboard.nextLine();
	        String[] aggregateNameList = aggregatenames.split(",");
			app2.displayPollsBySeat(aggregateNameList);

		}
		
		if (displayOption.equals("all")) {
			for (Poll aPoll: polls.getPolls()){
				app2.displayPollDataBySeat(aPoll);
				}
			}

		
		//app2.displayPollsBySeat()
		
		keyboard.close();

	}
	
	
	public static void main(String[] args) {
		run();
		System.out.println("REST OF THE CODE");
		int numOfSeats = 100;
		PollList polls = new PollList(4, numOfSeats);
		Factory factory = new Factory(numOfSeats);
		
		String[] partyNames = {"BQ","CPC","Green","Liberal","NDP","PPC","Rhinoceros"};
		factory.setPartyNames(partyNames);
		polls.addPoll(factory.createRandomPoll("Poll1"));
		polls.addPoll(factory.createRandomPoll("Poll2"));
		polls.addPoll(factory.createRandomPoll("Poll3"));
		polls.addPoll(factory.createRandomPoll("Poll4"));
		
		TextApplication app = new TextApplication(polls);
		app.displayPollsBySeat(factory.getPartyNames());
		
		

		
		Party conservative = new Party("Conservative",200,60.0f);
		Party liberal = new Party("Liberal",100,30.0f);
		Party ndp = new Party("NDP",150,40.0f);
		Party test = new Party("TEST",69,69.0f);

		
        String[] displayNameList = {"Conservative","Liberal","NDP","Hello"};
        		
        
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
		
		System.out.println(testPoll1);
		
		PollList testList = new PollList(3,225);
		
		testList.addPoll(testPoll1);
		testList.addPoll(testPoll2);
		testList.addPoll(testPoll3);
		
		for (Poll aPoll: testList.getPolls()) {
			System.out.println(aPoll);
			for (Party aParty: aPoll.getPartiesSortedByVotes()) {
				System.out.println(aParty.textVisualizationBySeats(1,1,1.0));
			}
			System.out.println("");

		}
		
		TextApplication app1 = new TextApplication(testList);
	
		for (Poll aPoll: testList.getPolls()){
			app.displayPollDataBySeat(aPoll);
		}
		
		app1.displayPollDataBySeat(testPoll1);
		
		app1.displayPollsBySeat(displayNameList);
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
}
}