package ru.dilarionov.springtest.history.domain;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;

@Entity
@Table(name="history")
@Qualifier("history")
public class History {

    public History() {}

    public History(String action, String time) {
        this.action = action;
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String action;
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
