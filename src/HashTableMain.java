import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class HashTableMain {
  public  static void main(String[] arg) throws FileNotFoundException {
    HashTable hashTable = new HashTable(90, 1);

    Scanner sc =  new Scanner(new File("src/alice_in_wonderland.txt"));
    while(sc.hasNextLine()) {
      String command = sc.nextLine();
      String[] s = command.split(" ");
      for (String str : s){
        StringBuilder sb = new StringBuilder(str.replaceAll("[^a-zA-Z0-9/-/./_]", ""));
        sb.trimToSize();
        HashTable.HashNode node = hashTable.find(sb.toString());
        if(node == null){
          inputHandler(new String[]{"insert", sb.toString(), "1"},hashTable);
        }
        else{
          inputHandler(new String[]{"increase", sb.toString()},hashTable);
        }
      }
    }
    hashTable.listAllKeys();

    sc = new Scanner(System.in);

    System.out.println("Running choices are as follows: 1.) insert <key> <value> 2.) find <key> " +
            "3.) increase <key> 4.)delete <key> 5.) list-all-keys\nPlease enter your choice:");
    while(sc.hasNextLine()) {
      System.out.println("Please enter your choice: ");
      inputHandler(sc.nextLine().split(" "),hashTable);
    }
  }

  private static void inputHandler( String[] input, HashTable hashTable){
    switch (input[0]) {
      case "insert":
        if(input.length <= 1)  System.out.println("Insert command must have command as: \"insert <key> <value>\"");
        else{ hashTable.insert(input[1], Integer.parseInt(input[2]));}
        break;
      case "delete":
        if(input.length <= 1)  System.out.println("Delete command must have command as: \"delete <key>\"");
        else System.out.println("Deleted Node: "+hashTable.delete(input[1]));
        break;

      case "search":
        if(input.length <= 1)  System.out.println("Find command must have command as: \"find <key>\"");
        else System.out.println("Searched Node: " + hashTable.find(input[1]));
        break;
      case "increase":
        if(input.length <= 1)  System.out.println("Increase command must have command as: \"increase <key>\"");
        else System.out.println("Increased key: "+ hashTable.increase(input[1]));
        break;

      case "list-all-keys":
         hashTable.listAllKeys();

      case "q":
        return;
      default:
        System.out.println("Not a valid Command.");
        break;
    }
    System.out.println("");
  }
}
