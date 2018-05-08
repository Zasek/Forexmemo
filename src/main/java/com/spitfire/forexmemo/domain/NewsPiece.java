/*
 * 实体类，定义新闻类
 * @author: Jiazhen Zhang
 * @version:0.0.1, 2018/05/04
 * */
package com.spitfire.forexmemo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

public class NewsPiece {
    @Id
    private String id;

    private String content;
    private String source;
    private String labels;
    private String originLang;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postTime;

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getOriginLang() {
        return originLang;
    }

    public void setOriginLang(String originLang) {
        this.originLang = originLang;
    }

    public Date getActualTime() {
        return actualTime;
    }

    public void setActualTime(Date actualTime) {
        this.actualTime = actualTime;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsPiece newsPiece = (NewsPiece) o;
        return Objects.equals(id, newsPiece.id) &&
                Objects.equals(content, newsPiece.content) &&
                Objects.equals(source, newsPiece.source) &&
                Objects.equals(labels, newsPiece.labels) &&
                Objects.equals(originLang, newsPiece.originLang) &&
                Objects.equals(actualTime, newsPiece.actualTime) &&
                Objects.equals(postTime, newsPiece.postTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, source, labels, originLang, actualTime, postTime);
    }

    @Override
    public String toString() {
        return "NewsPiece{" +
                "Id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", labels='" + labels + '\'' +
                ", originLang='" + originLang + '\'' +
                ", actualTime=" + actualTime +
                ", postTime=" + postTime +
                '}';
    }
}
