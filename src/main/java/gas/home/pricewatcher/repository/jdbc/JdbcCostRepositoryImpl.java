package gas.home.pricewatcher.repository.jdbc;

import gas.home.pricewatcher.model.Cost;
import gas.home.pricewatcher.repository.CostRepository;
import gas.home.pricewatcher.repository.jdbc.rowmap.CostRowMapper;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcCostRepositoryImpl implements CostRepository {

    private static final RowMapper<Cost> ROW_MAPPER = new CostRowMapper();

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertCost;

    public JdbcCostRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertCost = new SimpleJdbcInsert(dataSource)
                .withTableName("costs")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Cost save(Cost cost, int goodsId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", cost.getId())
                .addValue("goods_id", goodsId)
                .addValue("cost", cost.getCost())
                .addValue("date_time", cost.getDateTime());

        if (cost.isNew()) {
            Number newKey = insertCost.executeAndReturnKey(map);
            cost.setId(newKey.intValue());
        } else {
            int update = namedParameterJdbcTemplate.update(
                    "UPDATE costs " +
                            "SET id=:id, " +
                            "goods_id=:goods_id, " +
                            "cost=:cost " +
                            "WHERE id=:id AND goods_id=:goods_id", map);
            if (update == 0) {
                return null;
            }
        }
        return cost;
    }

    @Override
    public boolean delete(int id, int goodsId) {
        return jdbcTemplate.update("DELETE FROM costs WHERE id=? AND goods_id=?", id, goodsId) != 0;
    }

    @Override
    public Cost get(int id, int goodsId) {
        List<Cost> costs = jdbcTemplate.query("SELECT * FROM costs WHERE id=? AND goods_id=?", ROW_MAPPER, id, goodsId);
        return costs.size() == 0 ? null : DataAccessUtils.singleResult(costs);
    }

    @Override
    public List<Cost> getAll(int goodsId) {
        return jdbcTemplate.query("SELECT * FROM costs WHERE goods_id=? ORDER BY date_time DESC, id DESC", ROW_MAPPER, goodsId);
    }

    @Override
    public List<Cost> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int goodsId) {
        return jdbcTemplate.query("SELECT * FROM costs WHERE goods_id=? AND date_time BETWEEN ? AND ? ORDER BY date_time DESC, id DESC",
                ROW_MAPPER, goodsId, startDateTime, endDateTime);
    }
}
