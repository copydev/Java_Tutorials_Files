package binary_trees;

import java.util.ArrayList;
import java.util.Scanner;

import Queue.QueueEmptyException;
import Queue.QueueUsingLL;
import linkedList.Node;

public class BinaryTreeUse {
	
	public static void printTree(BinaryTreeNode<Integer> root){
		if(root ==null){
			return;
		}
		String toBePrinted = root.data + " ";
		if(root.left != null){
			toBePrinted += "L:" + root.left.data +",";
		}
		
		if(root.right != null){
			toBePrinted += "R:" + root.right.data+ ",";
		}
		
		System.out.println(toBePrinted);
		printTree(root.left);
		printTree(root.right);
		
	}
	
	public static BinaryTreeNode<Integer> takeInput(Scanner s){
		int rootData;
		System.out.println("Enter root data");
		rootData= s.nextInt();
		if(rootData==-1){
			return null;
		}
		BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);
		root.left = takeInput(s);
		root.right = takeInput(s);
		
		return root;
		
	}
	
	public static BinaryTreeNode<Integer> takeInputLevelWise(){
		Scanner s = new Scanner(System.in);
		QueueUsingLL<BinaryTreeNode<Integer>> pendingNodes = new QueueUsingLL<>();
		System.out.println("enter root data");
		int rootData= s.nextInt();
		if(rootData==-1){
			return null;
		}
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
		pendingNodes.enqueue(root);
		
		while(!pendingNodes.isEmpty()){
			BinaryTreeNode<Integer> front = null;
			try {
				 front = pendingNodes.dequeue();
			} catch (QueueEmptyException e) {
				return null;
			}
			
			System.out.println("Enter leftChild of "+ front.data);
			int leftChild = s.nextInt();
			if(leftChild!=-1){
				BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>(leftChild);
				pendingNodes.enqueue(left);
				front.left = left;
			}
			System.out.println("Enter rightChild of " + front.data);
			int rightChild = s.nextInt();
			if(rightChild!=-1){
				BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>(rightChild);
				pendingNodes.enqueue(right);
				front.right = right;
			}
			
		}
		return root;
	}
	public static boolean isBST(BinaryTreeNode<Integer> root) {
		/* Your class should be named Solution
		* Don't write main().
		* Don't read input, it is passed as function argument.
		* Return output and don't print it.
		* Taking input and printing output is handled automatically.
		*/
      int k = 0;
      return isBST(root,k).ans;
		
	}
  
  	public static Helper isBST(BinaryTreeNode<Integer> root,int k){
      if(root==null){
        return null;
      }
      if(root.left==null&&root.right==null){
        Helper temp = new Helper();
        temp.ans = true;
    	temp.max= root.data;
        temp.min = root.data;
        return temp;
      }
      Helper left = isBST(root.left,k);
      Helper right = isBST(root.right,k);
      
      Helper sol = new Helper();
      if(left==null){
        sol.max = right.max;
        sol.min = root.data;
        if(right.ans==true){
        if(root.data<=right.min){
          sol.ans = true;
        }
        else{
          sol.ans = false;
        }
        return sol;
        }
        else{
          sol.ans = false;
          return sol;
        }
      }
      else if(right==null){
        sol.max = root.data;
        sol.min = left.min;
        if(left.ans==true){
          if(root.data>left.max){
            sol.ans = true;
            return sol;
          }
          else{
            sol.ans = false;
            return sol;
          }
        }
        else{
          sol.ans = false;
          return sol;
        }
      }
      
      
      
    
       sol.max = right.max;
       sol.min = left.min;
      if(left.ans==false||right.ans==false){
        sol.ans  =false;
        return sol;
      }
      
      if(left.max<root.data&&right.min>=root.data){
       
        sol.ans = true;
        return sol;
      }
      else{
        sol.ans  = false;
        return sol;
      }
      
    }
	
	public static int countNodes(BinaryTreeNode<Integer> root){
		if(root==null){
			return 0;
		}
		
		int ans = 1;
		ans+= countNodes(root.left);
		ans+= countNodes(root.right);
		return ans;
		
	}
	public static void printLevelWise(BinaryTreeNode<Integer> root){
		if(root==null){
			return;
		}
		QueueUsingLL<BinaryTreeNode<Integer>> pendingNodes = new QueueUsingLL<>();
		pendingNodes.enqueue(root);
		while(!pendingNodes.isEmpty()){
			String s = new String();
			BinaryTreeNode<Integer> front = null;
			try {
				front = pendingNodes.dequeue();
			} catch (QueueEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s= front.data +":";
			if(front.left!=null){
				s+="L:"+front.left.data+",";
				pendingNodes.enqueue(front.left);
			}
			else{
	              s+="L:-1,";
	            }
			if(front.right!=null){
				s+="R:"+front.right.data;
				pendingNodes.enqueue(front.right);
			}
			else{
	            s+="R:-1";
	          }
			System.out.println(s);
		}
		
	}
	
	public static int diameter(BinaryTreeNode<Integer> root){
		if(root == null){
			return 0;
		}
		
		int option1 = height(root.left)+height(root.right);
		int option2= diameter(root.left);
		int option3 = diameter(root.right);
		
		return Math.max(option1, Math.max(option2,option3));
	}
	
	public static int height(BinaryTreeNode<Integer> root){
		if(root==null){
			return 0;
		}
		
		int lh = height(root.left);
		int rh = height(root.right);
		return 1 + Math.max(lh, rh);
	}
	
	public static Pair<Integer,Integer> heightDiameter(BinaryTreeNode<Integer> root){
		if(root==null){
			Pair<Integer,Integer> output = new Pair<>();
			output.height = 0;
			output.diameter = 0;
			return output;
			
		}
		
		Pair<Integer,Integer> lo = heightDiameter(root.left);
		Pair<Integer,Integer> ro = heightDiameter(root.right);
		int height = 1+ lo.height+ro.height;
		int option1 = lo.height +ro.height;
		int option2 = lo.diameter;
		int option3 = lo.diameter;
		int diameter = Math.max(option1, Math.max(option2, option3));
		
		Pair<Integer,Integer> output = new Pair<>();
		output.height = height;
		output.diameter = diameter;
		return output;
		
	}
	
	public static void inorder(BinaryTreeNode<Integer> root){
		if(root==null){
			return;
		}
		inorder(root.left);
		System.out.println(root.data);
		inorder(root.right);
	}
	
public static boolean checkBalanced(BinaryTreeNode<Integer> root){
		
		// Write your code here
      if(root==null){
        return true;
      }
      boolean left = checkBalanced(root.left);
      boolean right = checkBalanced(root.right);
      if(left==false||right==false){
        return false;
      }
      int rHeight = depth(root.right);
      int lHeight = depth(root.left);
     
      
      if(rHeight-lHeight>1||lHeight-rHeight>1){
        return false;
      }
		return true;
	}
  
  	public static int depth(BinaryTreeNode<Integer> root){
      if(root==null){
        return 0;
      }
      
      int countL = depth(root.left);
      int countR = depth(root.right);
      
      return Math.max(countL,countR) + 1;
      
    }
  	
	public static ArrayList<Node<BinaryTreeNode<Integer>>> LLForEachLevel(BinaryTreeNode<Integer> root) {
		
		// Write your code here
      QueueUsingLL<BinaryTreeNode<Integer>> pendingNodes = new QueueUsingLL<>();
      BinaryTreeNode<Integer> brk = new BinaryTreeNode<>(Integer.MIN_VALUE);
      pendingNodes.enqueue(root);
      pendingNodes.enqueue(brk);
      Node<BinaryTreeNode<Integer>> head = null;
      Node<BinaryTreeNode<Integer>> temp = null;
      Node<BinaryTreeNode<Integer>> prev = null;


      ArrayList<Node<BinaryTreeNode<Integer>>> list = new ArrayList<>();
      while(pendingNodes.size()>1){
        BinaryTreeNode<Integer> front = null;
        try{
        front = pendingNodes.dequeue(); 
        }
        catch(Exception e){
          
        }
        if(front==brk){
          list.add(head);
          pendingNodes.enqueue(brk);
          prev = null;
          head = null;
        }
        else{
          if(head==null){
            head = new Node<>(front);
          }
          else{
            temp = new Node<>(front);
            if(prev==null){
              prev = head;
            }
            prev.next = temp;
            prev = temp;
          }
          if(front.left!=null)
          pendingNodes.enqueue(front.left);
          if(front.right!=null)
          pendingNodes.enqueue(front.right);
        }
        
      }
      return list;

	}
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		BinaryTreeNode<Integer> root = takeInputLevelWise();
		BinarySearchTree BST = new BinarySearchTree();
		BST.root = root;
		System.out.println(BST.search(4));
		BST.deleteData(5);
		BST.printTree();
		BST.insertData(8);
		System.out.println("");
		BST.printTree();
		//System.out.println(BST.minNumInTree(root.right));
		//System.out.println("Diameter = "+diameter(root));
		//System.out.println(checkBalanced(root));
		//ArrayList<Node<BinaryTreeNode<Integer>>> sample = LLForEachLevel(root);
		
//		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
//		BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(2);
//		BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(3);
//		root.left= node1;
//		root.right = node2;
		
		System.out.println("");
	}
}

class Helper{
	boolean ans;
	int max;
	int min;
}
