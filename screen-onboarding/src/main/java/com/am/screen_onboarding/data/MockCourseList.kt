package com.am.screen_onboarding.data

data class GridItem (
    val id : Int,
    val text : String,
    val isTinted : Boolean
)

val list1 = mutableListOf(
    GridItem(11, "ТестовыйКурс", false),
    GridItem(12, "1С Администрирование", false),
    GridItem(13, "RabbitMQ", true),
    GridItem(14, "Трафик", false),
    GridItem(15, "Какой-то курс", false)
)

val list2 = mutableListOf(
    GridItem(21, "Что-то там маркетинг", false),
    GridItem(22, "B2B маркетинг", false),
    GridItem(23, "Google аналитика", false),
    GridItem(24, "Заглушка-курс", false)
)

val list3 = mutableListOf(
GridItem(31, "Короткий курс", false),
GridItem(32, "UI/UX исследователь", false),
GridItem(33, "Веб аналитика", false),
GridItem(34, "Big Data", true),
GridItem(35, "Некий курс", false),
GridItem(36, "И еще курс", false)
)

val list4 = mutableListOf(
    GridItem(41, "Что-то там", false),
    GridItem(42, "Веб-дизайн", false),
    GridItem(43, "Cinema 4D", false),
    GridItem(44, "Промпт инпут", false),
    GridItem(45, "Еще что-то", false)
)

val list5 = mutableListOf(
    GridItem(51, "Coursewww", false),
    GridItem(52, "Three.js", true),
    GridItem(53, "Парсинг", false),
    GridItem(54, "Python-разработчик", false),
    GridItem(55, "И еще какой-то курс", false)
)


