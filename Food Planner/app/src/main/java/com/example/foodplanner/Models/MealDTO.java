package com.example.foodplanner.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity(tableName = "Meals", primaryKeys = {"id","userId"})
public class MealDTO {

    private String day;
    @NonNull
    private String userId;
    @NonNull
    @SerializedName("idMeal")
    private String id;
    @SerializedName("strMeal")
    private String name;
    @SerializedName("strArea")
    private String area;
    private String areaImageUrl;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strTags")
    private String tags;
    @SerializedName("strInstructions")
    private String instructions;
    @SerializedName("strMealThumb")
    private String imgUrl;
    @SerializedName("strYoutube")
    private String videoUrl;

    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;

    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;

    public MealDTO() {
    }

    public MealDTO(String day, String userId, String id, String name, String category, String area, String tags, String instructions, String imgUrl, String videoUrl) {
        this.day = day;
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.tags = tags;
        this.instructions = instructions;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        String subName = area.toLowerCase(Locale.ROOT);
        subName = "" + subName.charAt(0) + subName.charAt(1);
        areaImageUrl = "https://www.themealdb.com/images/icons/flags/big/64/" + subName + ".png";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
    }

    public String getAreaImageUrl() {
        String code;
        if (!name.toLowerCase().contains("unknown")) {
            code = CountryCode.getCountryCode(area);
            areaImageUrl = "https://flagsapi.com/" + code + "/shiny/64.png";
        } else
            areaImageUrl = "https://th.bing.com/th/id/OIP.EbhipA3qMGKX9kdRv8kfGQHaHa?rs=1&pid=ImgDetMain";
        return areaImageUrl;
    }

    public String getTags() {
        return tags;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public List<IngredientDTO> getIngredients() {
        List<IngredientDTO> ingredients = new ArrayList<>();

        if (strIngredient1 != null && !strIngredient1.isEmpty()) {
            IngredientDTO ingredient1 = new IngredientDTO(strIngredient1, strMeasure1);
            ingredients.add(ingredient1);
        }
        if (strIngredient2 != null && !strIngredient2.isEmpty()) {
            IngredientDTO ingredient2 = new IngredientDTO(strIngredient2, strMeasure2);
            ingredients.add(ingredient2);
        }
        if (strIngredient3 != null && !strIngredient3.isEmpty()) {
            IngredientDTO ingredient3 = new IngredientDTO(strIngredient3, strMeasure3);
            ingredients.add(ingredient3);
        }
        if (strIngredient4 != null && !strIngredient4.isEmpty()) {
            IngredientDTO ingredient4 = new IngredientDTO(strIngredient4, strMeasure4);
            ingredients.add(ingredient4);
        }
        if (strIngredient5 != null && !strIngredient5.isEmpty()) {
            IngredientDTO ingredient5 = new IngredientDTO(strIngredient5, strMeasure5);
            ingredients.add(ingredient5);
        }
        if (strIngredient6 != null && !strIngredient6.isEmpty()) {
            IngredientDTO ingredient6 = new IngredientDTO(strIngredient6, strMeasure6);
            ingredients.add(ingredient6);
        }
        if (strIngredient7 != null && !strIngredient7.isEmpty()) {
            IngredientDTO ingredient7 = new IngredientDTO(strIngredient7, strMeasure7);
            ingredients.add(ingredient7);
        }
        if (strIngredient8 != null && !strIngredient8.isEmpty()) {
            IngredientDTO ingredient8 = new IngredientDTO(strIngredient8, strMeasure8);
            ingredients.add(ingredient8);
        }
        if (strIngredient9 != null && !strIngredient9.isEmpty()) {
            IngredientDTO ingredient9 = new IngredientDTO(strIngredient9, strMeasure9);
            ingredients.add(ingredient9);
        }
        if (strIngredient10 != null && !strIngredient10.isEmpty()) {
            IngredientDTO ingredient10 = new IngredientDTO(strIngredient10, strMeasure10);
            ingredients.add(ingredient10);
        }
        if (strIngredient11 != null && !strIngredient11.isEmpty()) {
            IngredientDTO ingredient11 = new IngredientDTO(strIngredient11, strMeasure11);
            ingredients.add(ingredient11);
        }
        if (strIngredient12 != null && !strIngredient12.isEmpty()) {
            IngredientDTO ingredient12 = new IngredientDTO(strIngredient12, strMeasure12);
            ingredients.add(ingredient12);
        }
        if (strIngredient13 != null && !strIngredient13.isEmpty()) {
            IngredientDTO ingredient13 = new IngredientDTO(strIngredient13, strMeasure13);
            ingredients.add(ingredient13);
        }
        if (strIngredient14 != null && !strIngredient14.isEmpty()) {
            IngredientDTO ingredient14 = new IngredientDTO(strIngredient14, strMeasure14);
            ingredients.add(ingredient14);
        }
        if (strIngredient15 != null && !strIngredient15.isEmpty()) {
            IngredientDTO ingredient15 = new IngredientDTO(strIngredient15, strMeasure15);
            ingredients.add(ingredient15);
        }
        if (strIngredient16 != null && !strIngredient16.isEmpty()) {
            IngredientDTO ingredient16 = new IngredientDTO(strIngredient16, strMeasure16);
            ingredients.add(ingredient16);
        }
        if (strIngredient17 != null && !strIngredient17.isEmpty()) {
            IngredientDTO ingredient17 = new IngredientDTO(strIngredient17, strMeasure17);
            ingredients.add(ingredient17);
        }
        if (strIngredient18 != null && !strIngredient18.isEmpty()) {
            IngredientDTO ingredient18 = new IngredientDTO(strIngredient18, strMeasure18);
            ingredients.add(ingredient18);
        }
        if (strIngredient19 != null && !strIngredient19.isEmpty()) {
            IngredientDTO ingredient19 = new IngredientDTO(strIngredient19, strMeasure19);
            ingredients.add(ingredient19);
        }
        if (strIngredient20 != null && !strIngredient20.isEmpty()) {
            IngredientDTO ingredient20 = new IngredientDTO(strIngredient20, strMeasure20);
            ingredients.add(ingredient20);
        }
        return ingredients;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAreaImageUrl(String areaImageUrl) {
        this.areaImageUrl = areaImageUrl;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public void setStrMeasure16(String strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public void setStrMeasure17(String strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public void setStrMeasure18(String strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public void setStrMeasure19(String strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public void setStrMeasure20(String strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}


