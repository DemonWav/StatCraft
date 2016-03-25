/*
 * StatCraft Bukkit Plugin
 *
 * Copyright (c) 2015 Kyle Wood (DemonWav)
 * http://demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql;

import com.demonwav.statcraft.StatCraft;
import com.demonwav.statcraft.Util;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

import java.sql.Connection;
import java.util.UUID;
import java.util.function.Consumer;

public class QueryRunnable<T extends RelationalPath<?>> implements Consumer<Connection> {
    private final Class<T> clazz;
    private final UUID uuid;
    private final QueryIdRunner<T, SQLInsertClause> insertClause;
    private final QueryIdRunner<T, SQLUpdateClause> updateClause;
    private final StatCraft plugin;

    public QueryRunnable(final Class<T> clazz,
                         final UUID uuid,
                         final QueryIdRunner<T, SQLInsertClause> insertClause,
                         final QueryIdRunner<T, SQLUpdateClause> updateClause,
                         final StatCraft plugin) {
        this.clazz = clazz;
        this.uuid = uuid;
        this.insertClause = insertClause;
        this.updateClause = updateClause;
        this.plugin = plugin;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    @Override
    public void accept(final Connection connection) {
        Util.runQuery(clazz, uuid, insertClause, updateClause, connection, plugin);
    }
}
