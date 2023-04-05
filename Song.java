import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



class Song<T1, T2, T3, T4> {
    private T1 songData1;
    private T2 songData2;
    private T3 songData3;
    private T4 songData4;
    
    public Song(T1 sd1, T2 sd2, T3 sd3, T4 sd4) {
        songData1 = sd1;
        songData2 = sd2;
        songData3 = sd3;
        songData4 = sd4;
    }
    
    public T1 getSongData1() {
        return songData1;
    }
    
    public void setSongData1(T1 data1) {
        songData1 = data1;
    }
    
    public T2 getSongData2() {
        return songData2;
    }
    
    public void setSongData2(T2 data2) {
        songData2 = data2;
    }
    
    public T3 getSongData3() {
        return songData3;
    }
    
    public void setSongData3(T3 data3) {
        songData3 = data3;
    }
    
    public T4 getSongData4() {
        return songData4;
    }
    
    public void setSongData4(T4 data4) {
        songData4 = data4;
    }
    
    public String toString() {
        return "Song data 1: " + songData1 + ", " +
               "Song data 2: " + songData2 + ", " +
               "Song data 3: " + songData3 + ", " +
               "Song data 4: " + songData4;
    }
}



public class MyClass {
    
  public static void displayAllSongs(LinkedList<Song<?, ?, ?, ?>> songs) {
    for (var song : songs) {
    	System.out.println(song.toString());
    }
  }
  	
	public static void testingSongs(){
    // Creating each of the songs 
    // Each song has different data types showing that Song's parameters are generic.
    Song<String, Integer, Double, Boolean> song1 = new Song<>("Paranoid Android", 10, 111.234, true);
    Song<String, Float, Integer, Boolean> song2 = new Song<>("My Iron Lung", 3.14f, 7, false);
    Song<String, Double, Boolean, Integer> song3 = new Song<>("No Surprises", 99.99, true, 6);

    // Put all of the songs into a linked list
    // ? because we don't know what the data type will be
    LinkedList<Song<?, ?, ?, ?>> list = new LinkedList<>();
    list.add(song1);
    list.add(song2);
    list.add(song3);

    // Test the display all songs function
    // Also tests the getSongData functions for each Song class
    System.out.println("OLD DATA FOR EACH SONG:");
    displayAllSongs(list);
    
    // Changing the data for each song
    song1.setSongData1("Karma Police");
    song1.setSongData2(20);
    song1.setSongData3(222.222);
    song1.setSongData4(false);

    song2.setSongData1("Street Spirit");
    song2.setSongData2(2.71f);
    song2.setSongData3(3);
    song2.setSongData4(true);

    song3.setSongData1("Exit Music (For a Film)");
    song3.setSongData2(555.555);
    song3.setSongData3(false);
    song3.setSongData4(10);

    // Printing out the new data:
    System.out.println("\nNEW DATA FOR EACH SONG:");
    displayAllSongs(list);    
	}
    
  
  public static LinkedList<Song<?, ?, ?, ?>> readSongsToLinkedList(String fileName){
    
    LinkedList<Song<?, ?, ?, ?>> songList = new LinkedList<>();
  
    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;
      bufferedReader.readLine();
      while ((line = bufferedReader.readLine()) != null) {
        if (!line.isEmpty()) {
            String[] data = line.split("\t");
          
            Integer songID = Integer.parseInt(data[0]);
            String title = data[1];
            String album = data[2];
            String dateAdded = data[3];
          
          	Song<?, ?, ?, ?> song1 = new Song<>(songID, title, album, dateAdded);	
            songList.add(song1);
        }
      }

      bufferedReader.close();
    }
    catch (IOException e) {
    	e.printStackTrace();
    }
    
    return songList;
	}
  
  
   public static Song[] readSongsToArray(String fileName){
    // create array
    Song[] songList = new Song[100000];
    
    try {
      // gets the file into the code
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      int numSongsReadIn = 0;

      String line;
      
      // skips the first line that says 'Song ID	Title	Album	Date Added'
      bufferedReader.readLine();
      
      // while we haven't reached the end of the file
      while ((line = bufferedReader.readLine()) != null) {
        if (!line.isEmpty()) {
          	// split up the string
            String[] data = line.split("\t");
          
            Integer songID = Integer.parseInt(data[0]);
            String title = data[1];
            String album = data[2];
            String dateAdded = data[3];
          
          	Song<?, ?, ?, ?> song = new Song<>(songID, title, album, dateAdded);	
          

            songList[numSongsReadIn] = song;
            numSongsReadIn += 1;
        }
      }

      bufferedReader.close();
    }

    catch (IOException e) {
    	e.printStackTrace();
    }
    
    return songList;
	}
  
//	public static void searchSongsByYear(Integer expectedYear, LinkedList<Song<?, ?, ?, T>> songList){
//    System.out.println("Songs with the year: " + expectedYear);
//		for (var song : songList) {
//      T dateString = song.getSongData4();
//      String[] parts = dateString.split("/");
//      String yearString = parts[2];
//      int year = Integer.parseInt(yearString);
//      
//   		if(year == expectedYear){
//				System.out.println(song.toString());
//			}
//   }
// }
  
  public static boolean searchSongsArray(String title, Song<?, ?, ?, ?>[] songList){
    for (int i = 0; i < songList.length; i++) {
    	if (songList[i] == null)
    		continue;
        if(songList[i].getSongData2().equals(title)){
           return true;
        }
    }

    return false;
  }
 
  public static void main(String[] args) {
    System.out.println("\n\n-----------------------------------------------------------------------------");
    System.out.println("TESTING SONG CLASS METHODS");
    System.out.println("-----------------------------------------------------------------------------");
    testingSongs();
    
    System.out.println("\n\n-----------------------------------------------------------------------------");
    System.out.println("SONGS IN THE LINKED LIST: ");
    System.out.println("-----------------------------------------------------------------------------");
    LinkedList<Song<?, ?, ?, ?>>  llSongList = readSongsToLinkedList("songList.txt");
    displayAllSongs(llSongList);
    
    System.out.println("\n\n-----------------------------------------------------------------------------");
    System.out.println("SONGS IN THE ARRAY:");
    System.out.println("-----------------------------------------------------------------------------");
    Song<?, ?, ?, ?>[] arraySongList = readSongsToArray("songList.txt");
    for(var song : arraySongList){
    	if (song == null)
    		continue;
    	System.out.println(song.toString());
    }
    
    
    //System.out.println("\n\n-----------------------------------------------------------------------------");
    //System.out.println(" SEARCHING FOR SONGS IN A CERTAIN YEAR IN LINKED LIST:");
    //System.out.println("-----------------------------------------------------------------------------");
  	//searchSongsByYear(2023, llSongList);
  
    
    System.out.println("\n\n-----------------------------------------------------------------------------");
    System.out.println(" SEARCHING FOR SONGS IN ARRAY LIST:");
    System.out.println("-----------------------------------------------------------------------------");
  	System.out.println("Found song 'new gold'? " + searchSongsArray("New Gold",arraySongList));
		System.out.println("Found song 'asdasdasdasd'? " + searchSongsArray("asdasdasdasd",arraySongList));    
  }
}
  
