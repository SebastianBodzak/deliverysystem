package com.testgroup.domain;

import javax.persistence.Embeddable;
import java.io.File;

/**
 * @author beata.ilowiecka@impaqgroup.com on 06.12.16.
 */
@Embeddable
public class Attachment {

    private File file;

    private Attachment(){}

    public Attachment(File file) {
        this.file = file;
    }
}
