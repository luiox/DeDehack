package me.caiyudu.dedehack.module.ui;

import me.caiyudu.dedehack.gui.hud.GuiHudEditor;
import me.caiyudu.dedehack.module.Module;

public final class HudEditorModule extends Module
{
    private GuiHudEditor m_HudEditor;

    public HudEditorModule()
    {
        super("HudEditor", new String[]{"HudEditor"}, "Displays the HudEditor", "GRAVE", 0xDBC824, ModuleType.UI);
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
            if (m_HudEditor == null)
                m_HudEditor = new GuiHudEditor(this);
            
            mc.displayGuiScreen(m_HudEditor);
        }
    }
}
