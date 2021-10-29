package com.usc.workoutapp.database;

import java.lang.System;

@androidx.room.Database(entities = {com.usc.workoutapp.utils.ActivityDetails.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/usc/workoutapp/database/DBHandler;", "Landroidx/room/RoomDatabase;", "()V", "daoAccess", "Lcom/usc/workoutapp/database/DaoAccess;", "Companion", "app_debug"})
public abstract class DBHandler extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final com.usc.workoutapp.database.DBHandler.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DB_NAME = "local_db";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TABLE_NAME = "activities";
    private static com.usc.workoutapp.database.DBHandler INSTANCE;
    
    public DBHandler() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.usc.workoutapp.database.DaoAccess daoAccess();
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/usc/workoutapp/database/DBHandler$Companion;", "", "()V", "DB_NAME", "", "INSTANCE", "Lcom/usc/workoutapp/database/DBHandler;", "TABLE_NAME", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.usc.workoutapp.database.DBHandler getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}