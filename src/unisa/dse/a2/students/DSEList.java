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
	public String remove(int index) {

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
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
	}

	//add String at parameter's index
	public boolean add(int index, String obj) {
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		return true;
	}
	
}
