package me.caiyudu.dedehack.events.render;

import me.caiyudu.dedehack.events.MinecraftEvent;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.math.BlockPos;

public class EventRenderChunk extends MinecraftEvent
{
    public BlockPos BlockPos;
    public RenderChunk RenderChunk;
    public EventRenderChunk(RenderChunk renderChunk, BlockPos blockPos)
    {
        BlockPos = blockPos;
        RenderChunk = renderChunk;
    }
}
