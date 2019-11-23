package dispatch;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class Emergency {
	public enum Threat { LOW, MEDIUM, HIGH };
	public enum Responder { ENVIRONMENT, MEDICAL, VEHICLE, FACILITY };
	Threat priority;
	Responder responder;
	String call;
	String station;
	public Emergency(String c) {
		call = c;
	}
	public void printout() {
		System.out.println("CALL: " + call + "\tTHREAT: " + priority + "\tRESPONSE: " + responder);
	}
}
