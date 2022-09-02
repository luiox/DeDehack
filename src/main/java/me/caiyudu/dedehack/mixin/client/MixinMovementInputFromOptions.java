package me.caiyudu.dedehack.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.player.EventPlayerUpdateMoveState;
import me.caiyudu.dedehack.main.Wrapper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;

@Mixin(value = MovementInputFromOptions.class, priority = 10000) ///< wwe has 9999, we should be atleast 1 above
public abstract class MixinMovementInputFromOptions extends MovementInput
{
    @Inject(method = "updatePlayerMoveState", at = @At("RETURN"))
    public void updatePlayerMoveStateReturn(CallbackInfo callback)
    {
        DeDeHackMod.EVENT_BUS.post(new EventPlayerUpdateMoveState());
    }
}
