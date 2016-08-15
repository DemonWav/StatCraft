/*
 * StatCraft Bukkit Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql;

import com.mysema.query.sql.RelationalPath;

public interface QueryIdRunnerMap<T extends RelationalPath<?>, S, R> {
    void run(T t, S s, int id, int worldId, R r);
}
