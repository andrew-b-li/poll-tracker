package application;

import model.Factory;
import model.PollList;

public class AddPollController extends PollTrackerController {
	private PollTrackerApp app;
	private PollList localPolls;
	private Factory localFactory;
	
	public void setPollTrackerApp(PollTrackerApp app) {
			this.app = app;
		}
	
	public void handleClear() {
	}
	
	public void handleSubmit() {
	}
	
	@Override
	public void refresh() {
		}
	}
		