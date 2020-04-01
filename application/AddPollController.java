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

public class AddPollController extends PollTrackerController {

    
    @FXML
    private TextField pollName;
    @FXML
    private ChoiceBox<String> pollPosition;
    
	private PollList localPollList;
    private Factory localFactory;
    private PollList tempPollList;

    public void handleClear(ActionEvent event) {
        refresh();
    }

    public void handleSubmit(ActionEvent event) {
    	if (pollName.getText().equals(null)) {
    		System.out.println("Need a name");
    	}
    	int pollToReplace = pollPosition.getSelectionModel().getSelectedIndex();
    	System.out.println(pollToReplace);
        
    	if (pollToReplace == -1) {
            System.out.println("Please Choose Something");
        } 

        Poll[] polls = localPollList.getPolls();
        int numOfPolls = polls.length;
        int numOfSeats = localPollList.getNumOfSeats();
        tempPollList = new PollList(numOfPolls, numOfSeats);
        for (int i = 0; i < numOfPolls; i++) {
        	if (pollToReplace == i) {
        		Poll newPoll = localFactory.createRandomPoll(pollName.getText());
        		tempPollList.addPoll(newPoll);
        	}
        	else {
        		tempPollList.addPoll(polls[i]);
        	}
        			
        }
        setPolls(tempPollList);
    	setFactory(localFactory);
    	refresh();
    }

    @Override
    public void refresh() {
        localPollList = getPolls();
        localFactory = getFactory();
    	Poll[] polls = localPollList.getPolls();
    	String[] pollNames = new String[polls.length];
    	for(int i= 0; i < pollNames.length; i++) {
    		pollNames[i] = i+1 + "(replaces poll " + polls[i].getPollName() + ")";
    	}
    	pollPosition.setItems(FXCollections.observableArrayList(pollNames));
        pollName.clear();
    }
}