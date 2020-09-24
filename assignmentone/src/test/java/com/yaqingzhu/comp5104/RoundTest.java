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

}
