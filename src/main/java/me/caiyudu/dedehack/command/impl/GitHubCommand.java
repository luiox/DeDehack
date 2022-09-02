package me.caiyudu.dedehack.command.impl;

import me.caiyudu.dedehack.command.Command;

public class GitHubCommand extends Command {

    public GitHubCommand()
    {
        super("GitHub", "Sends GitHub link.");
    }

    @Override
    public void ProcessCommand(String p_Args) {
        String[] l_Split = p_Args.split(" ");

        if (l_Split == null || l_Split.length <= 1) {
            mc.player.sendChatMessage("https://github.com/Orb924/dedehack/releases");
            return;
        }
    }

    @Override
    public String GetHelp()
    {
        return "Sends the -DeDeHack GitHub link to chat.";
    }

}
