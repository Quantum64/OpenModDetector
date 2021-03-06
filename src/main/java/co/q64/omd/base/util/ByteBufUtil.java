package co.q64.omd.base.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;

@Singleton
public class ByteBufUtil {
	protected @Inject ByteBufUtil() {}

	public int varIntByteCount(int toCount) {
		return (toCount & 0xFFFFFF80) == 0 ? 1 : ((toCount & 0xFFFFC000) == 0 ? 2 : ((toCount & 0xFFE00000) == 0 ? 3 : ((toCount & 0xF0000000) == 0 ? 4 : 5)));
	}

	public int readVarInt(ByteBuf buf, int maxSize) {
		int i = 0;
		int j = 0;
		byte b0;

		do {
			b0 = buf.readByte();
			i |= (b0 & 127) << j++ * 7;

			if (j > maxSize) {
				throw new RuntimeException("VarInt too big");
			}
		} while ((b0 & 128) == 128);

		return i;
	}

	public int readVarShort(ByteBuf buf) {
		int low = buf.readUnsignedShort();
		int high = 0;
		if ((low & 0x8000) != 0) {
			low = low & 0x7FFF;
			high = buf.readUnsignedByte();
		}
		return ((high & 0xFF) << 15) | low;
	}

	public void writeVarShort(ByteBuf buf, int toWrite) {
		int low = toWrite & 0x7FFF;
		int high = (toWrite & 0x7F8000) >> 15;
		if (high != 0) {
			low = low | 0x8000;
		}
		buf.writeShort(low);
		if (high != 0) {
			buf.writeByte(high);
		}
	}

	public void writeVarInt(ByteBuf to, int toWrite, int maxSize) {
		while ((toWrite & -128) != 0) {
			to.writeByte(toWrite & 127 | 128);
			toWrite >>>= 7;
		}

		to.writeByte(toWrite);
	}

	public String readUTF8String(ByteBuf from) {
		int len = readVarInt(from, 2);
		String str = from.toString(from.readerIndex(), len, Charsets.UTF_8);
		from.readerIndex(from.readerIndex() + len);
		return str;
	}

	public void writeUTF8String(ByteBuf to, String string) {
		byte[] utf8Bytes = string.getBytes(Charsets.UTF_8);
		writeVarInt(to, utf8Bytes.length, 2);
		to.writeBytes(utf8Bytes);
	}

	public String getContentDump(ByteBuf buffer) {
		int currentLength = buffer.readableBytes();
		StringBuffer returnString = new StringBuffer((currentLength * 3) + // The
																			// hex
				(currentLength) + // The ascii
				(currentLength / 4) + // The tabs/\n's
				30); // The text

		// returnString.append("Buffer contents:\n");
		int i, j; // Loop variables
		for (i = 0; i < currentLength; i++) {
			if ((i != 0) && (i % 16 == 0)) {
				// If it's a multiple of 16 and i isn't null, show the ascii
				returnString.append('\t');
				for (j = i - 16; j < i; j++) {
					if (buffer.getByte(j) < 0x20 || buffer.getByte(j) > 0x7F)
						returnString.append('.');
					else
						returnString.append((char) buffer.getByte(j));
				}
				// Add a linefeed after the string
				returnString.append("\n");
			}

			returnString.append(Integer.toString((buffer.getByte(i) & 0xF0) >> 4, 16) + Integer.toString((buffer.getByte(i) & 0x0F) >> 0, 16));
			returnString.append(' ');
		}

		// Add padding spaces if it's not a multiple of 16
		if (i != 0 && i % 16 != 0) {
			for (j = 0; j < ((16 - (i % 16)) * 3); j++) {
				returnString.append(' ');
			}
		}
		// Add the tab for alignment
		returnString.append('\t');

		// Add final chararacters at right, after padding

		// If it was at the end of a line, print out the full line
		if (i > 0 && (i % 16) == 0) {
			j = i - 16;
		} else {
			j = (i - (i % 16));
		}

		for (; i >= 0 && j < i; j++) {
			if (buffer.getByte(j) < 0x20 || buffer.getByte(j) > 0x7F)
				returnString.append('.');
			else
				returnString.append((char) buffer.getByte(j));
		}

		// Finally, tidy it all up with a newline
		returnString.append('\n');
		returnString.append("Length: " + currentLength);

		return returnString.toString();
	}
}