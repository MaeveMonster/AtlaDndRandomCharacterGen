public class AirCharacter extends Character{

    public AirCharacter(){

    }

    //airbender generation does not require a gender because all airbender names are genderneutral
    public AirCharacter(int l){

        name = "name";
        level = l;

        //sets max bending points depending on the level of the character
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

        //sets proficiency bonus depending on the level of the character
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

        //sets all stats to 0 so that random generation can occur in genRandomStats
        strength = 0;
        dex = 0;
        constitution = 0;
        intelligence = 0;
        wisdom = 0;
        charisma = 0;
        maxHitPoints = 0;
        hitDice = 0;
        //airbenders have a speed of 30ft per turn
        speed = 30;

        //airbending culture is based off of Tibetan culture, so all airbender names are Tibetan
        //most Tibetan names are genderneutral, so there is no male or female category
        String[] names = {"Adhe", "Amchila", "Amdo", "Chenrezig", "Chetsang", "Dadul", "Geshe",
                "Jetrung", "Kapsho", "Khenpo", "Lhamo", "Lotse", "Milarepa", "Namgang", "Norbu", "Pasang",
                "Pemba", "Rimshi", "Salden", "Sengi", "Takla", "Tenzing", "Tsetrung", "Wangdu", "Yangkyi",
                "Zachoeje"};

        //picks a random name from the list
        name = names[(int)(Math.random()*((23 - 0) +1))+0];

        name += " of the Air Nomads";

        //generates a random bending style, either 0 or 1
        style = (int)(Math.random()*((1-0)+1))+0;

        //sets the amount of skill points a character has based on their level
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

        //airbending styles are Defensive Airbending and Offensive Airbending
        tree1 = new SkillTree("Defensive", 7);
        tree2 = new SkillTree("Offensive", 8);

        //creates nodes of the skill trees in a precise order so that parent-child relationships are correct
        tree1.addNode(new Node("Air Block", 1, null, null, false));
        tree1.addNode(new Node("Air Sprint", 1, null, null, false));
        tree1.addNode(new Node("Air Boost", 3, tree1.nodes[1], null, false));
        tree1.addNode(new Node("Air Dodging", 3, tree1.nodes[0], tree1.nodes[2], true));
        tree1.addNode(new Node("Air Conditioning", 3, tree1.nodes[2], null, false));
        tree1.addNode(new Node("Extra Reactions", 3, tree1.nodes[3], null, false));
        tree1.addNode(new Node("Turbulence", 1, tree1.nodes[4], null, false));

        tree2.addNode(new Node("Air Punch", 3, null, null, false));
        tree2.addNode(new Node("Air Blast", 2, tree2.nodes[0], null, false));
        tree2.addNode(new Node("Wind Sweep", 2, tree2.nodes[0], null, false));
        tree2.addNode(new Node("Breath of Wind", 3, tree2.nodes[1], null, false));
        tree2.addNode(new Node("Wind Strike", 3, tree2.nodes[2], null, false));
        tree2.addNode(new Node("Remote Wind", 2, tree2.nodes[3], null, false));
        tree2.addNode(new Node("Parry", 1, tree2.nodes[4], null, false));
        tree2.addNode(new Node("Master Airbender", 1, tree2.nodes[5], tree2.nodes[6], true));
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
                    tree1.nodes[(int)(Math.random()*((1-0)+1))+0].addSkillPoint();
                    spUsed++;
                }
                else {
                    tree2.nodes[0].addSkillPoint();
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
                        //else generate a new random node
                        else{
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

        int[] stats = new int[6];
        int[] modifiers = new int[6];
        int[] rolls = new int[4];

        //for each of the six stats
        for(int i = 0; i < 6; i++){

            //roll a d6 four times
            rolls[0] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[1] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[2] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[3] = (int)(Math.random() * ((6-1)+1)) + 1;

            //determines the smallest of the four rolls
            int smallest = rolls[0];
            int smallestIndex = 0;
            for( int j = 0; j < 4; j++){

                if(rolls[j] < smallest){
                    smallest = rolls[j];
                    smallestIndex = j;
                }

            }

            //adds the largest 3 rolls together
            int sum = 0;
            for(int j = 0; j < 4; j++){
                if(j != smallestIndex){
                    sum += rolls[j];
                }
            }

            //assigns the sum of the largest three rolls to the stat at i
            stats[i] = sum;

        }

        //as a player levels up, occasionally they receive the opportunity to do one of the following:
        //increase one stat by 2
        //increase two stats by 1
        if(level > 3 && level < 8){
            int statOne = (int)(Math.random() * ((5-0)+1)) + 0;
            int statTwo = (int)(Math.random() * ((5-0)+1)) + 0;

            stats[statOne]++;
            stats[statTwo]++;
        }
        else if(level > 7 && level < 12){
            int statOne = (int)(Math.random() * ((5-0)+1)) + 0;
            int statTwo = (int)(Math.random() * ((5-0)+1)) + 0;
            int statThree = (int)(Math.random() * ((5-0)+1)) + 0;
            int statFour = (int)(Math.random() * ((5-0)+1)) + 0;

            stats[statOne]++;
            stats[statTwo]++;
            stats[statThree]++;
            stats[statFour]++;
        }
        else if(level > 11 && level < 16){
            int statOne = (int)(Math.random() * ((5-0)+1)) + 0;
            int statTwo = (int)(Math.random() * ((5-0)+1)) + 0;
            int statThree = (int)(Math.random() * ((5-0)+1)) + 0;
            int statFour = (int)(Math.random() * ((5-0)+1)) + 0;
            int statFive = (int)(Math.random() * ((5-0)+1)) + 0;
            int statSix = (int)(Math.random() * ((5-0)+1)) + 0;

            stats[statOne]++;
            stats[statTwo]++;
            stats[statThree]++;
            stats[statFour]++;
            stats[statFive]++;
            stats[statSix]++;
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

            stats[statOne]++;
            stats[statTwo]++;
            stats[statThree]++;
            stats[statFour]++;
            stats[statFive]++;
            stats[statSix]++;
            stats[statSeven]++;
            stats[statEight]++;
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

            stats[statOne]++;
            stats[statTwo]++;
            stats[statThree]++;
            stats[statFour]++;
            stats[statFive]++;
            stats[statSix]++;
            stats[statSeven]++;
            stats[statEight]++;
            stats[statNine]++;
            stats[statTen]++;
        }

        //airbenders have +2 dexterity and +2 wisdom
        stats[1] += 2;
        stats[4] += 2;

        strength = stats[0];
        dex = stats[1];
        constitution = stats[2];
        intelligence = stats[3];
        wisdom = stats[4];
        charisma = stats[5];

        //sets the modifiers based on each corresponding stat according to the rules
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

        //sets hit points to the minimum according to the rules
        if(level == 1){
            maxHitPoints = 8;
        }
        //each time a player levels up, they roll a d8, add their constitution modifier to the roll, and then add that number to their max hit points
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

        //prints stats for the user
        System.out.println("Name: " + name);
        System.out.println("Level: " + level + "\n");

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

        //bending bonus depends on which style of bending is chosen
        if(style == 0){
            System.out.println("Bending Bonus: " + dmod + "\n");
        }else{
            System.out.println("Bending Bonus: " + wmod + "\n");
        }

        System.out.println("Proficiency Bonus: " + profBonus + "\n");

        System.out.println("Saving Throws");
        System.out.println("Strength saving throw: " + smod);
        //airbenders are proficient in dexterity saving throws
        System.out.println("Dexterity saving throw: " + (dmod + profBonus));
        System.out.println("Constitution saving throw: " + (comod));
        System.out.println("Intelligence saving throw: " + imod);
        //airbenders are proficient in wisdom saving throws
        System.out.println("Wisdom saving throw: " + (wmod + profBonus));
        System.out.println("Charisma saving throw: " + (chmod) + "\n");

        //chooses 2 skills out of the possible 6 in which a airbender may be proficient as indicated by the rules
        int prof1 = (int)(Math.random()*((5-0)+1))+0;
        int prof2;
        do{

            prof2 = (int)(Math.random()*((5-0)+1))+0;

        }while(prof2 == prof1);

        System.out.println("Skills");
        System.out.println("Acrobatics: " + (dmod + profBonus));
        if(prof1 == 0 || prof2 == 0){

            System.out.println("Animal Handling: " + (wmod + profBonus));
        }else{
            System.out.println("Animal Handling: " + wmod);
        }
        if(prof1 == 1 || prof2 == 1){
            System.out.println("Athletics: " + (smod + profBonus));
        }else{
            System.out.println("Animal Handling: " + smod);
        }
        System.out.println("Deception: " + chmod);
        if(prof1 == 2 || prof2 == 2){
            System.out.println("History: " + (imod + profBonus));
        }else{
            System.out.println("History: " + imod);
        }
        if(prof1 == 3 || prof2 == 3){
            System.out.println("Insight: " + (wmod + profBonus));
        }else{
            System.out.println("Insight: " + wmod);
        }
        System.out.println("Intimidation: " + chmod);
        System.out.println("Investigation: " + imod);
        System.out.println("Medicine: " + wmod);
        System.out.println("Nature: " + imod);
        if(prof1 == 4 || prof2 == 4){
            System.out.println("Perception: " + (wmod + profBonus));
        }else{
            System.out.println("Perception: " + wmod);
        }
        System.out.println("Performance: " + chmod);
        System.out.println("Persuasion: " + chmod);
        if(prof1 == 5 || prof2 == 5){
            System.out.println("Slight of Hand: " + (dmod + profBonus));
        }else{
            System.out.println("Slight of Hand: " + dmod);
        }
        System.out.println("Stealth: " + (dmod + profBonus));
        System.out.println("Survival: " + wmod + "\n");

        System.out.println("Initiative: " + dmod + "\n");

        System.out.println("Speed: " + speed + "\n");

        System.out.println("Max Hit Points: " + maxHitPoints);
        System.out.println("Hit Dice (d8): " + level + "\n");

        System.out.println("Because you are an Airbender...");
        System.out.println("Dexterity +2 (already included in above calculations)");
        System.out.println("Wisdom +2 (already included in above calculations)");
        System.out.println("Light Footed: Proficiency in Acrobatics and Stealth (both already included in above calculations)\n");

        System.out.println("Proficiencies");
        System.out.println("Armor: none");
        System.out.println("Saving Throws: Dexterity Wisdom (both already included in above calculations)");
        String profSkills = "Skills: ";
        if(prof1 == 0 || prof2 == 0){
            profSkills += "Animal Handling ";
        }
        if(prof1 == 1 || prof2 == 1){
            profSkills += "Athletics ";
        }
        if(prof1 == 2 || prof2 == 2){
            profSkills += "History ";
        }
        if(prof1 == 3 || prof2 == 3){
            profSkills += "Insight ";
        }
        if(prof1 == 4 || prof2 == 4){
            profSkills += "Perception ";
        }
        if(prof1 == 5 || prof2 == 5){
            profSkills += "Slight of Hand ";
        }
        profSkills += "(both already included in above calculations)\n";
        System.out.println(profSkills + "\n");

        //prints the skills learned from both skill trees and at what level each skill is
        System.out.println("Airbending Skills known:\n");
        System.out.println("Defensive Airbending: (See rule sheet for description of each skill\n)");
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
        System.out.println("\nOffensive Airbending:");
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
