package org.kd.anotherspringtutorial.webapp.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SirThaddeus {
    private final List<String> lines = new ArrayList<>();

    public SirThaddeus() {
        lines.add("O Lithuania, my country, thou");
        lines.add("Art like good health; I never knew till now");
        lines.add("How precious, till I lost thee. Now I see");
        lines.add("The beauty whole, because I yearn for thee.");
        lines.add("O Holy Maid, who Czestochowa's shrine");
        lines.add("Dost guard and on the Pointed Gateway shine");
        lines.add("And watchest Nowogrodek's pinnacle!");
        lines.add("As Thou didst heal me by a miracle");
        lines.add("(For when my weeping mother sought Thy power,");
        lines.add("I raised my dying eyes, and in that hour");
        lines.add("My strength returned, and to Thy shrine I trod");
        lines.add("For life restored to offer thanks to God),");
        lines.add("So by a miracle Thou 'lt bring us home.");
        lines.add("Meanwhile, bear off my yearning soul to roam");
        lines.add("Those little wooded hills, those fields beside");
        lines.add("The azure Niemen, spreading green and wide,");
        lines.add("The vari-painted cornfields like a quilt,");
        lines.add("The silver of the rye, the whetfields' gilt;");
        lines.add("Where amber trefoil, buck-wheat white as snow,");
        lines.add("And clover with her maiden blushes grow,");
        lines.add("And all is girdled with a grassy band");
        lines.add("Of green, whereon the silent peear trees stand.");
    }

    public String read(int i) {
        return i < lines.size() ? lines.get(i) : "";
    }

    public String readAll() {
        return String.join("\n", lines);
    }
}
