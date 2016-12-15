package com.testgroup.domain;

import javax.persistence.*;

/**
 * @author beata.ilowiecka@impaqgroup.com on 06.12.16.
 */
@Entity
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User sender;
    @ManyToOne
    private User recipient;
    @Enumerated(EnumType.STRING)
    private ParcelType parcelType;
    @Embedded
    private Attachment attachment;

    private String content;
    private String overwrittenRecipient;

    private Parcel() {}

    public Parcel(Attachment attachment, String content, ParcelType parcelType, User recipient, User sender) {
        this.attachment = attachment;
        this.content = content;
        this.parcelType = parcelType;
        this.recipient = recipient;
        this.sender = sender;
    }

    public void overwriteUser(String user) {
        this.overwrittenRecipient = user;
    }

    public Long getId() {
        return id;
    }

    public ParcelType getParcelType() {
        return parcelType;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", parcelType=" + parcelType +
                ", attachment=" + attachment +
                ", content='" + content + '\'' +
                ", overwrittenRecipient='" + overwrittenRecipient + '\'' +
                '}';
    }

    public String getSender() {
        return sender.getName();
    }
    public String getRecipient() {
        return recipient.getName();
    }
}
