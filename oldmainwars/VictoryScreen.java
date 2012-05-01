/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oldmainwars;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author ryan
 */
public class VictoryScreen extends BasicGameState
{
    //initialize variables
    public Image background = null;
    public Image player1 = null;
    public Image player2 = null;
    public Character char1 = null;
    public Character char2 = null;
    public Character char3 = null;


    float scaleStep = 0.0001f;
    float startGameScale = 1;
    //float optionsScale = 1;

    public int stateID = -1;

    //Sound fx = null;

    //set up state id
    public VictoryScreen(int stateID)
    {
        this.stateID = stateID;
    }

    //returns the stateID
    @Override
    public int getID()
    {
        return stateID;
    }

    //initializes variables with filenames
    @Override
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
    {
        //System.out.println("VictoryScreen init");
        
        player1 = new Image("images/player1victory.png");
        player2 = new Image("images/player2victory.png");

    }
    
    //Code to do when entering state
    @Override
    public void enter(GameContainer gc, StateBasedGame sb)throws SlickException
    {
        // get map selected in map selection
        if (MapSelection.getMap() == 1)
            background = new Image("images/map1_2.png");
        else if (MapSelection.getMap() == 2)
            background = new Image("images/map2_2.png");
        else //if (MapSelection.getMap() == 3)
            background = new Image("images/map3.png");
        
        //Gets who the victor is and fills character variables with the characters they used
        if(GamePlay.getVictor() == 1)
        {
            if (AvatarSelection.getAvatar() == 1) //dante selected
            {
                char2 = new Character(OldMainWars.Dante, 1);
                char1 = new Character(OldMainWars.Mage, 1);
                char3 = new Character(OldMainWars.Archer, 1);
            }
            else if (AvatarSelection.getAvatar() == 2) //mordecai selected
            {
                char2 = new Character(OldMainWars.Mordecai, 1);
                char1 = new Character(OldMainWars.Archer, 1);
                char3 = new Character(OldMainWars.Warrior, 1);
            }
            else //if (AvatarSelection.getAvatar() == 3) //morohtar selected
            {
                char2 = new Character(OldMainWars.Morohtar, 1);
                char1 = new Character(OldMainWars.Warrior, 1);
                char3 = new Character(OldMainWars.Mage, 1);
            }
        }
        else if(GamePlay.getVictor() == 2)
        {
            if (AvatarSelection2.getAvatar() == 1) //dante selected
            {
                char2 = new Character(OldMainWars.Dante, 2);
                char1 = new Character(OldMainWars.Mage, 2);
                char3 = new Character(OldMainWars.Archer, 2);
            }
            else if (AvatarSelection2.getAvatar() == 2) //mordecai selected
            {
                char2 = new Character(OldMainWars.Mordecai, 2);
                char1 = new Character(OldMainWars.Archer, 2);
                char3 = new Character(OldMainWars.Warrior, 2);
            }
            else //if (AvatarSelection.getAvatar() == 3) //morohtar selected
            {
                char2 = new Character(OldMainWars.Morohtar, 2);
                char1 = new Character(OldMainWars.Warrior, 2);
                char3 = new Character(OldMainWars.Mage, 2);
            }
        }
         
        //Sets the characters up to be ready to be drawn
        char1.setCoordinates(170, 160);
        char2.setCoordinates(380, 160);
        char3.setCoordinates(590, 160);
        
    }

    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
    {
        // render the background
        background.draw();
        
        //Displays the victory screen
        if(GamePlay.getVictor() == 1)
            player1.draw(150, 200);
        else
            player2.draw(150, 200);
        
        //Draws the characters used by the victors
        char1.draw();
        char2.draw();
        char3.draw();
    }

    //Nothing happens here yet
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
        	sb.enterState(OldMainWars.MainMenu);
        }
    }
}
