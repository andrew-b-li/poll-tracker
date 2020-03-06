package application;

import model.Factory;
import model.PollList;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;

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
		createNewPolls();
	}
	
	// helper method
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
		localPolls = localFactory.createRandomPollList(localNumberOfPolls);
		
		//copy local versions of polls and factory to main app
		app.setPolls(localPolls);
		app.setFactory(localFactory);
		
	}

	public void handleClear() {
		localNumberOfPolls = 0;
		localNumberOfSeats = 0;
		localNumberOfParties = 0;
		refresh();
		
	}
	
	public void handleSubmit() {
		//takes arg as string and converts to int
		localNumberOfPolls = Integer.parseInt(numberOfPolls.getText());
		localNumberOfSeats = Integer.parseInt(numberOfSeats.getText());
		localNumberOfParties = Integer.parseInt(numberOfParties.getText());
		
		createNewPolls();
		refresh();
		
	}
	
	public void refresh() {
		//takes arg as int and converts to string
		numberOfPolls.setText(Integer.toString(localNumberOfPolls));
		numberOfSeats.setText(Integer.toString(localNumberOfSeats));
		numberOfParties.setText(Integer.toString(localNumberOfParties));		
	}
}
