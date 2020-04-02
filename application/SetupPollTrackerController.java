package application;

import model.Factory;
import model.PollList;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;

/**
 * @author Olivia
 * @version iteration 2
 * The class SetupPollController extends PollTrackerController,
 * the user can setup a new poll tracker by specifying the number of seats
 * and parties in the election and the number of polls to track
 */

public class SetupPollTrackerController extends PollTrackerController {
	private PollTrackerApp app;
	@FXML
	private TextField numberOfPolls;
	@FXML
	private TextField numberOfSeats;
	@FXML
	private TextField numberOfParties;
	private int localNumberOfPolls = 0;
	private int localNumberOfSeats = 0;
	private int localNumberOfParties = 0;
	private PollList localPolls;
	private Factory localFactory;
	
	public void setPollTrackerApp(PollTrackerApp app) {
		this.app = app;
		if (localNumberOfParties >= 0 && localNumberOfPolls >= 0 && localNumberOfSeats >= 0) {
			createNewPolls();
		} else {
			handleClear();
		}
	}
	
	/**
     * This helper method called after user enters values for
     * number of polls, seats, and parties
     * 
     * Creates a String array for party names and names them
     * 1 and onward at each index
     * 
     * Creates local objects for Factory and PollList, then
     * copies to application
     */
	
	private void createNewPolls() {
		String[] nameList = new String[localNumberOfParties];
		for(int i=0;i<nameList.length;i++) {
			// title party names "1,2,3,etc."
			nameList[i] = Integer.toString(i+1);
		}
		
		//create local factory object
		localFactory = new Factory(localNumberOfSeats);
		localFactory.setPartyNames(nameList);
		
		//create local pollList object
		//localPolls = localFactory.createEmptyPollList(localNumberOfPolls);
		localPolls = localFactory.createRandomPollList(localNumberOfPolls);

		//copy local versions of polls and factory to main app
		app.setPolls(localPolls);
		app.setFactory(localFactory);
		
	}
	
	/**
     * When user presses 'Clear' button, all current
     * infomation entered in the text boxes is initialized
     * to zero and refresh() method called
     * 
     */

	public void handleClear() {
		localNumberOfPolls = 0;
		localNumberOfSeats = 0;
		localNumberOfParties = 0;
		refresh();
		
	}
	
	 /**
     * When user presses 'Submit' button, application should have a new PollList with
     * input from the user for the number of polls to track and the number of seats in election
     * 
     * The Factory object used by the application should indicate the
     * number of seats and parties. Information is displayed in Visualize Polls tab.
     * 
     * If the value for the numer of parties, polls, and seats is valid, createNewPolls()
     * method is called
     * 
     */
	
	public void handleSubmit() {
		//takes arg as string and converts to int
		localNumberOfPolls = Integer.parseInt(numberOfPolls.getText());
		localNumberOfSeats = Integer.parseInt(numberOfSeats.getText());
		localNumberOfParties = Integer.parseInt(numberOfParties.getText());
		
		if (localNumberOfParties >= 0 && localNumberOfPolls >= 0 && localNumberOfSeats >= 0) {
			createNewPolls();
		} else {
			handleClear();
		}
		
		refresh();
		
	}
	
    /**
     * During initial loading ofprogram and opening of tab by user, tab is refreshed 
     * Fill choice boxes with the most recent information of the polls being visualized.
     */
	
	public void refresh() {
		//takes arg as int and converts to string
		numberOfPolls.setText(Integer.toString(localNumberOfPolls));
		numberOfSeats.setText(Integer.toString(localNumberOfSeats));
		numberOfParties.setText(Integer.toString(localNumberOfParties));		
	}
}
