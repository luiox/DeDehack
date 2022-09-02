package me.caiyudu.dedehack.managers;

import me.caiyudu.dedehack.main.DeDeHack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UpdateManager {

    public UpdateManager() {
        Load();
    }

    private String version = "";

    public void Load() {

        try {
            URL l_URL;
            URLConnection l_Connection;
            BufferedReader l_Reader;

            System.out.println("Getting version");
            l_URL = new URL("https://raw.githubusercontent.com/Orb924/dedehack-assets/master/assets/version.txt");
            l_Connection = l_URL.openConnection();
            l_Connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");

            l_Reader = new BufferedReader(new InputStreamReader(l_Connection.getInputStream()));

            version = l_Reader.readLine();

            l_Reader.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }

    public String getVersion() {
        return this.version;
    }

    public static UpdateManager Get() {
        return DeDeHack.GetUpdateManager();
    }

}
