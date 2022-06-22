package com.taurusxin;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Orientation implements ClientModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_NAME = "Critical Orientation Next";

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

    @Override
    public void onInitializeClient() {
        log(Level.INFO, "Initializing");
        new KeyBind();
    }
}
