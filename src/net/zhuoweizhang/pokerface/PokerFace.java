package net.zhuoweizhang.pokerface;

import java.lang.reflect.*;

import java.nio.ByteBuffer;

public class PokerFace {

	/** Page can be read.  */
	public static final int PROT_READ = 0x1;
	/** Page can be executed.  */
	public static final int PROT_WRITE = 0x2;
	/** Page can be executed.  */
	public static final int PROT_EXEC = 0x4;
	/** Page cannot be accessed.  */
	public static final int PROT_NONE = 0x0;

	/** Query parameter for the memory page size, used for sysconf. */
	public static final int _SC_PAGESIZE = 0x0027;

	/**
	 * Changes the protection on a page of memory.
	 * @param addr The starting address of the memory. Must be page aligned.
	 * @param len The length of memory to change. Also page aligned.
	 * @param prot The new protection: the PROT_* parameters ORed together
	 */

	public static native int mprotect(long addr, long len, int prot);

	/** Get system configuration. Calls sysconf from libcore through reflection. */
	public static long sysconf(int name) throws Exception {
		Method osSysconf = Class.forName("libcore.io.Os").getMethod("sysconf", Integer.TYPE);
		return (Long) osSysconf.invoke(Class.forName("libcore.io.Libcore").getField("os").get(null), name);
	}

	/** Creates a direct ByteBuffer to an area of memory using a libcore implementation. */

	public static ByteBuffer createDirectByteBuffer(long address, long length) throws Exception {
		Constructor cons = Class.forName("java.nio.ReadWriteDirectByteBuffer").getDeclaredConstructor(Integer.TYPE, Integer.TYPE);
		cons.setAccessible(true);
		return (ByteBuffer) cons.newInstance((int) address, (int) length);
	}

	static {
		System.loadLibrary("pokerface");
	}

}
