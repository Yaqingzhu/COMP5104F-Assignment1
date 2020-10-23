Feature: Full Chest test
  I want to use this file to test row 91-97

  @TestRow91
  Scenario: first roll gets 3 monkeys, 3 swords, 1 diamond, 1 parrot, FC: Gold, SC=400  (ie no bonus)
    Given A player get a gold card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Diamond", 8:"Parrot"
    Then score for this player is 400
    
  @TestRow92
  Scenario: first roll gets 3 monkeys, 3 swords, 2 Golds, FC: captain, SC=(100+100+200+500)*2 =  1800
    Given A player get a captain card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Gold", 8:"Gold"
    Then score for this player is 1800
    
  @TestRow93
  Scenario: first roll gets 3 monkeys, 4 swords, 1 diamond, FC: Gold, SC=1000 (ie 100++200+100+100+bonus)
    Given A player get a gold card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Sword", 8:"Diamond"
    Then score for this player is 1000
 
  @TestRow94-96
  Scenario: FC: 2 sword sea battle, first  roll:  4 monkeys, 1 sword, 2 parrots and a Gold then reroll 2 parrots and get Gold and 2nd sword score is: 200 (Golds) + 200 (monkeys) + 300 (swords of battle) + 500 (full chest) = 1200 
    Given A player get 2 sword sea battle card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Monkey", 5:"Sword", 6:"Parrot", 7:"Parrot", 8:"Gold"
    And reroll dice "6,7"
    And the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:monkey, 5:sword, 6:gold, 7:sword, 8:gold
    Then score for this player is 1200
    
  @TestRow97
  Scenario: FC: monkey business and RTS: 2 monkeys, 1 parrot, 2 golds, 3 diamonds   SC 1200 (bonus)
    Given A player get a monkey business card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Parrot", 4:"Gold", 5:"Gold", 6:"Diamond", 7:"Diamond", 8:"Diamond"
    Then score for this player is 1200