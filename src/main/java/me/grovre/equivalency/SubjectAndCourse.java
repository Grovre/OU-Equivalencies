package me.grovre.equivalency;

import java.util.ArrayList;
import java.util.List;

public class SubjectAndCourse {

    private String subject;
    private String course;

    public SubjectAndCourse(String subjectCourse) {
        String[] splitSubjectCourse = subjectCourse.split(" _-");

        this.subject = splitSubjectCourse[0];
        this.subject = this.subject.toUpperCase();

        this.course = splitSubjectCourse[1];
        this.course = this.course.toUpperCase();
    }

    public static List<SubjectAndCourse> SubjectAndCourses(String... subjectCourses) {
        List<SubjectAndCourse> subjectAndCourses = new ArrayList<>(subjectCourses.length);
        for(String subjectCourse : subjectCourses) {
            subjectAndCourses.add(new SubjectAndCourse(subjectCourse));
        }

        return subjectAndCourses;
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
