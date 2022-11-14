# TJ-Tasks-2022-Shabaj-Ansari

# Android Tasks


## Task 1: BMI Calculator App
### Basic Requirements:

* It should be a 4 screen application. 
* Screen 1: Login. Use Firebase’s sign in with email and password. 
* Screen 2: Register. Use Firebase’s sign up with email and password.
* After sign up, travel to screen 3.
* 3rd screen should take the following info from the user
    * Name (EditText)
    * Age (EditText)
    * Height (EditText)
    * Weight (EditText)
    * Gender (Spinner)
* There should also be a button at the bottom of the 3rd screen.
* OnClick of this button, the user must be navigated to the 4th screen.
* On the 4th screen, show the following things
    * Hi, &lt;Name>!
    * Your BMI = &lt;BMI>
    * You are in the &lt;BMI Category> category. 
* The background color of the 4th screen must depend on the gender. 
    * For example: if there are 3 genders, female, male and LGBTQ -- background color of the 4th screen should be different for female, male and LGBTQ entries. 
* There should be a button at the bottom of this page for logout -- onClick should logOut the user and move the navigation to the Login page. 

<img src="https://user-images.githubusercontent.com/53938155/142943012-8996dd9b-cb2c-4e14-8441-8770ee89c871.png">

## Task 2: Recipe App 


### Basic Requirements:



* Set Up a Firebase project with the following specs in Cloud Firestore
    * 1 collection of Recipes. 
    * Multiple documents inside this collection with details of each recipe. 
    * Each recipe has the following details. 
        * Title 
        * List of Ingredients
        * Image URL
        * Time taken to make
* Link your Android project with this Firebase project. 
* Create a recyclerview with cells showing the following: 
    * Title
    * Image 
* OnClick of any one of the cell opens the following:
    * Title of that recipe.
    * Image
    * Ingredients
    * Time taken to make it.
* OnBack takes the user back to Activity 1.
* All of the data in both these activities must be fetched from Firebase.  

<img src="https://user-images.githubusercontent.com/53938155/142943006-aa8d1ed8-a63e-4255-85e7-7307af22f484.png">


## Resources

* [How to install Android Studio?](https://developer.android.com/studio/install)
* [Connecting Firebase with Android Studio](https://www.youtube.com/watch?v=nep85PD8U7M)
* [Firebase Cloud Firestore](https://firebase.google.com/docs/firestore)
* [Recyclerviews in Android](https://www.youtube.com/watch?v=HtwDXRWjMcU)
* [Passing data between activities](https://www.youtube.com/watch?v=IWXYV1dC2FQ)
* [Firebase Authentication](https://firebase.google.com/docs/auth)
