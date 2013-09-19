package callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryCallback {
	 public Object call(ResultSet rs) throws SQLException;
}
