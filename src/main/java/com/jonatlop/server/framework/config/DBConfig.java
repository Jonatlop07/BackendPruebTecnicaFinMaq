package com.jonatlop.server.framework.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.jonatlop.server.data.db.jpa.entity")
@EnableJpaRepositories("com.jonatlop.server.data.db.jpa.repository")
public class DBConfig {}
