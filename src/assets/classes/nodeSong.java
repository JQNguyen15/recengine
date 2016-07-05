package assets.classes;

import java.util.HashMap;
import java.util.Map.Entry;
//helps to simulate a weighted graph
//likedSongs are neighbors, and the integer value are the values for edges
public class nodeSong extends song{
	HashMap <song,Integer> likedSongs;

	//node with no neighbors
	public nodeSong(String artist, String songName){
		super(artist,songName);
		likedSongs = new HashMap<song,Integer>();
	}
	
	// this constructor is for when we need to add a new node to the graph
	// and add its first vertice
	public nodeSong(String songName, String artist, song songB){
		super(artist,songName);
		likedSongs = new HashMap<song,Integer>();
		this.addToLiked(songB);
	}
	
	public HashMap<song,Integer> getNeighbors(){
		return likedSongs;
	}
	
	//increments rank of song in the hashmap by 1
	public void updateRank(song aSong){
		likedSongs.put(aSong , likedSongs.get(aSong)+1);
	}
	
	//adds a neighbor to the node
	public void addToLiked(song aSong){
		likedSongs.put(aSong, 1);
	}
	
	//checks if hashmap contains a song
	public boolean likedSongsContains(song songB){
		if (likedSongs.containsKey(songB))
			return true;
		else
			return false;
	}

	public song getSong(){
		return this;
	}
	
}
