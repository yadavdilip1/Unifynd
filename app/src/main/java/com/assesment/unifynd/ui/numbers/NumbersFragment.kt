package com.assesment.unifynd.ui.numbers

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.assesment.unifynd.databinding.FragmentNotificationsBinding

class NumbersFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val numberList = ArrayList<Int>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.editTextNumber.setOnEditorActionListener { textView, actionId, event ->

            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                val num = textView.text.toString()
                enterNumber(num)
            }
            false
        }


        binding.enterButton.setOnClickListener {

            val num = binding.editTextNumber.text.toString()
            enterNumber(num)
        }

        binding.doneButton.setOnClickListener {
            getLargestNumber()
        }

        binding.clearButton.setOnClickListener {
            numberList.clear()
            binding.largeNumberLyt.isVisible = false
            binding.enteredNumberLyt.isVisible = false

        }

    }

    private fun enterNumber(num: String) {

        if (num.isEmpty()) {
            Toast.makeText(requireContext(), "Please Enter Number", Toast.LENGTH_SHORT).show()
        } else {
            numberList.add(num.toInt())

            binding.txtEnteredNumber.text = ""
            binding.editTextNumber.setText("")

            numberList.forEach { post ->
                val content = "$post \n"
                binding.txtEnteredNumber.append(content)
            }

            binding.enteredNumberLyt.isVisible = true


        }


    }

    private fun getLargestNumber() {

        if (numberList.isNullOrEmpty() || numberList.size < 2) {
            Toast.makeText(
                requireContext(),
                "Please enter at least two numbers",
                Toast.LENGTH_SHORT
            ).show()

        } else {

            var temp: Int

            for (i in 0 until numberList.size) {
                for (j in i + 1 until numberList.size) {
                    if (numberList[i] > numberList[j]) {
                        temp = numberList[i]
                        numberList[i] = numberList[j]
                        numberList[j] = temp
                    }
                }
            }

            binding.txtEnteredNumber.text = ""
            numberList.forEach { post ->
                val content = "$post \n"
                binding.txtEnteredNumber.append(content)
            }

            val second = numberList[numberList.size - 2]
            binding.txtSecLargestNumber.text = second.toString()

            binding.largeNumberLyt.isVisible = true


        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}