public class AirCharacter extends Character{

    //airbending culture is based off of Tibetan culture, so all airbender names are Tibetan
    //most Tibetan names are genderneutral, so there is no male or female category
    final String[] NAMES = {"Adhe", "Amchila", "Amdo", "Chenrezig", "Chetsang", "Dadul", "Geshe",
            "Jetrung", "Kapsho", "Khenpo", "Lhamo", "Lotse", "Milarepa", "Namgang", "Norbu", "Pasang",
            "Pemba", "Rimshi", "Salden", "Sengi", "Takla", "Tenzing", "Tsetrung", "Wangdu", "Yangkyi",
            "Zachoeje"};

    public AirCharacter(){

    }

    //airbender generation does not require a gender because all airbender names are genderneutral
    public AirCharacter(int g, int l){

        super(g, l);

        //airbenders have a speed of 30ft per turn
        speed = 30;

        //picks a random name from the list
        name = NAMES[(int)(Math.random()*((23 - 0) +1))+0];

        name += " of the Air Nomads";

        //airbending styles are Defensive Airbending and Offensive Airbending
        tree1 = new SkillTree("Defensive Airbending", 7);
        tree2 = new SkillTree("Offensive Airbending", 8);

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

        int abilityScores[] = genRandomAbilityScores();
        abilityScores = levelUpAbilityScores(abilityScores, level);
        //airbenders have +2 dexterity and +2 wisdom
        abilityScores[1] += 2;
        abilityScores[4] += 2;
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
            System.out.println("Bending Bonus: " + dMod + "\n");
            System.out.println("Bending saving DC: " + (8 + profBonus + dMod) + "\n");
        }else{
            System.out.println("Bending Bonus: " + wMod + "\n");
            System.out.println("Bending saving DC: " + (8 + profBonus + wMod) + "\n");
        }

        System.out.println("Proficiency Bonus: " + profBonus + "\n");

        System.out.println("Saving Throws");
        System.out.println("Strength saving throw: " + sMod);
        //airbenders are proficient in dexterity saving throws
        System.out.println("Dexterity saving throw: " + (dMod + profBonus));
        System.out.println("Constitution saving throw: " + (coMod));
        System.out.println("Intelligence saving throw: " + iMod);
        //airbenders are proficient in wisdom saving throws
        System.out.println("Wisdom saving throw: " + (wMod + profBonus));
        System.out.println("Charisma saving throw: " + (chMod) + "\n");

        //chooses 2 skills out of the possible 6 in which a airbender may be proficient as indicated by the rules
        int prof1 = (int)(Math.random()*((5-0)+1))+0;
        int prof2;
        do{

            prof2 = (int)(Math.random()*((5-0)+1))+0;

        }while(prof2 == prof1);

        String profSkills = "Skills: ";
        System.out.println("Skills");
        System.out.println("Acrobatics: " + (dMod + profBonus));
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
        if(prof1 == 2 || prof2 == 2){
            System.out.println("History: " + (iMod + profBonus));
            profSkills += "History ";
        }else{
            System.out.println("History: " + iMod);
        }
        if(prof1 == 3 || prof2 == 3){
            System.out.println("Insight: " + (wMod + profBonus));
            profSkills += "Insight ";
        }else{
            System.out.println("Insight: " + wMod);
        }
        System.out.println("Intimidation: " + chMod);
        System.out.println("Investigation: " + iMod);
        System.out.println("Medicine: " + wMod);
        System.out.println("Nature: " + iMod);
        if(prof1 == 4 || prof2 == 4){
            System.out.println("Perception: " + (wMod + profBonus));
            profSkills += "Perception ";
        }else{
            System.out.println("Perception: " + wMod);
        }
        System.out.println("Performance: " + chMod);
        System.out.println("Persuasion: " + chMod);
        if(prof1 == 5 || prof2 == 5){
            System.out.println("Slight of Hand: " + (dMod + profBonus));
            profSkills += "Slight of Hand ";
        }else{
            System.out.println("Slight of Hand: " + dMod);
        }
        System.out.println("Stealth: " + (dMod + profBonus));
        System.out.println("Survival: " + wMod + "\n");

        profSkills += "(both already included in above calculations)\n";

        System.out.println("Because you are an Airbender...");
        System.out.println("Dexterity +2 (already included in above calculations)");
        System.out.println("Wisdom +2 (already included in above calculations)");
        System.out.println("Light Footed: Proficiency in Acrobatics and Stealth (both already included in above calculations)\n");

        System.out.println("Proficiencies");
        System.out.println("Armor: none");
        System.out.println("Saving Throws: Dexterity Wisdom (both already included in above calculations)");

        System.out.println(profSkills + "\n");

    }

}
