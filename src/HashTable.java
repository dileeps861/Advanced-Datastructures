/**
 * Hash table used to create a key value paired datastructures. A hashtable can have duplicate
 * values but it must have unique keys. This implementations of hash table is using linked list to
 * resolve collision.
 */
public class HashTable {
  HashNode[] lookup;
  int increment;

  public HashTable(int size, int increment) {
    lookup = new HashNode[size];
    this.increment = increment;
  }

  public void insert(String key, Integer value){
    int hashCode = hashFunction(key);
    int index = hashCode % lookup.length;

    HashNode foundNode = find(key);
    if(foundNode == null){
      HashNode node = lookup[index];
      HashNode newNode = new HashNode(key, value);
      if(node != null){
       newNode.next = node.next;
       node.next = newNode;
      }
      else {
        lookup[index] = newNode;
      }
    }
    else foundNode.value = value;
  }
  public HashNode delete(String key){
    int hashCode = hashFunction(key);
    int index = hashCode % lookup.length;
    HashNode node = lookup[index];
    if (node.key.equals(key)){
      if(node.next != null){
        lookup[index] = node.next;
      }
    }
    if(node.next != null){
      while (node.next != null){
        if(node.next.key.equals(key)){
          HashNode nodeDel = node.next;
          node.next = node.next.next;
          return nodeDel;
        }
        node = node.next;
      }
    }
    return null;
  }
  public HashNode increase(String key){

    HashNode foundNode = find(key);
    if(foundNode != null){
      foundNode.value += increment;
    }
    return foundNode;
  }
  public HashNode find(String key){
    int hashCode = hashFunction(key);
    int index = hashCode % lookup.length;
    HashNode node = lookup[index];
    if(node == null){
      return null;
    }
    else {
      while (node != null){
        if(node.key.equals(key)){
          return node;
        }
        node = node.next;
      }
      return null;
    }
  }
  public void listAllKeys(){
    for(int i = 0; i < lookup.length; i++){
      if(lookup[i] != null){
        HashNode node = lookup[i];
        while (node != null){
          System.out.println(node);
          node = node.next;
        }
      }
    }
  }

  protected class HashNode{
    String key;
    Integer value;
    HashNode next;
    public HashNode(String key, Integer value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("Node{");
      sb.append("key='").append(key).append('\'');
      sb.append(", value=").append(value);
      sb.append('}');
      return sb.toString();
    }

  }

  private int hashFunction(String s) {
    int hash = 7;
    for (int i = 0; i < s.length(); i++) {
      hash = hash*31 + s.charAt(i);
    }
    return Math.abs(hash);
  }
}
