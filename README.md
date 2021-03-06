# NewsViewer
인터넷 뉴스를 한눈에 볼 수 있는 모바일 앱

### 프로젝트 소개
```
인터넷 뉴스를 한눈에 볼 수 있는 뷰어 앱
각 html을 파싱하여 RecyclerView를 이용하여 한눈에 사용자가 볼 수 있도록 개발
```

### 사용 언어 및 라이브러리, 디자인 패턴
```
사용 언어 : Kotlin 
라이브러리 : Glide, Data Binding, Coroutine, Swipe layout
디자인패턴 : MVVM 패턴
```

### 코드 리뷰 
```
1. 클래스 내에서만 사용하는 프로퍼티, 메서드에 private 접근제한자가 붙어 있지 않음 
2. 뷰에서 Coroutine 생성, 뷰모델 내에서 관리하면 더 좋았을 것 
3. 좀 더 빨리 HTML 태그를 파싱할 수 없을까?
4. 액티비티가 종료될 경우 코루틴 취소 처리 해줄 것
5. Model에 인터넷 접근하는 코드 위치 변경
6. 뷰모델에서 비즈니스 로직 수행
```

### 어플리케이션 화면 

![image](https://user-images.githubusercontent.com/44944031/87563909-ba6c3a00-c6fa-11ea-821c-abe0cf587da8.png)

