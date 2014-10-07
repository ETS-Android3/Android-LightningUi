package com.cube.storm.ui.lib.manager;

import android.text.TextUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * Provides JSON from a file location in different formats
 *
 * @author Matt Allen
 */

public class FileManager
{
	private static FileManager instance;

	public static FileManager getInstance()
	{
		if (instance == null)
		{
			synchronized (FileManager.class)
			{
				if (instance == null)
				{
					instance = new FileManager();
				}
			}
		}

		return instance;
	}

	/**
	 * Get the age of the file in millis
	 * @param filePath Absolute path to the file
	 * @return Age of file in millis (Compared against current system time)
	 */
	public long getFileAge(String filePath)
	{
		if (!TextUtils.isEmpty(filePath))
		{
			return System.currentTimeMillis() - new File(filePath).lastModified();
		}
		return 0;
	}

	/**
	 * Check if the file exists from an absolute file path
	 * @param filePath The absolute path to the file on the local filesystem
	 * @return {@code true} if file exists
	 */
	public boolean fileExists(String filePath)
	{
		return !TextUtils.isEmpty(filePath) && new File(filePath).exists();
	}

	/**
	 * Read the file and return the byte array of its contents
	 * @param filePath The absolute path to the file on the local filesystem
	 * @return Byte array of file contents
	 */
	public byte[] readFile(String filePath)
	{
		return readFile(filePath);
	}

	/**
	 * Read the file and return a string of the contents
	 * @param filePath The absolute path to the file on the local filesystem
	 * @return String of file contents
	 */
	public String readFileAsString(String filePath)
	{
		return readFileAsString(new File(filePath));
	}

	/**
	 * Read from file and return a string representation of the contents
	 * @param file A file to read from
	 * @return String of file contents
	 */
	public String readFileAsString(File file)
	{
		return new String(readFile(file));
	}

	/**
	 * Read from file and return as a JSON element
	 * @param filePath The absolute path to the file on the local filesystem
	 * @return JSON representation of file contents
	 */
	public JsonElement readFileAsJson(String filePath)
	{
		return readFileAsJson(new File(filePath));
	}

	/**
	 * Read from file and return as a JSON element
	 * @param file The file on the local filesystem
	 * @return JSON representation of file contents
	 */
	public JsonElement readFileAsJson(File file)
	{
		return new JsonParser().parse(readFileAsString(file));
	}

	/**
	 * Read the file and return the byte array of its contents
	 * @param file The absolute path to the file on the local filesystem
	 * @return Byte array of file contents
	 */
	public byte[] readFile(File file)
	{
		try
		{
			return readFile(new FileInputStream(file));
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * Read the input stream and return the byte array of its contents
	 * @param input The input stream of the file to read
	 * @return Byte array of file contents
	 */
	public byte[] readFile(InputStream input)
	{
		ByteArrayOutputStream bos = null;

		try
		{
			bos = new ByteArrayOutputStream(8192);

			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];

			int len = 0;
			while ((len = input.read(buffer)) > 0)
			{
				bos.write(buffer, 0, len);
			}

			return bos.toByteArray();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				input.close();

				if (bos != null)
				{
					bos.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Gets the files
	 * @param filePath
	 * @return
	 */
	public String getFileHash(String filePath)
	{
		InputStream is = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			is = new FileInputStream(filePath);
			is = new DigestInputStream(is, md);

			byte[] contents = readFile(filePath);

			StringBuilder signature = new StringBuilder();
			byte[] messageDigest = md.digest(contents);

			for (int i = 0; i < messageDigest.length; i++)
			{
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1)
				{
					signature.append('0');
				}

				signature.append(hex);
			}

			return signature.toString();
		}
		catch (Exception ignored){}
		finally
		{
			try
			{
				if (is != null)
				{
					is.close();
				}
			}
			catch (Exception ignored){}
		}

		return "";
	}
}
