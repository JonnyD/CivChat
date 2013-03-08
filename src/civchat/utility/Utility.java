package civchat.utility;

public class Utility 
{
	public static String stripTrailingComma(String text)
	{
		int pos = text.lastIndexOf(",");
        if (pos >= 0)
        {
            return text.substring(0, pos);
        }
        return text;
	}
}
