package me.caiyudu.dedehack.events.player;

import me.caiyudu.dedehack.events.MinecraftEvent;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EventPlayerClickBlock extends MinecraftEvent
{
    public BlockPos Location;
    public EnumFacing Facing;

    public EventPlayerClickBlock(BlockPos loc, EnumFacing face)
    {
        Location = loc;
        Facing = face;
    }
}
