package com.takipi.keygen;

import com.takipi.keygen.instructions.InstructionsBuilder;
import com.takipi.keygen.key.SecretKeyGenerator;
import com.takipi.keygen.net.TakipiCom;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Thread.sleep(15000);
		
		if (!(args.length == 2 || args.length == 3))
		{
			System.out.println("Takipi Key Generator - www.takipi.com");
			System.out.println("=====================================");
			
			printUsage();
			
			return;
		}
		
		String username 	= args[0];
		String password 	= args[1];
		
		if ((username.isEmpty()) || (password.isEmpty()))
		{
			System.err.println("Problem with parameters.");
			
			printUsage();
			
			return;
		}
		
		String proxy = "";
		
		if (args.length == 3)
		{
			proxy = args[2];
		}
		
		String keyPrefix = TakipiCom.generateKeyPrefix(username, password);
		
		if (keyPrefix == null)
		{
			System.err.println("Contact keygen@takipi.com for help.");
			return;
		}
		
		String secretKey = SecretKeyGenerator.generateKey(keyPrefix); 
		
		InstructionsBuilder.buildInstructionsFile(username, secretKey, proxy);
		
		System.out.println("-- Takipi Key Generator ended.");
	}
	
	private static void printUsage()
	{
		System.out.println("Usage: USERNAME PASSWORD [SERVER-PROXY]");
		System.out.println("Example: john@example.com Pa$$woRd");
	}
}
