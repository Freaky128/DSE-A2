package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.List;

/**
 * @author simont
 *
 */
public class DSEList implements List {
	
	public Node head = null;
	private Node tail = null;

	public DSEList() {}
	
	public DSEList(Node head) {
		this.head = head;
		Node current = this.head;
		
		while (current.next != null) {
			current = current.next;
		}
		
		this.tail = current;
		
	}
	
	//Takes a list then adds each element into a new list
	public DSEList(DSEList other) { // Copy constructor. 
	}

	//remove the String at the parameter's index
	public String remove(int index) throws IndexOutOfBoundsException{
		if (index >= 0 && index < this.size()) {
			
			Node current = this.head;
			Node before = null;
			Node after = null;
			
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			
			String token = current.getString();
		
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
			
			return token;
			
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		
	}
	
	//returns String at parameter's index
	public String get(int index) {
		if (index < this.size() && index >= 0) {
			int count = 0;
			Node current = this.head;
			
			while (count < index) {
				current = current.next;
				count ++;
			}
			
			return current.getString();
						
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
		Node current = null;
		
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
		Node current = this.head;
		
		for (int i = 0; i < this.size(); i++) {
			concat += current.getString();
			if (current != this.tail) { // might have to change to equals method
				concat += " ";
			}
			
			current = current.next;
		}
		
		return concat;
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) throws NullPointerException{
		if (obj != null) {
			Node n = new Node(null, this.tail, obj);
			
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

	//add String at parameter's index
	public boolean add(int index, String obj) throws NullPointerException, IndexOutOfBoundsException{
		if (obj != null) {
			if (index >= 0 && index <= this.size()) {
				Node before = null;
				Node after = null;
				
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
				
				Node n = new Node(after, before, obj);
				
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
	public boolean contains(String obj) throws NullPointerException {
		if (obj != null) {
			Node current = this.head;
			
			for (int i = 0; i < this.size(); i++) {
				if (current.getString().equals(obj)) {
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

	//removes the parameter's String form the list
	public boolean remove(String obj) throws NullPointerException{
		if (obj != null) {
			
			Node current = this.head;
			Node before = null;
			Node after = null;
			
			for (int i = 0; i < this.size(); i++) {
				if (current.getString().equals(obj)) {
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
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		return true;
	}
	
	public void dPrint() {
		System.out.println("Head node: " + this.head + " node text: " + this.head.getString() + " previous: " + this.head.prev + " next: " + this.head.next);
		System.out.println("Tail node: " + this.tail + " node text: " + this.tail.getString() + " previous: " + this.tail.prev + " next: " + this.tail.next);
		
		Node current = this.head;
		for (int i = 0; i < this.size(); i++) {
			System.out.println("node: " + current + " node text: " + current.getString() + " previous: " + current.prev + " next: " + current.next);
			current = current.next;
		}
		System.out.println();
	}
	
}
