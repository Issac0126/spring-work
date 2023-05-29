package com.spring.myweb.command;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class KakaoUserVO {
	
	/*
	 @JsonProperty
	 Rest API 통신을 진행하다 보면 데이터를 주고 받을 때 JSON 형태의 데이터를 주로 이용한다.
	 json은 스네이크 케이스 방식을 사용하고, 서버단에서는 카멜 케이스 방식을 사용하다보니
	 서로 표현방식(이름)이 달라 데이터의 Key가 달라지는 경우가 발생한다.
	 이 때, @JsonProperty를 사용하면 key가 다른 값을 매칭할 수 있다.
	 */
	
	
	@JsonProperty("id") //이름이 다른 경우 JsonProperty 선언이 필요하다.
	private long userId;
	
	@JsonProperty("connected_at")
	private LocalDateTime connectedAt;
	
	@JsonProperty("kakao_account")
	private KakaoAccount kakaoAccount;
	@Getter @Setter @ToString
	static class KakaoAccount {
		
		// email처럼 이름이 완전히 동일한 경우, 제이슨프로퍼티를 붙일 필요가 없다.
		@JsonProperty("email")  
		private String email;
		
		private Profile profile; 
		@Getter @Setter @ToString
		static class Profile {
			
			private String nickname;
			
			@JsonProperty("profile_image_url")
			private String profileImageUrl;
			
			@JsonProperty("thumbnail_image_url")
			private String thumbnailImageUrl;
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
