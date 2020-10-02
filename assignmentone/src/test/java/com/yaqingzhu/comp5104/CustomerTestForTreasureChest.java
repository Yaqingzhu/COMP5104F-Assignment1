package com.yaqingzhu.comp5104;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTestForTreasureChest {
	/**
	 * roll 3 parrots, 2 swords, 2 diamonds, 1 coin     put 2 diamonds and 1 coin in chest
 	 *   then reroll 2 swords and get 2 parrots put 5 parrots in chest and take out 2 diamonds & coin
	 *   then reroll the 3 dice and get 1 skull, 1 coin and a parrot
	 *   score 6 parrots + 1 coin for 1100 points
	 */
	@Test
	public void customerTest1Row90() {
		Round round = new Round();
		round.setActiveCard(new TreasureChestCard());
		round.getDice().get(0).setLastResult("Parrot");
		round.getDice().get(1).setLastResult("Parrot");
		round.getDice().get(2).setLastResult("Parrot");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Diamond");
		round.getDice().get(6).setLastResult("Diamond");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(400, round.getScoreOfRound());
		
		round.setTreasureChestDices("6,7,8");
		round.getTreasureChestScore(round.getTreasureChestDices()); // getTreasureChestScore() only can be called when this round is about to end(die), 
																	//which will override the final score of this round(turn).

		Assert.assertEquals(300, round.getScoreOfRound());
		
		// then reroll 2 swords and get 2 parrots put 5 parrots in chest and take out 2 diamonds & coin
		round.getDice().get(3).setLastResult("Parrot");
		round.getDice().get(4).setLastResult("Parrot");
		round.checkSkullsOfRoll("4,5");
		round.calcRoundScore();
		
		Assert.assertEquals(1300, round.getScoreOfRound());
		
		round.setTreasureChestDices("1,2,3,4,5");
		round.getTreasureChestScore(round.getTreasureChestDices()); 
		
		Assert.assertEquals(500, round.getScoreOfRound());
		
		//then reroll the 3 dice and get 1 skull, 1 coin and a parrot
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Gold");
		round.getDice().get(7).setLastResult("Skull");
		round.checkSkullsOfRoll("6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(1100, round.getScoreOfRound());
		
	}
	
	/**
	 * roll 2 skulls, 3 parrots, 3 coins   put 3 coins in chest
 	 *     then rerolls 3 parrots and get 2 diamonds 1 coin    put coin in chest (now 4)
	 *      then reroll 2 diamonds and get 1 skull 1 coin     SC for chest only = 400 + 200 = 600
	 *            also interface reports death & end of turn
	 */
	@Test
	public void customerTest2Row95() {
		Round round = new Round();
		round.setActiveCard(new TreasureChestCard());
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Parrot");
		round.getDice().get(3).setLastResult("Parrot");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Gold");
		round.getDice().get(6).setLastResult("Gold");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();
		
		Assert.assertEquals(500, round.getScoreOfRound());
		
		round.setTreasureChestDices("6,7,8");
		round.getTreasureChestScore(round.getTreasureChestDices()); // getTreasureChestScore() only can be called when this round is about to end(die), 
																	//which will override the final score of this round(turn).

		Assert.assertEquals(400, round.getScoreOfRound());
		
		// then rerolls 3 parrots and get 2 diamonds 1 coin    put coin in chest (now 4)
		round.getDice().get(2).setLastResult("Diamond");
		round.getDice().get(3).setLastResult("Diamond");
		round.getDice().get(4).setLastResult("Gold");
		round.checkSkullsOfRoll("3,4,5");
		round.calcRoundScore();
		
		Assert.assertEquals(800, round.getScoreOfRound());
		
		round.setTreasureChestDices("5,6,7,8");
		round.getTreasureChestScore(round.getTreasureChestDices()); 
		
		Assert.assertEquals(600, round.getScoreOfRound());
		
		//then reroll 2 diamonds and get 1 skull 1 coin     SC for chest only = 400 + 200 = 600
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Gold");
		round.checkSkullsOfRoll("3,4");
		round.calcRoundScore();
		
		Assert.assertEquals(600, round.getScoreOfRound());
		
	}
}
