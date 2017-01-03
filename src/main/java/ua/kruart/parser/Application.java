package ua.kruart.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Arthur on 03.01.2017.
 */
public class Application {
    public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.info("Hello");
        LOGGER.debug("Hello");
    }
}
