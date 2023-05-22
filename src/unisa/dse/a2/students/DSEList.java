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
			
			//debug
			Node current = this.head;
			for (int i = 0; i < this.size(); i++) {
				System.out.println("node: " + current + " node text: " + current.getString() + " previous: " + current.prev + " next: " + current.next);
				current = current.next;
			}
			System.out.println();
			//debug end
			
			return true;
		}
		else {
			throw new NullPointerException();
		}
	}

	//add String at parameter's index
	public boolean add(int index, String obj) throws NullPointerException, IndexOutOfBoundsException{
		if (obj != null) {
			
			if (index == 0) {
				Node n = new Node(this.head, null, obj);
				this.head.prev = n;
				this.head = n;
				
			}
			else if(index == this.size()) {
				Node n = new Node(null, this.tail, obj);
				this.tail.next = n;
				this.tail = n;

			}
			else if (index > this.size() && index < 0) {
				throw new IndexOutOfBoundsException();
				
			}
			else {
				int count = 0;
				Node current = this.head;
				
				while (count < (index - 1)) {
					current = current.next;
					count ++;
				}
				
				Node n = new Node(current.next, current, obj);
				current.next = n;
				current = n.next;
				current.prev = n;
			}
			
			//debug
			Node current = this.head;
			for (int i = 0; i < this.size(); i++) {
				System.out.println("node: " + current + " node text: " + current.getString() + " previous: " + current.prev + " next: " + current.next);
				current = current.next;
			}
			System.out.println();
			//debug end
			
			return true;			
		}
		else {
			throw new NullPointerException();
		}
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
