---
slug: Java Record
title: Apple Music API 토큰 발급 받기
authors: [ siyeon ]
tags: [ Apple Music API, MusicKit, Apple Developer ]
---

안녕하세요. 스트릿 드랍에서 백엔드 개발을 하고 있는 손시연(Son Si-yeon) 입니다.

### 약 9천만 곡 가까이 되는 음악 데이터 베이스를 어떻게 구축할까요?
![](./2023-09-12-apple-music-api-token/search.png)

저희는 MVP를 빠르게 개발하기 위해, 크롤링보다는 음악 검색 API를 활용했습니다.

음악 검색 API로 Youtube Music, Spotify, Apple Music 등 다양한 후보군이 존재했습니다. 
그중 [Apple Music API - Search for Catalog Resources](https://developer.apple.com/documentation/applemusicapi/search_for_catalog_resources)를 사용하기로 결정했습니다.
별도의 계정 생성 없이 기존에 생성한 Apple Developer 계정으로 음악 검색 API를 사용할 수 있기 때문입니다.

이번 호스팅에서는 Apple Music API 토큰 발급 방법에 대해 알아보겠습니다.

## Apple Music API 토큰 발급받기
> [공식 문서](https://developer.apple.com/documentation/applemusicapi/generating_developer_tokens)를 참고하였습니다.

### 1. Apple Developer 가입
Apple Music API를 사용하기 위해서는 [Apple Developer](https://developer.apple.com) 계정이 필요합니다. 계정 등록비는 US$99(한화 129,000원)으로, 유지 기간은 1년입니다 😭

### 2. Identifiers 생성
1) [Apple Developer Member Center](https://developer.apple.com/account)로 이동합니다. 프로그램 리소스 > Certificates, Identifiers & Profiles > Identifiers 메뉴를 선택합니다.
![](./2023-09-12-apple-music-api-token/identifiers-1.png)

2) 새로운 Identifiers를 생성합니다.
![](./2023-09-12-apple-music-api-token/identifiers-2.png)

3) Media IDs를 선택합니다.
![](./2023-09-12-apple-music-api-token/identifiers-3.png)

4) Description과 Identifier를 입력하고, MusicKit을 선택합니다.
![](./2023-09-12-apple-music-api-token/identifiers-4.png)

5) Media IDs Identifier가 생성된 것을 확인할 수 있습니다.
![](./2023-09-12-apple-music-api-token/identifiers-5.png)


### 3. Keys 생성
1) 프로그램 리소스 > Certificates, Identifiers & Profiles > Keys 메뉴를 선택합니다. 새로운 Keys를 생성합니다.
![](./2023-09-12-apple-music-api-token/keys-1.png)

2) Media Services(MusicKit, ShazamKit)를 선택합니다.
![](./2023-09-12-apple-music-api-token/keys-2.png)

3) 앞에서 생성한 Identifiers를 선택합니다.
![](./2023-09-12-apple-music-api-token/keys-3.png)

4) Key(.p8 파일)를 생성하였습니다. 발급된 인증 키는 1회만 다운로드 가능하니, 안전한 위치에 저장합니다. Key ID와 Team ID를 확인할 수 있습니다.
![](./2023-09-12-apple-music-api-token/keys-4.png)

5) View Key Details에서도 Key ID와 Team ID를 확인할 수 있습니다.
![](./2023-09-12-apple-music-api-token/keys-5.png)

6) 생성된 Key를 다운로드하여 확인합니다.
![](./2023-09-12-apple-music-api-token/keys-6.png)


### 4. JWT 형식으로 토큰 생성
[Apple Music API 공식 문서](https://developer.apple.com/documentation/applemusicapi/generating_developer_tokens#3001626)를 확인해 보면 Apple Music API는 JWT(JSON Web Token) 사양을 지원합니다.
![](./2023-09-12-apple-music-api-token/jwt-1.png)

앞서 생성한 Key, Key ID, Team ID를 활용하여 ES256 알고리즘으로 서명된 개발자 토큰을 생성해 보겠습니다.
![](./2023-09-12-apple-music-api-token/jwt-2.png)

1) python JWT 라이브러리 설치합니다.
```shell
sudo pip install pyjwt
```

2) 암호화 패키지를 설치합니다.
```shell
sudo pip install cryptography
```

3) 본인의 Key, Key ID, Team ID를 수정합니다.
```python
import datetime
import jwt

key = """-----BEGIN PRIVATE KEY-----
ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123  
-----END PRIVATE KEY-----"""
keyId = '0123456789'
teamId = '9876543210'
alg = 'ES256'

time_now = datetime.datetime.now()
time_expired = datetime.datetime.now() + datetime.timedelta(hours=12)

headers = {
	'alg': alg,
	'kid': keyId
}
payload = {
	'iss': teamId,
	'exp': int(time_expired.strftime("%s")),
	'iat': int(time_now.strftime("%s"))
}

if __name__ == '__main__':
	token = jwt.encode(payload, key, algorithm=alg, headers=headers)

	print(token)
```

4) 파이썬 코드를 실행하면 JWT 토큰을 얻을 수 있습니다.
Apple Music API 해더에 토큰을 추가하여 API 요청을 보내면 됩니다!

```
curl -X 'GET' \
  'https://api.music.apple.com/v1/catalog/kr/search?types=songs&limit=10&term=apple' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123'
```
![](./2023-09-12-apple-music-api-token/jwt-3.png)

## 스트릿 드랍에서는
스트릿 드랍 검색 서버에 요청을 보내면

```
curl -X 'GET' \
  'https://search.street-drop.com/music?keyword=apple' \
  -H 'accept: */*'
```

내부 로직을 거쳐, [Apple Music API - Search for Catalog Resources](https://developer.apple.com/documentation/applemusicapi/search_for_catalog_resources)에 다음과 같은 요청을 보내게 됩니다.

```
curl -X 'GET' \
  'https://api.music.apple.com/v1/catalog/kr/search?types=songs&limit=10&term=apple' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123'
```

내부 코드는 다음과 같이 작성하였습니다. 사용자가 입력한 keyword를 토대로, iTunes Store 한국 지역(kr), 노래들(songs), 10개(limits)를 가져오는 로직입니다. 자세한 소스코드는 [스트릿 드랍 서버 깃허브](https://github.com/depromeet/street-drop-server/tree/dev/backend/streetdrop-search)을 참고해 주세요.

```java
@Cacheable(value = "music", key = "#keyword")
public MusicInfoListResponseDto searchMusic(String keyword) {
    String appleMusicApiKey = appleMusicConfig.getAppleMusicApiKey();
    WebClient webClient = WebClient.builder().baseUrl("https://api.music.apple.com").build();

    Mono<AppleMusicResponseDto> response = webClient.get()
            .uri(uriBuilder -> uriBuilder
                    .path("/v1/catalog/kr/search")
                    .queryParam("types", "songs")
                    .queryParam("limit", 10)
                    .queryParam("term", keyword)
                    .build())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + appleMusicApiKey)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                    clientResponse ->
                    {
                        if (clientResponse.statusCode() == HttpStatus.UNAUTHORIZED) {
                            eventPublisher.publishEvent(new AppleMusicApiKeyRefreshEvent());
                            throw new RuntimeException("error");
                        }
                        throw new RuntimeException("error");
                    }
            )
            .bodyToMono(AppleMusicResponseDto.class);
    return MusicInfoListResponseDto.ofAppleMusicResponseDto(response.block());
}
```