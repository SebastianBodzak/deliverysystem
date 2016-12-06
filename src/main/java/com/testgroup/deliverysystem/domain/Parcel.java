package com.testgroup.deliverysystem.domain;

import javax.persistence.*;

/**
 * @author beata.ilowiecka@impaqgroup.com on 06.12.16.
 */
@Entity
public class Parcel {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User sender;
    @ManyToOne
    private User recipient;
    @Enumerated(EnumType.STRING)
    private ParcelType parcelType;
    @OneToOne
    private Attachment attachment;

    private String content;
    private String overwrittenRecipient;

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
}
