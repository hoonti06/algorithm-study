package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_17413 {

	public static void main(String[] args) throws IOException {
		//�Է��� �ޱ� ���� BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//�Է¹��� ���ڿ��� char�� �迭�� ����
		char[] chars = br.readLine().toCharArray();
		int size=chars.length;
		
		//�ܾ ������ �����ϱ� ���� �Ͻ������� �ܾ ������ �迭
		char[] word = new char[size];
		int wordLength = 0;
		
		//����� �� ���� �ϱ� ���� StringBuilder
		StringBuilder sb = new StringBuilder();
		
		//tag ������ �Ǵ��ϴ� flag
		boolean isTag = false;
		
		//���� �ݺ���. �� ���ھ� �Է� �޴� �������� ����
		for (int i = 0; i < size; i++) {
			
			if(chars[i]=='<') {
				isTag = true;
				
				//�� ���� ������ �����ؾ� �� �ܾ ������ ����� ����
				if(wordLength!=0) {
					for (int j = 0; j < wordLength ; j++) {
						chars[i-j-1] = word[j];
					}
					wordLength = 0;
				}
			}
			else if(chars[i]=='>') {
				isTag = false;
				continue;
			}
			
			//�±� ���� �ƴ� �� == �ܾ� or ����
			if(!isTag) {
				if(!(chars[i]==' ')) {
					word[wordLength++] = chars[i];
				}
				else {
					//������ ������ ��� �ݵ�� ������ �����ؾ� �� �ܾ �ֱ� ������ ����� �ܾ ������ ����
					for (int j = 0; j < wordLength ; j++) {
						chars[i-j-1] = word[j];
					}
					wordLength = 0;

				}
			}
			
		}
		
		//���ڿ��� �ܾ�� ������ ���. ���������� ������ ������ �ܾ �ִ��� Ȯ���ϰ� ������ ������ ����
		if(wordLength!=0) {
			for (int j = 0; j < wordLength ; j++) {
				chars[size-j-1] = word[j];
			}
			wordLength = 0;
		}
		
		//���
		for (int i = 0; i < size; i++) {
			sb.append(chars[i]);
		}
		System.out.println(sb.toString());
	}

}
