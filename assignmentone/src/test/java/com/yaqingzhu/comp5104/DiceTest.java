package com.yaqingzhu.comp5104;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

	
	/**
	 * The test case to test if roll the dice without using any cards can
	 * get result with in the 6 possibilities.
	 */
	@Test
	public void testRollingWithoutCards() {
		ArrayList<String> diceRange = new ArrayList<String>();
		Dice dice = new Dice();
		diceRange.add("Skull");
		diceRange.add("Diamond");
		diceRange.add("Gold");
		diceRange.add("Monkey");
		diceRange.add("Parrot");
		diceRange.add("Sword");
		
		Assert.assertEquals(true, diceRange.contains(dice.roll()));
	}
	
	/**
	 * The test case to test if roll the dice with Skulls card, the result will
	 * be always Skull.
	 */
	@Test
	public void testRollingWithSkullsCard() {
		Dice dice = new Dice();
		Card card = new SkullsCard();
		Assert.assertEquals("Skull", dice.roll(card));
	}
	
	/**
	 * The test case to test if roll the dice with MonkeyBusiness card, the result will
	 * never be Parrot because all Parrot will be convert to Monkey.
	 */
	
	@Test
	public void testRollingWithMonkeyBusinessCard() {
		Dice dice = new Dice();
		Card card = new MonkeyBusinessCard();
		Assert.assertEquals(false, dice.roll(card).equals("Parrot") );
	}

}
