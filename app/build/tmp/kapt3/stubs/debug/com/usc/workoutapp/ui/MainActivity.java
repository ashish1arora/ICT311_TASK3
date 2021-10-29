package com.usc.workoutapp.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0015B\u0005\u00a2\u0006\u0002\u0010\u0003J\"\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0010H\u0014J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0005H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/usc/workoutapp/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/usc/workoutapp/ui/fragments/FragmentActivityList$AddIconListener;", "()V", "activityPos", "", "goToFragment", "", "fragment", "Landroidx/fragment/app/Fragment;", "navID", "", "addToStack", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "switchToActivityDetails", "actPos", "Nav", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity implements com.usc.workoutapp.ui.fragments.FragmentActivityList.AddIconListener {
    private int activityPos = -2;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void goToFragment(@androidx.annotation.NonNull()
    androidx.fragment.app.Fragment fragment, @com.usc.workoutapp.ui.MainActivity.Nav()
    java.lang.String navID, boolean addToStack) {
    }
    
    @java.lang.Override()
    public void switchToActivityDetails(int actPos) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0081\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000\u00a8\u0006\u0003"}, d2 = {"Lcom/usc/workoutapp/ui/MainActivity$Nav;", "", "Companion", "app_debug"})
    @java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(value = kotlin.annotation.AnnotationRetention.SOURCE)
    public static abstract @interface Nav {
        @org.jetbrains.annotations.NotNull()
        public static final com.usc.workoutapp.ui.MainActivity.Nav.Companion Companion = null;
        
        @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b\u00a8\u0006\f"}, d2 = {"Lcom/usc/workoutapp/ui/MainActivity$Nav$Companion;", "", "()V", "DETAILS", "", "getDETAILS", "()Ljava/lang/String;", "setDETAILS", "(Ljava/lang/String;)V", "LIST", "getLIST", "setLIST", "app_debug"})
        public static final class Companion {
            @org.jetbrains.annotations.NotNull()
            private static java.lang.String LIST = "LIST";
            @org.jetbrains.annotations.NotNull()
            private static java.lang.String DETAILS = "DETAIL";
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getLIST() {
                return null;
            }
            
            public final void setLIST(@org.jetbrains.annotations.NotNull()
            java.lang.String p0) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getDETAILS() {
                return null;
            }
            
            public final void setDETAILS(@org.jetbrains.annotations.NotNull()
            java.lang.String p0) {
            }
        }
    }
}