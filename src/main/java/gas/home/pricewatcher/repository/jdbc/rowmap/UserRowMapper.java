package gas.home.pricewatcher.repository.jdbc.rowmap;

import gas.home.pricewatcher.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));
        return user;
    }
}
