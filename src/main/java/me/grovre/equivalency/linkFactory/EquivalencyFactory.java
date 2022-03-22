package me.grovre.equivalency.linkFactory;

import me.grovre.equivalency.EquivalencyData;
import me.grovre.equivalency.linkFactory.link.FromOuLinkFactory;

public class EquivalencyFactory {

    // search by other institution scrape link
    // https://sis.ou.edu/ted/?stat_code=AK&sbgi_code=004201&trns_subj_code=AC&trns_subj_crse=AC-470&trns_crse_numb=470

    // search by ou scrape link
    // https://sis.ou.edu/ted/?ou_subj_code=A+HI&ou_course=A+HI-1113&stat_code=AL&sbgi_code=UAL001
    // https://sis.ou.edu/ted/?ou_course=A+HI-1113&ou_subj_code=A+HI&stat_code=AL&sbgi_code=UAL001

    // TODO: 3/22/22 Accept SubjectAndCourse list 
    public static EquivalencyData byOuCourses(String subject, String course, String stateAbbrev) {
        String link = FromOuLinkFactory.FromOuLinkFactory(subject, course, stateAbbrev);

        System.out.println(link);

        return null;
    }


}
