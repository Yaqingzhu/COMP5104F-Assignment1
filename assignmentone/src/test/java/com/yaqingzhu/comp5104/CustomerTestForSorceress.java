package com.yaqingzhu.comp5104;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTestForSorceress {

	/**
	 * roll 2 skulls, reroll one of them due to sorceress, then go to next round of turn        
	 */
	@Test
	public void customerTest1Row80() {
		Round round = new Round();
		round.setActiveCard(new SorceressCard());
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		Assert.assertEquals("1", round.rerollableSkullDice());	
		Assert.assertEquals(1, round.getSkullNumber());
	}
	
	/**
	 * roll no skulls, then next round roll 1 skull and reroll for it, then score         
	 */
	@Test
	public void customerTest2Row81() {
		Round round = new Round();
		round.setActiveCard(new SorceressCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(null, round.rerollableSkullDice());	
		Assert.assertEquals(200, round.getScoreOfRound());
		Assert.assertEquals(0, round.getSkullNumber());
		
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals("2", round.rerollableSkullDice());	
		Assert.assertEquals(100, round.getScoreOfRound());
		Assert.assertEquals(0, round.getSkullNumber());
	}
	
	/**
	 * roll no skulls, then next round roll 1 skull and reroll for it, then go to next round           
	 */
	@Test
	public void customerTest3Row82() {
		Round round = new Round();
		round.setActiveCard(new SorceressCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(null, round.rerollableSkullDice());	
		Assert.assertEquals(200, round.getScoreOfRound());
		Assert.assertEquals(0, round.getSkullNumber());
		
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Parrot");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Skull");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals("6", round.rerollableSkullDice());	
		Assert.assertEquals(100, round.getScoreOfRound());
		Assert.assertEquals(0, round.getSkullNumber());
	}
}
