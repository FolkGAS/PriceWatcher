package gas.home.pricewatcher.repository.jdbc;

import gas.home.pricewatcher.model.Goods;
import gas.home.pricewatcher.repository.GoodsRepository;
import gas.home.pricewatcher.repository.jdbc.rowmap.GoodsRowMapper;
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
public class JdbcGoodsRepositoryImpl implements GoodsRepository {

    private static final RowMapper<Goods> ROW_MAPPER = new GoodsRowMapper();

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertMeal;

    public JdbcGoodsRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("goods")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Goods save(Goods goods, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", goods.getId())
                .addValue("user_id", userId)
                .addValue("name", goods.getName())
                .addValue("description", goods.getDescription())
                .addValue("url", goods.getUrl())
                .addValue("in_url_name", goods.getInUrlName())
                .addValue("in_url_cost", goods.getInUrlCost())
                .addValue("date_time", goods.getDateTime())
                .addValue("route_indexes", goods.getRouteByIndexes())
                .addValue("route_tags", goods.getRouteByTags());

        if (goods.isNew()) {
            Number newKey = insertMeal.executeAndReturnKey(map);
            goods.setId(newKey.intValue());
        } else {
            int update = namedParameterJdbcTemplate.update(
                    "UPDATE goods " +
                            "SET id=:id, " +
                            "name=:name, " +
                            "description=:description, " +
                            "url=:url, " +
                            "in_url_name=:in_url_name, " +
                            "in_url_cost=:in_url_cost, " +
                            "route_indexes=:route_indexes, " +
                            "route_tags=:route_tags " +
                            "WHERE id=:id AND user_id=:user_id", map);
            if (update == 0) {
                return null;
            }
        }
        return goods;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM goods WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Goods get(int id, int userId) {
        List<Goods> goodsList = jdbcTemplate.query("SELECT * FROM goods WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return goodsList.size() == 0 ? null : DataAccessUtils.singleResult(goodsList);
    }

    @Override
    public List<Goods> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM goods WHERE user_id=? ORDER BY date_time DESC, id DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<Goods> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query("SELECT * FROM goods WHERE user_id=? AND date_time BETWEEN ? AND ? ORDER BY date_time DESC, ID DESC",
                ROW_MAPPER, userId, startDate, endDate);
    }
}
