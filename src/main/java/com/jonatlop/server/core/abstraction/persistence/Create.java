package com.jonatlop.server.core.abstraction.persistence;

public interface Create<Payload, Response> {
    Response create(Payload payload);
}
