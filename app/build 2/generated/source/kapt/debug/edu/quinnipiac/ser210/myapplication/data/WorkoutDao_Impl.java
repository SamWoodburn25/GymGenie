package edu.quinnipiac.ser210.myapplication.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class WorkoutDao_Impl implements WorkoutDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Workout> __insertionAdapterOfWorkout;

  private final EntityDeletionOrUpdateAdapter<Workout> __deletionAdapterOfWorkout;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public WorkoutDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkout = new EntityInsertionAdapter<Workout>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `workouts` (`id`,`title`,`body_part`,`exercises`,`reps`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Workout entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getBodyPart() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBodyPart());
        }
        if (entity.getExercises() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getExercises());
        }
        if (entity.getReps() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getReps());
        }
      }
    };
    this.__deletionAdapterOfWorkout = new EntityDeletionOrUpdateAdapter<Workout>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `workouts` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Workout entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM workouts";
        return _query;
      }
    };
  }

  @Override
  public Object insertWorkout(final Workout workout, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfWorkout.insertAndReturnId(workout);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public void delete(final Workout workouts) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfWorkout.handle(workouts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Workout>> getAllWorkouts() {
    final String _sql = "SELECT * FROM workouts";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"workouts"}, false, new Callable<List<Workout>>() {
      @Override
      @Nullable
      public List<Workout> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfBodyPart = CursorUtil.getColumnIndexOrThrow(_cursor, "body_part");
          final int _cursorIndexOfExercises = CursorUtil.getColumnIndexOrThrow(_cursor, "exercises");
          final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
          final List<Workout> _result = new ArrayList<Workout>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Workout _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpBodyPart;
            if (_cursor.isNull(_cursorIndexOfBodyPart)) {
              _tmpBodyPart = null;
            } else {
              _tmpBodyPart = _cursor.getString(_cursorIndexOfBodyPart);
            }
            final String _tmpExercises;
            if (_cursor.isNull(_cursorIndexOfExercises)) {
              _tmpExercises = null;
            } else {
              _tmpExercises = _cursor.getString(_cursorIndexOfExercises);
            }
            final String _tmpReps;
            if (_cursor.isNull(_cursorIndexOfReps)) {
              _tmpReps = null;
            } else {
              _tmpReps = _cursor.getString(_cursorIndexOfReps);
            }
            _item = new Workout(_tmpId,_tmpTitle,_tmpBodyPart,_tmpExercises,_tmpReps);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
