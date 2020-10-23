Feature: Sorceress Test
  I want to use this feature file to test row 70-72.

  @TestRow70
  Scenario: roll 2 skulls, reroll one of them due to sorceress, then go to next round of turn
    Given A player get a sorceress card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Skull", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Skull", 6:"Parrot", 7:"Parrot", 8:"Sword"
    Then dice "1" can be reroll
    
  @TestRow71
  Scenario: roll no skulls, then next round roll 1 skull and reroll for it, then score 
    Given A player get a sorceress card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Monkey", 6:"Parrot", 7:"Parrot", 8:"Sword"
    And reroll dice "1,2"
    And the result of this roll is 1:skull, 2:monkey, 3:monkey, 4:sword, 5:monkey, 6:parrot, 7:parrot, 8:sword
    Then dice "1" can be reroll
    
  @TestRow72
  Scenario: roll no skulls, then next round roll 1 skull and reroll for it, then go to next round
   Given A player get a sorceress card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Monkey", 6:"Parrot", 7:"Parrot", 8:"Sword"
    And reroll dice "1,2"
    And the result of this roll is 1:monkey, 2:skull, 3:monkey, 4:sword, 5:monkey, 6:parrot, 7:parrot, 8:sword
    Then dice "2" can be reroll

