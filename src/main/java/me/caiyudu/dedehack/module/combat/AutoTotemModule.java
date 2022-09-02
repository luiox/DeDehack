package me.caiyudu.dedehack.module.combat;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.caiyudu.dedehack.events.player.EventPlayerUpdate;
import me.caiyudu.dedehack.gui.SalGuiScreen;
import me.caiyudu.dedehack.main.DeDeHack;
import me.caiyudu.dedehack.managers.FriendManager;
import me.caiyudu.dedehack.module.Module;
import me.caiyudu.dedehack.module.Value;
import me.caiyudu.dedehack.util.entity.PlayerUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

/**
 * Author Seth 4/30/2019 @ 3:37 AM.
 * Updates by DeDeHack 2-1-20 (Ionar)
 */
public final class AutoTotemModule extends Module
{
    public final Value<Float> health = new Value<Float>("Health", new String[]
    { "Hp" }, "The amount of health needed to acquire a totem.", 16.0f, 0.0f, 20.0f, 0.5f);
    public final Value<AutoTotemMode> Mode = new Value<AutoTotemMode>("Mode", new String[]{"Mode"}, "If you are above the required health for a totem, x will be used in offhand instead.", AutoTotemMode.Totem);
    public final Value<AutoTotemMode> FallbackMode = new Value<AutoTotemMode>("Fallback", new String[]{"FallbackMode"}, "If you don't have the required item for mode, this will be the fallback.", AutoTotemMode.Crystal);
    public final Value<Float> FallDistance = new Value<Float>("FallDistance", new String[] {"Fall"}, "If your fall distance exceeds this value, use a totem", 15.0f, 0.0f, 100.0f, 10.0f);
    public final Value<Boolean> TotemOnElytra = new Value<Boolean>("TotemOnElytra", new String[] {"Elytra"}, "Will automatically switch to a totem if you're elytra flying", true);
    public final Value<Boolean> OffhandGapOnSword = new Value<Boolean>("SwordGap", new String[] {"SwordGap"}, "Will override all else, and try and use a gap in offhand when using a sword in main hand", false);
    public final Value<Boolean> OffhandStrNoStrSword = new Value<Boolean>("StrGap", new String[] {"Strength"}, "Will put a potion if offhand if you don't have strength and wearing a sword", false);
    public final Value<Boolean> HotbarFirst = new Value<Boolean>("HotbarFirst", new String[] {"Recursive"}, "Prioritizes your hotbar before inventory slots", false);
    
    
    public enum AutoTotemMode
    {
        Totem,
        Gap,
        Crystal,
        Pearl,
        Chorus,
        Strength,
        Shield,
    }
    
    public AutoTotemModule()
    {
        super("AutoTotem", new String[]
        { "Totem" }, "Automatically places a totem of undying in your offhand", "NONE", 0xDADB24, ModuleType.COMBAT);
    }

    @Override
    public String getMetaData()
    {
        return String.valueOf(Mode.getValue());
    }
    
