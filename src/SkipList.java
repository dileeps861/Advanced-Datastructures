import java.util.Random;

public class SkipList {
  Node head;
  Node tail;
  public SkipList() {
      head = new Node(Integer.MIN_VALUE, 0);

      tail = new Node(Integer.MAX_VALUE, 0);
      head.next = tail;
      tail.prev = head;
  }

  public void insert(int data) {
    Node foundNode = findBiggestSmaller(data);

    Node newNode = new Node(data, 0);
    newNode.next = foundNode.next;
    foundNode.next.prev = newNode;
    foundNode.next = newNode;
    newNode.prev = foundNode;

    // Create Layers
    int level = 1;
    boolean flipped = flipHead();
    Node prevNode = foundNode;
    while (flipped){
      Node newLayers = new Node(data,level++);
      newNode.up = newLayers;
      newLayers.down = newNode;
      while (prevNode.up == null && prevNode.data != Integer.MIN_VALUE){
        prevNode = prevNode.prev;
      }

      if(head.level >= newLayers.level){
        prevNode = prevNode.up;
        newLayers.prev = prevNode;
        newLayers.next = prevNode.next;
        prevNode.next.prev = newLayers;
        prevNode.next = newLayers;

      }
      else if(head.level < newLayers.level){

        Node newHead = new Node(head.data, head.level+1);
        newHead.down = head;
        head.up = newHead;
        newHead.next = newLayers;
        newLayers.prev = newHead;
        this.head =  newHead;
      }
      if(tail.level < newLayers.level ){
        Node newTail = new Node(tail.data, tail.level+1);
        newTail.down = tail;
        tail.up = newTail;
        newTail.prev = newLayers;
        newLayers.next = newTail;
        this.tail =  newTail;

      }
      newNode = newLayers;
      flipped = flipHead();
    }

  }

  protected boolean flipHead() {
    Random r = new Random();
    int chance = r.nextInt(2);
    if (chance == 1) {
      return false;
    } else {
      return true;
    }
  }

  public void delete(int data) {
    Node n = findBiggestSmaller(data);

    if (n.data == Integer.MIN_VALUE || n.data != data || data == Integer.MAX_VALUE || data == Integer.MAX_VALUE ) {
      System.out.println("No matching data found to delete.");
      return;
    }

    System.out.println("Successfully deleted the node: "+ n);
    while (n != null) {
      n.prev.next = n.next;
      n.next.prev = n.prev;
      n = n.up;
    }
  }

  /**
   * Pass the level node and then find the node which data is smaller than the argument data but is
   * the biggest one.
   *
   * @param node the node search data range from
   * @param data the data to find the smallest value
   * @return the biggest smaller node
   */
  protected Node findBiggestSmallerAtLayer(Node node, int data) {

    Node temp = node;
    while (temp.next.data <= data){
      temp = temp.next;
    }

    return temp;
  }

  protected Node findBiggestSmaller(int data) {
    Node temp = head;
    temp = findBiggestSmallerAtLayer(temp, data);
    while (temp.down != null) {
      temp = findBiggestSmallerAtLayer(temp.down, data);
    }
    return temp;
  }


  public Node find(int data) {
    Node temp = findBiggestSmaller(data);
    return temp.data == data ? temp : null;
  }

  void printList() {
    Node temp = head;
    while (temp.down != null) {
      temp = temp.down;
    }

    while (temp != null && temp.data <= Integer.MAX_VALUE) {
      Node n = temp;
      while (n != null) {
        System.out.print(n.data + "( Next: ");
        System.out.print((n.next != null ? n.next : " nil ") + ") | \t");
        n = n.up;
      }
      System.out.print("\n");
      temp = temp.next;
    }

  }
}

class Node {
  protected Node prev;
  protected Node next;
  protected Node down;
  protected Node up;
  protected int data;
  protected int level;

  public Node(int data, int level) {
    this.data = data;
    this.level = level;
  }

  @Override
  public String toString() {
    return "Node{" +
            "data=" + data +
            ", level=" + level +
            '}';
  }
}
