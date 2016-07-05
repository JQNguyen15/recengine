
package assets.classes;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class song extends Object {
	String artist;
	String songName;
	public song(String artist, String songName){
		this.artist = artist;
		this.songName = songName;
	}

	public boolean equals(song anotherSong){
		if (anotherSong == null)
			return false;
		if (songName.equals(anotherSong.songName) && artist.equals(anotherSong.artist))
			return true;
		else
			return false;
	}
	
	public int hashCode(){	
		// two randomly chosen prime numbers 17,31
		return new HashCodeBuilder(17, 31).append(artist).append(songName).toHashCode();
	}
	
	public String getName(){
		return songName;
	}
	
	public String getArtist(){
		return artist;
	}
}
