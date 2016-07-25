# Pacman

6 July : Create the Game from the Tutorial : 
http://zetcode.com/tutorials/javagamestutorial/pacman/

+  Add Loader map 
+  Entry Point for Ghost & Player
+  2 maps 
+  GainLife with 100 score 

  
7 July :

+ Add 2 Map ( Don't Fall + The Box) 
+ Reforme Class MazeLoader into Maze ( with acces to number Ghost)
+ Add name of the map InGame
+ Add Eatable Bonus

11 July :

+ Add 4 Bonus to eat to improve the Score 
+ Reforme the event of adding new Bonus
+ Add Animation after eating bonus
+ Correction of bug with pop of bonus

12 July : 

+ Add new eatable Bonus which give +1 life
+ Add new game mechanic with possibility of Dashing ( avoid ennemies and pass throught wall) with limit of the number of use
+ Add map Tuto to Learn how to use the Dash
+ Add new eatable Bonus for Upgrade the distance of the Dash ( limited by 1 for the moment)
+ Add new eatable Bonus for refill the Dash counter
+ Add the possibility of going out of the screen on the left will make you come from the right and etc *
+ Add Map for the use of "Warping out" the map
 
13 July : 

+ Ghost can pass from left to right of the map and etc*
+ Add MultPop location of Ghost
+ Each Map add a number of Dash given to the player dependent of the necessity of the level

14 July : 

+ Mechanic to place Bonus at the beginning of the map
+ Modify some map to place Bonus (Tuto Dash with bonus placed)
+ Clean Some code
+ Add Restart Level on key 'R' ( to prevent blocking in an area)
+ Add Swap Board Color on key 'C' (9 Color Available)
+ Make Compatible with Jar exportation
+ Balance the difficulty of the game with bonus 
+ Add Iconeimage for the JFrame
+ Add "Bonus" Decrease Dash

15 July :
+ Add better sprite movement ghost ( Color + looking in the direction they follow)
+ Add Dead and Weak state for ghost
+ Add Map LineRunner
+ Add Bonus to eat the ghost. When bonus "Ghost busters" is eaten, all ghost will be in weak mode so you can eat them
+ Add "delay" to go back to life for the ghost ( Weak and Dead)
+ Modify some map to add the Bonus Ghost Busters
+ Add point for eating ghost ( proportionaly)
+ Add some hotKey to help to test the game (T is to change the ghoststate, Y is for add life + 10 dash, P is to pause the game)

19 July : 
+ Create a new Class Ghost and rework entirely the Ghost Movement and DrawGhost
+ Clean a lot of code (~600 lines)
+ Correct some visual artefact with ghost
+ prepare to add comportement to ghost
 
20 July : 
+ Add PhaseGhost Class ( extends Ghost) which this ghost can pass through wall
+ Add ChaserGhost Class ( extends Ghost) which pursue the player ( V1 : basic implementation )
+ Add EscapeGhost Class ( extends Ghost) which run away from the player ( V1 : basic implementation )
+ Add BlockGhost Class ( extends Ghost) which try to be in the path of the player ( V1 : basic implementation )

21 July : 
+ Add PhaseChaserGhost Class ( extends Ghost) which this ghost chase the player through wall
+ Add PhaseEscapeGhost Class ( extends Ghost) which this ghost escape even the player through wall ( bug)
 
25 July :
+ Add Hotkey to go to previous level on I
+ Modify Hotkey to go to next level on O
+ Correct bug with deplacement of PhaseEscape Ghost
+ (Add PhaseBlockGhost Class ( extends Ghost) which this ghost block the player through wall)
+ Add Reward depending on the Ghost eated
+ Add finishing line "PacWoman"
+ Add Map ( Finishing line) map tuto finishing line

TODO :
+ Add Pathfinding depending on the map for special Ghost
+ Add Map(Warping puzzle etc)
+ Possibility to have different size of Map
+ Add Map All Tutorial
+ Add Score Serialization
+ Add Music
+ Improve swap Color board (Menu)
+ Add Portail/Warper into map
+ Add Trap insta-kill
+ Add Moving Trap on a specific pattern
+ Slow Motion hotkey to aid the player
+ Add some Gauge for the dashcounter (and Slowmotion counter )
+ Add Death Animation
+ add reward one special Ghost

Create Different Ghost with different habilities :
+  Boss Ghost map Scrolling ?
+  Ghost Mimic player ( 5 tile delay ? )

Add Menu Screen:
+ Select mode Normal (Score)
+ Mode Time Attack ( implement visuals Timer)
+ Tutorial Map access
+ Add Score Panel
+ Add Rules Panel

(2 Player ?) 
(implement Controler XBOX360 ?)


