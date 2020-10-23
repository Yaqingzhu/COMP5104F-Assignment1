package com.yaqingzhu.comp5104.cucumber;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.annotation.en.And;


import static org.junit.Assert.assertTrue;

import com.yaqingzhu.comp5104.*;
import com.yaqingzhu.comp5104.Player;
import com.yaqingzhu.comp5104.Round;


public class BasicDyingSD {
	Player player;
	Round round;
	String optFromConsole = "";
	
  @Given("A player roll all 8 dices on the first roll")
  public void given() throws Throwable {
	player = new Player();
	round = player.getRound();
	round.setActiveCard(new GoldCard());
	player.rollDice("1,2,3,4,5,6,7,8");
  }

  @When("get 3 skulls")
  public void whenrow38() throws Throwable {
	  round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
	  optFromConsole = player.showResult("1,2,3,4,5,6,7,8");
	  System.out.println(optFromConsole);
  }

  @Then("player die and end of turn")
  public void thenrow38() throws Throwable {
	  assertTrue(optFromConsole.contains("disqualified"));
  }
  
  
  // test row 39 
  @When("the result of first roll is 1:skull, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:sword, 7:sword, 8:sword")
  public void whenrow39() throws Throwable {
	  round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Parrot");
		round.getDice().get(2).setLastResult("Parrot");
		round.getDice().get(3).setLastResult("Parrot");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
	  optFromConsole = player.showResult("1,2,3,4,5,6,7,8");
	  System.out.println(optFromConsole);
  }
  
  @And("hold dices \"2,3,4,5\", reroll dices \"6,7,8\" for test row 39")
  public void androw39() throws Throwable {
	  player.rollDice("6,7,8");
	  round.getDice().get(5).setLastResult("Skull");
	  round.getDice().get(6).setLastResult("Skull");
	  round.getDice().get(7).setLastResult("Sword");
  }
  
  @And("the result of this roll is 1:skull, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:skull, 7:skull, 8:sword")
  public void and2row39() throws Throwable {
	  optFromConsole = player.showResult("6,7,8");
	  System.out.println(optFromConsole);
  }
  
  //test row 40
  @When("the result of first roll is 1:skull, 2:skull, 3:parrot, 4:parrot, 5:parrot, 6:parrot, 7:sword, 8:sword")
  public void whenrow40() throws Throwable {
	  round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Parrot");
		round.getDice().get(3).setLastResult("Parrot");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
	  optFromConsole = player.showResult("1,2,3,4,5,6,7,8");
	  System.out.println(optFromConsole);
  }
  
  @And("hold dices \"3,4,5,6\", reroll dices \"7,8\" for test row 40")
  public void androw40() throws Throwable {
	  player.rollDice("7,8");
	  round.getDice().get(6).setLastResult("Skull");
	  round.getDice().get(7).setLastResult("Sword");
  }
  
  @And("the result of this roll is 1:skull, 2:skull, 3:parrot, 4:parrot, 5:parrot, 6:parrot, 7:skull, 8:sword for test row 40")
  public void and2row40() throws Throwable {
	  optFromConsole = player.showResult("7,8");
	  System.out.println(optFromConsole);
  }
  
//test row 41
  
  @And("hold dices \"2,3,4,5\", reroll dices \"6,7,8\" for test row 41")
  public void androw41() throws Throwable {
	  player.rollDice("6,7,8");
	  round.getDice().get(5).setLastResult("Skull");
	  round.getDice().get(6).setLastResult("Monkey");
	  round.getDice().get(7).setLastResult("Monkey");
  }
  
  @And("the result of this roll is 1:skull, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:skull, 7:monkey, 8:monkey")
  public void and2row41() throws Throwable {
	  optFromConsole = player.showResult("6,7,8");
	  System.out.println(optFromConsole);
  }
  
  @And("hold dices \"2,3,4,5\", reroll dices \"7,8\" for test row 41")
  public void and3row41() throws Throwable {
	  player.rollDice("7,8");
	  round.getDice().get(6).setLastResult("Skull");
	  round.getDice().get(7).setLastResult("Monkey");
  }
  
  @And("the result of this roll is 1:skull, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:skull, 7:skull, 8:monkey")
  public void and4row41() throws Throwable {
	  optFromConsole = player.showResult("7,8");
	  System.out.println(optFromConsole);
  }
}
