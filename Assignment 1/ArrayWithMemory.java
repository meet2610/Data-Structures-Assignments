public class ArrayWithMemory<E> implements ReadWriteCount<E> {
public E anArray[];
public int readCount = 0;
public int writeCount = 0;

public ArrayWithMemory(int capacity) {
  this.anArray = (E[]) new Object[capacity];
}
public void write(int i, E e){
  this.writeCount++;
  this.anArray[i] = e;
}
public E read(int i) {
 this.readCount++;
 return this.anArray[i]; 
}

public int numberOfWrites() { 
 return this.writeCount;  
}
public int numberOfReads() {
 return this.readCount;
}
public void resetMemory() { 
 this.readCount = 0;
 this.writeCount = 0;
}
public void printOutContent() { 
 for(int i = 0; i < this.anArray.length; i++)
 {
   System.out.println(this.anArray[i]); 
 }
}
}