    private void SwitchOffHandIfNeed(AutoTotemMode p_Val)
    {
        Item l_Item = GetItemFromModeVal(p_Val);
        
        if (mc.player.getHeldItemOffhand().getItem() != l_Item)
        {
            int l_Slot = HotbarFirst.getValue() ? PlayerUtil.GetRecursiveItemSlot(l_Item) : PlayerUtil.GetItemSlot(l_Item);
            
            Item l_Fallback = GetItemFromModeVal(FallbackMode.getValue());
            
            String l_Display = GetItemNameFromModeVal(p_Val);
            
            if (l_Slot == -1 && l_Item != l_Fallback && mc.player.getHeldItemOffhand().getItem() != l_Fallback)
            {
                l_Slot = PlayerUtil.GetRecursiveItemSlot(l_Fallback);
                l_Display = GetItemNameFromModeVal(FallbackMode.getValue());
                
                /// still -1...
                if (l_Slot == -1 && l_Fallback != Items.TOTEM_OF_UNDYING)
                {
                    l_Fallback = Items.TOTEM_OF_UNDYING;
                    
                    if (l_Item != l_Fallback && mc.player.getHeldItemOffhand().getItem() != l_Fallback)
                    {
                        l_Slot = PlayerUtil.GetRecursiveItemSlot(l_Fallback);
                        l_Display = "Emergency Totem";
                    }
                }
            }

            if (l_Slot != -1)
            {
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, l_Slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP,
                        mc.player);
                
                /// @todo: this might cause desyncs, we need a callback for windowclicks for transaction complete packet.
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, l_Slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.updateController();
                
                DeDeHack.SendMessage(ChatFormatting.YELLOW + "[AutoTotem] " + ChatFormatting.LIGHT_PURPLE + "Offhand now has a " + l_Display);
            }
        }
    }

    @EventHandler
    private Listener<EventPlayerUpdate> OnPlayerUpdate = new Listener<>(p_Event ->
    {
        if (mc.currentScreen != null && (!(mc.currentScreen instanceof GuiInventory) && !(mc.currentScreen instanceof SalGuiScreen)))
            return;

        if (!mc.player.getHeldItemMainhand().isEmpty())
        {
            if (health.getValue() <= PlayerUtil.GetHealthWithAbsorption() && mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && OffhandStrNoStrSword.getValue() && !mc.player.isPotionActive(MobEffects.STRENGTH))
            {
                SwitchOffHandIfNeed(AutoTotemMode.Strength);
                return;
            }
            
            /// Sword override
            if (health.getValue() <= PlayerUtil.GetHealthWithAbsorption() && mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && OffhandGapOnSword.getValue())
            {
                SwitchOffHandIfNeed(AutoTotemMode.Gap);
                return;
            }
        }
        
        /// First check health, most important as we don't want to die for no reason.
        if (health.getValue() > PlayerUtil.GetHealthWithAbsorption() || Mode.getValue() == AutoTotemMode.Totem || (TotemOnElytra.getValue() && mc.player.isElytraFlying()) || (mc.player.fallDistance >= FallDistance.getValue() && !mc.player.isElytraFlying()) || noNearbyPlayers())
        {
            SwitchOffHandIfNeed(AutoTotemMode.Totem);
            return;
        }
        
        /// If we meet the required health

        SwitchOffHandIfNeed(Mode.getValue());

    });
    
    @Override
    public void onEnable()
    {
        super.onEnable();

    }
    
    public Item GetItemFromModeVal(AutoTotemMode p_Val)
    {
        switch (p_Val)
        {
            case Crystal:
                return Items.END_CRYSTAL;
            case Gap:
                return Items.GOLDEN_APPLE;
            case Pearl:
                return Items.ENDER_PEARL;
            case Chorus:
                return Items.CHORUS_FRUIT;
            case Strength:
                return Items.POTIONITEM;
            case Shield:
                return Items.SHIELD;
            default:
                break;
        }
        
        return Items.TOTEM_OF_UNDYING;
    }

    private String GetItemNameFromModeVal(AutoTotemMode p_Val)
    {
        switch (p_Val)
        {
            case Crystal:
                return "End Crystal";
            case Gap:
                return "Gap";
            case Pearl:
                return "Pearl";
            case Chorus:
                return "Chorus";
            case Strength:
                return "Strength";
            case Shield:
                return "Shield";
            default:
                break;
        }
        
        return "Totem";
    }

    private boolean noNearbyPlayers() {
        if (AutoTotemMode.Crystal == Mode.getValue() && mc.world.playerEntities.stream().noneMatch(e -> e != mc.player && isValidTarget(e))) {
            return true;
        }
        return false;
    }

    private boolean isValidTarget(Entity p_Entity)
    {
        if (FriendManager.Get().IsFriend(p_Entity)) {
            return false;
        }

        if (p_Entity == mc.player) {
            return false;
        }
        if (mc.player.getDistance(p_Entity) > 15) {
            return false;
        }
        return true;
    }
}
