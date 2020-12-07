package com.tjoeun.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class StreamTest {

	public static void main(String[] args) {

//		File f = new File("C:/test/images/sample.jpg");
//		imageCopy(f);
//		imageCopy2(f);
//		textCopy("C:/test/user.csv");
//		copyImgByFilter(f);
//		ReadTextByFilter("C:/test/user.csv");
		kbdToFile(new File("C:/test/kbdsave.txt")); //���ϰ�ü����
		System.out.println("���α׷� ����");
		
	}
	private static void kbdToFile(File file) {
		// Ű���忡�� ȸ������ �� ���� �Է¹޾Ƽ� kbdsave.txt ���Ͽ� �� ������ ����
		// �����: out(ǥ����½�Ʈ��, PrintStream), System.out(out�� static����)
		// Ű����: in(ǥ���Է½�Ʈ��, InputStream), System.in
		// BufferedReader(character stream) + System.in(binary stream)
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw = new PrintWriter(new FileWriter(file, true)); // node stream �ڵ�����
			String line = null;
			boolean go = true;
			while(go) {
				System.out.println("����(ctrl+z)|��ȣ,�̸�,��ȭ,������ �Է��ϼ���");
				if((line=br.readLine())!=null) {					
					pw.println(line);
				}else {
					go = false;
				}
			}
			br.close();
			pw.close();
			System.out.println("Ű����->���� �۾��Ϸ�");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void ReadTextByFilter(String filename) {
		try {
			BufferedReader br = new BufferedReader(
					new FileReader(filename, Charset.forName("utf-8")));
			String line = null;
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
			br.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	private static void copyImgByFilter(File f) {
		/*�޸𸮸� ���� ������ �ø��� ���*/
		try {
			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(f));
			/*byte�ϳ����� �ƴ� �� �྿ �������鼭 node stream ���� ���� ������μ� ����*/
			FileOutputStream fout = new FileOutputStream("C:/test/images/copy.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			byte[] buf = new byte[1024];
			int read = 0;
			while((read=bin.read(buf))!=-1) {
				bout.write(buf,0,read);
			}
			bin.close();
			bout.close(); // �ֻ��� stream�� ������ ����� stream ��� ����
			System.out.println("���ͽ�Ʈ���� ���� ���� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	private static void textCopy(String filename) {
		try {
			FileInputStream fin = new FileInputStream(filename); 
			// ������ �������� ���������� ����
			FileOutputStream fout = new FileOutputStream("C:/test/copy.csv"); 
			// ������ ����ϴ� ���������� ����
			
			byte[] buf = new byte[1024]; // 1k �޸� �Ҵ�
			int read = 0;
			while((read=fin.read(buf,0, buf.length))!=-1) {
			/*������ ����Ʈ���� ��� �о���� -1�� return*/
			fout.write(buf, 0, read); // ������ ������ũ�⸸ŭ ���
		}
		fin.close();
		fout.close();
			System.out.println("�ؽ�Ʈ ���� ���� �Ϸ�!");
		} catch (Exception e) {
			System.err.println("���� ����");
		}
		
	}
	// �̹��� ������ �����Ѵ�(��ũ->��ũ)
	private static void imageCopy(File f) {
		try {
			FileInputStream fin = new FileInputStream(f); // ������ ������ ����
			int data = fin.read(); // �� ����Ʈ �ε�(�ִ� 256)
			byte[] fdata = fin.readAllBytes(); // ���� ��ü ������ -> �޸𸮷ε�
			/*���̳ʸ� ��Ʈ������ binary �����͸� �о���⶧���� byte�迭�� ����*/
			System.out.printf("�ѹ���Ʈ��:%d\n", fdata.length);
			
			FileOutputStream fout = new FileOutputStream("C:/test/images/copy.jpg");
			/*�޸𸮿� �ε�� ������ binary stream���� node stream ����������*/
			fout.write(fdata); //
			fout.close(); // �Ҵ�� �޸� ����
			System.out.println("�̹������� ���� �Ϸ�!");
			
		} catch (Exception e) {
			System.err.println("���� ����");
		}
	}
	
	private static void imageCopy2(File f) {
		try {
			FileInputStream fin = new FileInputStream(f); 
			// ������ �������� ���������� ����
			FileOutputStream fout = new FileOutputStream("C:/test/images/copy.jpg"); 
			// ������ ����ϴ� ���������� ����
			byte[] buf = new byte[1024]; // 1k �޸� �Ҵ�
			int read = fin.read(buf,0, buf.length); // �о�� byte �����͸� return
			while((read=fin.read(buf,0, buf.length))!=-1) {
			/*������ ����Ʈ���� ��� �о���� -1�� return*/
			fout.write(buf); //�о�� �����͸� ��ü ���
			fout.write(buf, 0, read); // ������ ������ũ�⸸ŭ ���
		}
		fin.close();
		fout.close();
			
		} catch (Exception e) {
			System.err.println("���� ����");
		}
	}
}

















