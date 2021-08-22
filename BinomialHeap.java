/**
 * Binomial heap implementation. Binomial heap contains the tree of different degrees with no
 * duplicate degree tree.
 */
   public class BinomialHeap {

      private BHNode head;
      private final BHNode sentinel;

     /**
      * Constructor to create an empty Binomial heap.
      */
     public BinomialHeap() {
        this.sentinel =  new BHNode();
        this.sentinel.key = Integer.MIN_VALUE;
        head = sentinel;
     }

     /**
      * Insert a new node with given key in the Binomial heap.
      * @param key the new key
      */
     public void insert(int key) {
       if(key <= 0){
         System.out.println("Key must be greater than 0");
         return;
       }

      BHNode x = new BHNode(key);
      if (head == sentinel) {
        head = x;
      } else {
        union(x);
      }
     }

     /**
      * Merge the new node with the current node.
      * @param node the new node to merge
      */
      private void merge(BHNode node) {
        BHNode y = head;
        BHNode z = node;
        while ((y != sentinel) && (z != sentinel)) {
          if (y.degree == z.degree) {
            BHNode prevZ = z;
            z = z.sibling;
            prevZ.sibling = y.sibling;
            y.sibling = prevZ;
            y = prevZ.sibling;
          } else {
            if (y.degree < z.degree) {
              if ((y.sibling == sentinel) || (y.sibling.degree > z.degree)) {
                BHNode prevZ = z;
                z = z.sibling;
                prevZ.sibling = y.sibling;
                y.sibling = prevZ;
                y = prevZ.sibling;
              } else {
                y = y.sibling;
              }
            } else {
              BHNode prevY = y;
              y = z;
              z = z.sibling;
              y.sibling = prevY;
              if (prevY == head) {
                head = y;
              }
            }
          }
        }
        if (y == sentinel) {
          y = head;
          while (y.sibling != sentinel) {
            y = y.sibling;
          }
          y.sibling = z;
        }
      }

     /**
      * Union method to union new heap with current heap.
      * @param node the new node
      */
      public void union(BHNode node) {
        merge(node);
        BHNode prevY = sentinel, z = head, nextX = head.sibling;
        while (nextX != sentinel) {
          if ((z.degree != nextX.degree) || ((nextX.sibling != sentinel) && (nextX.sibling.degree == z.degree))) {
            prevY = z;
            z = nextX;
          } else {
            if (z.key <= nextX.key) {
              z.sibling = nextX.sibling;
              nextX.p = z;
              nextX.sibling = z.child;
              z.child = nextX;
              z.degree++;
            } else {
              if (prevY == sentinel) {
                head = nextX;
              } else {
                prevY.sibling = nextX;
              }
              z.p = nextX;
              z.sibling = nextX.child;
              nextX.child = z;
              nextX.degree++;
              z = nextX;
            }
          }
          nextX = z.sibling;
        }
      }

     /**
      * Retrieve the minimum node from the binomial heap
      * @return the minimum node
      */
      public BHNode minimum() {
        return head.minimum();
      }

     /**
      * Delete the node of the given key.
      * @param key the key to be deleted.
      */
      public void delete(int key) {
        if ((head != sentinel) && (head.search(key) != sentinel)) {
          decreaseKey(key, minimum().key - 1);
          extractMin();
        }
      }

     /**
      * Decrease the key of the binomial heap node whith the new given heap.
      * @param oldKey the the old key
      * @param newKey the new key
      */
      public void decreaseKey(int oldKey, int newKey) {
        BHNode x = head.search(oldKey);
        if (x == sentinel) {
          return;
        }
        x.key = newKey;
        BHNode y = x.p;
        while ((y != sentinel) && (x.key < y.key)) {
          int z = x.key;
          x.key = y.key;
          y.key = z;
          x = y;
          y = y.p;
        }
      }

     /**
      * Extract the minimum from the Binomial heap.
      * @return the minimum removed node.
      */
      public BHNode extractMin() {
        if (head == sentinel) {
          return head;
        }
        BHNode x = head, y = sentinel;
        BHNode min = head.minimum();
        while (x.key != min.key) {
          y = x;
          x = x.sibling;
        }
        if (y == sentinel) {
          head = x.sibling;
        } else {
          y.sibling = x.sibling;
        }
        x = x.child;
        BHNode z = x;
        while (x != sentinel) {
          x.p = sentinel;
          x = x.sibling;
        }
        if ((head == sentinel) && (z == sentinel)) {
        } else {
          if ((head == sentinel) && (z != sentinel)) {
            head = z.reverseList(sentinel);
          } else {
            if ((head == sentinel) || (z != sentinel)) {
              union(z.reverseList(sentinel));
            }
          }
        }
        return min;
      }

     /**
      * Method to print the Binomial heap.
      */
     public void print() {
        print(head);
      }

     /**
      * Private helper method to call print method recursively and print the node.
      * @param node
      */
      private void print(BHNode node) {
        if (node != sentinel) {
          System.out.println(node);
          print(node.sibling);
          print(node.child);
        }
      }

    /**
     * Search the key in the heap.
     * @param key
     * @return
     */
    public BHNode search(int key) {
          return this.head.search(key);
    }

      /**
      * Binomial heap node, stores the data of the heap.
      */
     class BHNode {
       int key;
       int degree;
       BHNode p;
       BHNode sibling;
       BHNode child;

       /**
        * Constructor to create a default node.
        */
       public BHNode() {
         this(0);
       }

       /**
        * Constructor to create a node with given key.
        * @param key
        */
       public BHNode(int key) {
         this.key = key;
         this.degree = 0;
         this.p = sentinel;
         this.sibling = sentinel;
         this.child = sentinel;
       }

       /**
        * Reverse the linked list.
        * @param x the input node
        * @return the reversed node
        */
        BHNode reverseList(BHNode x) {
         BHNode y;
         if (this.sibling != sentinel) {
           y = this.sibling.reverseList(this);
         } else {
           y = this;
         }
         this.sibling = x;
         return y;
       }

       /**
        * Find the minimum of the given binary heap.
        * @return the minimum node
        */
        BHNode minimum() {
         BHNode temp = this;
         BHNode min = this;
         while (temp != sentinel) {
           if (temp.key < min.key) {
             min = temp;
           }
           temp = temp.sibling;
         }
         return min;
       }

       /**
        * Search the node of given key.
        * @param key the key to search
        * @return the searched node
        */
        BHNode search(int key) {
         BHNode x = this, y = sentinel;
         while (x != sentinel) {
           if (x.key == key) {
             return x;
           }
           if (x.child != sentinel) {
             y = x.child.search(key);
             if(y != sentinel) return y;
             x = x.sibling;

           } else {
             x = x.sibling;
           }
         }
         return y;
       }

       @Override
       public String toString() {
         final StringBuilder sb = new StringBuilder("BHNode{");
         sb.append("key=").append(key);

         sb.append(", degree=").append(degree);
         if(p != null)
         sb.append(", parent=").append(p.key);
         if(child != null)
         sb.append(", child=").append(child.key);
         if(sibling != null)
         sb.append(", sibling=").append(sibling.key);
         sb.append('}');
         return sb.toString();
       }
     }
    }
