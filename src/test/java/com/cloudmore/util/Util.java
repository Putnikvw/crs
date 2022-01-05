package com.cloudmore.util;

import com.cloudmore.exception.ResourceFileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

import static org.apache.tomcat.util.http.fileupload.util.Streams.asString;

/**
 * Util
 *
 * @author zen
 */
public class Util {

    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

    public static String readResource(String path) {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(path);
        try {
            return asString(resource.getInputStream());
        } catch (IOException e) {
            LOG.error("Resource file not found");
            throw new ResourceFileNotFoundException("Resource file not found");

        }
    }
}
