package dispatch;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
//import java.util.PriorityQueue;

import dispatch.*;

public class Station {
	private String name;
	private int maxToProcess; // number to process per day
	private int processed;

	public boolean processEmergency(Emergency er) {	//will add to queue umnless not possible
		//if can add to queue, adds to queue, return 1
		//else, return 0
		if((er.priority != Emergency.Threat.HIGH) && (processed >= maxToProcess)) {
			return true;
		} else { //do stuff
			er.respond();
			processed++;
			return false;
		}
	}

	public void reset() {
		processed = 0;
	}

	public Station(String stationName, int stationMax) {
		name = stationName;
		maxToProcess = stationMax;
		processed = 0;
	}
}
