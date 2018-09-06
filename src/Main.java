import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int element;
        int level;
        int gender;

        System.out.println("Welcome to Avater the Last Airbender Dnd Random Character Generator!\n" +
                            "Please Have a character sheet ready to write down your stats, and a rule sheet " +
                            "handy to understand them!");
        System.out.println("Please enter the element of your character!\n" +
                         "Enter 0 for Airbender\n" +
                         "Enter 1 for Waterbender\n" +
                         "Enter 2 for Earthbender\n" +
                         "Enter 3 for Firebender");
        //error checks user input
        element = -1;
        do{
            System.out.print("Make sure your input is within the accepted range of integers.\nElement:  ");
            if(scanner.hasNextInt()){
                element = scanner.nextInt();
            }
            else{
                System.out.println("That's not an integer. Try again.");
                scanner.next();
            }

        }while(element < 0 || element > 3);

        System.out.println("\nGreat! Now enter the level of your character! It must be between 1 and 20, inclusive.");
        //error checks user input
        level = -1;
        do{
            System.out.print("Make sure your input is within the accepted range of integers.\nLevel:  ");
            if(scanner.hasNextInt()){
                level = scanner.nextInt();
            }
            else{
                System.out.println("That's not an integer. Try again.");
                scanner.next();
            }

        }while(level < 0 || level > 20);

        System.out.print("\nGreat! Now enter the gender of your character.\n" +
                         "Enter 0 for male.\n" +
                         "Enter 1 for female.\n" +
                         "Enter 2 for both/neither.\n");
        //error checks user input
        gender = -1;
        do{
            System.out.print("Make sure your input is within the accepted range of integers.\nGender:  ");
            if(scanner.hasNextInt()){
                gender = scanner.nextInt();
            }
            else{
                System.out.print("That's not an integer. Try again. ");
                scanner.next();
            }

        }while(gender < 0 || gender > 2);

        System.out.println("\n\n");

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
            earth.printStats();
        }
        else{
            FireCharacter fire = new FireCharacter(gender, level);
            fire.genRandomStats();
            fire.printStats();
        }





    }

}
