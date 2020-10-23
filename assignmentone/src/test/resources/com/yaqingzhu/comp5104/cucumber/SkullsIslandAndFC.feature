Feature: Skulls Island and Skull Fortune Cards Test 
  I want to use this file to test row 100-106

  @TestRow100
  Scenario: the player die by rolling one skull and having a FC with two skulls
    Given A player get a skullcardtwo card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Skull", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Parrot", 8:"Diamond"
    Then the player die and end of turn
    
  @TestRow101
  Scenario: the player die by rolling 2 skulls and having a FC with 1 skull
    Given A player get a skullscard card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Skull", 2:"Skull", 3:"Monkey", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Parrot", 8:"Diamond"
    Then the player die and end of turn  
 
  @TestRow102
  Scenario: the player roll 5 skulls with FC: Captain => -1000 for all other players  (Captain doubles the penalty)
    Given A player get a captain card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Skull", 2:"Skull", 3:"Skull", 4:"Skull", 5:"Skull", 6:"Diamond", 7:"Sword", 8:"Sword"
    And the player goes to skulls island 
    Then deduct 1000 for all other players
   
  @TestRow103
  Scenario: the player roll 2 skulls AND have a FC with two skulls: roll 2 skulls next roll, then 1 skull => -700 
    Given A player get a skullcardtwo card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Skull", 2:"Skull", 3:"Monkey", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Diamond", 8:"Parrot"
    And reroll dice "3,4"
    And the result of this roll is 1:skull, 2:skull, 3:skull, 4:skull, 5:sword, 6:sword, 7:diamond, 8:parrot
    And reroll dice "5,6"
    And the result of this roll is 1:skull, 2:skull, 3:skull, 4:skull, 5:skull, 6:sword, 7:diamond, 8:parrot
    Then deduct 700 for all other players

  @TestRow104
  Scenario: the player roll 3 skulls AND have a FC with two skulls: roll no skulls next roll => -500 
    Given A player get a skullcardtwo card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Skull", 2:"Skull", 3:"Skull", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Parrot", 8:"Diamond"
    And reroll dice "5,6"
    And the result of this roll is 1:skull, 2:skull, 3:skull, 4:sword, 5:parrot, 6:gold, 7:diamond, 8:sword
    Then deduct 500 for all other players    
     
  @TestRow105
  Scenario: the player roll 3 skulls AND have a FC with 1 skull: roll 1 skull next roll then none => -500
    Given A player get a skullscard card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Skull", 2:"Skull", 3:"Skull", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Parrot", 8:"Diamond"
    And reroll dice "4,5"
    And the result of this roll is 1:skull, 2:skull, 3:skull, 4:skull, 5:sword, 6:sword, 7:parrot, 8:diamond
    And reroll dice "5,6"
    And the result of this roll is 1:skull, 2:skull, 3:skull, 4:skull, 5:sword, 6:parrot, 7:parrot, 8:diamond
    Then deduct 500 for all other players
   
  @TestRow106
  Scenario: show deduction received cannot make your score negative
    Given A player get a skullscard card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Skull", 2:"Skull", 3:"Skull", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Parrot", 8:"Diamond"
    Then the other players score is not negative