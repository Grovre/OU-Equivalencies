package me.grovre;

import me.grovre.equivalency.SubjectAndCourse;
import me.grovre.equivalency.data.EquivalencyData;
import me.grovre.equivalency.linkFactory.EquivalencyFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter state ex. OK/NY: ");
        String state = scanner.nextLine();
        System.out.println("Enter courses and subjects ex. MATH 1914, MATH-1914, or MATH_1914");
        List<SubjectAndCourse> subjectsAndCourses = new ArrayList<>();
        String subjectCourse = "-1";
        System.out.println("Enter -1 to stop.");

        do {
            subjectCourse = scanner.nextLine();
            if(subjectCourse.equals("-1")) break;
            SubjectAndCourse subjectAndCourse = new SubjectAndCourse(subjectCourse);
            subjectsAndCourses.add(subjectAndCourse);
            System.out.println("Entered " + subjectAndCourse);
        } while(true);

        List<EquivalencyData> data = new ArrayList<>(subjectsAndCourses.size());
        for(var s : subjectsAndCourses) {
            List<EquivalencyData> dat = EquivalencyFactory.byOuCourse(state, s.getSubject(), s.getCourse());
            data.addAll(dat);
        }
        System.out.println(data);
    }
}
