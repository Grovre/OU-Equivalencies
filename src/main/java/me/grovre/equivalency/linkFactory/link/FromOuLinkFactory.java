package me.grovre.equivalency.linkFactory.link;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class FromOuLinkFactory implements OuBaseLinks {

    public static String FromOuLinkFactory(String subjectCode, String courseCode, String state) {
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
