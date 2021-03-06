package com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.fragment.navhost

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.R
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.databinding.FragmentNavhostDashboardBinding
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.fragment.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.util.Event
import com.smarttoolfactory.tutorial8_1dynamicfeatures_navigation.viewmodel.NavControllerViewModel


class DashboardNavHostFragment : BaseDataBindingFragment<FragmentNavhostDashboardBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_dashboard

    private val navControllerViewModel by activityViewModels<NavControllerViewModel>()


    private var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nestedDashboardNavHostFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

    }

    override fun onResume() {
        super.onResume()

        // Set this navController as ViewModel's navController
        navController?.let {
            navControllerViewModel.currentNavController.value = Event(it)
        }
    }


}