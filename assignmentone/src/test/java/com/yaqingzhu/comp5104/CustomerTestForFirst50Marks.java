package com.yaqingzhu.comp5104;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTestForFirst50Marks {

	/**
	 * die with 3 skulls on first roll          
	 */
	@Test
	public void customerTest1Row48() {
		Round round = new Round();
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		
		Assert.assertEquals(true, round.isToEndRound());
		
	}
	
	/**
	 * roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll swords, get 2 skulls 1 sword  die
	 */
	@Test
	public void customerTest2Row49() {
		Round round = new Round();
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Parrot");
		round.getDice().get(2).setLastResult("Parrot");
		round.getDice().get(3).setLastResult("Parrot");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		Assert.assertEquals(false, round.isToEndRound());
		
		round.getDice().get(5).setLastResult("Skull");
		round.getDice().get(6).setLastResult("Skull");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("6,7,8");
		Assert.assertEquals(true, round.isToEndRound());
		
	}
	
	/**
	 * roll 2 skulls, 4 parrots, 2 swords, hold parrots, reroll swords, get 1 skull 1 sword  die
	 */
	@Test
	public void customerTest3Row50() {
		Round round = new Round();
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Parrot");
		round.getDice().get(3).setLastResult("Parrot");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		Assert.assertEquals(false, round.isToEndRound());
		
		round.getDice().get(6).setLastResult("Skull");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("7,8");
		Assert.assertEquals(true, round.isToEndRound());
		
	}
	
	/**
	 * roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll swords, get 1 skull 2 monkeys
	 * reroll 2 monkeys, get 1 skull 1 monkey and die
	 */
	@Test
	public void customerTest4Row51() {
		Round round = new Round();
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Parrot");
		round.getDice().get(2).setLastResult("Parrot");
		round.getDice().get(3).setLastResult("Parrot");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		Assert.assertEquals(false, round.isToEndRound());
		
		round.getDice().get(5).setLastResult("Skull");
		round.getDice().get(6).setLastResult("Monkey");
		round.getDice().get(7).setLastResult("Monkey");
		round.checkSkullsOfRoll("6,7,8");
		Assert.assertEquals(false, round.isToEndRound());
		
		round.getDice().get(6).setLastResult("Skull");
		round.getDice().get(7).setLastResult("Monkey");
		round.checkSkullsOfRoll("7,8");
		Assert.assertEquals(true, round.isToEndRound());
		
	}
	
	/**
	 * score first roll with nothing but 2 diamonds and 2 coins and FC is captain (SC 800)
	 */
	@Test
	public void customerTest5Row53() {
		Round round = new Round();
		round.setActiveCard(new CaptainCard());
		round.getDice().get(0).setLastResult("Diamond");
		round.getDice().get(1).setLastResult("Diamond");
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Gold");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		Assert.assertEquals(800, round.getScoreOfRound());		
	}
	
	/**
	 * get set of 2 monkeys on first roll, get 3rd monkey on 2nd roll (SC 200 since FC is coin)
	 */
	@Test
	public void customerTest6Row54() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Skull");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		Assert.assertEquals(100, round.getScoreOfRound());	
		
		round.getDice().get(7).setLastResult("Monkey");
		round.checkSkullsOfRoll("8");
		round.calcRoundScore();
		Assert.assertEquals(200, round.getScoreOfRound());
	}
	
	/**
	 * score 2 sets of 3 (monkey, swords) in RTS on first roll   (SC 300)
	 */
	@Test
	public void customerTest7Row55() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Skull");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(300, round.getScoreOfRound());	
	}
	
	/**
	 * score 2 sets of 3 (monkey, parrots) in RTS using 2 rolls   (SC 300)
	 */
	@Test
	public void customerTest8Row56() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Skull");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(200, round.getScoreOfRound());	
		
		round.getDice().get(7).setLastResult("Parrot");
		round.checkSkullsOfRoll("8");
		round.calcRoundScore();
		
		Assert.assertEquals(300, round.getScoreOfRound());
	}
	
	/**
	 * score a set of 3 diamonds correctly (i.e., 400 points)   (SC 500)
	 */
	@Test
	public void customerTest9Row57() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Diamond");
		round.getDice().get(1).setLastResult("Diamond");
		round.getDice().get(2).setLastResult("Diamond");
		round.getDice().get(3).setLastResult("Skull");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(500, round.getScoreOfRound());	
	}
	
	/**
	 * score a set of 4 coins correctly (i.e., 600 points) with FC is a diamond (SC 700)
	 */
	@Test
	public void customerTest10Row58() {
		Round round = new Round();
		round.setActiveCard(new DiamondCard());
		round.getDice().get(0).setLastResult("Gold");
		round.getDice().get(1).setLastResult("Gold");
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Gold");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(700, round.getScoreOfRound());	
	}
	
	/**
	 * score set of 3 swords and set of 4 parrots correctly on first roll (SC 400 because of FC)
	 */
	@Test
	public void customerTest11Row59() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Parrot");
		round.getDice().get(2).setLastResult("Parrot");
		round.getDice().get(3).setLastResult("Parrot");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(400, round.getScoreOfRound());	
	}
	
	/**
	 * score set of 3 coins+ FC and set of 4 swords correctly over several rolls (SC = 200+400+200 = 800)
	 */
	@Test
	public void customerTest12Row60() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Parrot");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Gold");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(400, round.getScoreOfRound());	
		
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Parrot");
		round.getDice().get(2).setLastResult("Gold");
		round.checkSkullsOfRoll("1,2,3");
		round.calcRoundScore();
		
		Assert.assertEquals(600, round.getScoreOfRound());	
		
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Gold");
		round.checkSkullsOfRoll("1,2");
		round.calcRoundScore();
		
		Assert.assertEquals(800, round.getScoreOfRound());	
	}
	
	/**
	 * same as previous row but with captain fortune card  (SC = (100 + + 300 + 200)*2 = 1200)
	 */
	@Test
	public void customerTest13Row61() {
		Round round = new Round();
		round.setActiveCard(new CaptainCard());
		round.getDice().get(0).setLastResult("Parrot");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Gold");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(600, round.getScoreOfRound());	
		
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Parrot");
		round.getDice().get(2).setLastResult("Gold");
		round.checkSkullsOfRoll("1,2,3");
		round.calcRoundScore();
		
		Assert.assertEquals(800, round.getScoreOfRound());	
		
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Gold");
		round.checkSkullsOfRoll("1,2");
		round.calcRoundScore();
		
		Assert.assertEquals(1200, round.getScoreOfRound());	
	}
	
	/**
	 * score set of 5 swords over 3 rolls (SC 600)
	 */
	@Test
	public void customerTest14Row62() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Parrot");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(300, round.getScoreOfRound());	
		
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Parrot");
		round.checkSkullsOfRoll("1,2,3,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(300, round.getScoreOfRound());	
		
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(600, round.getScoreOfRound());	
	}
	
	/**
	 * score set of 6 monkeys on first roll (SC 1100)
	 */
	@Test
	public void customerTest15Row63() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Sword");
		round.getDice().get(1).setLastResult("Sword");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Monkey");
		round.getDice().get(5).setLastResult("Monkey");
		round.getDice().get(6).setLastResult("Monkey");
		round.getDice().get(7).setLastResult("Monkey");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(1100, round.getScoreOfRound());	
	}
	
	/**
	 * score set of 7 parrots on first roll (SC 2100)
	 */
	@Test
	public void customerTest16Row64() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Sword");
		round.getDice().get(1).setLastResult("Parrot");
		round.getDice().get(2).setLastResult("Parrot");
		round.getDice().get(3).setLastResult("Parrot");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Parrot");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(2100, round.getScoreOfRound());	
	}
	
	/**
	 * score set of 8 coins on first roll (SC 5400)  seq of 8 + 9 coins +  full chest
	 */
	@Test
	public void customerTest17Row65() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Gold");
		round.getDice().get(1).setLastResult("Gold");
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Gold");
		round.getDice().get(4).setLastResult("Gold");
		round.getDice().get(5).setLastResult("Gold");
		round.getDice().get(6).setLastResult("Gold");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(5400, round.getScoreOfRound());	
	}
	
	/**
	 * score set of 8 coins on first roll and FC is diamond (SC 5400)  
	 */
	@Test
	public void customerTest18Row66() {
		Round round = new Round();
		round.setActiveCard(new DiamondCard());
		round.getDice().get(0).setLastResult("Gold");
		round.getDice().get(1).setLastResult("Gold");
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Gold");
		round.getDice().get(4).setLastResult("Gold");
		round.getDice().get(5).setLastResult("Gold");
		round.getDice().get(6).setLastResult("Gold");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(5400, round.getScoreOfRound());	
	}
	
	/**
	 * score set of 8 swords on first roll and FC is captain (SC 4500x2 = 9000) if you have full chest  
	 */
	@Test
	public void customerTest19Row67() {
		Round round = new Round();
		round.setActiveCard(new CaptainCard());
		round.getDice().get(0).setLastResult("Sword");
		round.getDice().get(1).setLastResult("Sword");
		round.getDice().get(2).setLastResult("Sword");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(9000, round.getScoreOfRound());	
	}
	
	/**
	 * score set of 8 monkeys over several rolls (SC 4600 because of FC is coin and full chest)  
	 */
	@Test
	public void customerTest20Row68() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
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
		
		Assert.assertEquals(300, round.getScoreOfRound());	
		
		round.getDice().get(4).setLastResult("Monkey");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(600, round.getScoreOfRound());	
		
		round.getDice().get(5).setLastResult("Monkey");
		round.getDice().get(6).setLastResult("Monkey");
		round.getDice().get(7).setLastResult("Monkey");
		round.checkSkullsOfRoll("6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(4600, round.getScoreOfRound());	
	}
	
	/**
	 * score a set of 2 diamonds over 2 rolls with FC is diamond (SC 400)  
	 */
	@Test
	public void customerTest21Row69() {
		Round round = new Round();
		round.setActiveCard(new DiamondCard());
		round.getDice().get(0).setLastResult("Diamond");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(300, round.getScoreOfRound());	
		
		round.getDice().get(1).setLastResult("Diamond");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(400, round.getScoreOfRound());	
	}
	
	/**
	 * score a set of 3 diamonds over 2 rolls (SC 500)  
	 */
	@Test
	public void customerTest22Row70() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Diamond");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(300, round.getScoreOfRound());	
		
		round.getDice().get(1).setLastResult("Diamond");
		round.getDice().get(2).setLastResult("Diamond");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(500, round.getScoreOfRound());	
	}
	
	/**
	 * score a set of 3 coins over 2 rolls  (SC 600)  
	 */
	@Test
	public void customerTest23Row71() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Gold");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(300, round.getScoreOfRound());	
		
		round.getDice().get(1).setLastResult("Gold");
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(600, round.getScoreOfRound());	
	}
	
	/**
	 * score a set of 3 coins over 2 rolls  with FC is diamond (SC 500)
	 */
	@Test
	public void customerTest24Row72() {
		Round round = new Round();
		round.setActiveCard(new DiamondCard());
		round.getDice().get(0).setLastResult("Gold");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(300, round.getScoreOfRound());	
		
		round.getDice().get(1).setLastResult("Gold");
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(500, round.getScoreOfRound());	
	}
	
	/**
	 * score a set of 4 monkeys and a set of 3 coins (including the COIN fortune card) (SC 600)
	 */
	@Test
	public void customerTest25Row73() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Gold");
		round.getDice().get(1).setLastResult("Gold");
		round.getDice().get(2).setLastResult("Sword");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Monkey");
		round.getDice().get(5).setLastResult("Monkey");
		round.getDice().get(6).setLastResult("Monkey");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(600, round.getScoreOfRound());	
	}
	
	/**
	 * get 7 swords on first roll, try to roll the 8 die by itself -> interface reports not allowed
	 */
	@Test
	public void customerTest26Row75() {
		Player player = new Player();

		player.getRound().setActiveCard(new GoldCard());
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
		
		Assert.assertEquals(true, player.rollDice("7").contains("you have to roll at least 2 dices"));
	}
}
