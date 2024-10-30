package com.example.shownumber

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các thành phần trong giao diện
        val etNumber = findViewById<EditText>(R.id.etNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val listView = findViewById<ListView>(R.id.listView)
        val tvError = findViewById<TextView>(R.id.tvError)

        // Xử lý sự kiện khi nhấn nút "Show"
        btnShow.setOnClickListener {
            val input = etNumber.text.toString()

            // Kiểm tra tính hợp lệ của dữ liệu đầu vào
            val inputDouble = input.toDoubleOrNull()
            if (inputDouble == null || inputDouble <= 0 || inputDouble % 1 != 0.0) {
                tvError.text = "Vui lòng nhập số nguyên dương hợp lệ!"
                tvError.visibility = TextView.VISIBLE
                listView.adapter = null
                return@setOnClickListener
            } else {
                tvError.visibility = TextView.GONE
            }
            // Lấy giá trị n từ EditText và xác định loại số
            val n = input.toInt()
            val resultList = when (radioGroup.checkedRadioButtonId) {
                R.id.rbEven -> getEvenNumbers(n)
                R.id.rbOdd -> getOddNumbers(n)
                R.id.rbSquare -> getSquareNumbers(n)
                else -> listOf()
            }
            // Hiển thị danh sách kết quả trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listView.adapter = adapter
        }
    }

    // Hàm lấy danh sách số chẵn từ 0 đến n
    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    // Hàm lấy danh sách số lẻ từ 1 đến n
    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    // Hàm lấy danh sách số chính phương từ 0 đến n
    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).filter {
            kotlin.math.sqrt(it.toDouble()).toInt() * kotlin.math.sqrt(it.toDouble()).toInt() == it
        }
    }
}