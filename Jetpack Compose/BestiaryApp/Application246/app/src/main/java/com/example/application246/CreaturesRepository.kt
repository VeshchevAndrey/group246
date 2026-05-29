package com.example.application246

// Анонимный класс - описание класса является его единственным объектом
object CreaturesRepository {
    val creatures = listOf(
        Creature(
            id = 1,
            name = "Гоблин",
            description = "Маленький шустрый противник",
            image = R.drawable.goblin_ffvii
        ),
        Creature(
            id = 2,
            name = "Дракон",
            description = "Легендарное огнедышащее существо",
            image = R.drawable.dragon_ffvii
        ),
        Creature(
            id = 3,
            name = "Железный гигант",
            description = "Огромное непробиваемое создание",
            image = R.drawable.iron_man_ffvii
        )
    )

    fun getCreatureById(id: Int) = creatures.find {
        creature -> id == creature.id
    }
}