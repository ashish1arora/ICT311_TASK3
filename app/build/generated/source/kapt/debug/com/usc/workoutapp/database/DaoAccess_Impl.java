package com.usc.workoutapp.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.usc.workoutapp.utils.ActivityDetails;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DaoAccess_Impl implements DaoAccess {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ActivityDetails> __insertionAdapterOfActivityDetails;

  private final EntityDeletionOrUpdateAdapter<ActivityDetails> __deletionAdapterOfActivityDetails;

  private final EntityDeletionOrUpdateAdapter<ActivityDetails> __updateAdapterOfActivityDetails;

  public DaoAccess_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfActivityDetails = new EntityInsertionAdapter<ActivityDetails>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `activities` (`_id`,`act_title`,`act_date`,`act_place`,`act_start_time`,`act_end_time`,`act_type`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ActivityDetails value) {
        stmt.bindLong(1, value.get_id());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDate());
        }
        if (value.getPlace() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPlace());
        }
        if (value.getStarttime() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getStarttime());
        }
        if (value.getEndtime() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEndtime());
        }
        if (value.getType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getType());
        }
      }
    };
    this.__deletionAdapterOfActivityDetails = new EntityDeletionOrUpdateAdapter<ActivityDetails>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `activities` WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ActivityDetails value) {
        stmt.bindLong(1, value.get_id());
      }
    };
    this.__updateAdapterOfActivityDetails = new EntityDeletionOrUpdateAdapter<ActivityDetails>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `activities` SET `_id` = ?,`act_title` = ?,`act_date` = ?,`act_place` = ?,`act_start_time` = ?,`act_end_time` = ?,`act_type` = ? WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ActivityDetails value) {
        stmt.bindLong(1, value.get_id());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDate());
        }
        if (value.getPlace() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPlace());
        }
        if (value.getStarttime() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getStarttime());
        }
        if (value.getEndtime() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEndtime());
        }
        if (value.getType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getType());
        }
        stmt.bindLong(8, value.get_id());
      }
    };
  }

  @Override
  public long insertWorkout(final ActivityDetails activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfActivityDetails.insertAndReturnId(activity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteActivity(final ActivityDetails activity) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfActivityDetails.handle(activity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int updateActivity(final ActivityDetails activity) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfActivityDetails.handle(activity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<ActivityDetails> fetchActivities() {
    final String _sql = "select * from activities";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "act_title");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "act_date");
      final int _cursorIndexOfPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "act_place");
      final int _cursorIndexOfStarttime = CursorUtil.getColumnIndexOrThrow(_cursor, "act_start_time");
      final int _cursorIndexOfEndtime = CursorUtil.getColumnIndexOrThrow(_cursor, "act_end_time");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "act_type");
      final List<ActivityDetails> _result = new ArrayList<ActivityDetails>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ActivityDetails _item;
        final int _tmp_id;
        _tmp_id = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final String _tmpPlace;
        if (_cursor.isNull(_cursorIndexOfPlace)) {
          _tmpPlace = null;
        } else {
          _tmpPlace = _cursor.getString(_cursorIndexOfPlace);
        }
        final String _tmpStarttime;
        if (_cursor.isNull(_cursorIndexOfStarttime)) {
          _tmpStarttime = null;
        } else {
          _tmpStarttime = _cursor.getString(_cursorIndexOfStarttime);
        }
        final String _tmpEndtime;
        if (_cursor.isNull(_cursorIndexOfEndtime)) {
          _tmpEndtime = null;
        } else {
          _tmpEndtime = _cursor.getString(_cursorIndexOfEndtime);
        }
        final String _tmpType;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmpType = null;
        } else {
          _tmpType = _cursor.getString(_cursorIndexOfType);
        }
        _item = new ActivityDetails(_tmp_id,_tmpTitle,_tmpDate,_tmpPlace,_tmpStarttime,_tmpEndtime,_tmpType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ActivityDetails fetchActivity(final int actPos) {
    final String _sql = "select * from activities where _id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, actPos);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "act_title");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "act_date");
      final int _cursorIndexOfPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "act_place");
      final int _cursorIndexOfStarttime = CursorUtil.getColumnIndexOrThrow(_cursor, "act_start_time");
      final int _cursorIndexOfEndtime = CursorUtil.getColumnIndexOrThrow(_cursor, "act_end_time");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "act_type");
      final ActivityDetails _result;
      if(_cursor.moveToFirst()) {
        final int _tmp_id;
        _tmp_id = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final String _tmpPlace;
        if (_cursor.isNull(_cursorIndexOfPlace)) {
          _tmpPlace = null;
        } else {
          _tmpPlace = _cursor.getString(_cursorIndexOfPlace);
        }
        final String _tmpStarttime;
        if (_cursor.isNull(_cursorIndexOfStarttime)) {
          _tmpStarttime = null;
        } else {
          _tmpStarttime = _cursor.getString(_cursorIndexOfStarttime);
        }
        final String _tmpEndtime;
        if (_cursor.isNull(_cursorIndexOfEndtime)) {
          _tmpEndtime = null;
        } else {
          _tmpEndtime = _cursor.getString(_cursorIndexOfEndtime);
        }
        final String _tmpType;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmpType = null;
        } else {
          _tmpType = _cursor.getString(_cursorIndexOfType);
        }
        _result = new ActivityDetails(_tmp_id,_tmpTitle,_tmpDate,_tmpPlace,_tmpStarttime,_tmpEndtime,_tmpType);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
