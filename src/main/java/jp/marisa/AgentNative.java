/*--------------------------------------------------------------------------
 * Copyright (c) 2012, NAKANO Hideo
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

/**
 * this class provides interface to C++ `marisa::Agent` class.
 * 
 * @see http://marisa-trie.googlecode.com/svn/branches/marisa-0.2.0-beta7/docs/readme.en.html
 */
public class AgentNative {
    long handle;
    long stringBufferHandle;
    
    public AgentNative() {
    	handle = -1;
    	stringBufferHandle = -1;
    }
    
	public native QueryNative query() throws MarisaException;
	public native KeyNative key() throws MarisaException;
	
	public void set_query(byte[] str) throws MarisaException {
		set_query(str, 0, str.length);
	}
	public native void set_query(byte[] str, int start, int length) throws MarisaException;
	public native void set_query(int key_id) throws MarisaException;
	public native void set_query(long key_id) throws MarisaException;
	
	public native void dispose();
}
