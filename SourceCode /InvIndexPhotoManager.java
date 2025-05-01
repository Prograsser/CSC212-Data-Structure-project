
public class InvIndexPhotoManager {
BST<LinkedList<Photo>> index;
LinkedList<Photo> allPhotos;
public InvIndexPhotoManager() {
	index = new BST<LinkedList<Photo>>();
	allPhotos = new LinkedList<Photo>();
}

public void addPhoto(Photo p) {
	if(PhotoExist(p)) 
		return;
	allPhotos.insert(p);
	LinkedList<String> tags = p.getTags();
	if(tags.empty())
		return;
	tags.findFirst();
	while(! tags.last()) {
		String curTag = tags.retrieve();
		boolean found = index.findKey(curTag);
		if(! found) {
			LinkedList<Photo> curPhotos = new LinkedList<Photo>();
			curPhotos.insert(p);
			index.insert(curTag, curPhotos);
		}
		else {
			LinkedList<Photo> curPhotos = index.retrieve();
			curPhotos.insert(p);
		}
		tags.findNext();
	}
	//For Last Element
	String curTag = tags.retrieve();
	boolean found = index.findkey(curTag);
	if(! found) {
		LinkedList<Photo> curPhotos = new LinkedList<Photo>();
		curPhotos.insert(p);
		index.insert(curTag, curPhotos);
	}
	else {
		LinkedList<Photo> curPhotos = index.retrieve();
		curPhotos.insert(p);
	}
}



private void removeFromList(LinkedList<Photo> list, Photo target) {
    if (list.empty()) return;

    list.findFirst();
    while (!list.last()) {
        Photo currentPhoto = list.retrieve();
        if (currentPhoto != null && currentPhoto.path.equals(target.path)) {
            list.remove();
            return;
        }
        list.findNext();
    }

    // Also check the last item
    Photo lastPhoto = list.retrieve();
    if (lastPhoto != null && lastPhoto.path.equals(target.path)) {
        list.remove();
    }
}




public void deletePhoto(String path) {
	if(allPhotos.empty()) return;
	
	LinkedList<String> tags = null;
	
	allPhotos.findFirst();
	while(! allPhotos.last()) {
		if(allPhotos.retrieve().path.equals(path)) {
			tags = allPhotos.retrieve().tags;
			allPhotos.remove();
			break;
		}
		allPhotos.findNext();                           //FIND THE TAG'S and store it inside LL tags
	}
	if(allPhotos.retrieve().path.equals(path)) {
		tags = allPhotos.retrieve().tags;
		allPhotos.remove();
	}
	//Deleting
	if(tags == null || tags.empty()) return;
	Photo ourPhoto = new Photo(path,tags);
	tags.findFirst();
	while(! tags.last()) {
		if(index.findkey(tags.retrieve())) {
			removeFromList(index.retrieve(),ourPhoto);
			if(index.retrieve().empty())
				index.removeKey(tags.retrieve());
		}
		tags.findNext();
	}
	if(index.findkey(tags.retrieve())) {
		removeFromList(index.retrieve(),ourPhoto);
		if(index.retrieve().empty())
			index.removeKey(tags.retrieve());
	}
} 

public BST<LinkedList<Photo>> getPhotos() {
    return index;
}
//Additional Method's
public boolean PhotoExist(Photo p) {
	
    if (allPhotos.empty()) return false;
    allPhotos.findFirst();
    
    while (!allPhotos.last()) {
    	
        if (allPhotos.retrieve().path.equals(p.path))
            return true;
        allPhotos.findNext();
    }
    
    if (allPhotos.retrieve().path.equals(p.path)) //Check Last Node
        return true;
    
    return false;
}
}
