package com.codigo.aplios.toolbox.xbase.core.version;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * *
 * 0x63 dBASE IV SQL system files,no memo 0x83 FoxBASE+/dBASE III PLUS,with memo 0x8B dBASE IV with memo 0xCB dBASE
 * IV*SQL table files,with memo 0xF5 FoxPro 2. x(or earlier)with memo 0xFB FoxBASE
 */
public enum XbFileVersion {

    /**
     * 0x02 FoxBASE
     */
    XBASE2(0x02),
    /**
     * 0x03 FoxBASE+/Dbase III plus, no memo FoxBASE+/dBASE III PLUS, with memo
     */
    XBASE3(0x03, 0x83),
    /**
     * 0x30 Visual FoxPro 0x31 Visual FoxPro, autoincrement enabled 0x32 Visual FoxPro, Varchar, Varbinary, or
     * Blob-enabled
     * 0xF5 - FoxPro 2.x (or earlier) with memo
     */
    FOXPRO(0x30, 0x31, 0x32, 0xF5),
    /**
     * 0x43 dBASE IV SQL table files, no memo 0x63 dBASE IV SQL system files, no memo 0x8B dBASE IV with memo 0xCB dBASE
     * IV
     * SQL table files, with memo
     */
    XBASE4(0x43, 0x63, 0x8B, 0xCB),
    HIPERSIX(0xE5),
    FOXBASE(0xFB);

    // DBASE_5,
    // CLIPPER_5,
    /**
     * Atrybut wskazuje wersje formatu XBase dla różnych wydań. Jedna wersja może miec kilka wydań różniących isę
     * obsługa
     * formatu danych.
     */
    private final int[] versionCodes;

    /**
     * Podstawowy konstruktor obiektu klasy.
     *
     * @param versionCodes Numery kodowe wersji zbioru XBase
     */
    XbFileVersion(final int... versionCodes) {

        this.versionCodes = versionCodes;
    }

    /**
     * Metoda
     *
     * @param versionCode
     * @return
     */
    public static XbFileVersion ofVersionCode(final int versionCode) {
        //kodowanie ze
        return Stream.of(XbFileVersion.values())
                .filter(item -> Arrays.stream(item.versionCodes)
                        .filter(i -> Integer.valueOf(i).equals(versionCode))
                        .count() == 1)
                .findAny()
                .get();
    }

}
