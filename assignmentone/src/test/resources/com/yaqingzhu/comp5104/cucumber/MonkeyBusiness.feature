Feature: Monkey Business Test
  I want to use this file to test row 75-77

  @TestRow75
  Scenario: first roll gets 3 monkeys, 3 parrots, 1 skull, 1 gold, SC = 1100  (i.e., sequence of of 6 + gold)
    Given A player get a monkey business card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Parrot", 5:"Parrot", 6:"Parrot", 7:"Skull", 8:"Gold"
    Then score for this player is 1100

	@TestRow76
  Scenario: over several rolls: 2 monkeys, 1 parrot, 2 golds, 1 diamond, 2 swords, SC=400
    Given A player get a monkey business card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Sword", 4:"Sword", 5:"Sword", 6:"Parrot", 7:"Parrot", 8:"Gold"
    And reroll dice "3,4,5,6,7,8"
    And the result of this roll is 1:parrot, 2:monkey, 3:gold, 4:sword, 5:sword, 6:parrot, 7:parrot, 8:gold
    And reroll dice "3,4,5,6,7,8"
    And the result of this roll is 1:parrot, 2:monkey, 3:gold, 4:sword, 5:parrot, 6:parrot, 7:parrot, 8:gold
    And reroll dice "3,4,5,6,7,8"
    And the result of this roll is 1:parrot, 2:monkey, 3:gold, 4:sword, 5:sword, 6:diamond, 7:parrot, 8:gold
    Then score for this player is 400


  @TestRow77
  Scenario: over several rolls: 3 monkeys, 4 parrots, 1 sword, SC=2000
    Given A player get a monkey business card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Sword", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Parrot", 8:"Parrot"
    And reroll dice "3,4,5,6,7,8"
    And the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:sword, 5:sword, 6:parrot, 7:parrot, 8:gold
    And reroll dice "5,8"
    And the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:sword, 5:sword, 6:parrot, 7:parrot, 8:diamond
    And reroll dice "5,8"
    And the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:sword, 5:parrot, 6:parrot, 7:parrot, 8:parrot
    Then score for this player is 2000