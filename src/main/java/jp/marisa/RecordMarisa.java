/*--------------------------------------------------------------------------
 * Copyright (c) 2021, Ancel Wang
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 
 * - Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer. 
 * - Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution. 
 * - Neither the name of the <ORGANIZATION> nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 *THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *--------------------------------------------------------------------------*/
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
