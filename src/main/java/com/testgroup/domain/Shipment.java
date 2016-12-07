package com.testgroup.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author beata.ilowiecka@impaqgroup.com on 06.12.16.
 */
@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long parcelId;

    private Long senderId;

    private Long recipientId;

    private ParcelType parcelType;

    @OneToOne
    private User miner;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Shipment() {}

    public Shipment(Long parcelId, Long senderId, Long recipientId, ParcelType parcelType) {
        this.parcelId = parcelId;
        this.date = new Date();
        this.miner = null; //mock
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.parcelType = parcelType;
    }

    public Long getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", parcelId=" + parcelId +
                ", senderId=" + senderId +
                ", recipientId=" + recipientId +
                ", parcelType=" + parcelType +
                ", miner=" + miner +
                ", date=" + date +
                '}';
    }
}
