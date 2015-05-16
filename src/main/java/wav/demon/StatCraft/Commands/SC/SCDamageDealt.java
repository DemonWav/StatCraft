package wav.demon.StatCraft.Commands.SC;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import org.bukkit.command.CommandSender;
import wav.demon.StatCraft.Commands.ResponseBuilder;
import wav.demon.StatCraft.Querydsl.QDamageDealt;
import wav.demon.StatCraft.Querydsl.QPlayers;
import wav.demon.StatCraft.StatCraft;

import java.util.List;

public class SCDamageDealt extends SCTemplate {

    public SCDamageDealt(StatCraft plugin) {
        super(plugin);
        this.plugin.getBaseCommand().registerCommand("damagedealt", this);
    }

    @Override
    public boolean hasPermission(CommandSender sender, String[] args) {
        return sender.hasPermission("statcraft.user.damagedealt");
    }

    @Override
    public String playerStatResponse(String name) {
        try {
            int id = plugin.getDatabaseManager().getPlayerId(name);
            if (id < 0)
                throw new Exception();

            SQLQuery query = plugin.getDatabaseManager().getNewQuery();
            QDamageDealt d = QDamageDealt.damageDealt;
            Integer total = query.from(d).where(d.id.eq(id)).uniqueResult(d.amount.sum());

            return new ResponseBuilder(plugin)
                    .setName(name)
                    .setStatName("Damage Dealt")
                    .addStat("Total", df.format(total))
                    .toString();
        } catch (Exception e) {
            return new ResponseBuilder(plugin)
                    .setName(name)
                    .setStatName("Damage Dealt")
                    .addStat("Total", String.valueOf(0))
                    .toString();
        }
    }

    @Override
    public String serverStatListResponse(int num) {
        SQLQuery query = plugin.getDatabaseManager().getNewQuery();
        QDamageDealt d = QDamageDealt.damageDealt;
        QPlayers p = QPlayers.players;
        List<Tuple> list = query
                .from(d)
                .leftJoin(p)
                .on(d.id.eq(p.id))
                .groupBy(p.name)
                .orderBy(d.amount.sum().desc())
                .limit(num)
                .list(p.name, d.amount.sum());

        return topListResponse("Damage Dealt", list);
    }
}
