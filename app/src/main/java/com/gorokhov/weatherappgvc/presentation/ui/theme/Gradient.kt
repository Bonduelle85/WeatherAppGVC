package com.gorokhov.weatherappgvc.presentation.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class Gradient(
    val primaryGradient: Brush,
    val secondaryGradient: Brush,
    val shadowColor: Color,
) {

    constructor(
        firstColor: Color,
        secondColor: Color,
        thirdColor: Color,
        fourthColor: Color,
    ) : this(
        primaryGradient = Brush.linearGradient(listOf(firstColor, secondColor)),
        secondaryGradient = Brush.linearGradient(listOf(thirdColor, fourthColor)),
        shadowColor = firstColor
    )
}

object CardGradients {
    val gradients = listOf(
        // Теплый солнечный
        Gradient(
            firstColor = Color(0xFFFF6B47),
            secondColor = Color(0xFFFFB347),
            thirdColor = Color(0xFFFF8A37),
            fourthColor = Color(0xFFFFDF37),
        ),
        // Океанская волна
        Gradient(
            firstColor = Color(0xFF376FFF),
            secondColor = Color(0xFF37AFFF),
            thirdColor = Color(0xFF378FFF),
            fourthColor = Color(0xFF37CFFF),
        ),
        // Лавандовый закат
        Gradient(
            firstColor = Color(0xFF7F67FF),
            secondColor = Color(0xFFBF47FF),
            thirdColor = Color(0xFF9F57FF),
            fourthColor = Color(0xFFDF37FF),
        ),
        // Коралловый риф
        Gradient(
            firstColor = Color(0xFFFFC629),
            secondColor = Color(0xFFFF8A6B),
            thirdColor = Color(0xFFFFA84A),
            fourthColor = Color(0xFFFF6B8B),
        ),
        // Глубокое море
        Gradient(
            firstColor = Color(0xFF006064),
            secondColor = Color(0xFF0097A7),
            thirdColor = Color(0xFF00838F),
            fourthColor = Color(0xFF00BCD4),
        ),
        // Осенний лес
        Gradient(
            firstColor = Color(0xFF8D6E63),
            secondColor = Color(0xFFFF8A65),
            thirdColor = Color(0xFFA1887F),
            fourthColor = Color(0xFFFFA726),
        ),
        // Ягодный микс
        Gradient(
            firstColor = Color(0xFF3F51B5),
            secondColor = Color(0xFF9C27B0),
            thirdColor = Color(0xFF673AB7),
            fourthColor = Color(0xFFE91E63),
        ),
        // Тропический рай
        Gradient(
            firstColor = Color(0xFF9C27B0),
            secondColor = Color(0xFF00BCD4),
            thirdColor = Color(0xFF2196F3),
            fourthColor = Color(0xFF00E676),
        ),
        // Песчаные дюны
        Gradient(
            firstColor = Color(0xFFA1887F),
            secondColor = Color(0xFFFFD54F),
            thirdColor = Color(0xFFFFB74D),
            fourthColor = Color(0xFFFFF176),
        ),
        // Северное сияние
        Gradient(
            firstColor = Color(0xFFAB47BC),
            secondColor = Color(0xFF00ACC1),
            thirdColor = Color(0xFF5C6BC0),
            fourthColor = Color(0xFF00BFA5),
        ),
        // Кофейная палитра
        Gradient(
            firstColor = Color(0xFF8D6E63),
            secondColor = Color(0xFFBCAAA4),
            thirdColor = Color(0xFFA1887F),
            fourthColor = Color(0xFFD7CCC8),
        )
    )
}