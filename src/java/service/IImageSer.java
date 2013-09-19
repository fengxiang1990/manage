package service;

import java.io.File;
import java.io.IOException;

public interface IImageSer {

	public abstract String uploadFile(File file, int type, String username);

	public abstract String uploadByByte(String content, int type,
			String username);

	public abstract byte[][] getImagePgNum(int pg, int size);

	public abstract byte[] getBy(int id) throws IOException;

	public abstract String[] getImagePg(int pg, int size);

}