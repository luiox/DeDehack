package me.caiyudu.dedehack.managers;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.main.DeDeHack;
import me.caiyudu.dedehack.module.Module;
import me.caiyudu.dedehack.module.Module.ModuleType;
import me.caiyudu.dedehack.module.Value;
import me.caiyudu.dedehack.module.combat.*;
import me.caiyudu.dedehack.module.exploit.*;
import me.caiyudu.dedehack.module.misc.*;
import me.caiyudu.dedehack.module.movement.*;
import me.caiyudu.dedehack.module.render.*;
import me.caiyudu.dedehack.module.schematica.*;
import me.caiyudu.dedehack.module.chat.*;
import me.caiyudu.dedehack.module.ui.*;
import me.caiyudu.dedehack.module.world.*;
import me.caiyudu.dedehack.preset.Preset;
import me.caiyudu.dedehack.util.ReflectionUtil;
import me.caiyudu.dedehack.util.render.RenderUtil;
import net.minecraft.client.gui.GuiScreen;

public class ModuleManager
{
    public static ModuleManager Get()
    {
        return DeDeHack.GetModuleManager();
    }

    public ModuleManager()
    {
    }

    public ArrayList<Module> Mods = new ArrayList<Module>();
    private ArrayList<Module> ArrayListAnimations = new ArrayList<Module>();
    private KeybindsModule Keybinds = null;

    public void Init()
    {
        /// Combat
        Add(new AimbotModule());
        Add(new AntiBots());
        Add(new AntiCityBossModule());
        Add(new Auto32kModule());
        Add(new AutoArmorModule());
        Add(new AutoCityModule());
        Add(new AutoCrystalModule());
        Add(new AutoCrystalRewrite());
        Add(new AutoTotemModule());
        Add(new AutoTrap());
        Add(new AutoTrapFeet());
        Add(new BowSpamModule());
        Add(new CriticalsModule());
        Add(new HoleFillerModule());
        Add(new KillAuraModule());
        Add(new MiddleClickPearlModule());
        Add(new OldAutoCrystalRewrite());
        Add(new SelfTrapModule());
        Add(new SurroundModule());
        Add(new VelocityModule());

        /// Exploit
        Add(new CrashExploitModule());
        Add(new EntityDesyncModule());
        Add(new GhostModule());
        Add(new LiquidInteractModule());
        Add(new MountBypassModule());
        Add(new NoInteractModule());
        Add(new NoMiningTrace());
        Add(new NewChunksModule());
        Add(new PacketCancellerModule());
        Add(new PacketFlyModule());
        Add(new PortalGodModeModule());

        /// Misc
        Add(new AntiAFKModule());
        Add(new AutoDuperModule());
        Add(new AutoEatModule());
        Add(new AutoMendArmorModule());
        Add(new AutoMountModule());
        Add(new AutoReconnectModule());
        Add(new AutoRespawnModule());
        Add(new AutoSignModule());
        Add(new AutoTameModule());
        Add(new BuildHeightModule());
        Add(new ChatNotifierModule());
        Add(new ChestStealerModule());
        Add(new ChestSwapModule());
        Add(new DiscordRPCModule());
        Add(new FakePlayer());
        Add(new FriendsModule());
        Add(new GlobalLocationModule());
        Add(new HotbarCacheModule());
        Add(new ManualDupeModule());
        Add(new MiddleClickFriendsModule());
        Add(new StopWatchModule());
        Add(new TotemPopNotifierModule());
        Add(new VisualRangeModule());
        Add(new XCarryModule());

        /// Movement
        Add(new AntiLevitationModule());
        Add(new AutoWalkModule());
        Add(new BlinkModule());
        Add(new ElytraFlyModule());
        Add(new EntityControlModule());
        Add(new EntitySpeedModule());
        Add(new FlightModule());
        Add(new GlideModule());
        Add(new HorseJump());
        Add(new JesusModule());
        Add(new NoFallModule());
        Add(new NoRotateModule());
        Add(new NoSlowModule());
        Add(new ParkourJump());
        Add(new SafeWalkModule());
        Add(new SneakModule());
        Add(new SpeedModule());
        Add(new SprintModule());
        Add(new StepModule());
        Add(new YawModule());

        /// Render
        Add(new AntiFog());
        Add(new BlockHighlightModule());
        Add(new BreakHighlightModule());
        Add(new BrightnessModule());
        Add(new CityESPModule());
        Add(new EntityESPModule());
        Add(new FreecamModule());
        Add(new HoleESPModule());
        Add(new MobOwnerModule());
        Add(new NametagsModule());
        Add(new NoRenderModule());
        Add(new ShulkerPreviewModule());
        Add(new SkeletonModule());
        Add(new SmallShield());
        Add(new StorageESPModule());
        Add(new TracersModule());
        Add(new TrajectoriesModule());
        Add(new ViewClipModule());
        Add(new VoidESPModule());
        Add(new WaypointsModule());

        /// UI
        Add(new ChestModule());
        Add(new DupeModule());

        Add(new ColorsModule());
        Add(new ConsoleModule());
        Add(new ClickGuiModule());
        Add(new HudEditorModule());
        Add(new HudModule());
        Add(Keybinds = new KeybindsModule());
        Add(new ReliantChatModule());

        /// World
        Add(new AutoBuilderModule());
        Add(new AutoHighwayBuilder());
        Add(new AutoNameTagModule());
        Add(new AutoToolModule());
        Add(new AutoTunnelModule());
        Add(new AutoWitherModule());
        Add(new CoordsSpooferModule());
        Add(new EnderChestFarmer());
        Add(new FastPlaceModule());
        Add(new NoGlitchBlocksModule());
        Add(new NoWeatherModule());
        Add(new NukerModule());
        Add(new ScaffoldModule());
        Add(new SpeedyGonzales());
        Add(new StashFinderModule());
        Add(new StashLoggerModule());
        Add(new TimerModule());
        Add(new TorchAnnihilatorModule());

        /// Schematica
        Add(new PrinterModule());
        Add(new PrinterBypassModule());
        
        // Chat
        Add(new ChatModificationsModule());
        Add(new MessageModifierModule());
        Add(new PopBobSexDupeModule());

        LoadExternalModules();

        Mods.sort((p_Mod1, p_Mod2) -> p_Mod1.getDisplayName().compareTo(p_Mod2.getDisplayName()));

        final Preset preset = PresetsManager.Get().getActivePreset();

        Mods.forEach(mod ->
        {
            preset.initValuesForMod(mod);
        });

        Mods.forEach(mod ->
        {
            mod.init();
        });
    }

