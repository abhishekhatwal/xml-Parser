
import java.util.Scanner;

public class calling_function {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int value;

        do{
            System.out.println("enter 1: to read xml ,enter 2:to create xml, enter 3:to modify xml");
            int choose = s.nextInt();

            switch (choose)
            {
                case 1:
                    readingxml.readxml();
                    break;
                case 2:
                    Writingxml.writexml();
                    break;
                case 3:
                   modifyingxml.modifyxml();
                    break;
                default:
                    System.out.println("enter valid number");
                    break;
            }
            System.out.println();
            System.out.println("do you want to continue if yes press 1 if no press 0");
            value=s.nextInt();

        }while(value==1);
    }
}
