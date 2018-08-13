package binary_trees;

public class BinarySearchTree {

	public BinaryTreeNode<Integer> root;
	

	public boolean search(int data, BinaryTreeNode<Integer> root) {
		if (root == null) {
			return false;
		}

		if (root.data == data) {
			return true;
		}

		else if (root.data < data) {
			return search(data, root.right);
		} else {
			return search(data, root.left);
		}

	}

	public boolean search(int data) {
		return search(data, root);

	}

	public void insertData(int data) {
	    insertData(root,data);
	}

	public BinaryTreeNode<Integer> insertData(BinaryTreeNode<Integer> root,int data) {
		if(root==null){
			BinaryTreeNode<Integer> Node = new BinaryTreeNode<>(data);
			return Node;
		}
		BinaryTreeNode<Integer> nextNode ;
		if(root.data<=data){
			nextNode = insertData(root.right,data);
			root.right = nextNode;
		}
		else if(root.data>data){
			nextNode = insertData(root.left,data);
			root.left = nextNode;
		}
		
		return root;
	}
	
	public void deleteData(int data){
		deleteData(root,data);
	}
	
	
	
	public BinaryTreeNode<Integer> deleteData(BinaryTreeNode<Integer> root, int data){
		if(root==null){
			return null;
		}
		
		if(root.data==data){
			if(root.left==null&&root.right==null){
				return null;
			}
			else if(root.left!=null&&root.right==null){
				return root.left;
			}
			else if(root.right!=null&&root.left==null){
				return root.right;
			}
			else{
				int minRight = minNumInTree(root.right);
				BinaryTreeNode<Integer> newRoot = new BinaryTreeNode<>(minRight);
				root.right=deleteData(root.right,minRight);
				newRoot.left = root.left;
				newRoot.right = root.right;
				return newRoot;
			}
		}
		else if(root.data>data){
			root.left = deleteData(root.left,data);
			return root;
		}
		else{
			root.right = deleteData(root.right,data);
			return root;
		}
	}

	public int minNumInTree(BinaryTreeNode<Integer> root) {
		// TODO Auto-generated method stub
		if(root.left==null){
			return root.data;
		}
		return minNumInTree(root.left);
		
	}
	
	public void printTree(){
		printTree(root);
	}
	
	public void printTree(BinaryTreeNode<Integer> root){
		if(root == null ){
			return;
		}
		
		String S = "" + root.data+":";
		if(root.left!=null){
			S += "L:"+root.left.data;
			if(root.right!=null){
				S += ",R:"+root.right.data;
			}
		}
		else{
			if(root.right!=null){
				S += "R:"+root.right.data;
			}
		}
		
		
		
		
		System.out.println(S);
		printTree(root.left);
		printTree(root.right);
	}
	
}
