package cn.com.nightfield.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferTest {

	private static final int BUFFER_SIZE = 1024;
	private static final String FILE_NAME = "c:\\tmp\\test.txt";
	private static final String QUOTE = "If your actions inspire others to dream more, learn more, do  more and become more, you are a leader.";
	public static void main(String[] args) throws IOException {

	    System.out.println("Starting MyBufferWriteExample...");
	    FileOutputStream fileOS = new FileOutputStream(FILE_NAME);
	    FileChannel channel = fileOS.getChannel();
	    ByteBuffer myBuffer = ByteBuffer.allocate(BUFFER_SIZE);
	  
	    try {
		    myBuffer.put(QUOTE.getBytes());
		    myBuffer.flip();
		    int bytesWritten = channel.write(myBuffer);
		    System.out.println(String.format("%d bytes have been written to disk...", bytesWritten));
		    System.out.println(String.format("Current buffer position is %d", myBuffer.position()));
	    } finally  {
		    channel.close();
		    fileOS.close();
	    }
	  
	    try {
	    	Thread.sleep(200);
	    } catch (InterruptedException e) {
	    	e.printStackTrace();
	    }
	  
	    FileInputStream fileIS = new FileInputStream(FILE_NAME);
	    FileChannel inChannel = fileIS.getChannel();
	    System.out.println("reading from file...");
	    while (inChannel.read(myBuffer) > 0) {
		    myBuffer.flip();
		    while (myBuffer.hasRemaining()) {
			    System.out.println(myBuffer.getChar());
		    }
		    myBuffer.clear();
	    }
	    inChannel.close();
	    fileIS.close();
	}
}