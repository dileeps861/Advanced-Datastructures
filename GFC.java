// { Driver Code Starts
//Initial Template for Java

import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;

class Node
{
  int data;
  Node left;
  Node right;
  Node(int data)
  {
    this.data = data;
    left=null;
    right=null;
  }
}
class pair
{
  int first;
  int second;
  pair(int first , int second)
  {
    this.first = first;
    this.second = second;
  }
}
class GfG {

  static Node buildTree(String str)
  {

    if(str.length()==0 || str.charAt(0)=='N'){
      return null;
    }

    String ip[] = str.split(" ");
    // Create the root of the tree
    Node root = new Node(Integer.parseInt(ip[0]));
    // Push the root to the queue

    Queue<Node> queue = new LinkedList<>();

    queue.add(root);
    // Starting from the second element

    int i = 1;
    while(queue.size()>0 && i < ip.length) {

      // Get and remove the front of the queue
      Node currNode = queue.peek();
      queue.remove();

      // Get the current node's value from the string
      String currVal = ip[i];

      // If the left child is not null
      if(!currVal.equals("N")) {

        // Create the left child for the current node
        currNode.left = new Node(Integer.parseInt(currVal));
        // Push it to the queue
        queue.add(currNode.left);
      }

      // For the right child
      i++;
      if(i >= ip.length)
        break;

      currVal = ip[i];

      // If the right child is not null
      if(!currVal.equals("N")) {

        // Create the right child for the current node
        currNode.right = new Node(Integer.parseInt(currVal));

        // Push it to the queue
        queue.add(currNode.right);
      }
      i++;
    }

    return root;
  }
  static void printInorder(Node root)
  {
    if(root == null)
      return;

    printInorder(root.left);
    System.out.print(root.data+" ");

    printInorder(root.right);
  }

  public static boolean isBST(Node n, int lower, int upper)
  {
    if(n==null)
      return true;
    if( n.data <= lower || n.data >= upper )
      return false;
    return (  isBST( n.left, lower, n.data )  &&  isBST( n.right, n.data, upper )  );
  }

  public static boolean compare( Node a, Node b, ArrayList<pair> mismatch )
  {
    if( a==null && b==null ) return true;
    if( a==null || b==null ) return false;

    if( a.data != b.data )
    {
      pair temp = new pair(a.data,b.data);
      mismatch.add(temp);
    }


    return ( compare( a.left, b.left, mismatch ) && compare( a.right, b.right, mismatch ) );
  }

  public static void main (String[] args) throws IOException
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int t=Integer.parseInt(br.readLine());

    while(t-- > 0)
    {
      String s = br.readLine();
      Node root = buildTree(s);
      Node duplicate = buildTree(s);

      Sol g = new Sol();

      Node temp = g.correctBST(root);
      if(temp != root)
        System.out.println(0);

      // check 1: is tree now a BST
      if(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE) == false)
      {
        System.out.println(0);
        continue;
      }

      // check 2: comparing with duplicate tree


      ArrayList<pair> mismatch = new ArrayList<pair>();
      // an arraylist to store data of mismatching nodes

      if( compare( root, duplicate, mismatch) == false)
      {
        // false output from this function indicates change in structure of tree
        System.out.println(0);
        continue;
      }

      // finally, analysing the mismatching nodes
      if( mismatch.size() !=2 || mismatch.get(0).first!=mismatch.get(1).second || mismatch.get(0).second!=mismatch.get(1).first )
        System.out.println(0);
      else
        System.out.println(1);



    }
  }
}

// } Driver Code Ends


//User function Template for Java

class Sol
{
  //Function to fix a given BST where two nodes are swapped.
  public Node correctBST(Node root)
  {
    //code here.

    // Find the wrong position node.
    Node wrongNodeOne = findWrongNode(root, null);
    Node wrongNodeTwo = findWrongNode(root, wrongNodeOne);

    if(wrongNodeTwo == null && wrongNodeOne == null) return null;
    if(wrongNodeTwo == null){
      findLeft(wrongNodeOne, root);
    }
    else{
      findLeft(wrongNodeOne, findWrong(wrongNodeTwo));
    }

    return root;
  }

  private void findLeft(Node wrongNodeOne, Node wrongNodeTwo) {

    if(wrongNodeOne.right.data < wrongNodeOne.data ){
      int data = wrongNodeOne.right.data;
      wrongNodeOne.data = wrongNodeTwo.data;
      wrongNodeTwo.data = data;
    }
    else{
      int data = wrongNodeOne.left.data;
      wrongNodeOne.data = wrongNodeTwo.data;
      wrongNodeTwo.data = data;
    }
  }

  private Node findWrong(Node wrongNode) {
    if(wrongNode.right.data < wrongNode.data ){
      return wrongNode.right;
    }
    else {
      return wrongNode.left;
    }
  }

  private Node findWrongNode(Node node, Node firstWrong)
  {
    //code here.
    Node nodeWrong = null;

    if(node.left!=null && node.left.data > node.data && (firstWrong == null ||
            node.left.data != firstWrong.data)){
      return node;
    }
    if(node.right!=null && node.right.data < node.data
            && (firstWrong == null ||
            node.right.data != firstWrong.data)
    ){
      return node;
    }
    if(node.left!=null && node.right!=null ){
      return  null;
    }
    if(node.left!=null){
      nodeWrong = findWrongNode(node.left, firstWrong);
    }
    if(node.right!=null ){
      nodeWrong = findWrongNode(node.right, firstWrong);
    }

    // Find the wrong position node.
    return nodeWrong;
  }

  // private Node findWrongNode(Node node)
  // {
  //     //code here.
  //     Node nodeWrong;
  //     if(node.left!=null && node.left.data > node.data){
  //         return node;
  //     }
  //     if(node.right!=null && node.right.data < node.data){
  //         return node;
  //     }
  //     if(node.left!=null){
  //         nodeWrong = findWrongNode(node.left);
  //     }
  //      if(node.right!=null ){
  //          nodeWrong = findWrongNode(node.right);
  //      }

  //     // Find the wrong position node.
  //     return nodeWrong;
  // }
}
