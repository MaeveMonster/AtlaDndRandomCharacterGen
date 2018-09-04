import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        WaterCharacter water = new WaterCharacter(0, 10);
        water.genRandomStats();
        water.printStats();

        /*Scanner scanner = new Scanner(System.in);
        int element;
        int level;
        int gender;

        System.out.println("Welcome to Avater the Last Airbender Dnd Random Character Generator!\n" +
                            "Please Have a character sheet ready to write down your stats, and a rule sheet " +
                            "handy to understand them!");
        System.out.print("Please enter the element of your character!\n" +
                         "Enter 0 for Airbender\n" +
                         "Enter 1 for Waterbender\n" +
                         "Enter 2 for Earthbender\n" +
                         "Enter 3 for Firebender\n" +
                         "Element: ");
        //error checks user input
        while(!scanner.hasNextInt() || (scanner.nextInt() < 0 || scanner.nextInt() > 3)){
            if(!scanner.hasNextInt()){
                System.out.print("\nThat's not an integer. Try again: ");
                scanner.next();
            }
            else if(scanner.nextInt() < 0 || scanner.nextInt() > 3){
                System.out.print("\nThat number is outside of the accepted range. Try again: ");
                scanner.next();
            }
        }
        element = scanner.nextInt();

        System.out.print("\nGreat! Now enter the level of your character! It must be between 1 and 20, inclusive.\n" +
                         "Level: ");
        //error checks user input
        while(!scanner.hasNextInt() || (scanner.nextInt() < 1 || scanner.nextInt() > 20)){
            if(!scanner.hasNextInt()){
                System.out.print("\nThat's not an integer. Try again: ");
                scanner.next();
            }
            else{
                System.out.print("\nThat number is outside of the accepted range. Try again: ");
                scanner.next();
            }
        }
        level = scanner.nextInt();

        System.out.print("\nGreat! Now enter the gender of your character.\n" +
                         "Enter 0 for male.\n" +
                         "Enter 1 for female.\n" +
                         "Enter 2 for both/neither.\n" +
                         "Gender: ");
        //error checks user input
        while(!scanner.hasNextInt() || (scanner.nextInt() < 0 || scanner.nextInt() > 2)){
            if(!scanner.hasNextInt()){
                System.out.print("\nThat's not an integer. Try again: ");
                scanner.next();
            }
            else{
                System.out.print("\nThat number is outside of the accepted range. Try again: ");
                scanner.next();
            }
        }
        gender = scanner.nextInt();

        //creates a random character based on user input and calls genRandomStats
        if(element == 0){
            AirCharacter air = new AirCharacter(gender, level);
            air.genRandomStats();
            air.printStats();
        }
        else if(element == 1){
            WaterCharacter water = new WaterCharacter(gender, level);
            water.genRandomStats();
            water.printStats();
        }
        else if(element == 2){
            EarthCharacter earth = new EarthCharacter(gender, level);
            earth.genRandomStats();
        }
        else{
            FireCharacter fire = new FireCharacter(gender, level);
            fire.genRandomStats();
        }*/





    }

}
