
public class Word {
	String s;
	int distance = 0;
	
	public Word(String S, int distance)	{
		this.s= S;
		this.distance = distance;
	}
	
	public void incrementDistance()	{
		this.distance++;
	}
	
}
