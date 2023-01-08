package org.kd.anotherspringtutorial.utils.report;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("qa")
@Component
public class QaReport extends AbstractReport {

    private int systemIntTestCount = 5;
    private int systemTestCount = 1;

    public QaReport() {
        this.unitTestCount = 12;
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for environment: " + this.reportEnv() + "\n");
        builder.append("Unit tests complete " + this.unitTestCount + "\n");
        builder.append("System Integration tests complete " + this.systemIntTestCount + "\n");
        builder.append("System tests complete " + this.systemTestCount + "\n");

        return builder.toString();
    }
}
