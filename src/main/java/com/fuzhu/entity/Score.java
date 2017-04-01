package com.fuzhu.entity;

import java.util.Date;

/**
 * Created by ${符柱成} on 2017/3/31.
 */
public class Score {

    private Long id;
    //积分变化类型
    private String changeType;
    //创建时间
    private Date createTime;
    //积分变化数
    private Integer score;
    //爱豆变化者
    private User user;

    public Score() {
    }

    public Score(Long id, String changeType, Date createTime, Integer score, User user) {
        this.id = id;
        this.changeType = changeType;
        this.createTime = createTime;
        this.score = score;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
