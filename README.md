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
+ Add Trap (Lava) who kill pacman ( Tile Mapping)
+ Add better Lava Sprite
+ Modify Entree point for map (remove from tile mapping to map param)
+ Add Class Sound ( Play Background music loop (pacman intro) and add soundplayer for sound ( death)

26 July :
Find Sound for the Game :
+ Gain life 
+ Intro and next mission level music
+ eat a point 
+ eat a bonus 
+ eat a normal ghost 
+ use dash
+ upgrade dash
+ downgrade dash
+ refill dash
+ eat ghostbuster bonus
+ Pacaman death

27 July :
+ When weak ghost are near to come back to life, they switch between white and blue color and emit a warning sound
+ when dead ghost are near to come back to life, they emit a warning sound
+ Add Death Animation (correspond to the sound length)
+ When dash through one side of the map to another, the player is replaced a the correct position ( if dash is 2 block away and the player is next to the wall, then he will appears at the 2nd block a the other side of the map )
+ Modify the lifeup Image to be coherent with the rest of the level
+ Add Map LavaMaze

28 July : 
+ Add Moving Trap on a specific pattern ( Living Armor extends Ghost but goes on straight line for a round trip and are immortal)
+ Add Map Lava Circuit 
+ Player can't dash during the death animation
+ Add easy way to put special ghost inside map
+ Add basic visual for the dashcounter
+ Add Sound when dash counter is empty

29 July :
+ Add Menu Screen
+ Add Font for text
+ put the Main outside of the JFrame class
+ Add personal JButton
+ Game is launch 1 times to show the level then when the player press 's', the game begin

30 July :
+ Add New game
+ Add Select level 
+ All image are transparent now
+ Add Rules Panel (Common Rules, Controls)

31 July :
+ Add Bestiary to the Rules panel + Image
+ Show bonus on the Rules
+ On the Menu, Options panel will let the player choose the color he want for the board game 
 
01 August :
+ Add comment to the project
+ When Escape is pressed ingame : propose to go back to the main menu or resume
+ When Pacman has no life left, Game Over screen is shown with the socre and the possibility to go back to the main menu or restart the current level


TODO :

+ Add Map(Warping puzzle etc)
+ Add Score Serialization
+ Add Score Panel

Idea for improvement :
+  Boss Ghost map Scrolling ?
+  Ghost Mimic player ( 5 tile delay ? )
+  Add Pathfinding depending on the map for special Ghost
+ Slow Motion hotkey to aid the player (and Slowmotion Gauge )
+ Possibility to have different size of Map
+ Add Portail/Warper into map
+ 2 Player ? 
+ implement Controler XBOX360 ?
+ personalize hotkey
+ Choose player ( PacMan / PacWoman)
+ Mode Time Attack ( implement visuals Timer)
+ Tutorial Map access

Minor adjustement :

+ GhostBusters can pop even if the map have no ghost


