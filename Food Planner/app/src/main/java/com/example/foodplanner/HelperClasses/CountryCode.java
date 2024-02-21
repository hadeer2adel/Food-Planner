package com.example.foodplanner.HelperClasses;

import java.util.HashMap;
import java.util.Map;

public class CountryCode {

    private static final Map<String, String> nationalityToCountryCode = new HashMap<>();

    static  {
        nationalityToCountryCode.put("american", "US");
        nationalityToCountryCode.put("british", "GB");
        nationalityToCountryCode.put("canadian", "CA");
        nationalityToCountryCode.put("chinese", "CN");
        nationalityToCountryCode.put("croatian", "HR");
        nationalityToCountryCode.put("dutch", "NL");
        nationalityToCountryCode.put("egyptian", "EG");
        nationalityToCountryCode.put("filipino", "PH");
        nationalityToCountryCode.put("french", "FR");
        nationalityToCountryCode.put("greek", "GR");
        nationalityToCountryCode.put("indian", "IN");
        nationalityToCountryCode.put("irish", "IE");
        nationalityToCountryCode.put("italian", "IT");
        nationalityToCountryCode.put("jamaican", "JM");
        nationalityToCountryCode.put("japanese", "JP");
        nationalityToCountryCode.put("kenyan", "KE");
        nationalityToCountryCode.put("malaysian", "MY");
        nationalityToCountryCode.put("mexican", "MX");
        nationalityToCountryCode.put("moroccan", "MA");
        nationalityToCountryCode.put("polish", "PL");
        nationalityToCountryCode.put("portuguese", "PT");
        nationalityToCountryCode.put("russian", "RU");
        nationalityToCountryCode.put("spanish", "ES");
        nationalityToCountryCode.put("thai", "TH");
        nationalityToCountryCode.put("tunisian", "TN");
        nationalityToCountryCode.put("turkish", "TR");
        nationalityToCountryCode.put("vietnamese", "VN");
    }

    public static String getCountryCode(String nationality){
        return nationalityToCountryCode.get(nationality.toLowerCase());
    }

}
