package com.wym.mycatdemo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wym.mycatdemo.model.OrgMapping;

@Repository
public class OrgMappingDao {
	public static final String GLOBAL_SCHEMA = "GLOBALDB";
	@Autowired
	@Qualifier("businessJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	public OrgMapping findOrgMappingByCode(String code) {
		return jdbcTemplate.queryForObject("/*!mycat:schema=" + GLOBAL_SCHEMA + " */"+"select * from orgmapping where code=?", new Object[] { code }, new OrgMappingRowMapper());
	}
	
	class OrgMappingRowMapper implements RowMapper<OrgMapping> {

		@Override
		public OrgMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrgMapping orgMapping = new OrgMapping();
			orgMapping.setId(rs.getInt("id"));
			orgMapping.setCode(rs.getString("code"));
			orgMapping.setSchema(rs.getString("schema"));
			return orgMapping;
		}
	}
}
