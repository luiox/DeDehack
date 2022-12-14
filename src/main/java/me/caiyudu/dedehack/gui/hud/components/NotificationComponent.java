package me.caiyudu.dedehack.gui.hud.components;

import java.util.Iterator;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.gui.hud.GuiHudEditor;
import me.caiyudu.dedehack.gui.hud.HudComponentItem;
import me.caiyudu.dedehack.main.DeDeHack;
import me.caiyudu.dedehack.main.Wrapper;
import me.caiyudu.dedehack.managers.NotificationManager;
import me.caiyudu.dedehack.managers.NotificationManager.Notification;
import me.caiyudu.dedehack.module.Value;
import me.caiyudu.dedehack.util.render.RenderUtil;

public class NotificationComponent extends HudComponentItem
{
    public NotificationComponent()
    {
        super("Notifications", 500, 500);
        SetHidden(false);
    }

    @Override
    public void render(int p_MouseX, int p_MouseY, float p_PartialTicks)
    {
        super.render(p_MouseX, p_MouseY, p_PartialTicks);

        if (mc.currentScreen instanceof GuiHudEditor)
        {
            if (NotificationManager.Get().Notifications.isEmpty())
            {
                final String placeholder = "Notifications";
                SetWidth(RenderUtil.getStringWidth(placeholder));
                SetHeight(RenderUtil.getStringHeight(placeholder));
                RenderUtil.drawStringWithShadow(placeholder, GetX(), GetY(), 0xFFFFFF);
                return;
            }
        }
        
        Iterator<Notification> l_Itr = NotificationManager.Get().Notifications.iterator();
        
        float l_Y = GetY();
        float l_MaxWidth = 0f;
        
        while (l_Itr.hasNext())
        {
            Notification l_Notification = l_Itr.next();
            
            if (l_Notification.IsDecayed())
                NotificationManager.Get().Notifications.remove(l_Notification);
            
            //l_Notification.OnRender();
            
            float l_Width = RenderUtil.getStringWidth(l_Notification.GetDescription()) + 1.5f;
            
            RenderUtil.drawRect(GetX()-1.5f, l_Y, GetX()+l_Width, l_Y+13, 0x75101010);
            RenderUtil.drawStringWithShadow(l_Notification.GetDescription(), GetX(), l_Y+l_Notification.GetY(), 0xFFFFFF);
            
            if (l_Width >= l_MaxWidth)
                l_MaxWidth = l_Width;
            
            l_Y -= 13;
        }
        
        SetHeight(10f);
        SetWidth(l_MaxWidth);
    }
}
