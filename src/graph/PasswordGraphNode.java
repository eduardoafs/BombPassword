package graph;
import java.util.ArrayList;
import java.util.List;

public class PasswordGraphNode {
	private PasswordGraphNode parent;
	private char letter;
	private List<PasswordGraphNode> children;
	
	
	public PasswordGraphNode() {
		letter='\0';
		parent=null;
		children=new ArrayList<PasswordGraphNode>();
	}
	
	public PasswordGraphNode(PasswordGraphNode parent, char letter) {
		this.parent = parent;
		this.letter=letter;
		children=new ArrayList<PasswordGraphNode>();
	}
		
	public String formWord() {
		if (letter=='\0') return "";
		else return parent.formWord()+letter;
	}
	
	public List<String> possibleWords() {
		List<String> words = new ArrayList<String>();
		List<PasswordGraphNode> nodes = children;
		
		if (nodes.isEmpty()) {
			words.add(this.formWord());
		}
		else for (PasswordGraphNode n : nodes) {
			words.addAll(n.possibleWords());
		}
		
		return words;
	}

	public PasswordGraphNode getChild(char _char) {
		for (PasswordGraphNode cur : children) {
			if (cur.getChar()==_char) return cur;
		}
		return null;
	}

	public void addChildren(PasswordGraphNode passwordGraphNode) {
		children.add(passwordGraphNode);
	}

	public List<PasswordGraphNode> getChildren() {
		return this.children;
	}
	
	public Boolean isLeaf() {
		return children.isEmpty();
	}

	public char getChar() {
		return letter;
	}
	
	public List<PasswordGraphNode> getLeaves() {
		List<PasswordGraphNode> leafs = new ArrayList<PasswordGraphNode>();
		if (this.isLeaf()) leafs.add(this);
		else {
			for (PasswordGraphNode node : children) {
				leafs.addAll(node.getLeaves());
			}
		}
		
		return leafs;
	}
}
