package org.kd.anotherspringtutorial.webapp.controller;

import org.kd.anotherspringtutorial.webapp.model.GlobalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalInfoController {

    @Autowired
    private GlobalInfo globalInfo;

    @RequestMapping(path = "/")
    public String printInfo() {
        var header = "<!DOCTYPE html>\n<html>\n<body>";
        var info = globalInfo.getInfo();
        info = info.replaceAll("\n", "<br/>");
        var footer = "\n</body>\n</html>";

        return header + info + footer;
    }
}
