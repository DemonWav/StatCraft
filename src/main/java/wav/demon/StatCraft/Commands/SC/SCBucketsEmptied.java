package wav.demon.StatCraft.Commands.SC;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import org.bukkit.command.CommandSender;
import wav.demon.StatCraft.Commands.ResponseBuilder;
import wav.demon.StatCraft.Magic.BucketCode;
import wav.demon.StatCraft.Querydsl.BucketEmpty;
import wav.demon.StatCraft.Querydsl.QBucketEmpty;
import wav.demon.StatCraft.Querydsl.QPlayers;
import wav.demon.StatCraft.StatCraft;

import java.util.List;

public class SCBucketsEmptied extends SCTemplate {

    public SCBucketsEmptied(StatCraft plugin) {
        super(plugin);
        this.plugin.getBaseCommand().registerCommand("bucketsemptied", this);
    }

    @Override
    public boolean hasPermission(CommandSender sender, String[] args) {
        return sender.hasPermission("statcraft.user.bucketsemptied");
    }

    @Override
    public String playerStatResponse(String name) {
        try {
            int id = plugin.getDatabaseManager().getPlayerId(name);
            if (id < 0)
                throw new Exception();

            SQLQuery query = plugin.getDatabaseManager().getNewQuery();
            QBucketEmpty e = QBucketEmpty.bucketEmpty;
            List<BucketEmpty> results = query.from(e).where(e.id.eq(id)).list(e);

            int total;
            int water = 0;
            int lava = 0;
            int milk = 0;

            for (BucketEmpty bucketEmpty : results) {
                BucketCode code = BucketCode.fromCode(bucketEmpty.getType());

                if (code == null)
                    continue;

                switch (code) {
                    case WATER:
                        water = bucketEmpty.getAmount();
                        break;
                    case LAVA:
                        lava = bucketEmpty.getAmount();
                        break;
                    case MILK:
                        milk = bucketEmpty.getAmount();
                        break;
                }
            }

            total = water + lava + milk;

            return new ResponseBuilder(plugin)
                    .setName(name)
                    .setStatName("Buckets Emptied")
                    .addStat("Total", df.format(total))
                    .addStat("Water", df.format(water))
                    .addStat("Lava", df.format(lava))
                    .addStat("Milk", df.format(milk))
                    .toString();
        } catch (Exception e) {
            return new ResponseBuilder(plugin)
                    .setName(name)
                    .setStatName("Buckets Emptied")
                    .addStat("Total", String.valueOf(0))
                    .addStat("Water", String.valueOf(0))
                    .addStat("Lava", String.valueOf(0))
                    .addStat("Milk", String.valueOf(0))
                    .toString();
        }
    }

    @Override
    public String serverStatListResponse(int num) {
        SQLQuery query = plugin.getDatabaseManager().getNewQuery();
        QBucketEmpty e = QBucketEmpty.bucketEmpty;
        QPlayers p = QPlayers.players;
        List<Tuple> result = query
                .from(e)
                .leftJoin(p)
                .on(e.id.eq(p.id))
                .groupBy(p.name)
                .orderBy(e.amount.sum().desc())
                .limit(num)
                .list(p.name, e.amount.sum());

        return topListResponse("Buckets Emptied", result);
    }
}
