package MyPackage;

public class Netflix extends Account {
	public String latestMovie = "";
	public String latestShow = "";
	public int movieTime = 0;
	public int showEpisode = 0;
	public String currentUser = ""; 


	//Constructor 
	public Netflix(String username, String password, int id) {
		super(username, password, id);
		this.currentUser = username;
	}

	//Changes the user if you are not the primary user and erases your history 
	public void changeUser(String newUser) {
		if (!newUser.equals(currentUser)) {
			currentUser = newUser;
			latestMovie = "";
			latestShow = "";
			showEpisode = 0;
			movieTime = 0;
		}
		System.out.println("This account is now being used by user: " + this.currentUser);
	}

	//"Watches" a movie. If the name is one that the user was in the middle of, then it starts from the time they were at. otherwise, it starts from the beginning.
	//Assuming the time length of a movie is 3 hours (all calculations in minutes), if the current movie is at 180 minutes (completed), returns false
	//If by adding time watched the total time becomes 3 hrs or more, it tells you you finished the movie
	//Otherwise, it starts with the new movie that you are watching
	public boolean watchMovie(String movieName, int timeWatched) {  
		if (latestMovie.equals(movieName)) {
			if (movieTime == 180) { 
				System.out.println(movieName + " has already been completed.");
				this.movieTime = 0;
				return false;
			}
			else {
				System.out.println("For this movie, you are starting at " + movieTime + " minutes");
				if (this.movieTime + timeWatched >= 180) {
					System.out.println("You completed the movie: " + movieName);
					this.movieTime = 180;
				}
				else {
					this.movieTime += timeWatched;
				}
				return true;
			}
		}
		else {
			this.latestMovie = movieName;
			this.movieTime = timeWatched;
			System.out.println("For the movie " + movieName + ", you have reached " + this.movieTime + " minutes");
			return true;
		}
	} 

	//Watches a show. If the show is the one you were just watching, then it tells you the episode you are starting at and adds the number of episodes you want to watch
	//If it is a new show, it starts from episode 1
	public void watchShow(String showName, int episodesWatched) {//time in minutes
		if (latestShow.equals(showName)) {
			System.out.println("You are starting " + showName + " at episode " + showEpisode);
			showEpisode += episodesWatched;
		}
		else {
			this.latestShow = showName;
			this.showEpisode = episodesWatched;
		}
		System.out.println("You have reached episode " + this.showEpisode + " for the show " + showName);
	} 
}