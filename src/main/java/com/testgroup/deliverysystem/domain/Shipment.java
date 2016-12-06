package com.testgroup.deliverysystem.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

/**
 * @author beata.ilowiecka@impaqgroup.com on 06.12.16.
 */
@Entity
public class Shipment {

    @OneToOne
    private Parcel parcel;

    @OneToOne
    private User miner;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    public Shipment() {
    }

    public Shipment(Parcel parcel, LocalDateTime date, User miner) {
        this.parcel = parcel;
        this.date = date;
        this.miner = miner;
    }
}
