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
