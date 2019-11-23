import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

import dispatch.*;

class Dispatch {
	private static Threats thr = new Threats();
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Please input call queue file: ./Dispatch <textfile>");
		} else { //proceed with processing input
			List<String> list = new ArrayList<String>();
			try {
				Scanner scan = new Scanner(new File(args[0]));
				while (scan.hasNextLine()) {
					list.add(scan.nextLine());
				//	thr.categorizeCall(scan.nextLine());
				}
			} catch (Exception e) {
				System.out.println("Dispatch input error: " + e.getMessage());
			}
			for (String x : list) {
				//System.out.println("GOT: " + x);
				Emergency resp = thr.categorizeCall(x);
				resp.printout();
			}
		}
	}
}
