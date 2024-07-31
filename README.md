# Project Abstract

JourneyGenie is an online web application that helps users plan trip itineraries. Users can sign up and log in to create travel plans to access them later. Features include adding a destination, removing a destination, analyzing ideal paths to their desired locations, suggesting nearby places, giving weather updates at selected locations, etc. While logged in, the application will automatically load all the user’s saved destinations and planned activities. This web application will serve as an educational tool to enhance user knowledge of places around the globe. <br><br><br>


## How is this problem solved currently (if at all)?

There are currently various apps that provide similar functionality, such as Google Maps and TripIt (an IOS app). They each have several features similar to the ones we have chosen, but there are still features that they do not have. <br><br>


## How will this project make life better? Is it educational or just for entertainment?

This application will function as an educational tool to help people efficiently get to different places, plan their trips, provide details on nearby places, etc. It will save people’s time by providing various features all in one application, which will allow users to get things done quickly without having to leave the website. <br><br>


## Customer

The target audience is travelers, but anyone can use the website. <br><br>


## Competitive Analysis

There are several competitors, including Google Maps, TripIt, Trivago, etc. Our project will differ from other applications due to the variety of options available to users. For example, our app suggests local places and events to help the user immerse in the local culture. The checklist tool also provides suggestions on what to bring based on their plans and the weather. <br><br>


## Does this project have many individual features or one main feature (a possibility with many subproblems)? These are the ‘epics’ of our project.

Our project has one main feature – travel planning. It contains many subproblems: adding/saving/deleting destinations, analyzing ideal routes to their locations, providing weather information, listing local places, etc. <br>

### Epic #1: Local events and places

When in a city, our app will recommend different nearby places/events using the Google Maps API. This will allow users to immerse themselves within the local area’s culture. <br>

### Epic #2: Budget Tracker

Users can keep track of their budget in multiple currencies by utilizing the Open Exchange Rates API. This provides them with a better understanding of prices in various countries and also allows them to handle their expenses. <br>

### Epic #3: Destination recommendations

This feature suggests trips based on the user's interests, budget, and preferred travel date. It will use the TripAdvisor API to allow for simple and quick recommendations on hotels and restaurants. Users can click recommendations to mark them on the map. <br>

### Epic #4: Routing

This feature, implemented with the Google Maps API, allows users to calculate the most efficient route to get from the chosen starting point to the desired location. There will also be an option to save routes. <br>

### Epic #5: Checklist

This feature allows users to make a list of things they want to do at each location. The feature also provides weather forecasts and details on the surrounding area using the OpenWeatherMap API. <br><br>


## What are some sample stories/scenarios? For example, as a regular user of your site, what types of things can I do?

### User Story: Login, logout, registration

A user of our web application can register for an account to use our services. They have the option of making their account either a regular user or an admin. The main difference between the two types is that admins can view all users. When creating an account, if the username is already taken, they will be redirected to an error page. Users will then have to return to the registration page to create an account. After creating the account, the user can log in to access their personalized version of the site. <br><br>
If a user fails to log in, they will be redirected back to the login page. When successfully logged in, a user will then be able to find their saved trips, create new trips, etc. This can allow the user to save routes and receive suggestions based on their interests. A normal user will land on their home page when successfully logged in. From the navigation bar, users can go to their routes page. An admin will also land on their home page, but they will see a list of all created accounts. Once the user is content with what they have done, they can log out from the website and log back in when needed. <br><br>
When a user presses log out, they will be redirected to the login page. Once they log out, they will not be able to access their personalized pages. <br><br>


### User Story: Routes feature

After logging in, the routes page can be accessed by clicking a button in the navbar at the top, or via a button in the centre of the dashboard. Users can search for locations by typing the place names into the input boxes. To the right of the input boxes, above the map display, there is a row of buttons. There is a button to add another location, creating an input box below the ones already present. To the right, users can specify their mode of transportation (car, public transport, walking, etc.). The mode is set to “driving” by default. After the user selects a mode, they can click the “Display” button next to an input box to show the route from the previous location to the one selected or click the “Display Full Route” button to display a route containing all locations. The application may display alternate routes, which the user can select by clicking buttons shown above the map. If users find a route they like, they can save it by clicking on the “Save this route” button. At the bottom of the map, there is a link that, when clicked, directs users to a page displaying all their saved routes. While on this page, users can view their saved routes, or they can choose to delete them. Choosing to view a saved route will redirect them to a separate page. <br><br>


### User Story: Destination Recommendations feature

After registration, the user will be redirected to the preferences page where they can edit interests and preferences. They may return to the page at any time to make further adjustments. The user can click a button on the preferences page to generate a trip based on the interests and preferences provided. The user will be redirected to the map page where they can see all the locations on the map and modify the generated trip. On the map page, the user can click a button to add a hotel between locations. They will be provided a selection of hotels from TripAdvisor. <br><br>


### User Story: Budget Tracker feature

On the route details page for a given route the user can click on “Track Budget” which will take them to the budget tracker page where they can create a budget for their route. On this page the user can click “More” to change their “From Currency” and their “To Currency”. The user can input item names and their costs which will then converted into whatever currency they selected. They can then add this item to the list. They can also edit or remove any of the items on the list. If all the items are in the same currency then a total amount and a converted total amount will be displayed. If not then the user can click on “Match Currencies” to change the currency in the amount column and the converted amount and then they can click the “Update All Rows” button. <br><br>


### User Story: Checklist feature

When a user has a saved route and they are on the “Saved Routes” page, they can access their specific route’s checklist. In the checklist, a user will see a table of activities they have saved and a table of places they have saved. Users can choose to add more activities/places to the checklist, and they can choose to delete their activities/places. To add activities, users will be redirected to a separate page where they can add the activities. Users will also be able to add places to the checklist by going to the route details page. On the other hand, to delete an activity/place from the checklist, users will just have to press the delete button next to the activity/place they want to delete. <br><br>


### User Story: Weather feature

On the page of an individual route, users can press the “see weather” button to go to a page that allows them to see the weather forecasts of any place that they desire. They can choose to provide a city, or they can use their current location. A 5-day forecast will then be displayed, which includes the temperature, wind, and humidity for each day. If users provide an invalid place, an alert will appear that will remind them to add a valid place. <br><br>


### User Story: Local Events and Places feature

When logged in, users can view their saved routes. On the page of an individual route, users will be shown a map of nearby places/events. The red marker is where their route destination is located, and the blue markers are nearby places/events. Clicking on a blue marker will also show the name of the place. Below the map, there is a table that lists the names of the places and an option to add them to the route’s checklist. If there are no nearby places/events, the table will be empty, and nothing will happen. If a place fails to save to their checklist, they will also be notified. Otherwise, they can see their saved places in their checklist for the trip. <br><br>


### User Story: Notes feature

On the “Route Details” page, the user can see the routes they saved previously. Under the map, there is another map showing nearby places that might interest them. Additionally, there is a list of these nearby places. Below the list, the user can add notes. For each route that the user saved, there can be multiple notes and these are only accessible by that particular saved route only.  The user might have plans or activities they want to do at that location, which they can add to the notes. They might also want to list all the items they plan to carry with them, so they can add another note for that. The user can edit or delete these notes as well. <br><br><br>
