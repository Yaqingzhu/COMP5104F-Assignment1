#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Caculate player score
  Player should be able to recieve score

  @TestRow43
  Scenario: Scoring first roll with nothing but 2 diamonds and 2 golds
    Given A player get a captain card 
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Diamond", 2:"Diamond", 3:"Gold", 4:"Gold", 5:"Parrot", 6:"Parrot", 7:"Sword", 8:"Sword"
    Then score for this player is 800

	@TestRow44
  Scenario: Scoring
  	Given A player get a gold card
    And  roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Skull", 4:"Skull", 5:"Parrot", 6:"Parrot", 7:"Sword", 8:"Sword"
    And reroll dice "5"
    And the result of this roll is 1:monkey, 2:monkey, 3:skull, 4:skull, 5:monkey, 6:parrot, 7:sword, 8:sword
    Then score for this player is 200
    
  @TestRow45
  Scenario: Scoring 
    Given A player get a gold card
    And  roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Parrot", 6:"Parrot", 7:"Sword", 8:"Sword" 
    Then score for this player is 300
    
  @TestRow46
  Scenario: Scoring
    Given A player get a gold card
    And  roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Skull", 5:"Parrot", 6:"Parrot", 7:"Sword", 8:"Sword" 
    And reroll dice "5"
    And the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:skull, 5:sword, 6:parrot, 7:sword, 8:sword
    Then score for this player is 300
    
  @TestRow47
  Scenario: Scoring
    Given A player get a gold card
    And  roll all 8 dices on the first roll
    When the result of first roll is 1:"Diamond", 2:"Diamond", 3:"Diamond", 4:"Monkey", 5:"Parrot", 6:"Parrot", 7:"Sword", 8:"Sword"
    Then score for this player is 500
    
  @TestRow48
  Scenario: Scoring
    Given A player get a diamond card
    And  roll all 8 dices on the first roll
    When the result of first roll is 1:"Gold", 2:"Gold", 3:"Gold", 4:"Gold", 5:"Parrot", 6:"Parrot", 7:"Sword", 8:"Sword" 
    Then score for this player is 700

	@TestRow49
  Scenario: Scoring
    Given A player get a gold card
    And  roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Parrot", 3:"Parrot", 4:"Parrot", 5:"Parrot", 6:"Sword", 7:"Sword", 8:"Sword" 
    Then score for this player is 400
 
