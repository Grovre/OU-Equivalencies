package me.grovre;

import me.grovre.equivalency.linkFactory.EquivalencyFactory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter state ex. OK/NY: ");
        String state = scanner.nextLine();
        System.out.println("Enter courses and subjects ex. MATH 1914, MATH-1914, or MATH_1914");
        String[] subjectCourse = scanner.nextLine().split("[ _-]");
        String subject = subjectCourse[0];
        String course = subjectCourse[1];
        EquivalencyFactory.byOuCourses(state, subject, course);
    }
}
