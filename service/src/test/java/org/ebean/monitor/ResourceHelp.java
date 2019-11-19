package org.ebean.monitor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ebean.migration.util.IOUtils;
import org.ebean.monitor.api.MetricRequest;
import org.ebean.monitor.config.ObjectMapperConfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

/**
 * Utility to read test resources.
 */
public class ResourceHelp {

    private static final ObjectMapper mapper = ObjectMapperConfig.get();

    public static MetricRequest metricRequest(String resourcePath) {
        final String json1 = read(resourcePath);
        try {
            return mapper.readValue(json1, MetricRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read the content for the given resource path.
     */
    public static String read(String resourcePath) {
        InputStream is = ResourceHelp.class.getResourceAsStream(resourcePath);
        try {
            return IOUtils.readUtf8(is).trim();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String readFile(String path) {
        try {
            List<String> lines = Files.readAllLines(new File(path).toPath());
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    /**
//     * Convert the given object to JSON using Jackson.
//     */
//    public static String asJson(Object value) {
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapperConfig().objectMapper();
//            return objectMapper.writeValueAsString(value);
//
//        } catch (JsonProcessingException e) {
//            throw new IllegalArgumentException(e);
//        }
//    }
}
