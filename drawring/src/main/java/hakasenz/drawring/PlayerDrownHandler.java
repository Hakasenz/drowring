package hakasenz.drawring;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerDrownHandler implements Listener {
    private JavaPlugin plugin;

    public PlayerDrownHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
            Player player = (Player) event.getEntity();
            double maxHealth = player.getMaxHealth();

            // 取消伤害事件
            event.setCancelled(true);

            // 扣除生命值
            for (int i = 1; i <= 3; i++) {
                final int j = i;
                player.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
                {
                    public void run()
                    {
                        double currentHealth = player.getHealth();
                        double newHealth = currentHealth - maxHealth * 0.07 * j;
                        player.setHealth(Math.max(newHealth, 0));
                    }
                }, i);
            }
        }
    }
}
/*
扣除生命值的计算移到了Runnable内部，这样每次扣除的生命值都会根据i的值进行调整，从而实现了每次扣除生命值的数量逐渐增加的效果。
这样，我们就不需要在每次循环中都重新计算玩家的生命值，从而减少了主线程的负担
 */