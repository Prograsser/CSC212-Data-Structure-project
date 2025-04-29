public class PhotoManager {
    LinkedList<Photo> allPhotos;

    public PhotoManager() {
        allPhotos = new LinkedList<Photo>();
    }

    public void addPhoto(Photo p) {
    	
        if (PhotoExist(p))  // to prevent repeating, we create method "PhotoExist" to check if it's in the LinkedList of photos
        	return;              
        
        allPhotos.insert(p);
    }
    public void deletePhoto(String path) {
    	
    	if(allPhotos.empty())
    		return;
    	
    	allPhotos.findFirst();
    	
    	while(! allPhotos.last()){
    		
    		if(allPhotos.retrive().path.equals(path)) {
    			
    			allPhotos.remove();
    			
    			break;
    		}
    		else
    			allPhotos.findNext();
    	}
    	if(allPhotos.retrive().path.equals(path))  //Check Last Node
    		allPhotos.remove();
    }
    
    public LinkedList<Photo> getPhotos(){
    	return allPhotos;
    }
    
    // ====== Helping Method =========
    public boolean PhotoExist(Photo p) {
    	
        if (allPhotos.empty()) return false;
        allPhotos.findFirst();
        
        while (!allPhotos.last()) {
        	
            if (allPhotos.retrive().path.equals(p.path))
                return true;
            allPhotos.findNext();
        }
        
        if (allPhotos.retrive().path.equals(p.path)) //Check Last Node
            return true;
        
        return false;
    }
}
