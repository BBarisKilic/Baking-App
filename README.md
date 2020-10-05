# Baking App
The app displays a set of recipes with ingredients. In addition, this app allow the user to select a recipe and see video guided steps for how to make it.

## Screenshots from the Latest Version of The Application

### _Main Screen_
![ScreenShot](/images/Main_Screen.jpg)
### _Ingredient Screen_
![ScreenShot](/images/Ingredients.jpg)
### _Exo Player_
![ScreenShot](/images/Exo_Player.jpg)
### _Widget_
![ScreenShot](/images/Widget.jpg)

## Project Overview
You will productionize an app, taking it from a functional state to a production-ready state. This will involve finding and handling error cases, adding accessibility features, allowing for localization, adding a widget, and adding a library.

## Why this Project?
As a working Android developer, you often have to create and implement apps where you are responsible for designing and planning the steps you need to take to create a production-ready app. Unlike Popular Movies where we gave you an implementation guide, it will be up to you to figure things out for the Baking App.

## What Will I Learn?
In this project you will:

* Use MediaPlayer/Exoplayer to display videos.
* Handle error cases in Android.
* Add a widget to your app experience.
* Leverage a third-party library in your app.
* Use Fragments to create a responsive design that works on phones and tablets.

## App Description
Your task is to create a Android Baking App that will allow Udacity’s resident baker-in-chief, Miriam, to share her recipes with the world. You will create an app that will allow a user to select a recipe and see video-guided steps for how to complete it.

The recipe listing is located here.

The JSON file contains the recipes' instructions, ingredients, videos and images you will need to complete this project. Don’t assume that all steps of the recipe have a video. Some may have a video, an image, or no visual media at all.

One of the skills you will demonstrate in this project is how to handle unexpected input in your data -- professional developers often cannot expect polished JSON data when building an app.

# Rubric

### Common Project Requirements

* App is written solely in the Java Programming Language
* App utilizes stable release versions of all libraries, Gradle, and Android Studio.

### General App Usage

* App should display recipes from provided network resource.
* App should allow navigation between individual recipes and recipe steps.
* App uses RecyclerView and can handle recipe steps that include videos or images.
* App conforms to common standards found in the Android Nanodegree General Project Guidelines.

### Components and Libraries

* Application uses Master Detail Flow to display recipe steps and navigation between them.
* Application uses Exoplayer to display videos.
* Application properly initializes and releases video assets when appropriate.
* Application should properly retrieve media assets from the provided network links. It should properly handle network requests.
* Application makes use of Espresso to test aspects of the UI.
* Application sensibly utilizes a third-party library to enhance the app's features. That could be helper library to interface with ContentProviders if you choose to store the recipes, a UI binding library to avoid writing findViewById a bunch of times, or something similar.

### Homescreen Widget

* Application has a companion homescreen widget.
* Widget displays ingredient list for desired recipe.
