public class InvIndexAlbum {
	private String name;
	private String condition;
	private InvIndexPhotoManager manager;

	// Constructor: Initializes the album with a name, condition, and photo manager
	public InvIndexAlbum(String name, String condition, InvIndexPhotoManager manager) {
		this.name = name;
		this.condition = condition;
		this.manager = manager;
	}

	// Getter: Returns the name of the album
	public String getName() {
		return name;
	}

	// Getter: Returns the condition associated with the album
	public String getCondition() {
		return condition;
	}

	// Getter: Returns the manager responsible for handling photos
	public InvIndexPhotoManager getManager() {
		return manager;
	}

	// Returns all photos that satisfy the album's condition
	public LinkedList<Photo> getPhotos() {
		LinkedList<Photo> res = new LinkedList<>();

		// If no condition is set, return an empty list
		if (condition == null) return res;
		// If the condition is empty, return all photos
		if (condition.equals("")) return manager.allPhotos;

		// Split condition into multiple tags connected by "AND"
		String cond[] = condition.split("AND");
		for (int i = 0; i < cond.length; i++) {
			cond[i] = cond[i].trim();
		}
		
		// Get photos matching the first tag
		LinkedList<Photo> A = getTagPhotos(cond[0]);
		// Intersect with photos matching subsequent tags
		for(int i = 1; i < cond.length; i++) {
			System.out.println("a[i] = " + cond[0]);
			LinkedList<Photo> B = getTagPhotos(cond[i]);
			A = comparePhoto(A, B);
		}
		return A;
	}

	// Returns the number of tag comparisons performed when retrieving photos
	public int getNbComps() {
		return manager.index.num_comp;
	}

	// ===== Helping methods =====

	// Checks if a given photo exists in the provided list
	public boolean IsPhotoExist(LinkedList<Photo> l, Photo p) {
		if (l.empty())
			return false;
		l.findFirst();
		while (!l.last()) {
			if (l.retrieve().path.equals(p.path))
				return true;
			l.findNext();
		}
		// Check the last element
		if (l.retrieve().path.equals(p.path))
			return true;

		return false;
	}

	// Retrieves all photos that match a specific tag
	public LinkedList<Photo> getTagPhotos(String tag) {
		LinkedList<Photo> res = new LinkedList<Photo>();
		boolean flag = manager.getPhotos().findKeyPhoto(tag);
		if (flag)
			res = manager.getPhotos().retrieve();
		return res;
	}

	// Returns the intersection of two photo lists (photos common to both lists)
	public LinkedList<Photo> comparePhoto(LinkedList<Photo> a, LinkedList<Photo> b) {
		LinkedList<Photo> res = new LinkedList<Photo>();
		if (a.empty() || b.empty())
			return res;
		a.findFirst();
		while (true) {
			// Check if photo already added to result
			if (!IsPhotoExist(res, a.retrieve())) {
				b.findFirst();
				while (true) {
					if (b.retrieve().path.equals(a.retrieve().path)) {
						res.insert(a.retrieve());
						break;
					}
					if (!b.last())
						b.findNext();
					else
						break;
				}
			}
			if (!a.last())
				a.findNext();
			else
				break;
		}
		return res;
	}
}
