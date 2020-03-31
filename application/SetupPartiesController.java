package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Factory;

public class SetupPartiesController extends PollTrackerController {
	private PollTrackerApp app;
	
	@FXML
	private ChoiceBox<String> partyMenu;
	@FXML
	private ObservableList<String> partyNames;
	@FXML
	private TextField newPartyName;
	
	
	private Factory localFactory;
	
	public void setPollTrackerApp(PollTrackerApp app) {
		this.app = app;
	}
	
	public void handleSetPartyInfo () {
		//added try catch block
		try {
			int selectedIndex = partyMenu.getSelectionModel().getSelectedIndex();
			String newName = newPartyName.getText();
			partyMenu.getItems().remove(selectedIndex);
			partyMenu.getItems().add(selectedIndex, newName);
			partyNames.set(selectedIndex, newPartyName.getText());
		} catch (NullPointerException e) {
			System.out.println("Empty set party info");
		}
	}
	
	public void handleSubmitPartyInfo () {
		//added try catch block
		try {
			app.getFactory().setPartyNames(partyNames.toArray(new String[0]));
			refresh();
		} catch (NullPointerException e) {
			System.out.println("Empty submit party info");
		}
	}
	
	public void handleClear() {
		refresh();
	}
	
	public void refresh() {
		// added try catch block
		try {
			partyMenu.setItems(FXCollections.observableArrayList(localFactory.getPartyNames()));
		} catch (NullPointerException e) {
			System.out.println("Empty partyNames");
		}
	}
}
