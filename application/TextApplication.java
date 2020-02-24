

// Andrew Li's class

package application;

import java.util.Scanner;

/**
 * @author Andrew
 * @version iteration 1
 * 
 * The class TextApplication utilizes all of the other classes (Party, Poll, PollList, and Factory)
 * in order to form a complete text-based application. 
 * 
 * TextApplication contains a constant (the maximum number of stars) and a PollList.
 */
public class TextApplication {
	public final int MAX_NUMBER_OF_STARS = 25; //added instance variable
	private PollList polls; //added instance variable - 
	
	
	/**
	 * The default constructor - takes no arguments and does nothing
	 */
	public TextApplication() { 
	}
	
	/**
	 * The constructor generates a TextApplication and assigns it a PollList
	 * 
	 * @param aPollList	A PollList that will be assigned to TextApplication
	 */
	public TextApplication(PollList aPollList) { //constructor that takes a PollList as an argument - will be called polls
		polls = aPollList;
	}
	
	/**
	 * Visualizes one poll's data
	 * Prints the poll's name
	 * Prints the stars, name, votes, and seats for each party in the poll
	 * 
	 * @param aPoll			A Poll that will have its data visualized
	 */
	public void displayPollDataBySeat(Poll aPoll, int maxNumOfSeats) {
		System.out.println(aPoll.getPollName()); //prints the poll name
		for (Party aParty: aPoll.getPartiesSortedBySeats()) { //for each party in the poll, print name and stats
				System.out.println(aParty.textVisualizationBySeats(MAX_NUMBER_OF_STARS,13,maxNumOfSeats/MAX_NUMBER_OF_STARS));
				}
		System.out.println(""); //prints a space between polls
		}
	
	/**
	 * Visualizes all Poll objects in the polls instance variable then presents an 
	 * aggregate poll that only includes the parties that have been chosen by the user. 
	 * 
	 * @param partyNames		An array of strings containing the names of parties to be included in the aggregate poll
	 */
	public void displayPollsBySeat(String[] partyNames, int maxNumOfSeats) {
		System.out.println("--------");
		System.out.println("");
		System.out.println("DisplayPollsBySeat");
		System.out.println("");
		for (Poll aPoll: polls.getPolls()){
			displayPollDataBySeat(aPoll, maxNumOfSeats);
		}

		Poll Aggregate = polls.getAggregatePoll(partyNames);
		
		System.out.println(Aggregate);
	}
	
	/**
	 * Runs the entire application.
	 * Prompts the user for information
	 * 
	 * Uses factory to generate random polls
	 * 
	 * Creates a TextApplication object then presents the poll data 
	 * 
	 * @param partyNames		An array of strings containing the names of parties to be included in the aggregate poll
	 */
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
			app2.displayPollsBySeat(aggregateNameList, numOfSeats);

		}
		
		if (displayOption.equals("all")) {
			for (Poll aPoll: polls.getPolls()){
				app2.displayPollDataBySeat(aPoll, numOfSeats);
				}
			}		
		keyboard.close();
	}
	
	
	public static void main(String[] args) {
		run();


		/**
		 * @author Nathaly Verwaal
		 * Testing code initially provided by the skeleton
		 */
		
//		int numOfSeats = 100;
//		PollList polls = new PollList(4, numOfSeats);
//		Factory factory = new Factory(numOfSeats);	
//		String[] partyNames = {"BQ","CPC","Green","Liberal","NDP","PPC","Rhinoceros"};
//		factory.setPartyNames(partyNames);
//		polls.addPoll(factory.createRandomPoll("Poll1"));
//		polls.addPoll(factory.createRandomPoll("Poll2"));
//		polls.addPoll(factory.createRandomPoll("Poll3"));
//		polls.addPoll(factory.createRandomPoll("Poll4"));
//		
//		TextApplication app = new TextApplication(polls);
//		app.displayPollsBySeat(factory.getPartyNames());
//		
//
//		/**
//		 *
//		 *@author Andrew Li
//		 *Testing Code
//		 *
//		 *First creates a number of parties, polls, and a poll list. 
//		 */
//		
//		int numOfSeats = 100;
//		Party conservative = new Party("Conservative",200,60.0f);
//		Party liberal = new Party("Liberal",100,30.0f);
//		Party ndp = new Party("NDP",150,40.0f);
//		Party test = new Party("TEST",69,69.0f);
//		
//		String[] displayNameList = {"Conservative","liberal","NDP","Hello"};
//        		
//        
//		Poll testPoll1 = new Poll("Test Poll 1",3);
//		Poll testPoll2 = new Poll("Test Poll 2",2);
//		Poll testPoll3 = new Poll("Test Poll 3",2);
//		
//		testPoll1.addParty(conservative);
//		testPoll1.addParty(liberal);
//		testPoll1.addParty(ndp);
//
//		
//		testPoll2.addParty(conservative);
//		testPoll2.addParty(liberal);
//		
//		testPoll3.addParty(liberal);
//		testPoll3.addParty(ndp);
//		
//		System.out.println(testPoll1);
//		
//		PollList testList = new PollList(3,225);
//		
//		testList.addPoll(testPoll1);
//		testList.addPoll(testPoll2);
//		testList.addPoll(testPoll3);
//		
//		for (Poll aPoll: testList.getPolls()) {
//			System.out.println(aPoll);
//			for (Party aParty: aPoll.getPartiesSortedByVotes()) {
//				System.out.println(aParty.textVisualizationBySeats(1,1,1.0));
//			}
//			System.out.println("");
//
//		}
//		
//		TextApplication testApp = new TextApplication(testList);
//		
//		for (Poll aPoll: testList.getPolls()){
//			testApp.displayPollDataBySeat(aPoll, numOfSeats);
//		}
//		
//		testApp.displayPollDataBySeat(testPoll1, numOfSeats);
//		
//		testApp.displayPollsBySeat(displayNameList, numOfSeats);
//		
//		System.out.println("");
//		System.out.println("");
//		System.out.println("");
//		
	}
}


