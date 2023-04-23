package org.kd.anotherspringtutorial.test.utils.report;

import org.kd.anotherspringtutorial.test.config.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;

abstract sealed class AbstractReport permits DevReport, QaReport {

    protected int unitTestCount = 4;

    @Autowired
    private ProfileManager profileManager;

    public String reportEnv() {
        return "Current environment is " + profileManager.getActiveProfiles();
    }

}
