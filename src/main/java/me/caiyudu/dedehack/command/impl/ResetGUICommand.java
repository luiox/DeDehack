package me.caiyudu.dedehack.command.impl;

import me.caiyudu.dedehack.command.Command;
import me.caiyudu.dedehack.managers.ModuleManager;
import me.caiyudu.dedehack.module.ui.ClickGuiModule;

public class ResetGUICommand extends Command
{
    public ResetGUICommand()
    {
        super("ResetGUI", "Reset the ClickGUI positions to default");
    }
    
    @Override
    public void ProcessCommand(String p_Args)
    {
        ClickGuiModule mod = (ClickGuiModule)ModuleManager.Get().GetMod(ClickGuiModule.class);
        
        if (mod != null)
        {
            mod.m_ClickGui.ResetToDefaults();
        }
        
        SendToChat("Reset the ClickGUI");
    }
    
    @Override
    public String GetHelp()
    {
        return "Allows you teleport up x amount of blocks.";
    }
}
