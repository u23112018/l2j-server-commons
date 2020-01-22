/*
 * Copyright © 2004-2020 L2J Server
 * 
 * This file is part of L2J Server.
 * 
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.commons;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.l2jserver.commons.util.Util;

/**
 * Util test.
 * @author Zoey76
 * @version 2.6.1.0
 */
public class UtilTest {
	private static final String PROVIDE_ARGS = "PROVIDE_ARGS";
	
	private static final String PROVIDE_ARGS_FAIL = "PROVIDE_ARGS_FAIL";
	
	private static final String IGNORE_QUESTS = "-noquest";
	
	private static final String DP = "-dp";
	
	private static final String DP_PATH = "../../../L2J_DataPack/dist/game";
	
	@Test(dataProvider = PROVIDE_ARGS)
	public void testParseArg(String[] args, String arg, boolean hasArgValue, String expected) {
		Assert.assertEquals(Util.parseArg(args, arg, hasArgValue), expected);
	}
	
	@Test(dataProvider = PROVIDE_ARGS_FAIL, expectedExceptions = IllegalArgumentException.class)
	public void testParseArgFail(String[] args, String arg, boolean hasArgValue) {
		Util.parseArg(args, arg, hasArgValue);
	}
	
	@DataProvider(name = PROVIDE_ARGS)
	private Iterator<Object[]> provideArgs() {
		final List<Object[]> result = new LinkedList<>();
		// @formatter:off
		result.add(new Object[] { null, null, false, null });
		result.add(new Object[] { new String[] { }, IGNORE_QUESTS, false, null });
		result.add(new Object[] { new String[] { IGNORE_QUESTS }, null, false, null });
		result.add(new Object[] { new String[] { IGNORE_QUESTS }, "", false, null });
		result.add(new Object[] { new String[] { DP, DP_PATH }, DP, true, DP_PATH });
		result.add(new Object[] { new String[] { IGNORE_QUESTS }, IGNORE_QUESTS, false, IGNORE_QUESTS });
		result.add(new Object[] { new String[] { IGNORE_QUESTS, DP, DP_PATH }, DP, true, DP_PATH });
		// @formatter:on
		return result.iterator();
	}
	
	@DataProvider(name = PROVIDE_ARGS_FAIL)
	private Iterator<Object[]> provideArgsFail() {
		// @formatter:off
		return Collections.singletonList(new Object[] { new String[] { IGNORE_QUESTS }, IGNORE_QUESTS, true }).iterator();
		// @formatter:on
	}
}
