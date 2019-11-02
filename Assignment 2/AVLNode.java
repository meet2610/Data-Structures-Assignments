/* AVLNode.java code*/
public class AVLNode  {
    /** The key stored in the node. */
    public int key;
    
    /** The data stored in the node. */
    public String data;
    
    /** The node's parent. */
    public AVLNode parent;
     
    /** The node's balanceFactor. */
    public int bf;
    
    /** The node's left child. */
    public AVLNode left;
    
    /** The node's right child. */
    public AVLNode right;
    
    public int height;
    
    /**
     * Node constructor that initializes the values of key and the data and
     * makes other pointers null. The Balance Factor is initialized to be 0. 
     */
    public AVLNode(int key, String data)  {
      this.key = key;
      this.data = data;
      this.parent = null;
      this.left = null;
      this.right = null;
      this.bf = 0;
      this.height = 0;
    }
    public void updateBF() {
      int rightHeight = 0;
      int leftHeight = 0;
      if(this.right != null) {
        rightHeight = this.right.height+1; 
      }
      if(this.left != null) {
        leftHeight = this.left.height +1; 
      }
      
      this.bf =  rightHeight - leftHeight;
    }
    /**
     * Returns the node content as a string of the following form of: "key, data(bf)".
     */
    public String printNode()  {
      if (this == null)
        return "x";
      else
        return key + "," + data + "(" + bf + ")" + "(" + height + ")";
    }
  }