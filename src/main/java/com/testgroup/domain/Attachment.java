package com.testgroup.domain;

import javax.annotation.Generated;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;

/**
 * @author beata.ilowiecka@impaqgroup.com on 06.12.16.
 */
@Embeddable
public class Attachment {

    private File file;

    public Attachment(File file) {
        this.file = file;
    }
}
