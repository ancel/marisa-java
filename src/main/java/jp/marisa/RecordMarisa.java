package jp.marisa;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class RecordMarisa extends Marisa{
	private String valueSeparator;
	public RecordMarisa(String valueSeparator) {
		super();
		this.valueSeparator = valueSeparator;
	}

	public void push_back(String key, String value) throws MarisaException, UnsupportedEncodingException{
		this.keyset.push_back(pack(key, value).getBytes("UTF-8"));
	}
	public String pack(String key, String value){
		return key+this.valueSeparator+value;
	}
	public String unPack(String target){
		int index = target.indexOf(this.valueSeparator);
		return target.substring(index+1);
	}
	
	public boolean contain(String key){
		List<IdKeyPair> idKeyPairs = predictiveSearch(key+this.valueSeparator);
		if(null==idKeyPairs||idKeyPairs.size()<1){
			return false;
		}
		return true;
	}
	
	public boolean contain(String key, String value){
		IdKeyPair idKeyPair = lookup(pack(key, value));
		if(null==idKeyPair){
			return false;
		}
		return true;
	}
	
	public List<String> getValues(String key){
		List<IdKeyPair> idKeyPairs = predictiveSearch(key+this.valueSeparator);
		if(null==idKeyPairs||idKeyPairs.size()<1){
			return null;
		}
		List<String> list = new ArrayList<String>();
		for (IdKeyPair idKeyPair : idKeyPairs) {
			list.add(unPack(idKeyPair.Key));
		}
		return list;
	}
}
