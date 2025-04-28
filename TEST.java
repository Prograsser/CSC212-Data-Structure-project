
public class TEST {

	public static void main(String[] args) {
		test1(); //Test Class "Mostafa " Completed 100% for Photo & Photo Manger
		
		test2(); // University Test Class Not Completed
		
		
	}
	
	    public static void test2() {        //University Test Class
	    	PhotoManager manager = new PhotoManager();
	    	Photo photo1 = new Photo("hedgehog.jpg",toTagsLinkedList("animal, hedgehog, apple,grass, green"));
	    	manager.addPhoto(photo1);
	    	Photo photo2 = new Photo("bear.jpg",toTagsLinkedList("animal, bear, cab, grass,wind"));
	    	manager.addPhoto(photo2);
	    	Photo photo3 = new Photo("orange-butterfly.jpg",toTagsLinkedList("insect,butterfly, flower, color"));
	    	manager.addPhoto(photo3);
	    	//Album album1 = new Album("Album1", "bear", manager);
	    	//Album album2 = new Album("Album2", "animal AND grass", manager);
	    	System.out.println("Get photo1 path and tags:");
	    	System.out.println("photo1 path: " + photo1.getPath());
	    	//You can get the list of tags of photo1 by calling photo1.getTags().
	    	//You can write a method that prints the list of tags of photo1.
	    	System.out.println("Get album2 name, condition, and photos:");
	    	//System.out.println("album2 name: " + album2.getName());
	    	//System.out.println("album2 condition: " + album2.getCondition());
	    	//You can get the list of photos in album2 by calling album2.getPhotos().
	    	//You can write a method that prints the list of photos in album2.
	    	System.out.println("Delete the photo ’bear.jpg’:");
	    	manager.deletePhoto("bear.jpg");
	    	}

	    
	
		public static void test1() {        //Test Class "Mostafa "
		BST<Integer> b = new BST<Integer>();
        b.insert("d", 4);       //      "d",4                             Binary Search Tree Structure
        b.insert("b", 2);    //       /       \         Alphabetical order => A is smallest key , Z is maximum key
        b.insert("a", 1);   //    "b",2      "e",5  
        b.insert("c", 4);  //     /  \       /    \ 
        b.insert("e", 5);  //  "a",1"c",3   "f",6 "g",7
        b.insert("g", 7);
        b.insert("f", 6);
        b.displayINORDER();
        System.out.println("\n-------------------");
        
        //Define Tags and Photos 
        LinkedList<String> tags1 = new LinkedList<String>();
        tags1.insert("animal");
        tags1.insert("hedgehog");
        tags1.insert("apple");
        tags1.insert("grass");
        tags1.insert("green");
        Photo p1 = new Photo("hedgehog.jpg", tags1);
        
        LinkedList<String> tags2 = new LinkedList<String>();
        tags2.insert("animal");
        tags2.insert("bear");
        tags2.insert("cab");
        tags2.insert("grass");
        tags2.insert("wind");
        Photo p2 = new Photo("bear.jpg", tags2);
        
        LinkedList<String> tags3 = new LinkedList<String>();
        tags3.insert("insect");
        tags3.insert("butterfly");
        tags3.insert("flower");
        tags3.insert("color");        
        Photo p3 = new Photo("butterfly1.jpg", tags3);
        
        LinkedList<String> tags4 = new LinkedList<String>();
        tags4.insert("insect");
        tags4.insert("butterfly");
        tags4.insert("balck");
        tags4.insert("flower");       
        Photo p4 = new Photo("butterfly2.jpg", tags4);
        
        LinkedList<String> tags5 = new LinkedList<String>();
        tags5.insert("animal");
        tags5.insert("fox");
        tags5.insert("tree");
        tags5.insert("forest");
        tags5.insert("grass");
        Photo p5 = new Photo("fox.jpg", tags5);
        
        LinkedList<String> tags6 = new LinkedList<String>();
        tags6.insert("animal");
        tags6.insert("bear");
        tags6.insert("panda");
        tags6.insert("grass");
        Photo p6 = new Photo("panda.jpg", tags6);
        
        LinkedList<String> tags7 = new LinkedList<String>();
        tags7.insert("animal");
        tags7.insert("wolf");
        tags7.insert("mountain");
        tags7.insert("sky");
        tags7.insert("snow");
        tags7.insert("cloud");
        Photo p7 = new Photo("wolf.jpg", tags7);
        
        LinkedList<String> tags8 = new LinkedList<String>();
        tags8.insert("animal");
        tags8.insert("raccoon");
        tags8.insert("log");
        tags8.insert("snow");
        Photo p8 = new Photo("raccoon.jpg", tags8);
        
        //Creating Photo Manager object & add the photos
        PhotoManager manager = new PhotoManager();
        manager.addPhoto(p1);
        manager.addPhoto(p2);
        manager.addPhoto(p3);
        manager.addPhoto(p4);
        manager.addPhoto(p5);
        manager.addPhoto(p6);
        manager.addPhoto(p7);
        manager.addPhoto(p8);
        
        
        LinkedList<Photo> all_photos = manager.getPhotos(); // Creating a LinkedList of type photo to extract the photos form "PhotoManager" class
        
        displayPhotoList(all_photos);
        
        
        System.out.println("Deleting hedgehog.jpg");
        
        manager.deletePhoto("hedgehog.jpg");
        all_photos = manager.getPhotos();   //Update all_photos LinkedList after we remove hedgehog.jpg
        
        displayPhotoList(all_photos);
	}
	
   public static void displayPhotoList(LinkedList<Photo> l) {  //Display Method "Mostafa"
	   if(l == null)
		   System.out.println("Null List !");
	   else if(l.empty())
		   System.out.println("Empty List !");
	   System.out.println(" < All Photos Are > ");
	   l.findFirst();
	   while(! l.last()) {
		   System.out.println("\n "+l.retrieve().path);
		   //l.retrieve().displayPhoto();  ---> This will display photo details; tags & path
		   l.findNext();
	   }
	   System.out.println("\n "+l.retrieve().path);
	   //l.retrieve().displayPhoto();
	   System.out.println("-------------------");
   }
   
   private static LinkedList<String> toTagsLinkedList(String tags) { 
	   LinkedList<String> result = new LinkedList<String>();
	   String[] tagsArray = tags.split("\\s*,\\s*");
	   for (int i = 0; i < tagsArray.length; i++) {
	   result.insert(tagsArray[i]);
	   }
	   return result;
	   }

}
