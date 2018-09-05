public class FireCharacter extends Character{

    //firebending culture is based on Japanese culture, so these are Japanese names
    final String[] FEMALE_NAMES = {"Andrea", "Kaiya", "Mao", "Naoko", "Nariko", "Natsuko", "Nishi",
            "Noriko", "Nozomi", "Rei", "Sachi", "Tamiko", "Umeko", "Yoko",
            "Yoshiko", "Yuri", "Aika", "Aimi", "Akane", "Fumiko", "Haruka", "Mari",
            "Megu", "Chiyoko", "Chizuru", "Koharu"};

    final String[] UNISEX_NAMES = {"Aki", "Akiro", "Aoi", "Haru", "Hikaru", "Hinata",
            "Hiro", "Hisoka", "Jun", "Kaede", "Kaoru", "Kin", "Kohaku", "Kyo",
            "Makoto", "Masa", "Masumi", "Michi", "Minori", "Mitsuru", "Nao",
            "Natsu", "Ren", "Shinobu", "Seiko", "Sora"};

    final String[] MALE_NAMES = {"Aito", "Akito", "Daichi", "Daisuke", "Eiji", "Fumihiro", "Giichi",
            "Hibiki", "Itsuo", "Katsumi", "Kazuto", "Kouichi", "Mitsue", "Natsuo", "Noritaka", "Naozumi",
            "Raiden", "Reiji", "Saburo", "Takahiro", "Tatsuya", "Tetsu", "Toshiro", "Tsubasa", "Yosuke",
            "Yasahiro"};

    public FireCharacter(){

    }

    public FireCharacter(int g, int l){

        super(g, l);

        //firebenders have a movement speed of 30ft per turn
        speed = 30;

        //picks a random name based on gender
        if(gender == 0){
            name = MALE_NAMES[(int)(Math.random()*((25 - 0) +1))+0];
        }
        if(gender == 1){
            name = FEMALE_NAMES[(int)(Math.random()*((25 - 0) +1))+0];
        }
        if(gender == 2){
            name = UNISEX_NAMES[(int)(Math.random()*((23 - 0) +1))+0];
        }

        name += " of the Fire Nation";

        //firebending styes are Aggressive Firebending and Controlled Firebending
        //creates skill trees to hold bending skills
        tree1 = new SkillTree("Aggressive Firebending", 6);
        tree2 = new SkillTree("Controlled Firebending", 7);

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

        int abilityScores[] = genRandomAbilityScores();
        abilityScores = levelUpAbilityScores(abilityScores, level);
        //firebenders have +1 strength, +1 dexterity, and +1 intelligence
        abilityScores[0]++;
        abilityScores[1]++;
        abilityScores[3]++;
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

        System.out.println("Saving Throws");
        //firebenders are proficient in strength saving throwa
        System.out.println("Strength saving throw: " + (sMod + profBonus));
        //firebenders are proficient in dexterity saving throws
        System.out.println("Dexterity saving throw: " + (dMod + profBonus));
        System.out.println("Constitution saving throw: " + coMod);
        System.out.println("Intelligence saving throw: " + iMod);
        System.out.println("Wisdom saving throw: " + wMod);
        System.out.println("Charisma saving throw: " + chMod + "\n");

        //chooses 2 skills out of the possible 6 in which a firebender may be proficient as indicated by the rules
        int prof1 = (int)(Math.random()*((4-0)+1))+0;
        int prof2;
        do{

            prof2 = (int)(Math.random()*((5-0)+1))+0;

        }while(prof2 == prof1);

        String profSkills = "Skills: ";
        System.out.println("Skills");
        if(prof1 == 0 || prof2 == 0){

            System.out.println("Acrobatics: " + (dMod + profBonus));
            profSkills += "Acrobatics ";
        }else{
            System.out.println("Acrobatics: " + dMod);
        }
        System.out.println("Animal Handling: " + wMod);
        if(prof1 == 1 || prof2 == 1){

            System.out.println("Athletics: " + (sMod + profBonus));
            profSkills += "Athletics ";
            profSkills += "Athletics ";
        }else{
            System.out.println("Athletics: " + sMod);
        }
        if(prof1 == 2 || prof2 == 2){

            System.out.println("Deception: " + (chMod + profBonus));
            profSkills += "Deception ";
        }else{
            System.out.println("Deception: " + chMod);
        }
        System.out.println("History: " + iMod);
        if(prof1 == 3 || prof2 == 3){

            System.out.println("Insight: " + (wMod + profBonus));
            profSkills += "Insight ";

        }else{
            System.out.println("Insight: " + wMod);
        }
        if(prof1 == 4 || prof2 == 4){

            System.out.println("Intimidation: " + (chMod + profBonus));
            profSkills += "Intimidation ";

        }else{
            System.out.println("Intimidation: " + chMod);
        }
        System.out.println("Investigation: " + iMod);
        System.out.println("Medicine: " + wMod);
        System.out.println("Nature: " + iMod);
        if(prof1 == 5 || prof2 == 5){

            System.out.println("Perception: " + (wMod + profBonus));
            profSkills += "Perception ";

        }else{
            System.out.println("Perception: " + wMod);
        }
        System.out.println("Performance: " + chMod);
        System.out.println("Persuasion: " + chMod);
        System.out.println("Slight of Hand: " + dMod);
        System.out.println("Stealth: " + dMod);
        System.out.println("Survival: " + wMod + "\n");

        profSkills += "(both already included in above calculations)\n";

        System.out.println("Because you are an Firebender...");
        System.out.println("Strength +1 (already included in above calculations)");
        System.out.println("Dexterity +1 (already included in above calculations)");
        System.out.println("Intelligence +1 (already included in above calculations)");
        System.out.println("Firey Resistance: Resistance to burning damage\n");

        System.out.println("Proficiencies");
        System.out.println("Armor: Light armor, medium armor");
        System.out.println("Saving Throws: Strength Dexterity (both already included in above calculations)");

        System.out.println(profSkills);

    }

}
