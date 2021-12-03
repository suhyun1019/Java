import java.util.Scanner;
enum Command {ADD, LIST, SUM, QUIT, MAX,MIN, INVALID};

public class ArrayEnum {
    public static void main(String[] args) {

        final Scanner sc = new Scanner(System.in);
        int index=0;
        int []values = new int[100];

        while(true) {
            final Command command = getCommand(sc.next());
            if (command==Command.QUIT) {
                System.out.println("Bye!");
                break;
            }
            switch (command) {
                case ADD:
                    final int newValue = getValue(sc.nextInt());
                    values[index] = newValue;
                    index++;
                    break;
                case LIST:
                    printList(values, index);
                    break;
                case SUM:
                    System.out.println(getSum(values, index));
                    break;
                case MAX:
                    System.out.println(getMax(values, index));
                    break;
                case MIN:
                    System.out.println(getMin(values, index));
                    break;
                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }
        sc.close();
    }

    private static Command getCommand(String C) {

        Command command;

        try {command = Command.valueOf(C.toUpperCase());}
        catch (IllegalArgumentException e) {command=Command.INVALID;}

        return command;
    }

    private static int getValue(int num) {
        return num;
    }

    private static int printList(int val[], int i) {
        for(int k=0;k<i;k++)
            System.out.print(val[k]+" ");
        System.out.println();

        return 0;
    }

    private static int getSum(int val[], int i) {
        int sum=0;
        for(int k=0;k<i;k++)
            sum+=val[k];

        return sum;
    }

    private static int getMax(int val[], int i) {
        int max=0;
        for(int k=0;k<i;k++)
            if(max<val[k]) max=val[k];

        return max;
    }

    private static int getMin(int val[], int i) {
        int min=100;
        for(int k=0;k<i;k++)
            if(min>val[k]) min=val[k];

        return min;
    }
}
