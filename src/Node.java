public class Node {

    String name;
    int length;
    boolean[] used;
    Node parent1;
    Node parent2;
    //boolean isEndNode;
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

    public void addSkillPoint(){

        for(int i = 0; i < length; i++){
            if(used[i] == false){
                used[i] = true;
                break;
            }
        }

    }

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

    public boolean parentIsUsed(){
        if(parent1 == null || parent1.used[0] == true || (has2Parents && (parent1.used[0] == true || parent2.used[0] == true))){
            return true;
        }
        else{
            return false;
        }
    }
}
