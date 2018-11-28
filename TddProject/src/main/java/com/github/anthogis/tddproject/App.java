package com.github.anthogis.tddproject;

import java.util.Scanner;
import java.util.function.Consumer;

public class App {
    static Scanner scanner;
    static LearningInstitution school;
    static boolean run;

    public static void main(String [] args) {
        scanner = new Scanner(System.in);
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
                c.consumer.accept(input);
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
        private Consumer<String> consumer;

        Commands(String command, String description, Consumer<String> consumer) {
            this.command = command;
            this.description = description;
            this.consumer = consumer;
        }

        static void help(String input) {
            for (Commands c : Commands.values()) {
                System.out.printf("%s - %s\n", c.command, c.description);
            }
        }

        static void quit(String input) {
            System.out.println("Goodbye!");
            App.run = false;
        }

        static void addStudent(String input) {


        }
    }
}
