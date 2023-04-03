package com.example.tj_music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
        @Autowired
        JdbcTemplate jdbcTemplate;

        @RequestMapping(value = "/query", method = RequestMethod.GET)
        @ResponseBody
        public Map<String, Object> map() {
            String sql = "select * from user";
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
            return list.get(0);
        }
}
