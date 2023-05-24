package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric<T> implements ListGeneric<T> {
	
	public NodeGeneric<T> head = null;
	private NodeGeneric<T> tail = null;

	public DSEListGeneric() {
		
	}
	public DSEListGeneric(NodeGeneric<T> head) {
		this.head = head;
		NodeGeneric<T> current = this.head;
		
		while (current.next != null) {
			current = current.next;
		}
		
		this.tail = current;
	}
	
	//Takes a list then adds each element into a new list
	public DSEListGeneric(DSEListGeneric<T> other) { // Copy constructor.
		NodeGeneric<T> current = other.head;
		
		for (int i = 0; i < other.size(); i++) {
			this.add(current.get());
			current = current.next;
		}
	}

	//remove and return the item at the parameter's index
	public T remove(int index) throws IndexOutOfBoundsException{
		if (index >= 0 && index < this.size()) {
			
			NodeGeneric<T> current = this.head;
			NodeGeneric<T> before = null;
			NodeGeneric<T> after = null;
			
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			
			T t = current.get();
		
			before = current.prev;
			after = current.next;
			
			if (before != null ) {
				before.next = after;
			}
			else {
				this.head = after;
			}
			
			if (after != null) {
				after.prev = before;
			}
			else {
				this.tail = before;
			}
			
			return t;
			
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	//returns the index of the String parameter 
	public int indexOf(T obj) {
		NodeGeneric<T> current = this.head;
		
		for (int i = 0; i < this.size(); i++) {
			if (current.get().equals(obj)) {
				return i;
			}
			else {
				current = current.next;
			}
		}
		
		return -1;
	}
	
	//returns item at parameter's index
	public T get(int index) {
		if (index < this.size() && index >= 0) {
			int count = 0;
			NodeGeneric<T> current = this.head;
			
			while (count < index) {
				current = current.next;
				count ++;
			}
			
			return current.get();
						
		}
		else {
			return null;
		}
	}

	//checks if there is a list
	public boolean isEmpty() {
		if (this.head == null) {
			return true;
		}
		else {
			return false;
		}
	}

	//return the size of the list
	public int size() {
		int count = 0;
		NodeGeneric<T> current = null;
		
		if (!this.isEmpty()) {
			current = this.head;
			count++; 
			
			while (current.next != null) {
				current = current.next;
				count++;
			}
		}
		
		return count;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		String concat = "";
		NodeGeneric<T> current = this.head;
		
		for (int i = 0; i < this.size(); i++) {
			concat += current.get();
			if (current != this.tail) { // might have to change to equals method
				concat += " ";
			}
			
			current = current.next;
		}
		
		return concat;
	}

	//add the parameter item at of the end of the list
	public boolean add(T obj) throws NullPointerException {
		if (obj != null) {
			NodeGeneric<T> n = new NodeGeneric<T>((NodeGeneric<T>)null, this.tail, obj);
			
			if (this.isEmpty()) {
				this.head = n;
				this.tail = n;
			}
			else {
				this.tail.next = n;
				this.tail = n;
			}
			
			return true;
		}
		else {
			throw new NullPointerException();
		}
	}

	//add item at parameter's index
	public boolean add(int index, T obj) throws NullPointerException, IndexOutOfBoundsException {
		if (obj != null) {
			if (index >= 0 && index <= this.size()) {
				NodeGeneric<T> before = null;
				NodeGeneric<T> after = null;
				
				if (index != 0) {
					before = this.head;
					
					for (int i = 0; i < (index -1); i++) {
						before = before.next;
					}
					
					after = before.next;
				}
				else {
					after = this.head;
				}
				
				NodeGeneric<T> n = new NodeGeneric<T>(after, before, obj);
				
				if (before != null ) {
					before.next = n;
				}
				else {
					this.head = n;
				}
				
				if (after != null) {
					after.prev = n;
				}
				else {
					this.tail = n;
				}
				
				return true;
			
			}	
			else {
				throw new IndexOutOfBoundsException();
			}
			
		}
		else {
			throw new NullPointerException();
		}
	}

	//searches list for parameter's String return true if found
	public boolean contains(T obj) throws NullPointerException {
		if (obj != null) {
			NodeGeneric<T> current = this.head;
			
			for (int i = 0; i < this.size(); i++) {
				if (current.get().equals(obj)) {
					return true;
				}
				else {
					current = current.next;
				}
			}
			
			return false;			
		}
		else {
			throw new NullPointerException();
		}
	}

	//removes the parameter's item form the list
	public boolean remove(T obj) throws NullPointerException {
		if (obj != null) {
			
			NodeGeneric<T> current = this.head;
			NodeGeneric<T> before = null;
			NodeGeneric<T> after = null;
			
			for (int i = 0; i < this.size(); i++) {
				if (current.get().equals(obj)) {
					before = current.prev;
					after = current.next;
					
					if (before != null ) {
						before.next = after;
					}
					else {
						this.head = after;
					}
					
					if (after != null) {
						after.prev = before;
					}
					else {
						this.tail = before;
					}
					
					return true;
				}
				else {
					current = current.next;
				}
			}
			
			return false;			
		}
		else {
			throw new NullPointerException();
		}
	}
	
	@Override
	public int hashCode() {
		NodeGeneric<T> current = this.head;
		int hash = 0;
		
		for (int i = 0; i < this.size(); i++) {
			hash += current.hashCode();
			current = current.next;
		}
		
		return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (this.size() == ((DSEListGeneric<T>)other).size()) {
			NodeGeneric<T> thisCurrent = this.head;
			NodeGeneric<T> otherCurrent = ((DSEListGeneric<T>)other).head;

			for (int i = 0; i < this.size(); i++) {
				if (thisCurrent.equals(otherCurrent)) {
					thisCurrent = thisCurrent.next;
					otherCurrent = otherCurrent.next;
				}
				else {
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
}
