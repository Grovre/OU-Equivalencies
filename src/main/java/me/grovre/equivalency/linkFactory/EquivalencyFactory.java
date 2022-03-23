package me.grovre.equivalency.linkFactory;

import me.grovre.equivalency.data.EquivalencyData;
import me.grovre.equivalency.data.For;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class EquivalencyFactory implements OuBaseLinks {

    private static String collegeCode = null;

    // TODO: 3/22/22 Accept SubjectAndCourse list 
    public static List<EquivalencyData> byOuCourse(String stateAbbrev, String subject, String course) {
        String link = FromOuLinkFactory.FromOuLink(stateAbbrev, subject, course);
        System.out.println(link);

        return Objects.requireNonNull(EquivalencyData.createForCollege(For.OU, link));
    }

    private static class FromOuLinkFactory {

        // TODO: 3/22/22 Merge into EquivalencyFactory
        public static String FromOuLink(String state, String subjectCode, String courseCode) {
            assert subjectCode != null && subjectCode.length() > 0;
            assert courseCode != null && courseCode.length() > 0;
            assert state != null && state.length() == 2;

            StringBuilder linkBody = new StringBuilder(OuBaseLinks.OuSearchFinalHead);

            subjectCode = subjectCode.replaceAll("[ _-]", "+");
            subjectCode = subjectCode.toUpperCase();
            linkBody.append("&ou_subj_code=").append(subjectCode);

            courseCode = courseCode.toUpperCase();
            linkBody.append("&ou_course=").append(subjectCode).append("-").append(courseCode.toUpperCase());

            linkBody.append("&stat_code=").append(state.toUpperCase());

            String chosenCollegeSbgi;
            if(collegeCode == null) {
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
                chosenCollegeSbgi = OuBaseLinks.selectCollege(collegeElement);
                System.out.println(chosenCollegeSbgi);
                collegeCode = chosenCollegeSbgi;
            } else {
                chosenCollegeSbgi = collegeCode;
            }
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
