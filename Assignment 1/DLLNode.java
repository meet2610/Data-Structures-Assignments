public class DLLNode<E> {
 E data;
 DLLNode<E> next;
 DLLNode<E> prev;
  public DLLNode()
 {
 }
  public DLLNode(E data, DLLNode<E> prev, DLLNode<E> next)
 {
    this.data = data;
    this.next = next;
    this.prev = prev;
 }
  public void setNext(DLLNode<E> n)
  {
    this.next = n;
  }
  public void setPrev(DLLNode<E> n)
  {
    this.prev = n;
  }
  public void setInfo(E i)
  {
    this.data = i; 
  }
  public DLLNode<E> getNext()
  {
    return this.next;
  }
  public DLLNode<E> getPrev()
  {
    return this.prev;
  }
  public E getData()
  {
    return this.data;
  }
}