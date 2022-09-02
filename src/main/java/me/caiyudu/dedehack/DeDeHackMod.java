package me.caiyudu.dedehack;

import me.caiyudu.dedehack.main.ForgeEventProcessor;
import me.caiyudu.dedehack.main.DeDeHack;
import me.zero.alpine.fork.bus.EventBus;
import me.zero.alpine.fork.bus.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = "dedehack", name = " DeDeHack", version = DeDeHackMod.VERSION)
public final class DeDeHackMod
{
    public static final String NAME = " DeDeHack";
    public static final String VERSION = "2.8.1";

    public static final Logger log = LogManager.getLogger("dede");

    public static final EventBus EVENT_BUS = new EventManager();


    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        log.info("init  DeDehack v" + VERSION);
        Display.setTitle(NAME + " v" + VERSION);

        DeDeHack.Init();

        MinecraftForge.EVENT_BUS.register(new ForgeEventProcessor());


    }
}
