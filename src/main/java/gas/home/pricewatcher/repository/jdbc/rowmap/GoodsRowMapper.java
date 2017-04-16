package gas.home.pricewatcher.repository.jdbc.rowmap;

import gas.home.pricewatcher.model.Goods;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Goods goods = new Goods();
        goods.setId(rs.getInt("id"));
        goods.setName(rs.getString("name"));
        goods.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
        goods.setDescription(rs.getString("description"));
        goods.setUrl(rs.getString("url"));
        goods.setInUrlName(rs.getString("in_url_name"));
        goods.setInUrlCost(rs.getString("in_url_cost"));
        goods.setRouteByIndexes(rs.getString("route_indexes"));
        goods.setRouteByTags(rs.getString("route_tags"));
        return goods;
    }
}
