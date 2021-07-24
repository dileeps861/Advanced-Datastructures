import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class RBTreeMain {
  public  static void main(String[] arg) throws FileNotFoundException {
    RedBlackTree redBlackTree = new RedBlackTree();

    Scanner sc =  new Scanner(new File("src/input.text"));
    while(sc.hasNextLine()) {
      String command = sc.nextLine();
      System.out.println("Running command: "+command);
      inputHandler(command,redBlackTree);
    }

    sc = new Scanner(System.in);

    System.out.println("Running choices are as follows: 1.) insert <key> 2.) search <key> 3.) min 4.)" +
            " max 5.) predecessor <key> 6.) successor <key> 7.) sort 8.) q\nPlease enter your choice:");
    while(sc.hasNextLine()) {
        System.out.println("Please enter your choice: ");
        inputHandler(sc.nextLine(),redBlackTree);
    }
  }

  private static void inputHandler(String s, RedBlackTree redBlackTree){
    String[] input = s.split(" ");
    switch (input[0]) {
      case "insert":
        if(input.length <= 1)  System.out.println("Insert command must have command as: \"insert <key>\"");
        else redBlackTree.insert(Integer.parseInt(input[1]));
        break;
      case "delete":
        if(input.length <= 1)  System.out.println("Delete command must have command as: \"delete <key>\"");
        else redBlackTree.delete(Integer.parseInt(input[1]));
        break;

      case "search":
        if(input.length <= 1)  System.out.println("Search command must have command as: \"search <key>\"");
        else System.out.println("Searched Node: " + redBlackTree.search(Integer.parseInt(input[1])));
        break;
      case "sort":
        List<RBNode> sortedList = redBlackTree.sort();
        System.out.println("RB Tree's root is: "+ redBlackTree.root);
        System.out.println(sortedList.toString());
        break;

      case "max":
        System.out.println("Max: " + redBlackTree.max());
        break;

      case "min":
        System.out.println("Min: " +redBlackTree.min());
        break;
      case "successor":
        System.out.println("Successor: " +redBlackTree.successor(Integer.parseInt(input[1])));
        break;

      case "predecessor":
        System.out.println("Predecessor: " +redBlackTree.predecessor(Integer.parseInt(input[1])));
        break;

      case "q":
        return;
      default:
        System.out.println("Not a valid Command.");
        break;
    }
    System.out.println("Tree height: "+ redBlackTree.getTreeHeight()+"\n");
  }
}
