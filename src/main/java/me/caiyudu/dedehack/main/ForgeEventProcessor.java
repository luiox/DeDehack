package me.caiyudu.dedehack.main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.client.EventClientTick;
import me.caiyudu.dedehack.events.render.EventRenderGetFOVModifier;
import me.caiyudu.dedehack.events.render.RenderEvent;
import me.caiyudu.dedehack.managers.ModuleManager;
import me.caiyudu.dedehack.util.entity.EntityUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ForgeEventProcessor
{
    /// @TODO: All of these should be removed and replaced by mixins.

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event)
    { 
        if (event.isCanceled())
            return;
        
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.disableDepth();

        GlStateManager.glLineWidth(1f);
        DeDeHackMod.EVENT_BUS.post(new RenderEvent(event.getPartialTicks()));
        GlStateManager.glLineWidth(1f);

        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
    }
    
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event)
    {
        if (Wrapper.GetMC().player == null)
            return;

        DeDeHackMod.EVENT_BUS.post(new EventClientTick());
    }

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event)
    {
        if (event.isCanceled())
            return;

        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (Keyboard.getEventKeyState())
            ModuleManager.Get().OnKeyPress(Keyboard.getKeyName(Keyboard.getEventKey()));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerDrawn(RenderPlayerEvent.Pre event)
    {
        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerDrawn(RenderPlayerEvent.Post event)
    {
        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onChunkLoaded(ChunkEvent.Load event)
    {
        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onEventMouse(InputEvent.MouseInputEvent event)
    {
        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onChunkUnLoaded(ChunkEvent.Unload event)
    {
        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onInputUpdate(InputUpdateEvent event)
    {
        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onLivingEntityUseItemEventTick(LivingEntityUseItemEvent.Start entityUseItemEvent)
    {
        DeDeHackMod.EVENT_BUS.post(entityUseItemEvent);
    }

    @SubscribeEvent
    public void onLivingDamageEvent(LivingDamageEvent event)
    {
        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent entityJoinWorldEvent)
    {
        DeDeHackMod.EVENT_BUS.post(entityJoinWorldEvent);
    }

    @SubscribeEvent
    public void onPlayerPush(PlayerSPPushOutOfBlocksEvent event)
    {
        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event)
    {
        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent entityEvent)
    {
        DeDeHackMod.EVENT_BUS.post(entityEvent);
    }

    @SubscribeEvent
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event)
    {
        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onClientChat(ClientChatReceivedEvent event)
    {
        DeDeHackMod.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void getFOVModifier(EntityViewRenderEvent.FOVModifier p_Event)
    {
        EventRenderGetFOVModifier l_Event = new EventRenderGetFOVModifier((float) p_Event.getRenderPartialTicks(), true);
        DeDeHackMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled())
        {
            p_Event.setFOV(l_Event.GetFOV());
        }
    }
    
    @SubscribeEvent
    public void OnWorldChange(WorldEvent p_Event)
    {
        DeDeHackMod.EVENT_BUS.post(p_Event);
    }
}
