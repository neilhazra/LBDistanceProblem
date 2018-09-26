import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;

public class LBDistance {
	public static Set<String> allwords = new HashSet<String>();
	public static Map<String, Set<String>> lbmap = new TreeMap<String, Set<String>>();
	public static String alphabet = "abcdefghijklmnopqrstuvwxyz";	
	static int asdf= 0;
	public static int computeDistance(String word1, String word2) {
		LinkedList<Word> navigate = new LinkedList<Word>();
		Set<String> parsedWords = new HashSet<String>();
		navigate.add(new Word(word1, 0));
		while(navigate.size() != 0) {
			Word current = navigate.getFirst();
			parsedWords.add(current.s);
			if(current.s.equals(word2)) return current.distance;
			for(String s: lbmap.get(current.s))	{
				asdf++;
				navigate.add(new Word(s, current.distance+1));
			}
			Predicate<Word> personPredicate = p-> parsedWords.contains(p.s);
			navigate.removeIf(personPredicate);
		}
		return -1;
	}
	public static Boolean doesExist(String s)	{
		return allwords.contains(s);
	}
	public static void initialize()	{
		Scanner sc = null;
		try {
			sc = new Scanner(new File("dictionary_words.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(sc.hasNext()) {
			allwords.add(sc.nextLine());
		}
		for(String word: allwords) {
			HashSet<String> neighbors = new HashSet<String>();
			for(int i = 0; i < word.length(); i ++) {
				for(int j = 0; j < alphabet.length(); j++)	{
					char[] newWord = word.toCharArray();
					newWord[i] = alphabet.charAt(j);
					String newWordString = new String(newWord);
					if(doesExist(newWordString))	{
						neighbors.add(newWordString);
					}
				}
			}
			lbmap.put(word, neighbors);
		}
	}
	public static void main(String args[]) {
		initialize();		
		System.out.println(lbmap.get("dog"));
	}
}
