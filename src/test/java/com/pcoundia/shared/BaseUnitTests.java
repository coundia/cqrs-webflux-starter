package com.pcoundia.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest(
properties = "spring.config.name=application-test"
)
@Profile("test")
public class BaseUnitTests {

}
