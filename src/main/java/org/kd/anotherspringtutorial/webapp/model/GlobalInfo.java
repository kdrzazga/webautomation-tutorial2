package org.kd.anotherspringtutorial.webapp.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class GlobalInfo {

    @Getter
    private final String info = "HELLO\n";
}
