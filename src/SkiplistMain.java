import java.util.Scanner;

public class SkiplistMain {
  public  static void main(String[] arg){
    SkipList skipList = new SkipList();

    Scanner sc =  new Scanner(System.in);
    System.out.println("Please enter your choice: ");
    while(sc.hasNextLine()) {
      handlechoice(skipList, sc.nextLine());
    }
  }
  private static void handlechoice(SkipList skipList, String s){
    String[] input = s.split(" ");
    switch (input[0]) {
      case "insert":
        if(input.length <= 1)  System.out.println("Insert command must have command as: \"insert <key>\"");
        else skipList.insert(Integer.parseInt(input[1]));
        break;
      case "delete":
        if(input.length <= 1)  System.out.println("Delete command must have command as: \"delete <key>\"");
        else skipList.delete(Integer.parseInt(input[1]));
        break;

      case "find":
        if(input.length <= 1)  System.out.println("Find command must have command as: \"find <key>\"");
        else System.out.println("Searched Node: " + skipList.find(Integer.parseInt(input[1])));
        break;
      case "print":
        skipList.printList();
        break;
      case "q":
        return;
      default:
        System.out.println("Not a valid Command.");
        break;
    }
  }
}
