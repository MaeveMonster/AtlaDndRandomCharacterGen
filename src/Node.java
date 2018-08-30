public class Node {

    //name of the node
    String name;
    //number of upgrade spots available
    int length;
    //each array element indicates whether or not the skill has been upgraded to that level
    boolean[] used;
    //the first parent of the node, null if the node has no parents
    Node parent1;
    //the second parent of the node, null if the node only has 1 parent, or if the node has no parents
    Node parent2;
    //true if this node has 2 parents, false otherwise
    boolean has2Parents;

    public Node(String n, int l, Node p1, Node p2, boolean h){

        name = n;
        length = l;
        parent1 = p1;
        parent2 = p2;
        used = new boolean[length];

        for(int i = 0; i < length; i++){
            used[i] = false;
        }
        has2Parents = h;

    }

    //finds the first element of used which is false and makes it true
    //if all elements are true, does nothing
    public void addSkillPoint(){

        for(int i = 0; i < length; i++){
            if(used[i] == false){
                used[i] = true;
                break;
            }
        }

    }

    //returns true if all elements of used are true, false otherwise
    public boolean isFull(){
        int count = 0;
        for(int i = 0; i < length; i++){
            if(used[i] == true){
                count++;
            }
        }

        if (count == length){
            return true;
        }
        else{
            return false;
        }
    }

    //returns true if one or both of the parents of this node has at least one element of used which is true
    //also returns true if the node has no parents
    //false otherwise
    public boolean parentIsUsed(){
        if(parent1 == null || parent1.used[0] == true || (has2Parents && (parent1.used[0] == true || parent2.used[0] == true))){
            return true;
        }
        else{
            return false;
        }
    }
}
