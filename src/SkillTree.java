public class SkillTree {

    String name;
    int length;
    Node[] nodes;

    public SkillTree(String n, int l){

        name = n;
        length = l;
        nodes = new Node[length];

    }

    public boolean addNode(Node node){

        for(int i = 0; i < length; i++){

            if(nodes[i] == null){
                nodes[i] = node;
                return true;
            }

        }

        return false;

    }

}
