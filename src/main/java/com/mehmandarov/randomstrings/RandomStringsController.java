package com.mehmandarov.randomstrings;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 */
@Path("/")
@Singleton
public class RandomStringsController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns the adjective-noun pair",
            description = "The pair of one random adjective and one random noun is returned as an array.")
    @Counted
    public String[] getRandomStrings() {
        return generateRandomStringsPair();
    }

    public String[] generateRandomStringsPair() {
        List<String> adjectives = readFromEnum(Adjectives.values());
        List<String> nouns = readFromEnum(Nouns.values());

        return new String[] {getRandomElement(adjectives), getRandomElement(nouns)};
    }

    private List<String> readFromEnum(Enum<?>[] values) {
        return Arrays.stream(values).map(Enum::name).map(str -> str.replaceAll("_", "-")).collect(Collectors.toList());
    }

    private String getRandomElement(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
