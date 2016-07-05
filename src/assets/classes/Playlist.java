package assets.classes;

import java.util.ArrayList;

public class Playlist {
	String user;
	ArrayList<song> songs = new ArrayList<song>();
	
	private void addToPlaylist(song currentSong){
		this.songs.add(currentSong);
	}
	
	public Playlist(){
		
	}
	
	public Playlist(String user){
		this.user = user;
	}
	
	public ArrayList<song> getSongs(){
		return songs;
	}
	
}
