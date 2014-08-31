package edu.harvard.mgh.lcs.sprout.study.web.filter;

import java.io.IOException;

import javax.servlet.ServletOutputStream;

public class ByteArrayServletOutputStream extends ServletOutputStream {

	protected byte buf[];

	protected int count;

	public ByteArrayServletOutputStream() {
		this(32);
	}

	public ByteArrayServletOutputStream(int size) {
		if (size < 0) {
			throw new IllegalArgumentException("Negative initial size: " + size);
		}
		buf = new byte[size];
	}

	public synchronized byte toByteArray()[] {
		return copyOf(buf, count);
	}

	public synchronized void reset() {
		count = 0;
	}

	public synchronized int size() {
		return count;
	}

	public void enlarge(int size) {
		if (size > buf.length) {
			buf = copyOf(buf, Math.max(buf.length << 1, size));
		}
	}

	@Override
	public synchronized void write(int b) throws IOException {
		int newcount = count + 1;
		enlarge(newcount);
		buf[count] = (byte) b;
		count = newcount;
	}

	/**
	 * copy from: jdk1.6, java.util.Arrays.copyOf(byte[] original, int
	 * newLength)
	 */
	private static byte[] copyOf(byte[] original, int newLength) {
		byte[] copy = new byte[newLength];
		System.arraycopy(original, 0, copy, 0, Math.min(original.length,
				newLength));
		return copy;
	}
}
