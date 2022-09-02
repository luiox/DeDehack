package me.caiyudu.dedehack.gui.click.component.menus.mods;

import me.caiyudu.dedehack.gui.click.component.MenuComponent;
import me.caiyudu.dedehack.gui.click.component.item.ComponentItem;
import me.caiyudu.dedehack.gui.click.component.item.ComponentItemHUD;
import me.caiyudu.dedehack.gui.click.component.item.ComponentItemHiddenMod;
import me.caiyudu.dedehack.gui.click.component.item.ComponentItemKeybind;
import me.caiyudu.dedehack.gui.click.component.item.ComponentItemMod;
import me.caiyudu.dedehack.gui.click.component.item.ComponentItemValue;
import me.caiyudu.dedehack.gui.click.component.item.ComponentPresetItem;
import me.caiyudu.dedehack.gui.click.component.listeners.ComponentItemListener;
import me.caiyudu.dedehack.gui.hud.HudComponentItem;
import me.caiyudu.dedehack.main.DeDeHack;
import me.caiyudu.dedehack.managers.HudManager;
import me.caiyudu.dedehack.managers.ModuleManager;
import me.caiyudu.dedehack.managers.PresetsManager;
import me.caiyudu.dedehack.module.Module;
import me.caiyudu.dedehack.module.Module.ModuleType;
import me.caiyudu.dedehack.module.Value;
import me.caiyudu.dedehack.module.ui.ClickGuiModule;
import me.caiyudu.dedehack.module.ui.ColorsModule;
import me.caiyudu.dedehack.preset.Preset;

public class MenuComponentPresetsList extends MenuComponent
{
    private final float Width = 105f;
    private final float Height = 11f;

    public MenuComponentPresetsList(String p_DisplayName, ModuleType p_Type, float p_X, float p_Y, String p_Image, ColorsModule p_Colors, ClickGuiModule p_Click)
    {
        super(p_DisplayName, p_X, p_Y, 100f, 105f, p_Image, p_Colors, p_Click);

        PresetsManager.Get().GetItems().forEach(preset ->
        {
            AddPreset(preset);
        });
    }

    public void AddPreset(Preset preset)
    {
        ComponentItemListener listener = new ComponentItemListener()
        {
            @Override
            public void OnEnabled()
            {
            }
            
            @Override
            public void OnToggled()
            {
                PresetsManager.Get().SetPresetActive(preset);
            }

            @Override
            public void OnDisabled()
            {
            }

            @Override
            public void OnHover()
            {

            }

            @Override
            public void OnMouseEnter()
            {

            }

            @Override
            public void OnMouseLeave()
            {

            }
        };
        
        int flags = ComponentItem.Clickable | ComponentItem.Hoverable | ComponentItem.Tooltip;
        
        int state = 0;
        
        if (preset.isActive())
            state |= ComponentItem.Clicked;
        
        ComponentItem item = new ComponentPresetItem(preset, flags, state, listener, Width, Height);
        
        // todo: add values for deleting, renaming, and copying
        
        AddItem(item);
    }

    public void RemovePreset(Preset toRemove)
    {
        ComponentItem removeItem = null;
        
        for (ComponentItem item : this.Items)
        {
            if (item instanceof ComponentPresetItem)
            {
                ComponentPresetItem comp = (ComponentPresetItem) item;
                
                if (comp.getPreset() == toRemove)
                {
                    removeItem = comp;
                    break;
                }
            }
        }
        
        if (removeItem != null)
            this.Items.remove(removeItem);
    }
}
