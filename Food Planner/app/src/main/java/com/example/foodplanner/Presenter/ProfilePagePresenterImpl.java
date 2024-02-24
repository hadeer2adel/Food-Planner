package com.example.foodplanner.Presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.foodplanner.Controller.ProfilePageView;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.HelperClasses.PreferenceManager;
import com.example.foodplanner.Listeners.OnMessageListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePagePresenterImpl implements ProfilePagePresenter {
    private Repository repository;
    private ProfilePageView view;
    private OnMessageListener message;
    private static boolean check = false;

    public ProfilePagePresenterImpl(Repository _repository, ProfilePageView _view, OnMessageListener _message){
        repository = _repository;
        view = _view;
        message = _message;
    }

    @Override
    public void deleteAllFav() {
        repository.deleteAllFav()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ()-> message.showMsg("All favourite meals deleted"),
                        error -> message.showMsg(error.getMessage())
                );
    }

    @Override
    public void deleteAllPlans() {
        repository.deleteAllDays()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ()-> message.showMsg("All Week plans deleted"),
                        error -> message.showMsg(error.getMessage())
                );
    }

    @Override
    public void storeData() {
        check = true;
        repository.getFavMeals()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> storeData2(item),
                        error -> message.showMsg(error.getMessage())
                );
    }

    private void storeData2(List<MealDTO> favMeals) {
        if(check) {
            check = false;
            String userId = UserDTO.getUser().getId();
            DocumentReference userRef = FirebaseFirestore.getInstance().collection("users").document(userId);

            Map<String, Object> mealsData = new HashMap<>();
            for (MealDTO meal : favMeals) {
                Map<String, Object> mealMap = meal.toMap();
                mealsData.put(meal.getId(), mealMap);
            }
            userRef.collection("Meals").document("meals")
                    .set(mealsData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            message.showMsg("Favorite meals successfully backed up!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            message.showMsg(e.getMessage());
                        }
                    });
        }
    }

    @Override
    public void retrieveData() {
        String userId = UserDTO.getUser().getId();
        DocumentReference userRef = FirebaseFirestore.getInstance().collection("users").document(userId);

        userRef.collection("Meals").document("meals").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Map<String, Object> favoriteMealsData = documentSnapshot.getData();
                            for (Map.Entry<String, Object> entry : favoriteMealsData.entrySet()) {
                                MealDTO meal =  new MealDTO((Map<String, Object>) entry.getValue());
                                retrieveData2(meal);
                            }
                            message.showMsg("Retrieve data successfully");
                        } else {
                            message.showMsg("No Data Yet");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        message.showMsg(e.getMessage());
                    }
                });
    }

    private void retrieveData2(MealDTO meal) {
        repository.insertMeal(meal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {},
                        error -> message.showMsg(error.getMessage())
                );
    }

    @Override
    public void logOut(Context context) {
        FirebaseAuth.getInstance().signOut();
        UserDTO.removeUser();
        PreferenceManager preferenceManager = new PreferenceManager(context);
        preferenceManager.logoutUser();
        view.showDialog();
    }
}
