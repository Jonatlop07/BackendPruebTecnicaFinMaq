package com.jonatlop.server.core.abstraction.persistence;

import java.util.Optional;
import java.util.UUID;

public interface Query<DetailsDTO> {
    Optional<DetailsDTO> queryById(UUID id);
}
