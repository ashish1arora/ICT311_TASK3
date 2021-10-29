package com.usc.workoutapp.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\'J\u0012\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0007H\'J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0003H\'J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\'J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\'\u00a8\u0006\r"}, d2 = {"Lcom/usc/workoutapp/database/DaoAccess;", "", "deleteActivity", "", "activity", "Lcom/usc/workoutapp/utils/ActivityDetails;", "fetchActivities", "", "fetchActivity", "actPos", "insertWorkout", "", "updateActivity", "app_debug"})
public abstract interface DaoAccess {
    
    @androidx.room.Insert()
    public abstract long insertWorkout(@org.jetbrains.annotations.Nullable()
    com.usc.workoutapp.utils.ActivityDetails activity);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "select * from activities")
    public abstract java.util.List<com.usc.workoutapp.utils.ActivityDetails> fetchActivities();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from activities where _id=:actPos")
    public abstract com.usc.workoutapp.utils.ActivityDetails fetchActivity(int actPos);
    
    @androidx.room.Update()
    public abstract int updateActivity(@org.jetbrains.annotations.Nullable()
    com.usc.workoutapp.utils.ActivityDetails activity);
    
    @androidx.room.Delete()
    public abstract int deleteActivity(@org.jetbrains.annotations.Nullable()
    com.usc.workoutapp.utils.ActivityDetails activity);
}