package me.caiyudu.dedehack.managers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import me.caiyudu.dedehack.main.DeDeHack;

public class DirectoryManager
{
    public DirectoryManager()
    {
    }

    public void Init()
    {
        /// Create directories as needed
        try
        {
            CreateDirectory("DeDeHack");
            CreateDirectory("DeDeHack/Modules");
            CreateDirectory("DeDeHack/GUI");
            CreateDirectory("DeDeHack/HUD");
            CreateDirectory("DeDeHack/Locater");
            CreateDirectory("DeDeHack/StashFinder");
            CreateDirectory("DeDeHack/Config");
            CreateDirectory("DeDeHack/Capes");
            CreateDirectory("DeDeHack/Music");
            CreateDirectory("DeDeHack/CoordExploit");
            CreateDirectory("DeDeHack/LogoutSpots");
            CreateDirectory("DeDeHack/DeathSpots");
            CreateDirectory("DeDeHack/Waypoints");
            CreateDirectory("DeDeHack/Fonts");
            CreateDirectory("DeDeHack/CustomMods");
            CreateDirectory("DeDeHack/Presets");
            CreateDirectory("DeDeHack/Presets/Default");
            CreateDirectory("DeDeHack/Presets/Default/Modules");
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void CreateDirectory(String p_Path) throws IOException
    {
        new File(p_Path).mkdirs();
        
        //System.out.println("Created path at " + l_Path.get().toString());
    }
    
    public static DirectoryManager Get()
    {
        return DeDeHack.GetDirectoryManager();
    }

    public String GetCurrentDirectory() throws IOException
    {
        return new java.io.File(".").getCanonicalPath();
    }
}
