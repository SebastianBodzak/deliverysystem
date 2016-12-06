package com.testgroup.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author beata.ilowiecka@impaqgroup.com on 06.12.16.
 */
@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long parcelId;

    @OneToOne
    private User miner;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    public Shipment() {
    }

    public Shipment(Long parcelId) {
        this.parcelId = parcelId;
        this.date = LocalDateTime.now();
        this.miner = null; //mock
    }

    public Long getId() {
        return id;
    }
}
