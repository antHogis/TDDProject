package com.github.anthogis.tddproject;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner scanner;
    static School school;
    static boolean run;
    final static String invalid = "Invalid input!";

    public static void main(String [] args) {
        scanner = new Scanner(System.in);
        run = true;
        school = new School();

        System.out.println("Welcome to School Tycoon 2018!\nType help to see list of commands.");

        while (run) {
            System.out.print("main>");
            handleInput(scanner.nextLine());
        }
    }

    private static void handleInput(String input) {
        boolean validCommand = false;
        for (Commands c : Commands.values()) {
            if (input.equals(c.command)) {
                c.action.run();
                validCommand = true;
                break;
            }
        }

        if (!validCommand) System.out.println(invalid);
    }

    private enum Commands {
        ADD_COURSE("add crs", "Add a course for the school", Commands::addCourse),
        ADD_STUDENT("add stud", "Add student to the school", Commands::addStudent),
        ADD_STUDENT_COURSE("add stud crs", "Add a student to a course", Commands::addStudentToCourse),
        HELP("help", "List all commands", Commands::help),
        LIST_COURSES("list crs", "List courses", Commands::listCourses),
        LIST_STUDENTS("list studs", "List students", Commands::listStudents),
        QUIT("quit", "Quit the app", Commands::quit);

        private String command;
        private String description;
        private Runnable action;

        Commands(String command, String description, Runnable action) {
            this.command = command;
            this.description = description;
            this.action = action;
        }

        static String addMode = "add>";
        static String error = "%s (Reason: %s)\n";
        static String exit = "exit";


        static void help() {
            int len = 0;
            for (Commands c : Commands.values()) {
                if (c.command.length() > len) {
                    len = c.command.length();
                }
            }

            for (Commands c : Commands.values()) {
                StringBuilder indent = new StringBuilder();

                for (int i = c.command.length(); i < len; i++) {
                    indent.append(' ');
                }

                System.out.printf("%s%s - %s\n", c.command, indent.toString(), c.description);
            }
        }

        static void quit() {
            System.out.println("Goodbye!");
            App.run = false;
        }

        static void addStudent() {
            boolean valid = false;
            Person student = new Person();

            System.out.println("Give first name for student");
            while (!valid) try {
                System.out.print(addMode);
                String name = scanner.nextLine();
                student.setFirstName(name);
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.printf(error, invalid, e.getMessage());
            }

            valid = false;
            System.out.println("Give last name for student");
            while (!valid) try {
                System.out.print(addMode);
                String name = scanner.nextLine();
                student.setLastName(name);
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.printf(error, invalid, e.getMessage());
            }

            valid = false;
            System.out.println("Give birth date of student in format YYYY-MM-DD");
            while (!valid) try {
                System.out.print(addMode);
                String date = scanner.nextLine();
                student.setBirthDate(LocalDate.parse(date));
                valid = true;
            } catch (Exception e) {
                System.out.printf(error, invalid, e.getMessage());
            }

            school.addStudent(student);
            System.out.println("Student added!");
        }

        static void listStudents() {
            List<String> students = school.getStudentInfoList();

            for (String student : students) {
                System.out.println(student);
            }

            if (students.size() < 1) {
                System.out.println("There are no students in this school!");
            }
        }

        static void addCourse() {
            if (school.hasMoneyForNewCourse()) {
                boolean valid = false;
                int maxStudents = -1;

                //Prompting for max students of course
                while (!valid) try {
                    System.out.println("Input value for max students for course.");
                    System.out.print(addMode);
                    String input = scanner.nextLine();
                    maxStudents = Integer.parseInt(input);
                    new Course(maxStudents, "Testing");
                    valid = true;
                } catch (IllegalArgumentException e) {
                    System.out.printf(error, invalid, e.getMessage());
                }

                valid = false;
                //Prompting for name of course
                while (!valid) try {
                    System.out.println("Input value for the name of the course.");
                    System.out.print(addMode);
                    String input = scanner.nextLine();
                    school.addCourse(new Course(maxStudents, input));
                    valid = true;
                    System.out.println("Course added!");
                } catch (IllegalArgumentException e) {
                    System.out.printf(error, invalid, e.getMessage());
                }
            } else {
                System.out.println(new InsufficientFundsException().getMessage());
            }
        }

        static void listCourses() {
            List<String> courses = school.getCourseInfoList();

            for (String course : courses) {
                System.out.println(course);
            }

            if (courses.size() < 1) {
                System.out.println("There are no courses in this school!");
            }
        }

        static void addStudentToCourse() {
            if (school.coursesAmount() == 0) {
                System.out.println("There are no courses in this school!");
            } else if (school.studentsAmount() == 0) {
                System.out.println("There are no students in this school!");
            } else {
                int studentNumber = 0;
                int courseNumber = 0;
                boolean valid = false;
                boolean quit = false;

                while (!valid && !quit) try {
                    System.out.println("Input student number. Type list studs for list. Type exit to quit this mode.");
                    System.out.print(addMode);
                    String input = scanner.nextLine();
                    if (input.equals(LIST_STUDENTS.command)) {
                        listStudents();
                    } else if (input.equals(exit)) {
                        quit = true;
                    } else {
                        studentNumber = Integer.parseInt(input);
                        if (studentNumber >= 0 && studentNumber < school.studentsAmount()) {
                            valid = true;
                        } else {
                            throw new IllegalArgumentException(String.format(
                                    "Value not between %d and %d", 0, school.studentsAmount() - 1));
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.out.printf(error, invalid, e.getMessage());
                }

                valid = false;
                while (!valid && !quit) try {
                    System.out.println("Input course number. Type list crs for list. Type exit to quit this mode.");
                    System.out.print(addMode);
                    String input = scanner.nextLine();
                    if (input.equals(LIST_COURSES.command)) {
                        listCourses();
                    } else if (input.equals(exit)) {
                        quit = true;
                    } else {
                        courseNumber = Integer.parseInt(input);
                        if (courseNumber >= 0 && courseNumber < school.coursesAmount()) {
                            school.addStudentToCourse(studentNumber, courseNumber);
                            valid = true;
                        } else {
                            throw new IllegalArgumentException(String.format(
                                    "Value not between %d and %d", 0, school.studentsAmount() - 1));
                        }
                    }

                } catch (IllegalArgumentException e) {
                    System.out.printf(error, invalid, e.getMessage());
                }
            }
        }
    }
}
