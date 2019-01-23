package jp.marisa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.marisa.Marisa.IdKeyPair;


public class Test {
	public static void main(String[] args) {
		String telPath = "C:\\Users\\Li Yujie\\Desktop\\lifestyle\\lifestyle.tel";
//		String telPath = args[0];
		BufferedReader br = null;
		Marisa marisa = new Marisa();
		try {
			br = new BufferedReader(new FileReader(telPath));
			String line;
			KeysetNative keyset = new KeysetNative();
			List<String> list = new ArrayList<String>();
			int num = 0;
			while((line=br.readLine())!=null){
				if(num>=1000009){
					break;
				}
				line = line.trim();
				keyset.push_back(line.getBytes("UTF-8"));
				list.add(line);
				num++;
			}
			System.out.println(keyset.num_keys());
			System.out.println(keyset.size());
			TrieNative trie = new TrieNative();
			trie.build(keyset);
			marisa.setTrie(trie);
			System.out.println(marisa.lookup("010-12320"));
			System.out.println(marisa.lookup("010-10105050"));
			System.out.println(marisa.lookup("010-114"));
			Set<Long> ids = new HashSet<Long>();
			for (String string : list) {
				IdKeyPair idKeyPair = marisa.lookup(string); 
//				System.out.println(idKeyPair);
				ids.add(idKeyPair.Id);
			}
			System.out.println("end");
			System.out.println(ids.size());
			System.out.println(Collections.max(ids));
			System.out.println(Collections.min(ids));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			Thread.sleep(1000*60*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
