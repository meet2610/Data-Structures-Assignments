
public class DequeADT<E> implements Deque<E> {
  DLLNode<E> header;
  DLLNode<E> trailer;
  int size;
  
  public DequeADT() {
   this.header = new DLLNode<E>();
   this.trailer = new DLLNode<E>();
   this.header.setNext(trailer);
   this.trailer.setPrev(header);
  }
  
  public boolean isEmpty(){
   return (this.size==0); 
  }
  
  public int size() {
   return this.size; 
  }
  
  public void addFirst(E item) {
   DLLNode<E> second = this.header.getNext();
   DLLNode<E> first = new DLLNode<E>(item, this.header, second);
   this.header.setNext(first);
   second.setPrev(first);
   size++;   
  }
  
  public void addLast(E item) {
   DLLNode<E> secondLast = this.trailer.getPrev();
   DLLNode<E> last = new DLLNode<E>(item, secondLast, this.trailer);
   this.trailer.setPrev(last);
   secondLast.setNext(last);
   this.size++;   
  }
  
  public E removeFirst() {
   DLLNode<E> second = this.header.getNext().getNext();
   DLLNode<E> first = this.header.getNext();
   this.header.setNext(second);
   second.setPrev(this.header);
   this.size--;
   return (first.getData());
  }
  
  public E removeLast() {
   DLLNode<E> secondLast = this.trailer.getPrev().getPrev();
   DLLNode<E> last = this.trailer.getPrev();
   this.trailer.setPrev(secondLast);
   secondLast.setNext(this.trailer);
   this.size--;
   return (last.getData());
  }
  
  public E first() {
    return (this.header.getNext().getData());
  }
  
  public E last() {
    return (this.trailer.getPrev().getData());
  }
 
  public void printOutContent() {
    DLLNode<E> first = this.header.getNext();
    while(first.getData() != null)
    {
      System.out.println(first.getData());
      first = first.getNext();
    }
  }

}