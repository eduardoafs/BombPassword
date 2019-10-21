package ui;
import java.util.Collection;
import java.util.Scanner;

import solver.Solver;


public class DefaultUI {

	public static void main(String[] args) {
		Solver solver = new Solver();
		
		Scanner s = new Scanner(System.in);

		String pattern;
		while (true) {
			printPossibleWords(solver);
			System.out.println("-> Enter new charset: (-1) to exit, (0) to restart");
			pattern = s.nextLine().replace('\n', '\0');
			if (pattern.compareTo("-1")==0) {
				s.close();
				break;
			}
			else if (pattern.compareTo("0")==0) {
				System.out.println("-> Restarting...");
				solver.restart();
				continue;
			}
			else {
				solver.addFilter(pattern);
			}
		}
		s.close();
	}
	
	private static void printPossibleWords(Solver solver) {
		Collection<String> possible = solver.getPossibleWords();
		System.out.println("-> Possible words ("+possible.size()+"):");
		for (String word : possible) {
			System.out.println(word);
		}
	}

}
