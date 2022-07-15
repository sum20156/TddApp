package com.example.tddapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.tddapp.api.ApiResponse
import com.example.tddapp.api.ApiService
import com.example.tddapp.local_db.AppDatabase
import com.example.tddapp.ui.main_act.MainActRepo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.Assert.assertTrue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActRepoTest {



    private lateinit var appDb:AppDatabase
    private lateinit var api: ApiService

    lateinit var mainActRepo: MainActRepo
    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()


    @Before
    fun setup(){
        mockWebServer.start()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        appDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext()
            ,AppDatabase::class.java).build()

        mainActRepo = MainActRepo(remoteDB = api, localDb = appDb)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        appDb.close()
    }

    @Test
    fun insertUser() = runBlocking {
        val userEntity = UserBody("Random name", "test@mail.com")
        mainActRepo.insertUserLocal(userEntity)
        val users = mainActRepo.getAllUserLocal()
        assertThat(users).contains(userEntity)
    }

    @Test
    fun deleteUser() = runBlocking{
        val userEntity = UserBody("Random name", "test@mail.com")
        mainActRepo.insertUserLocal(userEntity)
        mainActRepo.deleteUserLocal(userEntity)
        val users = mainActRepo.getAllUserLocal()
        assertThat(users).doesNotContain(userEntity)
    }


    @Test
    fun updateUser() = runBlocking {
        val userEntity = UserBody("Random name", "test@mail.com")
        mainActRepo.insertUserLocal(userEntity)
        val newUser = userEntity.copy(name = "Some Name")
        mainActRepo.updateUserLocal(newUser)
        val users = mainActRepo.getAllUserLocal()
        assertThat(users).contains(newUser)
    }


    @Test
    fun responseStatusFieldShouldBeSuccessOnSuccessResponse()=runBlocking{

            mockWebServer.enqueue(
                MockResponse()
                .setBody(MockResponseFileReader(
                    "api_responses/success.json").content).setResponseCode(200))

            val userBody = UserBody("random name","random@mail.com")
            val serverMockRes =  mainActRepo.createUser(userBody)

            assertTrue(serverMockRes.isSuccessful&&serverMockRes.body()?.status=="success")

    }



    @After
    fun shut(){
        mockWebServer.shutdown()
    }
}