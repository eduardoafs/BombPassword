package graph;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PasswordGraph {

	private PasswordGraphNode start;
	
	public PasswordGraph() {
		start = new PasswordGraphNode();
	}
	
	public PasswordGraph(String word) {
		start=new PasswordGraphNode();
		
		this.addWord(word);
	}
	
	public PasswordGraph(List<String> words) {
		start=new PasswordGraphNode();
		for (String word : words) {
			this.addWord(word);
		}
	}
	
	public void addWord(String word) {
		if (word.isBlank()) return;

		PasswordGraphNode current = start;
		PasswordGraphNode next = null;
		for (int index=0; index<word.length(); index++) {
			char _char = word.charAt(index);
			next = current.getChild(_char);
			if (next==null) { 
				next = new PasswordGraphNode(current, word.charAt(index));
				current.addChildren(next);
			}
			current = next;
		}
	}
	
	public void printGraph() {
		Queue<PasswordGraphNode> nodes = new ConcurrentLinkedQueue<PasswordGraphNode>();
		nodes.add(start);
		
		PasswordGraphNode current;
		while (!nodes.isEmpty()) {
			current=nodes.poll();
			System.out.println(current.getChar()+ " ");
			nodes.addAll(current.getChildren());
		}
	}
	
	public List<PasswordGraphNode> getLeaves() {
		return start.getLeaves();
	}

	public PasswordGraphNode getRoot() {
		return start;
	}
}
