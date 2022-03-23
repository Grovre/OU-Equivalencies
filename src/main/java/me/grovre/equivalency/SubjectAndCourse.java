package me.grovre.equivalency;

import java.util.Arrays;
import java.util.List;

public class SubjectAndCourse {

    private String subject;
    private String course;

    public SubjectAndCourse(String subjectCourse) {
        String[] splitSubjectCourse = subjectCourse.split("([^A-z].*[^0-9])|([^A-z0-9])");

        this.subject = splitSubjectCourse[0];
        this.subject = this.subject.toUpperCase();

        this.course = splitSubjectCourse[1];
        this.course = this.course.toUpperCase();
    }

    public static List<SubjectAndCourse> SubjectAndCourses(String... subjectCourses) {
        return Arrays.stream(subjectCourses)
                .map(SubjectAndCourse::new)
                .toList();
    }

    public static List<String> getAllSubjects(List<SubjectAndCourse> subjectAndCourses) {
        return subjectAndCourses.stream()
                .map(SubjectAndCourse::getSubject)
                .toList();
    }

    public static List<String> getAllCourses(List<SubjectAndCourse> subjectAndCourses) {
        return subjectAndCourses.stream()
                .map(SubjectAndCourse::getCourse)
                .toList();
    }

    @Override
    public String toString() {
        return this.subject + " " + this.course;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
