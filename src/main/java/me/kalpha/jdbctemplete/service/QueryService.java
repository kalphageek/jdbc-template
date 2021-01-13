package me.kalpha.jdbctemplete.service;

import me.kalpha.jdbctemplete.domain.QueryDto;
import me.kalpha.jdbctemplete.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {
    private final QueryRepository queryRepository;

    @Autowired
    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    //추가
    public List query(QueryDto queryDto) {
        return  queryRepository.queryByParams(queryDto.getQuery(), queryDto.getParams());
    }

    public Boolean validate(QueryDto queryDto) {
        return  queryRepository.validateQueryByParams(queryDto.getQuery(), queryDto.getParams());
    }

    public List queryRecently(String tableNm, Integer limits) {
        return queryRepository.queryRecently(tableNm, limits);
    }
    public List queryRecently(String tableNm) {
        return queryRepository.queryRecently(tableNm);
    }
}
