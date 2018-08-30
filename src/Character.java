public class Character {

    String name;
    int gender;
    int level;
    int maxBP;
    int strength;
    int smod;
    int dex;
    int dmod;
    int constitution;
    int comod;
    int intelligence;
    int imod;
    int wisdom;
    int wmod;
    int charisma;
    int chmod;
    int maxHitPoints;
    int hitDice;
    int profBonus;
    int speed;
    int style;
    int skillPoints;
    SkillTree tree1;
    SkillTree tree2;

    public Character(){
        name = "name";
        gender = 0;
        level = 1;
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
    }

    public Character(int g, int l){

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

    }

    public void genRandomStats(){

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

        System.out.println("Bending Bonus: depends on element\n");

        System.out.println("Proficiency Bonus: " + profBonus + "\n");

        System.out.println("Saving Throws");
        System.out.println("Strength saving throw: " + smod);
        System.out.println("Dexterity saving throw: " + dmod);
        System.out.println("Constitution saving throw: " + comod);
        System.out.println("Intelligence saving throw: " + imod);
        System.out.println("Wisdom saving throw: " + wmod);
        System.out.println("Charisma saving throw: " + chmod + "\n");

        System.out.println("Skills");
        System.out.println("Acrobatics: " + dmod);
        System.out.println("Animal Handling: " + wmod);
        System.out.println("Athletics: " + smod);
        System.out.println("Deception: " + chmod);
        System.out.println("History: " + imod);
        System.out.println("Insight: " + wmod);
        System.out.println("Intimidation: " + chmod);
        System.out.println("Investigation: " + imod);
        System.out.println("Medicine: " + wmod);
        System.out.println("Nature: " + imod);
        System.out.println("Perception: " + wmod);
        System.out.println("Performance: " + chmod);
        System.out.println("Persuasion: " + chmod);
        System.out.println("Slight of Hand: " + dmod);
        System.out.println("Stealth: " + dmod);
        System.out.println("Survival: " + wmod + "\n");

        System.out.println("Initiative: " + dmod + "\n");

        System.out.println("Speed: depends on element\n");

        System.out.println("Max Hit Points: " + maxHitPoints);
        System.out.println("Hit Dice: " + level);



    }
}
