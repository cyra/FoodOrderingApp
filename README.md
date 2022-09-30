## Maintenance
How to add new restaurants, food items, and images into the app

### Adding a restaurant
To add a restaurant, it is required to provide a restaurant image.

#### Add a restaurant image
Requirements:
- Image file must be square, minimum 250 * 250pixels
- Image file must have an extension of either `.png`or `.jpg`
- Image file naming convention must be prefixed by `restaurant_` followed by the restaurant name. e.g. `restaurant_hungry_zaks`.❗NOTE: filename must be unique

1. Navigate to `app/src/main/res/drawable`
2. Add in the image file in the drawable directory

#### Adding the restaurant to the database
1. In the app’s main folder open the file `model/restaurant/RestaurantsList.java`
2. Scroll to the bottom of the class, and inside the `addAll()` method, create a new restaurant item.
   The format is as follows:
   ```java
   this.addRestaurant(new Restaurant(getSize(), "Restaurant Name", "restaurant_img"));
   ```
   Where:
    -  `RESTAURANTNAME` is a data type String, name of the restaurant
    -  `restaurant_img` is the filename, minus the file extension.

### Adding a food item
To add a food item, it is required that the restaurant has been added, and that the food item has an image associated with it in the drawables folder

#### Add a food item image
Requirements:
- Image file must be square, minimum 250 * 250pixels
- Image file must have an extension of either `.png`or `.jpg`
- Image file naming convention must be prefixed by `food` followed by the food name. e.g. `food_baconator`. ❗NOTE: filename must be unique

1. Navigate to `app/src/main/res/drawable`
2. Add in the image file in the drawable directory


#### Adding a food item to the database
1. In the app’s main folder open the file `model/fooditem/FoodItemList.java`
2. Scroll to the bottom of the class, and inside the `addAll()` method, create a new food item.
   The format is as follows:
   ```java
   // Restaurant Name
   this.addFoodItem(new FoodItem(getSize(), "Food Name", "Food Description", 0.0, "food_img", RESTAURANT_ID));
   ```

   Where:
    - `Food Name` is a data type String, name of the food item
    - `Food Description` is a data type String, description of the food item
    - `0.0` is of data type double, price of a single food item
    -  `food_img` is the filename, minus the file extension.
    - `RESTAURANT_ID` is the name of the restaurant the food item belongs to, prefixed with `_ID_`
3. At the top of the `addAll()` method, add the int that ill be linked to the restaurant ID
```java
final int RESTAURANT_ID  = 10;
```
Where:
- `RESTAURANT_ID` is the restaurant ID that was added in point 2.
- `10` is the succession of restaurants ID integers in the database. For each restaurant, increment this value by one.

