
public class Album {
	private String name;
	private String condition;
	private PhotoManager manager;
	private int Nbcomp = 0; //Total Number of Comparison

	// Constructor
	public Album(String name, String condition, PhotoManager manager) {
		this.name = name;
		this.condition = condition;
		this.manager = manager;
	}

	// Return the name of the album
	public String getName() {
		return name;
	}

	// Return the condition associated with the album
	public String getCondition() {
		return condition;
	}

	// Return the manager
	public PhotoManager getManager() {
		return manager;
	}

	// Return all photos that satisfy the album condition
	public LinkedList<Photo> getPhotos() {

		LinkedList<Photo> Photos = manager.getPhotos();
		LinkedList<Photo> album = new LinkedList<>();
		if (condition == null || Photos.empty())
			return album;
		if (condition.equals(""))
			return Photos;
		String cond[] = condition.split("AND");   //Split The Conditions in the array  
		for (int i = 0; i < cond.length; i++) {    
			cond[i] = cond[i].trim();             
		}

		Photos.findFirst();
		while (!Photos.last()) {
			if (IsTagsPartOfPhoto(cond, Photos.retrieve().getTags())) //Calling method IsTagsPartOfPhoto to check the tags if it satisfies the tags we will insert it in the album
			{
				album.insert(Photos.retrieve());
			}
			Photos.findNext();
		}
		if (IsTagsPartOfPhoto(cond, Photos.retrieve().getTags())) {
			album.insert(Photos.retrieve());
		}
		return album;
	}

	// Return the number of tag comparisons used to find all photos of the album
	public int getNbComps() {
		return Nbcomp;
	}
	
	// ==Additional Methods==
	
	// Check if the tag exist in photo, and compute the total number of comparisons
	public boolean IsTagInPhoto(String tag, LinkedList<String> l) {
		if (l.empty())
			return false;
		l.findFirst();
		while (!l.last()) {
			Nbcomp++;
			if (l.retrieve().equals(tag)) {
				System.out.println(tag + " Exist, total num of comp = " + Nbcomp);
				return true;
			}
			l.findNext();
		}

		Nbcomp++;
		if (l.retrieve().equals(tag)) {
			System.out.println(tag + " Exist, total num of comp = " + Nbcomp);
			return true;
		}
		System.out.println(tag + " Does not Exist, total num of comp = " + Nbcomp);
		return false;
	}

	// Check if the tags is a part of photo tag list
	public boolean IsTagsPartOfPhoto(String[] tags, LinkedList<String> l) {  //tags is an array of tags & l is LinkedList of photos
		if (tags.length == 0)
			return true;
		if(l.empty())
			return false;

		for (int i = 0; i < tags.length; i++) {
			if (!IsTagInPhoto(tags[i], l))  // Every index in the array having Tag so here it will check every tag if the tags is not in the linked list it will return false
				return false;  // then ! will make it true so the "IsTagsInPhoto" method will give us a false because this tag is not in the linked list
		}
		return true;
	}

}
