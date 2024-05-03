package edu.quinnipiac.ser210.myapplication

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class SavedWorkoutsFragmentArgs(
  public val workoutName: String?,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("workoutName", this.workoutName)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("workoutName", this.workoutName)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): SavedWorkoutsFragmentArgs {
      bundle.setClassLoader(SavedWorkoutsFragmentArgs::class.java.classLoader)
      val __workoutName : String?
      if (bundle.containsKey("workoutName")) {
        __workoutName = bundle.getString("workoutName")
      } else {
        throw IllegalArgumentException("Required argument \"workoutName\" is missing and does not have an android:defaultValue")
      }
      return SavedWorkoutsFragmentArgs(__workoutName)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): SavedWorkoutsFragmentArgs {
      val __workoutName : String?
      if (savedStateHandle.contains("workoutName")) {
        __workoutName = savedStateHandle["workoutName"]
      } else {
        throw IllegalArgumentException("Required argument \"workoutName\" is missing and does not have an android:defaultValue")
      }
      return SavedWorkoutsFragmentArgs(__workoutName)
    }
  }
}
