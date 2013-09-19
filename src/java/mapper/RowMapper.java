package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
	void buildRow(ResultSet rs)throws SQLException;
}
