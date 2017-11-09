package com.codigo.aplios.toolbox.xbase.core.builder;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

interface ICharacterBuilder {
}

interface IXbField {

    IXbField assignFieldCode(char fieldCode);

    IXbField assignFieldLength(int fieldLength);

    IXbField assignFieldDecimal(int fieldDecimal);

    IXbField create();

}

enum XbField implements IXbField, IXbFieldKind {

    CHARACTER,
    NUMERIC,
    LOGICAL,
    INTEGER,
    DOUBLE,
    FLOAT,
    DATE;

    @Override
    public IXbField assignFieldCode(final char fieldCode) {

        this.fldCode = fieldCode;
        return this;
    }

    @Override
    public IXbField assignFieldLength(final int fieldLength) {

        this.fldLength = fieldLength;
        return this;
    }

    @Override
    public IXbField assignFieldDecimal(final int fieldDecimal) {

        this.fldDecimal = fieldDecimal;
        return this;
    }

    @Override
    public IXbField create() {

        return this;
    }

    private char fldCode;

    private int fldLength;

    private int fldDecimal;

    @Override
    public void setFieldKind() {

        // TODO Auto-generated method stub
    }

}

interface IXbTableFormat {

    IXbTableName assignFormat(XbTableBuilder.XbFormats format);

}

interface IXbTableName {

    IXbTableField assignName(String tableName);

}

interface IXbTableField {

    XbField setFieldName(String name);

    // IXbTableField setFieldType(String name);
}

interface IXbFieldCreate {

    void create();

}

interface IXbFieldKind {

    void setFieldKind();

}

public class XbTableBuilder implements IXbTableFormat, IXbTableName, IXbTableField, IXbFieldKind {

    enum XbFormats {

        XBASE3 {
            @Override
            public EnumSet<XbField> fieldTypes() {

                XbField.CHARACTER.assignFieldCode('C')
                        .assignFieldLength(254)
                        .assignFieldDecimal(2)
                        .create();

                return EnumSet.of(XbField.CHARACTER, XbField.NUMERIC);
            }

        },
        XBASE4 {
            @Override
            public EnumSet<XbField> fieldTypes() {

                // TODO Auto-generated method stub
                return null;
            }

        };

        public abstract EnumSet<XbField> fieldTypes();

    }

    public static IXbTableFormat init() {

        return new XbTableBuilder();
    }

    // http://mindprod.com/jgloss/bytebuffer.html
    // http://www.tek-tips.com/faqs.cfm?fid=3162
    // http://www.oocities.org/geoff_wass/dBASE/GaryWhite/dBASE/FAQ/qformt.htm
    public static void main(final String[] args) throws IOException, InterruptedException {

        // 2 Foxbase
        // 3 Foxbase/Foxpro/dBaseIII/IV/V no memo
        // 48 Visual Foxpro
        // 67 dBase IV SQL table no memo
        // 99 dBase IV SQL system file no memo
        // 131 Foxbase/dBaseIII Plus with memo
        // 139 dBaseIV/V with memo
        // 203 dBaseIV SQL table with memo
        // 245 Foxpro 2.x with memo
        // 251 Foxbase
        // int bufPoz = 0, rem = 0;
        final ByteBuffer simple = ByteBuffer.allocate(32);
        // simple.put((byte)0xff);
        // //1
        // bufPoz = simple.position();
        //
        // simple.position(5);
        // simple.put((byte)0xff);
        // //6
        // bufPoz = simple.position();
        //
        // //4
        // rem = simple.remaining();
        // simple.limit(7);
        //
        // simple.rewind();

        final Path path = Paths.get("d://test-write.txt");
        if (!Files.exists(path))
            Files.createFile(path);

        // Path path = Paths.get("d://dbase.dbf");
        //
        // AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path,
        // StandardOpenOption.READ);
        //
        // ByteBuffer buffer = ByteBuffer.allocate(1024);
        // long position = 0;
        //
        // Future<Integer> operation = fileChannel.read(buffer, position);
        //
        // while (!operation.isDone())
        // ;
        //
        // buffer.flip();
        // byte[] data = new byte[buffer.limit()];
        // buffer.get(data);
        // System.out.println(new String(data));
        // buffer.clear();

        /*
         * Path path = Paths.get("data/test-write.txt"); AsynchronousFileChannel
         * fileChannel =
         * AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
         *
         * ByteBuffer buffer = ByteBuffer.allocate(1024); long position = 0;
         *
         * buffer.put("test data".getBytes()); buffer.flip();
         *
         * Future<Integer> operation = fileChannel.write(buffer, position);
         * buffer.clear();
         *
         * while(!operation.isDone());
         *
         * System.out.println("Write done");
         */
        FileInputStream ios = null;
        DataInputStream dis = null;
        try {
            ios =
                    new FileInputStream("d://codigo.warehouse//Bazy danych//XBase//sermilk//FAKP.dbf");
            dis = new DataInputStream(ios);
            ios.getChannel();
            int position = 0;
            for (int idx = 0; idx < 32; idx++) {
                final byte val = dis.readByte();
                simple.put(val);
                System.out.println(simple.get(position));
                // simple.flip();
                position++;

            }

        } catch (final FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }

        Charset.forName("ISO-8859-1");
        Charset.forName("Cp1250");
        Charset.forName("Cp857");

        XbTableBuilder.init()
                .assignFormat(XbFormats.XBASE3)
                .assignName("Table1")
                .setFieldName("2323");
        XbField.CHARACTER.create();
        // .setFieldName("sdsd").CHARACTER.
        // .setFieldType("");

        // final IXbField xbChar = XbField.CHARACTER.assignFieldCode('C')
        // .assignFieldLength(254)
        // .assignFieldDecimal(2)
        // .create();
        //
        // final IXbField xbNum = XbField.NUMERIC.assignFieldCode('N')
        // .assignFieldLength(18)
        // .assignFieldDecimal(0);
        //
        // final IXbField xbDate = XbField.DATE.assignFieldCode('D')
        // .assignFieldLength(8)
        // .assignFieldDecimal(0);
        //
        // final IXbField xbLogic = XbField.LOGICAL.assignFieldCode('L')
        // .assignFieldLength(1)
        // .assignFieldDecimal(0);
        //
        // final IXbField xbFloat = XbField.FLOAT.assignFieldCode('F')
        // .assignFieldLength(20)
        // .assignFieldDecimal(0);
        //
        // EnumSet<XbField> fieldSet = EnumSet.of(XbField.CHARACTER, XbField.NUMERIC);
    }

    @Override
    public IXbTableName assignFormat(final XbFormats format) {

        return this;
    }

    @Override
    public XbField setFieldName(final String name) {

        return XbField.CHARACTER;
    }

    @Override
    public IXbTableField assignName(final String tableName) {

        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public void setFieldKind() {

    }

}
