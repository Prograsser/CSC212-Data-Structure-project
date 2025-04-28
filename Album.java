
public class Album {
String name;
String condition;
PhotoManager manager;
int nbcomp = 0; //Total Number of Comparison
public Album(String name, String condition, PhotoManager manager) {
	this.name = name;
	this.condition = condition;
	this.manager = manager;
}
public String getName() {
	return name;
}
public String getCondition() {
	return condition;
}
public PhotoManager getManager() {
	return manager;
}

public boolean TagExist_inPhoto(LinkedList<String> l,String tag) {
	if(l.empty())
		return false;
	
	l.findFirst();	
	while(! l.last()) {
		nbcomp++;
		if(l.retrieve().equals(tag)) 			
			 return true;		
		l.findNext();
	}
	
	nbcomp++;
	if(l.retrieve().equals(tag))
		return true;
      	
	 return false;
}

public boolean subset(String [] a, LinkedList<String> l) {  //a is array stores tags & l is linkedlist of photos
	if(a.length == 0)
		return true; 
	if(l.empty())
		return false;     
	
	 for(int i =0; i<a.length; i++) {      
		if(! TagExist_inPhoto(l,a[i]))  // Every index in the array having Tag so here it will check every tag if the tags is not in the linked list it will return false
			return false;               // then ! it will make it true and the subset method will give us a false because this tag is not in the linked list
	  }
	 return true;
   }

public LinkedList<Photo> getPhotos(){ // this method will return the photos that meet with the condition of the album
	LinkedList<Photo> allPhotos = manager.getPhotos();
	LinkedList<Photo> album = new LinkedList<Photo>();
	if(condition == null)                 
		return album;
	if(condition.equals(""))             
		return allPhotos;
	                                      //                                                                              a: a[0]    a[1]   
	String a[] = condition.split("AND");  //.split it will cut from AND then replace AND with space ,ex:grass AND green --> grass    green
	
	for(int i =0; i < a.length; i++){     //                                                     a: a[0] | a[1]
		a[i] = a[i].trim();               //.trim it will remove spaces and it will become -->  grass | green
	}
	
	
	if(allPhotos.empty())    
		return album;
	
	allPhotos.findFirst();
	while(! allPhotos.last()) {
		
		if(subset(a,allPhotos.retrieve().getTags()))  //Calling method subset to check the tags if it satisfies the tags we will insert it in the album
			album.insert(allPhotos.retrieve());
		
		allPhotos.findNext();
	}
	if(subset(a,allPhotos.retrieve().getTags()))
		album.insert(allPhotos.retrieve());
	
	return album;
}

public int getNbComps() {
	return nbcomp;
}
}
