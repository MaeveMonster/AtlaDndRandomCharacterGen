public class Character {

    String name;
    int gender; //only used for generating name, all airbender names are unisex, so airbenders don't need this field
    int level;
    int maxBP; //maximum bending points for use in combat
    int strength;
    int sMod; //strength modifier
    int dex; //dexterity
    int dMod; //dexterity modifier
    int constitution;
    int coMod; //constitution modifier
    int intelligence;
    int iMod; //intelligence modifier
    int wisdom;
    int wMod; //wisdom modifier
    int charisma;
    int chMod; //charisma modifier
    int maxHitPoints;
    int hitDice;
    int profBonus; //proficiency bonus depends on level
    int speed;
    int style; //bending style, either 0 or 1 because each element has 2 possible bending styles
    int skillPoints; //number of upgrades possible when filling the skill tree, depends on level
    SkillTree tree1; //bending style 0
    SkillTree tree2; //bending style 1

    public Character(){
        name = "name";
        gender = 0;
        level = 1;

        //sets everything to the minimum as detailed in the rules
        maxBP = 9;
        profBonus = 2;
        strength = 0;
        dex = 0;
        constitution = 0;
        intelligence = 0;
        wisdom = 0;
        charisma = 0;
        maxHitPoints = 0;
        hitDice = 0;
        skillPoints = 2;

        //generates a random style, either 0 or 1
        style = (int)(Math.random()*((1-0)+1))+0;
    }

    public Character(int g, int l){

        name = "name";
        gender = g;
        level = l;

        maxBP = genMaxBendingPoints(level);

        profBonus = genProfBonus(level);

        //sets all ability scores to zero so that random generation can occur in genRandomStats
        strength = 0;
        dex = 0;
        constitution = 0;
        intelligence = 0;
        wisdom = 0;
        charisma = 0;
        maxHitPoints = 0;
        hitDice = 0;

        skillPoints = genSkillPoints(level);

        //generates a random style, either 0 or 1
        style = (int)(Math.random()*((1-0)+1))+0;

    }

    //generates stats randomly as Dnd rules state. Simulates rolling dice at marked locations
    public void genRandomStats(){

        int abilityScores[] = genRandomAbilityScores();
        abilityScores = levelUpAbilityScores(abilityScores, level);
        int[] modifiers = genAbilityModifiers(abilityScores);

        strength = abilityScores[0];
        dex = abilityScores[1];
        constitution = abilityScores[2];
        intelligence = abilityScores[3];
        wisdom = abilityScores[4];
        charisma = abilityScores[5];

        sMod = modifiers[0];
        dMod = modifiers[1];
        coMod = modifiers[2];
        iMod = modifiers[3];
        wMod = modifiers[4];
        chMod = modifiers[5];

        maxHitPoints = genMaxHitPoints(level, coMod);

    }

    public int[] genRandomAbilityScores(){

        int[] abilityScores = new int[6];
        int[] rolls = new int[4];

        //for each of the 6 ability scores: strength, dexterity, constitution, intelligence, wisdom, charisma
        for(int i = 0; i < 6; i++){

            //simulates rolling 4 d6 dice
            rolls[0] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[1] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[2] = (int)(Math.random() * ((6-1)+1)) + 1;
            rolls[3] = (int)(Math.random() * ((6-1)+1)) + 1;

            //determines the lowest number rolled and saves the index of that roll to smallestIndex
            int smallest = rolls[0];
            int smallestIndex = 0;
            for( int j = 0; j < 4; j++){

                if(rolls[j] < smallest){
                    smallest = rolls[j];
                    smallestIndex = j;
                }

            }

            //adds 3 largest rolls together
            int sum = 0;
            for(int j = 0; j < 4; j++){
                if(j != smallestIndex){
                    sum += rolls[j];
                }
            }

            //assigns the sum of the largest 3 rolls to one of the six ability scores
            abilityScores[i] = sum;

        }

        return abilityScores;

    }

    public int[] levelUpAbilityScores(int[] abilityScores, int lev){
        //as a player levels up, occasionally they receive the opportunity to do one of the following:
        //increase one ability score by 2
        //increase two ability scores by 1
        if(lev > 3 && lev < 8){
            int statOne = (int)(Math.random() * ((5-0)+1)) + 0;
            int statTwo = (int)(Math.random() * ((5-0)+1)) + 0;

            abilityScores[statOne]++;
            abilityScores[statTwo]++;
        }
        else if(lev > 7 && lev < 12){
            int statOne = (int)(Math.random() * ((5-0)+1)) + 0;
            int statTwo = (int)(Math.random() * ((5-0)+1)) + 0;
            int statThree = (int)(Math.random() * ((5-0)+1)) + 0;
            int statFour = (int)(Math.random() * ((5-0)+1)) + 0;

            abilityScores[statOne]++;
            abilityScores[statTwo]++;
            abilityScores[statThree]++;
            abilityScores[statFour]++;
        }
        else if(lev > 11 && lev < 16){
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
        else if(lev > 15 && lev < 19){
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
        else if(lev > 18){
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

        return abilityScores;
    }

    public int[] genAbilityModifiers(int[] abilityScores){

        int[] modis = new int[6];

        //sets modifiers to values indicated by the rules, based on the value of the corresponding stat
        //during gameplay modifiers are applied to rolls as indicated by the rules or at the DM's discretion
        for(int i = 0; i < 6; i++){
            if(abilityScores[i] == 1){
                modis[i] = -5;
            }
            else if(abilityScores[i] > 1 && abilityScores[i] < 4){
                modis[i] = -4;
            }
            else if(abilityScores[i] > 3 && abilityScores[i] < 6){
                modis[i] = -3;
            }
            else if(abilityScores[i] > 5 && abilityScores[i] < 8){
                modis[i] = -2;
            }
            else if(abilityScores[i] > 7 && abilityScores[i] < 10){
                modis[i] = -1;
            }
            else if(abilityScores[i] > 9 && abilityScores[i] < 12){
                modis[i] = 0;
            }
            else if(abilityScores[i] > 11 && abilityScores[i] < 14){
                modis[i] = 1;
            }
            else if(abilityScores[i] > 13 && abilityScores[i] < 16){
                modis[i] = 2;
            }
            else if(abilityScores[i] > 15 && abilityScores[i] < 18){
                modis[i] = 3;
            }
            else if(abilityScores[i] > 17 && abilityScores[i] < 20){
                modis[i] = 4;
            }
            else{
                modis[i] = 5;
            }
        }

        return modis;

    }

    public int genMaxHitPoints(int lev, int constitutionModifier){

        int maxHP;

        //sets hit points to the minimum as indicated by the rules
        if(lev == 1){
            maxHP = 8;
        }
        //each time a player levels up, they roll a d8, add their constitution modifier to the roll, and then add that number to their max hit points
        else{
            maxHP = 8;
            for(int i = 2; i < 21; i++){
                int roll = (int)(Math.random()*((8-1)+1))+1;
                maxHP += roll;
                maxHP += constitutionModifier;
                if(lev == i){
                    break;
                }
            }
        }

        return maxHP;

    }

    public void printStats(){

        //prints information for the user

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

        System.out.println("Proficiency Bonus: " + profBonus + "\n");

        System.out.println("Initiative: " + dMod + "\n");

        System.out.println("Speed: " + speed + "\n");

        System.out.println("Max Hit Points: " + maxHitPoints);
        System.out.println("Hit Dice (d8): " + level + "\n");



        //prints the skills learned from both skill trees and at what level each skill is
        if(style == 0){
            System.out.println("Bending style (skills from this tree cost fewer bending points to perform in battle): " + tree1.name);
        }
        else{
            System.out.println("Bending style (skills from this tree cost fewer bending points to perform in battle): " + tree2.name);
        }
        System.out.println("Bending skills known:\n");
        tree1.print();
        System.out.println();
        tree2.print();
        System.out.println();

    }

    public int genMaxBendingPoints(int lev){

        int maxBendingPoints;

        //sets max bending points depending on the level of the character
        if(lev == 1){
            maxBendingPoints = 8;
        }
        else if(lev > 1 && level < 5){
            maxBendingPoints = 9;
        }
        else if(lev > 4 && level < 8){
            maxBendingPoints = 10;
        }
        else if(lev > 7 && level < 11){
            maxBendingPoints = 11;
        }
        else if(lev > 10 && level < 14){
            maxBendingPoints = 12;
        }
        else if(lev > 13 && level < 17){
            maxBendingPoints = 13;
        }
        else if(lev > 16 && level < 20){
            maxBendingPoints = 14;
        }
        else{
            maxBendingPoints = 15;
        }

        return maxBendingPoints;

    }

    public int genProfBonus(int lev){

        int pb;

        //sets proficiency bonus depending on the level of the character
        if(lev < 5){
            pb = 2;
        }
        else if(lev > 4 && lev < 9){
            pb = 3;
        }
        else if(lev > 8 && lev < 13){
            pb = 4;
        }
        else if(lev > 12 && lev < 17){
            pb = 5;
        }
        else{
            pb = 6;
        }

        return pb;

    }

    public int genSkillPoints(int lev){

        int sp;

        //sets skill points depending on character's level as detailed in the rules
        if(level == 1){
            sp = 2;
        }
        else if(level == 2){
            sp = 3;
        }
        else if(level == 3 || level == 4){
            sp = 5;
        }
        else if(level == 5){
            sp = 6;
        }
        else if(level == 6){
            sp = 8;
        }
        else if(level == 7 || level == 8){
            sp = 10;
        }
        else if(level == 9){
            sp = 12;
        }
        else if(level == 10){
            sp = 14;
        }
        else if(level == 11 || level == 12){
            sp = 15;
        }
        else if(level == 13){
            sp = 17;
        }
        else if(level == 14){
            sp = 18;
        }
        else if(level == 15 || level == 16){
            sp = 20;
        }
        else if(level == 17){
            sp = 21;
        }
        else if(level == 18 || level == 19){
            sp = 23;
        }
        else{
            sp = 24;
        }

        return sp;

    }

    public void fillSkillTrees(int tree1Size, int tree2Size){

        //indicates how many skill points have been used to learn skills in the skill trees
        int spUsed = 0;

        //while skill points used is less than the number of skill points a character has to spend
        while(spUsed < skillPoints){
            //if no skill points have yet been used, pick one of the nodes at the top of the tree
            if(spUsed == 0){
                //first skill learned must be from the tree that corresponds to the character's bending style
                //if style is 0 pick tree1
                if(style == 0){
                    //pick a random node on tree1
                    int randNode = (int)(Math.random()*(((tree1Size-1)-0)+1))+0;
                    //represents the amount of sp used before the loop
                    int spUsedBeforeLoop = spUsed;
                    //do until a skill point is used
                    do{
                        //the randomly chosen node
                        Node node = tree1.nodes[randNode];
                        //if the randomly chosen node has no parents
                        //add a skill point to that node and add one to spUsed
                        if(node.parent1 == null && node.parent2 == null){
                            tree1.nodes[randNode].addSkillPoint();
                            spUsed++;
                        }
                        //if the node has at least one parent
                        //pick a new random node
                        else{
                            randNode = (int)(Math.random()*(((tree1Size-1)-0)+1))+0;
                        }
                    }while(spUsedBeforeLoop == spUsed);
                }
                //if style is 1 pick tree2
                else {
                    //pick a random node on tree2
                    int randNode = (int)(Math.random()*(((tree2Size-1)-0)+1))+0;
                    //represents the amount of sp used before the loop
                    int spUsedBeforeLoop = spUsed;
                    //do until a skill point is used
                    do{
                        //the randomly chosen node
                        Node node = tree2.nodes[randNode];
                        //if the randomly chosen node has no parents
                        //add a skill point to that node and add one to spUsed
                        if(node.parent1 == null && node.parent2 == null){
                            tree2.nodes[randNode].addSkillPoint();
                            spUsed++;
                        }
                        //if the node has at least one parent
                        //pick a new random node
                        else{
                            randNode = (int)(Math.random()*(((tree2Size-1)-0)+1))+0;
                        }
                    }while(spUsedBeforeLoop == spUsed);
                }
            }
            //if this is not the first skill point spent
            else{
                //pick a random tree
                int tree = (int)(Math.random()*((1-0)+1))+0;
                //if the first tree is chosen
                if(tree == 0){
                    //generate a random node on the tree
                    int randNode = (int)(Math.random()*(((tree1Size-1)-0)+1))+0;
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
                            randNode = (int)(Math.random()*(((tree1Size-1)-0)+1))+0;
                        }
                    }while(spUsedBeforeLoop == spUsed);
                }
                //if the second tree is chosen
                else{
                    //generate a random node on the tree
                    int randNode = (int)(Math.random()*(((tree2Size-1)-0)+1))+0;
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
                            randNode = (int)(Math.random()*(((tree2Size-1)-0)+1))+0;
                        }
                    }while(spUsedBeforeLoop == spUsed);
                }
            }

        }

    }
}
