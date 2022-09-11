package com.karrar.betterlife.ui

import com.github.aachartmodel.aainfographics.aachartcreator.*
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAStyle

class Charts(dataOfHabits: Array<Any>, dataOfPlans: Array<Any>) {

    private val aaChartModel = AAChartModel()
    val charts = aaChartModel.chartType(AAChartType.Column).stacking(AAChartStackingType.Normal)
        .title(TITLE)
        .yAxisTitle(TITLE_AXIS_Y)
        .titleStyle(AAStyle().fontWeight(AAChartFontWeightType.Bold))
        .backgroundColor("#FAFAFB")
        .colorsTheme(arrayOf(
            "#DDB4FE",
            "#6C5DD3",
        ))
        .dataLabelsEnabled(false)
        .categories(arrayOf("M", "T", "W", "T", "F", "S", "S"))
        .series(arrayOf(
            AASeriesElement()
                .name(FIRST_COLUMN)
                .data(dataOfHabits)
                .borderRadiusTopLeft(BORDER_RADIUS)
                .borderRadiusTopRight(BORDER_RADIUS),
            AASeriesElement()
                .name(SECOND_COLUMN)
                .data(dataOfPlans)
                .borderRadiusBottomRight(BORDER_RADIUS)
                .borderRadiusBottomLeft(BORDER_RADIUS)
        )
        )


    companion object {
        const val TITLE = "Completed in the last 7 Days"
        const val TITLE_AXIS_Y = ""
        const val FIRST_COLUMN = "habits"
        const val SECOND_COLUMN = "plans"

        const val BORDER_RADIUS = 8.0
    }
}