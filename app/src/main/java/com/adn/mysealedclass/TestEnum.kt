package com.adn.mysealedclass

enum class TestEnum {
    January, February
}

enum class TestEnum2(val number: Int) {
    January(1), February(2)
}

//enum class TestEnum3(val number: Int) {
//    January(1), February("2")
//}

//sealed class Months {
//    data object January : Months()
//    data object February : Months()
//}


//sealed class Months(val number: Int) {
//    class January(var shortHand: String, num: Int) : Months(num)
//    open class February : Months(2)
//}

sealed class Months(val number: Int) {
    class January(num: Int) : Months(num)
    open class February : Months(2)
}


