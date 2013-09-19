package global;

import helper.ExecuteHelper;
import helper.QueryHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mapper.ObjectMapper;
import mapper.RowMapper;
import callback.ExecuteCallback;
import callback.QueryCallback;

public class DatabaseManager {
	public void executeQuery(final RowMapper rowMapper, String sql,
			Object[] parameters) {

		QueryHelper.executeQuery(sql, parameters, new QueryCallback() {

			
			public Object call(ResultSet rs) throws SQLException {

				while (rs.next()) {
					rowMapper.buildRow(rs);
				}
				return null;
			}

		});
	}
	
	public void executeUpdate(final ObjectMapper objMapper,String sql,
			Object[] parameters) {

		ExecuteHelper.executeUpdate(sql, parameters, new ExecuteCallback() {
			
			public Object execute(int update_num) {
				// TODO Auto-generated method stub
				try {
					objMapper.buildObject(update_num);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
