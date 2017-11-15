/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.xbase;

import static junit.framework.TestCase.assertTrue;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author andrzej.radziszewski
 */
public class Test1 {

	@Test
	@DisplayName(value = "ziko")
	@Category(value = ImportantTest.class)
	public void shouldTest1() {
		assertTrue(200 == 100 + 100);
	}
}

interface ImportantTest {

}
