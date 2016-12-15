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

    public static String toParcelType(int parcelTypeNr) {
        ParcelType type ;
        switch (parcelTypeNr){
            case 0 : type = ParcelType.LETTER;
                break;
            case 1 : type = ParcelType.PACK;
                break;
            case 2 : type = ParcelType.INVOICE;
                break;
            case 3 : type = ParcelType.EMAIL;
                break;
            default : throw new IllegalArgumentException("undefined parcel type");
        }
        return type.toString();
    }
}
