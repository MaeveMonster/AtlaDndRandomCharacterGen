public class WaterCharacter extends Character{

    //waterbender culture is largely based off of Inuit culture, so these are Inuit names
    private final String[] FEMALE_NAMES = {"Ahnah", "Akna", "Alasie", "Amka", "Anjij", "Aqakuktuq", "Arnaaluk",
            "Arnakuagsak", "Arnaq", "Asiaq", "Elisapiee", "Hitty", "Isapoinhkyaki", "Jissika",
            "Kanna", "Kireama", "Kirima", "Korra", "Meriwa", "Nuliajuk", "Nuvua", "Tanaraq",
            "Tapeesa", "Ticasuk", "Uki", "Yue"};

    private final String[] UNISEX_NAMES = {"Adlartok", "Alornerk", "Amaruq", "Aput", "Atiqtalaaq", "Cupun",
            "Ikiaq", "Kallik", "Kanaaq", "Nanouk", "Nanurjuk", "Nukilik", "Nuniq", "Qimmiq",
            "Siqiniq", "Taqtu", "Tehoronianhen", "Tootega", "Tukkuttok", "Tulimak", "Umiaktorvik",
            "Uukkarnit", "Yuka", "Yura"};

    private final String[] MALE_NAMES = {"Amaqjuaq", "Bato", "Desna", "Inuksuk", "Kumaglak", "Kuruk", "Nootaikok",
            "Pana", "Panuk", "Pilip", "Silla", "Sokka", "Tarkik", "Tekkeitsertok", "Tiguaak", "Toklo",
            "Tonraq", "Torngarsuk", "Tulimaq", "Tulok", "Tulugaak", "Ujurak", "Unalaq", "Yoskolo", "Yotimo",
            "Yutu"};

    public WaterCharacter(){

    }

    public WaterCharacter(int g, int l){

        super(g, l);

        //waterbenders have a movement speed of 30ft per turn
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

        name += " of the Water Tribe";

        //waterbending styles are Traditional Waterbending and Advanced Waterbending
        //creates skill trees to hold bending skills
        tree1 = new SkillTree("Traditional Waterbending", 8);
        tree2 = new SkillTree("Advanced Waterbending", 8);

        //adds nodes to the skill trees in a precise order so that parant-child relationships are correct
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

    //generates random stats for the character and prints them for the user
    public void genRandomStats(){

        int abilityScores[] = genRandomAbilityScores();
        abilityScores = levelUpAbilityScores(abilityScores, level);
        //waterbenders have +1 intelligence and +2 charisma
        abilityScores[3]++;
        abilityScores[5] += 2;
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
            System.out.println("Bending Bonus: " + chMod + "\n");
            System.out.println("Bending saving DC: " + (8 + profBonus + chMod) + "\n");
        }else{
            System.out.println("Bending Bonus: " + wMod + "\n");
            System.out.println("Bending saving DC: " + (8 + profBonus + wMod) + "\n");
        }

        System.out.println("Saving Throws");
        System.out.println("Strength saving throw: " + sMod);
        System.out.println("Dexterity saving throw: " + dMod);
        //waterbenders are proficient in constitution saving throws
        System.out.println("Constitution saving throw: " + (coMod + profBonus));
        System.out.println("Intelligence saving throw: " + iMod);
        System.out.println("Wisdom saving throw: " + wMod);
        //waterbenders are proficient in charisma saving throws
        System.out.println("Charisma saving throw: " + (chMod + profBonus) + "\n");

        //chooses 2 skills out of the possible 6 in which a waterbender may be proficient as indicated by the rules
        int prof1 = (int)(Math.random()*((5-0)+1))+0;
        int prof2;
        do{

            prof2 = (int)(Math.random()*((5-0)+1))+0;

        }while(prof2 == prof1);

        String profSkills = "Skills: ";
        System.out.println("Skills");
        System.out.println("Acrobatics: " + dMod);
        System.out.println("Animal Handling: " + wMod);
        System.out.println("Athletics: " + sMod);
        if(prof1 == 0 || prof2 == 0){

            System.out.println("Deception: " + (chMod + profBonus));
            profSkills += "Deception ";
        }else{
            System.out.println("Deception: " + chMod);
        }
        System.out.println("History: " + iMod);
        if(prof1 == 1 || prof2 == 1){
            System.out.println("Insight: " + (wMod + profBonus));
            profSkills += "Insight ";
        }else{
            System.out.println("Insight: " + wMod);
        }
        System.out.println("Intimidation: " + chMod);
        if(prof1 == 2 || prof2 == 2){
            System.out.println("Investigation: " + (iMod + profBonus));
            profSkills += "Investigation ";
        }else{
            System.out.println("Investigation: " + iMod);
        }
        if(prof1 == 3 || prof2 == 3){
            System.out.println("Medicine: " + (wMod + profBonus));
            profSkills += "Medicine ";
        }else{
            System.out.println("Medicine: " + wMod);
        }
        System.out.println("Nature: " + iMod);
        System.out.println("Perception: " + wMod);
        if(prof1 == 4 || prof2 == 4){
            System.out.println("Performance: " + (chMod + profBonus));
            profSkills += "Performance ";
        }else{
            System.out.println("Performance: " + chMod);
        }
        if(prof1 == 5 || prof2 == 5){
            System.out.println("Persuasion: " + (chMod + profBonus));
            profSkills += "Persuasion ";
        }else{
            System.out.println("Persuasion: " + chMod);
        }
        System.out.println("Slight of Hand: " + dMod);
        System.out.println("Stealth: " + dMod);
        System.out.println("Survival: " + wMod + "\n");

        profSkills += "(both already included in above calculations)\n";

        System.out.println("Because you are a Waterbender...");
        System.out.println("Intelligence +1 (already included in above calculations)");
        System.out.println("Charisma +2 (already included in above calculations)");
        System.out.println("Undying Will: Advantage on saving throws against being frightened");
        System.out.println("Arctic Skin: Resistance to cold damage\n");

        System.out.println("Proficiencies");
        System.out.println("Armor: Light armor");
        System.out.println("Saving Throws: Constitution Charisma (both already included in above calculations)");
        System.out.println(profSkills);

    }

}

