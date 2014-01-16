package encryption.des.testcase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.siemens.ct.its.Des;

public class DesTest {
	
	private Des des;
	private String key = "just for test";
	private String cipher = "This is ciphertext";
	
	@Before
	public void setUp() throws Exception{
		des = new Des(key);
	}
	
	@Test
	public void DesTest() throws Exception{
		String encode = des.encrypt(cipher);
		String decode = des.decrypt(encode);
		Assert.assertTrue(cipher.equals(decode) && !cipher.equals(encode));
	}
}
