
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import assets.classes.Playlist;
import assets.classes.nodeSong;
import assets.classes.song;

//this class contains the actual graph to be used for finding songs
public class DataSetofSongs {
	ArrayList<nodeSong> graph = new ArrayList<nodeSong>();
	
	//this function would be called everytime a user went from song A to song B and 
	//subsequently liked song B. 
	private void updateGraph(song songA, song songB){
		//look in graph to see if we have the vertice
		//iterate through the graph checking each node
		Boolean found = false;
		if (graph.contains(songA)){
				int index = graph.indexOf(songA);
				nodeSong aNode = graph.get(index);
				graph.remove(songA);
				//check if this song is in the list of neighbors, if so update
				if (aNode.likedSongsContains(songB)){
					aNode.updateRank(songB);
				}
				//else this song is not a neighbor, add it 
				else{
					aNode.addToLiked(songB);
				}
				//readd this song to graph since we removed it earlier
				graph.add(aNode);
				found = true;
			}
		if (!found){
			nodeSong temp = new nodeSong(songA.getName(), songA.getArtist() ,songB);
			graph.add(temp);
		}
	}
	
	private song[] recListOfSongs(Playlist userPlaylist){
		ArrayList<song> songs = userPlaylist.getSongs();				
		HashMap <song,Integer> recList = new HashMap<song,Integer>();	//used for sorting
		song[] returnList = new song[30];								//actual list returned
		for (song aSong : songs){
			if (graph.contains(aSong)){
				int index = graph.indexOf(aSong);
				HashMap<song,Integer> temp = graph.get(index).getNeighbors();
				for (Entry<song,Integer> e : temp.entrySet()){
					song someSong = e.getKey();
					//if already in recommended list
					if (recList.containsKey(someSong)){
						recList.put(someSong, recList.get(someSong)+e.getValue());
					}
					//otherwise add to reclist
					else{
						recList.put(someSong, e.getValue());
					}
				}
			}
		}
		int i = 0;
		
		recList = sortByValues(recList);

		for (Entry<song,Integer> e : recList.entrySet()){
			if (i==29)
				break;
			returnList[i] = e.getKey();
			i++;
		}
		return returnList;
	}	
	
	 private static HashMap sortByValues(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });
	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }

}
