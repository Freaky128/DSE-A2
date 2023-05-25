package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.List;

/**
 * @author simont
 *
 */
public class DSEList implements List {
	
	public Node head = null; // Object at the start of the list
	private Node tail = null; // Object at the end of the list

	public DSEList() {}
	
	public DSEList(Node head) {
		this.head = head;
		Node current = this.head;
		
		while (current.next != null) { // Finds the last object in the list
			current = current.next;
		}
		
		this.tail = current; // Sets last object
		
	}
	
	//Takes a list then adds each element into a new list
	public DSEList(DSEList other) { // Copy constructor. 
		Node current = other.head;
		
		for (int i = 0; i < other.size(); i++) {
			this.add(current.getString()); // Creates a new node with the string from the node to be copied
			current = current.next;
		}
	}

	//remove the String at the parameter's index
	public String remove(int index) throws IndexOutOfBoundsException{
		if (index >= 0 && index < this.size()) { // Checks index is within bounds of the list
			
			Node current = this.head; // Object used to find the node to remove
			Node before = null; // Node preceding the node to be removed
			Node after = null; // Node following the node to be removed
			
			for (int i = 0; i < index; i++) { // Finds the node at the specified index
				current = current.next;
			}
			
			String token = current.getString();
		
			before = current.prev;
			after = current.next;
			
			if (before != null ) {   // If there was a node before the node being removed (the head isn't being removed), 
				before.next = after; // the before node's next attribute is set to the node after the node being removed.
			}						 // (This may be null if the tail is being removed)
			else {
				this.head = after; // If it was the head being removed a new head is assigned
			}
			
			if (after != null) {	 // If there is a node after the node being removed (the tail isn't being removed),
				after.prev = before; // the after node's prev attribute is set to the node before the node being removed.
			}						 // (This may be null if the head is being removed)
			else {
				this.tail = before; // If it was the tail being removed a new tail is assigned
			}
			
			return token; // returns string from the node that was removed
			
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		Node current = this.head;
		
		for (int i = 0; i < this.size(); i++) { // Cycles the list
			if (current.getString().equals(obj)) {
				return i; // If a match is found the index is returned
			}
			else {
				current = current.next; // If a match isn't found the next node is selected
			}
		}
		
		return -1;
	}
	
	//returns String at parameter's index
	public String get(int index) {
		if (index < this.size() && index >= 0) { // Checks if the index is in bounds
			Node current = this.head; // Node at the current index
			
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			
			return current.getString();
						
		}
		else {
			return null;
		}
	}

	//checks if there is a list
	public boolean isEmpty() {
		if (this.head == null) { // head will be null if there are no nodes
			return true;
		}
		else {
			return false;
		}
	}

	//return the size of the list
	public int size() {
		int count = 0;
		
		if (!this.isEmpty()) { // only begins the count if the list isn't empty
			Node current = this.head; // Node at the current index
			count++; // Add to the count to include the head node
			
			while (current.next != null) { // Continues count until there are no more nodes to come
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
		
		for (int i = 0; i < this.size(); i++) { // Cycles list
			concat += current.getString();
			if (current != this.tail) { // Adds a trailing space as long as the node isn't the tail
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
			
			if (this.isEmpty()) { // different assignment for if the list is empty
				this.head = n;
				this.tail = n;
			}
			else {
				this.tail.next = n; // Assigns the next attribute for the old tail
				this.tail = n; // Sets the new tail
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
			if (index >= 0 && index <= this.size()) { // Checks the given index is in bounds of the list
				Node before = null; // The node before the node to be inserted
				Node after = null; // The node after the node to  be inserted
				
				if (index != 0) { // Only begins the before assignment if the new node isn't to be inserted at the head
					before = this.head;
					
					for (int i = 0; i < (index -1); i++) { // Finds the node before the index the node will be inserted at
						before = before.next;
					}
					
					after = before.next; // Sets the node after the insertion index
				}
				else {
					after = this.head; // If the new node is to be inserted at the head after is set to the old head
				}
				
				Node n = new Node(after, before, obj); // Creates the node that will be inserted
				
				if (before != null ) { // If the node isn't being added to the head then the next attribute of the node,
					before.next = n;   // before is set to the inserted node.
				}
				else {
					this.head = n;
				}
				
				if (after != null) { // If the node isn't being added to the tail then the prev attribute of the node,
					after.prev = n;  // after is set to the inserted node.
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
			Node current = this.head; // The node at the current index
			
			for (int i = 0; i < this.size(); i++) { // Cycles the list
				if (current.getString().equals(obj)) {  // Compares the given string and the current node's string.
					return true;
				}
				else {
					current = current.next; // Selects the next node
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
			
			Node current = this.head; // Node at the current index
			Node before = null; // Node preceding the node to be removed
			Node after = null; // Node following the node to be removed
			
			for (int i = 0; i < this.size(); i++) { // Cycles the list
				if (current.getString().equals(obj)) { // Compares the current node's string to the given string
					before = current.prev; // If the string is a match the nodes before and after are selected
					after = current.next;
					
					if (before != null ) {	 // If there was a node before the node being removed (the head isn't being removed), 
						before.next = after; // the before node's next attribute is set to the node after the node being removed.
					}						 // (This may be null if the tail is being removed)
					else {
						this.head = after; // If it was the head being removed a new head is assigned
					}
					
					if (after != null) {	 // If there is a node after the node being removed (the tail isn't being removed),
						after.prev = before; // the after node's prev attribute is set to the node before the node being removed.
					}						 // (This may be null if the head is being removed)
					else {
						this.tail = before; // If it was the tail being removed a new tail is assigned
					}
					
					return true; // Successful removal
				}
				else {
					current = current.next;
				}
			}
			
			return false; // Non-successful removal (node with the given string didn't exist)
		}
		else {
			throw new NullPointerException();
		}
	}
	
	@Override
	public int hashCode() {
		Node current = this.head;
		int hash = 0;
		
		for (int i = 0; i < this.size(); i++) { // Cycles the list and adds the individual node's hashes together
			hash += current.hashCode();
			current = current.next;
		}
		
		return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (this.size() == ((DSEList)other).size()) { // Checks list sizes match
			Node thisCurrent = this.head;
			Node otherCurrent = ((DSEList)other).head;
			
			for (int i = 0; i < this.size(); i++) {
				if (thisCurrent.equals(otherCurrent)) { // Checks if each list's nodes are the same
					thisCurrent = thisCurrent.next;   // Selects the next nodes
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
	
	// A method I added for printing the list for debug purposes
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
