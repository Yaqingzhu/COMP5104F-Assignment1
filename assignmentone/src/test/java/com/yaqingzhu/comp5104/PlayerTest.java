package com.yaqingzhu.comp5104;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
	/**
	 * This test make sure each will not have score < 0.
	 */
	@Test
	public void testPlayerScore() {
		Player player = new Player();
		player.setScore(-100);
		
		Assert.assertEquals(0, player.getScore());
	}
	
	/**
	 * This test make sure each will not have score < 0.
	 */
	@Test
	public void testPlayerrollDiceWithOneDice() {
		Player player = new Player();
		
		Assert.assertEquals(true, player.rollDice("1").contains("you have to roll at least 2 dices"));
	}
	
	/**
	 * This test make you can use SorceressCard.
	 */
	@Test
	public void testPlayerrollDiceSorceress() {
		Player player = new Player();
		
		player.getRound().setActiveCard(new SorceressCard());
		player.getRound().getDice().get(0).setLastResult("Skull");
		player.getRound().getDice().get(1).setLastResult("Sword");
		player.getRound().getDice().get(2).setLastResult("Sword");
		player.getRound().getDice().get(3).setLastResult("Sword");
		player.getRound().getDice().get(4).setLastResult("Sword");
		player.getRound().getDice().get(5).setLastResult("Sword");
		player.getRound().getDice().get(6).setLastResult("Monkey");
		player.getRound().getDice().get(7).setLastResult("Sword");
		player.getRound().checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		player.getRound().calcRoundScore();
		
		Assert.assertEquals(true, player.rollDice("6,7").contains("You have Sorceress card and you can reroll one"));
	}
	
	/**
	 * This test make sure you can get correct for one round.
	 */
	@Test
	public void testPlayerScoreRound() {
		Player player = new Player();
		
		player.setScore(100);
		player.getRound().setActiveCard(new SorceressCard());
		player.getRound().getDice().get(0).setLastResult("Sword");
		player.getRound().getDice().get(1).setLastResult("Sword");
		player.getRound().getDice().get(2).setLastResult("Sword");
		player.getRound().getDice().get(3).setLastResult("Sword");
		player.getRound().getDice().get(4).setLastResult("Sword");
		player.getRound().getDice().get(5).setLastResult("Sword");
		player.getRound().getDice().get(6).setLastResult("Monkey");
		player.getRound().getDice().get(7).setLastResult("Sword");
		player.getRound().checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		player.getRound().calcRoundScore();
		player.processInput(2,"");
		
		Assert.assertEquals(2100, player.getScore());
	}
}
