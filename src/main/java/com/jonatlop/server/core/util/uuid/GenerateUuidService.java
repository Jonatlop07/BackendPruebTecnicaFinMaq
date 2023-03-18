package com.jonatlop.server.core.util.uuid;

import javax.lang.model.type.NullType;
import java.util.UUID;

public class GenerateUuidService implements GenerateUuidInteractor {
    @Override
    public UUID execute(NullType input) {
        return UUID.randomUUID();
    }
}
