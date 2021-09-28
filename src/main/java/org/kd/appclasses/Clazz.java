package org.kd.appclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Clazz extends BaseClazz {

    private String port;

    @Autowired
    public Clazz(@Value("${port}") String port) {
        this.port = port;
    }

    public String info() {
        return "info";
    }

    public String getPort() {
        return port;
    }
}
