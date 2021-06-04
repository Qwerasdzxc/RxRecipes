package rs.raf.projekat2.luka_petrovic_rn3318.data.model

/**
 * Created by Qwerasdzxc on 4.6.21.
 */
data class FoodCategory(
    val name: String,
    val imageUrl: String
)

val foodCategoryData: ArrayList<FoodCategory> = arrayListOf(
    FoodCategory(
        "Chicken",
        "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Chicken.png"
    ),
    FoodCategory(
        "Italian",
        "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Pizza.png"
    ),
    FoodCategory(
        "Breakfast",
        "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Breakfast.png"
    ),
    FoodCategory(
        "Barbeque",
        "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Bacon.png"
    ),
    FoodCategory(
        "Beef",
        "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Steak.png"
    ),
    FoodCategory(
        "Dinner",
        "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Indian.png"
    ),
    FoodCategory(
        "Brunch",
        "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Asian.png"
    ),
    FoodCategory(
        "Wine",
        "https://images-na.ssl-images-amazon.com/images/I/71W%2B3bONurL._AC_SX466_.jpg"
    ),
)