package me.grovre.equivalency.data;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OuData extends EquivalencyData {

    private String subjectThere;
    private String courseThere;
    private String titleThere;
    private int hoursThere;
    private String level;
    private int hours;
    private String genEd;

    OuData(Element trClassRow) {
        Elements rowData = trClassRow.children();
        this.subjectThere = rowData.get(4).text();
        this.courseThere = rowData.get(5).text();
        this.titleThere = rowData.get(6).text();
        this.hoursThere = Integer.parseInt(rowData.get(7).text());
        this.level = rowData.get(8).text();
        this.hours = Integer.parseInt(rowData.get(9).text());
        this.genEd = rowData.get(10).text();
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "OuData{" +
                "subjectThere='" + this.subjectThere + '\'' +
                ", courseThere='" + this.courseThere + '\'' +
                ", titleThere='" + this.titleThere + '\'' +
                ", hoursThere=" + this.hoursThere +
                ", level='" + this.level + '\'' +
                ", hours=" + this.hours +
                ", genEd='" + this.genEd + '\'' +
                '}';
    }
}
