package com.jonatlop.server.core.util.moment;

import javax.lang.model.type.NullType;
import java.time.Instant;

public class GetCurrentInstantService implements GetCurrentInstantInteractor {
    @Override
    public Instant execute( NullType input ) {
        return Instant.now();
    }
}
