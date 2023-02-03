//********************************************************
//Author_Name : Sheetal_Chaudhari
//Project     : Spotify_Api_Automation
//Date        : 29/01/2023
//********************************************************

package com.bridgelabz.qa.api_automation;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class ApiAutomationTest {

	String token;
	String userid;
	String playlistid;
	String username;
	String trackid = "6nDJIzi6BezsTcCdw8fmMg";

	@BeforeTest 

	public void getToken() {
		token = "Bearer BQBSl27rJV5FED2jcb_fF3kcTVHaSQV61dKnYB_SCMLY8Ud9OGvA7bpLH5UMocdwcvpidsPSnKG2YPrkzU4A60tF4EWSJfY5C6O8OMgUEv5JwMoBajaRGf7V5WEeuhSTmDAItc1uYvDPWj7Pgfi-6B2Z-VdJIP32hKXsl9zkKPeiO9JopEc16Pd7DXYQkNRS_3M1Sq2Z1SwK_6TWPMBfr2rCVmS9BEnmIYLYH6xlxLx6PEwwPaPtvWzwyMMix9pmXXo";
	}


	@Test(priority = 2)

	public void get_Current_User_Profile() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/me");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		userid = response.path("id");
		username = response.path("display_name");
		System.out.println("My current user id is = "+ userid);
		System.out.println("My current username is = "+ username);
		Assert.assertEquals("31krwvrsu6d2pzkyxab2wwocbgbi",userid );
		Assert.assertEquals("Abhishek Mishra", username);
		response.then().log().all();
		response.then().body("display_name",Matchers.equalTo("Abhishek Mishra"));

		System.out.println("Response Time of get_Current_User_Profile in ms = "+ response.timeIn(TimeUnit.MILLISECONDS) );
	}

	@Test(priority = 1)

	public void get_User_Profile() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/users/"+userid+"");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of get_User_Profile in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));
	}

	@Test(priority = 3)

	public void Create_Playlist() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.body("{\n"
						+ "  \"name\": \"Udit Narayan Hits\",\n"
						+ "  \"description\": \"New playlist description\",\n"
						+ "  \"public\": false\n"
						+ "}")
				.when()
				.post("https://api.spotify.com/v1/users/"+userid+"/playlists");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(201);
		playlistid = response.path("id");
		System.out.println("My current playlist id is = "+ playlistid);
		System.out.println("Response Time of Create_Playlist in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 4)

	public void Search_for_item() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.queryParam("q", "udit narayan")
				.queryParam("type","track")
				.when()
				.get("https://api.spotify.com/v1/search");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Search_for_item in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 5)


	public void Add_items_to_Playlist () {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.queryParam("uris","spotify:track:6nDJIzi6BezsTcCdw8fmMg,spotify:track:4RixtPhUXlci9ad4qIMj23,spotify:track:1QwVVMQ6AglyGK6CgRzkbz")

				.when()
				.post("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(201);
		System.out.println("Response Time of Add_items_to_Playlist in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 6)

	public void Get_Playlist_Items() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Get_Playlist_Items in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));



	}

	@Test(priority = 17)
	public void Remove_Playlist_Items () {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.body("{\n"
						+ "    \"tracks\": [\n"
						+ "        {\n"
						+ "            \"uri\": \"spotify:track:6nDJIzi6BezsTcCdw8fmMg\",\n"
						+ "            \"positions\": [0]\n"
						+ "        }\n"
						+ "        \n"
						+ "    ]\n"
						+ "}")
				.when()
				.delete("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Remove_Playlist_Items in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 8)

	public void Get_Playlist () {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlistid+"");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Get_Playlist in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 9)

	public void Get_Users_Playlists () {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/users/"+userid+"/playlists");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Get_Users_Playlists in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 10)

	public void Get_Current_Users_Playlists () {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/me/playlists");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Get_Current_Users_Playlists in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 11)

	public void Get_Playlist_Cover_Image() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlistid+"/images");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Get_Playlist_Cover_Image in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 12)

	public void Update_Playlist_Items() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.body("{\n"
						+ "  \"range_start\": 1,\n"
						+ "  \"insert_before\": 3,\n"
						+ "  \"range_length\": 2\n"
						+ "}")

				.when()
				.put("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Update_Playlist_Items in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 13)

	public void Change_Playlist_Details() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.body("{\n"
						+ "  \"name\": \"udit ji\",\n"
						+ "  \"description\": \"Updated playlist description\",\n"
						+ "  \"public\": false\n"
						+ "}")

				.when()
				.put("	https://api.spotify.com/v1/playlists/"+playlistid+"");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Change_Playlist_Details in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));


	}

	@Test(priority = 14)


	public void Get_Tracks() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/tracks/"+trackid+"");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Get_Tracks in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 15)

	public void Get_Tracks_Audio_Analysis() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("	https://api.spotify.com/v1/audio-analysis/"+trackid+"");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Get_Tracks_Audio_Analysis in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));
	}

	@Test(priority = 16)

	public void Get_Tracks_Audio_Features() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.queryParam("ids","4XEtEmnRgmNtDH9SayLCRR" )
				.when()
				.get("https://api.spotify.com/v1/audio-features");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Get_Tracks_Audio_Features in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));
	}

	@Test(priority = 17)

	public void Get_Track_Audio_Features() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/"+trackid+"");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Get_Track_Audio_Features in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));
	}

	@Test(priority = 7)

	public void Get_Several_Tracks() {

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.queryParam("ids","4XEtEmnRgmNtDH9SayLCRR" )
				.when()
				.get("	https://api.spotify.com/v1/tracks");
		response.prettyPrint(); 
		response.then().assertThat().statusCode(200);
		System.out.println("Response Time of Get_Several_Tracks in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}
}



