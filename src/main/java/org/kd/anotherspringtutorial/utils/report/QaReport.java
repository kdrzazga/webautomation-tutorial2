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
        builder.append("Report for environment: ").append(this.reportEnv()).append("\n").append("Number of PASSED tests: ").append(Stats.passedTcCount).append("Number of FAILED tests: ").append(Stats.failedTcCount);

        builder.append("Unit tests complete ").append(this.unitTestCount).append("\n");
        builder.append("System Integration tests complete ").append(this.systemIntTestCount).append("\n");
        builder.append("System tests complete ").append(this.systemTestCount).append("\n");

        return builder.toString();
    }
}
