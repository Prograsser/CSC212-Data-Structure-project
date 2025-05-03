
public class InvIndexAlbum {
	private String name;
	private String condition;
	private InvIndexPhotoManager manager;

	// Constructor
	public InvIndexAlbum(String name, String condition, InvIndexPhotoManager manager) {
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
	public InvIndexPhotoManager getManager() {
		return manager;
	}
	
	// Returns the number of tag comparisons performed when retrieving photos
		public int getNbComps() {
			return manager.TagsBST.getNum_comp();
		}

	// Return all photos that satisfy the album condition
	public LinkedList<Photo> getPhotos() {

		LinkedList<Photo> res = new LinkedList<>();

		if (condition == null) return res;
		if (condition.equals("")) return manager.allPhotos;

		String cond[] = condition.split("AND");
		for (int i = 0; i < cond.length; i++) {
			cond[i] = cond[i].trim();
		}
		
		LinkedList<Photo> A = getTagPhotos(cond[0]);
		for(int i = 1; i < cond.length; i++) {
			LinkedList<Photo> B = getTagPhotos(cond[i]);
			A = comparePhoto(A, B);
		}
		System.out.print("Condition Keys: ");
		for(int i = 0; i < cond.length; i++) System.out.print(cond[i]+", ");
		System.out.println("");
		return A;
	}

	// =====Helping methods========
	public boolean IsPhotoExist(LinkedList<Photo> l, Photo p) {
		if (l.empty())
			return false;
		l.findFirst();
		while (!l.last()) {
			if (l.retrieve().path.equals(p.path))
				return true;
			l.findNext();
		}
		if (l.retrieve().path.equals(p.path))
			return true;

		return false;

	}

	public LinkedList<Photo> getTagPhotos(String tag) {
		LinkedList<Photo> res = new LinkedList<Photo>();
		boolean flag = manager.getPhotos().findKeyPhoto(tag);
		if (flag)
			res = manager.getPhotos().retrieve();
		return res;
	}

	public LinkedList<Photo> comparePhoto(LinkedList<Photo> a, LinkedList<Photo> b) {
		LinkedList<Photo> res = new LinkedList<Photo>();
		if (a.empty() || b.empty())
			return res;
		a.findFirst();
		while (true) {
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