#row50 and 51 , 52 similar
  @TestRow50
  Scenario: Scoring
    Given A player get a gold card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Gold", 2:"Gold", 3:"Gold", 4:"Monkey", 5:"Monkey", 6:"Sword", 7:"Sword", 8:"Sword" 
    And reroll dice "4,5"
    And the result of this roll is 1:gold, 2:gold, 3:gold, 4:monkey, 5:parrot, 6:sword, 7:sword, 8:sword
    And reroll dice "4,5"
    And the result of this roll is 1:gold, 2:gold, 3:gold, 4:sword, 5:monkey, 6:sword, 7:sword, 8:sword
    Then score for this player is 800
    
  @TestRow51
  Scenario: Scoring
    Given A player get a captain card 
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Gold", 2:"Gold", 3:"Gold", 4:"Monkey", 5:"Monkey", 6:"Sword", 7:"Sword", 8:"Sword" 
    And reroll dice "4,5"
    And the result of this roll is 1:gold, 2:gold, 3:gold, 4:monkey, 5:parrot, 6:sword, 7:sword, 8:sword
    And reroll dice "4,5"
    And the result of this roll is 1:gold, 2:gold, 3:gold, 4:sword, 5:monkey, 6:sword, 7:sword, 8:sword
    Then score for this player is 1200
    
  @TestRow52
  Scenario: Scoring
    Given A player get a diamond card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Parrot", 4:"Parrot", 5:"Monkey", 6:"Parrot", 7:"Sword", 8:"Sword" 
    And reroll dice "1,2,3,4,5"
    And the result of this roll is 1:sword, 2:sword, 3:parrot, 4:parrot, 5:monkey, 6:parrot, 7:sword, 8:sword
    And reroll dice "4,5"
    And the result of this roll is 1:sword, 2:sword, 3:parrot, 4:sword, 5:monkey, 6:parrot, 7:sword, 8:sword
    Then score for this player is 600

 #row53, 54, 55 56, 57  
  @TestRow53
  Scenario: Scoring
    Given A player get a diamond card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Monkey", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Sword", 6:"Monkey", 7:"Monkey", 8:"Monkey" 
    Then score for this player is 1100
    
  @TestRow54
  Scenario: Scoring
    Given A player get a diamond card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Parrot", 2:"Parrot", 3:"Parrot", 4:"Parrot", 5:"Parrot", 6:"Parrot", 7:"Parrot", 8:"Skull"
    Then score for this player is 2100
    
  @TestRow55
  Scenario: Scoring
    Given A player get a gold card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Gold", 2:"Gold", 3:"Gold", 4:"Gold", 5:"Gold", 6:"Gold", 7:"Gold", 8:"Gold" 
    Then score for this player is 5400  
    
	@TestRow56
  Scenario: Scoring
    Given A player get a diamond card 
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Gold", 2:"Gold", 3:"Gold", 4:"Gold", 5:"Gold", 6:"Gold", 7:"Gold", 8:"Gold"
    Then score for this player is 5400 
    
  @TestRow57
  Scenario: Scoring
    Given A player get a captain card 
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Sword", 2:"Sword", 3:"Sword", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Sword", 8:"Sword"
    Then score for this player is 9000 
    
  @TestRow58
  Scenario: Scoring
    Given A player get a gold card 
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Sword", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Gold", 6:"Monkey", 7:"Monkey", 8:"Gold"
    And reroll dice "1,4,5,8"
    And the result of this roll is 1:parrot, 2:monkey, 3:monkey, 4:monkey, 5:sword, 6:monkey, 7:monkey, 8:monkey
    And reroll dice "1,5"
    And the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:monkey, 5:monkey, 6:monkey, 7:monkey, 8:monkey
    Then score for this player is 4600 
    
  @TestRow59
  Scenario: Scoring
    Given A player get a diamond card 
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Diamond", 2:"Parrot", 3:"Monkey", 4:"Sword", 5:"Parrot", 6:"Monkey", 7:"Parrot", 8:"Sword"
    And reroll dice "2,3,5"
    And the result of this roll is 1:diamond, 2:skull, 3:monkey, 4:sword, 5:diamond, 6:monkey, 7:parrot, 8:sword
    Then score for this player is 400 
    
  @TestRow60
  Scenario: Scoring
    Given A player get a gold card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Diamond", 2:"Parrot", 3:"Monkey", 4:"Sword", 5:"Parrot", 6:"Monkey", 7:"Parrot", 8:"Sword"
    And reroll dice "2,3,5"
    And the result of this roll is 1:diamond, 2:diamond, 3:monkey, 4:sword, 5:diamond, 6:monkey, 7:parrot, 8:sword
    Then score for this player is 500
    
  @TestRow61
  Scenario: Scoring
    Given A player get a gold card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Gold", 2:"Parrot", 3:"Monkey", 4:"Sword", 5:"Sword", 6:"Monkey", 7:"Parrot", 8:"Sword"
    And reroll dice "2,3,4,5"
    And the result of this roll is 1:gold, 2:gold, 3:sword, 4:gold, 5:monkey, 6:monkey, 7:parrot, 8:sword
    Then score for this player is 600 
    
  @TestRow62
  Scenario: Scoring
    Given A player get a diamond card
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Gold", 2:"Parrot", 3:"Monkey", 4:"Sword", 5:"Sword", 6:"Monkey", 7:"Parrot", 8:"Sword"
    Then reroll dice "2,3,4,5"
    When the result of this roll is 1:gold, 2:gold, 3:sword, 4:gold, 5:monkey, 6:monkey, 7:parrot, 8:sword
    Then score for this player is 500 
    
  @TestRow63
  Scenario: Scoring
    Given A player get a gold card 
    And roll all 8 dices on the first roll
    When the result of first roll is 1:"Gold", 2:"Monkey", 3:"Monkey", 4:"Sword", 5:"Gold", 6:"Monkey", 7:"Monkey", 8:"Sword"
    Then score for this player is 600 
    
  @TestRow64
  Scenario:  Reject reroll 1 dice
  	Given A player get a gold card
  	And roll all 8 dices on the first roll
  	When the result of first roll is 1:"Sword", 2:"Sword", 3:"Sword", 4:"Sword", 5:"Sword", 6:"Sword", 7:"Sword", 8:"Parrot"
  	Then reroll dice "8"
  	Then interface reports not allowed rerolling only one dice
  	
    
     
