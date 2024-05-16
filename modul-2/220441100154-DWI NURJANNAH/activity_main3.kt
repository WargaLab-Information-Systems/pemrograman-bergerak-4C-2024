<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/main"
    tools:context=".MainActivity3">

    <TextView
        android:id="@+id/textView2"
        android:layout_width="203dp"
        android:layout_height="27dp"
        android:text="@string/utm"
        android:layout_marginTop="23dp"
        android:layout_marginStart="18dp"
        android:fontFamily="@font/poppins_bold"
        android:textSize="18sp"

        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <ImageView
        android:id="@+id/imageView4"
        android:layout_width="36dp"
        android:layout_height="36dp"
        android:layout_marginTop="27dp"
        android:layout_marginEnd="23dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/utm" />

    <TextView
        android:id="@+id/textView9"
        android:layout_width="46dp"
        android:layout_height="23dp"
        android:textSize="15sp"
        android:fontFamily="@font/poppins"
        android:layout_marginStart="18dp"
        android:layout_marginTop="5dp"
        android:text="@string/pp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView2" />

    <ImageView
        android:id="@+id/imageView11"
        android:layout_width="169dp"
        android:layout_height="169dp"
        android:layout_marginTop="68dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView9"
        app:srcCompat="@drawable/utm" />

    <TextView
        android:id="@+id/textView12"
        android:layout_width="203dp"
        android:layout_height="27dp"
        android:layout_marginStart="95dp"
        android:layout_marginTop="21dp"
        android:fontFamily="@font/poppins_bold"
        android:text="@string/utm2"
        android:textSize="18sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView11" />

    <TextView
        android:id="@+id/textView13"
        android:layout_width="124dp"
        android:layout_height="26dp"
        android:layout_marginStart="18dp"
        android:layout_marginTop="63dp"
        android:fontFamily="@font/poppins_semibold"
        android:text="@string/pp1"
        android:textSize="17sp"

        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView12" />

    <TextView
        android:id="@+id/textView14"
        android:layout_width="358dp"
        android:layout_height="230dp"
        android:layout_marginTop="12dp"
        android:fontFamily="@font/poppins"
        android:layout_marginEnd="19dp"
        android:layout_marginStart="18dp"
        android:justificationMode="inter_word"
        android:text="@string/pp3"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView13" />
</androidx.constraintlayout.widget.ConstraintLayout>