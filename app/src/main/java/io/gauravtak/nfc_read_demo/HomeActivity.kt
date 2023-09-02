package io.gauravtak.nfc_read_demo

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.gauravtak.nfc_read_demo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var viewModel: HomeViewModel? = null
    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setupClickListeners()
        viewModel = viewModels<HomeViewModel>().value
        initNfcAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel?.nfcTagScanned?.observe(
            this,
            Observer {
                binding.tvNFCStatus.text = it
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        )
        viewModel?.invalidNFCCardDialogState?.observe(
            this
        ) {
            if (it) {
                showDialog(
                    title = getString(R.string.error),
                    message = getString(R.string.this_is_not_valid_card_please_another),
                    onclick = { dialog ->
                        dialog.dismiss()
                        viewModel?.hideInvalidNFCCardDialog()
                    }
                )
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnNfcScan.setOnClickListener {
            enableNfcReaderMode()
            if (viewModel?.isNFCEnabled == true) {
                expandBottomSheet(binding.viewOuterNfcScanBottomSheetView)
            } else {
                checkAndShowNFCErrorDialog()
            }
        }
        binding.nfcScanBottomSheetView.bindingNFCScanBottomSheetView.btnCancel.setOnClickListener {
            collapseBottomSheet(binding.viewOuterNfcScanBottomSheetView)
        }
    }
    // this is used to call the expanding of bottom sheet
    private fun expandBottomSheet(bottomView: View) {
        bottomView.bringToFront()
        BottomSheetBehavior.from(bottomView).state = BottomSheetBehavior.STATE_EXPANDED
    }

    // this is used to call the collapsing of bottom sheet explicitly, like by pressing back button or cancel button
    private fun collapseBottomSheet(bottomView: View) {
        BottomSheetBehavior.from(bottomView).state = BottomSheetBehavior.STATE_COLLAPSED
    }


    private fun initNfcAdapter() {
        val nfcManager = this@HomeActivity.getSystemService(Context.NFC_SERVICE) as NfcManager
        nfcAdapter = nfcManager.defaultAdapter
        viewModel?.isNFCSupported = nfcAdapter != null
        viewModel?.isNFCEnabled = nfcAdapter != null && nfcAdapter!!.isEnabled
    }

    private fun enableNfcReaderMode() {
        nfcAdapter?.enableReaderMode(
            this,
            {
                onNFCTagDetected(
                    tag = it,
                    onTagDetected = viewModel!!::onNewTag,
                    onProcessStart = viewModel!!::hideInvalidNFCCardDialog,
                    onInvalidTagDetected = viewModel!!::showInvalidNFCCardDialog
                )
            },
            NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_NFC_B or NfcAdapter.FLAG_READER_NFC_F or NfcAdapter.FLAG_READER_NFC_V or NfcAdapter.FLAG_READER_NFC_BARCODE,
            null
        )
    }

    private fun disableNFCReaderMode() {
        nfcAdapter?.disableReaderMode(this@HomeActivity)
    }

    override fun onPause() {
        super.onPause()
        collapseBottomSheet(binding.viewOuterNfcScanBottomSheetView)
        if (viewModel?.isNFCSupported == true && viewModel?.isNFCEnabled == true) disableNFCReaderMode()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.isNFCSupported = nfcAdapter != null
        viewModel?.isNFCEnabled = nfcAdapter != null && nfcAdapter!!.isEnabled
    }

   private fun checkAndShowNFCErrorDialog() {
        if (viewModel?.isNFCSupported == false) {
            showDialog(
                title = getString(R.string.error),
                message = getString(R.string.nfc_not_supported_for_this_device),
                onclick = { dialog ->
                    dialog.dismiss()
                }
            )
        } else if (viewModel?.isNFCEnabled == false) {
            showDialogWithTwoButtons(
                title = getString(R.string.error),
                message = getString(R.string.nfc_not_enable_for_this_device),
                btnPositiveText = getString(R.string.enable),
                onclick = { dialog ->
                    dialog.dismiss()
                    startActivity(Intent("android.settings.NFC_SETTINGS"))
                },
                onNegativeClick = { dialog ->
                    dialog.dismiss()
                }
            )
        }
    }
}