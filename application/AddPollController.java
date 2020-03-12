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
    private Factory factory;
    @FXML
    private TextField pollName;
    @FXML
    private ChoiceBox < String > enterPollPosition;

    private PollList localPollList;

    ObservableList < String > pollnames = FXCollections.observableArrayList();

    public void setPollTrackerApp(PollTrackerApp app) {
        this.app = app;
        this.factory = app.getFactory();
        refresh();
    }

    public void handleClear() {
        refresh();
    }

    public void handleSubmit() {
        String pollToReplace = enterPollPosition.getValue();
        int pollCount = 0;
        for (Poll aPoll: localPollList.getPolls()) {
            pollCount = pollCount + 1;
        }
        PollList tempLocalPollList = new PollList(pollCount, localPollList.getNumOfSeats());

        if (pollToReplace == null) {
            System.out.println("Please Choose Something");
        } else {
            for (Poll aPoll: localPollList.getPolls()) {
                if (pollToReplace == aPoll.getPollName()) {
                    Poll newPoll = factory.createRandomPoll(pollName.getText());
                    tempLocalPollList.addPoll(newPoll);
                } else {
                    tempLocalPollList.addPoll(aPoll);
                }
            }

            localPollList = tempLocalPollList;
    		app.setPolls(localPollList);
        }
        refresh();
    }

    //    private PollList generatePollList(int chosenNumOfPolls, int chosenNumOfSeats, String[] chosenNameList) {
    //        PollList newPollList = new PollList(chosenNumOfPolls, chosenNumOfSeats);
    //        Factory factory = new Factory(chosenNumOfSeats);
    //        factory.setPartyNames(chosenNameList);
    //        for (int i = 1; i <= chosenNumOfPolls; i++) {
    //            newPollList.addPoll(factory.createRandomPoll("Poll #" + i));
    //        }
    //        return newPollList;
    //    }


    @Override
    public void refresh() {


        localPollList = this.app.getPolls();

        int numberOfSeats = localPollList.getNumOfSeats();
        int numberOfPolls = 0;
        String[] partyNames = factory.getPartyNames();

        if (pollnames.size() == 0) {

            for (Poll aPoll: localPollList.getPolls()) {
                pollnames.add(aPoll.getPollName());
            }

            if (pollnames.size() > 0) {
                enterPollPosition.setItems(pollnames);
            }

        } else {
            pollnames.clear();
            System.out.println("clear");
            for (Poll aPoll: localPollList.getPolls()) {
                pollnames.add(aPoll.getPollName());
            }
            if (pollnames.size() > 0) {
                enterPollPosition.setItems(pollnames);
            }
        }
        numberOfPolls = pollnames.size();
        
        for (String name : partyNames) {
        System.out.println(name);
        }

    }
}