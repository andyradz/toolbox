package com.codigo.aplios.toolbox.xbase.core.header;

import java.io.DataInput;
import java.io.IOException;

/// <summary>
/// This class represents a DBF IV file header.
/// </summary>
///
/// <remarks>
/// DBF files are really wasteful on space but this legacy format lives on because it's really really simple.
/// It lacks much in features though.
///
///
/// Thanks to Erik Bachmann for providing the DBF file structure information!!
/// http://www.clicketyclick.dk/databases/xbase/format/dbf.html
///
///           _______________________  _______
/// 00h /   0| Version number      *1|  ^
///          |-----------------------|  |
/// 01h /   1| Date of last update   |  |
/// 02h /   2|      YYMMDD        *21|  |
/// 03h /   3|                    *14|  |
///          |-----------------------|  |
/// 04h /   4| Number of records     | Record
/// 05h /   5| in data file          | header
/// 06h /   6| ( 32 bits )        *14|  |
/// 07h /   7|                       |  |
///          |-----------------------|  |
/// 08h /   8| Length of header   *14|  |
/// 09h /   9| structure ( 16 bits ) |  |
///          |-----------------------|  |
/// 0Ah /  10| Length of each record |  |
/// 0Bh /  11| ( 16 bits )     *2 *14|  |
///          |-----------------------|  |
/// 0Ch /  12| ( Reserved )        *3|  |
/// 0Dh /  13|                       |  |
///          |-----------------------|  |
/// 0Eh /  14| Incomplete transac.*12|  |
///          |-----------------------|  |
/// 0Fh /  15| Encryption flag    *13|  |
///          |-----------------------|  |
/// 10h /  16| Free record thread    |  |
/// 11h /  17| (reserved for LAN     |  |
/// 12h /  18|  only )               |  |
/// 13h /  19|                       |  |
///          |-----------------------|  |
/// 14h /  20| ( Reserved for        |  |            _        |=======================| ______
///          |   multi-user dBASE )  |  |           / 00h /  0| Field name in ASCII   |  ^
///          : ( dBASE III+ - )      :  |          /          : (terminated by 00h)   :  |
///          :                       :  |         |           |                       |  |
/// 1Bh /  27|                       |  |         |   0Ah / 10|                       |  |
///          |-----------------------|  |         |           |-----------------------| For
/// 1Ch /  28| MDX flag (dBASE IV)*14|  |         |   0Bh / 11| Field type (ASCII) *20| each
///          |-----------------------|  |         |           |-----------------------| field
/// 1Dh /  29| Language driver     *5|  |        /    0Ch / 12| Field data address    |  |
///          |-----------------------|  |       /             |                     *6|  |
/// 1Eh /  30| ( Reserved )          |  |      /              | (in memory !!!)       |  |
/// 1Fh /  31|                     *3|  |     /       0Fh / 15| (dBASE III+)          |  |
///          |=======================|__|____/                |-----------------------|  |  -
/// 20h /  32|                       |  |  ^          10h / 16| Field length       *22|  |   |
///          |- - - - - - - - - - - -|  |  |                  |-----------------------|  |   | *7
///          |                    *19|  |  |          11h / 17| Decimal count      *23|  |   |
///          |- - - - - - - - - - - -|  |  Field              |-----------------------|  |  -
///          |                       |  | Descriptor  12h / 18| ( Reserved for        |  |
///          :. . . . . . . . . . . .:  |  |array     13h / 19|   multi-user dBASE)*18|  |
///          :                       :  |  |                  |-----------------------|  |
///       n  |                       |__|__v_         14h / 20| Work area ID       *16|  |
///          |-----------------------|  |    \                |-----------------------|  |
///       n+1| Terminator (0Dh)      |  |     \       15h / 21| ( Reserved for        |  |
///          |=======================|  |      \      16h / 22|   multi-user dBASE )  |  |
///       m  | Database Container    |  |       \             |-----------------------|  |
///          :                    *15:  |        \    17h / 23| Flag for SET FIELDS   |  |
///          :                       :  |         |           |-----------------------|  |
///     / m+263                      |  |         |   18h / 24| ( Reserved )          |  |
///          |=======================|__v_ ___    |           :                       :  |
///          :                       :    ^       |           :                       :  |
///          :                       :    |       |           :                       :  |
///          :                       :    |       |   1Eh / 30|                       |  |
///          | Record structure      |    |       |           |-----------------------|  |
///          |                       |    |        \  1Fh / 31| Index field flag    *8|  |
///          |                       |    |         \_        |=======================| _v_____
///          |                       | Records
///          |-----------------------|    |
///          |                       |    |          _        |=======================| _______
///          |                       |    |         / 00h /  0| Record deleted flag *9|  ^
///          |                       |    |        /          |-----------------------|  |
///          |                       |    |       /           | Data               *10|  One
///          |                       |    |      /            : (ASCII)            *17: record
///          |                       |____|_____/             |                       |  |
///          :                       :    |                   |                       | _v_____
///          :                       :____|_____              |=======================|
///          :                       :    |
///          |                       |    |
///          |                       |    |
///          |                       |    |
///          |                       |    |
///          |                       |    |
///          |=======================|    |
///          |__End_of_File__________| ___v____  End of file ( 1Ah )  *11
///
/// </remarks>
public class XbFileHeader {

