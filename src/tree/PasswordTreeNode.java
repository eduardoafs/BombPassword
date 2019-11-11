package tree;
import java.util.ArrayList;
import java.util.List;

public class PasswordTreeNode {
	private PasswordTreeNode parent;
	private char letter;
	private List<PasswordTreeNode> children;
	
	
	public PasswordTreeNode() {
		letter='\0';
		parent=null;
		children=new ArrayList<PasswordTreeNode>();
	}
	
	public PasswordTreeNode(PasswordTreeNode parent, char letter) {
		this.parent = parent;
		this.letter=letter;
		children=new ArrayList<PasswordTreeNode>();
	}
		
	public String formWord() {
		if (letter=='\0') return "";
		else return parent.formWord()+letter;
	}
	
	public List<String> possibleWords() {
		List<String> words = new ArrayList<String>();
		List<PasswordTreeNode> nodes = children;
		
		if (nodes.isEmpty()) {
			words.add(this.formWord());
		}
		else for (PasswordTreeNode n : nodes) {
			words.addAll(n.possibleWords());
		}
		
		return words;
	}

	public PasswordTreeNode getChild(char _char) {
		for (PasswordTreeNode cur : children) {
			if (cur.getChar()==_char) return cur;
		}
		return null;
	}

	public void addChildren(PasswordTreeNode passwordGraphNode) {
		children.add(passwordGraphNode);
	}

	public List<PasswordTreeNode> getChildren() {
		return this.children;
	}
	
	public Boolean isLeaf() {
		return children.isEmpty();
	}

	public char getChar() {
		return letter;
	}
	
	public List<PasswordTreeNode> getLeaves() {
		List<PasswordTreeNode> leaves = new ArrayList<PasswordTreeNode>();
		if (this.isLeaf()) leaves.add(this);
		else {
			for (PasswordTreeNode node : children) {
				leaves.addAll(node.getLeaves());
			}
		}
		
		return leaves;
	}
}
