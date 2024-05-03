package edu.quinnipiac.ser210.myapplication

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeFragmentToSavedWorkoutsFragment(
    public val workoutName: String?,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_HomeFragment_to_SavedWorkoutsFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("workoutName", this.workoutName)
        return result
      }
  }

  private data class ActionHomeFragmentToAllWorkoutsFragment(
    public val bodyPart: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_HomeFragment_to_AllWorkoutsFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("bodyPart", this.bodyPart)
        return result
      }
  }

  public companion object {
    public fun actionHomeFragmentToSavedWorkoutsFragment(workoutName: String?): NavDirections =
        ActionHomeFragmentToSavedWorkoutsFragment(workoutName)

    public fun actionHomeFragmentToInformationFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_HomeFragment_to_InformationFragment)

    public fun actionHomeFragmentToAllWorkoutsFragment(bodyPart: String): NavDirections =
        ActionHomeFragmentToAllWorkoutsFragment(bodyPart)
  }
}
