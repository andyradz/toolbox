package com.codigo.aplios.toolbox.core.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileSample {

	public static void listFiles(Path path) throws IOException {

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				if (Files.isDirectory(entry))
					FileSample.listFiles(entry);
				System.out.println(entry);
			}
		}
	}

	public static void main(String[] args) throws IOException {

		Path path = Paths.get("d:/");
		FileVisitor<Path> fsv = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				if (attrs.isRegularFile())
					System.out.println(file);
				return FileVisitResult.CONTINUE;
			}
		};

		Files.walkFileTree(path, fsv);

	}
}

// public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
// try
// {
// if (attrs.isRegularFile()) {
// System.out.println(file);
// }
// return FileVisitResult.CONTINUE;
//
// }
// catch(IOException e)
// {
// return FileVisitResult.CONTINUE;
// }
// }

/*
 * // try { // // Files.walkFileTree(path, fsv); // } catch (IOException e) { // // e.printStackTrace(); // } //
 * listFiles(Paths.get("c:/Windows")); // Path path=
 * Paths.get("C:\\Users\\Danny\\Documents\\workspace\\Test\\bin\\SomeFiles"); // final List<Path> files=new
 * ArrayList<>(); // try { // Files.walkFileTree(path, new SimpleFileVisitor<Path>(){ // @Override // public
 * FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException // { // if(!attrs.isDirectory()){
 * // files.add(file); // } // return FileVisitResult.CONTINUE; // } // }); // } catch (IOException e) { //
 * e.printStackTrace(); // }
 *
 * // test(); // try { // Files.walk(Paths.get("c:/Program Files")) // .filter(Files::isRegularFile) //
 * .forEach(System.out::println); // } catch (IOException e) { // // e.printStackTrace(); // }
 *
 * // DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() { // @Override // public boolean
 * accept(Path path) throws IOException { // // return (Files.isDirectory(path)); // } // }; // // Path dir =
 * FileSystems.getDefault() // .getPath("c:/Windows"); // try (DirectoryStream<Path> stream =
 * Files.newDirectoryStream(dir, filter)) { // for (Path path : stream) { // // Iterate over the paths in the directory
 * and print filenames // System.out.println(path); // } // } catch (IOException e) { // e.printStackTrace(); // } }
 */

// public static void test() throws IOException, InterruptedException {
// *
// * final Path rootDir = Paths.get("D://kdpw.tools//eclipse");
// *
// * // Walk thru mainDir directory
// * Files.walkFileTree(rootDir, new FileVisitor<Path>() {
// * // First (minor) speed up. Compile regular expression pattern only one time.
// * private Pattern pattern = Pattern.compile("^(.*?)");
// *
// * @Override
// * public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes atts) throws
// IOException
// * {
// *
// * boolean matches = pattern.matcher(path.toString())
// * .matches();
// *
// * // TODO: Put here your business logic when matches equals true/false
// *
// * return (matches) ? FileVisitResult.CONTINUE : FileVisitResult.SKIP_SUBTREE;
// * }
// *
// * @Override
// * @Override
// public FileVisitResult visitFile(Path path, BasicFileAttributes mainAtts) throws IOException {
// *
// * boolean matches = pattern.matcher(path.toString())
// * .matches();
// *
// * // TODO: Put here your business logic when matches equals true/false
// *
// * return FileVisitResult.CONTINUE;
// * }
// *
// * @Override
// * public FileVisitResult postVisitDirectory(Path path, IOException exc) throws IOException {
// *
// * // TODO Auto-generated method stub
// * return FileVisitResult.CONTINUE;
// * }
// *
// * @Override
// * public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
// *
// * exc.printStackTrace();
// *
// * // If the root directory has failed it makes no sense to continue
// * return path.equals(rootDir) ? FileVisitResult.TERMINATE : FileVisitResult.CONTINUE;
// * }
// * });
// * }*
// }*/}