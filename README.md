# pokerHand
 
Decription:

The job is to compare two pairs of poker hands to indicate which pair has a higher rank. Each pair has 5 cards in the hand.
There are four suits in one deck: clubs/C, diamonds/D, hearts/H and sprades/S. Each suit has 13 cards with face value: 2, 3 , 4, 5, 6, 7 ,8, 9, 10, J, Q, K, A from lowest to highest, which J is short for Jack, Q is for Queen, K is for King and A is for Ace with value, 11, 12, 13, 14.
For exampe - for one card:
2H - 2 is face value and H is short for heart


Rule/ranks: lowest to highest


High Card: cards with no two same cards in different suit. For example: 2S, 3S, 6S, 7D, 8H. 

One pair: a pair in 5 card. For example: 2H, 2D, 5S, 10C, 9H.

Two pairs: two paris of cards with the same value. For example, 2s, 2d, 5s, 5h, 10h

Three of a kind: Example: 8S, 8D, 8H, 5H, AS

Straight: Example, 5S, 6H, 7C, 8S, 9D

Flush: all cards in the same suit, but order is not consecutive. Example: 5H, 8H, 9H, JH, KH

Full house: 5S, 5C, 5D, AH, AS

Four of a kind: Example, 8S, 8D, 8H, 8C, KS

Straight Flush: all in same suit and order is consectuive. For example, 6H, 7H, 8H, 9H, 10H


If the two pairs are in the same rank, compare the highest value. For two pairs, compare the next value of a pair. 


Instruction to use PokerHand package:

Example:

Card[] card1 = { new Card("H", "2"), new Card("D", "3"), new Card("S", "5"), new Card("C", "9"),	new Card("D", "K") };


Card[] card110 = { new Card("H", "2"), new Card("h", "3"), new Card("h", "5"), new Card("h", "9"),	new Card("D", "K") };


PokerHand hand1 = new PokerHand(card1, "Hank");

PokerHand hand110 = new PokerHand(card110, "John");

Play play = new Play(p1, p2);
		
play.getResult();
  
Test Result:

2S,2D,8C,9H,9H,

2H,3C,3D,4D,5S,

Hank wins. -TWOPAIRS: 9

3D,6D,10D,12D,14D,

2H,3D,5S,9C,13D,

Hank wins. -FLUSH: Ace

2D,3S,8C,8H,8H,

2H,3C,4D,4D,5S,

Hank wins. -THREEOFAKIND: 8

2H,3D,5S,9C,13D,

8H,9H,10H,11H,12H,

John wins. -STRAIGHTFLUSH: Queen

2H,3D,5S,9C,13D,

8H,8C,8H,9S,15D,

John wins. -THREEOFAKIND: 8

2D,3S,8C,8H,8H,

10S,12D,12H,12C,12D,

John wins. -FOUROFAKIND: Queen

9H,9D,9C,9D,10S,


10S,12D,12H,12C,12D,

John wins. -FOUROFAKIND: Queen over 9

 
 //test result for Play class
 
 2H,3D,5S,9C,13D,
 HIGHCARD  100013
2H,3H,5H,9H,13D,
 HIGHCARD  100013
2H,2C,3D,4D,5S,
 ONEPAIR  200002
2H,3C,3D,4D,5S,
 ONEPAIR  200003
2H,3C,4D,4D,5S,
 ONEPAIR  200004
2H,3C,4D,11S,11D,
 ONEPAIR  200011
2C,8S,8D,10D,10H,
 TWOPAIRS  300010
2H,2D,8S,8D,10C,
 TWOPAIRS  300008
2S,2D,8C,9H,9H,
 TWOPAIRS  300009
2D,3S,8C,8H,8H,
 THREEOFAKIND  400008
2D,8C,8H,8H,9S,
 THREEOFAKIND  400008
8H,8C,8H,9S,15D,
 THREEOFAKIND  400008
2H,2C,2D,10D,10S,
 FULLHOUSE  700002
5H,5S,10D,10C,10D,
 FULLHOUSE  700010
9H,9D,9C,9D,10S,
 FOUROFAKIND  800009
10S,12D,12H,12C,12D,
 FOUROFAKIND  800012
8H,9S,10D,11C,12D,
 STRAIGHT  500012
1H,2S,3D,4C,5D,
 STRAIGHT  500005
2D,8D,9D,11D,12D,
 FLUSH  600012
3D,6D,10D,12D,14D,
 FLUSH  600014
8H,9H,10H,11H,12H,
 STRAIGHTFLUSH  900012
8D,9D,10D,11D,12D,
 STRAIGHTFLUSH  900012

