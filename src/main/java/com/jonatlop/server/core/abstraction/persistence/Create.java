package com.jonatlop.server.core.abstraction.persistence;

public interface Create<CreationData, CreatedType> {
    CreatedType create(CreationData creationData);
}
