package tree;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PasswordTree {

	private PasswordTreeNode start;
	
	public PasswordTree() {
		start = new PasswordTreeNode();
	}
	
	public PasswordTree(String word) {
		start=new PasswordTreeNode();
		
		this.addWord(word);
	}
	
	public PasswordTree(List<String> words) {
		start=new PasswordTreeNode();
		for (String word : words) {
			this.addWord(word);
		}
	}
	
	public void addWord(String word) {
		if (word.isBlank()) return;

		PasswordTreeNode current = start;
		PasswordTreeNode next = null;
		for (int index=0; index<word.length(); index++) {
			char _char = word.charAt(index);
			next = current.getChild(_char);
			if (next==null) { 
				next = new PasswordTreeNode(current, word.charAt(index));
				current.addChildren(next);
			}
			current = next;
		}
	}
	
	public void printGraph() {
		Queue<PasswordTreeNode> nodes = new ConcurrentLinkedQueue<PasswordTreeNode>();
		nodes.add(start);
		
		PasswordTreeNode current;
		while (!nodes.isEmpty()) {
			current=nodes.poll();
			System.out.println(current.getChar()+ " ");
			nodes.addAll(current.getChildren());
		}
	}
	
	public List<PasswordTreeNode> getLeaves() {
		return start.getLeaves();
	}

	public PasswordTreeNode getRoot() {
		return start;
	}
}
