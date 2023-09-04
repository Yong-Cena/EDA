
package ordenamientos;

public interface StackADT<T> {

    /**  Adds one element to the top of this stack. 
    */
    public void push (T element);

    /**  Removes and returns the top element from this stack. 
    */
    public T pop();

    /**  Returns without removing the top element of this stack. 
    */
    public T peek();

    /**  Returns true if this stack contains no elements. 
    */
    public boolean isEmpty();

    /**  Returns the number of elements in this stack. 
    */
    public int size();

    /**  Returns a string representation of this stack.
     *   Just for help.
    */
    public String toString();
    
    // MORE
    /**  Returns the maximum length of this stack implementation. 
    */
    public int length();    
    
    /**  Removes npops elements from stack, if possible, and returns a boolean
     * showing if the total remotion was done from this stack. 
    */    
    public boolean multiPop(int npops);    

    /**  Mixes two stacks, aggregating the elements of otherStack into
     * the local stack (this). The otraPila remains with the same elements.
    */    
    public  int  mezcla2Pilas(StackADT<T> otraPila);
}
