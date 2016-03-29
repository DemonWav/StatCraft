/*
 * StatCraft Bukkit Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * http://demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.listeners;

import com.demonwav.statcraft.StatCraft;
import com.demonwav.statcraft.magic.ProjectilesCode;
import com.demonwav.statcraft.querydsl.QProjectiles;
import com.mysema.query.types.expr.CaseBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.UUID;

public class EnderPearlListener implements Listener {

    private final StatCraft plugin;

    public EnderPearlListener(final StatCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEnderPearl(final PlayerTeleportEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            final Player player = event.getPlayer();
            final Location toLocation = event.getTo();
            final Location fromLocation = event.getFrom();

            final double distance = fromLocation.distance(toLocation);
            final int finalDistance = (int) Math.round(distance * 100.0);

            final UUID uuid = player.getUniqueId();
            final String worldName = player.getWorld().getName();

            plugin.getThreadManager().schedule(
                QProjectiles.class, uuid, worldName,
                (p, clause, id, worldId) ->
                    clause.columns(p.id, p.worldId, p.type, p.amount, p.totalDistance, p.maxThrow)
                        .values(id, worldId, ProjectilesCode.ENDER_PEARL.getCode(), 1, finalDistance, finalDistance).execute(),
                (p, clause, id, worldId) ->
                    clause.where(p.id.eq(id), p.worldId.eq(worldId), p.type.eq(ProjectilesCode.ENDER_PEARL.getCode()))
                        .set(p.amount, p.amount.add(1))
                        .set(p.totalDistance, p.totalDistance.add(finalDistance))
                        .set(p.maxThrow,
                            new CaseBuilder()
                                .when(p.maxThrow.lt(finalDistance)).then(finalDistance)
                                .otherwise(p.maxThrow))
                        .execute()
            );
        }
    }
}
