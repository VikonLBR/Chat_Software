

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class send implements Runnable{
	
	private DatagramSocket ds;
	private DatagramPacket dp;
	private BufferedReader br;
	private byte[] bread;
	send(DatagramSocket ds) throws IOException{
		br=new BufferedReader(new InputStreamReader(System.in));
		this.ds=ds;
	}
	
	public void run(){
		String str=null;
		try{
		while(!(str=br.readLine()).endsWith("over")){//也是阻塞式方法
			bread=str.getBytes();
			dp=new DatagramPacket(bread,bread.length,InetAddress.getByName("192.168.0.255"),2333);
			ds.send(dp);
		}
		bread=str.getBytes();
		dp=new DatagramPacket(bread,bread.length,InetAddress.getByName("192.168.0.255"),2333);//
		ds.send(dp);
		ds.close();
		br.close();	
		}
		catch(Exception e){
			Sop.print(e.toString());
		}
	}
}


class receive implements Runnable{

	private DatagramSocket ds;
	private DatagramPacket dp;
	private Calendar time;
	private SimpleDateFormat sdf;
	private String str_time;

	private byte[] bread;
	receive(DatagramSocket ds) throws IOException{
		
		this.ds=ds;

		bread=new byte[1024];
		dp=new DatagramPacket(bread,1024);
		sdf=new SimpleDateFormat("MM-hh HH:mm:ss");
		time=Calendar.getInstance();
		
	}
	
	public void run(){
		String str=null;
		try{
		while(true){//也是阻塞式方法
			ds.receive(dp);
			str=new String(dp.getData(),0,dp.getLength());
			if(str.endsWith("over"))
				break;
			else
			{
				
				str_time=sdf.format(time.getTime());
				Sop.print(str_time);
				Sop.print(str);
				Sop.print("");
			}
		}
		
		ds.send(dp);
		ds.close();
		}
		catch(Exception e){
			Sop.print(e.toString());
		}
	}
}






public class QQDemo {

	public static void main(String[] args) throws IOException {
		DatagramSocket ds=new DatagramSocket(2333);
		
		Thread t1=new Thread(new send(ds));
		Thread t2=new Thread(new receive(ds));
		t1.start();
		t2.start();

	}

}
