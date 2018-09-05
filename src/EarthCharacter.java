public class EarthCharacter extends Character{

    //earthbending culture is based off of Chinese culture, so these are Chinese names
    final String[] FEMALE_NAMES = {"Ai", "Bai", "Bo", "Chen", "Fa", "Fen", "Guo",
            "He", "Hui", "Ju", "Lan", "Liling", "Mei", "Nuan",
            "Nuo", "Qi", "Qiu", "Rong", "Shu", "Ting", "Tu", "Wei",
            "Wen", "Xiu", "Ya", "Zhen"};

    final String[] UNISEX_NAMES = {"Ah", "Bao", "Chin", "Fan", "Fang", "Guang",
            "Hai", "Heng", "Jiao", "Jie", "Lei", "Lim", "Lin", "Ling",
            "Mu", "Ning", "Shi", "Xiang", "Yan", "Yi", "Yin",
            "Ying", "Yong", "Yun", "Zan", "Zheng"};

    final String[] MALE_NAMES = {"An", "Biming", "Chang", "Chun", "Da", "Feng", "Gang",
            "Han", "Huang", "Jian", "Kang", "Li", "Min", "Ming", "Niu", "Peng",
            "Qiang", "Ru", "Sheng", "Song", "Si", "Tai", "Wang", "Xing", "Yu",
            "Zhu"};


    public EarthCharacter(){

    }

    public EarthCharacter(int g, int l){

        super(g, l);

        //earthbenders have a speed of 25ft per turn
        speed = 25;

        //picks a random name based on the given gender
        if(gender == 0){
            name = MALE_NAMES[(int)(Math.random()*((25 - 0) +1))+0];
        }
        if(gender == 1){
            name = FEMALE_NAMES[(int)(Math.random()*((25 - 0) +1))+0];
        }
        if(gender == 2){
            name = UNISEX_NAMES[(int)(Math.random()*((23 - 0) +1))+0];
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

        int abilityScores[] = genRandomAbilityScores();
        abilityScores = levelUpAbilityScores(abilityScores, level);
        //earthbenders have +2 strength and +2 constitution
        abilityScores[0] += 2;
        abilityScores[2] += 2;
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

        fillSkillTrees(tree1.length, tree2.length);

    }

    public void printStats(){

        super.printStats();

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

        String profSkills = "Skills: ";
        System.out.println("Skills");
        System.out.println("Acrobatics: " + dMod);
        if(prof1 == 0 || prof2 == 0){
            System.out.println("Animal Handling: " + (wMod + profBonus));
            profSkills += "Animal Handling ";
        }else{
            System.out.println("Animal Handling: " + wMod);
        }
        if(prof1 == 1 || prof2 == 1){

            System.out.println("Athletics: " + (sMod + profBonus));
            profSkills += "Athletics ";
        }else{
            System.out.println("Athletics: " + sMod);
        }
        System.out.println("Deception: " + chMod);
        System.out.println("History: " + iMod);
        System.out.println("Insight: " + wMod);
        if(prof1 == 2 || prof2 == 2){
            System.out.println("Intimidation: " + (chMod + profBonus));
            profSkills += "Intimidation ";

        }else{
            System.out.println("Intimidation: " + chMod);
        }
        System.out.println("Investigation: " + iMod);
        System.out.println("Medicine: " + wMod);
        if(prof1 == 3 || prof2 == 3){
            System.out.println("Nature: " + (iMod + profBonus));
            profSkills += "Nature ";
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
            profSkills += "Survival ";
        }else{
            System.out.println("Survival: " + wMod + "\n");
        }

        profSkills += "(both already included in above calculations)\n";

        System.out.println("Because you are an Earthbender...");
        System.out.println("Strength +2 (already included in above calculations)");
        System.out.println("Constitution +2 (already included in above calculations)");
        System.out.println("Earthen Toughness: Resistance to bludgeoning damage\n");

        System.out.println("Proficiencies");
        System.out.println("Armor: Light armor, medium armor");
        System.out.println("Saving Throws: Strength Constitution (both already included in above calculations)");

        System.out.println(profSkills);

    }

}
