Feature: Sea Battle Test
  I want to use this file to test row 109-122

   @TestRow109
	 Scenario: a player die on first roll with seabattlecardtwo
	    Given A player get 2 sword sea battle card
	    And roll all 8 dices on the first roll
	    When the result of first roll is 1:"Skull", 2:"Skull", 3:"Skull", 4:"Skull", 5:"Sword", 6:"Sword", 7:"Parrot", 8:"Diamond"
	    Then the player die and lose 300 points
	
	 @TestRow110
	 Scenario: a player die on first roll with seabattlecardthree
	    Given A player get 3 sword sea battle card
	    And roll all 8 dices on the first roll
	    When the result of first roll is 1:"Skull", 2:"Skull", 3:"Skull", 4:"Skull", 5:"Sword", 6:"Sword", 7:"Parrot", 8:"Diamond"
	    Then the player die and lose 500 points 
	
	 @TestRow111
	 Scenario: a player die on first roll with seabattlecardfour
	    Given A player get 4 sword sea battle card
	    And roll all 8 dices on the first roll
	    When the result of first roll is 1:"Skull", 2:"Skull", 3:"Skull", 4:"Skull", 5:"Sword", 6:"Sword", 7:"Parrot", 8:"Diamond"
	    Then the player die and lose 1000 points          

   @TestRow112
   Scenario: show deduction received cannot make your score negative
	    Given A player get 4 sword sea battle card
	    And roll all 8 dices on the first roll
	    When the result of first roll is 1:"Skull", 2:"Skull", 3:"Skull", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Parrot", 8:"Diamond"
	    Then the player die and end of turn with 0 point

	 @TestRow113
	 Scenario: a player roll 3 monkeys 2 swords, 1 gold, 2 parrots,FC 2 swords, SC = 100 + 100 + 300 = 500
	    Given A player get 2 sword sea battle card
	    And roll all 8 dices on the first roll
	    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Gold", 5:"Sword", 6:"Sword", 7:"Parrot", 8:"Parrot"
	    Then score for this player is 500 

	 @TestRow114
	 Scenario: FC 2 swords, roll 4 monkeys 1 sword, 1 skull  2 parrots
	           then reroll 2 parrots and get 1 sword and 1 skull   SC = 200 +  300 = 500
	    Given A player get 2 sword sea battle card
	    And roll all 8 dices on the first roll
	    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Monkey", 5:"Sword", 6:"Skull", 7:"Parrot", 8:"Parrot"
	    And reroll dice "7,8"
	    And the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:monkey, 5:sword, 6:skull, 7:sword, 8:skull
	    Then score for this player is 500
	    	 
	  @TestRow116
	  Scenario: a player roll 3 monkeys 4 swords, FC 3 swords, SC = 100 + 200 + 500 = 800
	    Given A player get 3 sword sea battle card
	    And roll all 8 dices on the first roll
	    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Sword", 8:"Parrot"
	    Then score for this player is 800
	    	 
	  @TestRow117
	  Scenario: FC 2 swords, roll 4 monkeys 2 swords 2 skulls then reroll 4 monkeys and get  2 skulls and 2 swords   -> DIE
	    Given A player get 2 sword sea battle card
	    And roll all 8 dices on the first roll
	    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Monkey", 5:"Sword", 6:"Sword", 7:"Skull", 8:"Skull"
	    And reroll dice "1,2,3,4"
	    And the result of this roll is 1:skull, 2:sword, 3:sword, 4:skull, 5:sword, 6:sword, 7:skull, 8:skull
	    Then the player die and end of turn
	    
	    
	  @TestRow119
	  Scenario: a player roll 3 monkeys 4 swords, 1 skull, FC 4 swords, SC = 100 + 200 + 1000 = 1300
	    Given A player get 4 sword sea battle card
	    And roll all 8 dices on the first roll
	    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Sword", 8:"Skull"
	    Then score for this player is 1300
 	    
	  @TestRow120
	  Scenario: FC 4 swords, roll 3 monkeys, 1 sword, 1 skull, 1 diamond, 2 parrots then reroll 2 parrots and get 2 swords thus you have 3 monkeys, 3 swords, 1 diamond, 1 skull then reroll 3 monkeys and get  1 sword and 2 parrots  SC = 200 + 100 + 1000 = 1300
	    Given A player get 4 sword sea battle card
	    And roll all 8 dices on the first roll
	    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Skull", 6:"Diamond", 7:"Parrot", 8:"Parrot"
	    And reroll dice "7,8"
	    And the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:sword, 5:skull, 6:diamond, 7:sword, 8:sword
	    And reroll dice "1,2,3"
	    And the result of this roll is 1:sword, 2:parrot, 3:parrot, 4:sword, 5:skull, 6:diamond, 7:sword, 8:sword
	    Then score for this player is 1300
 