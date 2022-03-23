package me.grovre.equivalency.data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class EquivalencyData {

    public static List<EquivalencyData> createForCollege(For forOtherOrOu, String... link) {
        assert link.length > 0;
        List<EquivalencyData> datas = new ArrayList<>(link.length);
        if(forOtherOrOu == For.OU) {
            for(int i = 0; i < link.length; i++) {
                Connection con = Jsoup.connect(link[i]);
                EquivalencyData data = null;
                Elements rows = null;
                try {
                    rows = Objects.requireNonNull(con.get()
                                    .getElementById("ouResults"))
                            .getElementsByAttributeValueStarting("class", "row");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert rows != null;
                for(Element row : rows) {
                    data = new OuData(row);
                    datas.add(data);
                }
            }

            return datas;

            // TODO: 3/22/22 Complete OtherData factory
        } else if(forOtherOrOu == For.OTHER) {
            for(int i = 0; i < link.length; i++) {

            }
        }

        return null;
    }
}

