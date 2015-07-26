package com.ws.mobileline.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

/**
 * 文件工具类
 * 
 * @author ycf
 * 
 */
public class Files {

	/**
	 * 在SD卡上创建文件
	 * 
	 * @param dir
	 *            目录路径
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private static File makeFile(String path) throws IOException {
		Logs.d("makeFile-" + path);
		if (!isAvailable()) {
			return null;
		}
		File file = new File(path);
		if (file.exists()) {
			return file;
		} else {
			if (path.contains("/")) {
				String dir = path.substring(0, path.lastIndexOf('/'));
				makeDir(dir);
			}
			if (!file.createNewFile()) {
				return null;
			}
		}
		Logs.d("makeFile-" + path);
		return file;
	}

	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dir
	 * @return
	 */
	private static File makeDir(String dir) {
		Logs.d("makeDir-" + dir);
		if (!isAvailable()) {
			return null;
		}
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			if (!dirFile.mkdirs()) {
				return null;
			}
		}
		Logs.d("makeDir-" + dir);
		return dirFile;
	}

	/***
	 * 获取SD卡的剩余容量,单位是Byte
	 * 
	 * @return
	 */
	public static long getAvailableSize() {
		if (isAvailable()) {
			// 取得sdcard文件路径
			File pathFile = android.os.Environment
					.getExternalStorageDirectory();
			android.os.StatFs statfs = new android.os.StatFs(pathFile.getPath());
			// 获取SDCard上每个block的SIZE
			long nBlocSize = statfs.getBlockSize();
			// 获取可供程序使用的Block的数量
			long nAvailaBlock = statfs.getAvailableBlocks();
			// 计算 SDCard 剩余大小Byte
			long nSDFreeSize = nAvailaBlock * nBlocSize;
			return nSDFreeSize;
		}
		return 0;
	}

	/***
	 * 获取SD卡是否可用
	 * 
	 * @return
	 */
	public static boolean isAvailable() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/***
	 * 从sd卡中读取文件，并且以InputStream返回
	 * 
	 * @param fileName
	 * @return
	 */
	public static InputStream readISFrom(String path) {
		File file = new File(path);
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			return is;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 从InputStream中读取文件，并且以字节流返回
	 * 
	 * @param fileName
	 * @return
	 */
	public static byte[] readFrom(InputStream is) {
		try {
			byte[] data = new byte[is.available()];
			is.read(data);
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new byte[] {};
	}

	/***
	 * 从sd卡中读取文件，并且以字节流返回
	 * 
	 * @param fileName
	 * @return
	 */
	public static byte[] readFrom(String path) {
		return readFrom(readISFrom(path));
	}

	/**
	 * 将一个byte[]写入到SD卡中
	 */
	public static void writeTo(String path, byte[] bytes) {
		OutputStream output = null;
		try {
			int size = bytes.length;
			// 拥有可读可写权限，并且有足够的容量
			if (isAvailable() && size < getAvailableSize()) {
				File file = makeFile(path);
				output = new FileOutputStream(file);
				output.write(bytes);
				output.flush();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public static void writeTo(String path, InputStream is) {
		writeTo(path, readFrom(is));
	}

}
