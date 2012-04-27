package oldmainwars;

import java.awt.Font;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlay extends BasicGameState
{
    //initializing variable
    public int stateId = 0;

    //number of units on each team
    public static int numUnits = 3;

    public static final int X_OFFSET = 20;
    public static final int Y_OFFSET = 10;

    public static final int MOVE = 0;
    public static final int ATTACK = 1;
    public static final int MAGIC = 2;
    public static final int HEAL = 3;
    
    //initialize tile width and height
    public static final int TILE_WIDTH = 80;
    public static final int TILE_HEIGHT = 64;

    //initializing image objects to null
    public Image gameMap = null;
    public Image gameInterface = null;
    public Image moveButton = null;
    public Image attackButton = null;
    public Image magicButton = null;
    public Image healButton = null;
    public Image passButton = null;
    public Image moveTile = null;
    public Image attackTile = null;
    public Image magicTile = null;
    public Image healTile = null;
    public Image blueDot = null;
    public Image redDot = null;
    public Image Tombstone = null;
    public Image downArrow = null;

    //initializing grid and sound effects to null
    public MyGrid grid = null;
    public Sound attackFX[] = new Sound[3];
    public Sound magicFX[] = new Sound[3];
    public Sound healFX[] = new Sound[3];
    
    //initializing button clicks to null 
    public boolean movePressed = false;
    public boolean attackPressed = false;
    public boolean magicPressed = false;
    public boolean healPressed = false;

    //initializing button position coordinates
    public static int moveX = 425;
    public static int moveY = 687;
    public static int attackX = 425;
    public static int attackY = 728;
    public static int magicX = 575;
    public static int magicY = 687;
    public static int healX = 575;
    public static int healY = 728;
    public static int passX = 730;
    public static int passY = 707;

    //initializing avatar images to null
    public static Image danteSheet1 = null;
    public static Image warriorSheet1 = null;
    public static Image morohtarSheet1 = null;
    public static Image archerSheet1 = null;
    public static Image mordecaiSheet1 = null;
    public static Image mageSheet1 = null;
    public static Image danteSheet2 = null;
    public static Image warriorSheet2 = null;
    public static Image morohtarSheet2 = null;
    public static Image archerSheet2 = null;
    public static Image mordecaiSheet2 = null;
    public static Image mageSheet2 = null;

    //initializing different animations to null;
    public Animation healAnimation = null;
    public Animation magicAnimation = null;
    public Animation attackAnimation = null;

    //initializing variable to keep track who's turn it is and whether they've moved/attacked yet
    public int turn;
    public boolean rightTeamSelected;
    public boolean movedYet;
    public boolean attackedYet;

    //initializing position tile variable
    public MyTile selectedTile = new MyTile();
    public MyTile pos1 = new MyTile();
    public MyTile pos2 = new MyTile();
    public MyTile damagedTile = new MyTile(); //last tile that was attacked/magiced/healed

    //initializing string to display tile location of avatar
    public String tileLoc = null;

    //initializing string to display damage done
    public String numbers = null;

    //initializing x,y location
    public int xLoc = 0;
    public int yLoc = 0;

    //initializing character array for troops
    Character unit[] = new Character[numUnits * 2];

    //variable to keep track of how much time has passed
    public int timer = 0;
    public int turnTimer = 0;

    //the last action taken, so we know which animation to show
    public int lastAction = 0;

    //initializing variable to keep track of victor
    public static int victor = 0;

    private enum STATES
    {
        START_GAME_STATE
    }

    private STATES currentState = null;

    //initialize font variables
    Font font = null;
    TrueTypeFont trueTypeFont = null;


    //constructor
    public GamePlay(int stateId)
    {
        this.stateId = stateId;
    }

    //get state id
    @Override
    public int getID()
    {
        return stateId;
    }

    //function to initialize images
    @Override
    public void init(GameContainer gc, StateBasedGame sb) throws SlickException
    {
        //initializing game interface
        gameInterface = new Image("images/interface.png");

        //making avatar image variables equal to their avatar sprite image file
        danteSheet1 = new Image("images/warriorSprite.png");
        warriorSheet1 = new Image("images/regular_warrior.png");
        morohtarSheet1 = new Image("images/archerSprite.png");
        archerSheet1 = new Image("images/regular_archer.png");
        mordecaiSheet1 = new Image("images/mageSprite.png");
        mageSheet1 = new Image("images/regular_mage.png");
        danteSheet2 = new Image("images/Copy of warriorSprite.png");
        warriorSheet2 = new Image("images/Copy of regular_warrior.png");
        morohtarSheet2 = new Image("images/Copy of archerSprite.png");
        archerSheet2 = new Image("images/Copy of regular_archer.png");
        mordecaiSheet2 = new Image("images/Copy of mageSprite.png");
        mageSheet2 = new Image("images/Copy of regular_mage.png");

        //initializing move,attack,magic, and heal tile images
        moveTile = new Image("images/move_tile.png");
        attackTile = new Image("images/attack_tile.png");
        magicTile = new Image("images/magic_tile.png");
        healTile = new Image("images/heal_tile.png");

        //making game button image variables equal to their button image file
        moveButton = new Image("images/moveButton.png");
        attackButton = new Image("images/attackButton.png");
        magicButton = new Image("images/magicButton.png");
        healButton = new Image("images/healButton.png");
        passButton = new Image("images/passButton.png");
        
        Tombstone = new Image("images/Tombstone.png");

        //getting images for blue and red dot to allow player to see if they can move and/or attack
        Image dots = new Image("images/dots.png");
        blueDot = dots.getSubImage(60, 0, 58, 58);
        redDot = dots.getSubImage(0, 0, 58, 58);
        blueDot = blueDot.getScaledCopy(.3f);
        redDot = redDot.getScaledCopy(.3f);
        
        //initializing down arrow image
        downArrow = new Image("images/down_arrow.png");

        //set up animations
        setAnimations();
  
        //initializing game grid
        grid = new MyGrid(10,10, TILE_HEIGHT, TILE_WIDTH);
        
        //setting string location variable to empty
        tileLoc = "";
        numbers = "";
        
        //initializing sound effects to play
        attackFX[0] = new Sound("sounds/attack1.wav");
        attackFX[1] = new Sound("sounds/attack2.wav");
        attackFX[2] = new Sound("sounds/attack3.wav");
        magicFX[0] = new Sound("sounds/magic1.wav");
        magicFX[1] = new Sound("sounds/magic2.wav");
        magicFX[2] = new Sound("sounds/magic3.wav");
        healFX[0] = new Sound("sounds/heal1.wav");
        healFX[1] = new Sound("sounds/heal2.wav");
        healFX[2] = new Sound("sounds/heal3.wav");
        
        //initialization of font
        font = new Font("images/abaddon.TTF", Font.BOLD, 30);
    	trueTypeFont = new TrueTypeFont(font, true);
    }

    //function to enter state
    @Override
    public void enter(GameContainer gc, StateBasedGame sb) throws SlickException
    {
        super.enter(gc, sb);
        
        //delay
        timer = 200;
        
        //Clears game grid and resets occupied positions
        for(int y = 0; y < 10; y++)
        {
            for(int x = 0; x < 10; x++)
            {
            	pos2 = grid.GetTile(x, y);
            	pos2.SetOccupiedFalse();
            	pos2.SetOccupiedBy(-1);
            }
        }

        // Player 1 Character Selection
        if (AvatarSelection.getAvatar() == 1) //dante selected
        {
            unit[0] = new Character(OldMainWars.Dante, 1);
            unit[1] = new Character(OldMainWars.Mage, 1);
            unit[2] = new Character(OldMainWars.Archer, 1);
        }
        
        else if (AvatarSelection.getAvatar() == 2) //mordecai selected
        {
            unit[0] = new Character(OldMainWars.Mordecai, 1);
            unit[1] = new Character(OldMainWars.Archer, 1);
            unit[2] = new Character(OldMainWars.Warrior, 1);
        }
        
        else //if (AvatarSelection.getAvatar() == 3) //morohtar selected
        {
            unit[0] = new Character(OldMainWars.Morohtar, 1);
            unit[1] = new Character(OldMainWars.Warrior, 1);
            unit[2] = new Character(OldMainWars.Mage, 1);
        }

        // Player 2 Character Selection
        if (AvatarSelection2.getAvatar() == 1) //dante selected
        {
            unit[3] = new Character(OldMainWars.Dante, 2);
            unit[4] = new Character(OldMainWars.Mage, 2);
            unit[5] = new Character(OldMainWars.Archer, 2);
        }
        
        else if (AvatarSelection2.getAvatar() == 2) //mordecai selected
        {
            unit[3] = new Character(OldMainWars.Mordecai, 2);
            unit[4] = new Character(OldMainWars.Archer, 2);
            unit[5] = new Character(OldMainWars.Warrior, 2);
        }
        
        else //if (AvatarSelection2.getAvatar() == 3) //morohtar selected
        {
            unit[3] = new Character(OldMainWars.Morohtar, 2);
            unit[4] = new Character(OldMainWars.Warrior, 2);
            unit[5] = new Character(OldMainWars.Mage, 2);
        }
        
        // get map selected in map selection and loads it
        if (MapSelection.getMap() == 1)
        {
            map1Setup();
        }
        
        else if (MapSelection.getMap() == 2)
        {
            map2Setup();
        }
        
        else //if (MapSelection.getMap() == 3)
        {
            map3Setup();
        }

        //sets turn equal to the loser of the previous game (player 1 by default)
        if (GamePlay.getVictor() == 1)
            turn = 2;
        else //if (GamePlay.getVictor() == 2)
            turn = 1;
        
        //initialize variables to false
        rightTeamSelected = false;
        movedYet = false;
        attackedYet = false;

        //Start game
        currentState = STATES.START_GAME_STATE;
    }

    //function to draw images on screen
    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
    {
        //Display Map
        gameMap.draw(0,0);

        //Display Interface
        gameInterface.draw(80, 666);

        //Display Buttons
        moveButton.draw(moveX, moveY);
        attackButton.draw(attackX, attackY);
        magicButton.draw(magicX, magicY);
        healButton.draw(healX, healY);
        passButton.draw(passX, passY);

        //draw each unit and draw it's  HUD in the interface if it's selected
        for (int i = 0; i < (numUnits * 2); i++)
        {
            if (unit[i].isAlive())        
                unit[i].draw();
                        
            //Unit died and put a tombstone and occupy space
            else
            {
            	Tombstone.draw(unit[i].getXCoordinate(), unit[i].getYCoordinate());
            	pos1 = grid.FindTile(unit[i].getXCoordinate(), unit[i].getYCoordinate());
                pos1.SetOccupiedTrue();
                pos1.SetOccupiedBy(-1);
            }
            
            if ((unit[i].isSelected()) && unit[i].isAlive())
            {
                unit[i].drawHUD(g);
                downArrow.draw(unit[i].getXCoordinate(),unit[i].getYCoordinate()-30);
            }
        }

        //display available movement squares if a unit is selected and move button pressed
        if(movePressed)
        {
            for(int y = 0; y < 10; y++)
            {
                for(int x = 0; x < 10; x++)
                {
                    pos2 = grid.GetTile(x, y);
                    if(inRange(selectedTile, pos2, MOVE) && (!pos2.GetOccupied()))
                    {
                    	
                        moveTile.draw(pos2.GetX(), pos2.GetY());
                    }
                }
            }
        }

        //displays available squares for attack if a unit is selected and attack button pressed
        if(attackPressed)
        {
            for(int y = 0; y < 10; y++)
            {
                for(int x = 0; x < 10; x++)
                {
                    pos2 = grid.GetTile(x, y);
                    if(inRange(selectedTile, pos2, ATTACK))
                    {
                        attackTile.draw(pos2.GetX(), pos2.GetY());
                    }
                }
            }
        }

         //displays available squares for magic attack if a unit is selected and magic button pressed
        if(magicPressed)
        {
            for(int y = 0; y < 10; y++)
            {
                for(int x = 0; x < 10; x++)
                {
                    pos2 = grid.GetTile(x, y);
                    if(inRange(selectedTile, pos2, MAGIC))
                    {
                        magicTile.draw(pos2.GetX(), pos2.GetY());
                    }
                }
            }
        }

        //displays available squares for heal if a unit is selected and heal button pressed
        if(healPressed)
        {
            for(int y = 0; y < 10; y++)
            {
                for(int x = 0; x < 10; x++)
                {
                    pos2 = grid.GetTile(x, y);
                    if(inRange(selectedTile, pos2, HEAL))
                    {
                        healTile.draw(pos2.GetX(), pos2.GetY());
                    }
                }
            }
        }

        // display blue dot if player hasn't moved yet this turn
        if (!movedYet)
            blueDot.draw(760, 645);

        // display red dot if player hasn't attacked/healed yet this turn
        if (!attackedYet)
            redDot.draw(780, 645);

        //render string for tile location
        g.drawString(tileLoc, 360, 645);

        //display appropriate damage/animation for a short period of time
        if (timer < 200)
        {
           	if (lastAction == ATTACK)
           	{
           		attackAnimation.draw(damagedTile.GetX(), damagedTile.GetY());
           	}
           	else if (lastAction == MAGIC)
           	{
               	magicAnimation.draw(damagedTile.GetX(), damagedTile.GetY());
           	}
           	else if (lastAction == HEAL)
           	{
               	healAnimation.draw(damagedTile.GetX(), damagedTile.GetY());
           	}
           		
           	g.drawString(numbers, damagedTile.GetX(), damagedTile.GetY());
        }
        
        //displays who turn it is at the start of the turn
        if(turn == 1 && turnTimer < 100)
        	trueTypeFont.drawString(275, 300, "Player One Turn");
        
        if(turn == 2 && turnTimer < 100)
        	trueTypeFont.drawString(275, 300, "Player Two Turn");

        //increment timer variables
        timer++;
        turnTimer++;
    }

    //function to update screen
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
    	//initialize variables
        Input input = gc.getInput();

        int mouseX = 0;
        int mouseY = 0;
        
        //sound
        OldMainWars.handleMusic(gc);
        

        //'M' key pressed while right team is selected
        if ((input.isKeyPressed(Input.KEY_M)) && (selectedTile.GetOccupied()) && (rightTeamSelected) && (!movedYet))
        {
            movePressed();
        }

        //'A' key pressed while right team is selected
        if ((input.isKeyPressed(Input.KEY_A)) && (selectedTile.GetOccupied()) && (rightTeamSelected) && (!attackedYet))
        {
            attackPressed();
        }

        //'G' key pressed while right team is selected
        if ((input.isKeyPressed(Input.KEY_G)) && (selectedTile.GetOccupied()) && (rightTeamSelected) && (!attackedYet))
        {
            magicPressed();
        }

        //'H' key pressed while right team is selected
        if ((input.isKeyPressed(Input.KEY_H)) && (selectedTile.GetOccupied()) && (rightTeamSelected) && (!attackedYet))
        {
            healPressed();
        }

        //'P' key pressed 
        if (input.isKeyPressed(Input.KEY_P))
        {
            passPressed();
        }

        //function for when mouse is pressed
        if((input.isMousePressed(Input.MOUSE_LEFT_BUTTON)))
        {
            //gets mouse location on screen
            mouseX = input.getMouseX();
            mouseY = input.getMouseY();

            // tile selected and no button pressed
            if(mouseX <= 800 && mouseY <= 640 && !movePressed && !attackPressed && !magicPressed && !healPressed)
            {
                selectedTile = grid.FindTile(mouseX, mouseY);
                xLoc = grid.GetXLoc(mouseX);
                yLoc = grid.GetYLoc(mouseY);

                // if selected tile is occupied
                if (selectedTile.GetOccupied())
                {
                    // select that unit, unselect all other units
                    for (int i = 0; i < (numUnits * 2); i++)
                    {
                        if (selectedTile.GetOccupiedBy() == i)
                            unit[i].setSelectedTrue();
                        else
                            unit[i].setSelectedFalse();
                    }

                    //checks to see if the selected unit is on the player's team whose turn it is
                    if (((int)selectedTile.GetOccupiedBy()/numUnits) == (turn - 1))
                    {
                        rightTeamSelected = true;
                    }
                    else
                    {
                        rightTeamSelected = false;
                    }
                }
                // empty tile selected, all units deselected
                else
                {
                    for (int i = 0; i < (numUnits * 2); i++)
                    {
                        unit[i].setSelectedFalse();
                        rightTeamSelected = false;
                    }
                }

                //Display tile location and who's turn it is
                tileLoc = "Tile: (" + yLoc + "," + xLoc + ") Player " + turn + " turn.";
            }
            // move button pressed
            else if((moveX < mouseX && mouseX < moveX + moveButton.getWidth()) && (moveY < mouseY && mouseY < moveY + moveButton.getHeight()) && (selectedTile.GetOccupied()) && (rightTeamSelected) && (!movedYet))
            {
                movePressed();
            }
            // attack button pressed
            else if((attackX < mouseX && mouseX < attackX + attackButton.getWidth()) && (attackY < mouseY && mouseY < attackY + attackButton.getHeight()) && (selectedTile.GetOccupied()) && (rightTeamSelected) && (!attackedYet))
            {
                attackPressed();
            }
            // magic button pressed
            else if((magicX < mouseX && mouseX < magicX + magicButton.getWidth()) && (magicY < mouseY && mouseY < magicY + magicButton.getHeight()) && (selectedTile.GetOccupied()) && (rightTeamSelected) && (!attackedYet))
            {
                magicPressed();
            }
            // heal button pressed
            else if((healX < mouseX && mouseX < healX + healButton.getWidth()) && (healY < mouseY && mouseY < healY + healButton.getHeight()) && (selectedTile.GetOccupied()) && (rightTeamSelected) && (!attackedYet))
            {
                healPressed();
            }
            // pass button pressed
            else if((passX < mouseX && mouseX < passX + passButton.getWidth()) && (passY < mouseY && mouseY < passY + passButton.getHeight()))
            {
                passPressed();
            }
            else if(mouseX <= 800 && mouseY <= 640 && movePressed) // move the selected unit
            {
                Action(selectedTile, input, MOVE);
            }
            else if(mouseX <= 800 && mouseY <= 640 && attackPressed) // physical attack with selected unit
            {
                Action(selectedTile, input, ATTACK);
            }
            else if(mouseX <= 800 && mouseY <= 640 && magicPressed) //magic attack with selected unit
            {
                Action(selectedTile, input, MAGIC);
            }
            else if(mouseX <= 800 && mouseY <= 640 && healPressed) // heal with selected unit
            {
                Action(selectedTile, input, HEAL);
            }
            else if(!movePressed && !attackPressed && !magicPressed && !healPressed && (mouseY >= 600)) //nothing selected
            {
                tileLoc = "Player " + turn + " turn.";
            }
        }
        
        //If all Player 1 units are killed declares Player 2 the victor and vice versa
        if(!unit[0].isAlive() && !unit[1].isAlive() && !unit[2].isAlive())
        {
            
        	victor = 2;
            delay(25000);
            sb.enterState(OldMainWars.VictoryScreen);
            
        }        
        else if(!unit[3].isAlive() && !unit[4].isAlive() && !unit[5].isAlive())
        {     	
        	victor = 1;
        	delay(25000);
        	sb.enterState(OldMainWars.VictoryScreen);
        }
        

    }
  //****************************************************************  
    public void delay (int howLong) // delay function to waste time
    {
    	for (int i = 1 ; i <= howLong ; i++)
    	{
    		double garbage = Math.PI * Math.PI;
    	}
    }
    //**************************************************************** 

    //function to execute correct action chosen by player
    public void Action(MyTile pos1, Input input, int action)
    {
    	//initialize variables
        MyTile pos2;

        int mouseX;
        int mouseY;
        int occupiedBy;

        //checks to see if left mouse button has been pressed
        if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
        {
            //gets mouse coordinates
            mouseX = input.getMouseX();
            mouseY = input.getMouseY();

            // mouse is in the grid area
            if(mouseX <= 800 && mouseY <= 640)
            {
                pos2 = grid.FindTile(mouseX, mouseY);
                xLoc = grid.GetXLoc(mouseX);
                yLoc = grid.GetYLoc(mouseY);

                //pos2 is in range of pos1
                if (inRange(pos1, pos2, action))
                {
                    //checks to see if move has been selected
                    if (action == MOVE)
                    {
                        //tile unit wants to move to is empty
                        if(!pos2.GetOccupied())
                        {
                            occupiedBy = pos1.GetOccupiedBy();
                            pos1.SetOccupiedFalse();
                            pos1.SetOccupiedBy(-1); //-1 = empty

                            pos2.SetOccupiedTrue();
                            pos2.SetOccupiedBy(occupiedBy);

                            unit[occupiedBy].setCoordinates(pos2.GetX() + X_OFFSET, pos2.GetY() + Y_OFFSET);

                            numbers = "";

                            lastAction = MOVE;
                            movedYet = true;
                        }
                    }
                    
                    //checks to see if attack has been selected
                    else if (action == ATTACK) // physical attack
                    {
                        // attacked tile is occupied by another unit
                        if(pos2.GetOccupied() && (pos2.GetOccupiedBy() >= 0))
                        {
                            int damage = unit[pos1.GetOccupiedBy()].AttackDamage();
                            unit[pos2.GetOccupiedBy()].Damage(damage);

                            //total attack damage dealt
                            numbers = "-" + damage;

                            damagedTile = pos2;
                            resetTimer();

                            // if attacked unit died, make that tile unoccupied
                            if (unit[pos2.GetOccupiedBy()].isAlive() == false)
                            {
                                pos2.SetOccupiedFalse();
                                pos2.SetOccupiedBy(-1);
                            }

                            // play random attack sound effect
                            int rand = randomNumber();
                            attackFX[rand].play();
                            
                            lastAction = ATTACK;
                            attackedYet = true;
                        }
                    }
                    
                  //checks to see if magic has been selected
                    else if (action == MAGIC) // magic attack
                    {
                        // attacked tile is occupied by another unit
                        if(pos2.GetOccupied() && (pos2.GetOccupiedBy() >= 0) && (unit[pos1.GetOccupiedBy()].EnoughMP(10)))
                        {
                            int damage = unit[pos1.GetOccupiedBy()].CastSpell();
                            unit[pos2.GetOccupiedBy()].Damage(damage);

                            //Total magic damage dealt
                            numbers = "-" + damage;

                            damagedTile = pos2;
                            resetTimer();

                            // if attacked unit died, make that tile unoccupied
                            if (unit[pos2.GetOccupiedBy()].isAlive() == false)
                            {
                                pos2.SetOccupiedFalse();
                                pos2.SetOccupiedBy(-1);
                            }

                            // play random magic sound effect
                            int rand = randomNumber();
                            magicFX[rand].play();

                            lastAction = MAGIC;
                            attackedYet = true;
                        }
                    }
                    
                  //checks to see if heal has been selected
                    else if (action == HEAL && (pos2.GetOccupiedBy() >= 0))
                    {
                        // attacked tile is occupied
                        if(pos2.GetOccupied() && (pos2.GetOccupiedBy() >= 0) && (unit[pos1.GetOccupiedBy()].EnoughMP(10)))
                        {
                            int heal = unit[pos1.GetOccupiedBy()].CastSpell();
                            unit[pos2.GetOccupiedBy()].Healed(heal);

                            //total amount healed
                            numbers = "+" + heal;

                            damagedTile = pos2;
                            
                            resetTimer();

                            // play random heal sound effect
                            int rand = randomNumber();
                            healFX[rand].play();
                            
                            lastAction = HEAL;
                            attackedYet = true;
                        }
                    }
                }

                // switch to other players turn after a move and an attack/magic/heal
                if ((movedYet) && (attackedYet))
                {
                    handleTurn();
                }
            }
        }

        //deselect all buttons
        movePressed = false;
        attackPressed = false;
        magicPressed = false;
        healPressed = false;

        //displays whose turn it is
        tileLoc = "Player " + turn + " turn.";
     }

    //function to verify valid range for move, attack, magic, and heal
    public boolean inRange(MyTile pos1, MyTile pos2, int action)
    {
    	//initialize variables
        boolean inRange;

        int pos1_xvalue;
        int pos1_yvalue;
        int pos2_xvalue;
        int pos2_yvalue;

        int pos1_x;
        int pos1_y;
        int pos2_x;
        int pos2_y;

        //gets x,y coordinates
        pos1_xvalue = pos1.GetX();
        pos1_yvalue = pos1.GetY();
        pos2_xvalue = pos2.GetX();
        pos2_yvalue = pos2.GetY();

        //gets location on the grid based off x,y coordinates
        pos1_x = grid.GetXLoc(pos1_xvalue);
        pos1_y = grid.GetYLoc(pos1_yvalue);
        pos2_x = grid.GetXLoc(pos2_xvalue);
        pos2_y = grid.GetYLoc(pos2_yvalue);

        //calculates distance between two squares
        int x = pos1_x - pos2_x;
        int xSquared = x * x;

        int y = pos1_y - pos2_y;
        int ySquared = y * y;

        double xDistance = Math.sqrt(xSquared);
        double yDistance = Math.sqrt(ySquared);

        double distance = xDistance + yDistance;

        //checks if move has been selected
        if (action == MOVE)
        {
            if (distance <= unit[pos1.GetOccupiedBy()].getMovement())
                inRange = true;
            else
                inRange = false;
        }
        else if (action == ATTACK) // physical attack has been selected
        {
            if (distance <= unit[pos1.GetOccupiedBy()].getAttackRange())
                inRange = true;
            else
                inRange = false;
        }
        else if (action == MAGIC) // magic attack has been selected
        {
            if (distance <= unit[pos1.GetOccupiedBy()].getMagicRange())
                inRange = true;
            else
                inRange = false;
        }
        else //if (action == HEAL) heal has been selected
        {
            if (distance <= unit[pos1.GetOccupiedBy()].getHealRange())
                inRange = true;
            else
                inRange = false;
        }
        return inRange;
    }

    //function to handle player turns
    public void handleTurn()
    {
    	//initialize variables to false
        rightTeamSelected = false;
        movedYet = false;
        attackedYet = false;

        //determine who's turn it is
        if (turn == 1)
            turn = 2;
        else
            turn = 1;
        
        //reset timer
        turnTimer = 0;
    }
    
    //function to get the victor
    public static int getVictor()
    {
        return victor;
    }
    
    //Function to set up the grid to be used with each map
    public void map1Setup() throws SlickException
    {
            gameMap = new Image("images/map1_2.png");
            
            //Makes the center area unable to be moved to
            pos1 = grid.GetTile(4, 4);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            pos1 = grid.GetTile(4, 5);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            pos1 = grid.GetTile(5, 4);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            pos1 = grid.GetTile(5, 5);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            unit[0].setCoordinates(TILE_WIDTH * 4 + X_OFFSET, 0 + Y_OFFSET);

            pos1 = grid.FindTile(unit[0].getXCoordinate(), unit[0].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(0);
            
            unit[1].setCoordinates(TILE_WIDTH * 5 + X_OFFSET, 0 + Y_OFFSET);

            pos1 = grid.FindTile(unit[1].getXCoordinate(), unit[1].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(1);
            
            unit[2].setCoordinates(TILE_WIDTH * 3 + X_OFFSET, 0 + Y_OFFSET);

            pos1 = grid.FindTile(unit[2].getXCoordinate(), unit[2].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(2);
            
            unit[3].setCoordinates(TILE_WIDTH * 4 + X_OFFSET, 576 + Y_OFFSET);

            pos1 = grid.FindTile(unit[3].getXCoordinate(), unit[3].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(3);
            
            unit[4].setCoordinates(TILE_WIDTH * 5 + X_OFFSET, 576 + Y_OFFSET);

            pos1 = grid.FindTile(unit[4].getXCoordinate(), unit[4].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(4);
            
            unit[5].setCoordinates(TILE_WIDTH * 3 + X_OFFSET, 576 + Y_OFFSET);

            pos1 = grid.FindTile(unit[5].getXCoordinate(), unit[5].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(5);
    }
    
  //Function to set up the grid to be used with each map
    public void map2Setup() throws SlickException
    {
            gameMap = new Image("images/map2_2.png");
            
            pos1 = grid.GetTile(4, 1);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            pos1 = grid.GetTile(5, 1);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            pos1 = grid.GetTile(4, 2);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            pos1 = grid.GetTile(5, 2);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            pos1 = grid.GetTile(4, 7);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            pos1 = grid.GetTile(5, 7);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            pos1 = grid.GetTile(4, 8);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            pos1 = grid.GetTile(5, 8);
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(-1);
            
            unit[0].setCoordinates(TILE_WIDTH * 4 + X_OFFSET, 0 + Y_OFFSET);

            pos1 = grid.FindTile(unit[0].getXCoordinate(), unit[0].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(0);
            
            unit[1].setCoordinates(TILE_WIDTH * 5 + X_OFFSET, 0 + Y_OFFSET);

            pos1 = grid.FindTile(unit[1].getXCoordinate(), unit[1].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(1);
            
            unit[2].setCoordinates(TILE_WIDTH * 3 + X_OFFSET, 0 + Y_OFFSET);

            pos1 = grid.FindTile(unit[2].getXCoordinate(), unit[2].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(2);
            
            unit[3].setCoordinates(TILE_WIDTH * 4 + X_OFFSET, 576 + Y_OFFSET);

            pos1 = grid.FindTile(unit[3].getXCoordinate(), unit[3].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(3);
            
            unit[4].setCoordinates(TILE_WIDTH * 5 + X_OFFSET, 576 + Y_OFFSET);

            pos1 = grid.FindTile(unit[4].getXCoordinate(), unit[4].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(4);
            
            unit[5].setCoordinates(TILE_WIDTH * 3 + X_OFFSET, 576 + Y_OFFSET);

            pos1 = grid.FindTile(unit[5].getXCoordinate(), unit[5].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(5);
    }
    
  //Function to set up the grid to be used with each map
    public void map3Setup() throws SlickException
    {
            gameMap = new Image("images/map3.png");
            
            for(int y = 0;y < 10; y++)
            {
                pos1 = grid.GetTile(0,y);
                pos1.SetOccupiedTrue();
                pos1.SetOccupiedBy(-1);
                
                pos1 = grid.GetTile(9, y);
                pos1.SetOccupiedTrue();
                pos1.SetOccupiedBy(-1);
            }
            
            unit[0].setCoordinates( 0 + X_OFFSET, TILE_HEIGHT * 4 + Y_OFFSET);

            pos1 = grid.FindTile(unit[0].getXCoordinate(), unit[0].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(0);
            
            unit[1].setCoordinates(0 + X_OFFSET, TILE_HEIGHT * 3 + Y_OFFSET);

            pos1 = grid.FindTile(unit[1].getXCoordinate(), unit[1].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(1);
            
            unit[2].setCoordinates(0 + X_OFFSET, TILE_HEIGHT * 5 + Y_OFFSET);

            pos1 = grid.FindTile(unit[2].getXCoordinate(), unit[2].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(2);
            
            unit[3].setCoordinates(720 + X_OFFSET, TILE_HEIGHT * 4 + Y_OFFSET);

            pos1 = grid.FindTile(unit[3].getXCoordinate(), unit[3].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(3);
            
            unit[4].setCoordinates(720 + X_OFFSET, TILE_HEIGHT * 3 + Y_OFFSET);

            pos1 = grid.FindTile(unit[4].getXCoordinate(), unit[4].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(4);
            
            unit[5].setCoordinates(720 + X_OFFSET, TILE_HEIGHT * 5 + Y_OFFSET);

            pos1 = grid.FindTile(unit[5].getXCoordinate(), unit[5].getYCoordinate());
            pos1.SetOccupiedTrue();
            pos1.SetOccupiedBy(5);
    }

    //function for animations
    public void setAnimations() throws SlickException
    {
        Image sheet;
        Image image[] = new Image[3];

        //load heal animation
        sheet = new Image("images/healAnimation.png");
        image[0] = sheet.getSubImage(0, 0, 80, 64);
        image[1] = sheet.getSubImage(80, 0, 80, 64);
        image[2] = sheet.getSubImage(160, 0, 80, 64);
        healAnimation = new Animation(image, 555);

        //load magic animation
        sheet = new Image("images/magicAnimation.png");
        image[0] = sheet.getSubImage(0, 0, 80, 64);
        image[1] = sheet.getSubImage(80, 0, 80, 64);
        image[2] = sheet.getSubImage(160, 0, 80, 64);
        magicAnimation = new Animation(image, 200);

        //load attack animation
        sheet = new Image("images/attackAnimation.png");
        image[0] = sheet.getSubImage(0, 0, 80, 64);
        image[1] = sheet.getSubImage(80, 0, 80, 64);
        image[2] = sheet.getSubImage(160, 0, 80, 64);
        attackAnimation = new Animation(image, 444);
    }

    //move button pressed
    public void movePressed()
    {
        movePressed = true;
        attackPressed = false;
        magicPressed = false;
        healPressed = false;
        tileLoc = "Player " + turn + " Choose a tile to move to.";
    }

    //attack button pressed
    public void attackPressed()
    {
        attackPressed = true;
        movePressed = false;
        magicPressed = false;
        healPressed = false;
        tileLoc = "Player " + turn + " Choose a tile to physical attack.";
    }

    //magic button pressed
    public void magicPressed()
    {
        magicPressed = true;
        movePressed = false;
        attackPressed = false;
        healPressed = false;
        tileLoc = "Player " + turn + " Choose a tile to magic attack.";
    }

    //heal button pressed
    public void healPressed()
    {
        healPressed = true;
        movePressed = false;
        attackPressed = false;
        magicPressed = false;
        tileLoc = "Player " + turn + " Choose a tile to heal.";
    }

    //pass button pressed
    public void passPressed()
    {
        movePressed = false;
        attackPressed = false;
        magicPressed = false;
        healPressed = false;
        tileLoc = "Player " + turn + " passed.";

        handleTurn();

        tileLoc += "  Player " + turn + " turn.";
    }

    //reset timer to zero
    public void resetTimer()
    {
        timer = 0;
    }

    //create random number between 0 and 2
    public int randomNumber()
    {
        Random rand = new Random();

        return (rand.nextInt(3));
    }
}
