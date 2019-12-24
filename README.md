# Trava

 ### Android Architecture Components and libraries used

```
- Dagger
- Retrofit
- RxJava 
- Lottie
- Mockito (Testing)
- Material
```

### What does this project do?

This application shows the recommended venues in a location within United Kingdom. The list of cities available can be found in the string resources, under *cities_array* 

I've added a few fake top cities (copied from Premier Inn) which can be selected to display a list of recommended venues in those areas.

//Screenshot  // Recommended Places

You use the search bar to find more cities.

// Screenshot // Recommended Places

I've added an generic error screen when the device goes offline (or any error occurs), this can be triggered by clicking on a city while the device is offline. I added a restart button which can be pressed to retrieve the recommended places when the devices goes back online.

// Screenshot // Error

### What approach did I take?

To save myself time, I copied a list of cities in the United Kingdom from Wikipedia :)

I started with making adding retrofit, rxjava and the model classes to get the venues from foursquare.

After sorting out the networking, I added dagger for dependency injection. Better to get this sorted earlier in the project than half way through.

After I moved on to creating a list of cities in United Kingdom and creating a search functionality to find them inside the dashboard fragment. 

I created the UI of the cities inside the dashboard fragment, and used a filter in the recycler view to handle searching for the cities.

I created a venue fragment to display the list of recommended places after a city has been selected from the dashboard.

I created a repository to handle the retrieval of the venues.

I then created a viewmodel alongisde the data and view states to handle the venue data and the view states.

I created the UI of the venue fragment which displays the category icon, the name, address and post code of the venue.

A loading spinner was added to indicate network calling so the user is aware something is happenning in the background.

I then created test regarding the data and view of the venues

- Bug fixes and improvement of the UI were added in between features and after.

- I didn't add database however I could had.
If I did add the database, I would use Room and store the city alongside the venues inside. 
