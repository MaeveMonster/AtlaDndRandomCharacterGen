public class SkillTree {

    //name of the skill tree
    String name;
    //number of elements in nodes
    int length;
    Node[] nodes;

    public SkillTree(String n, int l){

        name = n;
        length = l;
        nodes = new Node[length];

    }

    //adds a node to the skill tree if there is space available
    //returns true if adding a node was successful, false otherwise
    public boolean addNode(Node node){

        for(int i = 0; i < length; i++){

            if(nodes[i] == null){
                nodes[i] = node;
                return true;
            }

        }

        return false;

    }

    public void print(){

        System.out.println(name + ":");
        for(int i = 0; i < length; i++){
            if(nodes[i].used[0] == true){
                System.out.print(nodes[i].name);
                System.out.print(" ");
                String string = "";
                for(int j = 0; j < nodes[i].length; j++){
                    if(nodes[i].used[j] == true){
                        string += "I";
                    }
                }
                System.out.println(string);
            }
        }

    }

}