    private byte version;

    private final byte lastUpdateYear;

    private final byte lastUpdateMonth;

    private final byte lastUpdateDay;

    private int recordCount;

    private short headerSize;

    private short recordSize;

    private byte[] reserved1;

    private boolean transactionStatus;

    private boolean encryptionStatus;

    private byte[] reserved2;

    private byte[] reserved3;

    private byte mdxStatus;

    private byte codepage;

    private byte[] reserved4;

    private XbFileHeader(final DataInput input) throws IOException {

        // rozmiar tablicy sprawdzamys
        this.version = (byte)input.readUnsignedByte();
        this.lastUpdateYear = (byte)input.readUnsignedByte();
        this.lastUpdateMonth = (byte)input.readUnsignedByte();
        this.lastUpdateDay = (byte)input.readUnsignedByte();
    }

    private void readVersion(final DataInput input) throws IOException {

        this.version = input.readByte();
    }

    public byte getVersion() {

        return this.version;
    }

    private void readModifiedDate(final DataInput input) throws IOException {

        input.readByte();
        input.readByte();
        input.readByte();
    }

    public void readAll(final DataInput input) throws IOException {

        this.readVersion(input);
    }

}

class XbFileHeaderWriter {

    private XbFileHeader header;
    // tutaj mamy providera który przjmuj nagłowek i zapisuje od liku

}

class XbFileHeaderReader {

}

interface IXbFileHeader {

    static interface XbFileDescriptor {

        static interface XBase3 {

            static interface XbFileOffset {

                static final int OFFSET_VERSION = 0;

                static final int OFFSET_MODIFIED_DATE = 1;

                static final int OFFSET_RECORD_COUNT = 4;

                static final int OFFSET_HEADER_SIZE = 8;

                static final int OFFSET_RECORD_SIZE = 10;

                static final int OFFSET_RESERVED_1 = 12;

                static final int OFFSET_LAN = 15;

                static final int OFFSET_RESERVED_2 = 28;

                static final int OFFSET_FIELD_DESCRIPTORS = 32;

            }

            static interface XbFieldOffset {

                static final int FD_OFFSET_NAME = 0;

                static final int FD_OFFSET_TYPE = 11;

                static final int FD_OFFSET_DATA_ADDRESS = 12;

                static final int FD_OFFSET_LENGTH = 16;

                static final int FD_OFFSET_DECIMAL_COUNT = 17;

                static final int FD_OFFSET_RESERVED_MULTIUSER_1 = 18;

                static final int FD_OFFSET_WORK_AREA_ID = 20;

                static final int FD_OFFSET_RESERVED_MULTIUSER_2 = 21;

                static final int FD_OFFSET_SET_FIELDS_FLAG = 23;

                static final int FD_OFFSET_RESERVED = 24;

                static final int FD_OFFSET_INDEX_FIELD_FLAG = 31;

                static final int FD_OFFSET_NEXT_FIELD = 32;

            }
        }

        static interface XBase4 {

            static interface XbFileOffset extends XBase3.XbFileOffset {

                static final int OFFSET_INCOMPLETE_TRANSATION = 14;

                static final int OFFSET_ENCRYPTION_FLAG = 15;

                static final int OFFSET_FREE_RECORD_THREAD = 16;

                static final int OFFSET_RESERVED_2 = 20;

                static final int OFFSET_MDX_FLAG = 28;

