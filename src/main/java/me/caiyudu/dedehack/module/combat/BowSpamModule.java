package me.caiyudu.dedehack.module.combat;

import me.caiyudu.dedehack.events.client.EventClientTick;
import me.caiyudu.dedehack.module.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;

public class BowSpamModule extends Module
{
    /// (String displayName, String[] alias, String key, int color, ModuleType type)
    public BowSpamModule()
    {
        super("BowSpam", new String[]
        { "BS" }, "Releases the bow as fast as possible", "NONE", 0xDB2424, ModuleType.COMBAT);
    }

    @EventHandler
    private Listener<EventClientTick> OnTick = new Listener<>(p_Event ->
    {
        if (mc.player.getHeldItemMainhand().getItem() instanceof net.minecraft.item.ItemBow && mc.player.isHandActive() && mc.player.getItemInUseMaxCount() >= 3)
        {
            mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
            mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(mc.player.getActiveHand()));
            mc.player.stopActiveHand();
        }
    });
}
