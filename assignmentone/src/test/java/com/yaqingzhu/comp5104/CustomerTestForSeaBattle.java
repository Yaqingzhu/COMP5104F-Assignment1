package com.yaqingzhu.comp5104;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTestForSeaBattle {
	/**
	 * FC 2 swords, die on first roll   => lose 300 points  
	 */
	@Test
	public void customerTest1Row118() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardTwo());
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Skull");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(-300, round.getScoreOfRound());
		Assert.assertEquals(true, round.isToEndRound());
	}
	
	/**
	 * FC 3 swords, die on first roll   => lose 500 points
	 */
	@Test
	public void customerTest2Row119() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardThree());
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Skull");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(-500, round.getScoreOfRound());
		Assert.assertEquals(true, round.isToEndRound());
	}
	
	/**
	 * FC 4 swords, die on first roll   => lose 1000 points
	 */
	@Test
	public void customerTest3Row120() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardFour());
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Skull");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(-1000, round.getScoreOfRound());
		Assert.assertEquals(true, round.isToEndRound());
	}
	
	/**
	 * show deduction received cannot make your score negative  
	 */
	@Test
	public void customerTest4Row121() {
		Player player1 = new Player();
		
		Assert.assertEquals(0, player1.getScore());
		
		player1.getRound().setActiveCard(new SeaBattleCardFour());
		player1.getRound().getDice().get(0).setLastResult("Skull");
		player1.getRound().getDice().get(1).setLastResult("Skull");
		player1.getRound().getDice().get(2).setLastResult("Skull");
		player1.getRound().getDice().get(3).setLastResult("Sword");
		player1.getRound().getDice().get(4).setLastResult("Sword");
		player1.getRound().getDice().get(5).setLastResult("Sword");
		player1.getRound().getDice().get(6).setLastResult("Parrot");
		player1.getRound().getDice().get(7).setLastResult("Diamond");
		player1.getRound().checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		player1.getRound().calcRoundScore();

		Assert.assertEquals(true, player1.getRound().isToEndRound());
		Assert.assertEquals(-1000, player1.getRound().getScoreOfRound());
		player1.setScore(player1.getScore() + player1.getRound().getScoreOfRound());
		Assert.assertEquals(0, player1.getScore());

	}
	
	/**
	 * FC 2 swords, roll 3 monkeys 2 swords, 1 coin, 2 parrots  SC = 100 + 100 + 300 = 500
	 */
	@Test
	public void customerTest5Row122() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardTwo());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Gold");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Parrot");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(500, round.getScoreOfRound());
	}
	
	/**
	 * FC 2 swords, roll 4 monkeys 1 sword, 1 skull  2 parrots
	 *   then reroll 2 parrots and get 1 sword and 1 skull   SC = 200 +  300 = 500
	 */
	@Test
	public void customerTest6Row123() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardTwo());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Skull");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Parrot");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(-300, round.getScoreOfRound());
		
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Skull");
		round.checkSkullsOfRoll("7,8");
		round.calcRoundScore();

		Assert.assertEquals(500, round.getScoreOfRound());
	}
	
	/**
	 * FC 3 swords, roll 3 monkeys 4 swords  SC = 100 + 200 + 500 = 800
	 */
	@Test
	public void customerTest7Row125() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardThree());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Parrot");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(800, round.getScoreOfRound());
	}
	
	/**
	 * FC 3 swords, roll 4 monkeys 2 swords 2 skulls 
	 *         then reroll 4 monkeys and get  2 skulls and 2 swords   -> DIE
	 */
	@Test
	public void customerTest8Row126() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardThree());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Skull");
		round.getDice().get(6).setLastResult("Skull");
		round.getDice().get(7).setLastResult("Monkey");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(-500, round.getScoreOfRound());
		
		round.getDice().get(0).setLastResult("Sword");
		round.getDice().get(1).setLastResult("Sword");
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(7).setLastResult("Skull");
		round.checkSkullsOfRoll("1,2,8");
		round.calcRoundScore();

		Assert.assertEquals(-500, round.getScoreOfRound());
		Assert.assertEquals(true, round.isToEndRound());
	}
	
	/**
	 * FC 4 swords, roll 3 monkeys 4 swords 1 skull  SC = 100 +200 + 1000 = 1300
	 */
	@Test
	public void customerTest9Row128() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardFour());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Skull");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(1300, round.getScoreOfRound());
	}
	
	/**
	 * FC 4 swords, roll 3 monkeys, 1 sword, 1 skull, 1 diamond, 2 parrots  
	 *  then reroll 2 parrots and get 2 swords thus you have 3 monkeys, 3 swords, 1 diamond, 1 skull
	 *  then reroll 3 monkeys and get  1 sword and 2 parrots  SC = 200 + 100 + 1000 = 1300
	 */
	@Test
	public void customerTest10Row129() {
		Round round = new Round();
		round.setActiveCard(new SeaBattleCardFour());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Diamond");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Skull");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(-1000, round.getScoreOfRound());
		
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.checkSkullsOfRoll("6,7");
		round.calcRoundScore();

		Assert.assertEquals(-1000, round.getScoreOfRound());
		
		round.getDice().get(0).setLastResult("Sword");
		round.getDice().get(1).setLastResult("Parrot");
		round.getDice().get(2).setLastResult("Parrot");
		round.checkSkullsOfRoll("1,2,3");
		round.calcRoundScore();

		Assert.assertEquals(1300, round.getScoreOfRound());
	}
}
