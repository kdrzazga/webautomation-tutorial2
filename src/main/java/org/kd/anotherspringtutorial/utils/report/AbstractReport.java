package org.kd.anotherspringtutorial.utils.report;

import org.kd.anotherspringtutorial.config.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;

abstract class AbstractReport implements Report {

    protected int unitTestCount = 4;
    @Autowired
    private ProfileManager profileManager;

    @Override
    public String reportEnv() {
        return "Current environment is " + profileManager.getActiveProfiles();
    }
}
