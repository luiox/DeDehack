package me.caiyudu.dedehack.module.ui;

import me.caiyudu.dedehack.gui.chat.SalGuiConsole;
import me.caiyudu.dedehack.module.Module;

public final class ConsoleModule extends Module
{
    private SalGuiConsole m_Console;

    public ConsoleModule()
    {
        super("Console", new String[]{"Console"}, "Displays the click gui", "UP", 0xDBB024, ModuleType.UI);
    }
    
    @Override
    public void toggleNoSave()
    {
        
    }

    @Override
    public void onToggle()
    {
        super.onToggle();
        
        if (mc.world != null)
        {
            if (m_Console == null)
                m_Console = new SalGuiConsole(this);
            
            mc.displayGuiScreen(m_Console);
        }
    }
}
