
public class InvIndexPhotoManager {
	BST<LinkedList<Photo>> TagsBST;
	LinkedList<Photo> allPhotos;

	public InvIndexPhotoManager() {
		TagsBST = new BST<LinkedList<Photo>>();
		allPhotos = new LinkedList<Photo>();
	}

	public void addPhoto(Photo p) {
		if (PhotoExist(p))
			return;
		allPhotos.insert(p);
		LinkedList<String> tags = p.getTags();
		
		if (tags.empty())
			return;
		tags.findFirst();
		while (!tags.last()) {
			String curTag = tags.retrieve();
			boolean found = TagsBST.findKey(curTag);
			if (!found) {
				LinkedList<Photo> curPhotos = new LinkedList<Photo>();
				curPhotos.insert(p);
				TagsBST.insert(curTag, curPhotos);
			} else {
				LinkedList<Photo> curPhotos = TagsBST.retrieve();
				curPhotos.insert(p);
			}
			tags.findNext();
		}
		
		String curTag = tags.retrieve();
		boolean found = TagsBST.findKey(curTag);
		if (!found) {
			LinkedList<Photo> curPhotos = new LinkedList<Photo>(); 
			curPhotos.insert(p);   
			TagsBST.insert(curTag, curPhotos);
		} else {
			LinkedList<Photo> curPhotos = TagsBST.retrieve();  //already this tag is there in the TagsBST and we will insert it to the linked list inside The Node Tag
			curPhotos.insert(p);
		}
	}

	private void removeFromList(LinkedList<Photo> list, Photo target) {
		if (list.empty())
			return;

		list.findFirst();
		while (!list.last()) {
			Photo currentPhoto = list.retrieve();
			if (currentPhoto != null && currentPhoto.path.equals(target.path)) {
				list.remove();
				return;
			}
			list.findNext();
		}

		
		Photo lastPhoto = list.retrieve();
		if (lastPhoto != null && lastPhoto.path.equals(target.path)) {
			list.remove();
		}
	}

	public void deletePhoto(String path) {
		if (allPhotos.empty())
			return;

		LinkedList<String> tags = null;

		allPhotos.findFirst();
		while (!allPhotos.last()) {
			if (allPhotos.retrieve().path.equals(path)) {
				tags = allPhotos.retrieve().tags;
				allPhotos.remove();
				break;
			}
			allPhotos.findNext(); 
		}
		if (allPhotos.retrieve().path.equals(path)) {
			tags = allPhotos.retrieve().tags;
			allPhotos.remove();
		}
		// Deleting
		if (tags == null || tags.empty())
			return;
		Photo ourPhoto = new Photo(path, tags);
		tags.findFirst();
		while (!tags.last()) {
			if (TagsBST.findKey(tags.retrieve())) {
				removeFromList(TagsBST.retrieve(), ourPhoto);
				if (TagsBST.retrieve().empty())
					TagsBST.removeKey(tags.retrieve());
			}
			tags.findNext();
		}
		if (TagsBST.findKey(tags.retrieve())) {
			removeFromList(TagsBST.retrieve(), ourPhoto);
			if (TagsBST.retrieve().empty())
				TagsBST.removeKey(tags.retrieve());
		}
	}

	public BST<LinkedList<Photo>> getPhotos() {
		return TagsBST;
	}

// ==Additional Methods==
	
	public boolean PhotoExist(Photo p) { 

		if (allPhotos.empty())
			return false;
		allPhotos.findFirst();

		while (!allPhotos.last()) {

			if (allPhotos.retrieve().path.equals(p.path))
				return true;
			allPhotos.findNext();
		}

		if (allPhotos.retrieve().path.equals(p.path)) 
			return true;

		return false;
	}

	public void displayINORDER() {
		if (TagsBST.empty())
			System.out.println("empty tree");
		else
			inorder(TagsBST.getRoot());
	}

	private void inorder(BSTNode<LinkedList<Photo>> p) {
		if (p == null)
			return;
		inorder(p.left);
		System.out.println("key: " + p.key);
		displayPhotoList(p.data);
		inorder(p.right);
	}

	public void displayPhotoList(LinkedList<Photo> l) { // Display Method
		if (l == null)
			System.out.println("Null List !");
		else if (l.empty())
			System.out.println("Empty List !");
		System.out.println(" < All Photos Are > ");
		l.findFirst();
		while (!l.last()) {
			System.out.println("\n " + l.retrieve().path);					
			l.findNext();
		}
		System.out.println("\n " + l.retrieve().path);
		
		System.out.println("-------------------");
	}
}
