package edu.quinnipiac.ser210.myapplication

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class AllWorkoutsFragmentDirections private constructor() {
  private data class ActionAllWorkoutsFragmentToSavedWorkoutsFragment(
    public val workoutName: String?,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_AllWorkoutsFragment_to_SavedWorkoutsFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("workoutName", this.workoutName)
        return result
      }
  }

  public companion object {
    public fun actionAllWorkoutsFragmentToSavedWorkoutsFragment(workoutName: String?): NavDirections
        = ActionAllWorkoutsFragmentToSavedWorkoutsFragment(workoutName)

    public fun actionAllWorkoutsFragmentToInformationFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_AllWorkoutsFragment_to_InformationFragment)

    public fun actionAllWorkoutsFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_AllWorkoutsFragment_to_HomeFragment)
  }
}
