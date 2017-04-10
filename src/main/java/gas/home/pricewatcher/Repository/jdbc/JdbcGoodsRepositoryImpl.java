package gas.home.pricewatcher.Repository.jdbc;

import gas.home.pricewatcher.Repository.GoodsRepository;
import gas.home.pricewatcher.model.Goods;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcGoodsRepositoryImpl implements GoodsRepository {

    private static final BeanPropertyRowMapper<Goods> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Goods.class);

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
                .addValue("userid", userId)
                .addValue("name", goods.getName())
                .addValue("description", goods.getDescription())
                .addValue("url", goods.getUrl())
                .addValue("itemnamefromsite", goods.getItemNameFromSite())
                .addValue("cost", goods.getCost())
                .addValue("registered", goods.getRegistered())
                .addValue("costelementindexes", goods.getCostElementIndexes())
                .addValue("costelementtagsandclasses", goods.getCostElementTagsAndClasses());

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
                            "itemnamefromsite=:itemnamefromsite, " +
                            "cost=:cost, " +
                            "registered=:registered, " +
                            "costelementindexes=:costelementindexes, " +
                            "costelementtagsandclasses=:costelementtagsandclasses " +
                            "WHERE id=:id AND userid=:userid", map);
            if (update == 0) {
                return null;
            }
        }
        return goods;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM goods WHERE id=? AND userid=?", id, userId) != 0;
    }

    @Override
    public Goods get(int id, int userId) {
        List<Goods> goodsList = jdbcTemplate.query("SELECT * FROM goods WHERE id=? AND userid=?", ROW_MAPPER, id, userId);
        return goodsList.size() == 0 ? null : DataAccessUtils.singleResult(goodsList);
    }

    @Override
    public List<Goods> getAll(int userId) {
        List<Goods> query = jdbcTemplate.query("SELECT * FROM goods WHERE userid=? ORDER BY registered DESC, id DESC", ROW_MAPPER, userId);
        return query;
    }

    @Override
    public List<Goods> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query("SELECT * FROM goods WHERE userid=? AND registered BETWEEN ? AND ? ORDER BY registered DESC, ID DESC",
                ROW_MAPPER, userId, startDate, endDate);
    }
}
