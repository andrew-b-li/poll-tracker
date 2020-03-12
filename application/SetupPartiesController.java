package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import model.Factory;

public class SetupPartiesController extends PollTrackerController {
	private PollTrackerApp app;
	
	private ChoiceBox<String> partyMenu;
	private ObservableList<String> partyNames;
	private TextField newPartyName;
	
	private Factory localFactory;
	
	public void setPollTrackerApp(PollTrackerApp app) {
		this.app = app;
	}
	
	public void handleSetPartyInfo () {
		int selectedIndex = partyMenu.getSelectionModel().getSelectedIndex();
		String newName = newPartyName.getText();
		partyMenu.getItems().remove(selectedIndex);
		partyMenu.getItems().add(selectedIndex, newName);
		partyNames.set(selectedIndex, newPartyName.getText());
	}
	
	public void handleSubmitPartyInfo () {
		app.getFactory().setPartyNames(partyNames.toArray(new String[0]));
		refresh();
	}
	
	public void handleClear() {
		refresh();
	}
	
	public void refresh() {
		partyMenu.setItems(FXCollections.observableArrayList(localFactory.getPartyNames()));
	}
}
