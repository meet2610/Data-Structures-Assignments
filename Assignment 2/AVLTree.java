/* AVLTree.java code */
import java.util.*;
public class AVLTree  {
  
  /** Root of the AVL tree. */
  protected AVLNode root;
 
  /**
   * AVLTree constructor that initializes the underlying binary
   * searc tree with just a null, which is the root.
   */
  public AVLTree()  {
    root = null;
    
  }
  
  /**
   * Searches the tree for a node with a given key. If such a node exists,
   * prints the value of that node. 
   */
  public String search(int k)  {
    AVLNode node = search(root,k);
    System.out.println(" " + node.printNode());
    return node.data;
  }

  /**
   * Searches the subtree rooted at a given node for a node with a given key.  
   * An exception NoSuchElementException is thrown if no node exists.
   */
  protected AVLNode search(AVLNode node, int k)   {
    while ((node != null) && (k != node.key)) {
            if (k < node.key)
                node = node.left;
            else
                node = node.right;
     }

     if (node != null){
       return node; }
     else {
       throw new NoSuchElementException("Not Found"); }
  }

  /**
   * Returns the node with the minimum key in the subtree rooted at
   * a node.
   */
  protected AVLNode treeMinimum(AVLNode x)  {
    while (x.left != null)
      x = x.left;
    
    return x;
  }
  
