package application;

import model.Factory;
import model.Poll;
import model.PollList;
import model.Party;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Dvij
 * @version iteration 2
 * The class EditPollController extends PollTrackerController and allows for users to interact 
 * with the "Edit Poll" tab in the GUI. It has methods with handle the clicking of the clear 
 * and submit buttons along with an overwrite of the refresh method which is run each time the tab is opened
 */

public class EditPollController extends PollTrackerController{
	
    @FXML
    private TextField numOfSeats;
    @FXML
    private TextField projectedPercentageOfVotes;
    @FXML
    private ChoiceBox<String> pollEditName;
    @FXML
    private ChoiceBox<String> partyEditName;
    @FXML
    private Label numOfSeatsTextBox;
    
    private PollList localPolls;
    private Factory localFactory;
    private int localNumberOfSeats;
    private float localPercentageOfVotes;
    
    /**
     * Clears all the text-fields and choice-boxes upon the user clicking the 'Clear' button.
     * 
     * @param event FXML detection for mouse click
     */
    
    @FXML
    void handleClear(ActionEvent event) {
    	// Refresh resets all the widgets and keeps the choice boxes up to date
    	refresh();
    }
    
    /**
     * Upon the clicking of the 'Submit' button, edits the party in the poll selected with 
     * the number of seats and projected percentage of votes provided by the user. The list of 
     * polls which is visualized is also changed to reflect these edits.
     * 
     * @param event FXML detection for mouse click
     */
    
    @FXML
    void handleUpdate(ActionEvent event) {
    	// Temporary error checking to make sure something is entered in the numOfSeats TextField
    	if (numOfSeats.getText() == null) {
    		System.out.println("Need number of seats");
    	}
    	//Convert String to Integer value
    	else {
    		localNumberOfSeats = Integer.parseInt(numOfSeats.getText());
    	}
    	// Temporary error checking to make sure something is entered in the projectedPercentageOfVotes TextField
    	if (projectedPercentageOfVotes.getText() == null) {
    		System.out.println("Need number of seats");
    	}
    	//Convert String to Integer value
    	else {
    		localPercentageOfVotes = Float.parseFloat(projectedPercentageOfVotes.getText());
    	}
    	//Obtaining user choice from choice box
    	String nameOfSelectedPoll = pollEditName.getSelectionModel().getSelectedItem();
    	String nameOfSelectedParty = partyEditName.getSelectionModel().getSelectedItem();
    	Poll[] polls = localPolls.getPolls();
    	// Selecting the poll which has the same name as the one selected by the user
    	for (int i = 0; i < polls.length; i++) {
    		if (polls[i].getPollName().contentEquals(nameOfSelectedPoll)) {
    			Poll selectedPoll = polls[i];
    			String[] partyNames = localFactory.getPartyNames();
    			// Selecting the party which has the same name as the one selected by the user
    			for (int j = 0; j < selectedPoll.getNumberOfParties(); j++) {
    				if (partyNames[j].contentEquals(nameOfSelectedParty)) {
    					// Updating the the Party with the information given by the user and re-adding it to the selected poll
    					Party partyEdited = new Party(nameOfSelectedParty, (float)localNumberOfSeats, localPercentageOfVotes/100);
    					selectedPoll.addParty(partyEdited);
    				}
    			}
    		}
    	}
    	setFactory(localFactory);
    	setPolls(localPolls);
    	
    }
    
    /**
     * Refreshes the tab upon loading of the program and the opening of the tab by the user. 
     * Populates the choice boxes with the most recent information of the polls being visualized.
     */
    
    public void refresh() {
    	// Making local copies of the PollList and Factory class in PollTrackerApp
    	localFactory = getFactory();
    	localPolls = getPolls();
    	// Initializes the max number of seats available in the election
    	String numOfSeatsInPoll = Integer.toString(localPolls.getNumOfSeats());
    	numOfSeatsTextBox.setText("/" + numOfSeatsInPoll);
    	Poll[] polls = localPolls.getPolls();
    	String[] pollNames = new String[polls.length];
    	
    	// Setting the pollEditName choice box to display all the polls which are a part of PollList
    	for(int i= 0; i < pollNames.length; i++) {
    		pollNames[i] = polls[i].getPollName();
    	}
    	pollEditName.setItems(FXCollections.observableArrayList(pollNames));
    	
    	// Setting the partyEditName choice box to display all the parties which are included in Polls
    	String[] partyNames = localFactory.getPartyNames();
    	partyEditName.setItems(FXCollections.observableArrayList(partyNames));
    	
    	numOfSeats.clear();
    	projectedPercentageOfVotes.clear();
    }
    
}