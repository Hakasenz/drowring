package hakasenz.drawring;// DrawCommand.java
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DrawCommand implements CommandExecutor {
    private JavaPlugin plugin;

    public DrawCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("draw")) {
            if (args.length == 1) {
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target != null) {
                    int air = target.getRemainingAir();
                    target.setRemainingAir(Math.max(air - 20, 0));  // 每个氧气单位等于1秒，所以20单位等于1点氧气值
                    sender.sendMessage("成功减少玩家 " + target.getName() + " 的氧气值");
                    return true;
                } else {
                    sender.sendMessage("找不到玩家 " + args[0]);
                }
            } else {
                sender.sendMessage("请指定一个玩家名字");
            }
        }
        return false;
    }
}
