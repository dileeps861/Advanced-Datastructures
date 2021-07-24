import java.util.ArrayList;
import java.util.List;

/**
 * In computer science, a red–black tree is a kind of self-balancing binary search tree.
 * Each node stores an extra bit representing "color" ("red" or "black"), used to ensure
 * that the tree remains balanced during insertions and deletions.[2]
 *
 * When the tree is modified, the new tree is rearranged and "repainted" to restore
 * the coloring properties that constrain how unbalanced the tree can become in the worst case.
 * The properties are designed such that this rearranging and recoloring can be performed efficiently.
 *
 * The re-balancing is not perfect, but guarantees searching in O ( log ⁡ n ) {\displaystyle
 * {\mathcal {O}}(\log n)} {\mathcal {O}}(\log n) time, where n {\displaystyle n} n is the
 * number of nodes of the tree. The insertion and deletion operations, along with the tree
 * rearrangement and recoloring, are also performed in O ( log ⁡ n ) {\displaystyle
 * {\mathcal {O}}(\log n)} {\mathcal {O}}(\log n) time.[3]
 * Source: {@link https://en.wikipedia.org/wiki/Red%E2%80%93black_tree}
 */
public class RedBlackTree {
  final RBNode sentinel;
  RBNode root;

  public RedBlackTree() {
    this.sentinel = new RBNode();
    this.root = sentinel;
  }

  public void insert(int key) {
    RBNode node = new RBNode(key,true);
    rbInsert(node);
  }
  private void rbInsert(RBNode z) {
    RBNode y = sentinel;
    RBNode x = root;
    while (x != sentinel) {
      y = x;
      if (z.key < x.key) {
        x = x.left;
      } else {
        x = x.right;
      }
    }

    z.p = y;
    if (y == sentinel) {
      root = z;
    } else if (z.key < y.key) {
      y.left = z;
    } else {
      y.right = z;
    }
    z.left = sentinel;
    z.right = sentinel;
    z.red = true;
    insertFixup(z);
  }

  private void insertFixup(RBNode z) {
    while (z.p.red == true){
      if(z.p.p == null) break;
      if ( z.p == z.p.p.left){
        RBNode y = z.p.p.right;
        if (y.red == true){
          z.p.red = false;
          y.red = false;
          z.p.p.red = true;
          z = z.p.p;
        }

        else{
          if (z == z.p.right) {
            z = z.p;
            rotateLeft(z);
          }
          z.p.red = false;
          z.p.p.red = true;
          rotateRight(z.p.p);
        }
      }

      else{
        RBNode y = z.p.p.left;
        if (y.red == true){
          z.p.red = false;
          y.red = false;
          z.p.p.red = true;
          z = z.p.p;
        }

        else {
          if (z == z.p.left) {
            z = z.p;
            rotateRight(z);
          }
          z.p.red = false;
          z.p.p.red = true;
          rotateLeft(z.p.p);
        }
      }
    }
    this.root.red = false;
  }

  protected void rotateLeft(RBNode x) {
    RBNode y = x.right;
    x.right = y.left;
    if (y.left != sentinel) {
      y.left.p = x;
    }
    y.p = x.p;
    if (x.p == sentinel) {
      this.root = y;
    } else if (x == x.p.left) {
      x.p.left = y;
    } else {
      x.p.right = y;
    }
    y.left = x;
    x.p = y;

  }

  protected void rotateRight(RBNode x) {
    RBNode y = x.left;
    x.left = y.right;
    if (y.right != sentinel) {
      y.right.p = x;
    }
    y.p = x.p;
    if (x.p == sentinel) {
      this.root = y;
    } else if (x == x.p.left) {
      x.p.left = y;
    } else {
      x.p.right = y;
    }
    y.right = x;
    x.p = y;
  }

  public RBNode delete(int key) {
    return null;
  }

  public RBNode search(int key) {
    RBNode node = root;
    while (node != sentinel) {
      if (node.key == key) {
        return node;
      }
      if (node.left != sentinel && node.key > key) {
        node = node.left;
      } else if (node.right != sentinel && node.key < key) {
        node = node.right;
      } else {
        break;
      }
    }
    return null;
  }

  public List<RBNode> sort() {
    List<RBNode> list = new ArrayList<>();
    sort(list, root);

    return list;
  }

  private void sort(List<RBNode> list, RBNode node) {
    if (node.left != sentinel) {
      sort(list, node.left);
    }
    list.add(node);

    if (node.right != sentinel) {
      sort(list, node.right);
    }
  }

  public RBNode successor(int key) {
    RBNode foundNode = search(key);
    if(foundNode == null || foundNode == sentinel){
      System.out.println("No matching key found.");
      return null;
    }

    if(foundNode.right != sentinel){
      return this.min(foundNode.right);
    }

    RBNode pNode = foundNode.p;
    while(pNode != sentinel && foundNode == pNode.right){

      foundNode = pNode;
      pNode = pNode.p;
    }
    return pNode;
  }

  public RBNode predecessor(int key) {
    RBNode foundNode = search(key);
    if(foundNode == null || foundNode == sentinel){
      System.out.println("No matching key found.");
      return null;
    }

    if(foundNode.left != sentinel){
      return this.max(foundNode.left);
    }

    RBNode pNode = foundNode.p;
    while(pNode != sentinel && foundNode == pNode.left){
      foundNode = pNode;
      pNode = pNode.p;
    }
    return pNode;

  }

  public RBNode min() {

    return min(root);
  }
  private RBNode min(RBNode node) {

    while (node != sentinel) {
      if (node.left != sentinel) {
        node = node.left;
      } else {
        return node;
      }
    }
    return null;
  }
  public RBNode max() {

    return max(root);
  }
  private RBNode max(RBNode node) {

    while (node != sentinel) {
      if (node.right != sentinel) {
        node = node.right;
      } else {
        return node;
      }
    }
    return null;
  }

  public int getTreeHeight() {
    return getTreeHeight(root);
  }
  private int getTreeHeight( RBNode node) {

    if(node == sentinel)
      return 0;

    return Math.max(getTreeHeight(node.left)+1, getTreeHeight(node.right)+1);
  }

  public void printTree(){

  }

}

// Node for the RB Tree
class RBNode {
  boolean red;
  RBNode right;
  RBNode left;
  RBNode p;
  int key;

  RBNode(int key, boolean red) {
    this.key = key;
    this.red = red;
  }

  RBNode() {
    key = Integer.MIN_VALUE;
    red = false;
  }

  @Override
  public String toString() {
    return "RBNode{" +
            "red=" + red +
            ", key=" + key +
            '}';
  }
}

