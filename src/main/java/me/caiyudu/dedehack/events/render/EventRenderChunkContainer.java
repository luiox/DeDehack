package me.caiyudu.dedehack.events.render;

import me.caiyudu.dedehack.events.MinecraftEvent;
import net.minecraft.client.renderer.chunk.RenderChunk;

public class EventRenderChunkContainer extends MinecraftEvent
{
    public RenderChunk RenderChunk;
    public EventRenderChunkContainer(RenderChunk renderChunk)
    {
        RenderChunk = renderChunk;
    }
}
