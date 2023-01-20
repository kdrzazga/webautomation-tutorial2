package org.kd.anotherspringtutorial.webapp.common;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class GlobalInfo {

    @Getter
    private final String info = "HELLO\n";
}
