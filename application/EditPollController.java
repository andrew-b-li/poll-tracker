package application;

import model.Factory;
import model.Poll;
import model.PollList;
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
    private Factory localFactory = new Factory(325);
    
	
    @FXML
    void initialize() {
    	String[] partyList = {"Green", "Blue", "Red"};
    	localFactory.setPartyNames(partyList);
    	localPolls = localFactory.createRandomPollList(3);
    	/*Poll[] polls = localPolls.getPolls();
    	String[] pollNames = new String[polls.length];
    	for(int i= 0; i < pollNames.length; i++) {
    		pollNames[i] = polls[i].getPollName();
    	}
    	pollEditName.setItems(FXCollections.observableArrayList(pollNames));*/
   
    }
    
    @FXML
    void handleClear(ActionEvent event) {
    	/*numOfSeats.clear();
    	projectedPercentageOfVotes.clear();
    	pollEditName.setValue(null);
    	partyEditName.setValue(null);*/
    	
    }

    @FXML
    void handleUpdate(ActionEvent event) {
    	
    }
    
    public void refresh() {
    	/*localPolls = super.getPolls();
    	localFactory = super.getFactory();
    	numOfSeats.clear();
    	projectedPercentageOfVotes.clear();
    	pollEditName.setValue(null);
    	partyEditName.setValue(null);*/
    }
    
}