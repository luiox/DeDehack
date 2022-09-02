package me.caiyudu.dedehack.module.misc;

import me.caiyudu.dedehack.module.Module;

public class FriendsModule extends Module
{
    public FriendsModule()
    {
        super("Friends", new String[] {"Homies"}, "Allows the friend system to function, disabling this ignores friend requirements, useful for dueling friends.", "NONE", -1, ModuleType.MISC);
        setEnabled(true);
    }
}
