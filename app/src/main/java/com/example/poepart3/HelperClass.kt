package com.example.poepart3

import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private var arrBooks =  ArrayList<BookClass>()
private var arrCategories = ArrayList<CategoryClass>()
private var arrAwards = ArrayList<AwardsClass>()
private var arrUsers =  ArrayList<Users>()


class HelperClass {
    //adding a user
    fun AddUser(Username :String, Email :String,Password:String) // Adding user to array
    {

        arrUsers.add(Users(Username, Email, Password))

        val database = Firebase.database
        val myRef = database.getReference("users")

        val ud = Users(Username,
            Email, Password)
        myRef.child(Username).setValue(ud)


    }
    fun getUsers(){
//        arrUsers.clear()
//        val database = Firebase.database
//        val myRef = database.getReference("users")
//
//        myRef.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists()){
//                    for (empSnap in snapshot.children){
//                        val empdata = empSnap.getValue(Users::class.java)
//                        val obj: Users
//                        obj = empdata!!
//                        arrUsers.add(obj)
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
    }



    fun checkUser(Username :String,Password:String):Boolean // checking user login
    {
        var tempArr = arrUsers

        for (i in 0..tempArr.size - 1) {
            if ((tempArr[i].Password.equals(Password)) && (tempArr[i].Username.equals(Username))) {
                return true

            }
        }
        return false

    }

    //adding a category
    fun AddCategory(categoryID: Int, Name :String,goal:Int, image: Int) // Adding user to array
    {
        arrCategories.add(CategoryClass(categoryID,Name, goal, image))
    }
    //adding a book
    fun AddBook(Bookid: Int, title: String,aquisDate: String, author: String, price: Int, series: String,  rating: Int, image: Int, categoryID: Int, read: Boolean,fav: Boolean) // Adding user to array
    {
        arrBooks.add(BookClass(Bookid, title, aquisDate, author, price, series, rating, image, categoryID, read, fav))
    }

    fun AddAwardsUser(Starter: Boolean, Collector: Boolean, Packrat: Boolean){
        arrAwards.add(AwardsClass(starter = Starter, collector = Collector, packrat = Packrat))
    }

    fun UpdateAwardsUser(Starter: Boolean, Collector: Boolean, Packrat: Boolean){
        arrAwards.set(0,AwardsClass(starter = Starter, collector = Collector, packrat = Packrat))

    }

    fun getCategoriesArray(): ArrayList<CategoryClass>{
        return arrCategories
    }

    fun getSelectedCategoriesObject(position: Int): CategoryClass{

        return arrCategories[position]
    }

    fun getBooksArray(): ArrayList<BookClass>{
        return arrBooks
    }

    fun getBooksArrray(): ArrayList<AwardsClass>{
        return arrAwards
    }

    fun getUsersArrray(): ArrayList<Users>{
        return arrUsers
    }

    fun setCategoriesArray(newArray: ArrayList<CategoryClass>){
        arrCategories = newArray
    }

    fun setBooksArray(newArray: ArrayList<BookClass>){
        arrBooks = newArray
    }

    fun DeleteCategory(ID : Int)
    {
        arrCategories.removeAt(ID-1)
    }

    fun DeleteBook(ID : Int)
    {
        arrBooks.removeAt(ID-1)
    }

    fun updateCategory(ID: Int, Name :String,goal:Int, image: Int)
    {
        arrCategories.set(ID-1, CategoryClass(  CatID = ID, CatName = Name, CatGoal = goal, CatImage = image))

    }
    fun updateBook(BookID: Int, title: String,aquisDate: String, author: String, price: Int, series: String,  rating: Int, image: Int, categoryID: Int, read: Boolean,fav: Boolean) // Adding user to array
    {
        arrBooks.set(BookID-1, BookClass(bookid = BookID, Title = title, AquisDate = aquisDate, Author = author, Price = price, Series = series
            , Rating = rating, BookImage = image, CategoryID = categoryID, Read = read, Favourite = fav))
    }

    fun getNumBooksOfCategory(catid: Int): Int
    {
        var tempArr = ArrayList<BookClass>()
        for (i in 0..arrBooks.size-1 )
        {
            if(arrBooks[i].CategoryID == catid){
                tempArr.add(arrBooks[i])
            }
        }
        return tempArr.size


    }


    fun getCateLength(): Int{
        return arrCategories.size
    }

    fun getBookLength(): Int{
        return arrBooks.size
    }

    fun convertToInt(value: String) :Int{
        var convertedValue: Int = 0
        if(value != null){
            convertedValue = value.toInt()
            return  convertedValue
        }
        return 0;

    }


    }