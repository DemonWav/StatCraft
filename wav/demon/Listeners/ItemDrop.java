package wav.demon.Listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerPickupItemEvent;
import wav.demon.StatCraft;
import wav.demon.StatTypes;

public class ItemDrop extends StatListener implements CommandExecutor {

    public ItemDrop(StatCraft plugin) {
        super(plugin);
    }

    @SuppressWarnings({"unused", "deprecation"})
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onItemPickup(PlayerPickupItemEvent event) {
        final String message = event.getItem().getItemStack().getType().getId() + ":" +
                event.getItem().getItemStack().getData().getData();
        final String name = event.getPlayer().getName();
        final int x = event.getItem().getItemStack().getAmount();

        for (int y = 0; y < x; y++)
            incrementStat(StatTypes.ITEM_DROPS.id, name, message);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String[] names = getPlayers(sender, args);
        if (names == null)
            return false;

        for (String name : names) {
            int itemsPickedUp = getStat(name, StatTypes.ITEM_DROPS.id);

            sender.getServer().broadcastMessage(name + " - Items Dropped: " + itemsPickedUp);
        }
        return true;
    }
}