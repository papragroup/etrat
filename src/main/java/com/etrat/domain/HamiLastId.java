package com.etrat.domain;

import javax.persistence.*;

@Entity(name = "last_hami_id")
public class HamiLastId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long lastHamiId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLastHamiId() {
        return lastHamiId;
    }

    public void setLastHamiId(Long lastHamiId) {
        this.lastHamiId = lastHamiId;
    }
}
