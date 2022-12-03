package DoublyLinkedList; 
class CircularLL{ //doubly circular 
	private Node head; 
	CircularLL(){} 
	Node getHead() { 
		return head; 
	} 
	void add(String name) {  
		if(head==null) { 
		 Node nn = new Node(name, null, null); 
		 nn.setNext(nn); //circular, points to itself 
		 nn.setPrev(nn); 
		 head = nn; 
		} 
		else { 
		 Node nn = new Node(name, null, null); 
		 nn.setNext(head); 
		 nn.setPrev(head.getPrev()); 
		 head.setPrev(nn); 
		 nn.getPrev().setNext(nn); 
		 head = nn; 
		} 
	} 
	public String toString(){ 
		if(head == null) { 
			return ""; 
		} 
		else { 
			String s = ""; 
			Node curr = head; 
			while(curr.getNext()!=head) { 
				s+=curr.getName() + " "; 
				curr = curr.getNext(); 
			} 
			return s + head.getPrev().getName(); 
		} 
	} 
	} 
class CircularDoubly{ 
	public static void main(String args[]) { 
		CircularLL myList = new CircularLL(); 
		myList.add("dane");  
		myList.add("jill"); 
		myList.add("okay"); 
		myList.add("yeah"); 
		System.out.println(myList.toString()); 
		//System.out.println(myList.getHead().getName()); 
		//System.out.println(myList.getHead().getPrev().getNext().getName()); 
	} 
}
