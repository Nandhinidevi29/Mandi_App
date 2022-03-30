package com.example.mandi

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mandi.databinding.FragmentSellMyProduceSuccessBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SellMyProduceSuccessFragment : Fragment() {
    companion object {
        const val PARAM_SELLER_INFO_DATA_MODEL = "param_seller_info_data_model"
    }

    lateinit var sellerInfoDataModel: SellerInfoDataModel
    lateinit var binding: FragmentSellMyProduceSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.getParcelable<SellerInfoDataModel>(PARAM_SELLER_INFO_DATA_MODEL)?.let {
            sellerInfoDataModel = it
        }
        binding = FragmentSellMyProduceSuccessBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUI()
        binding.sellMoreButton.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_sell_my_produce_fragment)
        }
    }

    private fun loadUI() {
        sellerInfoDataModel.apply {
            binding.thankyouMessage.text =
                getString(R.string.thank_you_message, sellerInfo.name)
            binding.infoText.text =
                getString(R.string.info_text, grossPrice.toString(), grossWeight)
        }
    }
}