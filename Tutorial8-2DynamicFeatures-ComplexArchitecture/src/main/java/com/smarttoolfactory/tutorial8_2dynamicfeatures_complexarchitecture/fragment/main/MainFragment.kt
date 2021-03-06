package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.main

import android.os.Bundle
import android.view.View
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.databinding.FragmentMainBinding
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.setupWithNavController


class MainFragment : BaseDataBindingFragment<FragmentMainBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on create and when restoring state.
     */
    private fun setupBottomNavigationBar() {

        val binding = dataBinding!!

        val bottomNavigationView = binding.bottomNav


        val navGraphIds = listOf(
            R.navigation.nav_graph_view_pager,
            R.navigation.nav_graph_dashboard,
            R.navigation.nav_graph_notification
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )

        addNotificationBadge()

    }

    private fun addNotificationBadge() {

        val binding = dataBinding!!

        // Add badge to bottom navigation
        val bottomNavigationView = binding.bottomNav
        val menuItemId = bottomNavigationView.menu.getItem(2).itemId
        val badge = bottomNavigationView.getOrCreateBadge(menuItemId)
        badge.number = 2
    }
}

