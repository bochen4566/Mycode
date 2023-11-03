//Name: Bochen Wang
//bochenw

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Lab15Main {
	public ArrayList<Movie> movieList = new ArrayList<Movie>();
	private static Scanner keyboard = new Scanner(System.in);
    public static final int MOVIE_COUNT = 5; // Return this many movies in the searches
	
    /*
      Partially complete
      Add your code where needed
    */
	public static void main(String[] args) {
		Lab15Main lab15main = new Lab15Main();
		lab15main.readMovies("movies.tsv");
		int choice;
		do {
			choice = getMenuChoice();
			switch (choice) {
                // case 1 is done for you
				case 1: lab15main.sortBy("ID");
					lab15main.displayMovies(lab15main.getList());
					break;
				case 2:
                    lab15main.sortBy("Name");
                    lab15main.displayMovies(lab15main.getList());
					break;
				case 3:
					lab15main.sortBy("Year");
					lab15main.displayMovies(lab15main.getList());
					break;
				case 4:
					lab15main.sortBy("ReverseYear");
					lab15main.displayMovies(lab15main.getList());
					break;
				case 5:
					System.out.print("Enter the movie name: ");
                    // Use nextLine() everywhere!
					String name = keyboard.nextLine();
                    // Do something with name
					break;
				case 6:
					System.out.print("Enter the year: ");
					int year = Integer.parseInt(keyboard.nextLine());
					ArrayList<Movie> moviesByYear = lab15main.searchByYear(year);
					lab15main.displayMovies(moviesByYear);
					break;
				case 7:
					System.out.print("Enter the genre: ");
					String genre = keyboard.nextLine();
					ArrayList<Movie> moviesByGenre = lab15main.searchByGenre(genre);
					lab15main.displayMovies(moviesByGenre);
					break;
				case 8: 
					System.out.println("Bye");
					break;
			}
		} while (choice != 8);
	}
	
    /*
       Don't change this method
    */
	public static int getMenuChoice() {
		System.out.println("1. Display by ID\n2. Display by name\n3. Display by year\n" + 
				"4. Display by year in reverse\n5. Search by name\n6. Search by year\n" +
				"7. Search by Genre\n8. Quit");
		System.out.print("Enter your choice: ");
		int choice = Integer.parseInt(keyboard.nextLine());
		if (choice < 1 || choice > 8) choice = 8;
		return choice;
	}
		
	public void readMovies(String filename) {
		Scanner fileInput = null;
		
		try {
			fileInput = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


        while(fileInput.hasNext()){
			String x = fileInput.nextLine();
			String[] str = x.split("\t");
			Movie m = toMovie(str);
			movieList.add(m);
		}
        /* Your code here
           While there are lines in the file
            read a line and split it on \t
            create a Movie using toMovie
            add it to movieList
        */
		
	}
	
	public Movie toMovie(String[] str) {
        /*  Returns one Movie from the data in str
            Each line of str should contain one field
            Change last one to ArrayList<String> for genres
        */
        		int movieID = Integer.parseInt(str[0].strip());
		String movieName = str[1].strip().replace("\"", "");
		int year = Integer.parseInt(str[2].strip());
		String country = str[3].strip().replace("\"", "");
		ArrayList<String> genres = new ArrayList<String>();
        // Start at position #4
        // Strip and add to the ArrayList of genres
		for (int i=4; i<str.length; i++) {
			genres.add(str[i].strip());
		}
		return new Movie(movieID, movieName, year, country, genres);
	}
	
    // Don't change this, even if you don't like my table spacing
	private void displayMovies(ArrayList<Movie> list) {
		if (list.size() == 0) {
			System.out.println("Nothing to display");
		} else {
			System.out.format("%7s %50s %5s %30s %6s\n", "ID", "Name", "Year", "Country", "Genres");
			for (Movie m: list) {
				System.out.format("%7s %50s %5d %30s ", m.getMovieID(), m.getMovieName(), 
					m.getYear(), m.getCountry());
				for (int i=0; i<m.getGenres().size(); i++) {
					System.out.print(m.getGenres().get(i) + " ");
				}
				System.out.println();
			}
		}
		System.out.println();
	}
	
    // Sort according to the field indicated by s
	public void sortBy(String s) {
		switch (s) {
			case "ID":
				// Use Movie's built-in compareTo
				Collections.sort(movieList);
				break;
			case "Name":
				Collections.sort(movieList, Comparator.comparing(Movie::getMovieName));
				break;
			case "Year":
				Collections.sort(movieList, new SortByYear());
				break;
			case "ReverseYear":
				Comparator c = Collections.reverseOrder(new SortByYear());
				Collections.sort(movieList, c);
				break;
		}
	}
	
    // Search for MOVIE_COUNT movies by name
	public ArrayList<Movie> searchByName(String name) {
        // Sort by id before searches for consistent results
        sortBy("ID");

        // List of results
		ArrayList<Movie> list = new ArrayList<Movie>();

        // Count the # of matches
		int count = 0;
		for (Movie m: movieList) {
            // Does m match on the name key?
			if (m.getMovieName().equals(name)) {
                // Yes, so add it to the result list
				list.add(m);
				count++;

                // Quit if we hit the maximum # of movies to return
				if (count == Lab15Main.MOVIE_COUNT) break;
			}
		}
		return list;
	}
	
    // Search for MOVIE_COUNT movies by year
	public ArrayList<Movie> searchByYear(int year) {
		ArrayList<Movie> list = new ArrayList<>();
		int count = 0;
		for (Movie m : movieList) {
			if (m.getYear() == year) {
				list.add(m);
				count++;
				if (count == MOVIE_COUNT) break;
			}
		}
		return list;
	}


	// Search for MOVIE_COUNT movies by genre
	public ArrayList<Movie> searchByGenre(String genre) {
		ArrayList<Movie> list = new ArrayList<>();
		int count = 0;
		for (Movie m : movieList) {
			if (m.getGenres().contains(genre)) {
				list.add(m);
				count++;
				if (count == MOVIE_COUNT) break;
			}
		}
		return list;
	}


	// Breaks encapsulation, boo!
	public ArrayList<Movie> getList() { return movieList; }

}
