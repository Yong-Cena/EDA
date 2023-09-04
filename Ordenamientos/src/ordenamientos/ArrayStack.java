/**
 *  Represents an array implementation of a stack.
 */

package ordenamientos;

public class ArrayStack<T> implements StackADT<T> {
    // Constant to represent the default capacity of the array.
    private final int DEFAULT_CAPACITY = 100;

    // top represents both the number of elements and the next
    // available position in the array.
    private int top;

    // Array of generic elements to represent the stack.
    private T[] stack;

    // Creates an empty stack using the default capacity.
    public ArrayStack() {
        top = 0;
        stack = (T[])(new Object[DEFAULT_CAPACITY]);
    }

    // Creates an empty stack using the specified capacity.
    public ArrayStack (int initialCapacity) {
        top = 0;
        stack = (T[])(new Object[initialCapacity]);
    }

    // Pushes in or adds the specified element to the top of this stack, expanding
    // the capacity of the stack array if necessary.
    public void push (T element) throws ADTsException {
        if( element==null ) 
            throw new ADTsException("Sets.ArraySet.contains(): element null.\n");
        
        if (size() == stack.length)
            expandCapacity();   //Si el stack está lleno.
        stack[top]= element;
        top++;  // next available free element of the ArrayStack
    }

    // Creates a new array to store the contents of this stack with
    // twice the capacity of the old one.
    private void expandCapacity() {
        T[] larger = (T[])(new Object[stack.length*2]);

        for (int index=0; index < stack.length; index++)
            larger[index] = stack[index];

            stack = larger;

        // Alternative code
        // import java.util.Arrays; after packege
        // stack = Arrays.copyOf(stack, stack.length * 2);
    }
    
    // Pops out or removes the element at the top of this stack and returns a
    // reference to it. Throws an ADTsException if the stack
    // is empty.
    public T pop() throws ADTsException {
        T result;    
        if (isEmpty())
            throw new ADTsException("Stacks.ArrayStack.pop(): Empty Stack.\n");

        top--;  // Points out to the top T element
        result = stack[top];
        stack[top]= null;
        return result;
    }

    // Peeks and returns a reference to the T element at the top of this stack.
    // The element is not removed from the stack.  Throws an
    // ADTsException if the stack is empty.
    public T peek() throws ADTsException {
        if (isEmpty())
            throw new ADTsException("Stacks.ArrayStack.peek(): Empty Stack.\n");

        return stack[top-1];
    }

    // isEmpty: returns true if this stack is empty and false otherwise.
    public boolean isEmpty() {
        return (top == 0);
    }

    // size: returns the count (top) of elements in the stack.
    public int size() {
        return top;
    }

    // Returns a string representation of this stack.
    // Just for help.
    @Override
    public String toString() {
        String result = "ArrayStack(toString) is EMPTY.";
        if( !isEmpty() ) {
            result = "STACK{BASE:" + top + "/" + stack.length + ":: ";
            for (int index = 0; index < top; index++)
                result = result + stack[index].toString() + ", ";
            result = result + ":TOP}";
        }        
        return result;
    }
    
    // MORE
    /**  Returns the maximum length of this stack implementation. 
    */
    public int length() {
        return stack.length;        
    }
    
    /**  Removes n elements, if possible, and returns a boolean showing if
     *  the total remotion was done from this stack. 
    */    
    public boolean multiPop(int npops) {        
        boolean b = false;
        if( ( npops > 0 ) && ( npops <= size() ) ) {
            for( int i=0; i<npops; i++) {
                pop();
            }
            b = true;
        }
        // npops <= 0, no hay que hacer pop()s;
        // npops > size(), no se hacen pop()s; 
        
        return b;        
    }

    
    /**  Mixes two stacks, aggregating the elements of otherStack into
     * the local stack (this). The otraPila remains with the same elements.
    */    
    public  int  mezcla2Pilas(StackADT<T> otraPila) throws ADTsException {
        if (otraPila == null)
            throw new ADTsException("mezcla2Pilas: otraPila, null.\n");
        
        ArrayStack<T> paux1 = new ArrayStack ( this.length() );
        ArrayStack<T> paux2 = new ArrayStack ( otraPila.length() );
        
        // De this >> paux1, invirtiendo el orden
        while( !this.isEmpty() ) {
            paux1.push( this.pop() );
        }

        // De otraPila >> paux2, invirtiendo el orden
        while( !otraPila.isEmpty() ) {
            paux2.push( otraPila.pop() );
        }
        
        int totElems, i;
        totElems = paux1.size() + paux2.size();
        i = 0;
        while( i < totElems-1 ) {
            if( !paux1.isEmpty() ) {
                this.push(  paux1.pop() );
            }
            if( !paux2.isEmpty() ) {
                this.push(  paux2.peek() );
                otraPila.push(  paux2.pop() );
            }
            i++;            
        }
              
        return totElems;
    }    

}
