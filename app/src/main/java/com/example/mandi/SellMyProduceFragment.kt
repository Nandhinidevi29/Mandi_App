package com.example.mandi

import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.VisibleForTesting
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mandi.SellMyProduceSuccessFragment.Companion.PARAM_SELLER_INFO_DATA_MODEL
import com.example.mandi.databinding.FragmentSellMyProduceBinding

class SellMyProduceFragment : Fragment() {

    lateinit var binding: FragmentSellMyProduceBinding
    private val sellerViewModel: SellerViewModel by viewModels()
    private var sellerInfoDataModel: SellerInfoDataModel? = null
    private var sellerInfo: SellerInfo? = null
    private var pricePerKg: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSellMyProduceBinding.inflate(inflater, container, false)
        context?.let { sellerViewModel.insertSellerInfo(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUI()
    }

    private fun loadUI() {
        SellMyProduceHelper.setSellerCardTitle(binding, this@SellMyProduceFragment)
        //autofill input fields based on entered data
        addTextChangedListener(binding.sellerName.textInput) { checkSellerNameField() }
        addTextChangedListener(binding.loyaltyCardInfo.textInput) { checkLoyaltyCardIdField() }
        addTextChangedListener(binding.village.textInput) { findPricePerKg() }
        addTextChangedListener(binding.grossWeight.textInput) { calculateGrossPrice() }
    }

    private fun addTextChangedListener(view: EditText, afterTextChanged: () -> Unit) {
        binding.apply {
            view.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    //left explicit
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    //left explicit
                }

                override fun afterTextChanged(s: Editable?) {
                    view.setOnFocusChangeListener { view, focus ->
                        if (!focus)
                            afterTextChanged()
                    }
                }
            })
        }

    }

    private fun initListener() {
        binding.sellMyProduceButton.apply {
            //enable button after gross price is calculated and displayed
            isEnabled = true
            setOnClickListener {
                findNavController().navigate(
                    R.id.navigate_to_sell_my_produce_success_fragment,
                    bundleOf(PARAM_SELLER_INFO_DATA_MODEL to sellerInfoDataModel)
                )
            }
        }
    }

    private fun displayGrossPrice() {
        binding.grossPrice.apply {
            ViewUitls.show(grossPriceTitle, grossPrice, appliedIndex)
            grossPrice.text =
                getString(R.string.gross_price, sellerInfoDataModel?.grossPrice.toString())
            appliedIndex.text = getString(
                R.string.applied_loyalty_index,
                sellerInfoDataModel?.loyaltyIndex.toString()
            )
        }
        initListener()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun checkSellerNameField() {
        binding.apply {
            val sellerNameInfo = sellerName.textInput.text
            val loyaltyCardId = loyaltyCardInfo.textInput.text
            if (!sellerNameInfo.isNullOrEmpty() && loyaltyCardId.isNullOrEmpty()) {
                context?.let { context ->
                    sellerInfo =
                        sellerViewModel.getSellerByName(sellerNameInfo.toString(), context)
                }
                setSellerInfo()
                if (sellerInfoDataModel?.isRegisteredSeller == true) {
                    loyaltyCardInfo.textInput.setText(sellerInfoDataModel!!.sellerInfo.loyaltyCardId)
                } else {
                    loyaltyCardInfo.cardView.visibility = View.GONE
                }
            }

        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun checkLoyaltyCardIdField() {
        binding.apply {
            val sellerNameInfo = sellerName.textInput.text
            val loyaltyCardId = loyaltyCardInfo.textInput.text
            if (!loyaltyCardId.isNullOrEmpty() && sellerNameInfo.isNullOrEmpty()) {
                context?.let { context ->
                    sellerInfo =
                        sellerViewModel.getSellerByLoyaltyCardId(
                            loyaltyCardId.toString(),
                            context
                        )
                }
                sellerName.textInput.setText(sellerInfo?.name)
            }
            setSellerInfo()
        }
    }

    private fun setSellerInfo() = sellerInfo?.let {
        sellerInfoDataModel = SellMyProduceHelper.setSellerInfo(it)
        //enable village field only when sellerInfo is entered
        binding.village.textInput.isEnabled = true
    }

    private fun findPricePerKg() {
        binding.apply {
            val villageName = village.textInput.text.toString()
            pricePerKg =
                SellMyProduceHelper.findPricePerKg(villageName, sellerInfoDataModel!!.villageInfo)
            //enable gross weight field only when village is entered
            grossWeight.textInput.isEnabled = true
        }
    }

    private fun calculateGrossPrice() {
        SellMyProduceHelper.apply {
            val grossWeight = Integer.parseInt(binding.grossWeight.textInput.text.toString())
            val grossPrice = calculateGrossPrice(
                pricePerKg, sellerInfoDataModel!!.loyaltyIndex, grossWeight
            )
            val formattedGrossPrice = roundOffDecimal(grossPrice)
            //update sellerInfoDataModel with gross weight and gross price
            sellerInfoDataModel =
                sellerInfo?.let {
                    setSellerInfo(it, grossWeight, formattedGrossPrice)
                }
            displayGrossPrice()
        }
    }

}