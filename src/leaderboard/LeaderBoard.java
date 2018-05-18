/**
 * 
 */
package leaderboard;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.SortedMap;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

/**
 * @author sakshi
 *
 */
class Player{
	String email;
	int score;
	String country;
	String name;
	Player(String name, String email, String country){
		this.email = email;
		this.country = country;
		this.name = name;
		this.score = -1;
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.email);
	}
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(!(o instanceof Player))
			return false;
		Player p = (Player)o;
		return (this.email == p.email);
	}
	@Override
	public String toString() {
		return String.format(score + " || " + name +" || "+ country);
	}
}
public class LeaderBoard {
	TreeMap<Integer, LinkedHashSet<String>> leader_board;
	HashMap<String, Player> user_data;
	LeaderBoard(){
		user_data = new HashMap<>();
		leader_board = new TreeMap<>();
	}
	void upsert_user(String name, String country, String email){
		if(user_data.containsKey(email)) {
			System.out.println("User already added to Learderboard");
			return;
		}
		user_data.put(email,new Player(name, email, country));
	}
	void upsert_score(String email, String sc){
		int score = Integer.parseInt(sc);
		if(user_data.containsKey(email)) {
			Player data = user_data.get(email);
			if(data.score != -1) {
				leader_board.get(data.score).remove(email);
				leader_board.get(score).add(email);
			}
			else {
				if(leader_board.containsKey(score)) {
					leader_board.get(score).add(email);
				}
				else
					leader_board.put(score,new LinkedHashSet<String>(){{add(email);}});
			}
			data.score = score;
		}
		else {
		System.out.println("User not added to Learderboard");
		return;
		}
	}
	void get_top(String k, String country) {
		int K = Integer.parseInt(k);
		int i =1;
		System.out.println("Rank || Score || Name || Country");
		for(Entry<Integer, LinkedHashSet<String>> entry : leader_board.entrySet()) {
			  LinkedHashSet<String> value = entry.getValue();
			  Iterator<String> it = value.iterator();
			  while(it.hasNext() && K>0) {
				  Player p = user_data.get(it.next());
				  if((country != null && country.equals(p.country)) || country == null) {
					  System.out.println(i + " || " + p);
					  K--;
					  i++;
				  }
				  
			  }
		}
	}
	void get_users_with_score(String sc) {
		int score = Integer.parseInt(sc);
		leader_board.get(score).forEach(k->{System.out.println(user_data.get(k).name);});
	}
	void search(String name, String country, String email){
		for(Entry<String, Player> entry : user_data.entrySet()) {
			Player p = entry.getValue();
			if(p.name.equals(name) || p.country.equals(country) || p.email.equals(email))
				System.out.println(p);
		}
	}
	void get_range(String n, String k) {
		SortedMap<Integer, LinkedHashSet<String>> sm = leader_board.subMap(Integer.parseInt(n),Integer.parseInt(k));
		System.out.println("Score || Name || Country");
		for(Entry<Integer, LinkedHashSet<String>> entry : sm.entrySet()) {
			  LinkedHashSet<String> value = entry.getValue();
			  Iterator<String> it = value.iterator();
			  while(it.hasNext()) {
				  Player p = user_data.get(it.next());
			      System.out.println(p);
			  }
		}
	}
	void search_name(String name){
		for(Entry<String, Player> entry : user_data.entrySet()) {
			Player p = entry.getValue();
			if(p.name.matches(name+"(.*)"))
				System.out.println(p);
		}
	}
}
