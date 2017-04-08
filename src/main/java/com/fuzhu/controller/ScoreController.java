package com.fuzhu.controller;

import com.alibaba.fastjson.JSON;
import com.fuzhu.dao.UserDao;
import com.fuzhu.entity.Score;
import com.fuzhu.entity.User;
import com.fuzhu.service.ScoreService;
import com.fuzhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/4/2.
 */
@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/updateScore", produces = "text/html;charset=UTF-8", method = {RequestMethod.GET, RequestMethod.GET})
    public String updateScore(Long id,Integer scoreCount) {
        User user = userService.queryById(id);
        scoreService.updateScore(user,scoreCount);
        return JSON.toJSONString(user);
    }
    @RequestMapping(value = "/queryLimit", produces = "text/html;charset=UTF-8", method = {RequestMethod.GET, RequestMethod.GET})
    public String queryLimit(int offset,  int limit) {
        List<Score>scoreList = scoreService.queryLimit(offset,limit);
        return JSON.toJSONString(scoreList);
    }
}
