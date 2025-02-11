package com.microservice.util;

import org.apache.coyote.BadRequestException;

public class IdsValidator {

    public static void validate(String ids) throws BadRequestException {
        if (ids == null || ids.isEmpty()) {
            throw new BadRequestException("ID list should not be empty");
        }
        if (ids.length() >= 200) {
            throw new BadRequestException(
                    String.format("IDs string too long. Received %s characters, but must be less than 200 characters.", ids.length()));
        }
        String[] split = ids.split(",");
        for (String id : split) {
            if (!id.matches("\\d+")) {
                throw new BadRequestException("Invalid id: " + id);
            }
        }
    }
}
