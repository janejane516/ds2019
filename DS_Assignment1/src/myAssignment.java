//2016-18223 Jane Shin
import java.util.*;
public class myAssignment {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String prompt = "What action would you like to perform?";
        String[] menu = new String[12];
        menu[0] = "A\tAdd ContactEntry";
        menu[1] = "L\tAdd Last";
        menu[2] = "E\tCheck if Empty";
        menu[3] = "S\tSearch for Contact";
        menu[4] = "P\tPrint Contacts";
        menu[5] = "Z\tPrint Names";
        menu[6] = "N\tList Current Size";
        menu[7] = "Q\tQuit";
        menu[8] = "R\tRemove ContactEntry";
        menu[9] = "T\tSort";
        menu[10] = "U\tRemove Duplicates";
        menu[11] = "?\tDisplay Help";
        for(int i=0; i<12; i++) {
            System.out.println(menu[i]);
        }
        myLinkedList L = new myLinkedList();

        while (true) {
            System.out.println(prompt);
            char op = console.next().charAt(0);

            //Add Contact Entry
            if (op == 'a' || op == 'A') {
                System.out.println("Please enter an index:");
                int ind = console.nextInt();
                ContactEntry C = new ContactEntry();
                while (ind > L.size()) {
                    System.out.println("The last index is " + (L.size() - 1));
                    System.out.println("Please enter an index:");
                    ind = console.nextInt();
                }
                L.add(ind, C);
                System.out.println("Please enter a name:");
                C.setName(console.next());
                System.out.println("Please enter a phone number:");
                C.setPhoneNumber(console.nextInt());
                System.out.println("Please enter an occupation:");
                C.setOccupation(console.next());
            }
            //Add Last
            else if (op == 'l' || op == 'L') {
                ContactEntry C = new ContactEntry();
                L.add(L.size(), C);
                System.out.println("Please enter a name:");
                C.setName(console.next());
                System.out.println("Please enter a phone number:");
                C.setPhoneNumber(console.nextInt());
                System.out.println("Please enter an occupation:");
                C.setOccupation(console.next());
            }
            //Check if Empty
            else if (op == 'e' || op == 'E') {
                if (L.isEmpty()) {
                    System.out.println("The list is empty");
                } else {
                    System.out.println("The list contains some element(s)");
                }
            }
            //Search for Contact
            else if (op == 's' || op == 'S') {
                System.out.println("Please enter an index to search:");
                int ind = console.nextInt();
                while (ind >= L.size()) {
                    System.out.println("index you want is not in the list");
                    System.out.println("Please enter an index to search:");
                    ind = console.nextInt();
                }
                System.out.println("contact at the given index is " + ((ContactEntry) L.get(ind)).getName());
            }
            //Print Contacts
            else if (op == 'p' || op == 'P') {
                ContactEntry[] Stud = new ContactEntry[L.size()];
                ContactEntry[] Prof = new ContactEntry[L.size()];
                ContactEntry[] TA = new ContactEntry[L.size()];
                int s = 0, p = 0, t = 0;
                for(int i=0; i<L.size(); i++) {
                    ContactEntry C = (ContactEntry) L.get(i);
                    if(C.getOccupation().equals("Student")) {
                        Stud[s] = C;
                        s++;
                    }
                    else if(C.getOccupation().equals("Professor")) {
                        Prof[p] = C;
                        p++;
                    }
                    else if(C.getOccupation().equals("TA")) {
                        TA[t] = C;
                        t++;
                    }
                }
                System.out.println("Students:");
                for (int i = 0; i < s; i++) {
                    System.out.println(Stud[i].getName() + " " + Stud[i].getPhoneNumber());
                }
                System.out.println();
                System.out.println("Professors:");
                for (int i = 0; i < p; i++) {
                    System.out.println(Prof[i].getName() + " " + Prof[i].getPhoneNumber());
                }
                System.out.println();
                System.out.println("TA:");
                for (int i = 0; i < t; i++) {
                    System.out.println(TA[i].getName() + " " + TA[i].getPhoneNumber());
                }
                System.out.println();
            }
            //Print names
            else if (op == 'z' || op == 'Z') {
                for(int i=0; i<L.size(); i++) {
                    System.out.print(" " + ((ContactEntry)(L.get(i))).getName());
                }
                System.out.println(" ");
            }
            //List Current SIze
            else if (op == 'n' || op == 'N') {
                System.out.println("The current size is " + L.size());
            }
            //Quit
            else if (op == 'q' || op == 'Q') {
                break;
            }
            //Remove Contact
            else if (op == 'r' || op == 'R') {
                if (L.isEmpty()) {
                    System.out.println("Empty list");
                }
                else {
                    System.out.println("Please enter the index of the list to remove:");
                    int ind = console.nextInt();
                    while (ind >= L.size()) {
                       System.out.println("It does not exist");
                       System.out.println("Please enter the index of the list to remove:");
                       ind = console.nextInt();
                    }
                    System.out.println(((ContactEntry)(L.remove(ind))).getName() + " was removed");
                }
            }
            //Sort
            else if (op == 't' || op == 'T') {
                L.Sort();
                System.out.println("list sorted");
            }
            //Remove Duplicate
            else if (op == 'u' || op == 'U') {
                if(L.RemoveDuplicate()) {
                    System.out.println("Duplicate contact removed");
                }
                else {
                    System.out.println("there is no duplicate");
                }
            }
            //Display Help
            else if (op == '?') {
                for(int i=0; i<12; i++) {
                    System.out.println(menu[i]);
                }
            }

        }
    }

}
