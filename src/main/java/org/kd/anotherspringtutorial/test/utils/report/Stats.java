package org.kd.anotherspringtutorial.test.utils.report;

public class Stats {

    protected static int failedTcCount = 0;
    protected static int passedTcCount = 0;

    public static void addFailedTC() {
        failedTcCount++;
    }

    public static void addPassedTC() {
        passedTcCount++;
    }

    public static int getFailedTcCount() {
        return failedTcCount;
    }

    public static int getPassedTcCount() {
        return passedTcCount;
    }
}
