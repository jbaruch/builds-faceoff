package org.javaone.buildfaceoff.gradle;

import org.javaone.buildfaceoff.AppC;

/**
 * @author jbaruch
 * @since 9/16/16
 */
public class LibApp {

    public String getLibC(){
        return new AppC().getStrV1();
    }
}
