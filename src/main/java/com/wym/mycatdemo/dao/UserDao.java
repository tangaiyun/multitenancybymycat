package com.wym.mycatdemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wym.mycatdemo.model.User;

@Repository
public class UserDao {
	public static final String TENANT_SQL_TEMPLATE = "/*!mycat:schema= {0}*/{1}";
	@Autowired
	@Qualifier("businessJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public List<User> findAll(String tenantSchema) {
		return jdbcTemplate.query(MessageFormat.format(TENANT_SQL_TEMPLATE, tenantSchema, "select * from user"), new UserRowMapper());
	}

	@Transactional(readOnly = true)
	public User findUserById(String tenantSchema, int id) {
		return jdbcTemplate.queryForObject(MessageFormat.format(TENANT_SQL_TEMPLATE, tenantSchema, "select * from user where id=?"), new Object[] { id }, new UserRowMapper());
	}

	@Transactional
	public User create(String tenantSchema, final User user) {
		final String sql = MessageFormat.format(TENANT_SQL_TEMPLATE, tenantSchema, "insert into user(name,password) values(?,?)");

		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getName());
				ps.setString(2, user.getPassword());
				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		user.setId(newUserId);
		return user;
	}

	@Transactional
	public void delete(String tenantSchema, final Integer id) {
		final String sql = MessageFormat.format(TENANT_SQL_TEMPLATE, tenantSchema, "delete from user where id=?");
		jdbcTemplate.update(sql, new Object[] { id }, new int[] { java.sql.Types.INTEGER });
	}

	@Transactional
	public void update(String tenantSchema, final User user) {
		jdbcTemplate.update(MessageFormat.format(TENANT_SQL_TEMPLATE, tenantSchema, "update user set name=?,password=? where id=?"),
				new Object[] { user.getName(), user.getPassword(), user.getId() });
	}

	class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}

	}
}
