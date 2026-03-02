/* Функции в Kotlin */

// fun - оператор объявления (создания) функции
// Пример объявления простейшей функции
fun greeting(){
    print("Введите имя: ")
    val name = readln()
    println("Добро пожаловать в систему, $name!")
}

// Объявление функции с параметром
fun logIn(login: String){
    println("$login вошел в систему.")
}

// Объявление функции с параметрами разных типов
fun newUser(name: String, age: Byte){
    println("Имя: $name, возраст: $age")
}

// Объявление функции с необязательным параметром (параметр имеет изначальное значение)
fun employee(surname: String, name: String, age: Byte, education: String = "Middle"){
    println("Сотрудник $name $surname, возраст: $age, образование: $education")
}

// return - оператор возвращения значения из функции
// Объявление функции, возвращающей значения
fun sum(x: Int, y: Int): Int{
    return x + y
}

//
fun square(number: Int): Long {
    return number.toLong() * number
}

// Объявление анонимной функции
val welcome = fun () {
    println("Hello")
}

val sum = fun (x: Int, y: Int): Int {
    return x + y
}

val arraySqr = arrayOf({for (i in 1..10) i * i})
val squares = Array(7) { index -> index * index }

// vararg - оператор указания параметра с переменным количеством значений
fun users(vararg names: String){
    for (i in names){
        println(i)
    }
}

fun sumAll(vararg numbers: Int): Int{
    var result = 0
    for (i in numbers) result += i
    return result
}

// Объявление функции с простыми и переменным параметром
fun group(name: String, course: Byte, vararg surnames: String){
    println("Группа $name, $course курс.\nСтуденты:")
    for (i in surnames) println(i)
}

// Объявление функции с переменным параметром не на последней позиции
fun country(name: String, vararg bigCities: String, capital: String){
    println("Страна: $name, столица: $capital\nКрупные города:")
    for (i in bigCities) println(i)
}

fun sumOfSquares(vararg numbers: Int): Int{
    var result = 0
    for (i in numbers) result += i * i
    return result
}

// Пример лямбда-выражения (сокращённая запись)
val greetings = { println("Hello!") }

// Пример лямбда-выражения (полноценная запись)
val greetings2: () -> Unit = { println("Привет!") }

// Пример лямбда-выражения с передачей параметра (сокращённая запись)
val greetings3 = {name: String -> println("Добро пожаловать, $name") }

//
fun calculate(x: Int, y: Int, op: (Int, Int) -> Int): Int{
    return op(x, y)
}

// Однострочная функция
fun cubed(number: Int) = number * number * number

// main() - точка запуска программы
fun main(){
    greeting() // вызов функции без параметра
    logIn("admin") // вызов функции с параметром
    newUser("Андрей", 27) // вызов функции с параметрами
    newUser(age = 20, name = "Мария") // вызов функции с параметрами через указание их имён
    employee("Smith", "John", 33) // вызов функции с необязательным параметром
    employee("White", "Walter", 51, "High")
    employee(
        education = "Low",
        name = "Jane",
        surname = "Doe",
        age = 23
    )
    val result = sum(2,5) // вызов функции, возвращающей значения
    println(result)
    println(sum(6, 7))

    val sqr = square(10244)
    println(sqr)

    welcome()
    println(sum(3, 6))

    for (i in squares) println(i)

    users("admin", "user", "moderator")

    println(sumAll(1, 2, 3, 3, 2, 1, 4, 5, 6, 6, 5, 4))

    group("24/4", 2, "Иванов", "Петров", "Семёнов", "Васильев")

    country("Россия", "Новосибирск", "Санкт-Петербург", "Казань", "Екатеринбург", capital = "Москва")

    val numbers = intArrayOf(1, 2, 3, 4, 5)
    println(sumOfSquares(*numbers))

    greetings()

    greetings2()

    greetings3("Андрей")

    println( calculate(5, 6, { a: Int, b: Int -> a + b}) )
    println( calculate(7, 8, { a, b -> a + b}) )

    println(cubed(3))
}