  /**
   * Returns the successor of a given node in an inorder walk of the
   * tree.
   */
  protected AVLNode successor(AVLNode node)  {
    AVLNode x = node;
    
    if (x.right != null)
      return treeMinimum(x.right);
    
    AVLNode y = x.parent;
    while (y != null && x == y.right) {
      x = y;
      y = y.parent;
    }
    
    return y;
  }
  
/**
   * Performs single left rotation.
   */ 
   protected void singleLeftRotation(AVLNode a)   {
     // TO DO : Implement rotation;
     //   1
     // 2    3
     //4 5  6 7            a is 1
     //      8 9 
     AVLNode b = a.right; // b is 3
        if(a.parent == null)
           this.root = b; //root is 3
        
        b.parent = a.parent; // 3 parent is null
 
        a.right = b.left;    // 1 right is 6
 
        if (a.right != null)
            a.right.parent = a; // 6 parent is 1
 
        b.left = a;  // 3 left is 1
        a.parent = b; // 1 parent is 3
 
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b; 
            } else {
                b.parent.left = b;
            }
        }
        if(a!=null) {
        a.updateBF();
        a.height = getHeight(a);
        }
        if(b!=null) {
        b.updateBF();
        b.height = getHeight(b);
        }
   }
   
   /**
   * Performs single right rotation.
   */ 
   protected void singleRightRotation(AVLNode a)   {
     // TO DO : Implement rotation;   
     AVLNode b = a.left;
        b.parent = a.parent;
 
        a.left = b.right;
 
        if (a.left != null)
            a.left.parent = a;
 
        b.right = a;
        a.parent = b;
 
        if (b.parent != null) {
          
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
        if(a!=null) {
        a.updateBF();
        a.height = getHeight(a);
        }
        if(b!=null) {
        b.updateBF();
        b.height = getHeight(b);
        }
   }
   
   
   /**
   * Performs double right left rotation.
   */ 
   protected void doubleRightLeftRotation(AVLNode A)   {
         AVLNode aLeft = A.left;
         AVLNode aLeftRight = A.left.right;
         
         aLeft.parent = A.parent;
         aLeft.right = A;
         aLeft.height += 1;
         
         aLeft.parent.right = aLeft;
         
         A.left = aLeftRight;
         A.parent = aLeft;
         A.height -= 1;
         
         if (aLeftRight != null) {
           aLeftRight = A;
         }
         
   }
   
   /**
   * Performs double left right rotation.
   */ 
   protected void doubleLeftRightRotation(AVLNode A)   {
         AVLNode aRight = A.right;
         AVLNode aRightLeft = A.right.left;
         
         aRight.parent = A.parent;
         aRight.left = A;
         aRight.height += 1;
         
         aRight.parent.left = aRight;
         
         A.right = aRightLeft;
         A.parent = aRight;
         A.height -= 1;
        
         
         if (aRightLeft != null) {
           aRightLeft.parent = A;
         }
   }
    
   /** Balances a right-heavy or left-heavy unbalanced tree.
    * This function should check for the nature of the imbalance
    * and call the appropriate rotation functions. In addition, after
    * rotations are performed,
    * it should update the balance factors of the rotated nodes accordingly. 
    */
    protected AVLNode balance(AVLNode A)   {
       while(A.parent != null) {
       A.parent.height = getHeight(A.parent);
       A.updateBF();
       A.parent.updateBF();
       
       if(A.parent.bf > 1 || A.parent.bf < -1) {
                  
       if(A.parent.bf > 1) {
         if(A.bf >= 1) {
          singleLeftRotation(A.parent);
          continue;
         }
         else if (A.bf <= -1) {
          doubleRightLeftRotation(A); 
         }
         else if(A.parent.parent == null) {
          singleLeftRotation(A.parent); 
         }
        }
       else {
         if(A.bf >= 1) {
          doubleLeftRightRotation(A); 
         }
         else if (A.bf <= -1) {
           singleRightRotation(A.parent);
           continue;
         }
         else if(A.parent.parent == null) {
          singleRightRotation(A.parent); 
         }
       }
       } 
       A = A.parent;      
     
     }
          
     return A;
            
   }
 
   
  /**
   * Inserts a key and a data item into the tree, creating a new 
   * node for this key and data pair.
   */
  public void insert(int key, String data)  {
    AVLNode z = new AVLNode(key, data);
    treeInsert(z);
  }
  
  /**
   * Inserts a node into the tree.
   */
  protected void treeInsert(AVLNode z)  {
   AVLNode parent = null;
   AVLNode current = root;

  while (current != null) {
   parent = current;
   if (z.key <= current.key)
    current = current.left;
   else
    current = current.right;
  } // while

  z.parent = parent;
  if (parent == null) {
   root = z; // the tree had been empty
  } else {
   if (z.key <= parent.key)
    parent.left = z;
   else
    parent.right = z;

   root = balance(z);
  }// else
  }

  /**
   * Removes a node with the given key from the tree.
   * It is assumed that there is at most one node with the given
   * key present in the tree.
   */
  public void deleteKey(int k)  {
    AVLNode node = (AVLNode) search(root, k);
    if (node != null)
      delete(node);
    else
      throw new NoSuchElementException("Not Found");
  }

  /**
   * Removes a node from the tree.
   */
  protected void delete(AVLNode node)  {
    AVLNode deleteNode = node;
    AVLNode replaceNode = null;
    AVLNode replaceNodeParent = null;
    boolean leftNode = false;
    boolean rightNode = false;
    boolean leftRightNode = false;
    boolean rightLeftNode = false;
    if(node == null)  // checks if node is null
       throw new NoSuchElementException("empty node");
    else {
    if(node.left != null) { // gets lower value, meaning if node key is 12, it will find node key 11
      replaceNode = node.left;
      leftNode = true;
      while(replaceNode.right != null) {
       replaceNode = replaceNode.right;
       leftNode = false;
       leftRightNode = true;
      }
    }
    else if (node.right != null) {  // gets higher value, meaning if node key is 12, it will find node key 13
      replaceNode = node.right;
      rightNode = true;
      while(replaceNode.left != null) {
       replaceNode = replaceNode.left;
       rightLeftNode = true;
      }
    }
    else {  // node is leaf
      replaceNodeParent = node.parent;
      if(node.parent != null) {
      if(node.parent.left == node)
        node.parent.left = null;
      else if(node.parent.right == node)
        node.parent.right = null;
        node.parent = null;
      }
    }
     if(leftNode) {
     replaceNode.right = deleteNode.right;
     replaceNode.parent = deleteNode.parent;
    }
    else if(rightNode) {
     replaceNode.left = deleteNode.left;
     replaceNode.parent = deleteNode.parent;
    }
    else if(rightLeftNode || leftRightNode){
     replaceNodeParent = replaceNode.parent;
     if(replaceNodeParent != null) {
     if(replaceNodeParent.left == replaceNode)
       replaceNodeParent.left = null;
     else
       replaceNodeParent.right = null;
     }
     replaceNode.left = deleteNode.left;
     replaceNode.right = deleteNode.right;
     replaceNode.parent = deleteNode.parent;
    }
    
    if(replaceNode.parent != null){
     if(replaceNode.parent.left == deleteNode)
       replaceNode.parent.left = replaceNode;
     else if(replaceNode.parent.right == deleteNode)
       replaceNode.parent.right = replaceNode;
    }
    
    if(replaceNodeParent != null){
      replaceNodeParent.height = getHeight(replaceNodeParent);
      replaceNodeParent.updateBF();
      System.out.println(replaceNodeParent.printNode());
      
    }
        
    if(replaceNode != null) {
    if(replaceNode.left != null) 
      replaceNode.left.parent = replaceNode;
    if(replaceNode.right != null) 
      replaceNode.right.parent = replaceNode;
      if(replaceNode.parent != null){
        replaceNode.parent.height = getHeight(replaceNode.parent);
        replaceNode.parent.updateBF();
        
      }
      replaceNode.height = getHeight(replaceNode);
      replaceNode.updateBF();
      root = balance(replaceNode);
    }
    if(replaceNodeParent != null) {
      if(replaceNodeParent.bf < -1)
       singleRightRotation(replaceNodeParent); 
      else if(replaceNodeParent.bf > 1)
        singleLeftRotation(replaceNodeParent);
      root = balance(replaceNodeParent);
    }
    
}
}
  
  public int getHeight(AVLNode node) {
  int leftHeight = -1;
  int rightHeight = -1;
  // node is currently the root
  if (node.left != null)
   leftHeight = node.left.height;
  if (node.right != null)
   rightHeight = node.right.height;

  return 1 + Math.max(leftHeight, rightHeight);
 }


  /**
   * Print the whole tree.
   */
  public void print() {
    printHelper(root,0);
    System.out.print("\n");
  }
  
  /**
   * Print the tree rooted at <code>root</code>, with indent preceding 
   * all output lines.
   * The tree is printed rotated by 90 degrees.  
   * If there is no node at a given position of the tree, the 
   * character 'x' is printed. 
   *
   * WARNING: DO NOT MODIFY THIS METHOD!!!
   */
  protected static void printHelper(AVLNode n, int indent) {
    
    String blanks="";
    for (int i=0;i<indent;++i) blanks=blanks+" ";
    
    if (n == null) {
      System.out.println(blanks+"x");
      return;
    }
    
    
    printHelper(n.right, indent+n.printNode().length());
    System.out.println(blanks+n.printNode());
    printHelper(n.left, indent+n.printNode().length());
   
  }
  
  /**
   * Print the tree rooted at <code>root</code>, with indent preceding 
   * all output lines.
   * The tree is printed rotated by 90 degrees. Bars ("|")are 
   * added to indicate the parent node edges. It is memory-intensive,
   * so for large trees the integer version is recommended. 
   * If there is no node at a given position of the tree, the 
   * character 'x' is printed. 
   *
   * WARNING: DO NOT MODIFY THIS METHOD!!!
   */
  private static void printHelper(AVLNode n, String indent) {
    
   
    if (n == null) {
      System.out.println(indent+"x");
      return;
    }
   
    String blanks="";
    for (int i=0;i<n.printNode().length();++i) blanks=blanks+" ";
    String barandblanks="|";
    for (int i=0;i<n.printNode().length()-1;++i) barandblanks = barandblanks+" ";

    if (n.parent == null) //root
    {
      printHelper(n.right, indent+blanks);
      System.out.println(indent+"*"+n.printNode());
      printHelper(n.left, indent+blanks);
      return;
    } 
    
    if (n.parent.left == n)
    {
      printHelper(n.right, indent+barandblanks);
      System.out.println(indent+"|"+n.printNode());
      printHelper(n.left, indent+blanks);
    } else
    {
      printHelper(n.right, indent+blanks);
      System.out.println(indent+"|"+n.printNode());
      printHelper(n.left, indent+barandblanks);
    }
  }

  
 /**
  * Prints out the content of the tree in an inorder traversal manner.
  */
  public String inOrder() {
    return ("Inorder-traversal content of the tree:" + inOrder(root));
 }
   
  
 /**
  * Collects the content of the tree by performing an inorder traversal walk.
  */
  private String inOrder(AVLNode n) {
     if (n == null) return "";
     String returnString = inOrder(n.left);
     returnString += " { " + n.key + " -> " + n.data + " }";
     return returnString + inOrder(n.right); 
  }
  
}