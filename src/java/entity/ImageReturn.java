package entity;

import java.io.InputStream;

public class ImageReturn {
    private InputStream[] inss;
    public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the inss
	 */
	public InputStream[] getInss() {
		return inss;
	}
	/**
	 * @param inss the inss to set
	 */
	public void setInss(InputStream[] inss) {
		this.inss = inss;
	}
	private int count;
}
