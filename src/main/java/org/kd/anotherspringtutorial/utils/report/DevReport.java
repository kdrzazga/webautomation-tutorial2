package org.kd.anotherspringtutorial.utils.report;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DevReport extends AbstractReport {

    @Override
    public String generate() {
        var builder = new StringBuilder();
        builder.append("Report for environment: ")
                .append(this.reportEnv()).append("\n");

        builder.append("Unit tests complete ")
                .append(this.unitTestCount);

        return builder.toString();
    }
}
