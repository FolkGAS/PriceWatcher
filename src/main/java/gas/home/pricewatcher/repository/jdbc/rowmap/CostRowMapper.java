package gas.home.pricewatcher.repository.jdbc.rowmap;

import gas.home.pricewatcher.model.Cost;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CostRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cost cost = new Cost();
        cost.setId(rs.getInt("id"));
        cost.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
        cost.setCost(rs.getInt("cost"));
        return cost;
    }
}
