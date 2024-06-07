package com.adn.mysealedclass

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        checkState()
//        checkAbstractAnimal()

        checkAnimal2()

//        checkDataClass()

//        val month = Months(2)
//        val january = Months.January( 1)
//        val february = Months.February()


//        checkMonth(Months.January("Jan", 1))
//        checkMonth(Months.February())
    }


//    private fun checkMonth(months: Months) {
//        when(months) {
//            is Months.January -> Log.e("anhdn", "checkMonth1:" +months.shortHand+"-"+months.number)
//            is Months.February -> Log.e("anhdn", "checkMonth2: "+months.number )
//        }
//    }

    // constructor ---------------------
//    sealed interface Result
//    class Success(val data: String) : Result
//    class Failure(val exception: Throwable) : Result


    sealed class Result
    class Success(val data: String) : Result()
    class Failure(val exception: Throwable) : Result()

//    sealed class Result {
//        class Success(val data: String) : Result()
//        class Failure(val exception: Throwable) : Result()
//    }

    fun getDataResult(result: Result) {
        val a = arrayListOf<Int>()
        val checkHash = HashMap<Int, Int>()
        when (result) {
            is Failure -> TODO()
            is Success -> TODO()
        }
    }

    // data class & object  ---------------------
//    sealed class A {
//        class B : A()
//        class C : A()
//        object D : A() {
//            fun name() {
//                Log.e("anhdn", "name: D")
//            }
//        }
//
//        data class E(var name: String) : A()
//    }
//
//    fun checkDataClass() {
//        val e = A.E("E")
//        Log.e("anhdn", "e = " + e + " -- " + e.name)
//        val d = A.D
//        d.name()
//    }

    sealed interface State {
        data object Idle : State
        data class Loading(val message: String) : State
        data class Error(val error: Throwable) : State
        data class Success(val data: Any) : State
    }

    sealed interface ExtendedState : State {
        data class DetailedError(val errorCode: Int, val errorMessage: String) : ExtendedState
    }


    private fun checkState() {
        val states: List<State> = listOf(
            State.Idle,
            State.Loading("Loading data..."),
            State.Error(Throwable("Network error")),
            State.Success("Data loaded successfully"),
            ExtendedState.DetailedError(404, "Not Found")
        )
        for (state in states) {
            handleState(state)
        }
    }

    private fun handleState(state: State) {
        when (state) {
            is State.Idle -> Log.e("anhdn", "Idle")
            is State.Loading -> Log.e("anhdn", "Loading " + state.message)
            is State.Error -> Log.e("anhdn", "Error " + state.error.message)
            is State.Success -> Log.e("anhdn", "Success " + state.data)
            is ExtendedState.DetailedError -> Log.e(
                "anhdn",
                "DetailedError " + state.errorCode + " - " + state.errorMessage
            )
        }
    }

    //-------------------------

    abstract class Animal {
        abstract fun makeSound()

        fun sleep() {
            println("Sleeping")
        }
    }

    class Dog : Animal() {
        override fun makeSound() {
            println("Woof")
        }
    }

    class Cat : Animal() {
        override fun makeSound() {
            println("Meow")
        }
    }

    private fun checkAbstractAnimal() {
        val dog: Animal = Dog()
        dog.makeSound()
        dog.sleep()

        val cat: Animal = Cat()
        cat.makeSound()
        cat.sleep()
    }



    sealed class Animal2(val name: String) {
        fun sleep() {
            println("Sleeping")
        }

        class Dog(val progress: Int) : Animal2("Dog")

        data object Cat : Animal2("cat")
    }

    private fun checkAnimal2() {
//        val dog = Animal2.Dog("Buddy")
//        dog.sleep()
//        val cat = Animal2.Cat
//        cat.sleep()
    }
    // type safety ---------------------
//    sealed class A {
//
//        class B : A()
//
//        class C : A() {
//            class E : A() //this works.
//        }
//
//        init {
//            Log.e("anhdn", "Sealed class A", )
//        }
//    }
//
//    class D : A() {     //this works
//        class F : A() { //This won't work. Since sealed class is defined in another scope.
//
//        }
//    }
//
//    fun test2() {
////        var b = A.B()
////        var c = A.C()
//        var e = A.C.E()
//        var f =D.F()
//    }

    //--------------------------
    sealed class Shape
    class Circle(val radius: Float) : Shape()
    class Rectangle(val width: Float, val height: Float) : Shape()
    data object NoShape : Shape()

    sealed class Shape1 {
        class Circle1(val radius: Float) : Shape1()
        class Rectangle1(val width: Float, val height: Float) : Shape1()
    }

    fun describeShape(shape: Shape): String {
        return when (shape) {
            is Circle -> "Circle with radius ${shape.radius}"
            is Rectangle -> "Rectangle with height ${shape.height} and width ${shape.width}"
            is Test.Oval -> "Oval"
            NoShape -> ""
        }
    }

    fun describeShape1(shape: Shape1): String {
        return when (shape) {
            is Shape1.Circle1 -> "Circle with radius ${shape.radius}"
            is Shape1.Rectangle1 -> "Rectangle with height ${shape.height} and width ${shape.width}"
        }
    }

    sealed interface Shape2
    class Circle2(val radius: Float) : Shape2
    class Rectangle2(val width: Float, val height: Float) : Shape2

    sealed interface Shape3 {
        class Circle3(val radius: Float) : Shape3
        class Rectangle3(val width: Float, val height: Float) : Shape3
    }

    fun describeShape2(shape: Shape2): String {
        return when (shape) {
            is Circle2 -> "Circle with radius ${shape.radius}"
            is Rectangle2 -> "Rectangle with height ${shape.height} and width ${shape.width}"
        }
    }

    fun describeShape3(shape: Shape3): String {
        return when (shape) {
            is Shape3.Circle3 -> "Circle with radius ${shape.radius}"
            is Shape3.Rectangle3 -> "Rectangle with height ${shape.height} and width ${shape.width}"
        }
    }

}