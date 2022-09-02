package me.caiyudu.dedehack.mixin.client;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.blocks.EventSetOpaqueCube;
import me.caiyudu.dedehack.events.render.EventRenderEntityName;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VisGraph.class)
public class MixinVisGraph
{
    @Inject(method = "setOpaqueCube", at = @At("HEAD"), cancellable = true)
    public void setOpaqueCube(BlockPos pos, CallbackInfo info)
    {
        EventSetOpaqueCube l_Event = new EventSetOpaqueCube(); ///< pos is unused
        DeDeHackMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled())
            info.cancel();
    }
}
