package com.jsharper.userservice.services;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.util.StreamUtils;
import org.springframework.stereotype.Service;

@Service
public class DataSetupService implements CommandLineRunner {
	@Value("classpath:h2/init.sql")
	private Resource initSql;

	@Autowired
	private R2dbcEntityTemplate entityTemplate;

	@Override
	public void run(String... args) throws Exception {
		String query = StreamUtils.copyToString(initSql.getInputStream(), StandardCharsets.UTF_8);
		this.entityTemplate.getDatabaseClient().sql(query).then().subscribe();
	}

}
