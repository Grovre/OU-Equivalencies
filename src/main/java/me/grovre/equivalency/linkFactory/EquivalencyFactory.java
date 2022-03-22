package me.grovre.equivalency.linkFactory;

import me.grovre.equivalency.EquivalencyData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class EquivalencyFactory implements OuBaseLinks {

    // TODO: 3/22/22 Accept SubjectAndCourse list 
    public static EquivalencyData byOuCourses(String stateAbbrev, String subject, String course) {
        String link = FromOuLinkFactory.FromOuLink(stateAbbrev, subject, course);

        System.out.println(link);

        // TODO: 3/22/22 Create equivalencyData from link(s) 

        return null;
    }

    private static class FromOuLinkFactory {

        // TODO: 3/22/22 Merge into EquivalencyFactory
        public static String FromOuLink(String state, String subjectCode, String courseCode) {
            assert subjectCode != null && subjectCode.length() > 0;
            assert courseCode != null && courseCode.length() > 0;
            assert state != null && state.length() == 2;

            StringBuilder linkBody = new StringBuilder(OuBaseLinks.OuSearchFinalHead);

            subjectCode = subjectCode.replaceAll("[ _-]", "+");
            linkBody.append("&ou_subj_code=").append(subjectCode);

            courseCode = courseCode.toUpperCase();
            linkBody.append("&ou_course=").append(subjectCode).append("-").append(courseCode.toUpperCase());

            linkBody.append("&stat_code=").append(state.toUpperCase());

            Element collegeElement = null;
            try {
                Connection connection = Jsoup.connect(base + OuSearchSearchHead + linkBody.toString());
                assert connection != null;
                collegeElement = connection.get()
                        .getElementById("ouSearch")
                        .getElementById("sbgi_code");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String chosenCollegeSbgi = OuBaseLinks.selectCollege(collegeElement);
            linkBody.append("&sbgi_code=").append(chosenCollegeSbgi);

            String linkBod = linkBody.toString();
            System.out.println("Search link for verification: " + base + OuSearchSearchHead + linkBod);
            return base + OuSearchFinalHead + linkBod;
        }
    }

    // TODO: 3/22/22 Allow checking from another college
    private static class FromOtherLinkFactory {

    }
}
