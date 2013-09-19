package mapper;

import java.sql.SQLException;

public interface ObjectMapper {
	void buildObject(Object obj)throws SQLException;
}
