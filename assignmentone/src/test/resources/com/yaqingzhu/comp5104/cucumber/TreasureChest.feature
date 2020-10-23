
Feature: Treasure Chest Test
  I want to use this file to test row 80-88

  @TestRow80-83
  Scenario: roll 3 parrots, 2 swords, 2 diamonds, 1 gold, put 2 diamonds and 1 gold in chest
            then reroll 2 swords and get 2 parrots put 5 parrots in chest and take out 2 diamonds & gold
            then reroll the 3 dice and get 1 skull, 1 gold and a parrot
            score 6 parrots + 1 gold for 1100 points
    Given A player get a Tresure Chest card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Parrot", 2:"Parrot", 3:"Parrot", 4:"Sword", 5:"Sword", 6:"Diamond", 7:"Diamond", 8:"Gold"
    And put dice "6,7,8" in chest
    And reroll dice "4,5"
    And the result of this roll is 1:parrot, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:diamond, 7:diamond, 8:gold
    And put dice "1,2,3,4,5" in chest
    And reroll dice "6,7,8"
    And the result of this roll is 1:parrot, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:skull, 7:gold, 8:parrot
    Then score for this player is 1100
    
  @TestRow85-88
  Scenario: roll 2 skulls, 3 parrots, 3 golds   put 3 golds in chest
    				then rerolls 3 parrots and get 2 diamonds 1 gold    put gold in chest (now 4)
      			then reroll 2 diamonds and get 1 skull 1 gold     SC for chest only = 400 + 200 = 600
            also interface reports death & end of turn
    Given A player get a Tresure Chest card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Skull", 2:"Skull", 3:"Parrot", 4:"Parrot", 5:"Parrot", 6:"Gold", 7:"Gold", 8:"Gold"
    And put dice "6,7,8" in chest
    And reroll dice "3,4,5"
    And the result of this roll is 1:skull, 2:skull, 3:diamond, 4:diamond, 5:gold, 6:gold, 7:gold, 8:gold
    And put dice "5,6,7,8" in chest
    And reroll dice "3,4"
    And the result of this roll is 1:skull, 2:skull, 3:skull, 4:gold, 5:gold, 6:gold, 7:gold, 8:gold
    Then the player die and end of turn with 600 point
