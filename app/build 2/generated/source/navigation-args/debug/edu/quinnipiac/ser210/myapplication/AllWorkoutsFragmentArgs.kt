package edu.quinnipiac.ser210.myapplication

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class AllWorkoutsFragmentArgs(
  public val bodyPart: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("bodyPart", this.bodyPart)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("bodyPart", this.bodyPart)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): AllWorkoutsFragmentArgs {
      bundle.setClassLoader(AllWorkoutsFragmentArgs::class.java.classLoader)
      val __bodyPart : String?
      if (bundle.containsKey("bodyPart")) {
        __bodyPart = bundle.getString("bodyPart")
        if (__bodyPart == null) {
          throw IllegalArgumentException("Argument \"bodyPart\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"bodyPart\" is missing and does not have an android:defaultValue")
      }
      return AllWorkoutsFragmentArgs(__bodyPart)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): AllWorkoutsFragmentArgs {
      val __bodyPart : String?
      if (savedStateHandle.contains("bodyPart")) {
        __bodyPart = savedStateHandle["bodyPart"]
        if (__bodyPart == null) {
          throw IllegalArgumentException("Argument \"bodyPart\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"bodyPart\" is missing and does not have an android:defaultValue")
      }
      return AllWorkoutsFragmentArgs(__bodyPart)
    }
  }
}
