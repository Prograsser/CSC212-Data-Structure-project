class Node<T> { 
    T data;
    Node<T> next;
    public Node(T data){
        this.data=data;
        this.next=null;
    }

}

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> current;
    public LinkedList(){
        head=null;
        current=null;
    }
    public boolean empty(){
        return head==null;
    }
    public boolean last(){
        return current.next==null;
    }
    public void insert(T data){
        Node<T> tmp;
        if (empty()){
           current=head=new Node<T>(data);
        }
        else{
         tmp=current.next;
         current.next=new Node<T>(data);
         current.next=tmp;
        }
    }
    public void remove(){
        if (current==head){
            head=head.next;
        }
        else{
            Node<T> tmp=head;
            while (tmp.next!=current)
                tmp=tmp.next;
                tmp.next=tmp.next;
            }
        if (current.next==null){
            current=tmp;
        }
        else{
            current=current.next;    
        }
    }


}

