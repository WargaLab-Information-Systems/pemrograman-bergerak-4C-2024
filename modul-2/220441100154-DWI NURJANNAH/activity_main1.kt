<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView1"
        android:layout_width="303dp"
        android:layout_height="33dp"
        android:layout_marginStart="22dp"
        android:layout_marginTop="32dp"
        android:textColor="@color/black"
        android:layout_marginEnd="123dp"
        android:fontFamily="@font/poppins_bold"
        android:text="Univeristas Trunojoyo"
        android:textSize="22sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView2"
        tools:ignore="MissingConstraints" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="192dp"
        android:layout_height="36dp"
        android:layout_marginStart="22dp"
        android:layout_marginTop="10dp"
        android:layout_marginEnd="135dp"
        android:fontFamily="@font/poppins_light"
        android:text="Selamat datang di Aplikasi milik Univeritas Trunojoyo"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.1"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView1" />



    <ImageView
        android:id="@+id/imageView2"
        android:layout_width="421dp"
        android:layout_height="272dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/perpus" />

    <EditText
        android:id="@+id/editTextText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10"
        android:fontFamily="@font/poppins_light"
        android:inputType="text"
        android:text="Masukkan nama kamu"
        android:layout_marginStart="25dp"
        android:layout_marginTop="59dp"
        android:layout_marginEnd="122dp"

        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView"
        tools:ignore="MissingConstraints" />

    <com.google.android.material.button.MaterialButton
        android:id="@+id/button"
        android:layout_width="287dp"
        android:layout_height="50dp"
        android:layout_marginStart="55dp"
        android:layout_marginTop="45dp"
        android:layout_marginEnd="55dp"
        android:backgroundTint="@color/black"
        android:text="Lanjut"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/editTextText"
        tools:ignore="MissingConstraints" />


</androidx.constraintlayout.widget.ConstraintLayout>