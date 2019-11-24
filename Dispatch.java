import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;
import java.util.Queue;
import java.util.PriorityQueue;

import dispatch.*;

class Dispatch {
	private static Threats thr = new Threats();

	static int STATION_MAX = 3;
	static int REQUESTS_PER_DAY = 20;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Please input call queue file: ./Dispatch <textfile>");
		} else { //proceed with processing input
			Queue<Emergency> queue = new PriorityQueue<Emergency>();
			//List<String> list = new ArrayList<String>();
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
			//System.out.println(list.size());
			//main operation loop
			/*
			int count = 0;
			for (String x : list) {
				queue.add(thr.categorizeCall(x));
				count++;
				if(count > 10) {
					break;
				}
			}
			while(!q.isEmpty()) {
				Emergency e = queue.remove();
				e.printout();
			}
			*/
			int countQueued = 0;
			boolean stop = false;
			while(!list.isEmpty()) {
				//take in requests per day
				while(countQueued < REQUESTS_PER_DAY && !stop) {
					if(list.size() > 0) {
						String call = list.remove();
						queue.add(thr.categorizeCall(call));
						countQueued++;
					} else {
						stop = true;
					}
				}
				System.out.println("=====");
				while(!queue.isEmpty()) {
					Emergency er = queue.remove();
					er.printout();
				}
				countQueued = 0;
				stop = false;
			}
		}
	}
}
