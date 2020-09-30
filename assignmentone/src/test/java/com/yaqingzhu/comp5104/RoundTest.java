package com.yaqingzhu.comp5104;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class RoundTest {

	/**
	 * This test make sure each new round, play get 8 dice at the begining.
	 */
	@Test
	public void testInitRound() {
		Round round = new Round();
		Assert.assertEquals(8, round.getDice().size());
	}
	
	/**
	 * This test will test the code can calculate correct score for sets with out card.
	 * This will not test diamonds and golds extra points and full chest points.
	 */
	@Test
	public void testCountSetScoreWithoutCard() {
		Round round = new Round();
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		results.put("Diamond", 2);
		results.put("Gold", 2);
		results.put("Monkey", 4);
		round.countSetScore(results);
		Assert.assertEquals(200, round.getScoreOfRound());
	}
	
	/**
	 * This test will test the code can calculate correct score for sets with card(diamond and gold card).
	 * This will not test diamonds and golds extra points and full chest points.
	 */
	@Test
	public void testCountSetScoreWithCard() {
		Round round = new Round();
		round.setActiveCard(new DiamondCard());
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		results.put("Diamond", 2);
		results.put("Gold", 2);
		results.put("Monkey", 4);
		round.countSetScore(results);
		Assert.assertEquals(300, round.getScoreOfRound());
	}
	
	/**
	 * This test will test the code can calculate correct score for sets with card(diamond and gold card) and diamonds and golds extra points.
	 * This will not test full chest points.
	 */
	@Test
	public void testCountSetScoreWithCardAndDiamondsGolds() {
		Round round = new Round();
		round.setActiveCard(new DiamondCard());
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		results.put("Diamond", 2);
		results.put("Gold", 2);
		results.put("Monkey", 4);
		round.countSetScore(results);
		round.countDiamondAndGold(results);
		Assert.assertEquals(800, round.getScoreOfRound());
	}
	
	/**
	 * This test will test the code can calculate correct score for sets with card(diamond and gold card) and diamonds and golds extra points.
	 */
	@Test
	public void testCountSetScoreWithCardAndDiamondsGoldsAndFullChest() {
		Round round = new Round();
		round.setActiveCard(new DiamondCard());
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		results.put("Diamond", 2);
		results.put("Gold", 2);
		results.put("Monkey", 4);
		round.countSetScore(results);
		round.countDiamondAndGold(results);
		round.countFullChestScore(results);
		Assert.assertEquals(1300, round.getScoreOfRound());
		
		round = new Round();
		round.setActiveCard(new DiamondCard());
		results = new HashMap<String, Integer>();
		results.put("Diamond", 2);
		results.put("Gold", 1);
		results.put("Sword", 1);
		results.put("Monkey", 4);
		round.countSetScore(results);
		round.countDiamondAndGold(results);
		round.countFullChestScore(results);
		Assert.assertEquals(700, round.getScoreOfRound());
	}
	
	/**
	 * This test will test the code that re-roll part of dices will keep all values of not-re-rolled dices.
	 */
	@Test
	public void testrollAllDice() {
		Round round = new Round();
		String[] before = null;
		String[] after = null;
		round.processWithoutSeaBattle("1,2,3,4,5,6,7,8");
		before = round.showResult().split(",");
		round.processWithoutSeaBattle("1,2,3");
		after = round.showResult().split(",");
		
		for(int i = 3; i < before.length; i++) {
			Assert.assertEquals(before[i], after[i]);
		}
		
	}
	
	/**
	 * This test will test the code that end this turn. There are two cases: 1. If the Fortune card is not sea battle, you show get 3 skulls.
	 * 2. If the FC is sea battle, as long as skulls are >=3, you die.
	 */
	@Test
	public void testisToEndRound() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardTwo());
		round.setSkullNumber(4);
		Assert.assertEquals(true, round.isToEndRound());
		
		round = new Round();
		round.setSkullNumber(4);
		Assert.assertEquals(false, round.isToEndRound());
		round.setSkullNumber(3);
		Assert.assertEquals(true, round.isToEndRound());
		
	}
	
	/**
	 * This test will test the code only when you have Sorceress card, you can reroll one skull dice.
	 */
	@Test
	public void testrerollSkullWithSorceressCard() {
		String diceNum = null;
		Round round = new Round();
		round.setActiveCard(new SorceressCard());
		round.getDice().get(0).setLastResult("Skull");
		round.setSkullNumber(1);
		diceNum = round.rerollableSkullDice();
		
		Assert.assertEquals(diceNum, "1");
		Assert.assertEquals(0, round.getSkullNumber());
		Assert.assertEquals(null, round.getActiveCard());
	}
	
	/**
	 * This test will test the code to see if can know this player should go to island or not.
	 */
	@Test
	public void testIsWentToSkullIsland() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardTwo());
		
		Assert.assertEquals(false, round.isWentToSkullIsland());

		round.setActiveCard(new SorceressCard());
		round.setSkullNumber(1);
		Assert.assertEquals(false, round.isWentToSkullIsland());
		
		round.setActiveCard(new SorceressCard());
		round.setSkullNumber(4);
		Assert.assertEquals(true, round.isWentToSkullIsland());
	}
	
	/**
	 * This test will test the code to see if Monkeybusiness card works.
	 */
	@Test
	public void testMonkeyBusiness() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardTwo());
		
		Assert.assertEquals("Parrot", round.checkMonkeyBusiness("Parrot"));

		round.setActiveCard(new MonkeyBusinessCard());
		Assert.assertEquals("Monkey", round.checkMonkeyBusiness("Parrot"));
	}
	
	/**
	 * This test will test the code to see if Skull island works.
	 */
	@Test
	public void testSkullIsland() {
		Round round = new Round();
		round.setSkullNumber(4);
		round.countSkullIslandScore();
		
		Assert.assertEquals(400, round.getScoreOfSkullIsland());
		Assert.assertEquals(0, round.getScoreOfRound());
	}
	
	/**
	 * This test will test the code to see if Skull Island works with Captain Card.
	 */
	@Test
	public void testSkullIslandWithZeroSkullOfRoll() {
		Round round = new Round();
		round.getDice().get(0).setLastResult("Sword");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Skull");
		round.getDice().get(4).setLastResult("Skull");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		Assert.assertEquals(false, round.isToEndSkullIsland());

		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Parrot");
		round.checkSkullsOfRoll("7,8");
		
		Assert.assertEquals(true, round.isToEndSkullIsland());
	}
	
	/**
	 * This test will test the code to see if Sea battle works.
	 */
	@Test
	public void testSeaBattleActive() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardTwo());
		
		Assert.assertEquals(true, round.isSeaBattleActive());
	}
	
	/**
	 * This test will test the code to see if Sea battle works.
	 */
	@Test
	public void testSeaBattleScore() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardTwo());
		round.getDice().get(0).setLastResult("Sword");
		round.getDice().get(1).setLastResult("Sword");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.calcRoundScore();
		Assert.assertEquals(true, round.isSeaBattleActive());
		
		Assert.assertEquals(500, round.getScoreOfRound());
	}
}
