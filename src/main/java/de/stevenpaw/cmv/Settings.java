package de.stevenpaw.cmv;

public class Settings {

    public static Modes MODE;
    public static boolean NONAME;
    public static int itemsperpage = 9*4;

    /**
     * Initialize the CMV Settings
     */
    public static void Init()
    {
        MODE = Modes.INVMODE;
    }

    /**
     * Switches to the next Mode
     */
    public static void SwitchMode()
    {
        if(MODE == Modes.INVMODE){
            MODE = Modes.HANDMODE;
        } else if(MODE == Modes.HANDMODE){
            MODE = Modes.SECONDHANDMODE;
        } else if(MODE == Modes.SECONDHANDMODE){
            MODE = Modes.HATMODE;
        } else if(MODE == Modes.HATMODE){
            MODE = Modes.DROPMODE;
        } else if(MODE == Modes.DROPMODE){
            MODE = Modes.INVMODE;
        }
    }

    /**
     * Get the current Mode
     */
    public static String GetCurrentMode(){
        if(MODE == null){
            return Modes.INVMODE.toString();
        } else{
            return MODE.toString();
        }
    }

    public static void SwitchNoName(){
        NONAME = !NONAME;
    }
}
