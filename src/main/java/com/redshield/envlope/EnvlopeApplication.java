package com.redshield.envlope;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@MapperScan({"com.redshield.licence.licmgr.mapper"})
@SpringBootApplication
public class EnvlopeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnvlopeApplication.class, args);
	}

}
