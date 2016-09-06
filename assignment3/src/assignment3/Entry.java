package assignment3;

/**
 * 
 * Entry object is created by Main for each lexigraphic entry and
 * contains a String word and String[] POS for parts of speech.
 * 
 * @author walter
 *
 */
public class Entry implements Comparable<Object> {
	
	private String word;
	private String[] POS;

	public Entry(String word, String[] POS) {
		this.word = word;
		this.POS = POS;
	}
	
	public int compareTo(Object item) {
		return this.word.compareTo(((Entry)item).getWord());
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String[] getPOS() {
		return POS;
	}

	public void setPOS(String[] POS) {
		this.POS = POS;
	}

}
