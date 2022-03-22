object AppDep {
    const val appCompat = "androidx.appcompat:appcompat:${DepVersion.appcompatVersion}"
    const val coreKtx = "androidx.core:core-ktx:${DepVersion.coreKtxVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${DepVersion.constraintLayoutVersion}"
    const val material = "com.google.android.material:material:${DepVersion.materialVersion}"
    const val fragment = "androidx.fragment:fragment-ktx:${DepVersion.fragmentVersion}"
    const val activity = "androidx.activity:activity-ktx:${DepVersion.activityVersion}"
    const val cardView = "androidx.cardview:cardview:${DepVersion.cardViewVersion}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${DepVersion.recyclerViewVersion}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${DepVersion.lifecycleVersion}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${DepVersion.lifecycleVersion}"
    const val lifecycle = "androidx.lifecycle:lifecycle-common-java8:${DepVersion.lifecycleVersion}"
    const val lifecycleService = "androidx.lifecycle:lifecycle-service:${DepVersion.lifecycleVersion}"
    const val preference = "androidx.preference:preference:${DepVersion.preferenceVersion}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${DepVersion.navigationVersion}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${DepVersion.navigationVersion}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${DepVersion.navigationSafeArgsVersion}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${DepVersion.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${DepVersion.hiltVersion}"
    const val dagger = "com.google.dagger:dagger:${DepVersion.hiltVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${DepVersion.hiltVersion}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${DepVersion.retrofitVersion}"
    const val rxJava3adapter = "com.squareup.retrofit2:adapter-rxjava3:${DepVersion.retrofitVersion}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${DepVersion.retrofitVersion}"

    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DepVersion.coroutinesVersion}"
    const val timber = "com.jakewharton.timber:timber:${DepVersion.timberVersion}"

    const val room = "androidx.room:room-ktx:${DepVersion.roomVersion}"
    const val roomRuntime = "androidx.room:room-runtime:${DepVersion.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${DepVersion.roomVersion}"

    const val junit = "junit:junit:${DepVersion.junitVersion}"
    const val extJunit = "androidx.test.ext:junit:${DepVersion.junitExtVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${DepVersion.espressoVersion}"
}