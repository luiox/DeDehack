package me.caiyudu.dedehack.mixin.client;

import me.caiyudu.dedehack.events.entity.EventEntityAdded;
import me.caiyudu.dedehack.events.entity.EventEntityRemoved;
import me.caiyudu.dedehack.events.world.EventWorldSetBlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.render.EventRenderRainStrength;
import net.minecraft.world.World;

@Mixin(World.class)
public class MixinWorld
{
    @Inject(method = "getRainStrength", at = @At("HEAD"), cancellable = true)
    public void getRainStrength(float delta, CallbackInfoReturnable<Float> p_Callback)
    {
        EventRenderRainStrength l_Event = new EventRenderRainStrength();
        
        DeDeHackMod.EVENT_BUS.post(l_Event);
        
        if (l_Event.isCancelled())
        {
            p_Callback.cancel();
            p_Callback.setReturnValue(0.0f);
        }
    }

    @Inject(method = "setBlockState", at = @At("HEAD"), cancellable = true)
    public void setBlockState(BlockPos pos, IBlockState newState, int flags, CallbackInfoReturnable<Boolean> p_CallBack)
    {
        EventWorldSetBlockState l_Event = new EventWorldSetBlockState(pos, newState, flags);

        DeDeHackMod.EVENT_BUS.post(l_Event);

        if (l_Event.isCancelled())
        {
            p_CallBack.cancel();
            p_CallBack.setReturnValue(false);
        }
    }

    @Inject(method = "onEntityAdded", at = @At("HEAD"), cancellable = true)
    public void onEntityAdded(Entity p_Entity, CallbackInfo p_Info)
    {
        EventEntityAdded l_Event = new EventEntityAdded(p_Entity);

        DeDeHackMod.EVENT_BUS.post(l_Event);

        if (l_Event.isCancelled())
            p_Info.cancel();
    }

    @Inject(method = "onEntityRemoved", at = @At("HEAD"), cancellable = true)
    public void onEntityRemoved(Entity p_Entity, CallbackInfo p_Info)
    {
        EventEntityRemoved l_Event = new EventEntityRemoved(p_Entity);

        DeDeHackMod.EVENT_BUS.post(l_Event);

        if (l_Event.isCancelled())
            p_Info.cancel();
    }
}
