package me.grovre.equivalency.linkFactory;

import me.grovre.equivalency.data.EquivalencyData;
import me.grovre.equivalency.data.For;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Objects;

public class EquivalencyFactory implements OuBaseLinks {

    private static String collegeCode = null;

    // TODO: 3/22/22 Accept SubjectAndCourse list 
    public static EquivalencyData byOuCourse(String stateAbbrev, String subject, String course) {
        String link = FromOuLinkFactory.FromOuLink(stateAbbrev, subject, course);
        System.out.println(link);

        // FIXME: 3/22/22 Search link for verification: https://sis.ou.edu/ted/?home/byOU?&ou_subj_code=ENGR&ou_course=ENGR-2002&stat_code=OK&sbgi_code=000270
        //https://sis.ou.edu/ted/home/byOU?home/byOU?&ou_subj_code=ENGR&ou_course=ENGR-2002&stat_code=OK&sbgi_code=000270
        //Exception in thread "main" java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        //	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        //	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        //	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
        //	at java.base/java.util.Objects.checkIndex(Objects.java:359)
        //	at java.base/java.util.ArrayList.get(ArrayList.java:427)
        //	at me.grovre.equivalency.linkFactory.EquivalencyFactory.byOuCourse(EquivalencyFactory.java:21)
        //	at me.grovre.Main.main(Main.java:32)
        return Objects.requireNonNull(EquivalencyData.createForCollege(For.OU, link)).get(0);
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
