package me.caiyudu.dedehack.events.schematica;

import me.caiyudu.dedehack.events.MinecraftEvent;
import net.minecraft.util.math.BlockPos;

public class EventSchematicaPlaceBlock extends MinecraftEvent
{
    public BlockPos Pos;
    
    public EventSchematicaPlaceBlock(BlockPos p_Pos)
    {
        Pos = p_Pos;
    }
}
