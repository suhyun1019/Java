import java.util.ArrayList;
import java.util.Scanner;
enum StringCommand {ADD, REMOVE, CLEAN, QUIT, INVALID}

public class StringSetManager {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        final Scanner sc = new Scanner(System.in);

        while(true) {
            final StringCommand command = getCommand(sc.next());

            if(command == StringCommand.QUIT) {
                System.out.println("BYE!");
                break;
            }
            switch (command) {
                case ADD:
                    final String str1 = getString(sc.next());
                    if(list.contains(str1)) break;
                    else list.add(str1);
                    break;
                case REMOVE:
                    final String str2 = getString(sc.next());
                    list.remove(str2);
                    break;
                case CLEAN:
                    list.clear();
                    break;
                default:
                    System.out.println("Unknown Command!");
                    break;
            }
            System.out.println("Element Size: "+list.size()+", Values = "+list);
        }
    }

    private static StringCommand getCommand(String s) {
        StringCommand stringcommand;

        try {stringcommand=StringCommand.valueOf(s.toUpperCase());}
        catch (IllegalArgumentException e) {stringcommand=StringCommand.INVALID;}

        return stringcommand;
    }

    private static String getString(String S) {
        return S;
    }

}