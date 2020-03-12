package application;

import model.Factory;
import model.Poll;
import model.PollList;
import model.Party;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class EditPollController extends PollTrackerController{
	
	private PollTrackerApp app;
	
    @FXML
    private TextField numOfSeats;
    @FXML
    private TextField projectedPercentageOfVotes;
    @FXML
    private ChoiceBox<String> pollEditName;
    @FXML
    private ChoiceBox<String> partyEditName;
    
    private PollList localPolls;
    private Factory localFactory;
    private int localNumberOfSeats;
    private float localPercentageOfVotes;
    
    public void setPollTrackerApp(PollTrackerApp app) {
    	this.app = app;
    	refresh();
    }
    
    @FXML
    void handleClear(ActionEvent event) {
    	refresh();
    }

    @FXML
    void handleUpdate(ActionEvent event) {
    	if (numOfSeats.getText() == null) {
    		System.out.println("Need number of seats");
    	}
    	else {
    		localNumberOfSeats = Integer.parseInt(numOfSeats.getText());
    	}
    	if (projectedPercentageOfVotes.getText() == null) {
    		System.out.println("Need number of seats");
    	}
    	else {
    		localPercentageOfVotes = Float.parseFloat(projectedPercentageOfVotes.getText());
    	}
    	String nameOfSelectedPoll = pollEditName.getSelectionModel().getSelectedItem();
    	String nameOfSelectedParty = partyEditName.getSelectionModel().getSelectedItem();
    	this.localFactory = app.getFactory();
    	this.localPolls = app.getPolls();
    	Poll[] polls = localPolls.getPolls();
    	for (int i = 0; i < polls.length; i++) {
    		if (polls[i].getPollName().contentEquals(nameOfSelectedPoll)) {
    			Poll selectedPoll = polls[i];
    			String[] partyNames = localFactory.getPartyNames();
    			for (int j = 0; j < selectedPoll.getNumberOfParties(); j++) {
    				if (partyNames[j].contentEquals(nameOfSelectedParty)) {
    					Party partyEdited = new Party(nameOfSelectedParty, (float)localNumberOfSeats, localPercentageOfVotes/100);
    					selectedPoll.addParty(partyEdited);
    				}
    			}
    		}
    	}
    	app.setFactory(localFactory);
    	app.setPolls(localPolls);
    	
    }
    
    public void refresh() {
    	this.localFactory = app.getFactory();
    	this.localPolls = app.getPolls();
    	Poll[] polls = localPolls.getPolls();
    	String[] pollNames = new String[polls.length];
    	for(int i= 0; i < pollNames.length; i++) {
    		pollNames[i] = polls[i].getPollName();
    	}
    	pollEditName.setItems(FXCollections.observableArrayList(pollNames));
    	
    	String[] partyNames = localFactory.getPartyNames();
    	partyEditName.setItems(FXCollections.observableArrayList(partyNames));

    	numOfSeats.clear();
    	projectedPercentageOfVotes.clear();
    }
    
}