package CommonUtils;

import java.util.Random;

public class JavaUtils {
	
	
	public int getRandomNumber()
	{
		 Random r = new Random();
		 int ran =r.nextInt(1000);
		 return ran;
	}

}
