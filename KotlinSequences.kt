fun main(){
    // Последовательности в Kotlin
    // Массивы

    // Создание пустого массива
    val var_array: Array<Int>
    var_array = arrayOf(2, 5, 7) // arrayOf() - передача значений массиву

    // Создание массива определённого типа с изначальными данными
    val numbers: Array<Byte> = arrayOf(1, 4, 9, 13)

    // Создание массива с неявной типизацией
    val students = arrayOf("Андрей", "Мария", "Олег", "Ирина")

    // Создание массива смешанного типа
    val other = arrayOf("Привет", 13, 'Щ', 8.9)

    // Обращение к элементам массива
    val names = arrayOf("Кирилл", "Семён", "Сергей", "Алексей")
    println(names[0]) // Обращение к первому элементу
    println(names[1]) // Обращение ко второму элементу
    println(names[3]) // Обращение к третьему элементу

//    println(names[56])

    println(names[2])
    names[2] = "Анатолий"
    println(names[2])

    // Перебор массива
    val surnames = arrayOf("Иванов", "Петров", "Смирнов", "Алексеев")
    for (i in surnames){
        println("Сотрудник $i")
    }

    val myNumbers = arrayOf(1, 12, 56, 23, 90)
    for (i in myNumbers){
        println("Квадрат числа $i: ${i * i}")
    }

    // Проверка наличия некоторого элемента в массиве
    val student = arrayOf("Иванов", "Петров", "Смирнов", "Алексеев")
    println("Медведев" in student) // in - проверка наличия конкретного элемента в массиве
    println("Смирнов" in student)
    println("Медведев" !in student) // !in - проверка отсутствия конкретного элемента в массиве
    println("Смирнов" !in student)

    // Свойства массивов
    val arrayOfNumbers = arrayOf(1, 3, 5, 7)
    println(arrayOfNumbers.size) // .size - возвращение длины массива (количество элементов)
    println(arrayOfNumbers.lastIndex) // .lastIndex - возвращает индекс последнего значения в массиве
    println(arrayOfNumbers.indices) // .indices - возвращает числовой промежуток индексов данного массива


    // Диапазон
    // .. - оператор создания диапазона
    val range = 1..10 // создание числового диапазона
    for (i in range){
        print(i)
    }
    println()

    val chars = 'а'..'я'
    for (i in chars){
        print(i)
    }
    println()

    // downTo - оператор создания уменьшающейся последовательности
    val range2 = 10 downTo 1
    for (i in range2){
        print(i)
    }
    println()

    val chars2 = 'z' downTo 'a'
    for (i in chars2) print(i)
    println()

    // until - оператор создания диапазона без включения последнего значения. Аналог оператора ..<
    val range3 = 1 until 10
    for (i in range3) print(i)
    println()

    val chars3 = 'а' until 'я'
    for (i in chars3) print(i)
    println()

    // step - оператор указания шага изменения дальнейшего значения в диапазоне
    var range4 = 0..10 step 2
    var chars4 = 'я' downTo 'А' step 3

    for (i in range4) print(i)
    println()
    for (i in chars4) print(i)
    println()
}
