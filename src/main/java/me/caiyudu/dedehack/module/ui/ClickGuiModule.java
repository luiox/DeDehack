package me.caiyudu.dedehack.module.ui;

import me.caiyudu.dedehack.gui.click.ClickGuiScreen;
import me.caiyudu.dedehack.managers.ModuleManager;
import me.caiyudu.dedehack.module.Module;
import me.caiyudu.dedehack.module.Value;

public final class ClickGuiModule extends Module
{
    public final Value<Boolean> AllowOverflow = new Value<Boolean>("AllowOverflow", new String[]
    { "AllowOverflow" }, "Allows the GUI to overflow", true);
    public final Value<Boolean> Watermark = new Value<Boolean>("Watermark", new String[]
    { "Watermark" }, "Displays the watermark on the GUI", true);
    public final Value<Boolean> HoverDescriptions = new Value<Boolean>("HoverDescriptions", new String[] {"HD"}, "Displays hover descriptions over values and modules", true);
    public final Value<Boolean> Snowing = new Value<Boolean>("Snowing", new String[] {"SN"}, "Play a snowing animation in ClickGUI", true);

    public ClickGuiScreen m_ClickGui;

    public ClickGuiModule()
    {
        super("ClickGui", new String[]
        { "ClickGui", "ClickGui" }, "Displays the click gui", "LEFT", 0xDB9324, ModuleType.UI);
    }
    
    @Override
    public void toggleNoSave()
    {
        
    }

    @Override
    public void onEnable()
    {
        super.onEnable();
        
        if (m_ClickGui == null)
            m_ClickGui = new ClickGuiScreen(this, (ColorsModule)ModuleManager.Get().GetMod(ColorsModule.class));

        if (mc.world != null)
        {
            mc.displayGuiScreen(m_ClickGui);
        }
    }
}
