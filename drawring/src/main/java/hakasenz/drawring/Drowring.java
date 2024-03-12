package hakasenz.drawring;

import org.bukkit.plugin.java.JavaPlugin;

public class Drowring extends JavaPlugin {

    @Override
    public void onEnable() {
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(new PlayerDrownHandler(this), this);
        getServer().getPluginManager().registerEvents(new DeathMessageHandler(), this);

        // 注册指令处理器
        getCommand("draw").setExecutor(new DrawCommand(this));
    }
}