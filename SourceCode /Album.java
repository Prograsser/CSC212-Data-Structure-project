
public class Album {
	private String name;
	private String condition;
	private PhotoManager manager;
	private int totalNbcomp = 0; //Total Number of Comparison

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
		LinkedList<Photo> res = new LinkedList<>();
		if (condition == null || Photos.empty())
			return res;
		if (condition.equals(""))
			return Photos;
		                                         //                                                                              a: a[0]    a[1]   
		String cond[] = condition.split("AND"); //.split it will cut from AND then replace AND with space ,ex:grass AND green --> grass    green
		for (int i = 0; i < cond.length; i++) {    //                                                 a: a[0] | a[1]
			cond[i] = cond[i].trim();             //.trim it will remove spaces and it will become -->  grass | green
		}

		Photos.findFirst();
		while (!Photos.last()) {
			if (IsTagsPartOfPhoto(cond, Photos.retrieve().getTags())) //Calling method subset to check the tags if it satisfies the tags we will insert it in the album
			{
				res.insert(Photos.retrieve());
			}
			Photos.findNext();
		}
		if (IsTagsPartOfPhoto(cond, Photos.retrieve().getTags())) {
			res.insert(Photos.retrieve());
		}
		return res;
	}

	// Return the number of tag comparisons used to find all photos of the album
	public int getNbComps() {
		return totalNbcomp;
	}
	
	// =====Helping methods========
	// Check if the tag exist in photo, and compute the total number of comparisons
	public boolean IsTagInPhoto(String tag, LinkedList<String> l) {
		if (l.empty())
			return false;
		l.findFirst();
		while (!l.last()) {
			totalNbcomp++;
			if (l.retrieve().equals(tag)) {
				System.out.println(tag + " Exist, total num of comp = " + totalNbcomp);
				return true;
			}
			l.findNext();
		}

		totalNbcomp++;
		if (l.retrieve().equals(tag)) {
			System.out.println(tag + " Exist, total num of comp = " + totalNbcomp);
			return true;
		}
		System.out.println(tag + " Does not Exist, total num of comp = " + totalNbcomp);
		return false;
	}

	// Check if the tags is a part of photo tag list
	public boolean IsTagsPartOfPhoto(String[] tags, LinkedList<String> l) {  //tags is an array of tags & l is linkedlist of photos
		if (tags.length == 0)
			return true;
		if(l.empty())
			return false;

		for (int i = 0; i < tags.length; i++) {
			if (!IsTagInPhoto(tags[i], l))  // Every index in the array having Tag so here it will check every tag if the tags is not in the linked list it will return false
				return false;  // then ! will make it true so the "IsTagsPartOfPhoto" method will give us a false because this tag is not in the linked list
		}
		return true;
	}

}
