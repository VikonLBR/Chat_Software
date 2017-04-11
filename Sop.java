package ipaddress;
/*
an easy way to use
System.out.println
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;


public class Sop {

		public static void print(Object obj){
			System.out.println(obj);
		}
		
		public static void printCharArray(char[] c){
			for(int i=0;i<c.length;i++){
				System.out.print(c[i]);
			}
		}
		public static void printByteArray(byte[] c){
			for(int i=0;i<c.length;i++){
				Sop.print(c[i]);
			}
		}
	
		
		/*
		 * public static void printArrayList(ArrayList<?> al){
			
			for(ListIterator<?> li=al.listIterator();li.hasNext();){
				print(li.next());
			}
			}
		 * */
		
		
		
		public static <E> void printArrayList(ArrayList<E> al){//打印任意类型的ArrayList；E可以定义对象并进行操作,如下；
																						//E e=it.next(); 
			
			for(ListIterator<E> li=al.listIterator();li.hasNext();){
				print(li.next());
			}
		}

}
