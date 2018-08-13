package Tries;

class TrieNode{
	char data;
	boolean isTerminating;
	TrieNode[] children;
	int childCount;
	
	TrieNode(char data){
		this.data= data;
		isTerminating = false;
		children = new TrieNode[26];
		childCount = 0;
	}
	
}

public class Trie {
	TrieNode root;
	
	public Trie(){
		root = new TrieNode('\0');
		
	}
	
	private void add(TrieNode root, String word){
		if(word.length()==0){
			root.isTerminating =true;
			return;
		}
		int childIndex = word.charAt(0) - 'a';
		TrieNode child = root.children[childIndex];
		if(child==null){
			child = new TrieNode(word.charAt(0));
			root.children[childIndex] = child;
			root.childCount++;
		}
		
		add(child,word.substring(1));
		
	}
	
	public void add(String word){
		add(root,word);
		
	}
	
	public boolean search(String word){
		// add your code here
      return search(word,root);
	}
  
  	private boolean search(String word, TrieNode root){
      if(word.length()==0){
        return root.isTerminating;
      }
      
      int index = word.charAt(0) - 'a';
      if(root.children[index]!= null){
        boolean ans = search(word.substring(1),root.children[index]);
        return ans;
      }
      else{
        return false;
      }
    }
  	public void remove(String word){
  		remove(root,word);
  		
  	}
  	
  	private void remove(TrieNode root, String word){
  		if(word.length()==0){
  			root.isTerminating = false;
  			return;
  		}
  		
  		int childIndex = word.charAt(0)- 'a';
  		TrieNode child = root.children[childIndex];
  		if(child==null){
  			return;
  		}
  		remove(child,word.substring(1));
  		//Remove child node only if non terminating and no. of children is 0
  		if(!child.isTerminating&&child.childCount==0){
  			root.children[childIndex] = null;
  			child = null;
  			root.childCount--;
  		
//  		if(!child.isTerminating){		
//  			int numChild = 0;
//  			for(int i =0;i<26;i++){
//  				if(child.children[i]!=null){
//  					numChild++;
//  				}
//  			}
//  			if(numChild == 0){
//  				root.children[childIndex] = null;
//  				child = null;
//  				rootChild 
//  			}
  			
  		}
  	}

}
