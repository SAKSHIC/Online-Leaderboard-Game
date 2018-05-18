package leaderboard;

/* package whatever; // don't place package name! */

import java.util.*;
import java.util.Map.Entry;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class LeaderBoardRunner
{
	
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		List<String> operations = Arrays.asList("UPSERT_USER","UPSERT_USER","UPSERT_USER","UPSERT_SCORE","UPSERT_SCORE","UPSERT_USER","UPSERT_SCORE","GET_TOP","GET_USERS_WITH_SCORE","GET_TOP","SEARCH","SEARCH","GET_RANGE","SEARCH_NAME");
		List<List<String>> val = new ArrayList<List<String>>();
		val.add(Arrays.asList("Nikhil","India","nikhil@abc.com"));
		val.add(Arrays.asList("Nikhil","India","nikhil@abc.com"));
		val.add(Arrays.asList("Rahul","India","rahul@abc.com"));
		val.add(Arrays.asList("rahul@abc.com","1"));
		val.add(Arrays.asList("nikhil@abc.com","5"));
		val.add(Arrays.asList("Karan","Argentina","karan@abc.com"));
		val.add(Arrays.asList("karan@abc.com","1"));
		val.add(Arrays.asList("3"));
		val.add(Arrays.asList("1"));
		val.add(Arrays.asList("2","India"));
		val.add(Arrays.asList("Nikhil","null","India"));
		val.add(Arrays.asList("null","India","null"));
		val.add(Arrays.asList("1","2"));
		val.add(Arrays.asList("Nik"));
		
		LeaderBoard lb = new LeaderBoard();
		for(int i=0;i<operations.size();i++) {
			String operation = operations.get(i);
			switch(operation){
				case "UPSERT_USER":
					System.out.println(operation);
					List<String> v = val.get(i);
					lb.upsert_user((val.get(i)).get(0),(val.get(i)).get(1),(val.get(i)).get(2));
					break;
				case "UPSERT_SCORE":
					lb.upsert_score((val.get(i)).get(0),(val.get(i)).get(1));
					System.out.println(operation);
					break;
				case "GET_TOP":
					System.out.println(operation);
					if((val.get(i)).size() == 1)
						lb.get_top((val.get(i)).get(0),null);
					else
						lb.get_top((val.get(i)).get(0),(val.get(i)).get(1));
					break;
				case "GET_USERS_WITH_SCORE":
					System.out.println(operation);
					lb.get_users_with_score((val.get(i)).get(0));
					break;
				case "SEARCH":
					System.out.println(operation);
					lb.search((val.get(i)).get(0),(val.get(i)).get(1),(val.get(i)).get(2));
					break;
				case "GET_RANGE":
					System.out.println(operation);
					lb.get_range((val.get(i)).get(0),(val.get(i)).get(1));
					break;
				case "SEARCH_NAME":
					System.out.println(operation);
					lb.search_name((val.get(i)).get(0));
					break;
			}
		}
	}

}