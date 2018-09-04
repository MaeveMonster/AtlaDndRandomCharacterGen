public class EarthCharacter extends Character{

    public EarthCharacter(){

    }

    public EarthCharacter(int g, int l){

        super(g, l);

        //earthbenders have a speed of 25ft per turn
        speed = 25;

        //earthbending culture is based off of Chinese culture, so these are Chinese names
        String[] femaleNames = {"Ai", "Bai", "Bo", "Chen", "Fa", "Fen", "Guo",
                "He", "Hui", "Ju", "Lan", "Liling", "Mei", "Nuan",
                "Nuo", "Qi", "Qiu", "Rong", "Shu", "Ting", "Tu", "Wei",
                "Wen", "Xiu", "Ya", "Zhen"};

        String[] unisexNames = {"Ah", "Bao", "Chin", "Fan", "Fang", "Guang",
                "Hai", "Heng", "Jiao", "Jie", "Lei", "Lim", "Lin", "Ling",
                "Mu", "Ning", "Shi", "Xiang", "Yan", "Yi", "Yin",
                "Ying", "Yong", "Yun", "Zan", "Zheng"};

        String[] maleNames = {"An", "Biming", "Chang", "Chun", "Da", "Feng", "Gang",
                "Han", "Huang", "Jian", "Kang", "Li", "Min", "Ming", "Niu", "Peng",
                "Qiang", "Ru", "Sheng", "Song", "Si", "Tai", "Wang", "Xing", "Yu",
                "Zhu"};

        //picks a random name based on the given gender
        if(gender == 0){
            name = maleNames[(int)(Math.random()*((25 - 0) +1))+0];
        }
        if(gender == 1){
            name = femaleNames[(int)(Math.random()*((25 - 0) +1))+0];
        }
        if(gender == 2){
            name = unisexNames[(int)(Math.random()*((23 - 0) +1))+0];
        }

        name += " of the Earth Kingdom";

        //earthbending styles are Powerful Earthbending and Subtle Earthbending
        tree1 = new SkillTree("Powerful", 7);
        tree2 = new SkillTree("Subtle", 8);

        //creates nodes of the skill trees in a precise order so that parent-child relationships are correct
        tree1.addNode(new Node("Launch Boulder", 3, null, null, false));
        tree1.addNode(new Node("Earth Wall", 3, tree1.nodes[0], null, false));
        tree1.addNode(new Node("Rock Shatter", 1, tree1.nodes[0], null, false));
        tree1.addNode(new Node("Earthquake", 1, tree1.nodes[1], null, false));
        tree1.addNode(new Node("Earth Strike", 3, tree1.nodes[2], null, false));
        tree1.addNode(new Node("Lavabending", 1, tree1.nodes[3], null, false));
        tree1.addNode(new Node("Master Earthbender", 1, tree1.nodes[3], tree1.nodes[4], true));

        tree2.addNode(new Node("Throw Rocks", 3, null, null, false));
        tree2.addNode(new Node("Rock Jump", 1, null, null, false));
        tree2.addNode(new Node("Dust Cloud", 1, tree2.nodes[0], null, false));
        tree2.addNode(new Node("Earth Pillar", 3, tree2.nodes[1], null, false));
        tree2.addNode(new Node("Earth Manipulation", 3, tree2.nodes[2], tree2.nodes[3], true));
        tree2.addNode(new Node("Earth Shield", 3, tree2.nodes[4], null, false));
        tree2.addNode(new Node("Earth Hold", 3, tree2.nodes[4], null, false));
        tree2.addNode(new Node("Earth Sense", 1, tree2.nodes[5], tree2.nodes[6], true));

    }
    //generates random stats for the character and prints them for the user
    public void genRandomStats(){

        //indicates how many skill points have been used to learn skills in the skill trees
        int spUsed = 0;
        //while skill points used is less than the number of skill points a character has to spend
        while(spUsed < skillPoints){
            //if no skill points have yet been used, pick one of the nodes at the top of the tree
            if(spUsed == 0){
                //first skill learned must be from the tree that corresponds to the character's bending style
                if(style == 0){
                    tree1.nodes[0].addSkillPoint();
                    spUsed++;
                }
                else {
                    tree2.nodes[(int)(Math.random()*((1-0)+1))+0].addSkillPoint();
                    spUsed++;
                }
            }
            //if this is not the first skill point spent
            else{
                //pick a random tree
                int tree = (int)(Math.random()*((1-0)+1))+0;
                //if the first tree is chosen
                if(tree == 0){
                    //generate a random node on the tree
                    int randNode = (int)(Math.random()*((6-0)+1))+0;
                    //represents how many skill points have been used before entering this loop
                    int spUsedBeforeLoop = spUsed;
                    //do until a skill point is spent
                    do{
                        //if the chosen node is not full (some skills can be leveled up by using more skill points on them)
                        //and if that skill's parent has been learned
                        //add a skill point to that node and add one to spUsed
                        if(tree1.nodes[randNode].parentIsUsed() && !tree1.nodes[randNode].isFull()){
                            tree1.nodes[randNode].addSkillPoint();
                            spUsed++;
                        }
                        else{
                            //else generate a new random node
                            randNode = (int)(Math.random()*((6-0)+1))+0;
                        }
                    }while(spUsedBeforeLoop == spUsed);
                }
                //if the second tree is chosen
                else{
                    //generate a random node on the tree
                    int randNode = (int)(Math.random()*((7-0)+1))+0;
                    //represents how many skill points have been used before entering this loop
                    int spUsedBeforeLoop = spUsed;
                    //do until a skill point is spent
                    do{
                        //if the chosen node is not full (some skills can be leveled up by using more skill points on them)
                        //and if that skill's parent has been learned
                        //add a skill point to that node and add one to spUsed
                        if(tree2.nodes[randNode].parentIsUsed() && !tree2.nodes[randNode].isFull()){
                            tree2.nodes[randNode].addSkillPoint();
                            spUsed++;
                        }
                        //else generate a new random node
                        else{
                            randNode = (int)(Math.random()*((7-0)+1))+0;
                        }
                    }while(spUsedBeforeLoop == spUsed);
                }
            }

        }

        int[] abilityScores = new int[6];
        int[] modifiers = new int[6];
        int[] rolls = new int[4];

        //for each of the 6 ability scores
        for(int i = 0; i < 6; i++){

            //roll a d6 4 times
            rolls[0] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[1] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[2] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[3] = (int)(Math.random() * ((6-1)+1)) + 1;

            //determine the smallest roll and set its index to smallestIndex
            int smallest = rolls[0];
            int smallestIndex = 0;
            for( int j = 0; j < 4; j++){

                if(rolls[j] < smallest){
                    smallest = rolls[j];
                    smallestIndex = j;
                }

            }

            //add the three largest rolls together
            int sum = 0;
            for(int j = 0; j < 4; j++){
                if(j != smallestIndex){
                    sum += rolls[j];
                }
            }

            //set the stat to the sum of the three largest rolls
            abilityScores[i] = sum;

        }

        //as a player levels up, occasionally they receive the opportunity to do one of the following:
        //increase one ability score by 2
        //increase two ability scores by 1
        if(level > 3 && level < 8){
            int statOne = (int)(Math.random() * ((5-0)+1)) + 0;
            int statTwo = (int)(Math.random() * ((5-0)+1)) + 0;

            abilityScores[statOne]++;
            abilityScores[statTwo]++;
        }
        else if(level > 7 && level < 12){
            int statOne = (int)(Math.random() * ((5-0)+1)) + 0;
            int statTwo = (int)(Math.random() * ((5-0)+1)) + 0;
            int statThree = (int)(Math.random() * ((5-0)+1)) + 0;
            int statFour = (int)(Math.random() * ((5-0)+1)) + 0;

            abilityScores[statOne]++;
            abilityScores[statTwo]++;
            abilityScores[statThree]++;
            abilityScores[statFour]++;
        }
        else if(level > 11 && level < 16){
            int statOne = (int)(Math.random() * ((5-0)+1)) + 0;
            int statTwo = (int)(Math.random() * ((5-0)+1)) + 0;
            int statThree = (int)(Math.random() * ((5-0)+1)) + 0;
            int statFour = (int)(Math.random() * ((5-0)+1)) + 0;
            int statFive = (int)(Math.random() * ((5-0)+1)) + 0;
            int statSix = (int)(Math.random() * ((5-0)+1)) + 0;

            abilityScores[statOne]++;
            abilityScores[statTwo]++;
            abilityScores[statThree]++;
            abilityScores[statFour]++;
            abilityScores[statFive]++;
            abilityScores[statSix]++;
        }
        else if(level > 15 && level < 19){
            int statOne = (int)(Math.random() * ((5-0)+1)) + 0;
            int statTwo = (int)(Math.random() * ((5-0)+1)) + 0;
            int statThree = (int)(Math.random() * ((5-0)+1)) + 0;
            int statFour = (int)(Math.random() * ((5-0)+1)) + 0;
            int statFive = (int)(Math.random() * ((5-0)+1)) + 0;
            int statSix = (int)(Math.random() * ((5-0)+1)) + 0;
            int statSeven = (int)(Math.random() * ((5-0)+1)) + 0;
            int statEight = (int)(Math.random() * ((5-0)+1)) + 0;

            abilityScores[statOne]++;
            abilityScores[statTwo]++;
            abilityScores[statThree]++;
            abilityScores[statFour]++;
            abilityScores[statFive]++;
            abilityScores[statSix]++;
            abilityScores[statSeven]++;
            abilityScores[statEight]++;
        }
        else if(level > 18){
            int statOne = (int)(Math.random() * ((5-0)+1)) + 0;
            int statTwo = (int)(Math.random() * ((5-0)+1)) + 0;
            int statThree = (int)(Math.random() * ((5-0)+1)) + 0;
            int statFour = (int)(Math.random() * ((5-0)+1)) + 0;
            int statFive = (int)(Math.random() * ((5-0)+1)) + 0;
            int statSix = (int)(Math.random() * ((5-0)+1)) + 0;
            int statSeven = (int)(Math.random() * ((5-0)+1)) + 0;
            int statEight = (int)(Math.random() * ((5-0)+1)) + 0;
            int statNine = (int)(Math.random() * ((5-0)+1)) + 0;
            int statTen = (int)(Math.random() * ((5-0)+1)) + 0;

            abilityScores[statOne]++;
            abilityScores[statTwo]++;
            abilityScores[statThree]++;
            abilityScores[statFour]++;
            abilityScores[statFive]++;
            abilityScores[statSix]++;
            abilityScores[statSeven]++;
            abilityScores[statEight]++;
            abilityScores[statNine]++;
            abilityScores[statTen]++;
        }

        //earthbenders have +2 strength and +2 constitution
        abilityScores[0] += 2;
        abilityScores[2] += 2;

        strength = abilityScores[0];
        dex = abilityScores[1];
        constitution = abilityScores[2];
        intelligence = abilityScores[3];
        wisdom = abilityScores[4];
        charisma = abilityScores[5];

        //sets modifiers to values indicated by the rules, based on the value of the corresponding stat
        //during gameplay modifiers are applied to rolls as indicated by the rules or at the DM's discretion
        for(int i = 0; i < 6; i++){
            if(abilityScores[i] == 1){
                modifiers[i] = -5;
            }
            else if(abilityScores[i] > 1 && abilityScores[i] < 4){
                modifiers[i] = -4;
            }
            else if(abilityScores[i] > 3 && abilityScores[i] < 6){
                modifiers[i] = -3;
            }
            else if(abilityScores[i] > 5 && abilityScores[i] < 8){
                modifiers[i] = -2;
            }
            else if(abilityScores[i] > 7 && abilityScores[i] < 10){
                modifiers[i] = -1;
            }
            else if(abilityScores[i] > 9 && abilityScores[i] < 12){
                modifiers[i] = 0;
            }
            else if(abilityScores[i] > 11 && abilityScores[i] < 14){
                modifiers[i] = 1;
            }
            else if(abilityScores[i] > 13 && abilityScores[i] < 16){
                modifiers[i] = 2;
            }
            else if(abilityScores[i] > 15 && abilityScores[i] < 18){
                modifiers[i] = 3;
            }
            else if(abilityScores[i] > 17 && abilityScores[i] < 20){
                modifiers[i] = 4;
            }
            else{
                modifiers[i] = 5;
            }
        }

        sMod = modifiers[0];
        dMod = modifiers[1];
        coMod = modifiers[2];
        iMod = modifiers[3];
        wMod = modifiers[4];
        chMod = modifiers[5];

        //sets hit points to the minimum as indicated by the rules
        if(level == 1){
            maxHitPoints = 8;
        }
        //each time a player levels up, they roll a d8, add their constitution modifier to the roll, and then add that number to their max hit points
        else{
            maxHitPoints = 8;
            for(int i = 2; i < 21; i++){
                int roll = (int)(Math.random()*((8-1)+1))+1;
                maxHitPoints += roll;
                maxHitPoints += coMod;
                if(level == i){
                    break;
                }
            }
        }

        //prints stats for the user
        System.out.println("Name: " + name);
        System.out.println("Level: " + level + "\n");

        System.out.println("Ability Scores and Modifiers");
        System.out.println("Strength: " + strength);
        System.out.println("Strength modifier: " + sMod);
        System.out.println("Dexterity: " + dex);
        System.out.println("Dexterity modifier: " + dMod);
        System.out.println("Constitution: " + constitution);
        System.out.println("Constitution modifier: " + coMod);
        System.out.println("Intelligence: " + intelligence);
        System.out.println("Intelligence modifier: " + iMod);
        System.out.println("Wisdom: " + wisdom);
        System.out.println("Wisdom modifier: " + wMod);
        System.out.println("Charisma: " + charisma);
        System.out.println("Charisma modifier: " + chMod + "\n");

        //bending bonus depends on which style of bending is chosen
        if(style == 0){
            System.out.println("Bending Bonus: " + sMod + "\n");
        }else{
            System.out.println("Bending Bonus: " + wMod + "\n");
        }

        System.out.println("Proficiency Bonus: " + profBonus + "\n");

        System.out.println("Saving Throws");
        System.out.println("Strength saving throw: " + (sMod + profBonus));
        System.out.println("Dexterity saving throw: " + dMod);
        System.out.println("Constitution saving throw: " + (coMod + profBonus));
        System.out.println("Intelligence saving throw: " + iMod);
        System.out.println("Wisdom saving throw: " + wMod);
        System.out.println("Charisma saving throw: " + chMod + "\n");

        //chooses 2 skills out of the possible 5 in which a earthbenders may be proficient as indicated by the rules
        int prof1 = (int)(Math.random()*((4-0)+1))+0;
        int prof2;
        do{

            prof2 = (int)(Math.random()*((4-0)+1))+0;

        }while(prof2 == prof1);

        System.out.println("Skills");
        System.out.println("Acrobatics: " + dMod);
        if(prof1 == 0 || prof2 == 0){

            System.out.println("Animal Handling: " + (wMod + profBonus));
        }else{
            System.out.println("Animal Handling: " + wMod);
        }
        if(prof1 == 1 || prof2 == 1){

            System.out.println("Athletics: " + (sMod + profBonus));
        }else{
            System.out.println("Athletics: " + sMod);
        }
        System.out.println("Deception: " + chMod);
        System.out.println("History: " + iMod);
        System.out.println("Insight: " + wMod);
        if(prof1 == 2 || prof2 == 2){

            System.out.println("Intimidation: " + (chMod + profBonus));
        }else{
            System.out.println("Intimidation: " + chMod);
        }
        System.out.println("Investigation: " + iMod);
        System.out.println("Medicine: " + wMod);
        if(prof1 == 3 || prof2 == 3){

            System.out.println("Nature: " + (iMod + profBonus));
        }else{
            System.out.println("Nature: " + iMod);
        }
        System.out.println("Perception: " + wMod);
        System.out.println("Performance: " + chMod);
        System.out.println("Persuasion: " + chMod);
        System.out.println("Slight of Hand: " + dMod);
        System.out.println("Stealth: " + dMod);
        if(prof1 == 3 || prof2 == 3){

            System.out.println("Survival: " + (wMod + profBonus) + "\n");
        }else{
            System.out.println("Survival: " + wMod + "\n");
        }

        System.out.println("Initiative: " + dMod + "\n");

        System.out.println("Speed: " + speed + "\n");

        System.out.println("Max Hit Points: " + maxHitPoints);
        System.out.println("Hit Dice (d8): " + level + "\n");

        System.out.println("Because you are an Earthbender...");
        System.out.println("Strength +2 (already included in above calculations)");
        System.out.println("Constitution +2 (already included in above calculations)");
        System.out.println("Earthen Toughness: Resistance to bludgeoning damage\n");

        System.out.println("Proficiencies");
        System.out.println("Armor: Light armor, medium armor");
        System.out.println("Saving Throws: Strength Constitution (both already included in above calculations)");
        String profSkills = "Skills: ";
        if(prof1 == 0 || prof2 == 0){
            profSkills += "Animal Handling ";
        }
        if(prof1 == 1 || prof2 == 1){
            profSkills += "Athletics ";
        }
        if(prof1 == 2 || prof2 == 2){
            profSkills += "Intimidation ";
        }
        if(prof1 == 3 || prof2 == 3){
            profSkills += "Nature ";
        }
        if(prof1 == 4 || prof2 == 4){
            profSkills += "Survival ";
        }
        profSkills += "(both already included in above calculations)\n";
        System.out.println(profSkills);

        //prints the skills learned from both skill trees and at what level each skill is
        System.out.println("Earthbending Skills known: (See rule sheet for description of each skill)\n");
        System.out.println("Powerful Earthbending:");
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
        System.out.println("\nSubtle Earthbending:");
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
