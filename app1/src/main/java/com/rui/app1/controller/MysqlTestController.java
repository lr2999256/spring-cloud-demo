package com.rui.app1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class MysqlTestController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/app1/testdb")
    public String testDb() throws JsonProcessingException {
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from sys_user");
        return new ObjectMapper().writeValueAsString(list);
    }
}
