package com.cybereast.tmdbapi.source.remote.retrofit


import com.cybereast.tmdbapi.models.ResponseModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiResponse {

    @GET("popular")
    fun getPopularMovieList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Observable<Response<ResponseModel>>
//    @GET
//    fun getDirectionRoutes(@Url url: String): Observable<DirectionObject>
//
//    @POST("token")
//    fun getGoogleToken(@Body authModel: GoogleAuthModel): Observable<Response<GoogleResponseModel>>
//
//    @POST("user/social")
//    fun socialLogin(@Body authModel: AuthModel): Observable<Response<ResponseMain>>
//
//    @Multipart
//    @POST("user/signup")
//    fun signUp(
//        @Part("socialId") socialId: RequestBody?,
//        @Part("firstName") firstName: RequestBody,
//        @Part("lastName") lastName: RequestBody,
//        @Part("phoneNumber") phoneNumber: RequestBody,
//        @Part("email") email: RequestBody,
//        @Part("password") password: RequestBody,
//        @Part avatar: MultipartBody.Part?,
//        @Part("deviceName") deviceName: RequestBody,
//        @Part("deviceIdentifier") deviceIdentifier: RequestBody,
//        @Part("fcmToken") fcmToken: RequestBody
//    ): Observable<Response<ResponseMain>>
//
//    @POST("user/signin")
//    fun simpleSignin(@Body authModel: AuthModel): Observable<Response<ResponseMain>>
//
//    @POST("user/otp/send")
//    fun sendOtp(@Body authModel: AuthModel): Observable<Response<ResponseMain>>
//
//    @POST("user/otp/validate")
//    fun validateOtp(@Body authModel: AuthModel): Observable<Response<ResponseMain>>
//
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "user/logout", hasBody = true)
//    fun logout(@Field("deviceIdentifier") deviceIdentifier: String): Observable<Response<ResponseMain>>
//
//    @GET("user/profile")
//    fun getProfile(@Body authModel: AuthModel): Observable<Response<ResponseMain>>
//
//    @PUT("user/reset/password")
//    fun resetPassword(@Body authModel: AuthModel): Observable<Response<ResponseMain>>
//
//    @PATCH("user/approval")
//    fun sellerApproval(@Body authModel: AuthModel): Observable<Response<ResponseMain>>

}

