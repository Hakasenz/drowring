package hakasenz.drawring;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DeathMessageHandler implements Listener {

    @EventHandler
    public void handleDeathMessage(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getLastDamageCause() instanceof EntityDamageEvent) {
            EntityDamageEvent lastDamage = (EntityDamageEvent) player.getLastDamageCause();
            if (lastDamage.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                // 修改死亡信息
                String deathMessage = player.getName() + " 溺水而亡，但是被神秘力量救赎";
                event.setDeathMessage(deathMessage);

                // 将死亡信息写入文件
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("death_messages.txt", true))) {
                    writer.write(deathMessage);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
