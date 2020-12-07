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
		kbdToFile(new File("C:/test/kbdsave.txt")); //파일객체생성
		System.out.println("프로그램 종료");
		
	}
	private static void kbdToFile(File file) {
		// 키보드에서 회원정보 한 행을 입력받아서 kbdsave.txt 파일에 한 행으로 저장
		// 모니터: out(표준출력스트림, PrintStream), System.out(out은 static변수)
		// 키보드: in(표준입력스트림, InputStream), System.in
		// BufferedReader(character stream) + System.in(binary stream)
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw = new PrintWriter(new FileWriter(file, true)); // node stream 자동생성
			String line = null;
			boolean go = true;
			while(go) {
				System.out.println("종료(ctrl+z)|번호,이름,전화,메일을 입력하세요");
				if((line=br.readLine())!=null) {					
					pw.println(line);
				}else {
					go = false;
				}
			}
			br.close();
			pw.close();
			System.out.println("키보드->파일 작업완료");
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
		/*메모리를 통해 성능을 올리는 방법*/
		try {
			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(f));
			/*byte하나씩이 아닌 한 행씩 가져오면서 node stream 위에 상위 기능으로서 쓰임*/
			FileOutputStream fout = new FileOutputStream("C:/test/images/copy.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			byte[] buf = new byte[1024];
			int read = 0;
			while((read=bin.read(buf))!=-1) {
				bout.write(buf,0,read);
			}
			bin.close();
			bout.close(); // 최상위 stream만 닫으면 연결된 stream 모두 닫힘
			System.out.println("필터스트림에 의한 복사 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	private static void textCopy(String filename) {
		try {
			FileInputStream fin = new FileInputStream(filename); 
			// 데이터 가져오는 파이프라인 생성
			FileOutputStream fout = new FileOutputStream("C:/test/copy.csv"); 
			// 데이터 출력하는 파이프라인 생성
			
			byte[] buf = new byte[1024]; // 1k 메모리 할당
			int read = 0;
			while((read=fin.read(buf,0, buf.length))!=-1) {
			/*마지막 바이트까지 모두 읽어오면 -1을 return*/
			fout.write(buf, 0, read); // 기존의 데이터크기만큼 출력
		}
		fin.close();
		fout.close();
			System.out.println("텍스트 파일 복사 완료!");
		} catch (Exception e) {
			System.err.println("파일 없음");
		}
		
	}
	// 이미지 파일을 복사한다(디스크->디스크)
	private static void imageCopy(File f) {
		try {
			FileInputStream fin = new FileInputStream(f); // 데이터 파이프 라인
			int data = fin.read(); // 한 바이트 로드(최대 256)
			byte[] fdata = fin.readAllBytes(); // 파일 전체 데이터 -> 메모리로드
			/*바이너리 스트림에서 binary 데이터를 읽어오기때문에 byte배열로 받음*/
			System.out.printf("총바이트수:%d\n", fdata.length);
			
			FileOutputStream fout = new FileOutputStream("C:/test/images/copy.jpg");
			/*메모리에 로드된 파일을 binary stream으로 node stream 파이프생성*/
			fout.write(fdata); //
			fout.close(); // 할당된 메모리 해제
			System.out.println("이미지파일 복사 완료!");
			
		} catch (Exception e) {
			System.err.println("파일 없음");
		}
	}
	
	private static void imageCopy2(File f) {
		try {
			FileInputStream fin = new FileInputStream(f); 
			// 데이터 가져오는 파이프라인 생성
			FileOutputStream fout = new FileOutputStream("C:/test/images/copy.jpg"); 
			// 데이터 출력하는 파이프라인 생성
			byte[] buf = new byte[1024]; // 1k 메모리 할당
			int read = fin.read(buf,0, buf.length); // 읽어온 byte 데이터를 return
			while((read=fin.read(buf,0, buf.length))!=-1) {
			/*마지막 바이트까지 모두 읽어오면 -1을 return*/
			fout.write(buf); //읽어온 데이터를 전체 출력
			fout.write(buf, 0, read); // 기존의 데이터크기만큼 출력
		}
		fin.close();
		fout.close();
			
		} catch (Exception e) {
			System.err.println("파일 없음");
		}
	}
}

















