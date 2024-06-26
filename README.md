# Project Abstract

JourneyGenie is an online web application that helps users plan trip itineraries. Users can sign up and log in to create travel plans to access them later. Features include adding a destination, removing a destination, analyzing ideal paths to their desired locations, suggesting nearby activities, giving weather updates at selected locations, etc. While logged in, the application will automatically load all the user’s saved destinations and planned activities. This web application will serve as an educational tool to enhance user knowledge of places around the globe. <br><br><br>


## How is this problem solved currently (if at all)?

There are currently various apps that provide similar functionality, such as Google Maps and TripIt (an IOS app). They each have several features similar to the ones we have chosen, but there are still features that they do not have. <br><br>


## How will this project make life better? Is it educational or just for entertainment?

This application will function as an educational tool to help people efficiently get to different places, plan their trips, provide information on nearby events, etc. It will save people’s time by providing various features all in one application, which will allow users to get things done quickly without having to leave the website. <br><br>


## Customer

The target audience is travelers, but anyone can use the website. <br><br>


## Competitive Analysis

There are several competitors, including Google Maps, TripIt, Trivago, etc. Our project will differ from other applications due to the variety of options available to users. For example, our app suggests local events and festivals to help the user immerse in the local culture. The checklist tool also provides suggestions on what to bring based on their plans and the weather. <br><br>


## Does this project have many individual features or one main feature (a possibility with many subproblems)? These are the ‘epics’ of our project.

Our project has one main feature – travel planning. It contains many subproblems: adding/saving/deleting destinations, analyzing ideal routes to their locations, providing weather information, listing local events, etc. <br>

### Epic #1: Local events and festivals

When in a city, our app will recommend different nearby events/festivals using the Eventbrite API. This will allow users to immerse themselves within the local area’s culture. <br>

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

A user of our web application can register for an account to use our services. After creating the account, the user can log in to access a personalized version of the site. They will then be able to find their saved trips, create new trips, etc. This can allow the user to save routes and receive suggestions based on their interests. Once the user is content with what they have done, they can log out from the website and back in when needed. <br>

### User Story: Routes

After logging in there will be an input box for a user to type in a destination. After it will ask the user for a mode of transportation (car, public transport, walking, etc.). The user will then get redirected to a page showing the route on a map alongside travel directions and travel time. <br>

### User Story: Weather feature

Based on the destination’s weather, there will be items such as clothing that the user will be recommended to bring. <br>