                static final int OFFSET_LANGUAGE_DRIVER = 29;

                static final int OFFSET_RESERVED_3 = 30;

            }
        }
    }
}

/**
 * This class represents a DBF IV file header.
 *
 *
 *
 * DBF files are really wasteful on space but this legacy format lives on
 * because it's really really simple. It lacks
 * much in features though.
 *
 *
 * Thanks to Erik Bachmann for providing the DBF file structure information!!
 * http://www.clicketyclick.dk/databases/xbase/format/dbf.html
 *
 * _______________________ _______ 00h / 0| Version number *1| ^
 * |-----------------------| | 01h / 1| Date of last
 * update | | 02h / 2| YYMMDD *21| | 03h / 3| *14| | |-----------------------| |
 * 04h / 4| Number of records | Record 05h
 * / 5| in data file | header 06h / 6| ( 32 bits ) *14| | 07h / 7| | |
 * |-----------------------| | 08h / 8| Length of
 * header *14| | 09h / 9| structure ( 16 bits ) | | |-----------------------| |
 * 0Ah / 10| Length of each record | | 0Bh
 * / 11| ( 16 bits ) *2 *14| | |-----------------------| | 0Ch / 12| ( Reserved
 * ) *3| | 0Dh / 13| | |
 * |-----------------------| | 0Eh / 14| Incomplete transac.*12| |
 * |-----------------------| | 0Fh / 15| Encryption flag
 * *13| | |-----------------------| | 10h / 16| Free record thread | | 11h /
 * 17| (reserved for LAN | | 12h / 18| only )
 * | | 13h / 19| | | |-----------------------| | 14h / 20| ( Reserved for | | _
 * |=======================| ______ |
 * multi-user dBASE ) | | / 00h / 0| Field name in ASCII | ^ : ( dBASE III+ - )
 * : | / : (terminated by 00h) : | : : | |
 * | | | 1Bh / 27| | | | 0Ah / 10| | | |-----------------------| | |
 * |-----------------------| For 1Ch / 28| MDX flag
 * (dBASE IV)*14| | | 0Bh / 11| Field type (ASCII) *20| each
 * |-----------------------| | | |-----------------------|
 * field 1Dh / 29| Language driver *5| | / 0Ch / 12| Field data address | |
 * |-----------------------| | / | *6| | 1Eh /
 * 30| ( Reserved ) | | / | (in memory !!!) | | 1Fh / 31| *3| | / 0Fh / 15|
 * (dBASE III+) | |
 * |=======================|__|____/ |-----------------------| | - 20h / 32| | |
 * ^ 10h / 16| Field length *22| | | |- -
 * - - - - - - - - - -| | | |-----------------------| | | *7 | *19| | | 11h /
 * 17| Decimal count *23| | | |- - - - - - -
 * - - - - -| | Field |-----------------------| | - | | | Descriptor 12h / 18| (
 * Reserved for | | :. . . . . . . . . . .
 * .: | |array 13h / 19| multi-user dBASE)*18| | : : | |
 * |-----------------------| | n | |__|__v_ 14h / 20| Work area ID
 * *16| | |-----------------------| | \ |-----------------------| | n+1|
 * Terminator (0Dh) | | \ 15h / 21| ( Reserved
 * for
 * | | |=======================| | \ 16h / 22| multi-user dBASE ) | | m |
 * Database Container | | \
 * |-----------------------| | : *15: | \ 17h / 23| Flag for SET FIELDS | | : :
 * | | |-----------------------| | / m+263
 * | | | 18h / 24| ( Reserved ) | | |=======================|__v_ ___ | : : | :
 * : ^ | : : | : : | | : : | : : | | 1Eh /
 * 30| | | | Record structure | | | |-----------------------| | | | | \ 1Fh /
 * 31| Index field flag *8| | | | | \_
 * |=======================| _v_____ | | Records |-----------------------| | | |
 * | _ |=======================| _______ |
 * | | / 00h / 0| Record deleted flag *9| ^ | | | / |-----------------------| |
 * | | | / | Data *10| One | | | / :
 * (ASCII) *17: record | |____|_____/ | | | : : | | | _v_____ : :____|_____
 * |=======================| : : | | | | | | |
 * | | | | | | | | | |=======================| | |__End_of_File__________|
 * ___v____ End of file ( 1Ah ) *11
 *
 */
