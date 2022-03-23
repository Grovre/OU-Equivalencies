package me.grovre.equivalency.linkFactory;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public interface OuBaseLinks {

    String base = "https://sis.ou.edu/ted/";
    String OuSearchFinalHead = "home/byOU?";
    String OuSearchSearchHead = "?";
    String OuSearchLink = "https://sis.ou.edu/ted/?ou_subj_code=A+HI&ou_course=A+HI-1113&stat_code=AL&sbgi_code=UAL001";
    String OtherSearchLink = "https://sis.ou.edu/ted/?stat_code=AK&sbgi_code=004201&trns_subj_code=AC&trns_subj_crse=AC-470&trns_crse_numb=470";

    static int proposeSelection(List<? extends Object> proposals) {
        int selection = 0;
        boolean error;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < proposals.size(); i++) {
            var o = proposals.get(i);
            System.out.println((i) + ": " + o.toString());
        }
        do {
            error = false;
            System.out.println("Please choose a number: ");
            try {
                selection = scanner.nextInt();
                assert 0 <= selection && selection < proposals.size();
            } catch (Exception e) {
                error = true;
                System.out.printf("Please enter a valid number, 1 - %d%n", proposals.size());
            }
        } while(error);

        return selection;
    }

    static Map<String, String> getCollegeList(Element sbgiCodesParentElement) {
        Elements children = sbgiCodesParentElement.children();
        Map<String, String> collegeList = new HashMap<>(children.size()-1);
        for(Element el : children) {
            String college = el.text();
            if(college.contains("--")) continue;
            String collegeCode = el.attr("value");
            collegeList.put(college, collegeCode);
        }

        return collegeList;
    }

    static String selectCollege(Element sbgiCodesParentElement) {
        Map<String, String> collegeMap = getCollegeList(sbgiCodesParentElement);
        List<String> collegeNameList = new ArrayList<>(collegeMap.keySet());
        collegeNameList.sort(Comparator.naturalOrder());

        String collegeCode;
        do {
            String chosenCollege = collegeNameList.get(proposeSelection(collegeNameList));
            collegeCode = collegeMap.get(chosenCollege);
            if(collegeCode == null) {
                System.out.println("College is not valid.");
            }
        } while(collegeCode == null);

        return collegeCode;
    }
}
