package PurdueX.course_2_loopsAndDataStructures.holidayProject;

import java.io.IOException;
import java.util.Scanner;

public class HolidayRunner {
    public static void main(String[] args) throws IOException {
        DeliveryMap d = new DeliveryMap("src/PurdueX/course_2_loopsAndDataStructures/holidayProject/maps/mapA.txt");
        ScannerClaus sc = new ScannerClaus(d);
        Scanner s = new Scanner(System.in);
        int playMode;
        char choice;

        System.out.println("Automate (1) or Play (2): ");
        playMode = s.nextInt();
        if (playMode == 1) {
            sc.autoDeliver(d);
        } else {
            do {
                System.out.println(d.printMap());
                System.out.println(sc);
                System.out.println("Press W (up), A (left), S (down), D (right), Q (quit)\n");
                choice = s.next().toUpperCase().charAt(0);
                sc.move(d, choice);
            } while (choice != 'Q' && sc.getNumCarrots() > 0 && sc.getNumPresents() > 0);
        }

        System.out.println(d.printMap());
        System.out.println(sc);
        if (sc.getNumCarrots() == 0) {
            System.out.println("Your reindeer ran out of carrots and refuse to keep going!");
        } else if (sc.getNumPresents() == 0) {
            System.out.println("You delivered all of the presents! Ho ho!");
        }
    }
}

