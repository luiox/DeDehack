package me.caiyudu.dedehack.events.render;

import com.google.common.base.Predicate;

import me.caiyudu.dedehack.events.MinecraftEvent;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;

public class EventRenderGetEntitiesINAABBexcluding extends MinecraftEvent
{

    public EventRenderGetEntitiesINAABBexcluding(WorldClient worldClient, Entity entityIn, AxisAlignedBB boundingBox, Predicate predicate)
    {
        // TODO Auto-generated constructor stub
    }

}
