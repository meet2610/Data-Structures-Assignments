/* MaxHeap.java code */
import java.util.*;

class MaxHeap  {
   private Node[] heapArray;
   private int maxSize;           // size of array
   private int currentSize;       // number of nodes in array
   
   
// constructor -------------------------------------------------------------
   public MaxHeap(int mx) {
      maxSize = mx;
      currentSize = 0;
      heapArray = new Node[maxSize];  // create array
      }
   
   
// check if heap is empty --------------------------------------------------
   public boolean isEmpty()  {
      return currentSize==0; }
   
   
// insert a new element into heap ------------------------------------------
   public boolean insert(int key)  {
      if(currentSize==maxSize)
         return false;
      Node newNode = new Node(key);
      heapArray[currentSize] = newNode;
      heapUp(currentSize++);
      return true;
      }
   
   
// perform bubble-up procedure ----------------------------------------------
// @param index is the 'array location' of the heap-node which should be
// bubbled-up if heap property is not satisifed
   public void heapUp(int index)  {
     int parent = (index-1) / 2;
      Node bottom = heapArray[index];

      while( index > 0 && heapArray[parent].getKey() < bottom.getKey() )
      {
         heapArray[index] = heapArray[parent]; 
         index = parent;
         parent = (parent-1) / 2;
      }  
      heapArray[index] = bottom;
    } 
   
   
// delete item with max key (assumes non-empty heap) -------------------------
   public Node remove()  {        
      Node root = heapArray[0];
      heapArray[0] = heapArray[--currentSize];
      heapDown(0);
      return root;
      }
   
   
// perform bubble-down procedure ---------------------------------------------
// @param index is the 'array location' of the heap-node which should be
// bubbled-down if heap property is not satisifed
   public void heapDown(int index)  {
     int largerChild;
      Node top = heapArray[index];      
      while(index < currentSize/2){       
                                     
         int leftChild = 2*index+1;
         int rightChild = leftChild+1;
                                         
         if(rightChild < currentSize && heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
            largerChild = rightChild;
         else
            largerChild = leftChild;
                                         
         if(top.getKey() >= heapArray[largerChild].getKey())
            break;
                                        
         heapArray[index] = heapArray[largerChild];
         index = largerChild;            
      }  
      heapArray[index] = top;
      }
   
   
// display heap --------------------------------------------------------------
   public void displayHeap()
      {
      System.out.print("heapArray: ");    // array format
      for(int m=0; m<currentSize; m++)
         if(heapArray[m] != null)
            System.out.print( heapArray[m].getKey() + " ");
         else
            System.out.print( "-- ");
      System.out.println();
                                          // heap format
      int nBlanks = 32;
      int itemsPerRow = 1;
      int column = 0;
      int j = 0;                          // current item
      String dots = "...............................";
      System.out.println(dots+dots);      // dotted top line

      while(currentSize > 0)              // for each heap item
         {
         if(column == 0)                  // first item in row?
            for(int k=0; k<nBlanks; k++)  // preceding blanks
               System.out.print(' ');
                                          // display item
         System.out.print(heapArray[j].getKey());

         if(++j == currentSize)           // done?
            break;

         if(++column==itemsPerRow)        // end of row?
            {
            nBlanks /= 2;                 // half the blanks
            itemsPerRow *= 2;             // twice the items
            column = 0;                   // start over on
            System.out.println();         //    new row
            }
         else                             // next item on row
            for(int k=0; k<nBlanks*2-2; k++)
               System.out.print(' ');     // interim blanks
         }  // end for
      System.out.println("\n"+dots+dots); // dotted bottom line
      }  // end displayHeap()

   
// print heap sorted -------------------------------------------
// return a string of heap elements arranged as a sorted sequence
// IMPORTANT: be careful not to permanently 'destroy' the heap when
// printing out its elements
   public String printHeapSorted() {
     int[] keys = new int[currentSize];
     String s = "Sorted heap content:";
     for(int k=0; k<currentSize; k++){
      keys[k] = heapArray[k].getKey(); 
     }
     Arrays.sort(keys);
     for(int j=currentSize-1; j>=0; j--) {
       s = s + " " + keys[j];
     }
   
     return s;
   }
   
// -------------------------------------------------------------
}  // end class Heap