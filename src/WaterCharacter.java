public class WaterCharacter extends Character{

    public WaterCharacter(){

    }

    public WaterCharacter(int g, int l){

        name = "name";
        gender = g;
        level = l;
        if(level == 1){
            maxBP = 8;
        }
        else if(level > 1 && level < 5){
            maxBP = 9;
        }
        else if(level > 4 && level < 8){
            maxBP = 10;
        }
        else if(level > 7 && level < 11){
            maxBP = 11;
        }
        else if(level > 10 && level < 14){
            maxBP = 12;
        }
        else if(level > 13 && level < 17){
            maxBP = 13;
        }
        else if(level > 16 && level < 20){
            maxBP = 14;
        }
        else{
            maxBP = 15;
        }

        if(level < 5){
            profBonus = 2;
        }
        else if(level > 4 && level < 9){
            profBonus = 3;
        }
        else if(level > 8 && level < 13){
            profBonus = 4;
        }
        else if(level > 12 && level < 17){
            profBonus = 5;
        }
        else{
            profBonus = 6;
        }

        strength = 0;
        dex = 0;
        constitution = 0;
        intelligence = 0;
        wisdom = 0;
        charisma = 0;
        maxHitPoints = 0;
        hitDice = 0;
        speed = 30;

        String[] femaleNames = {"Ahnah", "Akna", "Alasie", "Amka", "Anjij", "Aqakuktuq", "Arnaaluk",
                "Arnakuagsak", "Arnaq", "Asiaq", "Elisapiee", "Hitty", "Isapoinhkyaki", "Jissika",
                "Kanna", "Kireama", "Kirima", "Korra", "Meriwa", "Nuliajuk", "Nuvua", "Tanaraq",
                "Tapeesa", "Ticasuk", "Uki", "Yue"};

        String[] unisexNames = {"Adlartok", "Alornerk", "Amaruq", "Aput", "Atiqtalaaq", "Cupun",
                "Ikiaq", "Kallik", "Kanaaq", "Nanouk", "Nanurjuk", "Nukilik", "Nuniq", "Qimmiq",
                "Siqiniq", "Taqtu", "Tehoronianhen", "Tootega", "Tukkuttok", "Tulimak", "Umiaktorvik",
                "Uukkarnit", "Yuka", "Yura"};

        String[] maleNames = {"Amaqjuaq", "Bato", "Desna", "Inuksuk", "Kumaglak", "Kuruk", "Nootaikok",
                "Pana", "Panuk", "Pilip", "Silla", "Sokka", "Tarkik", "Tekkeitsertok", "Tiguaak", "Toklo",
                "Tonraq", "Torngarsuk", "Tulimaq", "Tulok", "Tulugaak", "Ujurak", "Unalaq", "Yoskolo", "Yotimo",
                "Yutu"};

        if(gender == 0){
            name = maleNames[(int)(Math.random()*((25 - 0) +1))+0];
        }
        if(gender == 1){
            name = femaleNames[(int)(Math.random()*((25 - 0) +1))+0];
        }
        if(gender == 2){
            name = unisexNames[(int)(Math.random()*((23 - 0) +1))+0];
        }

        name += " of the Water Tribe";

        style = (int)(Math.random()*((1-0)+1))+0;

        if(level == 1){
            skillPoints = 2;
        }
        else if(level == 2){
            skillPoints = 3;
        }
        else if(level == 3 || level == 4){
            skillPoints = 5;
        }
        else if(level == 5){
            skillPoints = 6;
        }
        else if(level == 6){
            skillPoints = 8;
        }
        else if(level == 7 || level == 8){
            skillPoints = 10;
        }
        else if(level == 9){
            skillPoints = 12;
        }
        else if(level == 10){
            skillPoints = 14;
        }
        else if(level == 11 || level == 12){
            skillPoints = 15;
        }
        else if(level == 13){
            skillPoints = 17;
        }
        else if(level == 14){
            skillPoints = 18;
        }
        else if(level == 15 || level == 16){
            skillPoints = 20;
        }
        else if(level == 17){
            skillPoints = 21;
        }
        else if(level == 18 || level == 19){
            skillPoints = 23;
        }
        else{
            skillPoints = 24;
        }

        tree1 = new SkillTree("Traditional", 8);
        tree2 = new SkillTree("Advanced", 8);

        tree1.addNode(new Node("Water Whip", 3, null, null, false));
        tree1.addNode(new Node("Water Stream", 3, null, null, false));
        tree1.addNode(new Node("Watery Slashes", 3, tree1.nodes[0], null, false));
        tree1.addNode(new Node("Water Jet", 1, tree1.nodes[1], null, false));
        tree1.addNode(new Node("Water Shield", 1, tree1.nodes[2], tree1.nodes[3], true));
        tree1.addNode(new Node("Water Cloak", 3, tree1.nodes[4], null, false));
        tree1.addNode(new Node("Octopus Form", 1, tree1.nodes[4], null, false));
        tree1.addNode(new Node("Master Waterbender", 1, tree1.nodes[5], tree1.nodes[6], true));

        tree2.addNode(new Node("Temperature Control", 1, null, null, false));
        tree2.addNode(new Node("Healing Water", 3, null, null, false));
        tree2.addNode(new Node("Ice Spikes", 3, tree2.nodes[0], null, false));
        tree2.addNode(new Node("Plantbending", 1, tree2.nodes[1], null, false));
        tree2.addNode(new Node("Ice Manipulation", 3, tree2.nodes[2], null, false));
        tree2.addNode(new Node("Harvest Water", 1, tree2.nodes[3], null, false));
        tree2.addNode(new Node("Ice Hold", 3, tree2.nodes[4], null, false));
        tree2.addNode(new Node("Spiritbending", 1, tree2.nodes[5], null, false));

    }

    public void genRandomStats(){

        int spUsed = 0;
        while(spUsed < skillPoints){
            if(spUsed == 0){
                if(style == 0){
                    tree1.nodes[(int)(Math.random()*((1-0)+1))+0].addSkillPoint();
                    spUsed++;
                }
                else {
                    tree2.nodes[(int)(Math.random()*((1-0)+1))+0].addSkillPoint();
                    spUsed++;
                }
            }else{
                int tree = (int)(Math.random()*((1-0)+1))+0;
                if(tree == 0){
                    int randNode = (int)(Math.random()*((7-0)+1))+0;
                    int spUsedBeforeLoop = spUsed;
                    do{
                        if(tree1.nodes[randNode].parentIsUsed() && !tree1.nodes[randNode].isFull()){
                            tree1.nodes[randNode].addSkillPoint();
                            spUsed++;
                        }
                        else{
                            randNode = (int)(Math.random()*((7-0)+1))+0;
                        }
                    }while(spUsedBeforeLoop == spUsed);
                }
                else{
                    int randNode = (int)(Math.random()*((7-0)+1))+0;
                    int spUsedBeforeLoop = spUsed;
                    do{
                        if(tree2.nodes[randNode].parentIsUsed() && !tree2.nodes[randNode].isFull()){
                            tree2.nodes[randNode].addSkillPoint();
                            spUsed++;
                        }
                        else{
                            randNode = (int)(Math.random()*((7-0)+1))+0;
                        }
                    }while(spUsedBeforeLoop == spUsed);
                }
            }

        }

        int[] stats = new int[6];
        int[] modifiers = new int[6];
        int[] rolls = new int[4];

        for(int i = 0; i < 6; i++){

            rolls[0] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[1] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[2] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[3] = (int)(Math.random() * ((6-1)+1)) + 1;

            int smallest = rolls[0];
            int smallestIndex = 0;
            for( int j = 0; j < 4; j++){

                if(rolls[j] < smallest){
                    smallest = rolls[j];
                    smallestIndex = j;
                }

            }

            int sum = 0;
            for(int j = 0; j < 4; j++){
                if(j != smallestIndex){
                    sum += rolls[j];
                }
            }

            stats[i] = sum;

        }

        if(level > 3 && level < 8){
            int one = (int)(Math.random() * ((5-0)+1)) + 0;
            int two = (int)(Math.random() * ((5-0)+1)) + 0;

            stats[one]++;
            stats[two]++;
        }
        else if(level > 7 && level < 12){
            int one = (int)(Math.random() * ((5-0)+1)) + 0;
            int two = (int)(Math.random() * ((5-0)+1)) + 0;
            int three = (int)(Math.random() * ((5-0)+1)) + 0;
            int four = (int)(Math.random() * ((5-0)+1)) + 0;

            stats[one]++;
            stats[two]++;
            stats[three]++;
            stats[four]++;
        }
        else if(level > 11 && level < 16){
            int one = (int)(Math.random() * ((5-0)+1)) + 0;
            int two = (int)(Math.random() * ((5-0)+1)) + 0;
            int three = (int)(Math.random() * ((5-0)+1)) + 0;
            int four = (int)(Math.random() * ((5-0)+1)) + 0;
            int five = (int)(Math.random() * ((5-0)+1)) + 0;
            int six = (int)(Math.random() * ((5-0)+1)) + 0;

            stats[one]++;
            stats[two]++;
            stats[three]++;
            stats[four]++;
            stats[five]++;
            stats[six]++;
        }
        else if(level > 15 && level < 19){
            int one = (int)(Math.random() * ((5-0)+1)) + 0;
            int two = (int)(Math.random() * ((5-0)+1)) + 0;
            int three = (int)(Math.random() * ((5-0)+1)) + 0;
            int four = (int)(Math.random() * ((5-0)+1)) + 0;
            int five = (int)(Math.random() * ((5-0)+1)) + 0;
            int six = (int)(Math.random() * ((5-0)+1)) + 0;
            int seven = (int)(Math.random() * ((5-0)+1)) + 0;
            int eight = (int)(Math.random() * ((5-0)+1)) + 0;

            stats[one]++;
            stats[two]++;
            stats[three]++;
            stats[four]++;
            stats[five]++;
            stats[six]++;
            stats[seven]++;
            stats[eight]++;
        }
        else if(level > 18){
            int one = (int)(Math.random() * ((5-0)+1)) + 0;
            int two = (int)(Math.random() * ((5-0)+1)) + 0;
            int three = (int)(Math.random() * ((5-0)+1)) + 0;
            int four = (int)(Math.random() * ((5-0)+1)) + 0;
            int five = (int)(Math.random() * ((5-0)+1)) + 0;
            int six = (int)(Math.random() * ((5-0)+1)) + 0;
            int seven = (int)(Math.random() * ((5-0)+1)) + 0;
            int eight = (int)(Math.random() * ((5-0)+1)) + 0;
            int nine = (int)(Math.random() * ((5-0)+1)) + 0;
            int ten = (int)(Math.random() * ((5-0)+1)) + 0;

            stats[one]++;
            stats[two]++;
            stats[three]++;
            stats[four]++;
            stats[five]++;
            stats[six]++;
            stats[seven]++;
            stats[eight]++;
            stats[nine]++;
            stats[ten]++;
        }

        stats[3]++;
        stats[5] += 2;

        strength = stats[0];
        dex = stats[1];
        constitution = stats[2];
        intelligence = stats[3];
        wisdom = stats[4];
        charisma = stats[5];

        for(int i = 0; i < 6; i++){
            if(stats[i] == 1){
                modifiers[i] = -5;
            }
            else if(stats[i] > 1 && stats[i] < 4){
                modifiers[i] = -4;
            }
            else if(stats[i] > 3 && stats[i] < 6){
                modifiers[i] = -3;
            }
            else if(stats[i] > 5 && stats[i] < 8){
                modifiers[i] = -2;
            }
            else if(stats[i] > 7 && stats[i] < 10){
                modifiers[i] = -1;
            }
            else if(stats[i] > 9 && stats[i] < 12){
                modifiers[i] = 0;
            }
            else if(stats[i] > 11 && stats[i] < 14){
                modifiers[i] = 1;
            }
            else if(stats[i] > 13 && stats[i] < 16){
                modifiers[i] = 2;
            }
            else if(stats[i] > 15 && stats[i] < 18){
                modifiers[i] = 3;
            }
            else if(stats[i] > 17 && stats[i] < 20){
                modifiers[i] = 4;
            }
            else{
                modifiers[i] = 5;
            }
        }

        smod = modifiers[0];
        dmod = modifiers[1];
        comod = modifiers[2];
        imod = modifiers[3];
        wmod = modifiers[4];
        chmod = modifiers[5];

        if(level == 1){
            maxHitPoints = 8;
        }
        else{
            maxHitPoints = 8;
            for(int i = 2; i < 21; i++){
                int roll = (int)(Math.random()*((8-1)+1))+1;
                maxHitPoints += roll;
                maxHitPoints += comod;
                if(level == i){
                    break;
                }
            }
        }

        System.out.println("Name: " + name);
        System.out.println("Level: " + level + "\n");

        //testing purposes
        System.out.println("Ability Scores and Modifiers");
        System.out.println("Strength: " + strength);
        System.out.println("Strength modifier: " + smod);
        System.out.println("Dexterity: " + dex);
        System.out.println("Dexterity modifier: " + dmod);
        System.out.println("Constitution: " + constitution);
        System.out.println("Constitution modifier: " + comod);
        System.out.println("Intelligence: " + intelligence);
        System.out.println("Intelligence modifier: " + imod);
        System.out.println("Wisdom: " + wisdom);
        System.out.println("Wisdom modifier: " + wmod);
        System.out.println("Charisma: " + charisma);
        System.out.println("Charisma modifier: " + chmod + "\n");

        if(style == 0){
            System.out.println("Bending Bonus: " + chmod + "\n");
        }else{
            System.out.println("Bending Bonus: " + wmod + "\n");
        }

        System.out.println("Proficiency Bonus: " + profBonus + "\n");

        System.out.println("Saving Throws");
        System.out.println("Strength saving throw: " + smod);
        System.out.println("Dexterity saving throw: " + dmod);
        System.out.println("Constitution saving throw: " + (comod + profBonus));
        System.out.println("Intelligence saving throw: " + imod);
        System.out.println("Wisdom saving throw: " + wmod);
        System.out.println("Charisma saving throw: " + (chmod + profBonus) + "\n");

        int prof1 = (int)(Math.random()*((5-0)+1))+0;
        int prof2;
        do{

            prof2 = (int)(Math.random()*((5-0)+1))+0;

        }while(prof2 == prof1);

        System.out.println("Skills");
        System.out.println("Acrobatics: " + dmod);
        System.out.println("Animal Handling: " + wmod);
        System.out.println("Athletics: " + smod);
        if(prof1 == 0 || prof2 == 0){

            System.out.println("Deception: " + (chmod + profBonus));
        }else{
            System.out.println("Deception: " + chmod);
        }
        System.out.println("History: " + imod);
        if(prof1 == 1 || prof2 == 1){
            System.out.println("Insight: " + (wmod + profBonus));
        }else{
            System.out.println("Insight: " + wmod);
        }
        System.out.println("Intimidation: " + chmod);
        if(prof1 == 2 || prof2 == 2){
            System.out.println("Investigation: " + (imod + profBonus));
        }else{
            System.out.println("Investigation: " + imod);
        }
        if(prof1 == 3 || prof2 == 3){
            System.out.println("Medicine: " + (wmod + profBonus));
        }else{
            System.out.println("Medicine: " + wmod);
        }
        System.out.println("Nature: " + imod);
        System.out.println("Perception: " + wmod);
        if(prof1 == 4 || prof2 == 4){
            System.out.println("Performance: " + (chmod + profBonus));
        }else{
            System.out.println("Performance: " + chmod);
        }
        if(prof1 == 5 || prof2 == 5){
            System.out.println("Persuasion: " + (chmod + profBonus));
        }else{
            System.out.println("Persuasion: " + chmod);
        }
        System.out.println("Slight of Hand: " + dmod);
        System.out.println("Stealth: " + dmod);
        System.out.println("Survival: " + wmod + "\n");

        System.out.println("Initiative: " + dmod + "\n");

        System.out.println("Speed: " + speed + "\n");

        System.out.println("Max Hit Points: " + maxHitPoints);
        System.out.println("Hit Dice (d8): " + level + "\n");

        System.out.println("Because you are a Waterbender...");
        System.out.println("Intelligence +1 (already included in above calculations)");
        System.out.println("Charisma +2 (already included in above calculations)");
        System.out.println("Undying Will: Advantage on saving throws against being frightened");
        System.out.println("Arctic Skin: Resistance to cold damage\n");

        System.out.println("Proficiencies");
        System.out.println("Armor: Light armor");
        System.out.println("Saving Throws: Constitution Charisma (both already included in above calculations)");
        String profSkills = "Skills: ";
        if(prof1 == 0 || prof2 == 0){
            profSkills += "Deception ";
        }
        if(prof1 == 1 || prof2 == 1){
            profSkills += "Insight ";
        }
        if(prof1 == 2 || prof2 == 2){
            profSkills += "Investigation ";
        }
        if(prof1 == 3 || prof2 == 3){
            profSkills += "Medicine ";
        }
        if(prof1 == 4 || prof2 == 4){
            profSkills += "Performance ";
        }
        if(prof1 == 5 || prof2 == 5){
            profSkills += "Persuasion ";
        }
        profSkills += "(both already included in above calculations)\n";
        System.out.println(profSkills);

        System.out.println("Waterbending Skills known:\n");
        System.out.println("Traditional Waterbending: (See rule sheet for description of each skill\n)");
        for(int i = 0; i < tree1.length; i++){
            if(tree1.nodes[i].used[0] == true){
                System.out.print(tree1.nodes[i].name);
                System.out.print(" ");
                String string = "";
                for(int j = 0; j < tree1.nodes[i].length; j++){
                    if(tree1.nodes[i].used[j] == true){
                        string += "I";
                    }
                }
                System.out.println(string);
            }
        }
        System.out.println("\nAdvanced Waterbending:");
        for(int i = 0; i < tree2.length; i++){
            if(tree2.nodes[i].used[0] == true){
                System.out.print(tree2.nodes[i].name);
                System.out.print(" ");
                String string = "";
                for(int j = 0; j < tree2.nodes[i].length; j++){
                    if(tree2.nodes[i].used[j] == true){
                        string += "I";
                    }
                }
                System.out.println(string);
            }
        }
    }

}
