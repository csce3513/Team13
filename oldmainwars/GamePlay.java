package oldmainwars;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlay extends BasicGameState
{
	//initializing variable
    public int stateId = 0;

    public static int numUnits = 3;

    public static final int X_OFFSET = 20;
    public static final int Y_OFFSET = 10;

    public static final int MOVE = 0;
    public static final int ATTACK = 1;
    public static final int MAGIC = 2;
    public static final int HEAL = 3;

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    
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
    public Image moveTile = null;
    public Image attackTile = null;
    public Image magicTile = null;
    public Image healTile = null;

    //initializing grid and sound to null
    public MyGrid grid = null;
    public Sound blockFX = null;
    
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

    //initializing avatar images to null
    public static Image danteSheet = null;
    public static Image warriorSheet = null;
    public static Image morohtarSheet = null;
    public static Image archerSheet = null;
    public static Image mordecaiSheet = null;
    public static Image mageSheet = null;

    //initializing variable to keep track who's turn
    public int turn;
    public boolean rightTeamSelected;

    //initializing position tile variable
    public MyTile selectedTile = new MyTile();
    public MyTile pos1 = new MyTile();
    public MyTile pos2 = new MyTile();

    //initializing string to display tile location of avatar
    public String tileLoc = null;

    //initializing x,y location
    public int xLoc = 0;
    public int yLoc = 0;

    //initializing character array for troops
    Character unit[] = new Character[numUnits * 2];
    
    //initializing variable to keep track of victor
    public static int victor = 0;

    private enum STATES
    {
        START_GAME_STATE
    }

    private STATES currentState = null;

    public GamePlay(int stateId)
    {
        this.stateId = stateId;
    }

    @Override
    public int getID()
    {
        return stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sb) throws SlickException
    {
        System.out.println("GamePlay init");

        System.out.println("************************************************");
        System.out.println("-1:" + (int)-1/numUnits);
        System.out.println("0:" + (int)0/numUnits);
        System.out.println("1:" + (int)1/numUnits);
        System.out.println("2:" + (int)2/numUnits);
        System.out.println("3:" + (int)3/numUnits);
        System.out.println("4:" + (int)4/numUnits);
        System.out.println("5:" + (int)5/numUnits);
        System.out.println("************************************************");

        //making avatar image variables equal to their avatar sprite image file
        danteSheet = new Image("images/warriorSprite.png");
        warriorSheet = new Image("images/regular_warrior.png");
        morohtarSheet = new Image("images/archerSprite.png");
        archerSheet = new Image("images/regular_archer.png");
        mordecaiSheet = new Image("images/mageSprite.png");
        mageSheet = new Image("images/regular_mage.png");

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


        
        //initializing game grid
        grid = new MyGrid(10,10, TILE_HEIGHT, TILE_WIDTH);
        
        //setting string location variable to empty
        tileLoc = "";
        
        //initializing sound to play
        //blockFX  = new Sound("data/pulse.wav");
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sb) throws SlickException
    {
        super.enter(gc, sb);


        // Player 1 Character Selection
        if (AvatarSelection.getAvatar() == 1) //dante selected
        {
            unit[0] = new Character(OldMainWars.Dante);
            unit[1] = new Character(OldMainWars.Mage);
            unit[2] = new Character(OldMainWars.Archer);
        }
        else if (AvatarSelection.getAvatar() == 2) //mordecai selected
        {
            unit[0] = new Character(OldMainWars.Mordecai);
            unit[1] = new Character(OldMainWars.Archer);
            unit[2] = new Character(OldMainWars.Warrior);
        }
        else //if (AvatarSelection.getAvatar() == 3) //morohtar selected
        {
            unit[0] = new Character(OldMainWars.Morohtar);
            unit[1] = new Character(OldMainWars.Warrior);
            unit[2] = new Character(OldMainWars.Mage);
        }

        // Player 2 Character Selection
        if (AvatarSelection2.getAvatar() == 1) //dante selected
        {
            unit[3] = new Character(OldMainWars.Dante);
            unit[4] = new Character(OldMainWars.Mage);
            unit[5] = new Character(OldMainWars.Archer);
        }
        else if (AvatarSelection2.getAvatar() == 2) //mordecai selected
        {
            unit[3] = new Character(OldMainWars.Mordecai);
            unit[4] = new Character(OldMainWars.Archer);
            unit[5] = new Character(OldMainWars.Warrior);
        }
        else //if (AvatarSelection2.getAvatar() == 3) //morohtar selected
        {
            unit[3] = new Character(OldMainWars.Morohtar);
            unit[4] = new Character(OldMainWars.Warrior);
            unit[5] = new Character(OldMainWars.Mage);
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

        //sets turn equal to player 1
        turn = 0;
        rightTeamSelected = false;

        //Start game
        currentState = STATES.START_GAME_STATE;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
    {
        //Display Map
        gameMap.draw(0,0);

        //Display Interface
        gameInterface = new Image("images/interface.png");
        gameInterface.draw(80, 666);

        //Display Buttons
        moveButton.draw(moveX, moveY);
        attackButton.draw(attackX, attackY);
        magicButton.draw(magicX, magicY);
        healButton.draw(healX, healY);

        //draw each unit and draw it's  HUD in the interface if it's selected
        for (int i = 0; i < (numUnits * 2); i++)
        {
            if (unit[i].isAlive())
                unit[i].draw();

            if ((unit[i].isSelected()) && unit[i].isAlive())
                unit[i].drawHUD(g);
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

        //render string for tile location
        g.drawString(tileLoc, 360, 645);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
    	//initialize variables
        Input input = gc.getInput();

        int mouseX = 0;
        int mouseY = 0;

        //function for when mouse is pressed
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
        {
        	//gets mouse location on screen
            mouseX = input.getMouseX();
            mouseY = input.getMouseY();

            // tile selected and no button pressed
            if(mouseX <= 800 && mouseY <= 640 && !movePressed && !attackPressed && !magicPressed && !healPressed)
            {
                selectedTile = grid.FindTile(mouseX,mouseY);
                xLoc = grid.GetXLoc(mouseX);
                yLoc = grid.GetYLoc(mouseY);

                // if selected tile is occupied
                if (selectedTile.GetOccupied())
                {
                    System.out.println("occupyBy: " + selectedTile.GetOccupiedBy());

                    // select that unit, unselect all other units
                    for (int i = 0; i < (numUnits * 2); i++)
                    {
                        if (selectedTile.GetOccupiedBy() == i)
                            unit[i].setSelectedTrue();
                        else
                            unit[i].setSelectedFalse();
                    }

                    //checks to see if the selected unit is on the player's team whose turn it is
                    if (((int)selectedTile.GetOccupiedBy()/numUnits) == turn)
                    {
                        rightTeamSelected = true;
                        System.out.println("Right team selected");
                    }
                    else
                    {
                        System.out.println("Wrong team selected");
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
                tileLoc = "Tile: (" + yLoc + "," + xLoc + ") Player " + (turn + 1) + " turn.";
            }
            // move button pressed
            else if((moveX < mouseX && mouseX < moveX + moveButton.getWidth()) && (moveY < mouseY && mouseY < moveY + moveButton.getHeight()) && (selectedTile.GetOccupied()) && (rightTeamSelected))
            {
                movePressed = true;
                attackPressed = false;
                magicPressed = false;
                healPressed = false;
                tileLoc = "Choose a tile to move to.";

            }
            // attack button pressed
            else if((attackX < mouseX && mouseX < attackX + attackButton.getWidth()) && (attackY < mouseY && mouseY < attackY + attackButton.getHeight()) && (selectedTile.GetOccupied()) && (rightTeamSelected))
            {
                attackPressed = true;
                movePressed = false;
                magicPressed = false;
                healPressed = false;
                tileLoc = "Choose a tile to physical attack.";
            }
            // magic button pressed
            else if((magicX < mouseX && mouseX < magicX + magicButton.getWidth()) && (magicY < mouseY && mouseY < magicY + magicButton.getHeight()) && (selectedTile.GetOccupied()) && (rightTeamSelected))
            {
                magicPressed = true;
                movePressed = false;
                attackPressed = false;
                healPressed = false;
                tileLoc = "Choose a tile to magic attack.";

            }
            // heal button pressed
            else if((healX < mouseX && mouseX < healX + healButton.getWidth()) && (healY < mouseY && mouseY < healY + healButton.getHeight()) && (selectedTile.GetOccupied()) && (rightTeamSelected))
            {
                healPressed = true;
                movePressed = false;
                attackPressed = false;
                magicPressed = false;
                tileLoc = "Choose a tile to heal.";

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
                tileLoc = "";
            }
        }
        
        //If all Player 1 units are killed declares Player 2 the victor and vice versa
        if(!unit[0].isAlive() && !unit[1].isAlive() && !unit[2].isAlive())
        {
            victor = 2;
            sb.enterState(OldMainWars.VictoryScreen);
        }        
        else if(!unit[3].isAlive() && !unit[4].isAlive() && !unit[5].isAlive())
        {
            victor = 1;
            sb.enterState(OldMainWars.VictoryScreen);
        }
        
    }

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
                pos2 = grid.FindTile(mouseX,mouseY);
                xLoc = grid.GetXLoc(mouseX);
                yLoc = grid.GetYLoc(mouseY);

                if (inRange(pos1, pos2, action)) //pos2 is in range of pos1
                {
                	//checks to see if move has been selected
                    if (action == MOVE)
                    {
                        if(!pos2.GetOccupied())
                        {
                            occupiedBy = pos1.GetOccupiedBy();
                            pos1.SetOccupiedFalse();
                            pos1.SetOccupiedBy(-1); //-1 = empty

                            pos2.SetOccupiedTrue();
                            pos2.SetOccupiedBy(occupiedBy);

                            //System.out.println("Occupied By " + occupiedBy);

                            unit[occupiedBy].setCoordinates(pos2.GetX() + X_OFFSET, pos2.GetY() + Y_OFFSET);
                            turn = handleTurn();
                            rightTeamSelected = false;
                        }
                        //turn = handleTurn();
                        //rightTeamSelected = false;
                    }
                    
                  //checks to see if attack has been selected
                    else if (action == ATTACK) // physical attack
                    {
                        if(pos2.GetOccupied() && (pos2.GetOccupiedBy() >= 0))
                        {
                            int damage = unit[pos1.GetOccupiedBy()].AttackDamage();
                            System.out.println(damage + " damage dealt");
                            unit[pos2.GetOccupiedBy()].Damage(damage);

                            // if attacked unit died, make that tile unoccupied
                            if (unit[pos2.GetOccupiedBy()].isAlive() == false)
                            {
                                pos2.SetOccupiedFalse();
                                pos2.SetOccupiedBy(-1);
                            }
                            turn = handleTurn();
                            rightTeamSelected = false;
                        }
                    }
                    
                  //checks to see if magic has been selected
                    else if (action == MAGIC) // magic attack
                    {
                        if(pos2.GetOccupied() && (pos2.GetOccupiedBy() >= 0))
                        {
                            int damage = unit[pos1.GetOccupiedBy()].CastSpell();
                            System.out.println(damage + " magic damage dealt");
                            unit[pos2.GetOccupiedBy()].Damage(damage);

                            // if attacked unit died, make that tile unoccupied
                            if (unit[pos2.GetOccupiedBy()].isAlive() == false)
                            {
                                pos2.SetOccupiedFalse();
                                pos2.SetOccupiedBy(-1);
                            }
                            turn = handleTurn();
                            rightTeamSelected = false;
                        }
                    }
                    
                  //checks to see if heal has been selected
                    else if (action == HEAL && (pos2.GetOccupiedBy() >= 0))
                    {
                        if(pos2.GetOccupied())
                        {
                            int heal = unit[pos1.GetOccupiedBy()].CastSpell();
                            System.out.println(heal + " damage healed");
                            unit[pos2.GetOccupiedBy()].Healed(heal);
                            turn = handleTurn();
                            rightTeamSelected = false;
                        }
                    }
                }
            }
        }

        //deselect all buttons
        movePressed = false;
        attackPressed = false;
        magicPressed = false;
        healPressed = false;

        //displays who turn it is
        tileLoc = "Player " + (turn + 1) + " turn.";
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
    public int handleTurn()
    {
        if (turn == 0)
            return 1;
        else
            return 0;
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
}