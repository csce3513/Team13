package oldmainwars;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class InstructionScreen extends BasicGameState
{
	//initialize Image Variables to null
    public Image LetterA = null;
    public Image LetterG = null;
    public Image LetterH = null;
    public Image LetterM = null;
    public Image LetterP = null;
    public Image back = null;
	
    //initialize starting position for back button
    public static int backX = 300;
    public static int backY = 620;
    
    //initialize scaling affect for back button
    float scaleStep = 0.0001f;
    float backScale = 1;
    
    //initialize state id
    public int stateID = -1;
	
    Font font = null;
    TrueTypeFont trueTypeFont = null;

    //Constructor
    public InstructionScreen(int stateID)
    {
        this.stateID = stateID;
    }

    //returns id
    @Override
    public int getID()
    {
        return stateID;
    }
    
    //function to initialize images
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
    {
    	LetterA = new Image("images/letter_a.png");
    	LetterG = new Image("images/letter_g.png");
    	LetterH = new Image("images/letter_h.png");
    	LetterM = new Image("images/letter_m.png");
    	LetterP = new Image("images/letter_p.png");
    	back = new Image("images/back.png"); 
    	
    	font = new Font("Verdana", Font.BOLD, 30);
    	trueTypeFont = new TrueTypeFont(font, true);
    }
    
    //function to draw strings and images on screen
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
    {
    	trueTypeFont.drawString(225, 35, "Instruction Screen");
    	
    	arg2.drawImage(LetterM, 315, 80);
    	arg2.drawString("- Move", 375, 95);
    	
    	arg2.drawImage(LetterA, 314, 150);
    	arg2.drawString("- Attack", 375, 170);
    	
    	arg2.drawImage(LetterG, 314, 220);
    	arg2.drawString("- Magic", 375, 235);
    	
    	arg2.drawImage(LetterH, 314, 285);
    	arg2.drawString("- Heal", 375, 300);
    	
    	arg2.drawImage(LetterP, 314, 350);
    	arg2.drawString("- Pass", 375, 365);
    	
    	trueTypeFont.drawString(200, 425, "Game Play Instructions");
    	
    	arg2.drawString("Select your avatar with the mouse, afterwards you can either", 100, 460);
    	arg2.drawString("use the mouse click or use the hotkeys above to select an action.", 100, 475);
    	arg2.drawString("Finally use the mouse to move on the board and to attack an enemy,", 100, 490);
    	arg2.drawString("or heal an ally. Can make one move and one attack per turn.", 100, 505);
    	
    	trueTypeFont.drawString(300, 530, "Warning");
    	arg2.drawString("If you click attack and then click one of your units", 100, 565);
    	arg2.drawString("then damage will occur to your unit.", 100, 580);
    	
    	//render back and next buttons
        back.draw(backX, backY, backScale);
    }
    
    //function to allow player to return to main menu screen
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
    	Input input = gc.getInput();

        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        // handle background music
        OldMainWars.handleMusic(gc);
        
        boolean instruction = false;

        //handles return to main menu
        if( ( mouseX >= backX && mouseX <= backX + back.getWidth()) &&
            ( mouseY >= backY && mouseY <= backY + back.getHeight()) )
        {
            instruction = true;
        }

        if(instruction)
        {
            if(backScale < 1.05f)
                backScale += scaleStep * delta;

            if ( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
            {
            	sb.enterState(OldMainWars.MainMenu);
            }
        }
        
        else
        {
            if(backScale > 1.0f)
                backScale -= scaleStep * delta;
        }
    }
}
