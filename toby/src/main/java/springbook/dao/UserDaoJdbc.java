package springbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springbook.domain.Level;
import springbook.domain.User;
import springbook.service.sql.SqlService;

public class UserDaoJdbc implements UserDao{
	
	private SqlService sqlService;
	
	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}

	//private Map<String, String> sqlMap;
	//public void setSqlMap(Map<String, String> sqlMap) {
	//	this.sqlMap = sqlMap;
	//}
	
	//private String sqlAdd;
	//public void setSqlAdd(String sqlAdd) {
	//	this.sqlAdd = sqlAdd;
	//}

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<User> userMapper = new RowMapper<User>(){
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("user_id"));
			user.setName(rs.getString("user_name"));
			user.setPassword(rs.getString("user_password"));
			user.setEmail(rs.getString("user_email"));
			user.setLevel(Level.valueOf(rs.getInt("user_level")));
			user.setLogin(rs.getInt("login"));
			user.setRecommend(rs.getInt("recommend"));
			return user;
		}
	};

	public void deleteAll(){
		this.jdbcTemplate.update(
				//"delete from users"
				//sqlMap.get("delete")
				this.sqlService.getSql("userDeleteAll")
				);
	}

	public void add(final User user){
		this.jdbcTemplate.update(
				//sqlMap.get("add")
				//"insert into users(user_id, user_name, user_password, user_email, user_level, login, recommend) values(?,?,?,?,?,?,?)"
				//this.sqlAdd
				this.sqlService.getSql("userAdd")
				, user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getLogin(), user.getRecommend());
	}

	public User get(String id){
		return this.jdbcTemplate.queryForObject(
				//"select * from users where user_id = ?"
				//sqlMap.get("get")
				this.sqlService.getSql("userGet")
				,new Object[]{id}, this.userMapper);
		
	}

	public int getCount(){
		return this.jdbcTemplate.queryForInt(
				//"select count(*) from users"
				//sqlMap.get("getCount")
				this.sqlService.getSql("usetGetCount")
				);
	}
	
	public List<User> getAll(){
		return this.jdbcTemplate.query(
				//"select * from users order by user_id"
				//sqlMap.get("getAll")
				this.sqlService.getSql("userGetAll")
				,this.userMapper);
	}

	public void update(User user) {
		this.jdbcTemplate.update(
				//"update users set user_name = ?, user_password = ?, user_email = ?, user_level = ?, login = ?, recommend = ? where user_id = ?"
				//sqlMap.get("update")
				this.sqlService.getSql("userUpdate")
				, user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId()
				);
	}

	
	
}
