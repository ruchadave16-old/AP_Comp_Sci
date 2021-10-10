package MyPackage;
import java.util.*;

public class Spotify extends Account {
	public HashMap<String, ArrayList<String>> playlists = new HashMap<String, ArrayList<String>>();
	
	//Constructor 
	public Spotify(String username, String password, int id) {
		super(username, password, id);
	}

	//Creates key in HashMap with playlist name
	public void createPlaylist(String name) {
		playlists.put(name, new ArrayList<String>());
		System.out.println(playlists);
	}

	//Adds song to the playlist you say
	public void addSong(String name, String song) {
		ArrayList<String> currSongs = playlists.get(name);
		currSongs.add(song);
		playlists.put(name, currSongs);
		System.out.println("New playlist " + playlists.get(name));
	}

	//Takes song out from the playlist you say
	public void removeSong(String name, String song) {
		ArrayList<String> currSongs = playlists.get(name);
		currSongs.remove(song);
		if (currSongs.size() == 0) {
			System.out.println("This playlist is now empty");
		}
		playlists.put(name, currSongs);
		System.out.println(playlists);
	}

}