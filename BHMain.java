import java.io.IOException;
import java.util.Scanner;

public class BHMain {
  public static void main (String[] args) throws IOException
  {
    BinomialHeap binomialHeap =  new BinomialHeap();
    for (int i = 1; i<=200; i++ ){
      binomialHeap.insert(i);
    }
    binomialHeap.print();

    Scanner sc =  new Scanner(System.in);
    System.out.println("Please enter your choice: ");
    while(sc.hasNextLine()) {
      System.out.println("Please enter your choice: ");
      handleChoice(binomialHeap, sc.nextLine());
    }
  }

  private static void handleChoice(BinomialHeap binomialHeap, String s){
    String[] input = s.split(" ");
    switch (input[0]) {
      case "insert":
        if(input.length <= 1)  System.out.println("Insert command must have command as: \"insert <key>\"");
        else binomialHeap.insert(Integer.parseInt(input[1]));
        break;
      case "delete":
        if(input.length <= 1)  System.out.println("Delete command must have command as: \"delete <key>\"");
        else{
            binomialHeap.delete(Integer.parseInt(input[1]));
            System.out.println("Node deleted successfully.");
            System.out.println("Search deleted node: "+binomialHeap.search(Integer.parseInt(input[1])));
        }
        break;
      case "extract-min":
        System.out.println("Extracted min: "+binomialHeap.extractMin());
        break;
      case "minimum":
        System.out.println("Minimum: "+ binomialHeap.minimum());
        break;

      case "decrease-key":
        if(input.length <= 2)  System.out.println("Delete command must have command as: \"delete <oldKey> <newKey>\"");
        else {
          binomialHeap.decreaseKey(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
        }
        break;

      case "search":
        if(input.length <= 1)  System.out.println("Find command must have command as: \"search <key>\"");
        else System.out.println("Searched Node: " + binomialHeap.search(Integer.parseInt(input[1])));
        break;
      case "print":
        binomialHeap.print();
        break;
      case "q":
        return;
      default:
        System.out.println("Not a valid Command.");
        break;
    }
  }

}
