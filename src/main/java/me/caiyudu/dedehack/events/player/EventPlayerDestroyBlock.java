package me.caiyudu.dedehack.events.player;

import me.caiyudu.dedehack.events.MinecraftEvent;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EventPlayerDestroyBlock extends MinecraftEvent
{
    public BlockPos Location;

    public EventPlayerDestroyBlock(BlockPos loc)
    {
        Location = loc;
    }
}
