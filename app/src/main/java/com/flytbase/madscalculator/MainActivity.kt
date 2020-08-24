package com.flytbase.madscalculator

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var calculations = Calculations(this)
    private var calculationHistoryAdapter: CalculationHistoryAdapter? = null
    private var historyCalculationsList: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        historyCalculationsList = ArrayList()

        btn_c?.setOnClickListener(this)
        btn_bs?.setOnClickListener(this)
        btn_div?.setOnClickListener(this)
        btn_mul?.setOnClickListener(this)
        btn_add?.setOnClickListener(this)
        btn_sub?.setOnClickListener(this)
        btn_0?.setOnClickListener(this)
        btn_1?.setOnClickListener(this)
        btn_2?.setOnClickListener(this)
        btn_3?.setOnClickListener(this)
        btn_4?.setOnClickListener(this)
        btn_5?.setOnClickListener(this)
        btn_6?.setOnClickListener(this)
        btn_7?.setOnClickListener(this)
        btn_8?.setOnClickListener(this)
        btn_9?.setOnClickListener(this)
        btn_calculate?.setOnClickListener(this)
        btn_history?.setOnClickListener(this)
        btn_clearHistory?.setOnClickListener(this)

        calculationHistoryAdapter = CalculationHistoryAdapter(this, historyCalculationsList)

        recycler_History.adapter = calculationHistoryAdapter
        calculationHistoryAdapter?.notifyDataSetChanged()
    }

    override fun onClick(view: View?) {
        when (view) {
            btn_c -> {
                calculations.clear()
                tv_main?.text = "0"
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_bs -> {
                calculations.bs()
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_div -> {
                calculations.operatorClicked("/")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_mul -> {
                calculations.operatorClicked("*")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_add -> {
                calculations.operatorClicked("+")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_sub -> {
                calculations.operatorClicked("-")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_0 -> {
                calculations.numberClicked("0")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_1 -> {
                calculations.numberClicked("1")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_2 -> {
                calculations.numberClicked("2")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_3 -> {
                calculations.numberClicked("3")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_4 -> {
                calculations.numberClicked("4")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_5 -> {
                calculations.numberClicked("5")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_6 -> {
                calculations.numberClicked("6")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_7 -> {
                calculations.numberClicked("7")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_8 -> {
                calculations.numberClicked("8")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_9 -> {
                calculations.numberClicked("9")
                tv_main?.text = calculations.currentNumber
                tv_sub?.text = calculations.calc(calculations.numbers)
            }

            btn_calculate -> {
                val expression = calculations.numbers
                val evaluateAnswer = calculations.evaluateAnswer()
                tv_main?.text = calculations.answer

                if (!tv_sub.text.toString().isNullOrEmpty()) {
                    val calculation: String = tv_sub.text.toString() + "= ${calculations.answer}"

                    if (historyCalculationsList == null) {
                        historyCalculationsList = ArrayList()
                    }
                    if (historyCalculationsList?.size == 10) {
                        historyCalculationsList?.removeAt(0)
                    }

                    historyCalculationsList?.add(calculation)
                }

                if (recycler_History.visibility == View.VISIBLE) {
                    calculationHistoryAdapter?.notifyDataSetChanged()
                } else {
                    Log.d("Calculator", "History List not visible")
                }
                tv_sub.text = ""

            }

            btn_history -> {
                recycler_History.visibility = View.VISIBLE
                calculationHistoryAdapter?.notifyDataSetChanged()
            }

            btn_clearHistory -> {
                historyCalculationsList?.clear()
                calculationHistoryAdapter?.notifyDataSetChanged()
            }
        }
    }
}
