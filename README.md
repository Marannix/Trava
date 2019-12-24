# Trava

 ### Android Architecture Components and libraries used

```
- MVVM
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

| Dashboard (Top Cities) | Belfast (Top City)|
|---|---|
|![WhatsApp Image 2019-12-24 at 14 03 19 (2)](https://user-images.githubusercontent.com/15348446/71416320-d07bb580-2657-11ea-9076-3afcfb4908cd.jpeg)|![WhatsApp Image 2019-12-24 at 14 14 53](https://user-images.githubusercontent.com/15348446/71416322-d1144c00-2657-11ea-9c87-76dcbbfa9d04.jpeg)|

You use the search bar to find more cities.

| Filtering | Recommended Venues |
|---|---|
|![WhatsApp Image 2019-12-24 at 14 11 03](https://user-images.githubusercontent.com/15348446/71416159-3fa4da00-2657-11ea-8ad5-9c9d1f91ad6b.jpeg)|![WhatsApp Image 2019-12-24 at 14 11 03 (1)](https://user-images.githubusercontent.com/15348446/71416161-40d60700-2657-11ea-810e-74dd616e3c5d.jpeg)|

The user can scroll down and see more recommended venues.

| Oxford Venue | Scrolling down|
|---|---|
|![WhatsApp Image 2019-12-24 at 14 03 19](https://user-images.githubusercontent.com/15348446/71416258-93afbe80-2657-11ea-9471-42bfa690e289.jpeg)|![WhatsApp Image 2019-12-24 at 14 03 19 (1)](https://user-images.githubusercontent.com/15348446/71416260-94e0eb80-2657-11ea-9db8-563fa2c4b7a0.jpeg)|

I've added an generic error screen when the device goes offline (or any error occurs), this can be triggered by clicking on a city while the device is offline. I added a restart button which can be pressed to retrieve the recommended places when the devices goes back online.

| Loading  Screen | Error Screen |
|---|---|
|![WhatsApp Image 2019-12-24 at 14 03 12](https://user-images.githubusercontent.com/15348446/71416058-c7d6af80-2656-11ea-960b-6e8ad2fd62e9.jpeg)|![WhatsApp Image 2019-12-24 at 14 07 33](https://user-images.githubusercontent.com/15348446/71416059-c86f4600-2656-11ea-97dc-f7826e83f796.jpeg)|

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

The selected city is stored inside the shared preference where it can be retrieved by the venue fragment.

A loading spinner was added to indicate network calling so the user is aware something is happenning in the background.

I then created test regarding the data and view of the venues

- Bug fixes and improvement of the UI were added in between features and after.

- I didn't add database however I could had.
- If I did add the database, I would use Room and store the city alongside the venues inside. 
- The screen isn't optimised for tablet (I would had used a guideline to handle the width of the screen 
