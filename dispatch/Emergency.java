package dispatch;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Emergency implements Comparable<Emergency> {
	public enum Threat { HIGH, MEDIUM, LOW };
	public enum Responder { ENVIRONMENT, MEDICAL, VEHICLE, FACILITY };
	Threat priority;
	Responder responder;
	String call;
	String station;

	public Emergency(String c) {
		call = c;
	}

	public void respond() {
		System.out.println("Dispatching " + priority + " " + responder + " responder to " + station);
	}

	public String getStation() {
		return station;
	}

	public int compareTo(Emergency er) { //if this is less important than argument, return -1; greater, 1; even, 0
		return priority.compareTo(er.priority);
	}
}
