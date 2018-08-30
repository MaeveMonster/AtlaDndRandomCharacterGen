public class FireCharacter extends Character{

    public FireCharacter(){

    }

    public FireCharacter(int g, int l){

        name = "name";
        gender = g;
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

        //sets all stats to zero so that random generation can occur in genRandomStats
        strength = 0;
        dex = 0;
        constitution = 0;
        intelligence = 0;
        wisdom = 0;
        charisma = 0;
        maxHitPoints = 0;
        hitDice = 0;
        //firebenders have a movement speed of 30ft per turn
        speed = 30;

        //firebending culture is based on Japanese culture, so these are Japanese names
        String[] femaleNames = {"Andrea", "Kaiya", "Mao", "Naoko", "Nariko", "Natsuko", "Nishi",
                "Noriko", "Nozomi", "Rei", "Sachi", "Tamiko", "Umeko", "Yoko",
                "Yoshiko", "Yuri", "Aika", "Aimi", "Akane", "Fumiko", "Haruka", "Mari",
                "Megu", "Chiyoko", "Chizuru", "Koharu"};

        String[] unisexNames = {"Aki", "Akiro", "Aoi", "Haru", "Hikaru", "Hinata",
                "Hiro", "Hisoka", "Jun", "Kaede", "Kaoru", "Kin", "Kohaku", "Kyo",
                "Makoto", "Masa", "Masumi", "Michi", "Minori", "Mitsuru", "Nao",
                "Natsu", "Ren", "Shinobu", "Seiko", "Sora"};

        String[] maleNames = {"Aito", "Akito", "Daichi", "Daisuke", "Eiji", "Fumihiro", "Giichi",
                "Hibiki", "Itsuo", "Katsumi", "Kazuto", "Kouichi", "Mitsue", "Natsuo", "Noritaka", "Naozumi",
                "Raiden", "Reiji", "Saburo", "Takahiro", "Tatsuya", "Tetsu", "Toshiro", "Tsubasa", "Yosuke",
                "Yasahiro"};

        //picks a random name based on gender
        if(gender == 0){
            name = maleNames[(int)(Math.random()*((25 - 0) +1))+0];
        }
        if(gender == 1){
            name = femaleNames[(int)(Math.random()*((25 - 0) +1))+0];
        }
        if(gender == 2){
            name = unisexNames[(int)(Math.random()*((23 - 0) +1))+0];
        }

        name += " of the Fire Nation";

        //generates a random bending style, either 0 or 1
        style = (int)(Math.random()*((1-0)+1))+0;

        //sets skill points depending on level of the character
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

        //firebending styes are Aggressive Firebending and Controlled Firebending
        //creates skill trees to hold bending skills
        tree1 = new SkillTree("Aggressive", 6);
        tree2 = new SkillTree("Controlled", 7);

        //adds nodes to the skill trees in a precise order so that parant-child relationships are correct
        tree1.addNode(new Node("Flame Punch", 3, null, null, false));
        tree1.addNode(new Node("Fireball", 3, tree1.nodes[0], null, false));
        tree1.addNode(new Node("Flaming Kick", 3, tree1.nodes[0], null, false));
        tree1.addNode(new Node("Fire Stream", 3, tree1.nodes[1], null, false));
        tree1.addNode(new Node("Flaming Dropkick", 3, tree1.nodes[2], null, false));
        tree1.addNode(new Node("Jet Propulsion", 1, tree1.nodes[3], tree1.nodes[4], true));

        tree2.addNode(new Node("Breath of Fire", 3, null, null, false));
        tree2.addNode(new Node("Extinguish Fire", 3, null, null, false));
        tree2.addNode(new Node("Scorching Flames", 3, tree2.nodes[0], null, false));
        tree2.addNode(new Node("Deflect Fire", 2, tree2.nodes[1], null, false));
        tree2.addNode(new Node("Lightningbending", 2, tree2.nodes[2], null, false));
        tree2.addNode(new Node("Redirect Fire", 2, tree2.nodes[3], null, false));
        tree2.addNode(new Node("Master Firebender", 1, tree2.nodes[4], tree2.nodes[5], true));

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
                    int randNode = (int)(Math.random()*((5-0)+1))+0;
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
                            randNode = (int)(Math.random()*((5-0)+1))+0;
                        }
                    }while(spUsedBeforeLoop == spUsed);
                }
                //if the second tree is chosen
                else{
                    //generate a random node on the tree
                    int randNode = (int)(Math.random()*((6-0)+1))+0;
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
                            randNode = (int)(Math.random()*((6-0)+1))+0;
                        }
                    }while(spUsedBeforeLoop == spUsed);
                }
            }

        }

        int[] stats = new int[6];
        int[] modifiers = new int[6];
        int[] rolls = new int[4];

        //for each of the 6 stats
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

        //firebenders have +1 strength, +1 dexterity, and +1 intelligence
        stats[0]++;
        stats[1]++;
        stats[3]++;

        strength = stats[0];
        dex = stats[1];
        constitution = stats[2];
        intelligence = stats[3];
        wisdom = stats[4];
        charisma = stats[5];

        //sets modifiers to values indicated by the rules, based on the value of the corresponding stat
        //during gameplay modifiers are applied to rolls as indicated by the rules or at the DM's discretion
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
            System.out.println("Bending Bonus: " + smod + "\n");
        }else{
            System.out.println("Bending Bonus: " + wmod + "\n");
        }

        System.out.println("Proficiency Bonus: " + profBonus + "\n");

        System.out.println("Saving Throws");
        //firebenders are proficient in strength saving throwa
        System.out.println("Strength saving throw: " + (smod + profBonus));
        //firebenders are proficient in dexterity saving throws
        System.out.println("Dexterity saving throw: " + (dmod + profBonus));
        System.out.println("Constitution saving throw: " + comod);
        System.out.println("Intelligence saving throw: " + imod);
        System.out.println("Wisdom saving throw: " + wmod);
        System.out.println("Charisma saving throw: " + chmod + "\n");

        //chooses 2 skills out of the possible 6 in which a firebender may be proficient as indicated by the rules
        int prof1 = (int)(Math.random()*((4-0)+1))+0;
        int prof2;
        do{

            prof2 = (int)(Math.random()*((5-0)+1))+0;

        }while(prof2 == prof1);

        System.out.println("Skills");
        if(prof1 == 0 || prof2 == 0){

            System.out.println("Acrobatics: " + (dmod + profBonus));
        }else{
            System.out.println("Acrobatics: " + dmod);
        }
        System.out.println("Animal Handling: " + wmod);
        if(prof1 == 1 || prof2 == 1){

            System.out.println("Athletics: " + (smod + profBonus));
        }else{
            System.out.println("Athletics: " + smod);
        }
        if(prof1 == 2 || prof2 == 2){

            System.out.println("Deception: " + (chmod + profBonus));
        }else{
            System.out.println("Deception: " + chmod);
        }
        System.out.println("History: " + imod);
        if(prof1 == 3 || prof2 == 3){
            System.out.println("Insight: " + (wmod + profBonus));
        }else{
            System.out.println("Insight: " + wmod);
        }
        if(prof1 == 4 || prof2 == 4){

            System.out.println("Intimidation: " + (chmod + profBonus));
        }else{
            System.out.println("Intimidation: " + chmod);
        }
        System.out.println("Investigation: " + imod);
        System.out.println("Medicine: " + wmod);
        System.out.println("Nature: " + imod);
        if(prof1 == 5 || prof2 == 5){
            System.out.println("Perception: " + (wmod + profBonus));
        }else{
            System.out.println("Perception: " + wmod);
        }
        System.out.println("Performance: " + chmod);
        System.out.println("Persuasion: " + chmod);
        System.out.println("Slight of Hand: " + dmod);
        System.out.println("Stealth: " + dmod);
        System.out.println("Survival: " + wmod + "\n");

        System.out.println("Initiative: " + dmod + "\n");

        System.out.println("Speed: " + speed + "\n");

        System.out.println("Max Hit Points: " + maxHitPoints);
        System.out.println("Hit Dice (d8): " + level + "\n");

        System.out.println("Because you are an Firebender...");
        System.out.println("Strength +1 (already included in above calculations)");
        System.out.println("Dexterity +1 (already included in above calculations)");
        System.out.println("Intelligence +1 (already included in above calculations)");
        System.out.println("Firey Resistance: Resistance to burning damage\n");

        System.out.println("Proficiencies");
        System.out.println("Armor: Light armor, medium armor");
        System.out.println("Saving Throws: Strength Dexterity (both already included in above calculations)");
        String profSkills = "Skills: ";
        if(prof1 == 0 || prof2 == 0){
            profSkills += "Acrobatics ";
        }
        if(prof1 == 1 || prof2 == 1){
            profSkills += "Athletics ";
        }
        if(prof1 == 2 || prof2 == 2){
            profSkills += "Deception ";
        }
        if(prof1 == 3 || prof2 == 3){
            profSkills += "Insight ";
        }
        if(prof1 == 4 || prof2 == 4){
            profSkills += "Intimidation ";
        }
        if(prof1 == 5 || prof2 == 5){
            profSkills += "Perception ";
        }
        profSkills += "(both already included in above calculations)\n";
        System.out.println(profSkills);

        //prints the skills learned from both skill trees and at what level each skill is
        System.out.println("Firebending Skills known: (See rule sheet for description of each skill)\n");
        System.out.println("Aggressive Firebending:");
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
        System.out.println("\nControlled Firebending:");
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
