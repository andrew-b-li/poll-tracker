package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Factory;
import model.Poll;
import model.PollList;

public class AddPollController extends PollTrackerController {
	private PollTrackerApp app;
	
	@FXML
	private TextField pollName;
	
	@FXML
	private ChoiceBox<String> pollPosition;
	
	private int localNameOfPoll = 0;
	private int localPollPosition = 0;


	
	private PollList localPollList;
	private Factory factory;
	
	ObservableList<String> pollnames = FXCollections.observableArrayList();
	
	public void setPollTrackerApp(PollTrackerApp app) {
			this.app = app;
	}
	
	public void setFactory(Factory factory) {
		this.factory = factory;

	}

	
	public void handleClear() {
	}
	
	public void handleSubmit() {
		localPollList = this.app.getPolls();
		ArrayList<String> polls = new ArrayList<String>();
		for (Poll aPoll: localPollList.getPolls()) {
            System.out.println(aPoll.getPollName());
            pollnames.add(aPoll.getPollName());
		}
		pollPosition.setItems(pollnames);
		
	}
	
	@Override
	public void refresh() {
		}		
	}