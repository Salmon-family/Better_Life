package com.karrar.betterlife.ui

import com.github.aachartmodel.aainfographics.aachartcreator.*
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAStyle

class Charts<T>(dataOfHabits: Array<T>, title: String, nameOfCategory: Array<String>) {

    private val aaChartModel = AAChartModel()

    private val charts =
        aaChartModel.chartType(AAChartType.Column).stacking(AAChartStackingType.Normal)
            .title(title)
            .yAxisTitle(TITLE_AXIS_Y)
            .titleStyle(AAStyle().fontWeight(AAChartFontWeightType.Bold))
            .backgroundColor("#FAFAFB")
            .colorsTheme(arrayOf(
                "#26B05D",
            ))
            .dataLabelsEnabled(false)
            .categories(nameOfCategory)
            .series(arrayOf(
                AASeriesElement()
                    .name(FIRST_COLUMN)
                    .data(dataOfHabits as Array<Any>)
                    .borderRadius(BORDER_RADIUS)
                    .borderWidth(BORDER_WIDTH),
            )
            )

    fun drawCharts(): AAChartModel = charts

    companion object {
        const val TITLE_AXIS_Y = ""
        const val FIRST_COLUMN = "habits"

        const val BORDER_RADIUS = 8.0
        const val BORDER_WIDTH = 4.0
    }
}