package com.jonatlop.server.core.abstraction.persistence;

public interface Exists<Query> {
    boolean exists(Query query);
}
