package me.caiyudu.dedehack.command.impl;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.caiyudu.dedehack.command.Command;

public class SoundReloadCommand extends Command
{
    public SoundReloadCommand()
    {
        super("SoundReload", "Reloads the sound system");
    }
    
    @Override
    public void ProcessCommand(String p_Args)
    {
        mc.getSoundHandler().sndManager.reloadSoundSystem();
        SendToChat(ChatFormatting.GREEN + "Reloaded the SoundSystem!");
    }
    
    @Override
    public String GetHelp()
    {
        return "Reloads the sound manager sound system";
    }
}
