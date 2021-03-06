package me.kalpha.jdbctemplate.query;

import me.kalpha.jdbctemplate.query.dto.QueryDto;
import me.kalpha.jdbctemplate.query.dto.QueryResult;
import me.kalpha.jdbctemplate.query.dto.SamplesDto;
import me.kalpha.jdbctemplate.query.dto.TableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QueryService {
    public List<QueryResult> findSamples(SamplesDto samplesDto);

    public Page<QueryResult> findTable(Pageable pageable, TableDto tableDto);
    public long extractTable(TableDto tableDto);

    public Boolean validateSql(QueryDto queryDto);

    public Page<QueryResult> findByQuery(Pageable pageable, QueryDto queryDto);
    public long extractByQuery(QueryDto queryDto);
}
