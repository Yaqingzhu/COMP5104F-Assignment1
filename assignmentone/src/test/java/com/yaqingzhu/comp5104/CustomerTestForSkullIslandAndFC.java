package com.yaqingzhu.comp5104;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTestForSkullIslandAndFC {
	/**
	 * die by rolling one skull and having a FC with two skulls   
	 */
	@Test
	public void customerTest1Row110() {
		Round round = new Round();
		round.setActiveCard(new SkullCardTwo());
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(0, round.getScoreOfRound());
		Assert.assertEquals(true, round.isToEndRound());
	}
	
	/**
	 * die by rolling 2 skulls and having a FC with 1 skull   
	 */
	@Test
	public void customerTest2Row111() {
		Round round = new Round();
		round.setActiveCard(new SkullsCard());
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(0, round.getScoreOfRound());
		Assert.assertEquals(true, round.isToEndRound());
	}
	
	/**
	 * roll 2 skulls AND have a FC with two skulls: roll 2 skulls next roll, then 1 skull => -700    
	 */
	@Test
	public void customerTest3Row112() {
		Round round = new Round();
		round.setActiveCard(new SkullCardTwo());
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(false, round.isToEndRound());
		Assert.assertEquals(true, round.isWentToSkullIsland());
		Assert.assertEquals(0, round.getScoreOfRound());
		Assert.assertEquals(400, round.getScoreOfSkullIsland()); // ScoreOfSkullIsland means score got from Skull island that will deduct other players score
		
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Skull");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(false, round.isToEndRound());
		Assert.assertEquals(true, round.isWentToSkullIsland());
		Assert.assertEquals(0, round.getScoreOfRound());
		Assert.assertEquals(600, round.getScoreOfSkullIsland());
		
		round.getDice().get(4).setLastResult("Skull");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(false, round.isToEndRound());
		Assert.assertEquals(true, round.isWentToSkullIsland());
		Assert.assertEquals(0, round.getScoreOfRound());
		Assert.assertEquals(700, round.getScoreOfSkullIsland());
		
	}
	
	/**
	 * roll 3 skulls AND have a FC with two skulls: roll no skulls next roll  => -500    
	 */
	@Test
	public void customerTest4Row113() {
		Round round = new Round();
		round.setActiveCard(new SkullCardTwo());
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(false, round.isToEndSkullIsland());
		Assert.assertEquals(false, round.isToEndRound());
		Assert.assertEquals(true, round.isWentToSkullIsland());
		Assert.assertEquals(0, round.getScoreOfRound());
		Assert.assertEquals(500, round.getScoreOfSkullIsland());
		
		round.getDice().get(3).setLastResult("Diamond");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(true, round.isToEndSkullIsland()); // this roll doesn't have skull, so the round should stop
		Assert.assertEquals(false, round.isToEndRound());
		Assert.assertEquals(true, round.isWentToSkullIsland());
		Assert.assertEquals(0, round.getScoreOfRound());
		Assert.assertEquals(500, round.getScoreOfSkullIsland());
		
	}
	
	/**
	 * roll 3 skulls AND have a FC with 1 skull: roll 1 skull next roll then none => -500    
	 */
	@Test
	public void customerTest5Row114() {
		Round round = new Round();
		round.setActiveCard(new SkullsCard());
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(false, round.isToEndRound());
		Assert.assertEquals(true, round.isWentToSkullIsland());
		Assert.assertEquals(0, round.getScoreOfRound());
		Assert.assertEquals(400, round.getScoreOfSkullIsland());
		
		round.getDice().get(3).setLastResult("Skull");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(false, round.isToEndRound());
		Assert.assertEquals(true, round.isWentToSkullIsland());
		Assert.assertEquals(0, round.getScoreOfRound());
		Assert.assertEquals(500, round.getScoreOfSkullIsland());
		
	}
	
	/**
	 * show deduction received cannot make your score negative    
	 */
	@Test
	public void customerTest6Row115() {
		Player player1 = new Player();
		Player player2 = new Player();
		
		player1.getRound().setActiveCard(new SkullsCard());
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

		Assert.assertEquals(false, player1.getRound().isToEndRound());
		Assert.assertEquals(true, player1.getRound().isWentToSkullIsland());
		Assert.assertEquals(0, player1.getRound().getScoreOfRound());
		Assert.assertEquals(400, player1.getRound().getScoreOfSkullIsland());
		
		Assert.assertEquals(0, player2.getScore());
		player2.setScore(player2.getScore() - player1.getRound().getScoreOfSkullIsland());
		Assert.assertEquals(0, player2.getScore());	
	}
}
