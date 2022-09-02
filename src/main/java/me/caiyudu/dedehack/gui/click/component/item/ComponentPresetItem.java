package me.caiyudu.dedehack.gui.click.component.item;

import me.caiyudu.dedehack.gui.click.component.listeners.ComponentItemListener;
import me.caiyudu.dedehack.preset.Preset;

public class ComponentPresetItem extends ComponentItem
{
    private Preset _preset;

    public ComponentPresetItem(Preset preset, int flags, int state, ComponentItemListener listener, float width,
            float height)
    {
        super(preset.getName(), "", flags, state, listener, width, height);
        
        _preset = preset;
    }

    @Override
    public boolean HasState(int p_State)
    {
        if ((p_State & ComponentItem.Clicked) != 0)
            return _preset.isActive();
        
        return super.HasState(p_State);
    }

    public Preset getPreset()
    {
        return _preset;
    }
}
