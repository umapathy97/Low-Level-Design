import Service.UserService;
import model.Centre;
import model.Gender;
import model.VaccineType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Driver {
    static UserService userService = new UserService();
    public static void main(String[] args) throws IllegalAccessException {
        /*
        user1 covax,
user2 covax,
user3 covishield

center1 covax - 2
center2 covax - 1

getCenters user3
bookCenter1/2 user3
getStats
getCenters user1
bookcenter1 user1
bookcenter1 user1
bookcenter2 user1
getCenters user2

         */

        userService.registerUser("user1", VaccineType.COVAXINE, Gender.MALE);
        userService.registerUser("user2", VaccineType.COVAXINE, Gender.MALE);
        userService.registerUser("user3", VaccineType.COVIESHIELD, Gender.MALE);

        Map<VaccineType, Integer> map = new HashMap<>();
        map.put(VaccineType.COVAXINE, 2);
        userService.registerCentre("center1", map);
        map = new HashMap<>();
        map.put(VaccineType.COVAXINE, 1);
        userService.registerCentre("center2", map);
       userService.getCentres("user3");
        System.out.println(userService.bookSlot("user3", "center1"));
        userService.getCentres("user1");


        System.out.println(userService.bookSlot("user1", "center1"));
        System.out.println(userService.bookSlot("user1", "center1"));
        System.out.println(userService.bookSlot("user1", "center2"));
        userService.getCentres("user2");
        System.out.println(userService.bookSlot("user2", "center1"));
        userService.getStats();


        /*
        bookCenter1 user2
      getStats
         */








      /*  Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String s = sc.nextLine();
            switch (s) {
                case "register_user":
                    System.out.println("enter user details");
                    String userDet[] = sc.nextLine().split(" ");
                    try{
                        if (userService.registerUser(userDet[0], VaccineType.valueOf(userDet[1].toUpperCase()), Gender.valueOf(userDet[2].toUpperCase()))) {
                            System.out.println("user registered successfully");
                        }
                    }
                    catch(IllegalAccessException ex) {
                        System.out.println("user is already present");
                    }

                     break;
                case "register_center":
                    System.out.println("enter centre name");
                    String centrename = sc.nextLine();
                    System.out.println(" enter vaccineType and count");
                    String cur[] = sc.nextLine().split(" ");
                    Map<VaccineType, Integer> vaccineTypeToCentreMap = new HashMap<>();
                    String prev = "";
                    boolean toggle = false;
                    for (String c : cur) {
                        if (toggle) {
                            vaccineTypeToCentreMap.put(VaccineType.valueOf(prev.toUpperCase()), vaccineTypeToCentreMap.getOrDefault(VaccineType.valueOf(prev.toUpperCase()), 0) + Integer.parseInt(c));
                        }

                        else {
                            prev = c;
                        }

                        toggle = !toggle;

                    }
                    try {
                        if (userService.registerCentre(centrename, vaccineTypeToCentreMap))
                            System.out.println(" centre registered successfully");
                    }

                    catch(IllegalAccessException ex) {
                        System.out.println("centre is already booked");
                    }
                    break;


                case "get_centre":
                    String user = sc.nextLine();

                    try {
                        List<Centre> centreList = userService.getCentres(user);
                        for (Centre centre : centreList) {
                            System.out.println(centre.getCentreName() + "count " + centre.getVaccineTypeToCountMap());
                        }

                    }

                    catch(IllegalAccessException ex) {
                        System.out.println("no user with that id present");
                    }
                    break;

                case "book_slot":
                    String[] slot = sc.nextLine().split(" ");
                    if (userService.bookSlot(slot[0], slot[1]))
                        System.out.println(" booking has been done successfully");
                    else
                        System.out.println("booking couldn't be done");

                    break;
                case "get_stats":
                    userService.getStats();
                    break;
                case "exit":
                    exit = true;
                    break;


            }


        }
        */
    }
}
/*
Description
Flipkart is creating an app to allow people to register for vaccinations. In this app, centres can register themselves with the vaccine details. Users can register and make a booking for vaccination.

Features
Vaccinations take place in a centre
There can be multiple centres
Each vaccination center has some number of vaccines available
Of these two types:
    COVAX
COVISHIELD
User cannot make more than one booking
With Every Successful booking reduce the inventory
Users need to be uniquely identified

Requirements
Register a User:
register_user(user_details)
user_details: name, gender, preferred vaccineType
Register a centre:
register_centre(centre_details)
Centre_details: centreId, vaccineInventory
vaccineInventory: vaccine_tye x quantity

      3. getCentres(user)
Rank and Display the Centres which has user’s preferred vaccine available
Higher preference to centre with higher quantity of remaining vaccine
If Remaining vaccines are same, higher preference to centre with less number of bookings made so far
If this is also same, print the selections in any order

     4.bookslot(user, centerId)
Book a slot and return success if booking can be made
If booking cannot be made return failure

     5. getStats()
Display names of people who have registered and number of available vaccines for each centre with Centre name

Bonus
1. Introduce 3 slots(morning, afternoon, evening), There is a max-capacity per slot for each center which will be followed while booking.

Other Details:
Do not use any database or NoSQL store, use in-memory store for now.
Do not create any UI for the application.
Write a driver class for demo purposes. Which will execute all the commands at one place in the code and test cases.
Please prioritize code compilation, execution and completion.
Please do not access the internet for anything EXCEPT syntax.
You are free to use the language of your choice.
All work should be your own. If found otherwise, you may be disqualified.
Expectations:
Code should be demoable (very important)
Complete coding within the duration of 90 minutes.
Code should handle edge cases properly and fail gracefully.
Code should be readable. Follow good coding practices:
Use intuitive variable names, function names, class names etc.
Indent code properly.

Sample Test Case:
User1 - Covax
User2 - Covishield
Center1 - Covishield - 5, Covax - 1
Center2 - Covax - 10


getCentres(User1)

Center2  Covax-10
Center1  Covax - 1

bookslot(User2, Center2) : false
bookslot(User1, Center1) : true

getStats()

Center1:
    Covax 0 Covishield 5
            User1
Center2:
Covax 10



Bonus:

User 3 : covax
Center1 - Covishield - 5, Covax - 2, maxcapacityperslot -1
Center2 - Covax - 10, maxcapacityperslot - 4


bookSlot(User1, Center1, Morning) : true
bookslot(User3, Center1, Morning) : false
bookslot(User3, Center1, Evening) : true

 */