    public void Add(Module mod)
    {
        try
        {
            for (Field field : mod.getClass().getDeclaredFields())
            {
                if (Value.class.isAssignableFrom(field.getType()))
                {
                    if (!field.isAccessible())
                    {
                        field.setAccessible(true);
                    }
                    final Value val = (Value) field.get(mod);
                    val.InitalizeMod(mod);
                    mod.getValueList().add(val);
                }
            }
            Mods.add(mod);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public final List<Module> GetModuleList(ModuleType p_Type)
    {
        List<Module> list = new ArrayList<>();
        for (Module module : Mods)
        {
            if (module.getType().equals(p_Type))
            {
                list.add(module);
            }
        }
        // Organize alphabetically
        list.sort(Comparator.comparing(Module::getDisplayName));

        return list;
    }

    public final List<Module> GetModuleList()
    {
        return Mods;
    }

    public void OnKeyPress(String string)
    {
        if (string == null || string.isEmpty() || string.equalsIgnoreCase("NONE"))
            return;

        Mods.forEach(p_Mod ->
        {
            if (p_Mod.IsKeyPressed(string))
            {
                p_Mod.toggle();
            }
        });
    }

    public Module GetMod(Class p_Class)
    {
        /*Mods.forEach(p_Mod ->
        {
           if (p_Mod.getClass() == p_Class)
               return p_Mod;
        });*/

        for (Module l_Mod : Mods)
        {
            if (l_Mod.getClass() == p_Class)
                return l_Mod;
        }

        DeDeHackMod.log.error("Could not find the class " + p_Class.getName() + " in Mods list");
        return null;
    }

    public Module GetModLike(String p_String)
    {
        for (Module l_Mod : Mods)
        {
            if (l_Mod.GetArrayListDisplayName().toLowerCase().startsWith(p_String.toLowerCase()))
                return l_Mod;
        }

        return null;
    }

    public void OnModEnable(Module p_Mod)
    {
        ArrayListAnimations.remove(p_Mod);
        ArrayListAnimations.add(p_Mod);

        final Comparator<Module> comparator = (first, second) ->
        {
            final String firstName = first.GetFullArrayListDisplayName();
            final String secondName = second.GetFullArrayListDisplayName();
            final float dif = RenderUtil.getStringWidth(secondName) - RenderUtil.getStringWidth(firstName);
            return dif != 0 ? (int) dif : secondName.compareTo(firstName);
        };

        ArrayListAnimations = (ArrayList<Module>) ArrayListAnimations.stream()
        .sorted(comparator)
        .collect(Collectors.toList());
    }

    public void Update()
    {
        if (ArrayListAnimations.isEmpty())
            return;

        Module l_Mod = ArrayListAnimations.get(0);

        if ((l_Mod.RemainingXAnimation -= (RenderUtil.getStringWidth(l_Mod.GetFullArrayListDisplayName()) / 10)) <= 0)
        {
            ArrayListAnimations.remove(l_Mod);
            l_Mod.RemainingXAnimation = 0;
        }
    }

    public boolean IgnoreStrictKeybinds()
    {
        if (GuiScreen.isAltKeyDown() && !Keybinds.Alt.getValue())
            return true;
        if (GuiScreen.isCtrlKeyDown() && !Keybinds.Ctrl.getValue())
            return true;
        if (GuiScreen.isShiftKeyDown() && !Keybinds.Shift.getValue())
            return true;

        return false;
    }

    public void LoadExternalModules()
    {
        // from seppuku
        try
        {
            final File dir = new File("DeDeHack/CustomMods");

            for (Class newClass : ReflectionUtil.getClassesEx(dir.getPath()))
            {
                if (newClass == null)
                    continue;

                // if we have found a class and the class inherits "Module"
                if (Module.class.isAssignableFrom(newClass))
                {
                    //create a new instance of the class
                    final Module module = (Module) newClass.newInstance();

                    if (module != null)
                    {
                        // initialize the modules
                        Add(module);
                    }
                }

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
