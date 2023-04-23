package org.kd.anotherspringtutorial.webapp.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SirThaddeusText {
    private final List<String> lines = new ArrayList<>();

    public SirThaddeusText() {
        String fullText = """
                O Lithuania, my country, thou
                Art like good health; I never knew till now
                How precious, till I lost thee. Now I see
                The beauty whole, because I yearn for thee.
                O Holy Maid, who Czestochowa's shrine
                Dost guard and on the Pointed Gateway shine
                And watchest Nowogrodek's pinnacle!
                As Thou didst heal me by a miracle
                (For when my weeping mother sought Thy power,
                I raised my dying eyes, and in that hour
                My strength returned, and to Thy shrine I trod
                For life restored to offer thanks to God),
                So by a miracle Thou 'lt bring us home.
                Meanwhile, bear off my yearning soul to roam
                Those little wooded hills, those fields beside
                The azure Niemen, spreading green and wide,
                The vari-painted cornfields like a quilt,
                The silver of the rye, the wheatfields' gilt;
                Where amber trefoil, buck-wheat white as snow,
                And clover with her maiden blushes grow,
                And all is girdled with a grassy band
                Of green, whereon the silent pear trees stand.""";

        this.lines.addAll(fullText.lines().collect(Collectors.toList()));
    }

    public String read(int i) {
        return i < lines.size() ? lines.get(i) : "";
    }

    public String readAll() {
        return String.join("\n", lines);
    }
}
