package tw.Stopbukkit;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main
  extends JavaPlugin
  implements Listener
{
  public static String Format(String text)
  {
    return text.replaceAll("&", "§");
  }
  
  public void onEnable()
  {
    File file = new File(getDataFolder(), "config.yml");
    Bukkit.getServer().getLogger().info("[StopFix]開啟!!");
    getServer().getPluginManager().registerEvents(this, this);
    if (!getDataFolder().exists())
    {
      getDataFolder().mkdir();
      saveDefaultConfig();
    }
    else
    {
      if (!file.exists()) {
        saveDefaultConfig();
      }
      reloadConfig();
    }
  }
  
  public void onDisable()
  {
    Bukkit.getServer().getLogger().info("[StopFix]關閉!!");
  }
  
  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] commandString)
  {
    if (!(sender instanceof Player))
    {
      if (command.getName().toLowerCase().equalsIgnoreCase("stop")) {
    
        if (commandString.length == 0) {
          sender.sendMessage(ChatColor.RED + "正確用法為:/stop <原因>");
          return true;
        } else {
            for (Player player2 : Bukkit.getOnlinePlayers())
            {
            	 if(commandString.length > 0){
            		
            			 String msg = "";
            			 int count = commandString.length;
            			 for(int i = 0; i < count; i++){
            				  msg = msg + " " + commandString[i];
            			 }
            			 
                     player2.kickPlayer(Format(msg));
                     Bukkit.getServer().shutdown();
            		
            		 
            	 
            	 }
            }
          }
      }
      return true;
    }
    Player player = (Player)sender;
    if ((command.getName().toLowerCase().equalsIgnoreCase("stop")) && (player.hasPermission(getConfig().getString("Stop.CustomPermisson"))))
    {
    	
      if (commandString.length == 0) {
        player.sendMessage(ChatColor.RED + "正確用法為:/stop <原因>");
        return true;
      } else {
        for (Player player2 : Bukkit.getOnlinePlayers())
        {
        	 if(commandString.length > 0){
        		
        			 String msg = "";
        			 int count = commandString.length;
        			 for(int i = 0; i < count; i++){
        				  msg = msg + " " + commandString[i];
        			 }
        			 
                 player2.kickPlayer(Format(msg));
                 Bukkit.getServer().shutdown();
        		
        		 
        	 
        	 }
        }
      }
    }
    else
    {
      player.sendMessage(Format(getConfig().getString("Stop.NoPermission")));
      


      return true;
    }
    return false;
  }
}
