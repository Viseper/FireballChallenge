package me.viseper.fireballchallenge;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public final class FireballChallenge extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent blockBreakEvent) {
        Player player = blockBreakEvent.getPlayer();
        if(player != null) {
            blockBreakEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent playerInteractEvent) {
        Player player = playerInteractEvent.getPlayer();
        World world = player.getWorld();
        if (playerInteractEvent.getAction().isLeftClick()) {
            int explosivePower = (int)Math.floor(Math.random() * 5) + 1;
            if(Math.floor(Math.random() * 10) == 2.0 && Math.floor(Math.random() * 10) == 2.0) {
                explosivePower = 127;
            }
            Fireball fireball = (Fireball) world.spawnEntity(new Location(world, player.getLocation().getX(),player.getLocation().getY() + 1, player.getLocation().getZ()), EntityType.FIREBALL);
            fireball.setYield(explosivePower);
            fireball.setVelocity(player.getLocation().getDirection());
            fireball.setDirection(player.getLocation().getDirection());
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent damage) {
        Fireball fireball = (Fireball) damage.getDamager();
        Player player = (Player) damage.getEntity();
        if (player != null && fireball != null) {
            damage.setDamage(0);
        }
    }

    }
