package com.github.anthogis.tddproject;

import java.util.Scanner;

public class App {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        LearningInstitution school = new LearningInstitution();

        System.out.println("Welcome!\nType help to see list of commands");

        while (run) {
            System.out.print('>');
            run = handleInput(scanner.nextLine());
        }
    }

    private static boolean handleInput(String input) {
        boolean run = true;

        if (input.equals(Commands.HELP.command)) {
            for (Commands c : Commands.values()) {
                System.out.printf("%s - %s\n", c.command, c.description);
            }
        } else if (input.equals(Commands.QUIT.command)) {
            System.out.println("Goodbye!");
            run = false;
        }

        return run;
    }

    private enum Commands {
        HELP("help", "List all commands"),
        QUIT("quit", "Quit the app."),
        LIST("add student", "List fucks given");

        private String command;
        private String description;

        Commands(String command, String description) {
            this.command = command;
            this.description = description;
        }
    }
}
