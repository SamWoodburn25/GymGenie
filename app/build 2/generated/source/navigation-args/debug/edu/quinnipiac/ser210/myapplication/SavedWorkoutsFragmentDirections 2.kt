package edu.quinnipiac.ser210.myapplication

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class SavedWorkoutsFragmentDirections private constructor() {
  public companion object {
    public fun actionSavedWorkoutsFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_SavedWorkoutsFragment_to_HomeFragment)

    public fun actionSavedWorkoutsFragmentToInformationFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_SavedWorkoutsFragment_to_InformationFragment)
  }
}
