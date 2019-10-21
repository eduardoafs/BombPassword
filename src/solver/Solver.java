package solver;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import graph.PasswordGraph;
import graph.PasswordGraphNode;

public class Solver {

	private final String[] words = {
			"about", "after", "again", "below", "could",
			"every", "first", "found", "great", "house",
			"large", "learn", "never", "other", "place",
			"plant", "point", "right", "small", "sound",
			"spell", "still", "study", "their", "there",
			"these", "thing", "think", "three", "water",
			"where", "which", "world", "would", "write"
	};
	
	private PasswordGraph graph;
	private Collection<PasswordGraphNode> candidates; 
	
	public Solver() {
		graph = new PasswordGraph();
		candidates = new HashSet<PasswordGraphNode>();
		for (String word : words) {
			graph.addWord(word);
		}
		candidates.add(graph.getRoot());
	}
	
	public void restart() {
		candidates.clear();
		candidates.add(graph.getRoot());
	}
	
	public void addFilter(final String chars) {
		String _chars = chars.toLowerCase().replace(' ', '\0');

		Set<PasswordGraphNode> newCandidates = new HashSet<PasswordGraphNode>();
		
		Iterator<PasswordGraphNode> it = candidates.iterator();
		char cchar;
		PasswordGraphNode current;
		PasswordGraphNode children;
		while (it.hasNext()) {
			current = it.next();
			for (int index=0; index<_chars.length(); index++) {
				cchar = _chars.charAt(index);
				children = current.getChild(cchar);
				if (children!=null) newCandidates.add(children);
			}
		}
		this.candidates = newCandidates;
	}
	
	public Collection<String> getPossibleWords() {
		HashSet<String> words = new HashSet<String>();
		
		for (PasswordGraphNode node : candidates) {
			for (PasswordGraphNode leaf : node.getLeaves()) {
				words.add(leaf.formWord());
			}
		}
		return words;
	}
	
}
