package com.yaqingzhu.comp5104;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class GameServerTest {

	/**
	 * This test game server can know who win the game.
	 */
	@Test
	public void testGameServerWin() {
		GameServer gameServer = new GameServer();
		gameServer.getPlayerPool().add(new PlayerThread());

        gameServer.getPlayerPool().get(0).getPlayer().setScore(7000);
		
		Assert.assertEquals(7000, gameServer.getPlayerPool().get(0).getPlayer().getScore());
	}
}
