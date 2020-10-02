package com.yaqingzhu.comp5104;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTestForMonkeyBusiness {

	/**
	 * first roll gets 3 monkeys 3 parrots  1 skull 1 coin  SC = 1100  (i.e., sequence of of 6 + coin)        
	 */
	@Test
	public void customerTest1Row85() {
		Round round = new Round();
		round.setActiveCard(new MonkeyBusinessCard());
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(1100, round.getScoreOfRound());
	}
	
	/**
	 * over several rolls: 2 monkeys, 1 parrot, 2 coins, 1 diamond, 2 swords         SC 400        
	 */
	@Test
	public void customerTest2Row86() {
		Round round = new Round();
		round.setActiveCard(new MonkeyBusinessCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Sword");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(900, round.getScoreOfRound()); // 4(monkey + parrot) + 1 gold + 3 sword + full chest
		
		round.setActiveCard(new MonkeyBusinessCard());
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(400, round.getScoreOfRound()); //4(monkey + parrot) + 2 gold
		
		round.setActiveCard(new MonkeyBusinessCard());
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(700, round.getScoreOfRound()); //5(monkey + parrot) + 2 gold
		
		round.setActiveCard(new MonkeyBusinessCard());
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Diamond");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(400, round.getScoreOfRound());
	}
	
	/**
	 * over several rolls get 3 monkeys, 4 parrots, 1 sword    SC 2000 (ie seq of 7)        
	 */
	@Test
	public void customerTest3Row87() {
		Round round = new Round();
		round.setActiveCard(new MonkeyBusinessCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Sword");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Sword");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(900, round.getScoreOfRound()); // 4(monkey + parrot) + 4 sword + full chest
		
		round.setActiveCard(new MonkeyBusinessCard());
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(600, round.getScoreOfRound()); //5(monkey + parrot) + 1 gold
		
		round.setActiveCard(new MonkeyBusinessCard());
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("5,8");
		round.calcRoundScore();

		Assert.assertEquals(600, round.getScoreOfRound()); //5(monkey + parrot) + 1 gold
		
		round.setActiveCard(new MonkeyBusinessCard());
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Parrot");
		round.checkSkullsOfRoll("5,8");
		round.calcRoundScore();

		Assert.assertEquals(2000, round.getScoreOfRound());
	}
}
