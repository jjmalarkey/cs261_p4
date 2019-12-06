import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;

import dispatch.*;

class Dispatch {
	private static Threats thr = new Threats();

	static int STATION_MAX = 3;
	static int REQUESTS_PER_DAY = 20;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Please input call queue file: ./Dispatch <textfile>");
		} else { //proceed with processing input
			Map<String, Station> stations = new HashMap<String, Station>();
			Station al = new Station("Alabama", STATION_MAX);
			stations.put("Alabama", al);
			Station bj = new Station("Bon Jovi", STATION_MAX);
			stations.put("Bon Jovi", bj);
			Station bn = new Station("Boston", STATION_MAX);
			stations.put("Boston", bn);
			Station ch = new Station("Chicago", STATION_MAX);
			stations.put("Chicago", ch);
			Station ka = new Station("Kansas", STATION_MAX);
			stations.put("Kansas", ka);
			Station jn = new Station("Journey", STATION_MAX);
			stations.put("Journey", jn);
			Station sv = new Station("Survivor", STATION_MAX);
			stations.put("Survivor", sv);
			Queue<Emergency> queue = new PriorityQueue<Emergency>();
			Queue<String> list = new LinkedList<String>();
			List<Emergency> leftover = new ArrayList<Emergency>();
			try {
				Scanner scan = new Scanner(new File(args[0]));
				while (scan.hasNextLine()) {
					list.add(scan.nextLine());
				}
			} catch (Exception e) {
				System.out.println("Dispatch input error: " + e.getMessage());
			}
			int daysPassed = 1;
			int countQueued = 0;
			boolean stop = false;
			while(!list.isEmpty() || !leftover.isEmpty()) {
				//take in requests per day
				while(countQueued < REQUESTS_PER_DAY && !stop) {
					//refresh daily request cap
					for(Station stat : stations.values()) {
						stat.reset();
					}
					if(list.size() > 0) {
						String call = list.remove();
						queue.add(thr.categorizeCall(call));
						countQueued++;
					} else {
						stop = true;
					}
				}
				System.out.println("\n======= Processing requests for day " + daysPassed);
				System.out.println("======= " + leftover.size() + " requests carried over from previous day");
				if(leftover.size() > 0) {
					queue.addAll(leftover);
					leftover.clear();
				}
				while(!queue.isEmpty()) {
					Emergency er = queue.remove();
					Station stat = stations.get(er.getStation());
					//if can be processed by station, do not add to leftovers
					if(stat.processEmergency(er)) {
						leftover.add(er);
					}
					//else do not
				}
				countQueued = 0;
				stop = false;
				daysPassed++;
			}
		}
	}
}
