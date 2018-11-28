package com.github.anthogis.tddproject;

import java.util.Scanner;

public class App {
    static LearningInstitution school;
    static boolean run;

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        run = true;
        school = new LearningInstitution();

        System.out.println("Welcome!\nType help to see list of commands.");

        while (run) {
            System.out.print('>');
            handleInput(scanner.nextLine());
        }
    }

    private static void handleInput(String input) {
        boolean validCommand = false;
        for (Commands c : Commands.values()) {
            if (input.equals(c.command)) {
                c.runnable.run();
                validCommand = true;
            }
        }

        if (!validCommand) System.out.println("Invalid input!");
    }

    private enum Commands {
        HELP("help", "List all commands", Commands::help),
        QUIT("quit", "Quit the app.", Commands::quit),
        ADD_STUDENT("add student", "Add student to the school", Commands::addStudent);

        private String command;
        private String description;
        private Runnable runnable;

        Commands(String command, String description, Runnable runnable) {
            this.command = command;
            this.description = description;
            this.runnable = runnable;
        }

        static void help() {
            for (Commands c : Commands.values()) {
                System.out.printf("%s - %s\n", c.command, c.description);
            }
        }

        static void quit() {
            App.run = false;
        }

        static void addStudent() {

        }
    }
}
