
public class ProjUtil {
	static Symmetric alg(String s) throws Exception{
		if(s.equals("AES")){
			 return new AES();
		}else if(s.equals("DES")){
			 return new DES();	
		}else if(s.equals("Blowfish")){
			 return new Blowfish();	
		}else if(s.equals("RC4")){
			 return new RC4();	
		}else if(s.equals("DESede")){
			 return new DESede();	
		}else if(s.equals("RC5")){
			 return new RC5();	
		}
		return null;
	}
}
