package me.caiyudu.dedehack.command;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.caiyudu.dedehack.main.DeDeHack;
import me.caiyudu.dedehack.main.Wrapper;
import net.minecraft.client.Minecraft;

public class Command
{
    private String Name;
    private String Description;
    
    protected final Minecraft mc = Wrapper.GetMC();
    protected final List<String> CommandChunks = new ArrayList<String>();
    
    public Command(String p_Name, String p_Description)
    {
        Name = p_Name;
        Description = p_Description;
    }
    
    public String GetName()
    {
        return Name;
    }
    
    public String GetDescription()
    {
        return Description;
    }
    
    public void ProcessCommand(String p_Args)
    {
    }


    protected void SendToChat(String p_Desc)
    {
        DeDeHack.SendMessage(String.format("%s[%s]: %s", ChatFormatting.LIGHT_PURPLE, GetName(), ChatFormatting.YELLOW + p_Desc));
    }

    public List<String> GetChunks()
    {
        return CommandChunks;
    }

    public String GetHelp()
    {
        return Description;
    }
}
