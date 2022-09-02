package me.caiyudu.dedehack.mixin.client;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.render.EventRenderLayers;
import me.caiyudu.dedehack.gui.chat.SalGuiChat;
import me.caiyudu.dedehack.main.Wrapper;

@Mixin(RenderLivingBase.class)
public class MixinRenderLivingBase
{
}
