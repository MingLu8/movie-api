package movie.demo.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class Database( @Autowired private val _jdbcTemplate: JdbcTemplate) {
    fun execute(sql:String) : Unit = _jdbcTemplate.execute(sql)
    fun <T> query(sql: String, rowMapper: RowMapper<T>) : List<T> = _jdbcTemplate.query<T>(sql, rowMapper)
    fun <T> query(sql: String, parameters: Array<Any>, rowMapper: RowMapper<T>) : List<T> = _jdbcTemplate.query<T>(sql, parameters, rowMapper)
}