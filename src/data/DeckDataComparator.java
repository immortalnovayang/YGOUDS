package data;

import java.util.Comparator;

public class DeckDataComparator implements Comparator {
	  int colIndex;

	  DeckDataComparator(int colIndex) {
	    this.colIndex = colIndex;
	  }

	  public DeckDataComparator() {
		    
	}
	  
	  public int compare(Object a, Object b) {
		  DeckData d1 = (DeckData) a;
		  DeckData d2 = (DeckData) b;
	    Object o1 = d1.getCount();
	    Object o2 = d2.getCount();

	    if (o1 instanceof String && ((String) o1).length() == 0) {
	      o1 = null;
	    }
	    if (o2 instanceof String && ((String) o2).length() == 0) {
	      o2 = null;
	    }

	    if (o1 == null && o2 == null) {
	      return 0;
	    } else if (o1 == null) {
	      return 1;
	    } else if (o2 == null) {
	      return -1;
	    } else if (o1 instanceof Comparable) {

	      return ((Comparable) o1).compareTo(o2);
	    } else {

	      return o1.toString().compareTo(o2.toString());
	    }
	  }
	}
