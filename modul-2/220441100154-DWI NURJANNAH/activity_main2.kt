<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity2">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="1.0"
        tools:layout_editor_absoluteX="-30dp">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <TextView
                android:id="@+id/textView3"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="18dp"
                android:layout_marginTop="23dp"
                android:layout_marginEnd="172dp"
                android:fontFamily="@font/poppins_bold"
                android:text="@string/universitas_trunojoyo"
                android:textSize="18sp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.258"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />

            <TextView
                android:id="@+id/textView4"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="18dp"
                android:layout_marginTop="5dp"
                android:fontFamily="@font/poppins"
                android:text="@string/beranda"
                android:textSize="15sp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/textView3" />

            <ImageView
                android:id="@+id/imageView"
                android:layout_width="36dp"
                android:layout_height="36dp"
                android:layout_marginTop="27dp"
                android:layout_marginEnd="23dp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:srcCompat="@drawable/utm" />

            <TextView
                android:id="@+id/textView5"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="18dp"
                android:layout_marginTop="36dp"
                android:layout_marginEnd="237dp"
                android:fontFamily="@font/poppins_medium"
                android:text="@string/aktivitas_kegiatan"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/textView4" />


            <ImageView
                android:id="@+id/imageView7"
                android:layout_width="165dp"
                android:layout_height="165dp"
                android:layout_marginStart="18dp"
                android:layout_marginTop="12dp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/textView5"
                app:srcCompat="@drawable/baca1" />

            <ImageView
                android:id="@+id/imageView3"
                android:layout_width="165dp"
                android:layout_height="165dp"
                android:layout_marginTop="12dp"
                android:layout_marginEnd="18dp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="1.0"
                app:layout_constraintStart_toEndOf="@+id/imageView7"
                app:layout_constraintTop_toBottomOf="@+id/textView5"
                app:srcCompat="@drawable/baca2" />

            <ImageView
                android:id="@+id/imageView5"
                android:layout_width="165dp"
                android:layout_height="165dp"
                android:layout_marginStart="18dp"
                android:layout_marginTop="27dp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/imageView7"
                app:srcCompat="@drawable/baca1" />

            <ImageView
                android:id="@+id/imageView6"
                android:layout_width="165dp"
                android:layout_height="165dp"
                android:layout_marginTop="27dp"
                android:layout_marginEnd="18dp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/imageView3"
                app:srcCompat="@drawable/baca2" />

            <TextView
                android:id="@+id/textView6"
                android:layout_width="49dp"
                android:layout_height="24dp"
                android:layout_marginStart="18dp"
                android:layout_marginTop="38dp"
                android:fontFamily="@font/poppins_semibold"
                android:text="@string/artikel"
                android:textSize="15sp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/imageView5" />

            <ImageView
                android:id="@+id/imageView8"
                android:layout_width="116dp"
                android:layout_height="116dp"
                android:layout_marginStart="18dp"
                android:layout_marginTop="15dp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/textView6"
                app:srcCompat="@drawable/baca1" />

            <TextView
                android:id="@+id/textView7"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="13dp"
                android:layout_marginTop="87dp"
                android:fontFamily="@font/poppins_semibold"
                android:text="@string/news1"
                android:textSize="13sp"
                app:layout_constraintStart_toEndOf="@+id/imageView8"
                app:layout_constraintTop_toBottomOf="@+id/imageView6" />

            <TextView
                android:id="@+id/textView8"
                android:layout_width="228dp"
                android:layout_height="51dp"
                android:layout_marginStart="13dp"
                android:layout_marginTop="15dp"
                android:fontFamily="@font/poppins_light"
                android:text="@string/isi1"
                android:textSize="11sp"
                app:layout_constraintStart_toEndOf="@+id/imageView8"
                app:layout_constraintTop_toBottomOf="@+id/textView7"
                tools:ignore="MissingConstraints" />

            <ImageView
                android:id="@+id/imageView9"
                android:layout_width="116dp"
                android:layout_height="116dp"
                android:layout_marginStart="18dp"
                android:layout_marginTop="26dp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/imageView8"
                app:srcCompat="@drawable/baca1" />

            <TextView
                android:id="@+id/textView10"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="13dp"
                android:layout_marginTop="56dp"
                android:fontFamily="@font/poppins_semibold"
                android:text="@string/news2"
                android:textSize="13sp"
                app:layout_constraintStart_toEndOf="@+id/imageView9"
                app:layout_constraintTop_toBottomOf="@+id/textView8" />

            <ImageView
                android:id="@+id/imageView10"
                android:layout_width="116dp"
                android:layout_height="116dp"
                android:layout_marginStart="18dp"
                android:layout_marginTop="26dp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/imageView9"
                app:srcCompat="@drawable/baca1"
                 />

            <TextView
                android:id="@+id/textView11"
                android:layout_width="228dp"
                android:layout_height="51dp"
                android:layout_marginStart="13dp"
                android:layout_marginTop="15dp"
                android:fontFamily="@font/poppins_light"
                android:text="@string/isi2"
                android:textSize="11sp"
                app:layout_constraintStart_toEndOf="@+id/imageView9"
                app:layout_constraintTop_toBottomOf="@+id/textView10" />

            <TextView
                android:id="@+id/textView17"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="13dp"
                android:layout_marginTop="37dp"
                android:fontFamily="@font/poppins_semibold"
                android:text="@string/news3"
                android:textSize="13sp"
                app:layout_constraintStart_toEndOf="@+id/imageView10"
                app:layout_constraintTop_toBottomOf="@+id/imageView9" />

            <TextView
                android:id="@+id/textView20"
                android:layout_width="228dp"
                android:layout_height="51dp"
                android:layout_marginStart="13dp"
                android:layout_marginTop="15dp"
                android:fontFamily="@font/poppins_light"
                android:text="@string/isi3"
                android:textSize="11sp"
                app:layout_constraintStart_toEndOf="@+id/imageView10"
                app:layout_constraintTop_toBottomOf="@+id/textView17" />


        </androidx.constraintlayout.widget.ConstraintLayout>

    </ScrollView>


</androidx.constraintlayout.widget.ConstraintLayout>