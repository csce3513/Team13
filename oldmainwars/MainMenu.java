package oldmainwars;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState
{
	//initialize Variables for images
    public Image background = null;
    public Image title = null;
    public Image startGameOption = null;
    public Image InstructionOption = null;
    public Image ExitOption = null;

    //initialize Starting Position Variables
    public static int startX = 100;
    public static int startY = 595;
    
    public static int InstructionX = 310;
    public static int InstructionY = 595;
    
    public static int ExitX = 520;
    public static int ExitY = 595;
    
    //initialize scaling Variables
    float scaleStep = 0.0001f;
    float startGameScale = 1;
    float InstructionScale = 1;
    float ExitScale = 1;

    public int stateID = -1;
    
    private STATES currentState = null;
    
    private enum STATES
    {
        START_GAME_STATE
    }

    //constructor
    public MainMenu(int stateID)
    {
        this.stateID = stateID;
    }

    //get state id
    @Override
    public int getID()
    {
        return stateID;
    }

    //function to populate variables
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
    {
        //Load background
        background = new Image("images/old_main.jpg");

        //load title
        title = new Image("images/gamename.png");
        
        //button options
        startGameOption = new Image("images/start.png");
        InstructionOption = new Image("images/instructions.png");
        ExitOption = new Image("images/exit.png");
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sb) throws SlickException
    {
    	//enters this game state
        super.enter(gc, sb);

        //Start game
        currentState = STATES.START_GAME_STATE;
    }

    //function to draw objects on screen
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
    {
        // render the background
        background.draw((OldMainWars.ScreenWidth - background.getWidth()) / 2, 150);

        //render title
        title.draw((OldMainWars.ScreenWidth - title.getWidth()) / 2, 50);
        
        //render button options
        startGameOption.draw(startX, startY, startGameScale);
        InstructionOption.draw(InstructionX, InstructionY, InstructionScale);
        ExitOption.draw(ExitX, ExitY, ExitScale);
    }

    //function for using mouse click
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {

        Input input = gc.getInput();

        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        //sound
        OldMainWars.handleMusic(gc);

        //initialize variables
        boolean insideStartGame = false;
        boolean insideInstructionMenu = false;
        boolean insideExitGame = false;

        //determines which button has been selected
        if( ( mouseX >= startX && mouseX <= startX + startGameOption.getWidth()) &&
            ( mouseY >= startY && mouseY <= startY + startGameOption.getHeight()) )
        {
            insideStartGame = true;
        }
        
        else if(( mouseX >= InstructionX && mouseX <= InstructionX + InstructionOption.getWidth()) &&
                ( mouseY >= InstructionY && mouseY <= InstructionY + InstructionOption.getHeight()))
        {
        	insideInstructionMenu = true;
        }
        
        else if(( mouseX >= ExitX && mouseX <= ExitX + ExitOption.getWidth()) &&
                ( mouseY >= ExitY && mouseY <= ExitY + ExitOption.getHeight()))
        {
        	insideExitGame = true;
        }

        //performs correct action based on button selected
        //Start game selected, then load avatar selection screen
        if(insideStartGame)
        {
            if(startGameScale < 1.05f)
                startGameScale += scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
            {
                sb.enterState(OldMainWars.AvatarSelection);
            }
        }
        else
        {
            if(startGameScale > 1.0f)
                startGameScale -= scaleStep * delta;
        }
        
        //if instruction select, then load instruction menu
        if(insideInstructionMenu)
        {
            if(InstructionScale < 1.05f)
                InstructionScale += scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
            {
            	sb.enterState(OldMainWars.InstructionScreen);
            }
        }
        
        else
        {
            if(InstructionScale > 1.0f)
                InstructionScale -= scaleStep * delta;
        }
        
        //if exit selected, then quit game
        if(insideExitGame)
        {
            if(ExitScale < 1.05f)
                ExitScale += scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
            {
            	gc.exit();
            }
        }
        
        else
        {
            if(ExitScale > 1.0f)
                ExitScale -= scaleStep * delta;
        }
    }
}