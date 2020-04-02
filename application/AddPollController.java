package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import model.Factory;
import model.Party;
import model.Poll;
import model.PollList;

/**
 * @author Andrew
 * @version iteration 2
 * The class AddPollController extends PollTrackerController and allows for users to interact 
 * with the "Add Poll" tab in the GUI. It has methods with handle the entering of poll names and the
 * clicking of the clear and submit buttons along with an overwrite of the refresh method 
 * which is run each time the tab is opened.
 */

public class AddPollController extends PollTrackerController {

    @FXML
    private TextField pollName;
    @FXML
    private ChoiceBox < String > selectPollPosition;

    private PollList localPollList;
    private Factory localFactory;
    private PollList tempPollList;

    /**
     * Clears all the text-fields and choice-boxes upon the user clicking the 'Clear' button.
     * 
     * @param event FXML detection for mouse click
     */

    public void handleClear(ActionEvent event) {
        // Refresh resets all the widgets and keeps the choice boxes up to date
        refresh();
    }

    /**
     * Upon the clicking of the 'Submit' button, creates a poll with the name provided by the user.
     * This new poll replaces the poll selected in the choice box. The list of 
     * polls which is visualized is also changed to reflect these edits.
     * 
     * @param event FXML detection for mouse click
     */

    public void handleSubmit(ActionEvent event) {
        // Temporary error checking to make sure a poll name is entered 
        if (pollName.getText().equals("")) {
            System.out.println("Please enter a poll name");
        }
        // Selecting the poll to be replaced with the new poll
        int pollToReplace = selectPollPosition.getSelectionModel().getSelectedIndex();
        System.out.println("Replacing poll at index: " + pollToReplace);
        // Temporary error checking to make sure a poll position is selected
        if (pollToReplace == -1) {
            System.out.println("Please choose a valid poll to replace");
        }
        // Creating a new list of polls by copying the previous list and replacing the desired poll
        Poll[] polls = localPollList.getPolls();
        int numOfPolls = polls.length;
        int numOfSeats = localPollList.getNumOfSeats();
        tempPollList = new PollList(numOfPolls, numOfSeats);
        for (int i = 0; i < numOfPolls; i++) {
        	// Creates a new poll with the chosen name at the chosen index. All other polls are simply copied
            if (pollToReplace == i) {
                Poll newPoll = localFactory.createRandomPoll(pollName.getText());
                tempPollList.addPoll(newPoll);
            } else {
                tempPollList.addPoll(polls[i]);
            }

        }
        setPolls(tempPollList);
        setFactory(localFactory);
        refresh();
    }

    /**
     * Refreshes the tab upon loading of the program and the opening of the tab by the user. 
     * Populates the choice box with the most recent information of the polls being visualized.
     */

    public void refresh() {
    	// Making local copies of the PollList and Factory class in PollTrackerApp
        localPollList = getPolls();
        localFactory = getFactory();
        Poll[] polls = localPollList.getPolls();
        String[] pollNames = new String[polls.length];
        
    	// Setting the selectPollPosition choice box to display all the polls which are a part of PollList
        for (int i = 0; i < pollNames.length; i++) {
            pollNames[i] = i + 1 + "(replaces poll " + polls[i].getPollName() + ")";
        }
        selectPollPosition.setItems(FXCollections.observableArrayList(pollNames));
        pollName.clear();
    }
}