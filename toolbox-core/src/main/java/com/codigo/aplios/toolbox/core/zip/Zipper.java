package com.codigo.aplios.toolbox.core.zip;
//
//    // -----------------------------------------------------------------------------------------------------------------
//    private static int FileDescriptorSize = 33; // 32bytes + terminator byte;
//
//    // -----------------------------------------------------------------------------------------------------------------
//    private static int ColumnDescriptorSize = 32;
//
//    // -----------------------------------------------------------------------------------------------------------------
//    public void readDbf() throws IOException {
//        // ...test dbf read with random access file
//
//        // final Path dbfPath = Paths.get("D:/TBKMK.dbf");
//        // final Path dbfPath = Paths.get("D:/XBas1.dbf");
//        final Path dbfPath = Paths.get("D:/FREE.dbf");
//        // final Path dbfPath = Paths.get("D:", "County M.dbf");
//        // final Path dbfPath = Paths.get("D:", "PYACFL.dbf");
//
//        final BasicFileAttributeView view = Files.getFileAttributeView(dbfPath, BasicFileAttributeView.class);
//        final BasicFileAttributes attributes = view.readAttributes();
//
////		System.out.println("Creation Time: " + attributes.creationTime());
////		System.out.println("Last Accessed Time: " + attributes.lastAccessTime());
////		System.out.println("Last Modified Time: " + attributes.lastModifiedTime());
////		System.out.println("File Key: " + attributes.fileKey());
////		System.out.println("Directory: " + attributes.isDirectory());
////		System.out.println("Other Type of File: " + attributes.isOther());
////		System.out.println("Regular File: " + attributes.isRegularFile());
////		System.out.println("Symbolic File: " + attributes.isSymbolicLink());
////		System.out.println("Size: " + attributes.size());
//        try (FileChannel fch = FileChannel.open(dbfPath, READ)) {
//            final ByteBuffer dbfBuffer = ByteBuffer.allocate(Zipper.FileDescriptorSize - 1);
//            dbfBuffer.order(ByteOrder.LITTLE_ENDIAN);
//            dbfBuffer.clear();
//            fch.read(dbfBuffer);
//            dbfBuffer.flip();
//
//            dbfBuffer.get();
//
//            dbfBuffer.get();
//            dbfBuffer.get();
//            dbfBuffer.get();
//            final int recCount = dbfBuffer.getInt();
//            final short headBytes = dbfBuffer.getShort();
//            final short recordBytes = dbfBuffer.getShort();
//
//            // read the field length in bytes
//            // if field type is char, then read FieldLength and Decimal count as one number to allow char fields to be
//            // longer than 256 bytes (ASCII char). This is the way Clipper and FoxPro do it, and there is really no
//            // downside
//            // since for char fields decimal count should be zero for other versions that do not support this extended
//            // functionality.
//            // ---------------------------------------------------------------------------------------------------------
//            final int nNumFields = (headBytes - Zipper.FileDescriptorSize) / Zipper.ColumnDescriptorSize;
//            final List<XbFieldInfo> columns = new ArrayList<>();
//
//            for (int idx = 0; idx < nNumFields; idx++) {
//                dbfBuffer.clear();
//                fch.read(dbfBuffer);
//                dbfBuffer.flip();
//                columns.add(new XbFieldInfo(dbfBuffer));
//            }
//
//            columns.forEach(System.out::println);
//            final ByteBuffer data = ByteBuffer.allocate(recordBytes);
//            for (int jdx = 1; jdx <= recCount; jdx++) {
//                data.clear();
//                fch.read(data);
//                data.flip();
//                System.out.printf("RecNO:" + jdx);
//                for (int idx = 0; idx < nNumFields; idx++) {
//                    final byte[] raw = new byte[columns.get(idx)
//                            .getFieldSize()];
//                    data.get(raw);
//                    final String value = new String(raw,
//                            Charset.forName("utf-8"));
//                    System.out.printf("%30s", value.trim());
//                }
//                System.out.println("");
//                System.out.println(String.join("", Collections.nCopies(800, "-")));
//            }
//
//            fch.close();
//        } catch (final Exception ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        final byte val = (byte)200;
//        new Zipper().readDbf();
//
//        // ...test bigendian
//        final ByteBuffer bigEndian = ByteBuffer.allocate(16);
//        bigEndian.order(ByteOrder.BIG_ENDIAN);
//        final IntBuffer i = bigEndian.asIntBuffer();
//        i.put(1)
//                .put(2)
//                .put(3)
//                .put(4);
//        bigEndian.rewind();
//        bigEndian.get();
//
//        // ByteBuffer littleEndian = ByteBuffer.wrap(bytes);
//        // littleEndian.order(ByteOrder.LITTLE_ENDIAN);
//        // littleEndian.rewind();
//        // value = littleEndian.get();
//        new DecimalFormat("#+,##0.00;#,##0.00-");
//
//        // final Path filepath = Paths.get("D:/kdpw.install/rdi/RDI_V9.5_CORE_PRODUCT_INSTALL_1_E.zip");
//        // final Path filepath = Paths.get("D:/kdpw.tools/jtoolbox/toolbox.jar");
//        final Path filepath = Paths.get("F:/allmarks_2017.09.21.zip");
//
//        Zipper.printEntries(System.out, filepath.toAbsolutePath()
//                .toString());
//    }
//
//    // -----------------------------------------------------------------------------------------------------------------
//    public static void printEntries(PrintStream stream, String zip) {
//        try (ZipFile zipFile = new ZipFile(zip)) {
//            final Predicate<ZipEntry> isFile = ze -> !ze.isDirectory();
//            final Predicate<ZipEntry> isJava = ze -> ze.getName()
//                    .matches(".*class");
//            final Comparator<ZipEntry> bySize = (ze1, ze2) -> Long.valueOf(ze2.getSize() - ze1.getSize())
//                    .intValue();
//            zipFile.stream()
//                    .filter(isFile.and(isJava))
//                    .sorted(bySize)
//                    .forEach(ze -> Zipper.print(stream, ze));
//        } catch (final IOException e) {
//            // error while opening a ZIP file
//        }
//    }
//
//    // -----------------------------------------------------------------------------------------------------------------
//    private static void print(PrintStream stream, ZipEntry zipEntry) {
//        stream.println(zipEntry.getName() + ", size = " + zipEntry.getSize());
//    }
//
//    // -----------------------------------------------------------------------------------------------------------------
//}
