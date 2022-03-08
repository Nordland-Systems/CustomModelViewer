package de.stevenpaw.cmv;

import org.bukkit.Bukkit;

public class Settings {

    public static Modes mode;

    /**
     * Initialize the CMV Settings
     */
    public static void Init()
    {
        mode = Modes.INVMODE;
    }

    /**
     * Switches to the next Mode
     */
    public static void SwitchMode()
    {
        if(mode == Modes.INVMODE){
            mode = Modes.HANDMODE;
        } else if(mode == Modes.HANDMODE){
            mode = Modes.SECONDHANDMODE;
        } else if(mode == Modes.SECONDHANDMODE){
            mode = Modes.HATMODE;
        } else if(mode == Modes.HATMODE){
            mode = Modes.DROPMODE;
        } else if(mode == Modes.DROPMODE){
            mode = Modes.INVMODE;
        }
    }

    /**
     * Switches to the given Mode
     * @param modeIn (Modes) = The mode to switch to
     */
    public static void SwitchMode(Modes modeIn)
    {
        switch(modeIn){
            default:
                mode = Modes.INVMODE;
                break;
            case HANDMODE:
                mode = Modes.HANDMODE;
                break;
            case SECONDHANDMODE:
                mode = Modes.SECONDHANDMODE;
                break;
            case HATMODE:
                mode = Modes.HATMODE;
                break;
            case DROPMODE:
                mode = Modes.DROPMODE;
                break;
        }
    }

    /**
     * Get the current Mode
     */
    public static String GetCurrentMode(){
        if(mode == null){
            return Modes.INVMODE.toString();
        } else{
            return mode.toString();
        }
    }
}
