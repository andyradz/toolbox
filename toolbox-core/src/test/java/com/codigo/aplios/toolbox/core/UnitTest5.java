package com.codigo.aplios.toolbox.core;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author andrzej.radziszewski
 */
@DisplayName(value = "Test bez znaczenia")
public class UnitTest5 {

	@BeforeAll
	public static void initAll() {

	}

	@BeforeEach
	public void initEach() {

	}

	@Test
	public void test1() {

		Assertions.assertEquals(2, 1 + 1);
	}

	@Test
	public void test2() {

		MatcherAssert.assertThat(2 + 1, CoreMatchers.is(CoreMatchers.equalTo(3)));
	}

	@Test
	void testOnlyOnCiServer() {

		Assumptions.assumeTrue("CI".equals(System.getenv("ENV")));
		// remainder of test
	}

	@Test
	void testOnlyOnDeveloperWorkstation() {

		Assumptions.assumeTrue("DEV".equals(System.getenv("ENV")), () -> "Aborting test: not on developer workstation");
		// remainder of test
	}

	@Test
	void testInAllEnvironments() {

		Assumptions.assumingThat("CI".equals(System.getenv("ENV")), () -> {
			// perform these assertions only on the CI server
			Assertions.assertEquals(2, 21);
		});

		// perform these assertions in all environments
		Assertions.assertEquals("a string", "a string");
	}
}
