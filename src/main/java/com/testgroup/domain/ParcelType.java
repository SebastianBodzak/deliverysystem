package com.testgroup.domain;

/**
 * @author beata.ilowiecka@impaqgroup.com on 06.12.16.
 */
public enum ParcelType {

    LETTER (0),
    PACK (1),
    INVOICE (2),
    EMAIL (3);

    private int number;

    ParcelType(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
