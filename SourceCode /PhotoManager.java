public class PhotoManager {
    LinkedList<Photo> allPhotos;

    public PhotoManager() {
        allPhotos = new LinkedList<Photo>();
    }

    public void addPhoto(Photo p) {
    	
        if (isPhotoExist(p))  
        	return;              
        
        allPhotos.insert(p);
    }
    public void deletePhoto(String path) {
    	
    	if(allPhotos.empty())
    		return;
    	
    	allPhotos.findFirst();
    	
    	while(! allPhotos.last()){
    		
    		if(allPhotos.retrieve().path.equals(path)) {
    			
    			allPhotos.remove();
    			
    			break;
    		}
    		else
    			allPhotos.findNext();
    	}
    	if(allPhotos.retrieve().path.equals(path))  
    		allPhotos.remove();
    }
    
    public LinkedList<Photo> getPhotos(){
    	return allPhotos;
    }
    
    // ==Additional Methods== 
    
    public boolean isPhotoExist(Photo p) { //To avoid repetition, we created a method called "PhotoExist" to check if a photo is in the LinkedList of photos.
    	
        if (allPhotos.empty()) return false;
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
}
