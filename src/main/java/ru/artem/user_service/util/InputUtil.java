package ru.artem.user_service.util;

import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine();
    }

    public static Integer readInt(String message) {

        System.out.print(message + ": ");

        while (!scanner.hasNextInt()) {
            System.out.println("Введите число!");
            scanner.next();
        }

        int value = scanner.nextInt();
        scanner.nextLine();

        return value;
    }

    public static Long readLong(String message) {

        System.out.print(message + ": ");

        while (!scanner.hasNextLong()) {
            System.out.println("Введите число!");
            scanner.next();
        }

        long value = scanner.nextLong();
        scanner.nextLine();

        return value;
    }
}
