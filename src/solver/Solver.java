package solver;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import tree.PasswordTree;
import tree.PasswordTreeNode;

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
	
	private PasswordTree graph;
	private Collection<PasswordTreeNode> candidates; 
	
	public Solver() {
		graph = new PasswordTree();
		candidates = new HashSet<PasswordTreeNode>();
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

		Set<PasswordTreeNode> newCandidates = new HashSet<PasswordTreeNode>();
		
		Iterator<PasswordTreeNode> it = candidates.iterator();
		char cchar;
		PasswordTreeNode current;
		PasswordTreeNode children;
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
		
		for (PasswordTreeNode node : candidates) {
			for (PasswordTreeNode leaf : node.getLeaves()) {
				words.add(leaf.formWord());
			}
		}
		return words;
	}
	
}
